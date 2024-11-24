package interface_adapter.display_daily;

import constants.Constants;
import org.jetbrains.annotations.NotNull;

/**
 * The state for the Display Weekly Model.
 */
public class DisplayDailyState {

    // weekday temperatures
    private String[] temperatureForecasts = new String[7];
    // weekday conditions (i.e. sunny, cloudy, rainy)
    private String[] conditionForecasts = new String[7];
    // weekday feels like temperatures
    private String[] feelsLikeForecasts = new String[7];
    // weekday wind speeds
    private String[] windSpeedForecasts = new String[7];
    // weekday precipitation forecasts
    private String[] precipitationForecasts = new String[7];
    // weekday UV indices
    private String[] uvIndexForecasts = new String[7];
    // weekday air quality forecasts
    private String[] airQualityForecasts = new String[7];
    // weekday humidity forecasts
    private String[] humidityForecasts = new String[7];

    private String city;

    public DisplayDailyState() {
        for (int i = Constants.MONDAY; i <= Constants.SUNDAY; i++) {
            temperatureForecasts[i] = "";
            conditionForecasts[i] = "";
            feelsLikeForecasts[i] = "";
            windSpeedForecasts[i] = "";
            precipitationForecasts[i] = "";
            uvIndexForecasts[i] = "";
            airQualityForecasts[i] = "";
            humidityForecasts[i] = "";
        }
        city = "";
    }

    public String[] getTemperatureForecasts() {
        return temperatureForecasts;
    }

    public String[] getConditionForecasts() {
        return conditionForecasts;
    }

    public String[] getFeelsLikeForecasts() {
        return feelsLikeForecasts;
    }

    public String[] getWindSpeedForecasts() {
        return windSpeedForecasts;
    }

    public String[] getPrecipitationForecasts() {
        return precipitationForecasts;
    }

    public String[] getUvIndexForecasts() {
        return uvIndexForecasts;
    }

    public String[] getAirQualityForecasts() {
        return airQualityForecasts;
    }

    public String[] getHumidityForecasts() {
        return humidityForecasts;
    }

    public String getCity() {
        return city;
    }

    public void setTemperatureForecasts(@NotNull String[] temperatureForecasts) {
        this.temperatureForecasts = temperatureForecasts.clone();
    }

    public void setConditionForecasts(@NotNull String[] conditionForecasts) {
        this.conditionForecasts = conditionForecasts.clone();
    }

    public void setFeelsLikeForecasts(@NotNull String[] feelsLikeForecasts) {
        this.feelsLikeForecasts = feelsLikeForecasts.clone();
    }

    public void setWindSpeedForecasts(@NotNull String[] windSpeedForecasts) {
        this.windSpeedForecasts = windSpeedForecasts.clone();
    }

    public void setPrecipitationForecasts(@NotNull String[] precipitationForecasts) {
        this.precipitationForecasts = precipitationForecasts.clone();
    }

    public void setUvIndexForecasts(@NotNull String[] uvIndexForecasts) {
        this.uvIndexForecasts = uvIndexForecasts.clone();
    }

    public void setAirQualityForecasts(@NotNull String[] airQualityForecasts) {
        this.airQualityForecasts = airQualityForecasts.clone();
    }

    public void setHumidityForecasts(@NotNull String[] humidityForecasts) {
        this.humidityForecasts = humidityForecasts.clone();
    }

    public void setCity(String city) {
        this.city = city;
    }

}
