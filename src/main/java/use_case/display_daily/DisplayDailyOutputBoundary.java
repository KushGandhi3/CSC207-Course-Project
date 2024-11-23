package use_case.display_daily;

/**
 * The output boundary for the Display Weekly use-case.
 */
public interface DisplayDailyOutputBoundary {

    /**
     * Prepares the success view for the Display Weekly use-case.
     * @param displayDailyOutputData the output data
     */
    void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData);

    /**
     * Prepares the failure view for the Display Weekly Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
