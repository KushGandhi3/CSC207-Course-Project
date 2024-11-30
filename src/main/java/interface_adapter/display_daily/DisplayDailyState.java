package interface_adapter.display_daily;

import constants.Constants;
import entity.weather.day_weather.DayWeatherData;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
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
    private List<String> temperatures;
    // list of conditions in order of their corresponding weekdays
    private List<String> conditions;
    // selected weather details for the particular weekday
    private String feelsLikeTemperature;
    private String uvIndex;
    private String windSpeed;
    private String cloudCover;
    private String precipitation;
    private String humidity;

    public DisplayDailyState() {
        this.city = "";

        this.weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        this.temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        this.conditions = new ArrayList<>(Constants.WEEK_SIZE);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            this.weekdays.add("");
            this.temperatures.add("");
            this.conditions.add("");
        }

        this.feelsLikeTemperature = "";
        this.uvIndex = "";
        this.windSpeed = "";
        this.cloudCover = "";
        this.precipitation = "";
        this.humidity = "";
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

    public List<String> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<String> temperatures) {
        this.temperatures = temperatures;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public String getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(String feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

}
