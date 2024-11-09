package use_case.show_current_weather;

/**
 * The Input Data for the Show-Current-WeatherForecast.java Use Case.
 */
public class ShowCurrentWeatherInputData {

    private final String location;

    public ShowCurrentWeatherInputData(String location) {
        this.location = location;
    }

    String getLocation() {
        return location;
    }

}