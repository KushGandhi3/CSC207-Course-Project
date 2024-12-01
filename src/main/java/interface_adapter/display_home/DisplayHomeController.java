package interface_adapter.display_home;

import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeInputData;
//import use_case.display_summarization.DisplaySummarizationInputBoundary;

/**
 * The controller for the Display Home Use Case.
 */
public class DisplayHomeController {

    private final DisplayHomeInputBoundary displayHomeInteractor;

    public DisplayHomeController(DisplayHomeInputBoundary displayHomeInteractor) {
        this.displayHomeInteractor = displayHomeInteractor;
    }

    /**
     * Executes the Display Home Use Case.
     * @param cityName the name of the city to fetch weather data for
     */
    public void execute(String cityName) {
        // Create the input data for the use case
        final DisplayHomeInputData displayHomeInputData = new DisplayHomeInputData(cityName);

        displayHomeInteractor.execute(displayHomeInputData);
    }

    public void switchToDailyView() {
        this.displayHomeInteractor.switchToDailyView();
    }

    // TODO: Implement switchToHourlyView()
//    public void switchToHourlyView() {
//        this.displayHomeInteractor.switchToHourlyView();
//    }

    public void switchToCheckerView() {
        this.displayHomeInteractor.switchToCheckerView();
    }

    // TODO: Implement switchToSummaryView()
//    public void switchToSummaryView() {
//        this.displayHomeInteractor.switchToSummaryView();
//    }

    // TODO: Implement switchToHistoryView()
//    public void switchToHistoryView() {
//        this.displayHomeInteractor.switchToHistoryView();
//    }

}
