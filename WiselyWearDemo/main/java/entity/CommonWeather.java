package entity;

public class CommonWeather implements Weather {

    int currentTemperature;
    String location;

    public CommonWeather(int currentTemperature, String location) {
        this.currentTemperature = currentTemperature;
        this.location = location;
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public String getLocation() {
        return location;
    }

}
