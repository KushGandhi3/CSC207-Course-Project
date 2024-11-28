package use_case.display_home;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplayHomeDAI {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    HourlyWeatherData getHourlyWeatherData(String location) throws APICallException;
}
