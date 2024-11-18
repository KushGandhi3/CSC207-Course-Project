package interface_adapter.display_map;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * WeatherDataService is responsible for interacting with the OpenWeatherMap API
 * to retrieve current weather data for a given city.
 * The data includes details like temperature, wind speed, pressure, precipitation, and clouds.
 */
public class WeatherDataService {

    private static final String API_KEY = "your_openweathermap_api_key"; // Replace with your actual API key
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    /**
     * Fetches weather data from OpenWeatherMap API for a given city.
     *
     * @param city The name of the city to get the weather data for.
     * @return A JSONObject containing the weather data for the specified city, or null if an error occurs.
     */
    public static JSONObject getWeatherData(String city) {
        try {
            String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"; // Metric for Celsius
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
