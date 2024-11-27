package interface_adapter.display_checker;

public class DisplayCheckerState {

    private String location = "";
    private String weatherConditionOptions = "";
    private int startChecking;
    private int stopChecking;
    private String message = null;

    // Getters
    public String getLocation() {return location;}
    public String getWeatherConditionOptions() {return weatherConditionOptions;}
    public int getStartChecking() {return startChecking;}
    public int getStopChecking() {return stopChecking;}
    public String getMessage() { return message; }

    // Setters
    public void setLocation(String location) {this.location = location;}
    public void setWeatherConditionOptions(String weatherConditionOptions) {this.weatherConditionOptions = weatherConditionOptions;}
    public void setStartChecking(int startChecking) {this.startChecking = startChecking;}
    public void setStopChecking(int stopChecking) {this.stopChecking = stopChecking;}
    public void setMessage(String nonexist) { this.message = nonexist; }
}
