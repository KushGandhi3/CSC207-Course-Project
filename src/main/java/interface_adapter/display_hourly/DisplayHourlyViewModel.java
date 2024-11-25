package interface_adapter.display_hourly;

import interface_adapter.ViewModel;

public class DisplayHourlyViewModel {
    public DisplayHourlyViewModel() {
        super("Hourly Forecast");
        setState(new DisplayHourlyState());
    }
}
