package view;

import interface_adapter.display_hourly.DisplayHourlyViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HourlyView extends JPanel implements PropertyChangeListener {

    // View Name
    private final String viewName = "Hourly Forecast";

    // View Model
    private final DisplayHourlyViewModel displayHourlyViewModel;

    // City Labels
    private JLabel city; // TODO: Change to input field
    private JLabel lowTemperature;
    private JLabel highTemperature;
    private JLabel forecast;
    private JLabel details;

    // Weather Labels
    private JLabel[] time = new JLabel[8];
    private JLabel[] condition = new JLabel[8];
    private JLabel[] temperature = new JLabel[8];
    private JLabel[] feelsLike = new JLabel[8];
    private JLabel[] windSpeed = new JLabel[8];
    private JLabel[] precipitation = new JLabel[8];
    private JLabel[] uvIndex = new JLabel[8];
    private JLabel[] airQuality = new JLabel[8];
    private JLabel[] humidity = new JLabel[8];

    // Buttons
    private JButton homeButton;

    public HourlyView(DisplayHourlyViewModel displayHourlyViewModel) {
        this.displayHourlyViewModel = displayHourlyViewModel;

        // Set Layout
        this.setLayout(null);

        // Create City Labels
        this.city = new JLabel("Toronto");
        this.lowTemperature = new JLabel("Low: 17 C");
        this.highTemperature = new JLabel("High: 23 C");
        this.forecast = new JLabel("Today's Forecast");
        this.details = new JLabel("Weather Details");

        // Create Weather Labels
        for (int i = 0; i < 8; i++) {
            this.time[i] = new JLabel("Time: 09:00");
        }
        for (int i = 0; i < 8; i++) {
            this.condition[i] = new JLabel("Condition: Sunny");
        }
        for (int i = 0; i < 8; i++) {
            this.temperature[i] = new JLabel("Temperature: 18 C");
        }
        for (int i = 0; i < 8; i++) {
            this.feelsLike[i] = new JLabel("Feels Like: 20 C");
        }
        for (int i = 0; i < 8; i++) {
            this.windSpeed[i] = new JLabel("Wind Speed: 8 km/h SE");
        }
        for (int i = 0; i < 8; i++) {
            this.precipitation[i] = new JLabel("Precipitation: 0''");
        }
        for (int i = 0; i < 8; i++) {
            this.uvIndex[i] = new JLabel("UV Index: 2");
        }
        for (int i = 0; i < 8; i++) {
            this.airQuality[i] = new JLabel("Air Quality: 96");
        }
        for (int i = 0; i < 8; i++) {
            this.humidity[i] = new JLabel("Humidity: 76%");
        }

        // Create Buttons
        this.homeButton = new JButton("Home");

        // Add Components
        this.add(city);
        this.add(lowTemperature);
        this.add(highTemperature);
        this.add(forecast);
        this.add(details);
        for (int i = 0; i < 8; i++) {
            this.add(time[i]);
            this.add(condition[i]);
            this.add(temperature[i]);
            this.add(feelsLike[i]);
            this.add(windSpeed[i]);
            this.add(uvIndex[i]);
            this.add(airQuality[i]);
            this.add(humidity[i]);
        }
        this.add(homeButton);

        // Set Bounds
        city.setBounds(10, 10, 100, 20);
        lowTemperature.setBounds(10, 40, 100, 20);
        highTemperature.setBounds(10, 80, 100, 20);
        forecast.setBounds(10, 120, 100, 20);
        details.setBounds(10, 160, 100, 20);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement method
    }
}
