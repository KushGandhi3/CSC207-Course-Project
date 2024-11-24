package entity.weather.daily_weather;

import entity.weather.day_weather.DayWeatherData;

import java.util.List;

/**
 * Entity for the daily weather forecasts in the coming week (all forecasts for every day of the week).
 */
public class ConcreteDailyWeatherData implements DailyWeatherData {

    // a list of the day weather forecasts
    private final List<DayWeatherData> dayWeatherDataList;
    private final String timezone;
    private final String city;

    public ConcreteDailyWeatherData(List<DayWeatherData> dayWeatherDataList, String timezone, String city) {
        this.timezone = timezone;
        this.dayWeatherDataList = dayWeatherDataList;
        this.city = city;
    }

    @Override
    public List<DayWeatherData> getDayWeatherDataList() {
        return dayWeatherDataList;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

}
