package interface_adapter.checker;

import  interface_adapter.ViewModel;
import view.CheckerView;

public class CheckerViewModel extends ViewModel<CheckerState> {

    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public CheckerViewModel() {
        super("checker");
        setState(new CheckerState());
    }


    public void addPropertyChangeListener(CheckerView checkerView) {

    }
}
