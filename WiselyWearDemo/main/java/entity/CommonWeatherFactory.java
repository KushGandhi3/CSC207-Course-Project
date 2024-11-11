package entity;

public class CommonWeatherFactory implements WeatherFactory {

    @Override
    public Weather create(double currentTemperature, String location) {
        return new CommonWeather(currentTemperature, location);
    }

}
