package data_access.summarization;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.summarization.Summarization;
import entity.summarization.SummarizationFactory;
import exception.APICallException;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;

/**
 * This class makes API calls to request summarization data from OpenAI.
 */
public class SummarizationSummaryDAO implements DisplaySummarizationSummaryDAI {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final SummarizationFactory summarizationFactory;
    private final Dotenv dotenv = Dotenv.load();
    private final String apikey = dotenv.get("OPENAI_API_KEY");

    public SummarizationSummaryDAO(SummarizationFactory summarizationFactory) {
        this.summarizationFactory = summarizationFactory;
    }

    /**
     * Returns Summarization entity with updated summarization information from the OpenAI API.
     *
     * @param prompt the prompt to use for the summarization which includes weather data.
     * @return a Summarization entity
     * @throws APICallException if the request fails or the API Key is not set
     * @throws RuntimeException if the request fails
     */
    public Summarization getSummarization(String prompt) throws APICallException {
        final OkHttpClient client = new OkHttpClient();

        // Define the JSON schema for the response
        final JSONObject schema = new JSONObject();
        schema.put("type", "object");
        final JSONObject properties = new JSONObject();
        properties.put("weather_summary", new JSONObject().put("type", "string"));
        properties.put("outfit_suggestion", new JSONObject().put("type", "string"));
        properties.put("travel_advice", new JSONObject().put("type", "string"));
        schema.put("properties", properties);
        schema.put("required", new JSONArray().put("weather_summary").put("outfit_suggestion").put("travel_advice"));
        schema.put("additionalProperties", false);

        // Add the schema to the functions field
        final JSONArray functions = new JSONArray();
        final JSONObject function = new JSONObject();
        function.put("name", "get_weather_summary");
        function.put("parameters", schema);
        functions.put(function);

        // Create the request body
        final JSONObject jsonBody = new JSONObject();

        jsonBody.put("model", "gpt-4-0613"); // Use a model that supports structured responses
        jsonBody.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", prompt)));
        jsonBody.put("functions", functions);
        jsonBody.put("function_call", new JSONObject().put("name", "get_weather_summary")); // Explicit function call

        // Build the HTTP request
        final okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonBody.toString(),
                okhttp3.MediaType.parse("application/json"));
      
        final Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apikey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        // Execute the request
        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {

                throw new IOException("API Call Unsuccessful. HTTP Code: " + response.code());
            }
            if (response.body() == null) {
                throw new IOException("API Returned No Response.");
            }

            String responseBody = response.body().string();
            System.out.println("Raw API Response: " + responseBody);

            // Parse the JSON response
            final JSONObject responseJson = new JSONObject(responseBody);
            final JSONArray choices = responseJson.getJSONArray("choices");
            if (choices.isEmpty()) {
                throw new IOException("API Returned No Choices.");
            }

            final JSONObject choice = choices.getJSONObject(0);
            final JSONObject message = choice.getJSONObject("message");
            if (!message.has("function_call")) {
                throw new IOException("API Response Does Not Contain Expected Function Call.");
            }

            final JSONObject functionCall = message.getJSONObject("function_call");
            final JSONObject arguments = new JSONObject(functionCall.getString("arguments"));

            // Extract fields from the structured response
            final String weatherSummary = arguments.getString("weather_summary");
            final String outfitSuggestion = arguments.getString("outfit_suggestion");
            final String travelAdvice = arguments.getString("travel_advice");

            return this.summarizationFactory.createSummarization(weatherSummary, outfitSuggestion, travelAdvice);
        } catch (IOException exception) {
            throw new APICallException("Failed To Get Summarization. " + exception.getMessage(), exception);
        }
    }
}
