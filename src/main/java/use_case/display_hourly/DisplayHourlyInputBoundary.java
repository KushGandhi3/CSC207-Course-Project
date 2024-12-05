package use_case.display_hourly;

/**
 * Input Boundary for the hourly forecast use case.
 * Defines the methods that the interactor must implement.
 */
public interface DisplayHourlyInputBoundary {
    /**
     * Executes the use case with the provided input data.
     *
     * @param displayHourlyInputData the input data for the use case
     */
    void execute(DisplayHourlyInputData displayHourlyInputData);

    /**
     * Switches to the home view.
     */
    void switchToHomeView();
}
