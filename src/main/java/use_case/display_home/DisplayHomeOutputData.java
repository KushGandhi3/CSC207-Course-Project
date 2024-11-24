package use_case.display_home;

/**
 * Output Data for the Display Home Use Case.
 */
public class DisplayHomeOutputData {

    private final WeatherData weatherData;
    private final boolean useCaseFailed;

    /**
     * Constructor to initialize DisplayHomeOutputData with weather data and failure flag.
     *
     * @param weatherData The weather data to be displayed.
     * @param useCaseFailed Whether the use case for fetching the weather data failed.
     */
    public DisplayHomeOutputData(WeatherData weatherData, boolean useCaseFailed) {
        this.weatherData = weatherData;
        this.useCaseFailed = useCaseFailed;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
