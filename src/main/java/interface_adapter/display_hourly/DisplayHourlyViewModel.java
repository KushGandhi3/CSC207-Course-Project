package interface_adapter.display_hourly;

import interface_adapter.ViewModel;

/**
 * ViewModel for the hourly forecast feature.
 * Holds the state for the hourly forecast view and provides methods to update and manage the state.
 */
public class DisplayHourlyViewModel extends ViewModel<DisplayHourlyState> {
    /**
     * Constructs a DisplayHourlyViewModel with an initial state for the hourly forecast.
     */
    public DisplayHourlyViewModel() {
        super("Hourly Forecast");
        setState(new DisplayHourlyState());
    }
}