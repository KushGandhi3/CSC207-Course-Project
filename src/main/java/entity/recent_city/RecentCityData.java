package entity.recent_city;

import java.util.List;

/**
 * Interface for Recent City Data entities.
 */
public interface RecentCityData {

    /**
     * Retrieves the list of recently accessed city names.
     * The cities are ordered with the most recently accessed city at the front of the list.
     *
     * @return a list of strings representing the names of recently accessed cities.
     */
    List<String> getRecentCityList();
}
