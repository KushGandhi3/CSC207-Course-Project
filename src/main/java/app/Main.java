package app;

// TODO: Implement the Main class [TEAM]

import data_access.weather.open_weather.OpenWeatherGeocodingDAO;

public class Main {
    public static void main(String[] args) {
//        AppBuilder appBuilder = new AppBuilder();
//        JFrame frame = appBuilder.build();
//        frame.setVisible(true);
//        WeatherDAO weatherDAO = new WeatherDAO();
//        weatherDAO.getWeatherDataTest("Toronto");

        OpenWeatherGeocodingDAO.getCoordinates("Toronto");
    }
}
