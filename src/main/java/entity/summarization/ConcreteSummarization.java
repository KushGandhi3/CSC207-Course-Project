package entity.summarization;

/**
 * Concrete implementation of the Summarization interface.
 */
public class ConcreteSummarization implements Summarization {

    private final String summary;
    private final String outfit;
    private final String travel;

    public ConcreteSummarization(String summary, String outfit, String travel) {
        this.summary = summary;
        this.outfit = outfit;
        this.travel = travel;
    }

    @Override
    public String getWeatherSummary() {
        return summary;
    }

    @Override
    public String getOutfitSuggestion() {
        return outfit;
    }

    @Override
    public String getTravelAdvice() {
        return travel;
    }
}
