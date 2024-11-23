package use_case.display_checker;

import entity.weather_data.WeatherDataFactory;

/**
 * Tge Checker Interactor class.
 */
public class DisplayCheckerInteractor implements DisplayCheckerInputBoundary {

    private final DisplayCheckerDAI displayCheckerDAI;
    private final DisplayCheckerOutputBoundary displayCheckerOutputBoundary;
    private final WeatherDataFactory weatherDataFactory;

    public DisplayCheckerInteractor(DisplayCheckerDAI displayCheckerDAI,
                                    DisplayCheckerOutputBoundary displayCheckerOutputBoundary,
                                    WeatherDataFactory weatherFactory) {
        this.displayCheckerDAI = displayCheckerDAI;
        this.displayCheckerOutputBoundary = displayCheckerOutputBoundary;
        this.weatherDataFactory = weatherFactory;
    }

    /**
     * This method is used to check the weather data.
     * @param inputData the input data for the Checker use case.
     */
    @Override
    public void execute(DisplayCheckerInputData inputData) {
        String location = inputData.getLocation();
        String weatherConditionOptions = inputData.getWeatherConditionOptions();
        int startChecking = inputData.getStartChecking();
        int stopChecking = inputData.getStopChecking();

        boolean condMet = displayCheckerDAI.checkWeatherData(location, weatherConditionOptions, startChecking, stopChecking);

        // pass the output data to the output boundary
        if (condMet) {
            displayCheckerOutputBoundary.prepareCondMetView();
        } else {
            displayCheckerOutputBoundary.prepareCondNotMetView();
        }
    }
}
