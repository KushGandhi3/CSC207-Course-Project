package data_access.weather.open_weather;

import java.io.IOException;
import java.util.Map;

import exception.ApiCallException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class makes API calls to request weather data from OpenWeather.
 */
public class OpenWeatherWeatherDAO {
    // load environment variables file
    private static final Dotenv DOTENV = Dotenv.load();
    // standard, metric, imperial
    private static final String UNITS = "metric";
    // weather data to be excluded from API response (current,minutely,
    // hourly,daily,alerts)
    private static final String EXCLUDE = "current,minutely,alerts";
    private static final String API_KEY = DOTENV.get("OPEN_WEATHER_API_KEY");
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}"
            + "&exclude={part}&appid={API key}&units={units}";

    /**
     * Requests the weather data from the API.
     * @param city the city to request the weather data for
     * @return the weather data for the city in a JSONObject
     * @throws ApiCallException if the request fails or the API Key is not set
     */
    public static JSONObject apiRequest(String city) throws ApiCallException {
        final Map<String, Double> coordinates =
                OpenWeatherGeocodingDAO.getCoordinates(city);

        if (API_KEY == null) {
            throw new ApiCallException("API Key Not Set.");
        }

        final String url = buildUrl(coordinates);

        // build http request
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        // executeDisplayHome request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API Call Unsuccessful.");
            }
            if (response.body() == null) {
                throw new IOException("API Returned No Response.");
            }

            return new JSONObject(response.body().string());

        }
        catch (IOException exception) {
            throw new ApiCallException("Failed To Get Weather For: " + city + ". " + exception.getMessage(), exception);
        }
    }

    /**
     * Builds the API URL for grabbing the weather in the city.
     * @param coordinates the coordinates of the city that weather data
     *                    will be requested for.
     * @return the OpenWeather Weather API URL for requesting
     *         weather for the city.
     */
    private static String buildUrl(Map<String, Double> coordinates) {
        final int latitude = coordinates.get("latitude").intValue();
        final int longitude = coordinates.get("longitude").intValue();

        return API_URL
                .replaceFirst("\\{lat}", String.valueOf(latitude))
                .replaceFirst("\\{lon}", String.valueOf(longitude))
                .replaceFirst("\\{part}", EXCLUDE)
                .replaceFirst("\\{API key}", API_KEY)
                .replaceFirst("\\{units}", UNITS);
    }

}
