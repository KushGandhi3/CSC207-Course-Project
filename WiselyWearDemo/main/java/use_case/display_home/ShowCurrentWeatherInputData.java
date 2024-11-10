package use_case.display_home;

/**
 * The Input Data for the Show-Current-Weather.java Use Case.
 */
public class ShowCurrentWeatherInputData {

    private final String location;
    private final int temperature;
    private final int highTemperature;
    private final int lowTemperature;


    public ShowCurrentWeatherInputData(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }



}