package entity.weekly_weather;

/**
 * The factory for the weather data.
 */
public interface WeatherDataFactory {

    /**
     * Create a new weather data object.
     * @param location the name of the location.
     * @param currentWeatherCondition the current weather condition.
     * @param unixTime the unix time.
     * @param currentTemperature the current temperature.
     * @param highTemperature the high temperature.
     * @param lowTemperature the low temperature.
     * @return the new weather data object.
     */
    WeatherData createWeatherData(String location, String currentWeatherCondition, long unixTime,
                                  double currentTemperature, double highTemperature, double lowTemperature);
}
