package entity.weather.day_weather;

/**
 * Entity interface for DayWeatherData objects.
 */
public interface DayWeatherData {

    /**
     * Retrieves the name of the city associated with the weather data.
     * @return the city name as a String
     */
    String getCity();

    /**
     * Retrieves the weather condition for the day.
     * @return the weather condition as a String, such as "sunny" or "rainy"
     */
    String getCondition();

    /**
     * Retrieves the temperature for the day.
     * @return the temperature as an integer in degrees Celsius
     */
    int getTemperature();

    /**
     * Retrieves the 'feels like' temperature for the day.
     * @return the 'feels like' temperature as an integer in degrees Celsius
     */
    int getFeelsLikeTemperature();

    /**
     * Retrieves the wind speed for the day.
     * @return the wind speed as an integer in meters per second
     */
    int getWindSpeed();

    /**
     * Retrieves the UV index for the day.
     * @return the UV index as an integer
     */
    int getUvIndex();

    /**
     * Retrieves the cloud cover for the day.
     * @return the cloud cover as a percentage
     */
    int getCloudCover();

    /**
     * Retrieves the precipitation level for the day.
     * @return the precipitation level as a percentage
     */
    int getPrecipitation();

    /**
     * Retrieves the humidity level for the day.
     * @return the humidity level as a percentage
     */
    int getHumidity();

    /**
     * Retrieves the timezone associated with the weather data.
     * @return the timezone as a String
     */
    String getTimezone();
}
