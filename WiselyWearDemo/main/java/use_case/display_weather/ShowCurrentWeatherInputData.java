package use_case.display_weather;

/**
 * The Input Data for the Show-Current-Weather.java Use Case.
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