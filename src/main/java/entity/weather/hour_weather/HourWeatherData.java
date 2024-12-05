package entity.weather.hour_weather;

/**
 * Entity interface for hour weather data.
 */
public interface HourWeatherData {

    /**
     * Retrieves the temperature for the specific hour of weather data.
     * @return the temperature in degrees Celsius.
     */
    int getTemperature();

    /**
     * Retrieves the 'feels like' temperature for the specific hour of weather data.
     * @return the 'feels like' temperature in degrees Celsius.
     */
    int getFeelsLikeTemperature();

    /**
     * Retrieves the wind speed for the specific hour of weather data.
     * @return the wind speed in kilometers per hour.
     */
    int getWindSpeed();

    /**
     * Retrieves the UV index for the specific hour of weather data.
     * @return the UV index as an integer.
     */
    int getUvIndex();

    /**
     * Retrieves the cloud cover for the specific hour of weather data.
     * @return the cloud cover percentage as an integer.
     */
    int getCloudCover();

    /**
     * Retrieves the humidity for the specific hour of weather data.
     * @return the humidity percentage as an integer.
     */
    int getHumidity();

    /**
     * Retrieves the precipitation level for the specific hour of weather data.
     * @return the precipitation level in millimeters.
     */
    int getPrecipitation();

    /**
     * Retrieves the weather condition for the specific hour of weather data.
     * @return the weather condition as a String.
     */
    String getCondition();

    /**
     * Retrieves the city name for the specific hour of weather data.
     * @return the city name as a String.
     */
    String getCity();

    /**
     * Retrieves the timezone for the specific hour of weather data.
     * @return the timezone as a String.
     */
    String getTimezone();
}
