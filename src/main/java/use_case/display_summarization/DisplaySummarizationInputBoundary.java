package use_case.display_summarization;

/**
 * The Input Boundary for the Analyze Outfit use case.
 */
public interface DisplaySummarizationInputBoundary {

    /**
     * Switches to the Home View.
     */
    void switchtoHomeView();

    /**
     * Executes the Analyze Outfit use case.
     * @param inputData The input data for the use case.
     */
    void execute(DisplaySummarizationInputData inputData);
}
