package use_case.display_weekly;

import exception.APICallException;

/**
 * The interactor for the Display Weekly use-case.
 */
public class DisplayWeeklyInteractor implements DisplayWeeklyInputBoundary {
    private final DisplayWeeklyDataAccessInterface displayWeeklyDataAccessObject;
    private final DisplayWeeklyOutputBoundary displayWeeklyPresenter;

    public DisplayWeeklyInteractor(
            DisplayWeeklyDataAccessInterface displayWeeklyDataAccessObject,
            DisplayWeeklyOutputBoundary displayWeeklyPresenter) {
        this.displayWeeklyDataAccessObject= displayWeeklyDataAccessObject;
        this.displayWeeklyPresenter = displayWeeklyPresenter;
    }

    @Override
    public void execute(DisplayWeeklyInputData displayWeeklyInputData) {
        final String city = displayWeeklyInputData.getCity();

        try (weatherDataAccessObject.) {

        } catch (APICallException exception) {
            displayWeeklyPresenter.prepareFailView(exception.getMessage());
        }

    }
}
