package use_case.display_home;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import entity.recent_city.RecentCityData;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;
import exception.RecentCitiesDataException;

/**
 * The Interactor for the Display Home Use Case.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {

    private static final String DEGREES_CELSIUS = "°C";

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
        try {
            final String city = displayHomeInputData.getCityName();
            if (city == null) {
                throw new ApiCallException("No City Name Provided.");
            }

            recentCitiesDataAccessObject.addCity(city);

            final DisplayHomeOutputData displayHomeOutputData = getOutputData(city);
            this.displayHomePresenter.prepareSuccessView(displayHomeOutputData);
        }
        catch (ApiCallException | RecentCitiesDataException exception) {
            exception.printStackTrace();
            displayHomePresenter.prepareFailView("City Not Found.");
        }
    }

    /**
     * Executes the DisplayHome use case to show weather information for the most recently accessed city.
     */
    public void execute() {
        try {
            final RecentCityData recentCityData = recentCitiesDataAccessObject.getRecentCityData();
            if (recentCityData.getRecentCityList().isEmpty()) {
                throw new RecentCitiesDataException("Recent cities not found");
            }
            final String recentCity = recentCityData.getRecentCityList().getFirst();

            final DisplayHomeOutputData displayHomeOutputData = getOutputData(recentCity);
            this.displayHomePresenter.prepareSuccessView(displayHomeOutputData);
        }
        catch (ApiCallException | RecentCitiesDataException exception) {
            exception.printStackTrace();
            displayHomePresenter.prepareFailView("City Not Found.");
        }
    }

    private DisplayHomeOutputData getOutputData(String city) throws ApiCallException {
        final HourlyWeatherData hourlyWeatherData = weatherDataAccessObject
                .getHourlyWeatherData(city);

        final String timezone = hourlyWeatherData.getTimezone();
        final String lowTemperature = hourlyWeatherData.getLowTemperature() + DEGREES_CELSIUS;
        final String highTemperature = hourlyWeatherData.getHighTemperature() + DEGREES_CELSIUS;

        // weather data for the most recent hour
        final HourWeatherData hourWeatherData = hourlyWeatherData.getHourWeatherDataList().getFirst();
        final String temperature = hourWeatherData.getTemperature() + DEGREES_CELSIUS;
        final String condition = hourWeatherData.getCondition();

        // get the date
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(timezone));
        // formatter for the date pattern
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d");
        final String date = zonedDateTime.format(formatter);

        return new DisplayHomeOutputData(city, lowTemperature, highTemperature,
                temperature, condition, date);
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

    @Override
    public void switchToHourlyView() {
        displayHomePresenter.switchToHourlyView();
    }
}
