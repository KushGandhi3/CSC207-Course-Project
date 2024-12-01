package use_case.display_home;

/**
 * Input Boundary for actions which are related to the DisplayHome.
 */
public interface DisplayHomeInputBoundary {
    /**
     * Executes the DisplayHome use case.
     * @param displayHomeInputData the input data
     */
    void execute(DisplayHomeInputData displayHomeInputData);

    /**
     * Switches the view to the Daily View.
     */
    void switchToDailyView();

//    /**
//     * Switches the view to the Hourly View.
//     */
//    void switchToHourlyView();

    /**
     * Switches the view to the Checker View.
     */
    void switchToCheckerView();

    /**
     * Switches the view to the Summary View.
     */
    void switchToSummaryView();

    /**
     * Switches the view to the History View.
     */
    void switchToHistoryView();

}
