package use_case.display_home;

import entity.Weather;
import entity.WeatherFactory;

/**
 * The Display Home Interactor.
 */
public class DisplayHomeInteractor {

    private final DisplayHomeWeatherDataAccessInterface displayHomeAccessObject;
    private final DisplayHomeOutputBoundary weatherPresenter;

    public DisplayHomeInteractor(DisplayHomeWeatherDataAccessInterface displayHomeAccessObject,
                                 DisplayHomeOutputBoundary weatherPresenter,
                                 WeatherFactory weatherFactory) {
        this.displayHomeAccessObject = displayHomeAccessObject;
        this.weatherPresenter = weatherPresenter;
    }

    public void execute() {

        // pass the weather data to output boundary
        final DisplayHomeOutputData displayHomeOutputData =
                new DisplayHomeOutputData(LOCATION, /* don't know what goes
                here */ );
        weatherPresenter.prepareView(displayHomeOutputData);

    }

}
