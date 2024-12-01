package interface_adapter.display_history;

import interface_adapter.ViewManagerModel;
import use_case.display_history.DisplayHistoryOutputBoundary;
import use_case.display_history.DisplayHistoryOutputData;

/**
 * DisplayHistoryPresenter manages the preparation and presentation of the
 * historical view of cities in the application.
 */
public class DisplayHistoryPresenter implements DisplayHistoryOutputBoundary {

    private final DisplayHistoryViewModel displayHistoryViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayHistoryPresenter(DisplayHistoryViewModel displayHistoryViewModel, ViewManagerModel viewManagerModel) {
        this.displayHistoryViewModel = displayHistoryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare view to display history.
     */
    @Override
    public void prepareSuccessView(DisplayHistoryOutputData cities) {
        final DisplayHistoryState displayHistoryState = displayHistoryViewModel.getState();
        displayHistoryState.setCities(cities.getCities());
        displayHistoryViewModel.setState(displayHistoryState);
        displayHistoryViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        // TODO: Implement the failure view.
    }

    /**
     * Prepare view to switch to home view.
     */
    @Override
    public void switchToHomeView() {
        viewManagerModel.setState("home");
        viewManagerModel.firePropertyChanged();

    }
}
