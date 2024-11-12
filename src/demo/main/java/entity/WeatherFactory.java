package entity;

import entity.Weather;

/**
 * Factory for creating weather forecasts.
 */
public interface WeatherFactory {

    /**
     * Creates a new Weather.
     * @param currentTemperature the current temperature.
     * @return the new weather forecast
     */
    Weather create(double currentTemperature, String location);
}