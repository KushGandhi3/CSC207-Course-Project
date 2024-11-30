package use_case.display_home;

import exception.APICallException;
import entity.weather.hourly_weather.HourlyWeatherData;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherData;

import java.util.List;

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
        // Fetch hourly weather data for the city
        HourlyWeatherData hourlyWeatherData = dataAccessObject.getWeatherData(displayHomeInputData.getCityName());

        if (hourlyWeatherData == null) {
            userPresenter.prepareFailView("Unable to fetch weather data.");
            return;
        }

        // Retrieve the relevant data from the HourlyWeatherData object
        String city = displayHomeInputData.getCityName();
        String timezone = hourlyWeatherData.getTimezone();
        int lowTemperature = Integer.parseInt(hourlyWeatherData.getLowTemperature());  // This should be set correctly in your data model
        String highTemperature = hourlyWeatherData.getHighTemperature();  // Same as above
        List<HourWeatherData> hourlyWeatherDataList = hourlyWeatherData.getHourWeatherDataList();

        // Use the factory to create HourlyWeatherData object (if needed)
        HourlyWeatherData createdHourlyWeatherData = weatherDataFactory.create(
                hourlyWeatherDataList, timezone, city, lowTemperature, Integer.parseInt(highTemperature));

        // Prepare the success view with the weather data
        DisplayHomeOutputData outputData = new DisplayHomeOutputData(createdHourlyWeatherData, false);
        userPresenter.prepareSuccessView(outputData);

    }
}
