package data_access.summarization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import exception.ApiCallException;
import org.json.JSONObject;

import entity.summarization.Summarization;
import entity.summarization.SummarizationFactory;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;

/**
 * In Memory Data Object for simulating the creation of the Summarization entity.
 */
public class InMemorySummarizationDAO implements DisplaySummarizationSummaryDAI {

    private static final String IN_MEMORY_SUMMARIZATION_DATA_PATH = "src/main/resources/data/InMemorySummarization"
            + "Data.json";

    private final SummarizationFactory summarizationFactory;

    public InMemorySummarizationDAO(SummarizationFactory summarizationFactory) {
        this.summarizationFactory = summarizationFactory;
    }

    /**
     * Returns Summarization entity with updated summary information from in memory summarization data file.
     * @param prompt The prompt to API. Note: not needed for the InMemorySummarizationDAO.
     * @return Summarization a Summarization entity
     * @throws ApiCallException if the in memory summarization data cannot be accessed.
     */
    @Override
    public Summarization getSummarization(String prompt) throws ApiCallException {
        try {
            // Read the JSON response from the file
            final String jsonContent = Files.readString(Path.of(IN_MEMORY_SUMMARIZATION_DATA_PATH));
            final JSONObject jsonObject = new JSONObject(jsonContent);

            // Navigate to the nested structure
            final JSONObject choices = jsonObject.getJSONArray("choices").getJSONObject(0);
            final JSONObject message = choices.getJSONObject("message");
            final JSONObject functionCall = message.getJSONObject("function_call");
            final String arguments = functionCall.getString("arguments");

            // Parse the arguments field into a JSON object
            final JSONObject argumentsJson = new JSONObject(arguments);

            // Extract required fields
            if (!argumentsJson.has("weather_summary") || !argumentsJson.has("outfit_suggestion")
                    || !argumentsJson.has("travel_advice")) {
                throw new IOException("Missing required fields in the JSON data.");
            }

            final String weatherSummary = argumentsJson.getString("weather_summary");
            final String outfitSuggestion = argumentsJson.getString("outfit_suggestion");
            final String travelAdvice = argumentsJson.getString("travel_advice");

            // Create and return the Summarization entity
            return this.summarizationFactory.createSummarization(weatherSummary, outfitSuggestion, travelAdvice);
        }
        catch (IOException exception) {
            throw new ApiCallException("Failed to load in-memory summarization data: " + exception.getMessage(),
                    exception);
        }
    }

}
