package use_case.display_home;

/**
 * The output boundary for the Display Home Use Case.
 */
public interface DisplayHomeOutputBoundary {

    /**
     * Prepares the view for the Display Home use case.
     * @param outputData the output data.
     */
    void prepareView(DisplayHomeOutputData outputData);

}
