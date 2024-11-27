package use_case.display_summarization;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplaySummarizationDAI {

    /**
     * Get Weather data from the OpenWeather API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    HourlyWeatherData getHourlyWeatherData(String location) throws APICallException;

    /**
     * Get the summarization from the OpenAI API.
     * @param HourlyWeatherData the weather data used in the prompt
     * @return the summarization
     * @throws APICallException if the request fails.
     */
    String getSummarization(HourlyWeatherData HourlyWeatherData) throws APICallException;
}
