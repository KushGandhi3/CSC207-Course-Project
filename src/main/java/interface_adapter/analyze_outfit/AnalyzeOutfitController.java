package interface_adapter.analyze_outfit;

import use_case.analyze_outfit.AnalyzeOutfitInputBoundary;
import use_case.analyze_outfit.AnalyzeOutfitInputData;

/**
 * The Controller for when the user wants to analyze an outfit.
 */
public class AnalyzeOutfitController {

    private final AnalyzeOutfitInputBoundary analyzeOutfitInputInteractor;

    public AnalyzeOutfitController(AnalyzeOutfitInputBoundary analyzeOutfitInputInteractor) {
        this.analyzeOutfitInputInteractor = analyzeOutfitInputInteractor;
    }

    /**
     * Executes the Analyze Outfit use case.
     * @param imageData The image data to analyze.
     */

    public void execute(byte[] imageData) {
        AnalyzeOutfitInputData inputData = new AnalyzeOutfitInputData(imageData);
        analyzeOutfitInputInteractor.execute(inputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        analyzeOutfitInputInteractor.switchtoHomeView();
    }
}
