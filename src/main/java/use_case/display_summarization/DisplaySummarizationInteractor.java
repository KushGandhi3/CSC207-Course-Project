package use_case.display_summarization;

import entity.summarization.Summarization;
import entity.summarization.SummarizationFactory;

/**
 * Interactor for the Display Summarization use case.
 */
public class DisplaySummarizationInteractor implements DisplaySummarizationInputBoundary {
    private final DisplaySummarizationDAI summaryDAO;
    private final DisplaySummarizationOutputBoundary summaryPresenter;
    private final SummarizationFactory summaryFactory;

    public DisplaySummarizationInteractor(DisplaySummarizationDAI summaryDAO, DisplaySummarizationOutputBoundary
            summaryPresenter, SummarizationFactory summaryFactory) {
        this.summaryDAO = summaryDAO;
        this.summaryPresenter = summaryPresenter;
        this.summaryFactory = summaryFactory;
    }

    @Override
    public void execute() {
    }

    @Override
    public void switchtoHomeView() {
    }
}
