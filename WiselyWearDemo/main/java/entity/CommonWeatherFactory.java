package entity;

public class CommonWeatherFactory implements WeatherFactory {

    @Override
    public Weather create(int currentTemperature, String location) {
        return new CommonWeather(currentTemperature, location);
    }

}
