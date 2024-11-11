package use_case.display_home;

import entity.Weather;

import java.io.IOException;

/**
 * The interface of the DAO for the Display Home Use Case.
 */
public interface DisplayHomeWeatherDataAccessInterface {

    /**
     * Gets the current temperature.
     */
    Weather getWeatherData() throws IOException;

}
