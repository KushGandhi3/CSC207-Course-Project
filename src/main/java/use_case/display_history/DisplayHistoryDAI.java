package use_case.display_history;

import entity.recent_city.RecentCityData;

/**
 * The interface of the DAO for grabbing the recent city data for the display history use case
 */
public interface DisplayHistoryDAI {

    /**
     * Get the recent city data
     * @return the recent city data
     */
    RecentCityData getRecentCityData();

    /**
     * Set the recent city data
     * @param location the wanted location
     */
    void setChosenLocation(String location);
}
