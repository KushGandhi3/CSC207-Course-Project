package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data_access.WeatherDataAccessObject;
import entity.Weather;

/**
 * The View for when the user is on the home page, displaying weather information.
 */
public class DisplayHomeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "home page";

    private final JLabel temperatureLabel = new JLabel();
    private final JLabel cityLabel = new JLabel();

    private final WeatherDataAccessObject weatherDataAccessObject;

    public DisplayHomeView(WeatherDataAccessObject weatherDataAccessObject) {
        this.weatherDataAccessObject = weatherDataAccessObject;

        setLayout(new BorderLayout());

        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(cityLabel, BorderLayout.NORTH);
        add(temperatureLabel, BorderLayout.CENTER);

        updateWeatherInfo();
    }

    private void updateWeatherInfo() {
        try {
            Weather weather = weatherDataAccessObject.getWeatherData();
            cityLabel.setText("City: " + weather.getLocation());
            temperatureLabel.setText("Temperature: " + weather.getCurrentTemperature() + "°C");
        } catch (Exception e) {
            temperatureLabel.setText("Failed to get weather data");
            e.printStackTrace();
        }
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Implement property change events for the Display-Home View if needed
    }
}