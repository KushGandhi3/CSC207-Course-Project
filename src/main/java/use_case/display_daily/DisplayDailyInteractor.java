package use_case.display_daily;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import constants.Constants;
import entity.recent_city.RecentCityData;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.day_weather.DayWeatherData;
import exception.ApiCallException;
import exception.RecentCitiesDataException;

/**
 * The interactor for the Display Daily use-case.
 */
public class DisplayDailyInteractor implements DisplayDailyInputBoundary {

    private static final String PERCENT = "%";

    private final DisplayDailyRecentCitiesDAI recentCitiesDAO;
    private final DisplayDailyWeatherDAI weatherDataAccessObject;
    private final DisplayDailyOutputBoundary displayDailyPresenter;

    public DisplayDailyInteractor(DisplayDailyRecentCitiesDAI recentCitiesDAO,
                                  DisplayDailyWeatherDAI weatherDataAccessObject,
                                  DisplayDailyOutputBoundary displayDailyPresenter) {
        this.recentCitiesDAO = recentCitiesDAO;
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.displayDailyPresenter = displayDailyPresenter;
    }

    @Override
    public void execute(DisplayDailyInputData displayDailyInputData) {
        // get the name of the last city requested from the user
        final String city;
        try {
            final RecentCityData recentCityData = this.recentCitiesDAO.getRecentCityData();
            if (recentCityData.getRecentCityList().isEmpty()) {
                throw new RecentCitiesDataException("No Cities To Display.");
            }
            city = recentCityData.getRecentCityList().getFirst();

            final DailyWeatherData dailyWeatherData = this.weatherDataAccessObject.getDailyWeatherData(city);
            final DayOfWeek selectedWeekday = DayOfWeek.valueOf(displayDailyInputData.getWeekday().toUpperCase());
            // package data for DisplayDailyOutputData constructor
            final JSONObject outputDataPackage = packageOutputData(dailyWeatherData, selectedWeekday, city);

            final DisplayDailyOutputData displayDailyOutputData = new DisplayDailyOutputData(outputDataPackage);

            displayDailyPresenter.prepareSuccessView(displayDailyOutputData);
        }
        catch (ApiCallException | RecentCitiesDataException exception) {
            exception.printStackTrace();
            displayDailyPresenter.prepareFailView("Weather Data Unavailable.");
        }
    }

    @Override
    public void execute() {
        // get the name of the last city requested from the user
        final String city;
        try {
            final RecentCityData recentCityData = this.recentCitiesDAO.getRecentCityData();
            if (recentCityData.getRecentCityList().isEmpty()) {
                throw new RecentCitiesDataException("No Cities To Display.");
            }
            city = recentCityData.getRecentCityList().getFirst();

            final DailyWeatherData dailyWeatherData = this.weatherDataAccessObject.getDailyWeatherData(city);
            // choose the current day of the week
            final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(dailyWeatherData.getTimezone()));
            final DayOfWeek selectedWeekday = zonedDateTime.getDayOfWeek();
            // package data for DisplayDailyOutputData constructor
            final JSONObject outputDataPackage = packageOutputData(dailyWeatherData, selectedWeekday, city);

            final DisplayDailyOutputData displayDailyOutputData = new DisplayDailyOutputData(outputDataPackage);

            displayDailyPresenter.prepareSuccessView(displayDailyOutputData);
        }
        catch (ApiCallException | RecentCitiesDataException exception) {
            exception.printStackTrace();
            displayDailyPresenter.prepareFailView("Weather Data Unavailable.");
        }
    }

    /**
     * Packages the output data for initializing DisplayDailyOutputData.
     * @param dailyWeatherData the weather data for the days to be packaged
     * @param selectedWeekday the weekday the user selected
     * @param city the city the weather data is from
     * @return the package which can be used to initialize DisplayDailyOutputData
     */
    private JSONObject packageOutputData(DailyWeatherData dailyWeatherData, DayOfWeek selectedWeekday, String city) {
        final List<String> weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        final List<String> temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        final List<String> conditions = new ArrayList<>(Constants.WEEK_SIZE);
        // create weekdays, temperatures, and conditions
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(dailyWeatherData.getTimezone()));
        final DayOfWeek currentWeekday = zonedDateTime.getDayOfWeek();
        final List<DayWeatherData> dayWeatherDataList = dailyWeatherData.getDayWeatherDataList();
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            final ZonedDateTime dateTime = zonedDateTime.plusDays(i);
            weekdays.add(dateTime.getDayOfWeek().toString());

            final DayWeatherData dayWeatherData = dayWeatherDataList.get(i);

            temperatures.add(dayWeatherData.getTemperature() + "°C");
            conditions.add(dayWeatherData.getCondition());
        }

        final int selectedWeekdayIndex;
        if (selectedWeekday.ordinal() >= currentWeekday.ordinal()) {
            selectedWeekdayIndex = selectedWeekday.ordinal() - currentWeekday.ordinal();
        }
        else {
            selectedWeekdayIndex =
                    Constants.WEEK_SIZE + selectedWeekday.ordinal() - currentWeekday.ordinal();
        }
        final DayWeatherData selectedDayWeatherData = dayWeatherDataList.get(selectedWeekdayIndex);
        // get weather details for the selected weekday
        final String feelsLikeTemperature = selectedDayWeatherData.getFeelsLikeTemperature() + "°C";
        final String uvIndex = String.valueOf(selectedDayWeatherData.getUvIndex());
        final String windSpeed = selectedDayWeatherData.getWindSpeed() + " m/s";
        final String cloudCover = selectedDayWeatherData.getCloudCover() + PERCENT;
        final String precipitation = selectedDayWeatherData.getPrecipitation() + PERCENT;
        final String humidity = selectedDayWeatherData.getHumidity() + PERCENT;

        // package data for DisplayDailyOutputData constructor
        final JSONObject outputDataPackage = new JSONObject();
        outputDataPackage.put("city", city);
        outputDataPackage.put("weekdays", weekdays);
        outputDataPackage.put("temperatures", temperatures);
        outputDataPackage.put("conditions", conditions);
        outputDataPackage.put("feelsLikeTemperature", feelsLikeTemperature);
        outputDataPackage.put("uvIndex", uvIndex);
        outputDataPackage.put("windSpeed", windSpeed);
        outputDataPackage.put("cloudCover", cloudCover);
        outputDataPackage.put("precipitation", precipitation);
        outputDataPackage.put("humidity", humidity);

        return outputDataPackage;
    }

    @Override
    public void switchToHomeView() {
        displayDailyPresenter.switchToHomeView();
    }

}
