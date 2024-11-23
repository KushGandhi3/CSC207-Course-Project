package data_access.weather;

import use_case.display_daily.DisplayDailyDAI

public class InMemoryWeatherDataAccessObject implements DisplayDailyDAI {

    @Override
    public WeatherData getWeatherData(String city) {
        return WeatherData;
    }

}
