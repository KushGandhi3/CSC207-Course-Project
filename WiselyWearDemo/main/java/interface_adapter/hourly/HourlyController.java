package interface_adapter.hourly;

import use_case.hourly.HourlyInputBoundary;

/**
 * Controller for the hourly Use Case.
 */
public class HourlyController {
    private final HourlyInputBoundary hourlyUseCaseInteractor;

    public HourlyController(HourlyInputBoundary hourlyUseCaseInteractor) {
        this.hourlyUseCaseInteractor = hourlyUseCaseInteractor;
    }

    /**
     * Executes the Hourly Use Case.
     * @param location the location for which to fetch the hourly weather forecast
     */
    public void execute(String location) {
        final HourlyInputData inputData = new HourlyInputData(location);

        // Execute the use case, which fetches the data for hourly weather.
        hourlyUseCaseInteractor.execute(inputData);
    }
}