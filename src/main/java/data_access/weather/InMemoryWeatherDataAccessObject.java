package data_access.weather;

import entity.weekly_weather.WeatherData;
import use_case.display_weekly.DisplayWeeklyDataAccessInterface;

public class InMemoryWeatherDataAccessObject implements DisplayWeeklyDataAccessInterface {

    @Override
    public WeatherData getWeatherData(String city) {
        return null;
    }

}
