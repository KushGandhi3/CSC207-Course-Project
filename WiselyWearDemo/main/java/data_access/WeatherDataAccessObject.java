package data_access;

import entity.Weather;
import entity.WeatherFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import use_case.display_home.DisplayHomeWeatherDataAccessInterface;
import java.io.IOException;

public class WeatherDataAccessObject implements DisplayHomeWeatherDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String TEMPERATURE_LABEL = "temperature";
    private static final String LOCATION = "TORONTO";
    private static final String API_KEY = "";
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall";
    private final WeatherFactory weatherFactory;

    public WeatherDataAccessObject(WeatherFactory weatherFactory) {
        this.weatherFactory = weatherFactory;
    }

    /**
     * Gets the current temperature.
     *
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param exclude  the data to exclude
     */
    @Override
    public Weather getWeatherData(double latitude, double longitude, String exclude){

        // Make an API call to get the weather data
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(API_URL + "?lat=" + latitude + "&lon=" + longitude + "&exclude=" + exclude + "&appid=" + API_KEY)
                .method("GET", null)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return weatherFactory.create(responseBody.getDouble(TEMPERATURE_LABEL), LOCATION);
            } else {
                throw new RuntimeException("Failed to get weather data");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
