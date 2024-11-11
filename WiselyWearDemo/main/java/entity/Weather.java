package java.entity;

import java.time.LocalDateTime;


/**
 * The representation of a weather forecast in our program.
 */
public interface Weather {

    /**
     * Returns the current temperature.
     * @return the current temperature.
     */
    int getCurrentTemperature();

    /**
     * Returns the highest forecasted temperature in the day.
     * @return the highest forecasted temperature in the day.
     */
    int getHighTemperature();

    /**
     * Returns the lowest forecasted temperature in the day.
     * @return the lowest forecasted temperature in the day.
     */
    int getLowTemperature();

    /**
     * Returns the weather condition (e.g. "Sunny", "Cloudy").
     * @return the weather condition.
     */
    String getWeatherCondition();

    /**
     * Returns the current time.
     * @return the current time.
     */
    LocalDateTime getTime();

}