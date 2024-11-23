package use_case.display_checker;

public interface DisplayCheckerInputBoundary {

    /**
     * Execute the checker use case
     * @param displayCheckerInputData the input data
     */
    void execute(DisplayCheckerInputData displayCheckerInputData);

    /**
     * Executes the switch to the home view use case
     */
    void switchToHomeView();
}
