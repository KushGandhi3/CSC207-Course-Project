package interface_adapter.display_home;

public class DisplayHomeState {

    private String location = "";
    private Double currentTemperature = 0.0;

    // Getters
    public String getLocation() {
        return location;
    }

    public String getCurrentTemperature() {
        return String.valueOf(currentTemperature);
    }

    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

}