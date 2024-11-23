package interface_adapter.display_home;

import interface_adapter.ViewManagerModel;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;

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
        // On success, update the state with weather data.

        DisplayHomeState state = displayHomeViewModel.getState();

        // Update the weather data state
        state.setCity(response.getWeatherData().getCity());
        state.setTemperature(response.getWeatherData().getTemperature());
        state.setHighTemperature(response.getWeatherData().getHighTemperature());
        state.setLowTemperature(response.getWeatherData().getLowTemperature());
        state.setCondition(response.getWeatherData().getCondition());

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
