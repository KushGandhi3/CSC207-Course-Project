package entity.weather.daily_weather;

import entity.weather.day_weather.DayWeatherData;

import java.util.List;

/**
 * Entity interface for daily weather data.
 */
public interface DailyWeatherData {

    List<DayWeatherData> getDayWeatherDataList();

    String getCity();

    String getTimezone();

}
