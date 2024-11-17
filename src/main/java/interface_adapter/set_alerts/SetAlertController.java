package interface_adapter.set_alerts;

import use_case.set_alerts.SetAlertsInputBoundary;

/**
 * Controller for set alerts related Use Cases.
 */
public class SetAlertController {

    private final SetAlertsInputBoundary setAlertsInteractor;

    public SetAlertController(SetAlertsInputBoundary setAlertsInteractor) {
        this.setAlertsInteractor = setAlertsInteractor;
    }

    /**
     * Executes the set alerts Use Cases.
     */
    public void execute() {
        setAlertsInteractor.setAlerts();
    }
}
