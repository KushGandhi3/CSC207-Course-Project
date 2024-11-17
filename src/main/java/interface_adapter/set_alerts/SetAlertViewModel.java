package interface_adapter.set_alerts;

import  interface_adapter.ViewModel;
import view.SetAlertsView;

public class SetAlertViewModel extends ViewModel<SetAlertState> {

    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public SetAlertViewModel() {
        super("set alerts");
        setState(new SetAlertState());
    }


    public void addPropertyChangeListener(SetAlertsView setAlertsView) {

    }
}
