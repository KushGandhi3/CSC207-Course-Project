package use_case.display_home;

import entity.Weather;

/**
 * The interface of the DAO for the Display Home Use Case.
 */
public interface DisplayHomeWeatherDataAccessInterface {

    /**
     * Gets the current temperature.
     */
    String getWeatherData(double latitude, double longitude, String exclude);

}
