package entity.weather.hour_weather;

/**
 * Entity interface for hour weather data.
 */
public interface HourWeatherData {

    int getTemperature();

    int getFeelsLikeTemperature();

    int getWindSpeed();

    int getUvIndex();

    int getCloudCover();

    int getHumidity();

    int getPrecipitation();

    String getCondition();

    String getCity();

    String getTimezone();

}
