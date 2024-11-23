package use_case.display_home;

/**
 * The output boundary for the DisplayHome Use Case.
 */
public interface DisplayHomeOutputBoundary {
    /**
     * Prepares the success view for the DisplayHome Use Case
     * @param outputData the output data
     */
    void prepareSuccessView(DisplayHomeOutputData outputData);


    /**
     * Prepares the failure view for the DisplayHome Use Case.
     * @param errorMessage the explanation of the failure.
     */
    void prepareFailView(String errorMessage);
}
