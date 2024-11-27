package entity.weather.day_weather;

/**
 * Entity interface for DayWeatherData objects.
 */
public interface DayWeatherData {

    String getCity();

    String getCondition();

    int getTemperature();

    int getFeelsLikeTemperature();

    int getWindSpeed();

    int getUvIndex();

    int getCloudCover();

    int getPrecipitation();

    int getHumidity();

    String getTimezone();

}
