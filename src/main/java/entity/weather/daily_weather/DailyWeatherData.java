package entity.weather.daily_weather;

import java.util.List;

import entity.weather.day_weather.DayWeatherData;

/**
 * Entity interface for daily weather data.
 */
public interface DailyWeatherData {

    /**
     * Retrieves a list of DayWeatherData entities representing weather data for individual days.
     * @return a List of DayWeatherData objects, each containing weather information for a specific day.
     */
    List<DayWeatherData> getDayWeatherDataList();

    /**
     * Retrieves the name of the city for which the weather data is applicable.
     * @return the city name as a String.
     */
    String getCity();

    /**
     * Retrieves the timezone associated with the daily weather data.
     * @return the timezone as a String.
     */
    String getTimezone();

}
