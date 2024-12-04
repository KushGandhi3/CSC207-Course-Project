package entity.weather.hourly_weather;

import java.util.List;

import entity.weather.hour_weather.HourWeatherData;

/**
 * Entity interface for hourly weather data.
 */
public interface HourlyWeatherData {

    /**
     * Retrieves a list of weather data for each hour.
     * @return a list of HourWeatherData objects representing the weather information for individual hours.
     */
    List<HourWeatherData> getHourWeatherDataList();

    /**
     * Retrieves the lowest temperature recorded or forecasted in the specified period.
     * @return the lowest temperature in degrees.
     */
    int getLowTemperature();

    /**
     * Retrieves the highest temperature recorded or forecasted in the specified period.
     * @return the highest temperature in degrees.
     */
    int getHighTemperature();

    /**
     * Retrieves the name of the city associated with the weather data.
     * @return a string representing the city's name.
     */
    String getCity();

    /**
     * Retrieves the timezone associated with the weather data.
     * @return a string representing the timezone.
     */
    String getTimezone();

}
