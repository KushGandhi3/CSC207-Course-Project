package use_case.display_summarization;

import entity.summarization.Summarization;
import exception.APICallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplaySummarizationSummaryDAI {

    /**
     * Get the summarization from the OpenAI API.
     * @param prompt the prompt to use for the summarization which includes weather data.
     * @return the summarization
     * @throws APICallException if the request fails.
     */
    Summarization getSummarization(String prompt) throws APICallException;
}
