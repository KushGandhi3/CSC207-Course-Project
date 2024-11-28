package interface_adapter.display_daily;

import constants.Constants;
import entity.weather.day_weather.DayWeatherData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Display Daily View Model.
 */
public class DisplayDailyState {

    private String city;
    // list of the weekdays in the correct order
    private List<String> weekdays;
    // list of the temperatures in order of their corresponding weekdays
    private List<Integer> temperatures;
    // list of conditions in order of their corresponding weekdays
    private List<String> conditions;
    // selected weather details for the particular weekday
    private int feelsLikeTemperature;
    private int uvIndex;
    private int windSpeed;
    private int cloudCover;
    private int precipitation;
    private int humidity;

    public DisplayDailyState() {
        this.city = "";

        this.weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        this.temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        this.conditions = new ArrayList<>(Constants.WEEK_SIZE);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            this.weekdays.add("");
            this.temperatures.add(0);
            this.conditions.add("");
        }

        this.feelsLikeTemperature = 0;
        this.uvIndex = 0;
        this.windSpeed = 0;
        this.cloudCover = 0;
        this.precipitation = 0;
        this.humidity = 0;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<String> weekdays) {
        this.weekdays = weekdays;
    }

    public List<Integer> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Integer> temperatures) {
        this.temperatures = temperatures;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public int getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(int feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

}
