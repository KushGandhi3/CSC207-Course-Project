package data_access.recent_city;

import exception.RecentCitiesDataException;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * DAO for accessing data about recently viewed cities.
 */
public class RecentCitiesDAO {

    // the path to the data resource
    private static String path = "data/RecentCities.json";

    /**
     * Add a city to the recently viewed cities list.
     * @param city the city to add to the recently viewed city list
     * @throws RecentCitiesDataException when there is an issue writing data
     */
    public void addCity(String city) throws RecentCitiesDataException {
        try {
            Path filePath = Paths.get(path);
            String jsonContent = Files.readString(filePath);

            JSONArray recentCities = new JSONArray(jsonContent);

            // add the new city to the front of the city list
            recentCities.put(0, city);

            // write the updated JSON back to the file
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                writer.write(jsonContent);
            }

        } catch (IOException exception) {
            throw new RecentCitiesDataException(exception);
        }
    }

    /**
     * Reads the recent cities from the JSON file and returns them as a list.
     * @return a list of recent city names
     * @throws RecentCitiesDataException if there is an issue reading or parsing the data
     */
    public List<String> getCityList() throws RecentCitiesDataException {
        try {
            Path filePath = Paths.get(path);
            String jsonContent = Files.readString(filePath);

            JSONArray recentCities = new JSONArray(jsonContent);

            List<String> cityList = new ArrayList<>();
            for (int i = 0; i < recentCities.length(); i++) {
                cityList.add(recentCities.getString(i));
            }

            return cityList;

        } catch (IOException | JSONException exception) {
            throw new RecentCitiesDataException(exception);
        }
    }

}
