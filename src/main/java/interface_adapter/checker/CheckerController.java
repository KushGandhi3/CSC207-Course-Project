package interface_adapter.checker;

import use_case.checker.CheckerInputBoundary;
import use_case.checker.CheckerInputData;

/**
 * Controller for checker related Use Cases.
 */
public class CheckerController {

    private final CheckerInputBoundary checkerInteractor;

    public CheckerController(CheckerInputBoundary checkerInteractor) {
        this.checkerInteractor = checkerInteractor;
    }

    /**
     * Executes the checker Use Cases.
     */
    public void execute(String location, String weatherConditionOptions, int startChecking, int stopChecking) {
        CheckerInputData checkerInputData = new CheckerInputData(location, weatherConditionOptions, startChecking, stopChecking);

        checkerInteractor.execute(checkerInputData);
    }

    /**
     * Executes the "Switch to HomeView" Use Cases.
     */
    public void switchToHomeView() {
        checkerInteractor.switchToHomeView();
    }
}
