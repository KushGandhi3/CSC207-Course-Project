package data_access.summarization;

import entity.summarization.Summarization;
import exception.APICallException;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;

/**
 * In memory implementation of the Outfit data access object.
 */
public class InMemorySummarizationDAO implements DisplaySummarizationSummaryDAI {
    @Override
    public Summarization getSummarization(String prompt) throws APICallException {
        return null;
    }
}
