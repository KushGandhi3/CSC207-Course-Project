package entity;

import java.time.LocalDateTime;


/**
 * The representation of a weather forecast in our program.
 */
public interface Weather {

    /**
     * Returns the current temperature.
     * @return the current temperature.
     */
    Double getCurrentTemperature();

    /**
     * Returns the city location.
     * @return the city location
     */
    String getLocation();

}