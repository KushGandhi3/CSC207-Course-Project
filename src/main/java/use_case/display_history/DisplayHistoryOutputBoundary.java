package use_case.display_history;

public interface DisplayHistoryOutputBoundary {

    /**
     * Prepare the success view for the history use case
     * @param locations the locations to display
     */
    void prepareSuccessView(DisplayHistoryOutputData locations);

    /**
     * Prepare the switch to home view
     */
    void prepareHomeView();
}
