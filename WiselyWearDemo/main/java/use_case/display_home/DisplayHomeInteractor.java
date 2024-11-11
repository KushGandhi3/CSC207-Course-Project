package use_case.display_home;

import entity.Weather;
import entity.WeatherFactory;

import java.io.IOException;

/**
 * The Display Home Interactor.
 */
public class DisplayHomeInteractor {

    private final DisplayHomeWeatherDataAccessInterface displayHomeAccessObject;
    private final DisplayHomeOutputBoundary weatherPresenter;

    public DisplayHomeInteractor(DisplayHomeWeatherDataAccessInterface displayHomeAccessObject,
                                 DisplayHomeOutputBoundary weatherPresenter) {
        this.displayHomeAccessObject = displayHomeAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    public void execute() throws IOException {

        final Weather weather = displayHomeAccessObject.getWeatherData();

        final DisplayHomeOutputData displayHomeOutputData =
                new DisplayHomeOutputData(weather.getLocation(),
                        weather.getCurrentTemperature());
        weatherPresenter.prepareView(displayHomeOutputData);
    }

}
