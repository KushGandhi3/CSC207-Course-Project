package interface_adapter.display_home;

import interface_adapter.ViewModel;

public class DisplayHomeViewModel extends ViewModel<DisplayHomeState> {

    public static final String HOURLY_BUTTON_LABEL = "Hourly";
    public static final String WEEKLY_BUTTON_LABEL = "Weekly";
    public static final String ALERTS_BUTTON_LABEL = "Alerts";
    public static final String OUTFIT_BUTTON_LABEL = "Outfit";
    public static final String MAP_BUTTON_LABEL = "Map";

    public DisplayHomeViewModel() {
        super("display home");
        setState(new DisplayHomeState());
    }

}