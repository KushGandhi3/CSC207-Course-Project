package use_case.display_summarization;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

/**
 * The Interface for DAO's that work with recent city data for the display summarization use case.
 */
public interface DisplaySummarizationRecentCitiesDAI {

    /**
     * Gets an entity containing data about recently requested cities.
     * @return an entity containing data about recently requested cities
     * @throws RecentCitiesDataException when there is an issue reading data
     */
    RecentCityData getRecentCityData() throws RecentCitiesDataException;
}
