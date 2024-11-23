package use_case.display_home;

import entity.WeatherData;
import entity.WeatherDataFactory;

/**
 * The DisplayHome Interactor.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {

    private final DisplayHomeDataAccessInterface dataAccessObject;
    private final DisplayHomeOutputBoundary userPresenter;
    private final WeatherDataFactory weatherDataFactory;

    public DisplayHomeInteractor(DisplayHomeDataAccessInterface dataAccessObject,
                                 DisplayHomeOutputBoundary userPresenter,
                                 WeatherDataFactory weatherDataFactory) {
        this.dataAccessObject = dataAccessObject;
        this.userPresenter = userPresenter;
        this.weatherDataFactory = weatherDataFactory;
    }

    @Override
    public void execute(DisplayHomeInputData displayHomeInputData) {
        //Check if the city exists in the system (you can modify this logic based on how cities are handled)
        if (!dataAccessObject.cityExists(displayHomeInputData.getCityName())) {
            userPresenter.prepareFailView("City not found.");
            return;
        }

        //Fetch the weather data using the WeatherDataFactory
        WeatherData weatherData = dataAccessObject.fetchWeatherData(displayHomeInputData.getCityName());

        //If no weather data is returned, show failure view
        if (weatherData == null) {
            userPresenter.prepareFailView("Unable to fetch weather data.");
        } else {
            //Prepare the success view with the weather data
            DisplayHomeOutputData outputData = new DisplayHomeOutputData(weatherData, false);
            userPresenter.prepareSuccessView(outputData);
        }
    }

}
