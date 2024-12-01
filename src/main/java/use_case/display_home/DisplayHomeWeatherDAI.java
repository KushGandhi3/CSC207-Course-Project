package use_case.display_home;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;

/**
 * The data access interface for the Display Home Use Case used for grabbing weather data.
 */
public interface DisplayHomeWeatherDAI {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    HourlyWeatherData getHourlyWeatherData(String location) throws APICallException;

}
