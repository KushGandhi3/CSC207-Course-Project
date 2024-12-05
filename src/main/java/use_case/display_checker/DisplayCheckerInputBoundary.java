package use_case.display_checker;

public interface DisplayCheckerInputBoundary {

    /**
     * Executes the weather checker use case based on the given input data, which includes location,
     * desired weather conditions, and the time range for checking.
     * @param displayCheckerInputData the input data for the Checker use case, containing the location,
     *                                weather condition options, start time, and stop time for checking.
     */
    void execute(DisplayCheckerInputData displayCheckerInputData);

    /**
     * Switches the current view to the home view.
     */
    void switchToHomeView();
}
