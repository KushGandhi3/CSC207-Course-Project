package interface_adapter.display_home;
import use_case.checker.CheckerInputBoundary;
import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeInputData;

public class DisplayHomeController {
    /**
     * Controller for DisplayHome related Use Case.
     */
    private final DisplayHomeInputBoundary displayHomeInteractor;
    public DisplayHomeController(DisplayHomeInputBoundary displayHomeInteractor) {
        this.displayHomeInteractor = displayHomeInteractor;
    }
    /**
     * Executes the DisplayHome Use Case.
     */
    public void execute() {
        //ToDo
        displayHomeInteractor.execute(DisplayHomeInputData);
    }
}
