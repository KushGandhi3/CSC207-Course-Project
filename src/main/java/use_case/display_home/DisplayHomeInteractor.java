package use_case.display_home;

import entity.weather.WeatherData;
import entity.weather.WeatherDataFactory;
import exception.APICallException;

/**
 * The DisplayHome Interactor.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {

    private final DisplayHomeDAI dataAccessObject; // Use DisplayHomeDAI here
    private final DisplayHomeOutputBoundary userPresenter;
    private final WeatherDataFactory weatherDataFactory;

    public DisplayHomeInteractor(DisplayHomeDAI dataAccessObject, // Updated constructor parameter
                                 DisplayHomeOutputBoundary userPresenter,
                                 WeatherDataFactory weatherDataFactory) {
        this.dataAccessObject = dataAccessObject;
        this.userPresenter = userPresenter;
        this.weatherDataFactory = weatherDataFactory;
    }

    @Override
    public void execute(DisplayHomeInputData displayHomeInputData) {
        // Check if the city exists in the system (you can modify this logic based on how cities are handled)
        String cityName = displayHomeInputData.getCityName();

        try {
            // Fetch the weather data using the DisplayHomeDAI
            WeatherData weatherData = dataAccessObject.getWeatherData(cityName);

            // If no weather data is returned, show failure view
            if (weatherData == null) {
                userPresenter.prepareFailView("Unable to fetch weather data.");
            } else {
                // Prepare the success view with the weather data
                DisplayHomeOutputData outputData = new DisplayHomeOutputData((entity.WeatherData) weatherData, false);
                userPresenter.prepareSuccessView(outputData);
            }
        } catch (APICallException e) {
            // Handle exception if the API call fails
            userPresenter.prepareFailView("API call failed: " + e.getMessage());
        }
    }
}
