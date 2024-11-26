package use_case.display_checker;

import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;

import java.util.List;

/**
 * The Checker Interactor class.
 */
public class DisplayCheckerInteractor implements DisplayCheckerInputBoundary {

    private final DisplayCheckerOutputBoundary displayCheckerOutputBoundary;
    private final DisplayCheckerDAI displayCheckerDAI;

    public DisplayCheckerInteractor(DisplayCheckerDAI displayCheckerDAI,
                                    DisplayCheckerOutputBoundary displayCheckerOutputBoundary) {
        this.displayCheckerDAI = displayCheckerDAI;
        this.displayCheckerOutputBoundary = displayCheckerOutputBoundary;
    }

    /**
     * This method is used to check the weather data.
     *
     * @param inputData the input data for the Checker use case.
     */
    @Override
    public void execute(DisplayCheckerInputData inputData) {
        String location = inputData.getLocation();
        String weatherConditionOptions = inputData.getWeatherConditionOptions();
        int startChecking = inputData.getStartChecking();
        int stopChecking = inputData.getStopChecking();

        // check if the location is not empty
        if (location.isEmpty()) {
            displayCheckerOutputBoundary.prepareLocationEmptyView();
            return;
        }

        boolean condMet = checkWeatherData(location, weatherConditionOptions, startChecking, stopChecking);

        // pass the output data to the output boundary
        if (condMet) {
            displayCheckerOutputBoundary.prepareCondMetView();
        } else {
            displayCheckerOutputBoundary.prepareCondNotMetView();
        }
    }

    @Override
    public void switchToHomeView() {
        displayCheckerOutputBoundary.prepareHomeView();
    }

    private boolean checkWeatherData(String location, String weatherConditionOptions, int startChecking, int stopChecking) {
        try {
            // get the hourly weather data from DAO
            HourlyWeatherData hourlyWeatherData = displayCheckerDAI.getHourlyWeatherData(location);

            if (hourlyWeatherData == null) {
                System.out.println("Error: Hourly weather data is null");
                return false;
            }

            List<HourWeatherData> hourlyForecast = hourlyWeatherData.getHourWeatherDataList();

            // Log whether the data is empty or not
            if (hourlyForecast.isEmpty()) {
                System.out.println("Hourly weather data is empty.");
            } else {
                System.out.println("Hourly weather data is not empty. Size: " + hourlyForecast.size());
            }

            // loop through the hourly data between the start time until the (stop time + start time)
            for (int i = startChecking; i <= (stopChecking + startChecking); i++) {
                if (i >= hourlyForecast.size()) {
                    System.out.println("Warning: Index " + i + " is out of bounds.");
                    return false;
                }

                HourWeatherData hourData = hourlyForecast.get(i);
                String weatherCondition = hourData.getCondition();

                // check if the weather condition at that hour matches the desired condition
                if (weatherCondition.equals(weatherConditionOptions)) {
                    return true;
                }
            }
            return false;
        } catch (APICallException e) {
            System.out.println("Error occurred while calling the API: " + e.getMessage());
            return false;
        }
    }

}
