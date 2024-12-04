package entity.weather.hour_weather;

import org.json.JSONObject;

/**
 * Interface for factories that create HourWeatherData objects.
 */
public interface HourWeatherDataFactory {

    /**
     * Creates an HourWeatherData object from the provided JSON object containing weather data.
     *
     * @param weatherData a JSONObject containing weather data for a specific hour,
     *                    which includes information such as temperature, feels-like temperature,
     *                    wind speed, UV index, cloud cover, precipitation, humidity, weather condition,
     *                    city name, and timezone.
     * @return an HourWeatherData object populated with the data from the provided JSON object.
     */
    HourWeatherData create(JSONObject weatherData);
}
