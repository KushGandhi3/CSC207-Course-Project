package use_case.display_home;

/**
 * The Input Data for the DisplayHome Use Case.
 */

public class DisplayHomeInputData {

    private final String cityName;
    /**
     * Constructor to initialize DisplayHomeInputData with all necessary fields.
     * @param cityName The city name for the weather display.
     */
    public DisplayHomeInputData(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

}
