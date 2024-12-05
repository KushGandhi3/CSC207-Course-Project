package interface_adapter.display_daily;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
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
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final DisplayDailyState displayDailyState = this.displayDailyViewModel.getState();
        displayDailyState.setCity(errorMessage);
        // prepare default view
        final List<String> weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        final List<String> temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        final List<String> conditions = new ArrayList<>(Constants.WEEK_SIZE);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            weekdays.add(DayOfWeek.of(i + 1).toString());
            temperatures.add("---");
            conditions.add("----");
        }
        final String feelsLikeTemperature = "---";
        final String uvIndex = "-";
        final String windSpeed = "-----";
        final String cloudCover = "--";
        final String precipitation = "--";
        final String humidity = "--";
        displayDailyState.setWeekdays(weekdays);
        displayDailyState.setTemperatures(temperatures);
        displayDailyState.setConditions(conditions);
        displayDailyState.setFeelsLikeTemperature(feelsLikeTemperature);
        displayDailyState.setUvIndex(uvIndex);
        displayDailyState.setWindSpeed(windSpeed);
        displayDailyState.setCloudCover(cloudCover);
        displayDailyState.setPrecipitation(precipitation);
        displayDailyState.setHumidity(humidity);

        this.displayDailyViewModel.setState(displayDailyState);
        this.displayDailyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(displayHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
