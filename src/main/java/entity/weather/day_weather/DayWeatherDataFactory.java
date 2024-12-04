package entity.weather.day_weather;

import org.json.JSONObject;

/**
 * Interface for factories that create DayWeatherData objects.
 */
public interface DayWeatherDataFactory {

    /**
     * Creates a new instance of a DayWeatherData object using the provided JSON object.
     * @param weatherData a JSONObject containing the weather data, which includes fields such as city,
     *                    timezone, condition, temperature, feels like temperature, wind speed, UV index,
     *                    cloud cover, precipitation, and humidity
     * @return a DayWeatherData object populated with the values from the provided JSON object
     */
    DayWeatherData create(JSONObject weatherData);

}
