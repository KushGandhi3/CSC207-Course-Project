package use_case.display_summarization;

/**
 * The Input Boundary for the Analyze Outfit use case.
 */
public interface DisplaySummarizationInputBoundary {

    /**
     * Executes the Summarization use case.
     */
    void execute();

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();
}
