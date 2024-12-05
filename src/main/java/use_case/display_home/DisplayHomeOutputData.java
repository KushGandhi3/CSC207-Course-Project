package use_case.display_home;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final String city;
    private final String lowTemperature;
    private final String highTemperature;
    private final String temperature;
    private final String condition;
    private final String date;

    public DisplayHomeOutputData(String city, String lowTemperature, String highTemperature, String temperature,
                                 String condition, String date) {
        this.city = city;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.temperature = temperature;
        this.condition = condition;
        this.date = date;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }
}
