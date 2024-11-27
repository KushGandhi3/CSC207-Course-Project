package data_access.recent_city;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.recent_city.RecentCityData;
import entity.recent_city.RecentCityDataFactory;
import exception.RecentCitiesDataException;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.in;

/**
 * DAO for accessing data about recently viewed cities.
 */
public class RecentCitiesDAO {

    // the path to the data resource
    private static final String path = "data/RecentCities.json";
    private final RecentCityDataFactory recentCityDataFactory;

    public RecentCitiesDAO(RecentCityDataFactory recentCityDataFactory) {
        this.recentCityDataFactory = recentCityDataFactory;
    }

    /**
     * Add a city to the recently viewed cities list. The data will be added to the recent city data file.
     * @param city the city to add to the recently viewed city list
     * @throws RecentCitiesDataException when there is an issue writing data
     */
    public void addCity(String city) throws RecentCitiesDataException {
        try (InputStream jsonFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(jsonFile, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);

            JSONArray recentCities = new JSONArray(jsonString);

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
     * Reads the recent cities from the JSON file and returns the corresponding RecentCityData entity.
     * @return a list of recent city names
     * @throws RecentCitiesDataException if there is an issue reading or parsing the data
     */
    public RecentCityData getCityList() throws RecentCitiesDataException {
        try {
            Path filePath = Paths.get(path);
            String jsonContent = Files.readString(filePath);

            JSONArray recentCities = new JSONArray(jsonContent);

            List<String> cityList = new ArrayList<>();
            for (int i = 0; i < recentCities.length(); i++) {
                cityList.add(recentCities.getString(i));
            }

            return this.recentCityDataFactory.create(cityList);

        } catch (IOException | JSONException exception) {
            throw new RecentCitiesDataException(exception);
        }
    }

}
