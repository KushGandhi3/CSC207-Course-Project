package entity.summarization;

/**
 * Concrete implementation of the SummarizationFactory interface.
 * This factory class is responsible for creating instances of ConcreteSummarization.
 */
public class ConcreteSummarizationFactory implements SummarizationFactory {

    @Override
    public Summarization createSummarization(String summary, String outfit, String travel) {
        return new ConcreteSummarization(summary, outfit, travel);
    }
}
