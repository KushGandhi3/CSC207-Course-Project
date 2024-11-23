package use_case.checker;

import entity.weather_data.WeatherDataFactory;

/**
 * Tge Checker Interactor class.
 */
public class CheckerInteractor implements CheckerInputBoundary {

    private final CheckerDataAccessInterface checkerDataAccessInterface;
    private final CheckerOutputBoundary checkerOutputBoundary;
    private final WeatherDataFactory weatherDataFactory;

    public CheckerInteractor(CheckerDataAccessInterface checkerDataAccessInterface,
                             CheckerOutputBoundary checkerOutputBoundary,
                             WeatherDataFactory weatherFactory) {
        this.checkerDataAccessInterface = checkerDataAccessInterface;
        this.checkerOutputBoundary = checkerOutputBoundary;
        this.weatherDataFactory = weatherFactory;
    }

    /**
     * This method is used to check the weather data.
     * @param inputData the input data for the Checker use case.
     */
    @Override
    public void execute(CheckerInputData inputData) {
        String location = inputData.getLocation();
        String weatherConditionOptions = inputData.getWeatherConditionOptions();
        int startChecking = inputData.getStartChecking();
        int stopChecking = inputData.getStopChecking();

        boolean condMet = checkerDataAccessInterface.checkWeatherData(location, weatherConditionOptions, startChecking, stopChecking);

        // pass the output data to the output boundary
        if (condMet) {
            checkerOutputBoundary.prepareCondMetView();
        } else {
            checkerOutputBoundary.prepareCondNotMetView();
        }
    }
}
