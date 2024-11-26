package entity.recent_city;

import java.util.List;

/**
 * Entity that holds data related to Recent City Data.
 */
public class ConcreteRecentCityData implements RecentCityData {

    // front of the list is the most recently added cities
    private final List<String> recentCityList;

    public ConcreteRecentCityData(List<String> recentCityList) {
        this.recentCityList = recentCityList;
    }

    @Override
    public List<String> getRecentCityList() {
        return recentCityList;
    }

}
