package entity;

import java.time.LocalDateTime;


/**
 * An implementation of the WeatherForecast interface. Stores data about the
 * current weather forecast.
 */
public class WeatherForecastCurrent implements WeatherForecast {

    private final int currentTemperature;
    private final int highTemperature;
    private final int lowTemperature;
    private final String weatherCondition;
    private final LocalDateTime currentTime;

    public WeatherForecastCurrent(int currentTemperature, int highTemperature,
                                  int lowTemperature, String weatherCondition,
                                  LocalDateTime) {
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.weatherCondition = weatherCondition;
        this.currentTime = LocalDateTime.now();
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public int getHighTemperature() {
        return highTemperature;
    }

    @Override
    public int getLowTemperature() {
        return lowTemperature;
    }

    @Override
    public String getWeatherCondition() {
        return weatherCondition;
    }

    @Override
    public LocalDateTime getTime() {
        return currentTime;
    }
}