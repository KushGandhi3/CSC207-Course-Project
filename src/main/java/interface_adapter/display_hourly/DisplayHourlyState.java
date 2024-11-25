package interface_adapter.display_hourly;

import org.jetbrains.annotations.NotNull;

public class DisplayHourlyState {

    // City Variables
    private String city;
    private String lowTemperature;
    private String highTemperature;
    private String time;

    // Weather Variables
    private String[] condition = new String[8];
    private String[] temperature = new String[8];
    private String[] feelsLike = new String[8];
    private String[] windSpeed = new String[8];
    private String[] precipitation = new String[8];
    private String[] uvIndex = new String[8];
    private String[] airQuality = new String[8];
    private String[] humidity = new String[8];

    public DisplayHourlyState() {
        for (int i = 0; i < 8; i++) {
            condition[i] = "";
            temperature[i] = "";
            feelsLike[i] = "";
            windSpeed[i] = "";
            precipitation[i] = "";
            uvIndex[i] = "";
            airQuality[i] = "";
            humidity[i] = "";
        }
        city = "";
    }

    // Getters
    public String getCity() {
        return city;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public String getTime() {
        return time;
    }

    public String[] getCondition() {
        return condition;
    }

    public String[] getTemperature() {
        return temperature;
    }

    public String[] getFeelsLike() {
        return feelsLike;
    }

    public String[] getWindSpeed() {
        return windSpeed;
    }

    public String[] getPrecipitation() {
        return precipitation;
    }

    public String[] getUvIndex() {
        return uvIndex;
    }

    public String[] getAirQuality() {
        return airQuality;
    }

    public String[] getHumidity() {
        return humidity;
    }

    // Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCondition(@NotNull String[] condition) {
        this.condition = condition.clone();
    }

    public void setTemperature(@NotNull String[] temperature) {
        this.temperature = temperature.clone();
    }

    public void setFeelsLike(@NotNull String[] feelsLike) {
        this.feelsLike = feelsLike.clone();
    }

    public void setWindSpeed(@NotNull String[] windSpeed) {
        this.windSpeed = windSpeed.clone();
    }

    public void setPrecipitation(@NotNull String[] precipitation) {
        this.precipitation = precipitation.clone();
    }

    public void setUvIndex(@NotNull String[] uvIndex) {
        this.uvIndex = uvIndex.clone();
    }

    public void setAirQuality(@NotNull String[] airQuality) {
        this.airQuality = airQuality.clone();
    }

    public void setHumidity(@NotNull String[] humidity) {
        this.humidity = humidity.clone();
    }
}
