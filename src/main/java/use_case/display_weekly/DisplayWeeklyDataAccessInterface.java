package use_case.display_weekly;

import entity.weekly_weather.WeatherData;

/**
 * DAO Interface for the Weekly Data use-case.
 */
public interface DisplayWeeklyDataAccessInterface {

    /**
     * Return the WeatherData object with the current weather information.
     * @param city the city to grab weather information
     * @return the WeatherData object with current weather information
     */
    WeatherData getWeatherData(String city);

}
