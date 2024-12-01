package use_case.display_history;

/**
 * Interface representing the output boundary for displaying history in the application.
 */
public interface DisplayHistoryOutputBoundary {

    /**
     * Prepare the success view for the history use case.
     * @param cities the locations to display
     */
    void prepareSuccessView(DisplayHistoryOutputData cities);

    /**
     * Prepares the failure view for the history use case.
     * @param errorMessage the explanation of the failure.
     */
    void prepareFailureView(String errorMessage);

    /**
     * Switch to home view.
     */
    void switchToHomeView();
}