package use_case.display_home;

import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;
import exception.RecentCitiesDataException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Interactor for the Display Home Use Case.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {

    private final DisplayHomeWeatherDAI weatherDataAccessObject;
    private final DisplayHomeRecentCitiesDAI recentCitiesDataAccessObject;
    private final DisplayHomeOutputBoundary displayHomePresenter;

    public DisplayHomeInteractor(DisplayHomeWeatherDAI weatherDataAccessObject,
                                 DisplayHomeOutputBoundary displayHomePresenter,
                                 DisplayHomeRecentCitiesDAI recentCitiesDataAccessObject) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.displayHomePresenter = displayHomePresenter;
        this.recentCitiesDataAccessObject = recentCitiesDataAccessObject;
    }

    @Override
    public void execute(DisplayHomeInputData displayHomeInputData) {
        final String city = displayHomeInputData.getCityName();
        try {
            recentCitiesDataAccessObject.addCity(city);
            final HourlyWeatherData hourlyWeatherData = weatherDataAccessObject
                    .getHourlyWeatherData(city);

            final String timezone = hourlyWeatherData.getTimezone();
            final String lowTemperature = hourlyWeatherData.getLowTemperature() + "°C";
            final String highTemperature = hourlyWeatherData.getHighTemperature() + "°C";

            // weather data for the most recent hour
            final HourWeatherData hourWeatherData = hourlyWeatherData.getHourWeatherDataList().getFirst();
            final String temperature = hourWeatherData.getTemperature() + "°C";
            final String condition = hourWeatherData.getCondition();

            // get the date
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            // formatter for the date pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d");
            String date = zonedDateTime.format(formatter);

            DisplayHomeOutputData outputData = new DisplayHomeOutputData(city, lowTemperature, highTemperature,
                    temperature, condition, date);
            this.displayHomePresenter.prepareSuccessView(outputData);
        } catch(APICallException | RecentCitiesDataException exception) {
            displayHomePresenter.prepareFailView(exception.getMessage());
        }
    }

    @Override
    public void switchToDailyView() {
        displayHomePresenter.switchToDailyView();
    }

    @Override
    public void switchToCheckerView() {
        displayHomePresenter.switchToCheckerView();
    }

    @Override
    public void switchToSummaryView() {
        displayHomePresenter.switchToSummaryView();
    }

    @Override
    public void switchToHistoryView() {
        displayHomePresenter.switchToHistoryView();
    }
}