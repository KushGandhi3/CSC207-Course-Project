package entity;

/**
 * Factory for creating weather forecasts.
 */
public interface WeatherFactory {

    /**
     * Creates a new Weather.
     * @param currentTemperature the current temperature.
     * @param highTemperature the high temperature for the day.
     * @param lowTemperature the low temperature for the day.
     * @param weatherCondition the current weather condition.
     * @return the new weather forecast
     */
    Weather create(int currentTemperature, int highTemperature, int lowTemperature, String weatherCondition);

}