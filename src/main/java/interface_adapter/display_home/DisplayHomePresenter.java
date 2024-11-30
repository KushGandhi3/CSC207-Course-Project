package interface_adapter.display_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_home.DisplayHomeState;
import interface_adapter.display_home.DisplayHomeViewModel;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;
import entity.weather.hourly_weather.HourlyWeatherData;
import entity.weather.hour_weather.HourWeatherData;

/**
 * The Presenter for the Display Home Use Case.
 */
public class DisplayHomePresenter implements DisplayHomeOutputBoundary {

    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayHomePresenter(ViewManagerModel viewManagerModel,
                                DisplayHomeViewModel displayHomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayHomeViewModel = displayHomeViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayHomeOutputData response) {
        // On success, update the state with hourly weather data.
        DisplayHomeState state = displayHomeViewModel.getState();

        // Get HourlyWeatherData from the response
        HourlyWeatherData hourlyWeatherData = response.getHourlyWeatherData();

        // Set the general weather details
        state.setCity(hourlyWeatherData.getCity());
        state.setCondition(hourlyWeatherData.getTimezone());  // Assuming the timezone can be shown as condition here
        state.setHighTemperature(hourlyWeatherData.getHighTemperature());
        state.setLowTemperature(hourlyWeatherData.getLowTemperature());

        // Retrieve the first hourly data (you can decide how to display this)
        if (!hourlyWeatherData.getHourWeatherDataList().isEmpty()) {
            HourWeatherData firstHourData = hourlyWeatherData.getHourWeatherDataList().getFirst();
            // Update state with the first hour's data (or choose an appropriate time range)
            state.setTemperature(firstHourData.getTemperature());
            state.setCondition(firstHourData.getCondition());  // Assuming condition refers to weather condition at that hour
        }

        // Notify the view model that data has been updated
        displayHomeViewModel.firePropertyChanged();

        // Update the view manager state to "DisplayHome"
        this.viewManagerModel.setState("DisplayHome");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // In case of an error, set the error state
        DisplayHomeState state = displayHomeViewModel.getState();
        state.setCondition(error); // Using the condition to store the error message

        // Notify the view model that data has been updated
        displayHomeViewModel.firePropertyChanged();
    }
}