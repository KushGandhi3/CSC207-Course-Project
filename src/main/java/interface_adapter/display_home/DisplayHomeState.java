package interface_adapter.display_home;

/**
 * The state for the Home View Model Use Case.
 */
public class DisplayHomeState {

    private String city = "";
    private String temperature = "";
    private String highTemperature = "";
    private String lowTemperature = "";
    private String condition = "";

    // Public getter methods
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

    // Public setter methods
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
}
