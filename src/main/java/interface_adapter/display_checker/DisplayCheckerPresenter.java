package interface_adapter.display_checker;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_home.DisplayHomeViewModel;
import use_case.display_checker.DisplayCheckerOutputBoundary;

/**
 * Presenter for the checker use case.
 */
public class DisplayCheckerPresenter implements DisplayCheckerOutputBoundary {

    private final DisplayCheckerViewModel displayCheckerViewModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayCheckerPresenter(DisplayCheckerViewModel displayCheckerViewModel,
                                   DisplayHomeViewModel displayHomeViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.displayCheckerViewModel = displayCheckerViewModel;
        this.displayHomeViewModel = displayHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the view state when certain conditions are met. This method updates
     * the message of the current state to "exist" and triggers a property change
     * event*/
    @Override
    public void prepareCondMetView() {

        final DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setMessage("exist");

        displayCheckerViewModel.setState(displayCheckerState);
        displayCheckerViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view state when certain conditions are not met. This method
     * updates the message of the current state to "nonexist" and triggers a
     * property change event.
     */
    @Override
    public void prepareCondNotMetView() {

        final DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setMessage("nonexist");

        displayCheckerViewModel.setState(displayCheckerState);
        displayCheckerViewModel.firePropertyChanged();
    }

    /**
     * Prepares the home view.
     */
    @Override
    public void prepareHomeView() {
        // switch and update to home view
        viewManagerModel.setState(displayHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view state for when a location is not specified. This method updates the message of
     * the current state to "empty" and triggers a property change event to notify any observers.
     */
    @Override
    public void prepareLocationEmptyView() {
        final DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setMessage("empty");
        displayCheckerViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view state for handling an invalid location input. This method updates the
     * current state's message to "invalid" and triggers a property change event to notify observers
     * of the change in state.
     */
    @Override
    public void prepareInvalidLocationView() {
        final DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setMessage("invalid");

        displayCheckerViewModel.setState(displayCheckerState);
        displayCheckerViewModel.firePropertyChanged();
    }
}
