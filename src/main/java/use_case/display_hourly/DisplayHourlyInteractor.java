package use_case.display_hourly;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import constants.Constants;
import entity.recent_city.RecentCityData;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;
import exception.RecentCitiesDataException;

/**
 * Interactor for the hourly forecast use case.
 * Contains the business logic for processing hourly weather data.
 */
public class DisplayHourlyInteractor implements DisplayHourlyInputBoundary {
    private final DisplayHourlyRecentCitiesDAI recentCitiesDAO;
    private final DisplayHourlyWeatherDAI weatherDataAccessObject;
    private final DisplayHourlyOutputBoundary displayHourlyPresenter;

    public DisplayHourlyInteractor(DisplayHourlyRecentCitiesDAI recentCitiesDAO,
                                   DisplayHourlyWeatherDAI weatherDataAccessObject,
                                   DisplayHourlyOutputBoundary displayHourlyPresenter) {
        this.recentCitiesDAO = recentCitiesDAO;
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.displayHourlyPresenter = displayHourlyPresenter;
    }

    @Override
    public void execute(DisplayHourlyInputData displayHourlyInputData) {
        final String city;

        try {
            final RecentCityData recentCityData = this.recentCitiesDAO.getRecentCityData();
            city = recentCityData.getRecentCityList().getFirst();
        }
        catch (RecentCitiesDataException exception) {
            displayHourlyPresenter.prepareFailView(exception.getMessage());
            return;
        }

        try {
            final HourlyWeatherData hourlyWeatherData = this.weatherDataAccessObject.getHourlyWeatherData(city);
            final LocalTime selectedTime = LocalTime.parse(displayHourlyInputData.getSelectedTime());
            final JSONObject outputDataPackage = packageOutputData(hourlyWeatherData, selectedTime, city);
            final DisplayHourlyOutputData displayHourlyOutputData = new DisplayHourlyOutputData(outputDataPackage);
            displayHourlyPresenter.prepareSuccessView(displayHourlyOutputData);
        }
        catch (ApiCallException exception) {
            displayHourlyPresenter.prepareFailView(exception.getMessage());
        }
    }

    /**
     * Packages the output data for initializing DisplayHourlyOutputData.
     * @param hourlyWeatherData the weather data for the hours to be packaged
     * @param selectedTime the time the user selected
     * @param city the city the weather data is from
     * @return the package which can be used to initialize DisplayHourlyOutputData
     */
    private JSONObject packageOutputData(HourlyWeatherData hourlyWeatherData, LocalTime selectedTime, String city) {
        final List<Integer> times = new ArrayList<>(Constants.TIME_SIZE);
        final List<Integer> temperatures = new ArrayList<>(Constants.TIME_SIZE);
        final List<String> conditions = new ArrayList<>(Constants.TIME_SIZE);
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(hourlyWeatherData.getTimezone()));
        final LocalTime currentTime = zonedDateTime.toLocalTime();
        final List<HourWeatherData> hourWeatherDataList = hourlyWeatherData.getHourWeatherDataList();

        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            final ZonedDateTime dateTime = zonedDateTime.plusHours(i);
            times.add(dateTime.getHour());
            final HourWeatherData hourWeatherData = hourWeatherDataList.get(i);
            temperatures.add(hourWeatherData.getTemperature());
            conditions.add(hourWeatherData.getCondition());
        }

        final int totalSteps = Constants.TIME_SIZE;
        final int selectedTimeIndex = (int) ((ChronoUnit.HOURS.between(currentTime, selectedTime) + totalSteps)
                % totalSteps);

        final HourWeatherData selectedHourWeatherData = hourWeatherDataList.get(selectedTimeIndex);
        final int feelsLikeTemperature = selectedHourWeatherData.getFeelsLikeTemperature();
        final int windSpeed = selectedHourWeatherData.getWindSpeed();
        final int precipitation = selectedHourWeatherData.getPrecipitation();
        final int uvIndex = selectedHourWeatherData.getUvIndex();
        final int cloudCover = selectedHourWeatherData.getCloudCover();
        final int humidity = selectedHourWeatherData.getHumidity();

        final JSONObject outputDataPackage = new JSONObject();
        outputDataPackage.put("city", city);
        outputDataPackage.put("lowTemperature", hourlyWeatherData.getLowTemperature());
        outputDataPackage.put("highTemperature", hourlyWeatherData.getHighTemperature());
        outputDataPackage.put("time", times);
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
        displayHourlyPresenter.switchToHomeView();
    }

}
