package interface_adapter.display_summarization;

import use_case.display_summarization.DisplaySummarizationInputBoundary;
import use_case.display_summarization.DisplaySummarizationInputData;

/**
 * The Controller for when the user wants to analyze an outfit.
 */
public class SummarizationController {

    private final DisplaySummarizationInputBoundary analyzeOutfitInputInteractor;

    public SummarizationController(DisplaySummarizationInputBoundary analyzeOutfitInputInteractor) {
        this.analyzeOutfitInputInteractor = analyzeOutfitInputInteractor;
    }

    /**
     * Executes the Analyze Outfit use case.
     * @param imageData The image data to analyze.
     */

    public void execute(byte[] imageData) {
        //DisplaySummarizationInputData inputData =
                //new DisplaySummarizationInputData(imageData);
        //analyzeOutfitInputInteractor.execute(inputData);
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        analyzeOutfitInputInteractor.switchtoHomeView();
    }
}
