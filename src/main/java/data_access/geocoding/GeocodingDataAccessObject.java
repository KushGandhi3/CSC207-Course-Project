package data_access.geocoding;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import java.io.IOException;
import exception.APICallException;

/**
 * Finds geo-coordinates of a city given a String name using the OpenWeather
 * Geocoding API.
 */
public class GeocodingDataAccessObject {

    // limit indicates the number of matching queries that will be returned by
    // the API
    private static final Integer LIMIT = 1;
    private static final String GEOCODING_API_URL = "http://api" +
            ".openweathermap.org/geo/1" +
            ".0/direct?q={city name}&limit={limit}&appid={API key}";
    private static final String API_KEY = System.getenv("OPEN_WEATHER_API_KEY");

    /**
     * Get the geo-coordinates of a city using the OpenWeather Geocoding API.
     * @param city the name of the city
     * @return coordinates of the city
     */
    public Map<String, Double> getCoordinates(String city) throws APICallException {
        String url = buildAPIURL(city);

        // Build http request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        // Execute request
        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("API call unsuccessful! " + response);
            }
            if (response.body() == null) {
                throw new IOException("API returned no response!");
            }

            JSONArray responseBody = new JSONArray(response.body().string());
            // grab the top result
            JSONObject firstResult = responseBody.getJSONObject(0);

            double latitude = firstResult.getDouble("lat");
            double longitude = firstResult.getDouble("lon");

            Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("latitude", latitude);
            coordinates.put("longitude", longitude);

            return coordinates;

        } catch (IOException exception) {
            exception.printStackTrace();
            throw new APICallException("Failed to get geo-coordinates for " + city,
                    exception);
        }
    }

    /**
     * Builds the API URL for grabbing the geo-coordinates of the city.
     * @param city the city that geo-coordinates will be requested for.
     * @return the OpenWeather Geocoding API URL for requesting
     * geo-coordinates of the city
     */
    private String buildAPIURL(String city) {
        return GEOCODING_API_URL
                .replaceFirst("\\{city name}", city)
                .replaceFirst("\\{limit}", LIMIT.toString())
                .replaceFirst("\\{API key}", API_KEY);
    }

}
