package use_case.display_home;

import entity.weather.hourly_weather.HourlyWeatherData;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final HourlyWeatherData hourlyWeatherData;
    private final boolean useCaseFailed;

    /**
     * Constructor to initialize DisplayHomeOutputData with weather data and failure flag.
     *
     * @param hourlyWeatherData The weather data to be displayed.
     * @param useCaseFailed Whether the use case for fetching the weather data failed.
     */
    public DisplayHomeOutputData(HourlyWeatherData hourlyWeatherData, boolean useCaseFailed) {
        this.hourlyWeatherData = hourlyWeatherData;
        this.useCaseFailed = useCaseFailed;
    }

    public HourlyWeatherData getHourlyWeatherData() {
        return hourlyWeatherData;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
