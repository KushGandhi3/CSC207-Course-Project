package use_case.display_home;

import entity.WeatherData;

/**
 * DAO (Data Access Object) for the Display Home Use Case.
 */
public interface DisplayHomeDataAccessInterface {

    /**
     * Checks if the given city exists in the system.
     * @param cityName the name of the city to check for
     * @return true if the city exists in the system; false otherwise
     */
    boolean cityExists(String cityName);

    /**
     * Fetches the weather data for the given city.
     * @param cityName the name of the city for which to fetch weather data
     * @return WeatherData object containing the weather information for the city
     */
    WeatherData fetchWeatherData(String cityName);
    // TODO
}
