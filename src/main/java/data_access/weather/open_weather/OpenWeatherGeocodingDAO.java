package data_access.weather.open_weather;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import exception.ApiCallException;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Finds geo-coordinates of a city given a String name using the OpenWeather
 * Geocoding API.
 */
public class OpenWeatherGeocodingDAO {

    // limit indicates the number of matching queries that will be returned by
    // the API
    private static final Dotenv DOTENV = Dotenv.load();
    private static final Integer LIMIT = 1;
    private static final String GEOCODING_API_URL = "http://api"
            + ".openweathermap.org/geo/1"
            + ".0/direct?q={city name}&limit={limit}&appid={API key}";
    private static final String API_KEY = DOTENV.get("OPEN_WEATHER_API_KEY");

    /**
     * Get the geo-coordinates of a city using the OpenWeather Geocoding API.
     * @param city the name of the city
     * @return coordinates of the city
     * @throws ApiCallException if API call fails.
     */
    public static Map<String, Double> getCoordinates(String city) throws ApiCallException {
        final String url = buildUrl(city);

        // Build http request
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        // Execute request
        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("API Call Unsuccessful.");
            }
            if (response.body() == null) {
                throw new IOException("API Returned No Response.");
            }

            final JSONArray responseBody = new JSONArray(response.body().string());
            if (responseBody.isEmpty()) {
                throw new IOException("API Returned Empty Response.");
            }
            // grab the top result
            final JSONObject firstResult = responseBody.getJSONObject(0);

            final double latitude = firstResult.getDouble("lat");
            final double longitude = firstResult.getDouble("lon");

            final Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("latitude", latitude);
            coordinates.put("longitude", longitude);

            return coordinates;

        }
        catch (IOException exception) {
            throw new ApiCallException("Failed To Get Geo-Coordinates For " + city + ". " + exception.getMessage(),
                    exception);
        }
    }

    /**
     * Builds the API URL for grabbing the geo-coordinates of the city.
     * @param city the city that geo-coordinates will be requested for.
     * @return the OpenWeather Geocoding API URL for requesting
     */
    private static String buildUrl(String city) {
        return GEOCODING_API_URL
                .replaceFirst("\\{city name}", city)
                .replaceFirst("\\{limit}", LIMIT.toString())
                .replaceFirst("\\{API key}", API_KEY);
    }
}
