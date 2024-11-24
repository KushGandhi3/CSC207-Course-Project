package entity.summarization;

/**
 * The factory for summarization entity.
 */
public interface SummarizationFactory {

    /**
     * Create a new summarization object.
     * @param summary the summary of the weather.
     * @param outfit the outfit suggestion based on the weather.
     * @param travel the travel advice based on the weather.
     * @return the new summarization object.
     */
    Summarization createSummarization(String summary, String outfit, String travel);
}
