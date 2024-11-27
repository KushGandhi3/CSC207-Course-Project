package use_case.display_summarization;

/**
 * Interface representing the output boundary for displaying summarization results.
 */
public interface DisplaySummarizationOutputBoundary {

    /**
     * Prepares the success view for the Summarization Use Case.
     * @param outputData the output data of the OpenAI API response
     */
    void prepareSuccessView(DisplaySummarizationOutputData outputData);

    /**
     * Prepares the failure view for the Summarization Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailureView(String errorMessage);

    /**
     * Prepare to switch to the Home View.
     */
    void prepareHomeView();

}
