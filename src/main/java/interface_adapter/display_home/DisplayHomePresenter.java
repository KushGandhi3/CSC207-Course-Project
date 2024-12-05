package interface_adapter.display_home;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_checker.DisplayCheckerViewModel;
import interface_adapter.display_daily.DisplayDailyViewModel;
import interface_adapter.display_history.DisplayHistoryViewModel;
import interface_adapter.display_hourly.DisplayHourlyViewModel;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;

/**
 * The Presenter for the Display Home Use Case.
 */
public class DisplayHomePresenter implements DisplayHomeOutputBoundary {

    private static final String UPDATE_DATA = "update_data";

    private final ViewManagerModel viewManagerModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final DisplaySummarizationViewModel displaySummarizationViewModel;
    private final DisplayHistoryViewModel displayHistoryViewModel;
    private final DisplayDailyViewModel displayDailyViewModel;
    private final DisplayCheckerViewModel displayCheckerViewModel;
    private final DisplayHourlyViewModel displayHourlyViewModel;

    public DisplayHomePresenter(ViewManagerModel viewManagerModel, DisplayHomeViewModel displayHomeViewModel,
                                DisplaySummarizationViewModel displaySummarizationViewModel,
                                DisplayHistoryViewModel displayHistoryViewModel,
                                DisplayDailyViewModel displayDailyViewModel,
                                DisplayCheckerViewModel displayCheckerViewModel,
                                DisplayHourlyViewModel displayHourlyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayHomeViewModel = displayHomeViewModel;
        this.displayHistoryViewModel = displayHistoryViewModel;
        this.displaySummarizationViewModel = displaySummarizationViewModel;
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayCheckerViewModel = displayCheckerViewModel;
        this.displayHourlyViewModel = displayHourlyViewModel;
    }

    @Override
    public void prepareSuccessView(DisplayHomeOutputData displayHomeOutputData) {
        // On success, update the state with hourly weather data.
        final DisplayHomeState state = displayHomeViewModel.getState();

        state.setCity(displayHomeOutputData.getCity());
        state.setCondition(displayHomeOutputData.getCondition());
        state.setTemperature(displayHomeOutputData.getTemperature());
        state.setHighTemperature(displayHomeOutputData.getHighTemperature());
        state.setLowTemperature(displayHomeOutputData.getLowTemperature());
        state.setDate(displayHomeOutputData.getDate());

        this.displayHomeViewModel.setState(state);
        this.displayHomeViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // set error state
        final DisplayHomeState state = this.displayHomeViewModel.getState();

        state.setCity(error);
        state.setCondition("-");
        state.setTemperature("-°C");
        state.setLowTemperature("-°C");
        state.setHighTemperature("-°C");
        state.setDate("-, - -");

        this.displayHomeViewModel.setState(state);
        displayHomeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToDailyView() {
        // execute the Display Daily Weather Use Case
        displayDailyViewModel.firePropertyChanged(UPDATE_DATA);

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
        displaySummarizationViewModel.firePropertyChanged(UPDATE_DATA);

        viewManagerModel.setState(displaySummarizationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHistoryView() {
        displayHistoryViewModel.firePropertyChanged(UPDATE_DATA);

        viewManagerModel.setState(displayHistoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHourlyView() {
        displayHourlyViewModel.firePropertyChanged(UPDATE_DATA);

        viewManagerModel.setState(displayHourlyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
