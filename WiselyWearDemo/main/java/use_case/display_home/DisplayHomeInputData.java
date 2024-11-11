package use_case.display_home;

/**
 * The Input Data for the Display-Home Use Case.
 */
public class DisplayHomeInputData {

    public final int currentTemperature;
    public final int highTemperature;
    public final int lowTemperature;
    public final String weatherCondition;

    public DisplayHomeInputData(int currentTemperature, int highTemperature,
                                int lowTemperature, String weatherCondition) {
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.weatherCondition = weatherCondition;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

}