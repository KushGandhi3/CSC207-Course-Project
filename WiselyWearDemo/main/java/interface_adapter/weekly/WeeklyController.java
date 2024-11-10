package interface_adapter.weeekly;

import use_case.weekly.WeeklyInputBoundary;

/**
 * Controller for the weekly Use Case.
 */
public class WeeklyController {
    private final WeeklyInputBoundary weeklyUseCaseInteractor;

    public WeeklyController(WeeklyInputBoundary weeklyUseCaseInteractor) {
        this.weeklyUseCaseInteractor = weeklyUseCaseInteractor
    }

    /**
     * Executes the Weekly Use Case.
     * @param location the location for which to fetch the Weekly weather forecast
     */
    public void execute(String location) {
        final WeeklyInputData inputData = new WeeklyInputData(location);

        // Execute the use case, which fetches the data for weekly weather.
        weeklyUseCaseInteractor.execute(inputData);
    }
}