package use_case.display_history;

import exception.RecentCitiesDataException;

/**
 * Interface for handling display history input actions.
 */
public interface DisplayHistoryInputBoundary {

    /**
     * Executes the history use case with the chosen city.
     * @param displayHistoryInputData the chosen city.
     * @throws RecentCitiesDataException if there is an error getting recent cities.
     */
    void execute(DisplayHistoryInputData displayHistoryInputData);

    /**
     * Executes the "Switch to home" use case.
     */
    void switchToHomeView();
}