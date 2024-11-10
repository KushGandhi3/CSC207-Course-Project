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

    @Override // TODO: Complete the execute method
    public void execute(DisplayHomeInputData displayHomeInputData) {
        final Weather weather =
                weatherFactory.create(changePasswordInputData.getUsername(),
                changePasswordInputData.getPassword());
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                false);
        weatherPresenter.prepareSuccessView(changePasswordOutputData);
    }
}
