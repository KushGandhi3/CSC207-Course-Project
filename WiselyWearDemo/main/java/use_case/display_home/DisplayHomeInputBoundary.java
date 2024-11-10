package use_case.display_home;

/**
 * Input Boundary for actions which are related to showing the current
 * weather report on the home screen.
 */
public interface DisplayHomeInputBoundary {

    /**
     * Executes the display_home use case.
     */
    void execute(DisplayHomeInputData displayHomeInputData);

}