package interface_adapter.display_home;

import interface_adapter.ViewManagerModel;
import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeOutputData;

/**
 * The Presenter for the DisplayHome Use Case.
 */
public class DisplayHomePresenter extends DisplayHomeInputBoundary {
    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayHomePresenter(ViewManagerModel viewManagerModel, DisplayHomeViewModel displayHomeViewModel) {
        this.displayHomeViewModel = displayHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public static void handleDailyWeatherRequest() {
        //TODO
    }

    public static void handleWeeklyWeatherRequest() {
        //TODO
    }

    public static void handleWeatherCheckerRequest() {
        //TODO
    }

    public static void handleOutfitSuggestionsRequest() {
        //TODO
    }

    public static void handleMapRequest() {
        //TODO
    }

    @Override
    public void prepareSuccessView(DisplayHomeOutputData response) {
        // On success, switch to the DisplayHome in view.

        final DisplayHomeState loggedInState = displayHomeViewModel.getState();
        this.viewManagerModel.setState(displayHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        displayHomeViewModel.firePropertyChanged();
    }
}
