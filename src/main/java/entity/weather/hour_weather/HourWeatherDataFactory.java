package entity.weather.hour_weather;

import org.json.JSONObject;

/**
 * Interface for factories that create HourWeatherData objects.
 */
public interface HourWeatherDataFactory {

    HourWeatherData create(JSONObject weatherData);

}
