package use_case.display_home;

/**
 * The Input Data for the Show-Current-Weather.java Use Case.
 */
public class DisplayHomeInputData {

    private final String location;


    public DisplayHomeInputData(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}