package interface_adapter.display_home;

/**
 * The state for the Display Home Use Case.
 */
public class DisplayHomeState {

    private String city = "-";
    private String lowTemperature = "-°C";
    private String highTemperature = "-°C";
    private String temperature = "-°C";
    private String condition = "-";
    private String date = "-, - -";

    public String getCity() {
        return this.city;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public String getHighTemperature() {
        return this.highTemperature;
    }

    public String getLowTemperature() {
        return this.lowTemperature;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
