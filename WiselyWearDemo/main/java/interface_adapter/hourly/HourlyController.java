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
     */
    public void execute() {
        // Execute the use case.
        hourlyUseCaseInteractor.execute();
    }
}