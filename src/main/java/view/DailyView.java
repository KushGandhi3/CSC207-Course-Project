package view;

import interface_adapter.display_daily.DisplayDailyViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DailyView extends JPanel implements PropertyChangeListener {

    // View Name
    private final String viewName = "Daily Forecast";

    // View Model
    private final DisplayDailyViewModel displayDailyViewModel;

    // City Labels
    private JLabel cityDaily; // TODO: Change to input field
    private JLabel lowTemperatureDaily;
    private JLabel highTemperatureDaily;
    private JLabel forecastDaily;
    private JLabel detailsDaily;

    // Weather Labels
    private JLabel[] timeDaily = new JLabel[8];
    private JLabel[] conditionDaily = new JLabel[8];
    private JLabel[] temperatureDaily = new JLabel[8];
    private JLabel[] feelsLikeDaily = new JLabel[8];
    private JLabel[] windSpeedDaily = new JLabel[8];
    private JLabel[] precipitationDaily = new JLabel[8];
    private JLabel[] uvIndexDaily = new JLabel[8];
    private JLabel[] airQualityDaily = new JLabel[8];
    private JLabel[] humidityDaily = new JLabel[8];

    // Buttons
    private JButton homeButton;

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;

        // Set Layout
        this.setLayout(null);

        // Create City Labels
        this.cityDaily = new JLabel("Toronto");
        this.lowTemperatureDaily = new JLabel("Low: 17 C");
        this.highTemperatureDaily = new JLabel("High: 23 C");
        this.forecastDaily = new JLabel("Today's Forecast");
        this.detailsDaily = new JLabel("Weather Details");

        // Create Weather Labels
        for (int i = 0; i < 8; i++) {
            this.timeDaily[i] = new JLabel("Time: 09:00");
        }
        for (int i = 0; i < 8; i++) {
            this.conditionDaily[i] = new JLabel("Condition: Sunny");
        }
        for (int i = 0; i < 8; i++) {
            this.temperatureDaily[i] = new JLabel("Temperature: 18 C");
        }
        for (int i = 0; i < 8; i++) {
            this.feelsLikeDaily[i] = new JLabel("Feels Like: 20 C");
        }
        for (int i = 0; i < 8; i++) {
            this.windSpeedDaily[i] = new JLabel("Wind Speed: 8 km/h SE");
        }
        for (int i = 0; i < 8; i++) {
            this.precipitationDaily[i] = new JLabel("Precipitation: 0''");
        }
        for (int i = 0; i < 8; i++) {
            this.uvIndexDaily[i] = new JLabel("UV Index: 2");
        }
        for (int i = 0; i < 8; i++) {
            this.airQualityDaily[i] = new JLabel("Air Quality: 96");
        }
        for (int i = 0; i < 8; i++) {
            this.humidityDaily[i] = new JLabel("Humidity: 76%");
        }

        // Create Buttons
        this.homeButton = new JButton("Home");

        // Add Components
        this.add(cityDaily);
        this.add(lowTemperatureDaily);
        this.add(highTemperatureDaily);
        this.add(forecastDaily);
        this.add(detailsDaily);
        for (int i = 0; i < 8; i++) {
            this.add(timeDaily[i]);
            this.add(conditionDaily[i]);
            this.add(temperatureDaily[i]);
            this.add(feelsLikeDaily[i]);
            this.add(windSpeedDaily[i]);
            this.add(uvIndexDaily[i]);
            this.add(airQualityDaily[i]);
            this.add(humidityDaily[i]);
        }
        this.add(homeButton);

        // Set Bounds
        cityDaily.setBounds(10, 10, 100, 20);
        lowTemperatureDaily.setBounds(10, 40, 100, 20);
        highTemperatureDaily.setBounds(10, 80, 100, 20);
        forecastDaily.setBounds(10, 120, 100, 20);
        detailsDaily.setBounds(10, 160, 100, 20);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement method
    }
}
