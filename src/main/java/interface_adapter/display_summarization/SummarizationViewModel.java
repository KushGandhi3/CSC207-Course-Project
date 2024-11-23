package interface_adapter.display_summarization;

import interface_adapter.ViewModel;

/**
 * The ViewModel for when the user wants to analyze an outfit.
 */
public class SummarizationViewModel extends ViewModel<SummarizationState> {

    public SummarizationViewModel() {
        super("Analyze Outfit");
        setState(new SummarizationState());
    }
}
