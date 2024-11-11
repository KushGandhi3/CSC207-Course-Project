package java.data_access;

import use_case.display_home.DisplayHomeWeatherDataAccessInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class WeatherDataAccessObject implements DisplayHomeWeatherDataAccessInterface {

    private static final String API_KEY = ""; // ADD API KEY HERE;
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall";

    /**
     * Gets the current temperature.
     *
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param exclude  the data to exclude
     */
    @Override
    public double getWeatherData(double latitude, double longitude, String exclude) throws IOException {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&exclude=" + exclude + "&appid=" + API_KEY;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            StringBuilder content = new StringBuilder();
            while ((input = rd.readLine()) != null) {
                content.append(input);
            }
            rd.close();

            JSONObject jsonResponse = new JSONObject(content.toString());
            return jsonResponse.getJSONObject("current").getDouble("temp");
        }
        else {
            throw new RuntimeException("Failed to get weather data");
        }
    }
}
