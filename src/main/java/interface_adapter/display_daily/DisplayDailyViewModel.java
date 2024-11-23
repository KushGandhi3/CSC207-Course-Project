package interface_adapter.display_daily;

import interface_adapter.ViewModel;

/**
 * The View Model for the Display Weekly View.
 */
public class DisplayDailyViewModel extends ViewModel {

    public DisplayDailyViewModel() {
        super("Weekly Forecast");
        setState(new DisplayDailyState());
    }

}
