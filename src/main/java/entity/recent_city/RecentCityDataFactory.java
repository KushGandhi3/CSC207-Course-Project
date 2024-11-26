package entity.recent_city;

import java.util.List;

/**
 * Interface for creating RecentCityData entities.
 */
public interface RecentCityDataFactory {

    // front of the recent city list are the most recently viewed cities
    RecentCityData create(List<String> recentCityDataList);

}
