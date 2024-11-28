package use_case.display_daily;

import constants.Constants;
import entity.recent_city.RecentCityData;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.day_weather.DayWeatherData;
import exception.APICallException;
import exception.RecentCitiesDataException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The interactor for the Display Daily use-case.
 */
public class DisplayDailyInteractor implements DisplayDailyInputBoundary {
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
            city = recentCityData.getRecentCityList().getFirst();
        } catch(RecentCitiesDataException exception) {
            displayDailyPresenter.prepareFailView(exception.getMessage());
            return;
        }
        
        try {
            final DailyWeatherData dailyWeatherData = this.weatherDataAccessObject.getDailyWeatherData(city);
            final DayOfWeek selectedWeekday = displayDailyInputData.getWeekday();
            // package data for DisplayDailyOutputData constructor
            final JSONObject outputDataPackage = packageOutputData(dailyWeatherData, selectedWeekday, city);

            final DisplayDailyOutputData displayDailyOutputData = new DisplayDailyOutputData(outputDataPackage);

            displayDailyPresenter.prepareSuccessView(displayDailyOutputData);
        } catch (APICallException exception) {
            displayDailyPresenter.prepareFailView(exception.getMessage());
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
        final List<DayOfWeek> weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        final List<Integer> temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        final List<String> conditions = new ArrayList<>(Constants.WEEK_SIZE);
        // create weekdays, temperatures, and conditions
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(dailyWeatherData.getTimezone()));
        final DayOfWeek currentWeekday = zonedDateTime.getDayOfWeek();
        final List<DayWeatherData> dayWeatherDataList = dailyWeatherData.getDayWeatherDataList();
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            final ZonedDateTime dateTime = zonedDateTime.plusDays(i);
            weekdays.add(dateTime.getDayOfWeek());

            DayWeatherData dayWeatherData = dayWeatherDataList.get(i);

            temperatures.add(dayWeatherData.getTemperature());
            conditions.add(dayWeatherData.getCondition());
        }

        final int selectedWeekdayIndex;
        if (selectedWeekday.ordinal() >= currentWeekday.ordinal()) {
            selectedWeekdayIndex = selectedWeekday.ordinal() - currentWeekday.ordinal();
        } else {
            selectedWeekdayIndex =
                    Constants.WEEK_SIZE + selectedWeekday.ordinal() - currentWeekday.ordinal();
        }
        final DayWeatherData selectedDayWeatherData = dayWeatherDataList.get(selectedWeekdayIndex);
        // get weather details for the selected weekday
        final int feelsLikeTemperature = selectedDayWeatherData.getFeelsLikeTemperature();
        final int uvIndex = selectedDayWeatherData.getUvIndex();
        final int windSpeed = selectedDayWeatherData.getWindSpeed();
        final int cloudCover = selectedDayWeatherData.getCloudCover();
        final int precipitation = selectedDayWeatherData.getPrecipitation();
        final int humidity = selectedDayWeatherData.getHumidity();

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
