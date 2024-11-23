package use_case.display_daily;

/**
 * The Input Data for the Weekly View Use Case.
 */
public class DisplayDailyInputData {

    private final String city;

    public DisplayDailyInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
