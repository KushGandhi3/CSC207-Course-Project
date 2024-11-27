package entity.recent_city;

import java.util.List;

/**
 * Interface for Recent City Data entities.
 */
public interface RecentCityData {

    // front of the recent city list are the most recently viewed cities
    List<String> getRecentCityList();

}
