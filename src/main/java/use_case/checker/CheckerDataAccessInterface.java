package use_case.checker;

import java.io.IOException;

import entity.weather_data.WeatherData;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface CheckerDataAccessInterface {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws IOException if the request fails.
     */
    WeatherData getWeatherData(String location) throws IOException;


