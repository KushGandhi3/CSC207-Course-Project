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

    private String getCity() {
        return this.city;
    }

    private String getTemperature() {
        return this.temperature;
    }

    private String getHighTemperature() {
        return this.highTemperature;
    }

    private String getLowTemperature() {
        return this.lowTemperature;
    }

    private String getCondition() {
        return this.condition;
    }

    private void setCity(String city) {
        this.city = city;
    }

    private void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    private void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    private void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    private void setCondition(String condition) {
        this.condition = condition;
    }

}
