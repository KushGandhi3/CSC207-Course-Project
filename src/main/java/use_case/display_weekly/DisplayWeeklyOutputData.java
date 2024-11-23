package use_case.display_weekly;

import constants.Constants;

/**
 * Output Data for the Display Weekly use-case.
 */
public class DisplayWeeklyOutputData {

    private final String city;

    // weekday temperature forecasts
    private final Double[] temperatureForecasts;
    // weekday conditions (i.e. sunny, cloudy, rainy)
    private final String[] conditionForecasts;
    // weekday feels like temperatures
    private final Double[] feelsLikeForecasts;
    // weekday wind speeds
    private final Double[] windSpeedForecasts;
    // weekday precipitation forecasts
    private final Double[] precipitationForecasts;
    // weekday UV indices
    private final Double[] uvIndexForecasts;
    // weekday air quality forecasts
    private final Double[] airQualityForecasts;
    // weekday humidity forecasts
    private final Double[] humidityForecasts;

    public DisplayWeeklyOutputData(String city, Double[] temperatureForecasts,
                                   String[] conditionForecasts,
                                   Double[] feelsLikeForecasts,
                                   Double[] windSpeedForecasts,
                                   Double[] precipitationForecasts,
                                   Double[] uvIndexForecasts,
                                   Double[] airQualityForecasts,
                                   Double[] humidityForecasts) {
        this.city = city;
        this.temperatureForecasts = temperatureForecasts.clone();
        this.conditionForecasts = conditionForecasts.clone();
        this.feelsLikeForecasts = feelsLikeForecasts.clone();
        this.windSpeedForecasts = windSpeedForecasts.clone();
        this.precipitationForecasts = precipitationForecasts.clone();
        this.uvIndexForecasts = uvIndexForecasts.clone();
        this.airQualityForecasts = airQualityForecasts.clone();
        this.humidityForecasts = humidityForecasts.clone();
    }


    public Double[] getTemperatureForecasts() {
        return temperatureForecasts.clone();
    }

    public String[] getConditionForecasts() {
        return conditionForecasts.clone();
    }

    public Double[] getFeelsLikeForecasts() {
        return feelsLikeForecasts.clone();
    }

    public Double[] getWindSpeedForecasts() {
        return windSpeedForecasts.clone();
    }

    public Double[] getPrecipitationForecasts() {
        return precipitationForecasts.clone();
    }

    public Double[] getUvIndexForecasts() {
        return uvIndexForecasts.clone();
    }

    public Double[] getAirQualityForecasts() {
        return airQualityForecasts.clone();
    }

    public Double[] getHumidityForecasts() {
        return humidityForecasts.clone();
    }

}