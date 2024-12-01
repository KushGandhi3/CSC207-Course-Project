package interface_adapter.display_summarization;

import interface_adapter.ViewManagerModel;
import use_case.display_summarization.DisplaySummarizationOutputBoundary;
import use_case.display_summarization.DisplaySummarizationOutputData;

/**
 * Presenter for the Display Summarization use case.
 */
public class DisplaySummarizationPresenter implements DisplaySummarizationOutputBoundary {

    private final DisplaySummarizationViewModel displaySummarizationViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplaySummarizationPresenter(DisplaySummarizationViewModel displaySummarizationViewModel,
                                         ViewManagerModel viewManagerModel) {
        this.displaySummarizationViewModel = displaySummarizationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Display Summarization use case by updating
     * the ViewModel state with the weather, outfit, and travel information
     * from the response, and triggers a property change event.
     *
     * @param response the output data of the summarization use case
     */
    @Override
    public void prepareSuccessView(DisplaySummarizationOutputData response) {
        final DisplaySummarizationState displaySummarizationState = displaySummarizationViewModel.getState();
        displaySummarizationState.setWeather(response.getWeather());
        displaySummarizationState.setOutfit(response.getOutfit());
        displaySummarizationState.setTravel(response.getTravel());
        displaySummarizationViewModel.setState(displaySummarizationState);
        displaySummarizationViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        final DisplaySummarizationState displaySummarizationState = displaySummarizationViewModel.getState();
        displaySummarizationState.setWeather(errorMessage);
        displaySummarizationState.setOutfit(errorMessage);
        displaySummarizationState.setTravel(errorMessage);
        displaySummarizationViewModel.setState(displaySummarizationState);
        displaySummarizationViewModel.firePropertyChanged();
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState("home");
        viewManagerModel.firePropertyChanged();
    }
}
