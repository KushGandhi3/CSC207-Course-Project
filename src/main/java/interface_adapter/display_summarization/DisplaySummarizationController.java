package interface_adapter.display_summarization;

import use_case.display_summarization.DisplaySummarizationInputBoundary;

/**
 * Controller for the summarization use case.
 */
public class DisplaySummarizationController {

    private final DisplaySummarizationInputBoundary summarizationInteractor;

    public DisplaySummarizationController(DisplaySummarizationInputBoundary summarizationInteractor) {
        this.summarizationInteractor = summarizationInteractor;
    }

    /**
     * Executes the Summarization Use Case.
     */
    public void execute() {
        summarizationInteractor.execute();
    }

    /**
     * Executes the "Switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        summarizationInteractor.switchToHomeView();
    }
}
