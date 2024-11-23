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
     * Executes the checker Use Cases.
     */
    public void execute(String location, String weatherConditionOptions, int startChecking, int stopChecking) {
        DisplayCheckerInputData displayCheckerInputData = new DisplayCheckerInputData(location, weatherConditionOptions, startChecking, stopChecking);

        checkerInteractor.execute(displayCheckerInputData);
    }

    /**
     * Executes the "Switch to HomeView" Use Cases.
     */
    public void switchToHomeView() {
        checkerInteractor.switchToHomeView();
    }
}
