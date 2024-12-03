package use_case.display_hourly;

/**
 * Output Boundary for the hourly forecast use case.
 * Defines the methods for preparing the output for the view.
 */
public interface DisplayHourlyOutputBoundary {
    /**
     * Prepares the success view with the provided output data.
     *
     * @param dailyHourlyOutputData the output data to be displayed
     */
    void prepareSuccessView(DisplayHourlyOutputData dailyHourlyOutputData);

    /**
     * Prepares the failure view with the provided error message.
     *
     * @param errorMessage the error message to display
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the home view.
     */
    void switchToHomeView();
}
