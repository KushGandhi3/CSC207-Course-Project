package use_case.display_hourly;

public class DisplayHourlyInputData {
    private final String city;

    public DisplayHourlyInputData(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
