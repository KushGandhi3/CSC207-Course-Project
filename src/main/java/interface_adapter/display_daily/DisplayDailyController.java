package interface_adapter.display_daily;

import use_case.display_daily.DisplayDailyInputBoundary;
import use_case.display_daily.DisplayDailyInputData;

/**
 * The controller for the Display Daily Weather Use Case.
 */
public class DisplayDailyController {

    private final DisplayDailyInputBoundary displayDailyInteractor;

    public DisplayDailyController(DisplayDailyInputBoundary displayDailyInteractor) {
        this.displayDailyInteractor = displayDailyInteractor;
    }

    /**
     * Executes the Display Daily Use Case.
     * @param city the city to show weather for
     */
    public void execute(String city) {
        final DisplayDailyInputData displayDailyInputData = new DisplayDailyInputData(city);

        this.displayDailyInteractor.execute(displayDailyInputData);
    }

}
