package use_case.display_home;

/**
 * Input Boundary for actions which are related to the DisplayHome.
 */
public interface DisplayHomeInputBoundary {
    /**
     * Executes the DisplayHome use case.
     * @param displayHomeInputData the input data
     */
    void execute(DisplayHomeInputData displayHomeInputData);
}
