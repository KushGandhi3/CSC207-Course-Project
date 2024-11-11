package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import data_access.WeatherDataAccessObject;
import entity.CommonWeatherFactory;
import entity.Weather;
import java.io.IOException;

/**
 * The View for when the user is on the home page, displaying weather information.
 */
public class WeatherHomeView extends JPanel {

    private final JTextField cityInputField = new JTextField(15);
    private final JLabel cityLabel = new JLabel("City:");
    private final JLabel temperatureLabel = new JLabel("Temperature: -- °C");
    private final JLabel locationLabel = new JLabel("Location: --");
    private final JButton fetchWeatherButton = new JButton("Get Weather");

    private final WeatherDataAccessObject weatherDataAccessObject;

    public WeatherHomeView() {
        CommonWeatherFactory weatherFactory = new CommonWeatherFactory();
        weatherDataAccessObject = new WeatherDataAccessObject(weatherFactory);

        setupLayout();
        setupActions();
    }

    private void setupLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title);

        JPanel cityPanel = new JPanel();
        cityPanel.add(cityLabel);
        cityPanel.add(cityInputField);
        this.add(cityPanel);

        this.add(temperatureLabel);
        this.add(locationLabel);

        fetchWeatherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(fetchWeatherButton);
    }

    private void setupActions() {
        fetchWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayWeather();
            }
        });
    }

    private void fetchAndDisplayWeather() {
        try {
            double latitude = 43.7; // Toronto
            double longitude = -79.42;
            String exclude = "minutely,hourly,daily";

            Weather weather = weatherDataAccessObject.getWeatherData(latitude, longitude, exclude);

            temperatureLabel.setText("Temperature: " + weather.getCurrentTemperature() + " °C");
            locationLabel.setText("Location: " + weather.getLocation());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching weather data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}