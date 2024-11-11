package use_case.display_home;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final String location;
    private final double temperature;

    public DisplayHomeOutputData(String location, double temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

}
