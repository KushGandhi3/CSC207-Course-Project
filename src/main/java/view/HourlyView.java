package view;

import interface_adapter.display_hourly.DisplayHourlyController;
import interface_adapter.display_hourly.DisplayHourlyViewModel;

import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;

/**
 * View for displaying hourly weather data.
 * Updates dynamically based on the ViewModel's state.
 */
public class HourlyView extends JPanel implements PropertyChangeListener {

    // View Name
    private final String viewName = "Hourly Forecast";

    // View Model
    private final DisplayHourlyViewModel displayHourlyViewModel;
    private DisplayHourlyController displayHourlyController;

    // City Labels
    private final JLabel city;
    private final JLabel lowTemperature;
    private final JLabel highTemperature;
    private final JLabel time;
    private final JLabel forecast;
    private final JLabel details;

    // Weather Labels
    private final JLabel[] condition = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] temperature = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] feelsLike = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] windSpeed = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] precipitation = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] uvIndex = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] cloudCover = new JLabel[Constants.TIME_SIZE];
    private final JLabel[] humidity = new JLabel[Constants.TIME_SIZE];

    // Buttons
    private final JButton hourOne;
    private final JButton hourTwo;
    private final JButton hourThree;
    private final JButton hourFour;
    private final JButton hourFive;
    private final JButton hourSix;
    private final JButton hourSeven;
    private final JButton hourEight;
    private final JButton homeButton;

    /**
     * Constructs an HourlyView with a specific ViewModel.
     *
     * @param displayHourlyViewModel the ViewModel to bind to this view
     */
    public HourlyView(DisplayHourlyViewModel displayHourlyViewModel) {
        this.displayHourlyViewModel = displayHourlyViewModel;
        this.displayHourlyViewModel.addPropertyChangeListener(this);

        // Set Layout
        this.setLayout(null);

        // Create City Labels
        this.city = new JLabel("Toronto");
        this.lowTemperature = new JLabel("Low: 17 C");
        this.highTemperature = new JLabel("High: 23 C");
        this.time = new JLabel("9:00");
        this.forecast = new JLabel("Today's Forecast");
        this.details = new JLabel("Weather Details");

        // Create Weather Labels
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.condition[i] = new JLabel("Condition: Sunny");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.temperature[i] = new JLabel("Temperature: 18 C");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.feelsLike[i] = new JLabel("Feels Like: 20 C");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.windSpeed[i] = new JLabel("Wind Speed: 8 km/h SE");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.precipitation[i] = new JLabel("Precipitation: 0''");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.uvIndex[i] = new JLabel("UV Index: 2");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.cloudCover[i] = new JLabel("Air Quality: 96");
        }
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.humidity[i] = new JLabel("Humidity: 76%");
        }

        // Create Buttons
        this.hourOne = new JButton("");
        this.hourTwo = new JButton("");
        this.hourThree = new JButton("");
        this.hourFour = new JButton("");
        this.hourFive = new JButton("");
        this.hourSix = new JButton("");
        this.hourSeven = new JButton("");
        this.hourEight = new JButton("");
        this.homeButton = new JButton("Home");

        // Add Components
        this.add(city);
        this.add(lowTemperature);
        this.add(highTemperature);
        this.add(forecast);
        this.add(details);

        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            this.add(condition[i]);
            this.add(temperature[i]);
            this.add(feelsLike[i]);
            this.add(windSpeed[i]);
            this.add(uvIndex[i]);
            this.add(cloudCover[i]);
            this.add(humidity[i]);
        }

        this.add(hourOne);
        this.add(hourTwo);
        this.add(hourThree);
        this.add(hourFour);
        this.add(hourFive);
        this.add(hourSix);
        this.add(hourSeven);
        this.add(hourEight);
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
