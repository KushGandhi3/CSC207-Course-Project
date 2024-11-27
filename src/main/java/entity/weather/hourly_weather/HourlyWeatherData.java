package entity.weather.hourly_weather;

import entity.weather.hour_weather.HourWeatherData;

import java.util.List;

/**
 * Entity interface for hourly weather data
 */
public interface HourlyWeatherData {

    List<HourWeatherData> getHourWeatherDataList();

    int getLowTemperature();

    int getHighTemperature();

    String getCity();

    String getTimezone();

}
