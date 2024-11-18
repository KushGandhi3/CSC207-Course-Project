package interface_adapter.checker;

import use_case.checker.CheckerInputBoundary;

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
    public void execute() {
        checkerInteractor.checker();
    }
}
