package use_case.display_checker;

public interface DisplayCheckerOutputBoundary {
    /**
     * Prepare the condition met view with the given exist string.
     */
    void prepareCondMetView();

    /**
     * Prepare the condition not met view with the given nonexist string.
     */
    void prepareCondNotMetView();

    /**
     * Prepare the switch to home view.
     */
    void prepareHomeView();

    /**
     * Prepare the location empty view.
     */
    void prepareLocationEmptyView();

    /**
     * Prepare the invalid location view.
     */
    void prepareInvalidLocationView();
}
