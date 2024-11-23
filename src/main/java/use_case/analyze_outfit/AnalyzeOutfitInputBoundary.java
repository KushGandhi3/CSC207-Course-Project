package use_case.analyze_outfit;

/**
 * The Input Boundary for the Analyze Outfit use case.
 */
public interface AnalyzeOutfitInputBoundary {

    /**
     * Switches to the Home View.
     */
    void switchtoHomeView();

    /**
     * Executes the Analyze Outfit use case.
     * @param inputData The input data for the use case.
     */
    void execute(AnalyzeOutfitInputData inputData);
}
