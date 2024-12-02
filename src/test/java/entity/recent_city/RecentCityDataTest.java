package entity.recent_city;

import data_access.recent_city.RecentCitiesDAO;
import exception.RecentCitiesDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecentCityDataTest {

    static final String filePath = "src/main/resources/data/RecentCities.json";

    /**
     * Clear the JSON file to an empty JSON Array
     */
    @BeforeEach
    void clearJsonFile() {
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write("[]");
        } catch (IOException e) {
            System.err.println("Failed to clear JSON file: " + e.getMessage());
        }
    }

    @Test
    void readRecentCitiesTest() {
        RecentCityDataFactory recentCityDataFactory = new ConcreteRecentCityDataFactory();
        RecentCitiesDAO recentCitiesDAO = new RecentCitiesDAO(recentCityDataFactory);

        // add new cities to the recent cities data file
        try {
            recentCitiesDAO.addCity("Toronto");
            recentCitiesDAO.addCity("Vancouver");
            recentCitiesDAO.addCity("Waterloo");

            RecentCityData recentCityData = recentCitiesDAO.getRecentCityData();
            List<String> recentCityDataList = recentCityData.getRecentCityList();

            assertEquals(3, recentCityDataList.size());

            List<String> data = List.of("Waterloo", "Vancouver", "Toronto");
            for (int i = 0; i < recentCityDataList.size(); i++) {
                assertEquals(data.get(i), recentCityDataList.get(i));
            }
        } catch (RecentCitiesDataException exception) {
            fail(exception.getMessage());
        }
    }
}
