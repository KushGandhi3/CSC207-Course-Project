package entity.weather.hourly_weather;

import entity.weather.hour_weather.HourWeatherData;

import java.util.List;

/**
 * Interface for factories that create HourlyWeatherData objects.
 */
public interface HourlyWeatherDataFactory {

    HourlyWeatherData create(List<HourWeatherData> hourWeatherDataList, String timezone, String city,
                             int lowTemperature, int highTemperature);

}
