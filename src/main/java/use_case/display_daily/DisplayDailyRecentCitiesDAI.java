package use_case.display_daily;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

/**
 * The Interface for DAO's that work with recent city data for the display daily use case.
 */
public interface DisplayDailyRecentCitiesDAI {

    /**
     * Adds a city to the recent cities data
     * @throws RecentCitiesDataException when there is an issue reading/writing data
     */
    void addCity(String city) throws RecentCitiesDataException;

    /**
     * Gets an entity containing data about recently requested cities.
     * @return an entity containing data about recently requested cities
     * @throws RecentCitiesDataException when there is an issue reading data
     */
    RecentCityData getRecentCityData() throws RecentCitiesDataException;

}
