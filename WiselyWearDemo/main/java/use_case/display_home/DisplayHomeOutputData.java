package use_case.display_home;

import javax.swing.*;
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
    private final String weatherCondition;

    public DisplayHomeOutputData(String location, int temperature,
                                 int highTemperature, int lowTemperature, LocalDateTime time, String weatherCondition) {
        this.location = location;
        this.temperature = temperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.time = time;
        this.weatherCondition = weatherCondition;
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

    public String getWeatherCondition() { return weatherCondition; }

    public LocalDateTime getTime() { return time; }
}
