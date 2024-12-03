package interface_adapter.display_hourly;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;

import java.time.LocalTime;

/**
 * State model for representing hourly weather data in the view.
 */
public class DisplayHourlyState {

    // City Variables
    private String city;
    private int lowTemperature;
    private int highTemperature;

    // Weather Variables
    private List<String> time;
    private List<String> condition;
    private List<String> temperature;
    private String feelsLike;
    private String windSpeed;
    private String precipitation;
    private String uvIndex;
    private String cloudCover;
    private String humidity;

    /**
     * Constructs an empty DisplayHourlyState with default values.
     */
    public DisplayHourlyState() {
        this.city = "Toronto";

        this.time = new ArrayList<>(Constants.TIME_SIZE);
        this.temperature = new ArrayList<>(Constants.TIME_SIZE);
        this.condition = new ArrayList<>(Constants.TIME_SIZE);
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.time.add(LocalTime.of(i + 1, 0).toString());
            this.temperature.add("0°C");
            this.condition.add("Clear");
        }

        this.feelsLike = "0°C";
        this.windSpeed = "0 m/s";
        this.precipitation = "0%";
        this.uvIndex = "0";
        this.cloudCover = "0%";
        this.humidity = "0%";
    }

    // Getters
    public String getCity() {
        return city;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public List<String> getTime() {
        return time;
    }

    public List<String> getCondition() {
        return condition;
    }

    public List<String> getTemperature() {
        return temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getHumidity() {
        return humidity;
    }

    // Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }

    public void setTemperature(List<String> temperature) {
        this.temperature = temperature;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
