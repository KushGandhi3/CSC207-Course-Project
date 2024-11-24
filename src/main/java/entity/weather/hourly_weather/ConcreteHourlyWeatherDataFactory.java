package entity.weather.hourly_weather;

import entity.weather.hour_weather.HourWeatherData;

import java.util.List;

/**
 * Factory that creates HourlyWeatherData objects.
 */
public class ConcreteHourlyWeatherDataFactory implements HourlyWeatherDataFactory {

    public HourlyWeatherData create(List<HourWeatherData> hourWeatherDataList, String timezone, String city,
                                    int lowTemperature, int highTemperature) {
        return new ConcreteHourlyWeatherData(hourWeatherDataList, timezone, city, lowTemperature, highTemperature);
    }

}
