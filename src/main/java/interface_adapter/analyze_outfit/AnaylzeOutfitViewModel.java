package interface_adapter.analyze_outfit;

import interface_adapter.ViewModel;

/**
 * The ViewModel for when the user wants to analyze an outfit.
 */
public class AnaylzeOutfitViewModel extends ViewModel<AnalyzeOutfitState> {

    public AnaylzeOutfitViewModel() {
        super("Analyze Outfit");
        setState(new AnalyzeOutfitState());
    }
}
