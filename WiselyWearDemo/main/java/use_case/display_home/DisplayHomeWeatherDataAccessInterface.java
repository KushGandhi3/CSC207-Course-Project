package use_case.display_home;

import entity.Weather;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * The interface of the DAO for the Display Home Use Case.
 */
public interface DisplayHomeWeatherDataAccessInterface {

    /**
     * Gets the current temperature.
     */
    double getWeatherData(double latitude, double longitude, String exclude) throws IOException;

}
