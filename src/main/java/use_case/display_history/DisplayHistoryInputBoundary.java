package use_case.display_history;

/**
 * Interface for handling display history input actions.
 */
public interface DisplayHistoryInputBoundary {

    /**
     * Executes the history use case with the chosen city.
     * @param displayHistoryInputData the chosen city.
     */
    void execute(DisplayHistoryInputData displayHistoryInputData);

    /**
     * Executes the history use case with no city specified.
     */
    void execute();

    /**
     * Executes the "Switch to home" use case.
     */
    void switchToHomeView();
}
