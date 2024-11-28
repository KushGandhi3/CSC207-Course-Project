package use_case.display_daily;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

public interface DisplayDailyRecentCitiesDAI {

    RecentCityData getRecentCityData() throws RecentCitiesDataException;

}
