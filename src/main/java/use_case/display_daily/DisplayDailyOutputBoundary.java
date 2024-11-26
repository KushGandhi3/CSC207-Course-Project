package use_case.display_daily;

/**
 * The output boundary for the Display Daily use-case.
 */
public interface DisplayDailyOutputBoundary {

    /**
     * Prepares the success view for the Display Daily use-case.
     * @param displayDailyOutputData the output data
     */
    void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData);

    /**
     * Prepares the failure view for the Display Daily Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches the view to the home view.
     */
    void switchToHomeView();

}
