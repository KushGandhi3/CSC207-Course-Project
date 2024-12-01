package use_case.display_home;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

/**
 * The data access interface for recent city data in the Display Home use-case.
 */
public interface DisplayHomeRecentCitiesDAI {

    /**
     * Adds the city to the recent city data.
     * @param city the city to add.
     * @throws RecentCitiesDataException when there is an issue writing data
     */
    void addCity(String city) throws RecentCitiesDataException;

    /**
     * Gets the recent city data as a RecentCityData entity.
     * @return the corresponding RecentCityData entity
     * @throws RecentCitiesDataException when there is an issue reading or parsing the data
     */
    RecentCityData getRecentCityData() throws RecentCitiesDataException;

}
