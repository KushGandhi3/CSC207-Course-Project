package use_case.display_checker;

import entity.weather.hourly_weather.HourlyWeatherData;
import exception.ApiCallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplayCheckerDAI {

    /**
     * Compare the hourly data with the wanted condition.
     * @param location the name of the location.
     * @return the weather data.
     */
    HourlyWeatherData getHourlyWeatherData(String location) throws ApiCallException;
}

