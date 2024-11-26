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
        this.displayHomeViewModel = displayHomeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
        // on success, switch to the display daily view
        final DisplayDailyState displayDailyState = displayDailyViewModel.getState();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToHomeView() {

    }


}
