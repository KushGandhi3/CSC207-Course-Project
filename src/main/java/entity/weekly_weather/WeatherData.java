package entity.weekly_weather;

/**
 * The interface for the weather data.
 */
public interface WeatherData {

    /**
     * Get the location.
     * @return the location.
     */
    String getLocation();

    /**
     * Get the current weather condition.
     * @return the current weather condition.
     */
    String getCurrentWeatherCondition();

    /**
     * Get the unix time.
     * @return the unix time.
     */
    long getUnixTime();

    /**
     * Get the current temperature.
     * @return the current temperature.
     */
    double getCurrentTemperature();

    /**
     * Get the high temperature.
     * @return the high temperature.
     */
    double getHighTemperature();

    /**
     * Get the low temperature.
     * @return the low temperature.
     */
    double getLowTemperature();
}
