package use_case.display_home;

import entity.Weather;
import entity.WeatherFactory;

/**
 * The Display Home Input Boundary.
 */
public class DisplayHomeInteractor implements DisplayHomeInputBoundary {
    private final DisplayHomeWeatherDataAccessInterface displayHomeAccessObject;
    private final DisplayHomeOutputBoundary weatherPresenter;
    private final WeatherFactory weatherFactory;

    public DisplayHomeInteractor(DisplayHomeWeatherDataAccessInterface displayHomeAccessObject,
                                 DisplayHomeOutputBoundary weatherPresenter,
                                 WeatherFactory weatherFactory) {
        this.displayHomeAccessObject = displayHomeAccessObject;
        this.weatherPresenter = weatherPresenter;
        this.weatherFactory = weatherFactory;
    }

    @Override
    public void execute(DisplayHomeInputData displayHomeInputData) {

        // get the weather data from the API
        final Weather weather =
                weatherFactory.create(displayHomeInputData.getCurrentTemperature(),
                        displayHomeInputData.getHighTemperature(), displayHomeInputData.getLowTemperature(),
                        displayHomeInputData.getWeatherCondition() );
        displayHomeAccessObject.recordWeather(weather);

        // pass the weather data to output boundary
        final DisplayHomeOutputData displayHomeOutputData = new DisplayHomeOutputData("Toronto",
                weather.getCurrentTemperature(), weather.getHighTemperature(), weather.getLowTemperature(),
                weather.getTime(), weather.getWeatherCondition());
        weatherPresenter.prepareView(displayHomeOutputData);
    }
}
