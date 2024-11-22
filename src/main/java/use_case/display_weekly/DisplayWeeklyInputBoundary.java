package use_case.display_weekly;

/**
 * Input Boundary for actions which are related to the displaying weekly
 * weather use-case.
 */
public interface DisplayWeeklyInputBoundary {

    /**
     * Executes the display weekly use case.
     * @param displayWeeklyInputData the input data
     */
    void execute(DisplayWeeklyInputData displayWeeklyInputData);

}
