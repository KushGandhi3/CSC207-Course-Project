package entity.recent_city;

import java.util.List;

/**
 * Factory that creates Recent City data entities.
 */
public class ConcreteRecentCityDataFactory implements RecentCityDataFactory {

    // front of the recent city list are the most recently viewed cities
    @Override
    public RecentCityData create(List<String> recentCityDataList) {
        return new ConcreteRecentCityData(recentCityDataList);
    }

}
