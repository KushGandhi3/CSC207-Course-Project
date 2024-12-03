package interface_adapter.display_hourly;

import use_case.display_hourly.DisplayHourlyOutputBoundary;
import use_case.display_hourly.DisplayHourlyOutputData;
import interface_adapter.display_home.DisplayHomeViewModel;
import interface_adapter.ViewManagerModel;

/**
 * Presenter for preparing data to be displayed on the hourly weather view.
 * It adapts the output data from the interactor to a format suitable for the view.
 */
public class DisplayHourlyPresenter implements DisplayHourlyOutputBoundary {
    private final DisplayHourlyViewModel displayHourlyViewModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a DisplayHourlyPresenter with specific view models and a view manager.
     *
     * @param displayHourlyViewModel the view model for hourly weather
     * @param displayHomeViewModel   the view model for the home view
     * @param viewManagerModel       the view manager for managing view states
     */
    public DisplayHourlyPresenter(DisplayHourlyViewModel displayHourlyViewModel,
                                  DisplayHomeViewModel displayHomeViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.displayHourlyViewModel = displayHourlyViewModel;
        this.displayHomeViewModel = displayHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DisplayHourlyOutputData displayHourlyOutputData) {
        final DisplayHourlyState displayHourlyState = this.displayHourlyViewModel.getState();

        displayHourlyState.setCity(displayHourlyOutputData.getCity());
        displayHourlyState.setLowTemperature(displayHourlyOutputData.getLowTemperature());
        displayHourlyState.setHighTemperature(displayHourlyOutputData.getHighTemperature());
        displayHourlyState.setTime(displayHourlyOutputData.getTime());
        displayHourlyState.setCondition(displayHourlyOutputData.getCondition());
        displayHourlyState.setTemperature(displayHourlyOutputData.getTemperature());
        displayHourlyState.setFeelsLike(displayHourlyOutputData.getFeelsLike());
        displayHourlyState.setWindSpeed(displayHourlyOutputData.getWindSpeed());
        displayHourlyState.setPrecipitation(displayHourlyOutputData.getPrecipitation());
        displayHourlyState.setUvIndex(displayHourlyOutputData.getUvIndex());
        displayHourlyState.setCloudCover(displayHourlyOutputData.getCloudCover());
        displayHourlyState.setHumidity(displayHourlyOutputData.getHumidity());

        this.displayHourlyViewModel.setState(displayHourlyState);
        this.displayHourlyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.displayHourlyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final DisplayHourlyState displayHourlyState = this.displayHourlyViewModel.getState();
        displayHourlyState.setCity(displayHourlyState.getCity());
        this.displayHourlyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(this.displayHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
