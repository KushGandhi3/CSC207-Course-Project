package use_case.display_home;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final String location;
    private final Double temperature;

    public DisplayHomeOutputData(String location, Double temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public Double getTemperature() {
        return temperature;
    }

}
