package entity.weather.daily_weather;

import entity.weather.day_weather.DayWeatherData;

import java.util.List;

/**
 * Interface for factories that create DailyWeatherData objects.
 */
public interface DailyWeatherDataFactory {

    DailyWeatherData create(List<DayWeatherData> dayWeatherDataList, String timezone, String city);

}
