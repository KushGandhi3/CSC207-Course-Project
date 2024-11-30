package use_case.display_summarization;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for grabbing weather data for the display summarization use case.
 */
public interface DisplaySummarizationWeatherDAI {

    /**
     * Get Hourly Weather data from the API.
     * @param city the name of the city to get the forecast for
     * @return the weather data
     * @throws APICallException if the API request fails or the API Key is not set.
     */
    HourlyWeatherData getHourlyWeatherData(String city) throws APICallException;
}
