package interface_adapter.display_weekly;

import interface_adapter.ViewModel;

/**
 * The View Model for the Display Weekly View.
 */
public class DisplayWeeklyViewModel extends ViewModel {

    public DisplayWeeklyViewModel() {
        super("Weekly Forecast");
        setState(new DisplayWeeklyState());
    }

}
