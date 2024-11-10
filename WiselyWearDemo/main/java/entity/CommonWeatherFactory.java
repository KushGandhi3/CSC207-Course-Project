package entity;

/**
 * Factory for creating CommonWeather objects.
 */
public class CommonWeatherFactory implements WeatherFactory {

    @Override
    public Weather create(int currentTemperature, int highTemperature, int lowTemperature, String weatherCondition) {
        return new CommonWeather(currentTemperature, highTemperature, lowTemperature, weatherCondition);
    }

}