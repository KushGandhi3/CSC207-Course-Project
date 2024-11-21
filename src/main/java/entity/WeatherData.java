package entity;

public class WeatherData {

    private final String temperature;
    private final String highTemperature;
    private final String lowTemperature;
    private final String condition;

    public WeatherData(String temperature, String highTemperature, String lowTemperature, String condition) {
        this.temperature = temperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.condition = condition;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public String getCondition() {
        return condition;
    }
}
