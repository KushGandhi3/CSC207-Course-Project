package use_case.display_daily;

import entity.weather.daily_weather.DailyWeatherData;
import exception.ApiCallException;

/**
 * The interface of the DAO for grabbing weather data for the display daily use case.
 */
public interface DisplayDailyWeatherDAI {

    /**
     * Get Daily Weather data from the API.
     * @param city the name of the city to get the forecast for
     * @return the weather data
     * @throws ApiCallException if the API request fails or the API Key is not set.
     */
    DailyWeatherData getDailyWeatherData(String city) throws ApiCallException;
}
