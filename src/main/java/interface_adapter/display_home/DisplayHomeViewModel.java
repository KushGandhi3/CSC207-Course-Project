package interface_adapter.display_home;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the HomeView
 */
public class DisplayHomeViewModel extends ViewModel<DisplayHomeState> {

    public DisplayHomeViewModel() {
        super("Home");
        this.setState(new DisplayHomeState());
    }
}
