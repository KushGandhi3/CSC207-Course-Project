package entity.weather.hourly_weather;

import entity.weather.hour_weather.HourWeatherData;

import java.util.List;

/**
 * Entity for the hourly weather forecasts in the coming day (all forecasts for each hour in the day).
 */
public class ConcreteHourlyWeatherData implements HourlyWeatherData {

    private final List<HourWeatherData> hourWeatherDataList;
    private final String timezone;
    private final String city;
    private final int lowTemperature;
    private final int highTemperature;

    public ConcreteHourlyWeatherData(List<HourWeatherData> hourWeatherDataList, String timezone, String city,
                                     int lowTemperature, int highTemperature) {
        this.city = city;
        this.timezone = timezone;
        this.hourWeatherDataList = hourWeatherDataList;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
    }

    @Override
    public List<HourWeatherData> getHourWeatherDataList() {
        return hourWeatherDataList;
    }

    @Override
    public int getLowTemperature() {
        return lowTemperature;
    }

    @Override
    public int getHighTemperature() {
        return highTemperature;
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
