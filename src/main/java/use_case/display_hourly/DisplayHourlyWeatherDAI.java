package use_case.display_hourly;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;

/**
 * The interface of the DAO for grabbing weather data for the display daily use case.
 */

public interface DisplayHourlyWeatherDAI {
    /**
     * Get Hourly Weather data from the API.
     * @param city the name of the city to get the forecast for
     * @return the weather data
     * @throws ApiCallException if the API request fails or the API Key is not set.
     */
    HourlyWeatherData getHourlyWeatherData(String city) throws ApiCallException;
}
