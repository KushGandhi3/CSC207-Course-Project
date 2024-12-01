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
     * @param selectedWeekday the selected weekday on the view
     */
    public void execute(String selectedWeekday) {
        final DisplayDailyInputData displayDailyInputData = new DisplayDailyInputData(selectedWeekday);

        this.displayDailyInteractor.execute(displayDailyInputData);
    }

    /**
     * Executes the Display Daily Use Case without a selected weekday.
     */
    public void execute() {
        this.displayDailyInteractor.execute();
    }

    public void switchToHomeView() {
        this.displayDailyInteractor.switchToHomeView();
    }

}
