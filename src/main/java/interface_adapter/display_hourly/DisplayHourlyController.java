package interface_adapter.display_hourly;

import use_case.display_hourly.DisplayHourlyInputBoundary;
import use_case.display_hourly.DisplayHourlyInputData;

/**
 * Controller for handling hourly weather display actions.
 * It acts as the interface between the user input and the use case interactor.
 */
public class DisplayHourlyController {
    private final DisplayHourlyInputBoundary displayHourlyInputBoundary;

    /**
     * Constructs a DisplayHourlyController with a specific interactor.
     *
     * @param displayHourlyInputBoundary the interactor to handle use case logic
     */
    public DisplayHourlyController(DisplayHourlyInputBoundary displayHourlyInputBoundary) {
        this.displayHourlyInputBoundary = displayHourlyInputBoundary;
    }

    /**
     * Executes the use case for displaying hourly weather based on a selected time.
     *
     * @param selectTime the selected time for hourly weather
     */
    public void execute(String selectTime) {
        final DisplayHourlyInputData displayHourlyInputData = new DisplayHourlyInputData(selectTime);
        this.displayHourlyInputBoundary.execute(displayHourlyInputData);
    }
}
