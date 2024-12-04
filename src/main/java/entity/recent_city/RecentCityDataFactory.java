package entity.recent_city;

import java.util.List;

/**
 * Interface for creating RecentCityData entities.
 */
public interface RecentCityDataFactory {

    /**
     * Creates a new RecentCityData entity from a list of recently accessed city names.
     *
     * @param recentCityDataList a list of strings representing the names of recently accessed cities
     * @return a RecentCityData entity containing the recent city data
     */
    RecentCityData create(List<String> recentCityDataList);

}
