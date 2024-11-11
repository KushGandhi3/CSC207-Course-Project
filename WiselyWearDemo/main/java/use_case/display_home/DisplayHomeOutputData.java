package use_case.display_home;

import java.time.LocalDateTime;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final String location;
    private final int temperature;
    private final int highTemperature;
    private final int lowTemperature;
    private final LocalDateTime time;

    public DisplayHomeOutputData(String location, int temperature,
                                 int highTemperature, int lowTemperature, LocalDateTime time) {
        this.location = location;
        this.temperature = temperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public LocalDateTime getTime() { return time; }
}
