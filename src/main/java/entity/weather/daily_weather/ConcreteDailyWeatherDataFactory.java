package entity.weather.daily_weather;

import java.util.List;

import entity.weather.day_weather.DayWeatherData;

/**
 * Factory for creating DailyWeatherData objects.
 */
public class ConcreteDailyWeatherDataFactory implements DailyWeatherDataFactory {

    @Override
    public DailyWeatherData create(List<DayWeatherData> dayWeatherDataList, String timezone, String city) {
        return new ConcreteDailyWeatherData(dayWeatherDataList, timezone, city);
    }

}
