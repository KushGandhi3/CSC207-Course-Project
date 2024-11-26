package use_case.display_daily;

import constants.Constants;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.day_weather.DayWeatherData;
import exception.APICallException;
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
    private final DisplayDailyDAI weatherDataAccessObject;
    private final DisplayDailyOutputBoundary displayDailyPresenter;

    public DisplayDailyInteractor(DisplayDailyDAI weatherDataAccessObject, DisplayDailyOutputBoundary displayDailyPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.displayDailyPresenter = displayDailyPresenter;
    }

    @Override
    public void execute(DisplayDailyInputData displayDailyInputData) {
        final String city = displayDailyInputData.getCity();
        final DayOfWeek selectedWeekday = displayDailyInputData.getWeekday();
        
        try {
            final DailyWeatherData dailyWeatherData = weatherDataAccessObject.getDailyWeatherData(city);

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


            final DisplayDailyOutputData displayDailyOutputData =
                    new DisplayDailyOutputData(outputDataPackage);

            displayDailyPresenter.prepareSuccessView(displayDailyOutputData);
        } catch (APICallException exception) {
            displayDailyPresenter.prepareFailView(exception.getMessage());
        }

    }

    @Override
    public void switchToHomeView() {
        displayDailyPresenter.switchToHomeView();
    }
}
