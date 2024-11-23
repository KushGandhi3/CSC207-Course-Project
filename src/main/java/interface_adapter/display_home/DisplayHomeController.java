package interface_adapter.display_home;

import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeInputData;

/**
 * The controller for the Display Home Use Case.
 */
public class DisplayHomeController {

    private final DisplayHomeInputBoundary displayHomeUseCaseInteractor;

    public DisplayHomeController(DisplayHomeInputBoundary displayHomeUseCaseInteractor) {
        this.displayHomeUseCaseInteractor = displayHomeUseCaseInteractor;
    }

    /**
     * Executes the Display Home Use Case.
     * @param cityName the name of the city to fetch weather data for
     * @param daily whether the user wants the daily weather
     * @param weekly whether the user wants the weekly weather
     * @param checker whether the user wants a weather checker
     * @param outfit whether the user wants outfit suggestions
     * @param map whether the user wants to see a map
     */
    public void execute(String cityName, boolean daily, boolean weekly, boolean checker, boolean outfit, boolean map) {
        // Create the input data for the use case
        final DisplayHomeInputData displayHomeInputData = new DisplayHomeInputData(
                cityName, daily, weekly, checker, outfit, map
        );

        // Execute the Interactor
        displayHomeUseCaseInteractor.execute(displayHomeInputData);
    }

}
