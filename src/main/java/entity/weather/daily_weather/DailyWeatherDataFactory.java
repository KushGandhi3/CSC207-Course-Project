package entity.weather.daily_weather;

import java.util.List;

import entity.weather.day_weather.DayWeatherData;

/**
 * Interface for factories that create DailyWeatherData objects.
 */
public interface DailyWeatherDataFactory {

    /**
     * Creates a DailyWeatherData object using a list of daily weather data, timezone, and city.
     * @param dayWeatherDataList the list of DayWeatherData objects representing individual day forecasts
     * @param timezone the timezone string for the daily weather data
     * @param city the name of the city for which the weather data is applicable
     * @return a DailyWeatherData object constructed with the provided daily forecasts, timezone, and city information
     */
    DailyWeatherData create(List<DayWeatherData> dayWeatherDataList, String timezone, String city);

}
