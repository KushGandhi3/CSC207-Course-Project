package use_case.display_weekly;

/**
 * The Input Data for the Weekly View Use Case.
 */
public class DisplayWeeklyInputData {

    private final String city;

    public DisplayWeeklyInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
