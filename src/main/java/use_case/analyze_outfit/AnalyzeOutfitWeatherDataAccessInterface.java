package use_case.analyze_outfit;

import entity.weather_data.WeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface AnalyzeOutfitWeatherDataAccessInterface {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    WeatherData getWeatherData(String location) throws APICallException;
}
