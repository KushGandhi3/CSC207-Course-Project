package interface_adapter.display_summarization;

import interface_adapter.ViewModel;

/**
 * The ViewModel for when the user wants to analyze an outfit.
 */
public class DisplaySummarizationViewModel extends ViewModel<DisplaySummarizationState> {

    public DisplaySummarizationViewModel() {
        super("Summarization");
        setState(new DisplaySummarizationState());
    }
}
