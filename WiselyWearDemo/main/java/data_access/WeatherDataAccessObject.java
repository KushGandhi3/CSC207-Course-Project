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

    private static final String TEMPERATURE_LABEL = "temp";
    private static final String LOCATION = "TORONTO";
    private static final double TORONTO_LATITUDE = 43.7;
    private static final double TORONTO_LONGITUDE = -79.42;
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall";
    private final WeatherFactory weatherFactory;

    public WeatherDataAccessObject(WeatherFactory weatherFactory) {
        this.weatherFactory = weatherFactory;
    }

    @Override
    public Weather getWeatherData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "?lat=" + TORONTO_LATITUDE + "&lon=" + TORONTO_LONGITUDE + "&units=metric&exclude=minutely,hourly,daily,alerts&appid=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            double temperature = responseBody.getJSONObject("current").getDouble(TEMPERATURE_LABEL);
            return weatherFactory.create(temperature, LOCATION);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get weather data", e);
        }
    }
}