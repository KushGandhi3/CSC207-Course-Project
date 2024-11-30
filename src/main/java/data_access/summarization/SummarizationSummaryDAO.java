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
        final String apiurl = "https://api.openai.com/v1/chat/completions";

        // Create the request body
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "gpt-4o-mini");
        final JSONArray messages = new JSONArray();
        final JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.put(message);
        jsonBody.put("messages", messages);

        // build http request
        final okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonBody.toString(),
                okhttp3.MediaType.parse("application/json"));
        final Request request = new Request.Builder()
                .url(apiurl)
                .addHeader("Authorization", "Bearer " + apikey)
                .post(body)
                .build();

        // execute request
        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new APICallException("API call unsuccessful! " + response);
            }
            if (response.body() == null) {
                throw new APICallException("API returned no response!");
            }

            final JSONObject responseJson = new JSONObject(response.body().string());
            final JSONArray choices = responseJson.getJSONArray("choices");
            final JSONObject choice = choices.getJSONObject(0);
            final String completion = choice.getJSONObject("message").getString("content");

            // Parse the completion and create a Summarization object
            final JSONObject parsedCompletion = new JSONObject(completion);
            final String weatherSummary = parsedCompletion.getString("weather_summary");
            final String outfitSuggestion = parsedCompletion.getString("outfit_suggestion");
            final String travelAdvice = parsedCompletion.getString("travel_advice");

            // Create the Summarization object
            return this.summarizationFactory.createSummarization(weatherSummary, outfitSuggestion, travelAdvice);
        }
        catch (APICallException exception) {
            throw new APICallException("Failed to get summarization!", exception);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
