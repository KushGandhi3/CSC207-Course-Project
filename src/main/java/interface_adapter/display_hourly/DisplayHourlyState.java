package interface_adapter.display_hourly;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;

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
    private List<Integer> temperature;
    private int feelsLike;
    private int windSpeed;
    private int precipitation;
    private int uvIndex;
    private int cloudCover;
    private int humidity;

    /**
     * Constructs an empty DisplayHourlyState with default values.
     */
    public DisplayHourlyState() {
        city = "";
        lowTemperature = 0;
        highTemperature = 0;

        this.time = new ArrayList<>(Constants.TIME_SIZE);
        this.condition = new ArrayList<>(Constants.TIME_SIZE);
        this.temperature = new ArrayList<>(Constants.TIME_SIZE);

        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.time.add("");
            this.condition.add("");
            this.temperature.add(0);
        }

        this.feelsLike = 0;
        this.windSpeed = 0;
        this.precipitation = 0;
        this.uvIndex = 0;
        this.cloudCover = 0;
        this.humidity = 0;
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

    public List<Integer> getTemperature() {
        return temperature;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getHumidity() {
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

    public void setTemperature(List<Integer> temperature) {
        this.temperature = temperature;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPrecipitation(int precipitation) {
        this.precipitation = precipitation;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
