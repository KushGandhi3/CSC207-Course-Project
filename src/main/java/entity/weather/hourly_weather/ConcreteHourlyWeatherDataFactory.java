package entity.weather.hourly_weather;

import java.util.List;

import entity.weather.hour_weather.HourWeatherData;

/**
 * Factory that creates HourlyWeatherData objects.
 */
public class ConcreteHourlyWeatherDataFactory implements HourlyWeatherDataFactory {

    /**
     * Creates an instance of HourlyWeatherData using the provided weather data, timezone, city,
     * and temperature range.
     * @param hourWeatherDataList a list of hour-specific weather data objects
     *                            containing temperature, wind speed, humidity, and other hourly details.
     * @param timezone the timezone of the location for which the weather data is relevant.
     * @param city the name of the city to which the weather data belongs.
     * @param lowTemperature the lowest temperature recorded or forecasted in the specified period.
     * @param highTemperature the highest temperature recorded or forecasted in the specified period.
     * @return a new instance of HourlyWeatherData populated with the provided data.
     */
    public HourlyWeatherData create(List<HourWeatherData> hourWeatherDataList, String timezone, String city,
                                    int lowTemperature, int highTemperature) {
        return new ConcreteHourlyWeatherData(hourWeatherDataList, timezone, city, lowTemperature, highTemperature);
    }
}
