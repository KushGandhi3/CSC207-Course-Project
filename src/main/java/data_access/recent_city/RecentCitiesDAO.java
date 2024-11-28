package data_access.recent_city;

import entity.recent_city.RecentCityData;
import entity.recent_city.RecentCityDataFactory;
import exception.RecentCitiesDataException;
import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for accessing data about recently viewed cities.
 */
public class RecentCitiesDAO {

    // the path to the data resource
    private static final String RESOURCES_FOLDER_PATH = "src/main/resources/";
    private static final String RECENT_CITIES_PATH = "/data/RecentCities.json";

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
        JSONArray recentCitiesArray = readRecentCities();
        // check if the city is already in the array
        if (!cityExists(city, recentCitiesArray)) {
            // add the new city at the front of the JSON array
            List<String> recentCitiesList = new ArrayList<>();
            recentCitiesList.add(city);
            for (int i = 0; i < recentCitiesArray.length(); i++) {
                recentCitiesList.add(recentCitiesArray.getString(i));
            }
            recentCitiesArray = new JSONArray(recentCitiesList);

            // write to the RecentCities file
            writeToRecentCities(recentCitiesArray);
        }
    }

    /**
     * Reads the recent cities from the JSON file and returns the corresponding RecentCityData entity.
     * @return a list of recent city names
     * @throws RecentCitiesDataException if there is an issue reading or parsing the data
     */
    public RecentCityData getCityList() throws RecentCitiesDataException {
        JSONArray recentCitiesArray = readRecentCities();

        // create an array list of the city names
        List<String> cityList = new ArrayList<>(recentCitiesArray.length());
        for (int i = 0; i < recentCitiesArray.length(); i++) {
            cityList.add(recentCitiesArray.getString(i));
        }

        return this.recentCityDataFactory.create(cityList);
    }

    /**
     * Read the RecentCities json file from resource. Return a JSONArray of the data in the file.
     * @return JSONArray containing the recent cities
     * @throws RecentCitiesDataException when the RecentCities json file is not found
     */
    private JSONArray readRecentCities() throws RecentCitiesDataException{
        try (InputStream inputStream = this.getClass().getResourceAsStream(RECENT_CITIES_PATH)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + RECENT_CITIES_PATH);
            }
            // read file data
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder jsonString = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            // convert the recent cities file data to a JSON array
            return new JSONArray(jsonString.toString());
        } catch(IOException exception) {
            throw new RecentCitiesDataException("Filed to load recent city data! " + exception);
        }
    }

    /**
     * Write the recent cities array to the RecentCities JSON file
     * @param recentCitiesArray the array of cities to write to the JSON file
     * @throws RecentCitiesDataException when the RecentCities file cannot be found or written to
     */
    private void writeToRecentCities(JSONArray recentCitiesArray) throws RecentCitiesDataException {
        // open writing object to write to the file
        try (PrintWriter writer = new PrintWriter(RESOURCES_FOLDER_PATH + RECENT_CITIES_PATH)) {
            // write the new json data to the file
            writer.println(recentCitiesArray);
        } catch(IOException exception) {
            throw new RecentCitiesDataException(
                    "Failed to write to file: " + RESOURCES_FOLDER_PATH + RECENT_CITIES_PATH
            );
        }
    }

    /**
     * Check whether a provided city is already in a JSON Array.
     * @param city the city to look for
     * @param recentCitiesArray the array to look for the city in
     * @return true when the city is in the array & false when the city is not in the array
     */
    private boolean cityExists(String city, JSONArray recentCitiesArray) {
        boolean exists = false;
        for (int i = 0; i < recentCitiesArray.length(); i++) {
            String savedCityName = recentCitiesArray.getString(i);
            if (savedCityName.equals(city)) {
                exists = true;
            }
        }
        return exists;
    }

}
