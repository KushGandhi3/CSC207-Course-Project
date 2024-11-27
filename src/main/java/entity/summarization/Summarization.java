package entity.summarization;

/**
 * The interface for the summarization.
 */
public interface Summarization {

    /**
     * Get the weather summary.
     * @return the summary
     */
    String getWeatherSummary();

    /**
     * Get the outfit suggestions.
     * @return the outfit suggestions
     */
    String getOutfitSuggestion();

    /**
     * Get the travel advice.
     * @return the travel advice.
     */
    String getTravelAdvice();
}
