package interface_adapter.display_home;

public class DisplayHomeState {
    private String location = "";
    private String time = "";
    private String weather = "";
    private String curr_temp = "";
    private String highest_temp = "";
    private String lowest_temp = "";
    // private Image(?) image = ?;
    private String locationError;

    // Getters
    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getWeather() {
        return weather;
    }

    public String getCurr_temp() {
        return curr_temp;
    }

    public String getHighest_temp() {
        return highest_temp;
    }

    public String getLowest_temp() {
        return lowest_temp;
    }

    public String getLocationError() {
        return locationError;
    }

    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setCurr_temp(String curr_temp) {
        this.curr_temp = curr_temp;
    }

    public void setHighest_temp(String highest_temp) {
        this.highest_temp = highest_temp;
    }

    public void setLowest_temp(String lowest_temp) {
        this.lowest_temp = lowest_temp;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }
}