package entity.weather.hour_weather;

import org.json.JSONObject;

/**
 * Factory that creates HourWeatherData objects.
 */
public class ConcreteHourWeatherDataFactory implements HourWeatherDataFactory {

    @Override
    public HourWeatherData create(JSONObject weatherData) {
        return new ConcreteHourWeatherData(weatherData);
    }

}
