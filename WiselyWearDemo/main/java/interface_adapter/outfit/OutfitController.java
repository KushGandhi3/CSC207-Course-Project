package interface_adapter.outfit;

import use_case.outfit.OutfitInputBoundary;

/**
 * Controller for the Outfit Use Case.
 */
public class OutfitController {
    private final OutfitInputBoundary outfitUseCaseInteractor;

    public OutfitController(OutfitInputBoundary outfitUseCaseInteractor) {
        this.outfitUseCaseInteractor = outfitUseCaseInteractor;
    }

    /**
     * Executes the outfit Use Case.
     */
    public void execute() {
        // Execute the use case without additional input data.
        outfitUseCaseInteractor.execute();
    }
}
