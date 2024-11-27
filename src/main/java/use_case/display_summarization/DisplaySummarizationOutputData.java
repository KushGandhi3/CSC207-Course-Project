package use_case.display_summarization;

/**
 * Output Data for the Summarization Use Case.
 */
public class DisplaySummarizationOutputData {

    private final String weatherSummary;
    private final String outfitSuggestion;
    private final String travelAdvice;

    public DisplaySummarizationOutputData(String weather, String outfit, String travel) {
        this.weatherSummary = weather;
        this.outfitSuggestion = outfit;
        this.travelAdvice = travel;
    }

    public String getWeather() {
        return weatherSummary;
    }

    public String getOutfit() {
        return outfitSuggestion;
    }

    public String getTravel() {
        return travelAdvice;
    }
}
