package interface_adapter.display_daily;

import interface_adapter.ViewModel;

/**
 * The View Model for the Display Daily View.
 */
public class DisplayDailyViewModel extends ViewModel<DisplayDailyState> {

    public DisplayDailyViewModel() {
        super("Daily Forecast");
        setState(new DisplayDailyState());
    }

}
