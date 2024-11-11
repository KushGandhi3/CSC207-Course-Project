package interface_adapter.display_home;


import interface_adapter.ViewManagerModel;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;

public class DisplayHomePresenter implements DisplayHomeOutputBoundary {

    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayHomePresenter(DisplayHomeViewModel displayHomeViewModel,
                                ViewManagerModel viewManagerModel) {
        this.displayHomeViewModel = displayHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(DisplayHomeOutputData outputData) {
        final DisplayHomeState displayHomeState =
                this.displayHomeViewModel.getState();
        displayHomeState.setLocation(outputData.getLocation());
        displayHomeState.setCurrentTemperature(outputData.getTemperature());
        this.displayHomeViewModel.setState(displayHomeState);
        this.displayHomeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.displayHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}