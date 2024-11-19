package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

import entity.map.MapFactory;
import entity.map.Map;
import use_case.display_map.DisplayMapDataAccessInterface;

public class WeatherMapDataAccessObject implements DisplayMapDataAccessInterface {

    //private static final String TEMPERATURE_LABEL = "temp";
    private static final String LOCATION = "";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String GEOCODING_API_URL = "http://api" +
            ".openweathermap.org/geo/1" +
            ".0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}";
    private final MapFactory mapFactory;

    //http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}


        public WeatherMapDataAccessObject(MapFactory mapFactory) {
            this.mapFactory = mapFactory;
        }

        @Override
        public Map getMap(String city) {

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

}
