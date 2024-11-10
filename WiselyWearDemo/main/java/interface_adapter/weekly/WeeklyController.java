package interface_adapter.weeekly;

import use_case.weekly.WeeklyInputBoundary;

/**
 * Controller for the weekly Use Case.
 */
public class WeeklyController {
    private final WeeklyInputBoundary weeklyUseCaseInteractor;

    public WeeklyController(WeeklyInputBoundary weeklyUseCaseInteractor) {
        this.weeklyUseCaseInteractor = weeklyUseCaseInteractor;
    }

    /**
     * Executes the Weekly Use Case.
     */
    public void execute() {
        // Execute the use case.
        weeklyUseCaseInteractor.execute();
    }
}