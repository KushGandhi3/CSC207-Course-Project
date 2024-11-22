package use_case.checker;

public interface CheckerOutputBoundary {
    /**
     * Prepare the success view with the given exist string.
     * @param exist the string to display in the success view
     */
    void prepareCondMetView(String exist);

    /**
     * Prepare the fail view with the given nonexist string.
     * @param nonexist the string to display in the fail view
     */
    void prepareCondNotMetView(String nonexist);

    /**
     * Switch to the home view.
     */
    void switchToHomeView();
}