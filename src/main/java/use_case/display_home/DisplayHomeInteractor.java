package use_case.display_home;

import exception.APICallException;
import entity.weather.hourly_weather.HourlyWeatherData;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherData;

/**
 * The DisplayHome Interactor.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {

    private final DisplayHomeDAI dataAccessObject;
    private final DisplayHomeOutputBoundary userPresenter;
    private final HourlyWeatherDataFactory weatherDataFactory;

    public DisplayHomeInteractor(DisplayHomeDAI dataAccessObject,
                                 DisplayHomeOutputBoundary userPresenter,
                                 HourlyWeatherDataFactory weatherDataFactory) {
        this.dataAccessObject = dataAccessObject;
        this.userPresenter = userPresenter;
        this.weatherDataFactory = weatherDataFactory;
    }

    @Override
    public void execute(DisplayHomeInputData displayHomeInputData) {
        try {
            // Check if the city exists by calling the API
            WeatherData weatherData = dataAccessObject.getWeatherData(displayHomeInputData.getCityName());

            if (weatherData == null) {
                userPresenter.prepareFailView("Unable to fetch weather data.");
                return;
            }

            // Retrieve the relevant data from the WeatherData object
            String city = displayHomeInputData.getCityName();
            String timezone = weatherData.getTimezone();
            int lowTemperature = weatherData.getLowTemperature();
            int highTemperature = weatherData.getHighTemperature();
            List<HourWeatherData> hourlyWeatherDataList = weatherData.getHourlyWeatherDataList();

            // Use the factory to create HourlyWeatherData object
            HourlyWeatherData hourlyWeatherData = weatherDataFactory.create(
                    hourlyWeatherDataList, timezone, city, lowTemperature, highTemperature);

            // Prepare the success view with the weather data
            DisplayHomeOutputData outputData = new DisplayHomeOutputData(hourlyWeatherData, false);
            userPresenter.prepareSuccessView(outputData);

        } catch (APICallException e) {
            // Handle the API call failure
            userPresenter.prepareFailView("Failed to fetch weather data due to API call failure.");
        }
    }
}
