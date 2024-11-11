package use_case.display_home;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final String location;
    private final int temperature;

    public DisplayHomeOutputData(String location, int temperature) {
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
