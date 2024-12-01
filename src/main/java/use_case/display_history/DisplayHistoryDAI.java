package use_case.display_history;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

/**
 * The interface of the recentCitiesDAO to update the list with the selected one.
 */
public interface DisplayHistoryDAI {

    /**
     * Gets the list of recently accessed cities.
     * @return list of city entity.
     * @throws RecentCitiesDataException if there is an error getting the list of cities.
     */
    RecentCityData getRecentCityData() throws RecentCitiesDataException;

    /**
     * Adds a city to the RecentCities file.
     * @param city the city
     * @throws RecentCitiesDataException if there is an error while updating the recent city's data.
     */
    void addCity(String city) throws RecentCitiesDataException;
}
