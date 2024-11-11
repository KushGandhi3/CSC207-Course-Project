package interface_adapter.display_home;

/**
 * Controller for the display_home Use Case.
 */
public class DisplayHomeController {

    private final DisplayHomeInputBoundary displayHomeUseCaseInteractor;

    public DisplayHomeController(DisplayHomeInputBoundary displayHomeUseCaseInteractor) {
        this.displayHomeUseCaseInteractor = displayHomeUseCaseInteractor;
    }

    /**
     * Executes the Display Home Use Case.
     */
    public void execute() {
        // Execute the use case.
        final DisplayHomeInputData displayHomeInputData = new DisplayHomeInputData();
        displayHomeUseCaseInteractor.execute(displayHomeInputData);
    }
}