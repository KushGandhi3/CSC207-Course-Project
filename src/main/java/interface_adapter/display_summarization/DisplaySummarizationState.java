package interface_adapter.display_summarization;

/**
 * The State of the Summarization Use Case.
 */
public class DisplaySummarizationState {

    private String weatherSummary = "";
    private String outfitSuggestion = "";
    private String travelAdvice = "";

    public String getWeatherSummary() {
        return weatherSummary;
    }

    public String getOutfitSuggestion() {
        return outfitSuggestion;
    }

    public String getTravelAdvice() {
        return travelAdvice;
    }

    public void setWeather(String weather) {
        this.weatherSummary = weather;
    }

    public void setOutfit(String outfit) {
        this.outfitSuggestion = outfit;
    }

    public void setTravel(String travel) {
        this.travelAdvice = travel;
    }

}
