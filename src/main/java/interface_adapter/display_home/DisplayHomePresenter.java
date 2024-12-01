package interface_adapter.display_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_checker.DisplayCheckerViewModel;
import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyViewModel;
import interface_adapter.display_history.DisplayHistoryViewModel;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;

/**
 * The Presenter for the Display Home Use Case.
 */
public class DisplayHomePresenter implements DisplayHomeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final DisplaySummarizationViewModel displaySummarizationViewModel;
    private final DisplayHistoryViewModel displayHistoryViewModel;
    private final DisplayDailyViewModel displayDailyViewModel;
    private final DisplayCheckerViewModel displayCheckerViewModel;

    public DisplayHomePresenter(ViewManagerModel viewManagerModel, DisplayHomeViewModel displayHomeViewModel,
                                DisplaySummarizationViewModel displaySummarizationViewModel,
                                DisplayHistoryViewModel displayHistoryViewModel,
                                DisplayDailyViewModel displayDailyViewModel,
                                DisplayCheckerViewModel displayCheckerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayHomeViewModel = displayHomeViewModel;
        this.displayHistoryViewModel = displayHistoryViewModel;
        this.displaySummarizationViewModel = displaySummarizationViewModel;
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayCheckerViewModel = displayCheckerViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayHomeOutputData displayHomeOutputData) {
        // On success, update the state with hourly weather data.
        DisplayHomeState state = displayHomeViewModel.getState();

        state.setCity(displayHomeOutputData.getCity());
        state.setCondition(displayHomeOutputData.getCondition());
        state.setTemperature(displayHomeOutputData.getTemperature());
        state.setHighTemperature(displayHomeOutputData.getHighTemperature());
        state.setLowTemperature(displayHomeOutputData.getLowTemperature());
        state.setDate(displayHomeOutputData.getDate());

        // update the weather data in other use cases
        this.displayDailyViewModel.firePropertyChanged();

        this.displayHomeViewModel.setState(state);
        this.displayHomeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.displayHomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    // TODO: implement fail view for home
    @Override
    public void prepareFailView(String error) {
        // In case of an error, set the error state
        DisplayHomeState state = displayHomeViewModel.getState();
        state.setCondition(error); // Using the condition to store the error message

        // Notify the view model that data has been updated
        displayHomeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDailyView() {
        // execute the Display Daily Weather Use Case
        displayDailyViewModel.firePropertyChanged("update_data");

        // switch the view to the DailyView
        viewManagerModel.setState(displayDailyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCheckerView() {
        viewManagerModel.setState(displayCheckerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSummaryView() {
        displaySummarizationViewModel.firePropertyChanged("update_data");

        viewManagerModel.setState(displaySummarizationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHistoryView() {
        displayHistoryViewModel.firePropertyChanged("update_data");

        viewManagerModel.setState(displayHistoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}