package use_case.display_home;

import entity.Weather;

/**
 * The interface of the DAO for the Display Home Use Case.
 */
public interface DisplayHomeWeatherDataAccessInterface {

    /**
     * Updates the system to record the current weather forecast.
     * @param weather the current weather forecast
     */
    void recordWeather(Weather weather);

}
