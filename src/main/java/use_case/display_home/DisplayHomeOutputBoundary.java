package use_case.display_home;

/**
 * The output boundary for the DisplayHome Use Case.
 */
public interface DisplayHomeOutputBoundary {
    /**
     * Prepares the success view for the DisplayHome Use Case
     * @param outputData the output data
     */
    void prepareSuccessView(DisplayHomeOutputData outputData);


    /**
     * Prepares the failure view for the DisplayHome Use Case.
     * @param errorMessage the explanation of the failure.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches the view to the Daily View.
     */
    void switchToDailyView();
//      /**
//       * Switches the view to the Hourly View.
//       */
////    void switchToHourlyView();

    /**
     * Switches the view to the Checker View.
     */
    void switchToCheckerView();

//    /**
//     * Switches the view to the Summary View.
//     */
//    void switchToSummaryView();

//    /**
//     * Switches the view to the History View.
//     */
//    void switchToHistoryView();

}