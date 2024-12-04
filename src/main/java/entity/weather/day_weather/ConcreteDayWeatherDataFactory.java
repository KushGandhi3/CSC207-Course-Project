package entity.weather.day_weather;

import org.json.JSONObject;

/**
 * Factory for creating DayWeatherData objects.
 */
public class ConcreteDayWeatherDataFactory implements DayWeatherDataFactory {

    @Override
    public DayWeatherData create(JSONObject weatherData) {
        return new ConcreteDayWeatherData(weatherData);
    }
}
