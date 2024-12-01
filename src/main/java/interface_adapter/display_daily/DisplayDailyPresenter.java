package interface_adapter.display_daily;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_home.DisplayHomeViewModel;
import use_case.display_daily.DisplayDailyOutputBoundary;
import use_case.display_daily.DisplayDailyOutputData;

/**
 * The Presenter for the Display Daily Use Case.
 */
public class DisplayDailyPresenter implements DisplayDailyOutputBoundary {

    private final DisplayDailyViewModel displayDailyViewModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayDailyPresenter(DisplayDailyViewModel displayDailyViewModel, DisplayHomeViewModel displayHomeViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.displayHomeViewModel = displayHomeViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
        // set up the updated state
        final DisplayDailyState displayDailyState = this.displayDailyViewModel.getState();
        displayDailyState.setCity(displayDailyOutputData.getCity());
        displayDailyState.setWeekdays(displayDailyOutputData.getWeekdays());
        displayDailyState.setTemperatures(displayDailyOutputData.getTemperatures());
        displayDailyState.setConditions(displayDailyOutputData.getConditions());
        displayDailyState.setFeelsLikeTemperature(displayDailyOutputData.getFeelsLikeTemperature());
        displayDailyState.setUvIndex(displayDailyOutputData.getUvIndex());
        displayDailyState.setWindSpeed(displayDailyOutputData.getWindSpeed());
        displayDailyState.setCloudCover(displayDailyOutputData.getCloudCover());
        displayDailyState.setPrecipitation(displayDailyOutputData.getPrecipitation());
        displayDailyState.setHumidity(displayDailyOutputData.getHumidity());

        this.displayDailyViewModel.setState(displayDailyState);
        this.displayDailyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.displayDailyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO: implement the fail view
        final DisplayDailyState displayDailyState = this.displayDailyViewModel.getState();
        displayDailyState.setCity(displayDailyState.getCity());
        this.displayDailyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(displayHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}