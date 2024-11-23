package use_case.display_daily;

/**
 * Input Boundary for actions which are related to the displaying weekly
 * weather use-case.
 */
public interface DisplayDailyInputBoundary {

    /**
     * Executes the display weekly use case.
     * @param displayDailyInputData the input data
     */
    void execute(DisplayDailyInputData displayDailyInputData);

}
