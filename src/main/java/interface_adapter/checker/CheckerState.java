package interface_adapter.checker;

public class CheckerState {

    private String location = "";
    // private String tempOptions = "Below";
    // private int tempValue = 0;
    private String weatherConditionOptions = "";
    private int startChecking;
    private int stopChecking;

    // Getters
    public String getLocation() {return location;}
    // public String getTempOptions() {return tempOptions;}
    // public int getTempValue() {return tempValue;}
    public String getWeatherConditionOptions() {return weatherConditionOptions;}
    public int getStartChecking() {return startChecking;}
    public int getStopChecking() {return stopChecking;}

    // Setters
    public void setLocation(String location) {this.location = location;}
    // public void setTempOptions(String tempOptions) {this.tempOptions = tempOptions;}
    // public void setTempValue(int tempValue) {this.tempValue = tempValue;}
    public void setWeatherConditionOptions(String weatherConditionOptions) {this.weatherConditionOptions = weatherConditionOptions;}
    public void setStartChecking(int startChecking) {this.startChecking = startChecking;}
    public void setStopChecking(int stopChecking) {this.stopChecking = stopChecking;}
}
