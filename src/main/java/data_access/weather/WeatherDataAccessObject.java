package data_access.weather;

import entity.weekly_weather.WeatherData;
import exception.APICallException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.display_weekly.DisplayWeeklyDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The DAO for weather data.
 */
public class WeatherDataAccessObject implements DisplayWeeklyDataAccessInterface {

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

    @Override
    public WeatherData getWeatherData(String city) {
        return null;
    }
}
