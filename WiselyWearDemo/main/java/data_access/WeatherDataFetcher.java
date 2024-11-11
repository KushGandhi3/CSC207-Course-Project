package java.data_access;

import use_case.display_home.DisplayHomeWeatherDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherDataFetcher implements DisplayHomeWeatherDataAccessInterface {
    private static final String API_KEY = ""; // ADD API KEY HERE;
    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall";

    public static void main(String[] args) {
        double latitude = 100;
        double longitude = 100;
        String exclude = "minutely,hourly,daily,alerts";

        try {
            String weatherData = getWeatherData(latitude, longitude, exclude);
            System.out.println(weatherData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getWeatherData(double latitude, double longitude, String exclude) throws Exception {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&exclude=" + exclude + "&appid=" + API_KEY;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuilder content = new StringBuilder();
            while ((inputline = rd.readLine()) != null) {
                content.append(inputline);
            }
            rd.close();
            return content.toString();
        }
        else {
            throw new RuntimeException("Failed to get weather data");
        }
    }
}
