package use_case.display_home;

import entity.Weather;
import entity.WeatherFactory;

import java.io.IOException;

/**
 * The Display Home Interactor.
 */
public class DisplayHomeInteractor {

    private final String LOCATION = "Toronto";

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

    public void execute() throws IOException {

        final Weather weather = displayHomeAccessObject.getWeatherData();
    }

}
