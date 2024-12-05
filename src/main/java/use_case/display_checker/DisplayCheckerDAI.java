package use_case.display_checker;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplayCheckerDAI {

    /**
     * Retrieves the hourly weather data for a specific location.
     *
     * @param location the name of the location for which to retrieve the weather data.
     * @return an instance of HourlyWeatherData containing weather information for each hour at the specified location.
     * @throws ApiCallException if there is an error during the API call to fetch the weather data.
     */
    HourlyWeatherData getHourlyWeatherData(String location) throws ApiCallException;
}

