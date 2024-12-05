package data_access.summarization;

import java.io.IOException;

import exception.ApiCallException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.summarization.Summarization;
import entity.summarization.SummarizationFactory;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;

/**
 * This class makes API calls to request summarization data from OpenAI.
 */
public class SummarizationSummaryDAO implements DisplaySummarizationSummaryDAI {

    // Repeated Words
    private static final String TYPE = "type";
    private static final String WEATHERSUMMARY = "weather_summary";
    private static final String OUTFITSUGGESTION = "outfit_suggestion";
    private static final String TRAVELADVICE = "travel_advice";
    private static final String STRING = "string";
    private static final String FUNCTIONCALL = "function_call";

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
     * @throws ApiCallException if the request fails or the API Key is not set
     * @throws RuntimeException if the request fails
     */
    public Summarization getSummarization(String prompt) throws ApiCallException {
        final OkHttpClient client = new OkHttpClient();

        // Define the JSON schema for the response
        final JSONObject schema = new JSONObject();
        schema.put(TYPE, "object");
        final JSONObject properties = new JSONObject();
        properties.put(WEATHERSUMMARY, new JSONObject().put(TYPE, STRING));
        properties.put(OUTFITSUGGESTION, new JSONObject().put(TYPE, STRING));
        properties.put(TRAVELADVICE, new JSONObject().put(TYPE, STRING));
        schema.put("properties", properties);
        schema.put("required", new JSONArray().put(WEATHERSUMMARY).put(OUTFITSUGGESTION).put(TRAVELADVICE));
        schema.put("additionalProperties", false);

        // Add the schema to the functions field
        final JSONArray functions = new JSONArray();
        final JSONObject function = new JSONObject();
        function.put("name", "get_weather_summary");
        function.put("parameters", schema);
        functions.put(function);

        // Create the request body
        final JSONObject jsonBody = new JSONObject();

        jsonBody.put("model", "gpt-4-0613");
        jsonBody.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", prompt)));
        jsonBody.put("functions", functions);
        jsonBody.put(FUNCTIONCALL, new JSONObject().put("name", "get_weather_summary"));

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

            final String responseBody = response.body().string();

            // Parse the JSON response
            final JSONObject arguments = getJsonObject(responseBody);

            // Extract fields from the structured response
            final String weatherSummary = arguments.getString(WEATHERSUMMARY);
            final String outfitSuggestion = arguments.getString(OUTFITSUGGESTION);
            final String travelAdvice = arguments.getString(TRAVELADVICE);

            return this.summarizationFactory.createSummarization(weatherSummary, outfitSuggestion, travelAdvice);
        }
        catch (IOException exception) {
            throw new ApiCallException("Failed To Get Summarization. " + exception.getMessage(), exception);
        }
    }

    @NotNull
    private static JSONObject getJsonObject(String responseBody) throws IOException {
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

        final JSONObject functionCall = message.getJSONObject(FUNCTIONCALL);
        return new JSONObject(functionCall.getString("arguments"));
    }
}

