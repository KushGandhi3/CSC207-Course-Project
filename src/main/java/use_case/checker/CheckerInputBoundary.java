package use_case.checker;

public interface CheckerInputBoundary {

    /**
     * Execute the checker use case
     * @param checkerInputData the input data
     */
    void execute(CheckerInputData checkerInputData);

    /**
     * Executes the switch to the home view use case
     */
    void switchToHomeView();
}
