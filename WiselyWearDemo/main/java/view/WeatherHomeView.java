package view;

import javax.swing.*;
import java.awt.*;
import data_access.WeatherDataAccessObject;
import entity.CommonWeatherFactory;
import entity.Weather;

/**
 * The View for when the user is on the home page, displaying weather information.
 */
public class WeatherHomeView extends JPanel {
    private final JLabel temperatureLabel;
    private final JLabel cityLabel;
    private final WeatherDataAccessObject weatherDataAccessObject;

    public WeatherHomeView() {
        CommonWeatherFactory weatherFactory = new CommonWeatherFactory();
        this.weatherDataAccessObject = new WeatherDataAccessObject(weatherFactory);

        setLayout(new BorderLayout());

        cityLabel = new JLabel("City: ", SwingConstants.CENTER);
        temperatureLabel = new JLabel("Temperature: ", SwingConstants.CENTER);

        add(cityLabel, BorderLayout.NORTH);
        add(temperatureLabel, BorderLayout.CENTER);

        updateWeatherInfo();
    }

    private void updateWeatherInfo() {
        try {
            Weather weather = weatherDataAccessObject.getWeatherData();
            cityLabel.setText("City: " + weather.getLocation());
            temperatureLabel.setText("Temperature: " + weather.getCurrentTemperature() + "K");
        } catch (Exception e) {
            temperatureLabel.setText("Failed to get weather data");
            e.printStackTrace();
        }
    }
}