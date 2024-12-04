package use_case.display_hourly;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplayHourlyDAI {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws ApiCallException if the request fails.
     */
    HourlyWeatherData getWeatherData(String location) throws ApiCallException;
}
