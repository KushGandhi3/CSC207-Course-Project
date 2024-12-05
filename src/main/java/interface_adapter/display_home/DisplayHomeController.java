package interface_adapter.display_home;

import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeInputData;

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

    /**
     * Executes the Display Home Use Case.
     */
    public void execute() {
        displayHomeInteractor.execute();
    }

    /**
     * Switches the current view to the Daily View within the Display Home Use Case.
     */
    public void switchToDailyView() {
        this.displayHomeInteractor.switchToDailyView();
    }

    /**
     * Switches the current view to the Checker View within the Display Home Use Case.
     */
    public void switchToCheckerView() {
        this.displayHomeInteractor.switchToCheckerView();
    }

    /**
     * Switches the current view to the Summary View within the Display Home Use Case.
     */
    public void switchToSummaryView() {
        this.displayHomeInteractor.switchToSummaryView();
    }

    /**
     * Switches the current view to the History View within the Display Home Use Case.
     */
    public void switchToHistoryView() {
        this.displayHomeInteractor.switchToHistoryView();
    }

}
