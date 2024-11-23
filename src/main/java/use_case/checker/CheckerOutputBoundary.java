package use_case.checker;

public interface CheckerOutputBoundary {
    /**
     * Prepare the condition met view with the given exist string.
     */
    void prepareCondMetView();

    /**
     * Prepare the condition not met view with the given nonexist string.
     */
    void prepareCondNotMetView();
}