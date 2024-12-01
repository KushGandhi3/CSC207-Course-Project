package interface_adapter.display_history;

import exception.RecentCitiesDataException;
import use_case.display_history.DisplayHistoryInputBoundary;
import use_case.display_history.DisplayHistoryInputData;

/**
 * Controller for history use case.
 */
public class DisplayHistoryController {

    private final DisplayHistoryInputBoundary displayHistoryInteractor;

    public DisplayHistoryController(DisplayHistoryInputBoundary displayHistoryInteractor) {
        this.displayHistoryInteractor = displayHistoryInteractor;
    }

    /**
     * Executes the history use case with the chosen city.
     * @param city name of the chosen city.
     * @throws RecentCitiesDataException if there is an error getting the recent cities.
     */
    public void execute(String city) throws RecentCitiesDataException {
        final DisplayHistoryInputData displayHistoryInputData = new DisplayHistoryInputData(city);
        displayHistoryInteractor.execute(displayHistoryInputData);
    }

    /**
     * Executes the "Switch to home" use case.
     */
    public void switchToHomeView() {
        displayHistoryInteractor.switchToHomeView();
    }

}
