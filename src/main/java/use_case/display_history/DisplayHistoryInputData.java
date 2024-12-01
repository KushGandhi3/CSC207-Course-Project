package use_case.display_history;

/**
 * Represents the input data for the display history use case, encapsulating the city information.
 */
public class DisplayHistoryInputData {
    private final String city;

    public DisplayHistoryInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}