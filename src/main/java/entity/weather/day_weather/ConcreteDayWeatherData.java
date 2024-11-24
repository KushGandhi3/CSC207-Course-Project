package entity.weather.day_weather;

import org.json.JSONObject;

/**
 * Entity for the day weather forecast (i.e. summarized weather forecast for a single day).
 */
public class ConcreteDayWeatherData implements DayWeatherData {

    private final String city;
    private final String timezone;
    // the weather condition (i.e. sunny, rainy, etc)
    private final String condition;
    private final int temperature;
    private final int feelsLikeTemperature;
    private final int windSpeed;
    private final int uvIndex;
    private final int cloudCover;
    private final int precipitation;
    private final int humidity;

    public ConcreteDayWeatherData(JSONObject weatherData) {
        this.city = weatherData.getString("city");
        this.timezone = weatherData.getString("timezone");
        this.condition = weatherData.getString("condition");
        this.temperature = weatherData.getInt("temperature");
        this.feelsLikeTemperature = weatherData.getInt("feelsLikeTemperature");
        this.windSpeed = weatherData.getInt("windSpeed");
        this.uvIndex = weatherData.getInt("uvIndex");
        this.cloudCover = weatherData.getInt("cloudCover");
        this.precipitation = weatherData.getInt("precipitation");
        this.humidity = weatherData.getInt("humidity");
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

    @Override
    public int getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    @Override
    public int getWindSpeed() {
        return windSpeed;
    }

    @Override
    public int getUvIndex() {
        return uvIndex;
    }

    @Override
    public int getCloudCover() {
        return cloudCover;
    }

    @Override
    public int getPrecipitation() {
        return precipitation;
    }

    @Override
    public int getHumidity() {
        return humidity;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

}
