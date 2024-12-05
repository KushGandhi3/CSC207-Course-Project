package interface_adapter.display_checker;

import use_case.display_checker.DisplayCheckerInputBoundary;
import use_case.display_checker.DisplayCheckerInputData;

/**
 * Controller for checker related Use Cases.
 */
public class DisplayCheckerController {

    private final DisplayCheckerInputBoundary checkerInteractor;

    public DisplayCheckerController(DisplayCheckerInputBoundary checkerInteractor) {
        this.checkerInteractor = checkerInteractor;
    }

    /**
     * Executes the checker use case with the given input parameters.
     *
     * @param location the location to be checked
     * @param weatherConditionOptions the weather conditions to be considered
     * @param startChecking the start time in hours from now for checking the weather
     * @param stopChecking the stop time in hours from the start time for checking the weather
     */
    public void execute(String location, String weatherConditionOptions, int startChecking, int stopChecking) {
        final DisplayCheckerInputData displayCheckerInputData = new DisplayCheckerInputData(location,
                weatherConditionOptions, startChecking, stopChecking);

        checkerInteractor.execute(displayCheckerInputData);
    }

    /**
     * Executes the "Switch to HomeView" Use Cases.
     */
    public void switchToHomeView() {
        checkerInteractor.switchToHomeView();
    }
}
