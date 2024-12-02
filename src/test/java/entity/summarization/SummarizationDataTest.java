package entity.summarization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import data_access.summarization.InMemorySummarizationDAO;
import exception.APICallException;

public class SummarizationDataTest {

    @Test
    public void readSummarizationDataTest() {
        final SummarizationFactory summarizationFactory = new ConcreteSummarizationFactory();
        final InMemorySummarizationDAO inMemorySummarizationDAO = new InMemorySummarizationDAO(summarizationFactory);

        try {
            final String prompt = "";
            final Summarization summarization = inMemorySummarizationDAO.getSummarization(prompt);

            assertNotNull(summarization);

            final String weatherSummary = "The weather in Toronto is expected to be cloudy throughout the day with "
                    + "temperatures fluctuating between -3 and 1 degree Celsius.";
            final String outfitSuggestions = "Considering the cold temperatures and cloudy conditions, it is advisable"
                    + " to wear warm clothing, including a thick coat, gloves and a hat.";
            final String travelAdvice = "The roads might be icy due to low temperatures so please exercise caution "
                    + "while commuting. If possible, avoid driving during peak hours to minimize travel delays.";

            assertEquals(weatherSummary, summarization.getWeatherSummary());
            assertEquals(outfitSuggestions, summarization.getOutfitSuggestion());
            assertEquals(travelAdvice, summarization.getTravelAdvice());
        }
        catch (APICallException exception) {
            fail(exception.getMessage());
        }
    }
}
