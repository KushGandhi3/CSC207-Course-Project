package data_access.weather;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import data_access.geocoding.GeocodingDAO;
import entity.weather.WeatherData;
import entity.weather.WeatherDataFactory;
import exception.APICallException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.display_summarization.DisplaySummarizationDAI;
import use_case.display_hourly.DisplayHourlyDAI;
import use_case.display_home.DisplayHomeDAI;
import use_case.display_daily.DisplayDailyDAI;

import static data_access.geocoding.GeocodingDAO.LIMIT;

/**
 * The DAO for the weather data used by all use cases.
 * TODO: This class is not complete. It also contains numerous checkstyle errors.
 */
public class WeatherDAO implements DisplayHomeDAI,
        DisplaySummarizationDAI,
        DisplayHourlyDAI,
        DisplayDailyDAI {

    // weather data to be excluded from API response (current,minutely,
    // hourly,daily,alerts)
    private static final String EXCLUDE = "minutely,alerts";
    private static final String API_KEY = System.getenv("OPEN_WEATHER_API_KEY");
    private static final String API_URL = "https://api.openweathermap.org/data/" +
            "3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}";
    private final WeatherDataFactory weatherDataFactory;

    public WeatherDAO(WeatherDataFactory weatherDataFactory) {
        this.weatherDataFactory = weatherDataFactory;
    }

    /**
     * Get Daily weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    @Override
    public WeatherData getWeatherData(String location) throws APICallException {

        final Map<String, Double> coordinates =
                GeocodingDAO.getCoordinates(location);

        final String url = buildAPIURL(coordinates);

        // build http request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        // execute request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API call unsuccessful! " + response);
            }
            if (response.body() == null) {
                throw new IOException("API returned no response! Weather data" +
                        " not found!");
            }

            final JSONObject jsonResponse = new JSONObject(response.body().string());

            // Home Screen Weather Data
            final String currentWeatherCondition = jsonResponse.getJSONObject("current")
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");
            final long unixTime = jsonResponse.getJSONObject("current").getLong("dt");
            final double currentTemperature = jsonResponse.getJSONObject("current").getDouble("temp");
            final double highTemperature = jsonResponse.getJSONArray("daily")
                    .getJSONObject(0)
                    .getJSONObject("temp")
                    .getDouble("max");
            final double lowTemperature = jsonResponse.getJSONArray("daily")
                    .getJSONObject(0)
                    .getJSONObject("temp")
                    .getDouble("min");

            // TODO: More weather data to be added here
            return weatherDataFactory.createWeatherData(location, currentWeatherCondition, unixTime, currentTemperature,
                    highTemperature, lowTemperature);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            throw new APICallException("Failed to get weather data for " + location + "!", exception);
        }
    }

    /**
     * Builds the API URL for grabbing the weather in the city.
     * @param coordinates the coordinates of the city that weather data
     *                    will be requested for.
     * @return the OpenWeather Weather API URL for requesting
     * weather for the city.
     */
    private static String buildAPIURL(Map<String, Double> coordinates) {
        String latitude = String.valueOf(coordinates.get("latitude"));
        String longitude = String.valueOf(coordinates.get("longitude"));

        return API_URL.replaceFirst("\\{lat}", latitude)
                .replaceFirst("\\{lon}", longitude)
                .replaceFirst("\\{part}", EXCLUDE)
                .replaceFirst("\\{API key}", API_KEY);
    }

}
