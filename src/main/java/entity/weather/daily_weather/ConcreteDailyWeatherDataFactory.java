package entity.weather.daily_weather;

import entity.weather.day_weather.DayWeatherData;

import java.util.List;

/**
 * Factory for creating DailyWeatherData objects.
 */
public class ConcreteDailyWeatherDataFactory implements DailyWeatherDataFactory {

    @Override
    public DailyWeatherData create(List<DayWeatherData> dayWeatherDataList, String timezone, String city) {
        return new ConcreteDailyWeatherData(dayWeatherDataList, timezone, city);
    }

}
