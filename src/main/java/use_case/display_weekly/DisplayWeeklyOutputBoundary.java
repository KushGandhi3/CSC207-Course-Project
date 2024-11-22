package use_case.display_weekly;

/**
 * The output boundary for the Display Weekly use-case.
 */
public interface DisplayWeeklyOutputBoundary {

    /**
     * Prepares the success view for the Display Weekly use-case.
     * @param displayWeeklyOutputData the output data
     */
    void prepareSuccessView(DisplayWeeklyOutputData displayWeeklyOutputData);

    /**
     * Prepares the failure view for the Display Weekly Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
