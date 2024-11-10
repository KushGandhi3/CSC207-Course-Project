package entity;

import java.time.LocalDateTime;


/**
 * An implementation of the Weather interface. Stores data about the
 * current weather forecast.
 */
public class CommonWeather implements Weather {

    private final int currentTemperature;
    private final int highTemperature;
    private final int lowTemperature;
    private final String weatherCondition;
    private final LocalDateTime currentTime;

    public CommonWeather(int currentTemperature, int highTemperature,
                                  int lowTemperature, String weatherCondition) {
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.weatherCondition = weatherCondition;
        this.currentTime = LocalDateTime.now();
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public int getHighTemperature() {
        return highTemperature;
    }

    @Override
    public int getLowTemperature() {
        return lowTemperature;
    }

    @Override
    public String getWeatherCondition() {
        return weatherCondition;
    }

    @Override
    public LocalDateTime getTime() {
        return currentTime;
    }

}