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

/**
 * The DAO for the weather data used by all use cases.
 * TODO: This class is not complete. It also contains numerous checkstyle errors.
 */
public class WeatherDAO implements DisplayHomeDAI,
        DisplaySummarizationDAI,
        DisplayHourlyDAI,
        DisplayDailyDAI {

    private static final String API_KEY = "";
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall";
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

        final Map<String, Double> coordinates = GeocodingDAO.getCoordinates(location);

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(API_URL + "?lat=" + coordinates.get("lat") + "&lon=" + coordinates.get("long")
                        + "&units=metric&exclude=minutely,hourly,daily,alerts&appid=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new APICallException("API call unsuccessful! " + response);
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
            throw new APICallException("Failed to get daily weather data", exception);
        }
    }
}
