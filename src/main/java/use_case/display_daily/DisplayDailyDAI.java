package use_case.display_daily;

import entity.weather.daily_weather.DailyWeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for the daily weather data use case.
 */
public interface DisplayDailyDAI {

    /**
     * Get Daily Weather data from the API.
     * @param city the name of the city to get the forecast for
     * @return the weather data
     * @throws APICallException if the API request fails or the API Key is not set.
     */
    DailyWeatherData getDailyWeatherData(String city) throws APICallException;
}
