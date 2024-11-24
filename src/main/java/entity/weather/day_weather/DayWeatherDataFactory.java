package entity.weather.day_weather;

import org.json.JSONObject;

/**
 * Interface for factories that create DayWeatherData objects.
 */
public interface DayWeatherDataFactory {

    DayWeatherData create(JSONObject weatherData);

}
