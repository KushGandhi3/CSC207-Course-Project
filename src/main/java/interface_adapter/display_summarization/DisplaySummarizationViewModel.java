package interface_adapter.display_summarization;

import interface_adapter.ViewModel;

/**
 * The ViewModel for when the user wants to analyze an outfit.
 */
public class DisplaySummarizationViewModel extends ViewModel<DisplaySummarizationState> {

    public static final String TITLE_LABEL = "AI Summarization";
    public static final String SUMMARY_TITLE = "Weather Summary";
    public static final String OUTFIT_LABEL = "Outfit Suggestion";
    public static final String TRAVEL_LABEL = "Travel Advice";

    public DisplaySummarizationViewModel() {
        super("summarization");
        setState(new DisplaySummarizationState());
    }
}
