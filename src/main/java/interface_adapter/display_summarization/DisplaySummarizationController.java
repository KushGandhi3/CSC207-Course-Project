package interface_adapter.display_summarization;

import use_case.display_summarization.DisplaySummarizationInputBoundary;

/**
 * The Controller for when the user wants to analyze an outfit.
 */
public class DisplaySummarizationController {

    private final DisplaySummarizationInputBoundary summarizationUseCaseInteractor;

    public DisplaySummarizationController(DisplaySummarizationInputBoundary summarizationUseCaseInteractor) {
        this.summarizationUseCaseInteractor = summarizationUseCaseInteractor;
    }

    /**
     * Executes the Summarization Use Case.
     */
    public void execute() {
        summarizationUseCaseInteractor.execute();
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
    }
}
