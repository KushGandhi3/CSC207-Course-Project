package interface_adapter.display_history;

import java.util.ArrayList;
import java.util.List;

/**
 * State for the display_history use case.
 */
public class DisplayHistoryState {

    private List<String> cities = new ArrayList<>();

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
