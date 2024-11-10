package interface_adapter.set_alert;

import use_case.set_alert.SetAlertInputBoundary;

/**
 * Controller for the Set Alert Use Case.
 */
public class SetAlertController {
    private final SetAlertInputBoundary setAlertUseCaseInteractor;

    public SetAlertController(SetAlertInputBoundary setAlertUseCaseInteractor) {
        this.setAlertUseCaseInteractor = setAlertUseCaseInteractor;
    }

    /**
     * Executes the Set Alert Use Case.
     */
    public void execute() {

        // Execute the use case,.
        setAlertUseCaseInteractor.execute();
    }
}
