package use_case.display_history;

import java.util.List;

/**
 * Output data for the display history use case.
 */
public class DisplayHistoryOutputData {

    private final List<String> cities;

    public DisplayHistoryOutputData(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getCities() {
        return cities;
    }
}