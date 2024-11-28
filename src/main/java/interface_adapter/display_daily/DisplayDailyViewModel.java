package interface_adapter.display_daily;

import interface_adapter.ViewModel;

import javax.swing.*;

/**
 * The View Model for the Display Daily View.
 */
public class DisplayDailyViewModel extends ViewModel<DisplayDailyState> {

    public static final String FORECAST_LABEL = "DAILY FORECAST";
    public static final String DETAILS_LABEL = "WEATHER DETAILS";

    public static final ImageIcon BACK_BUTTON_IMAGE = new ImageIcon("src/main/resources/assets/Back Button.png");

    public DisplayDailyViewModel() {
        super("Daily Forecast");
        setState(new DisplayDailyState());
    }

}
