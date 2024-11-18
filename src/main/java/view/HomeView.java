package view;

import interface_adapter.display_home.DisplayHomeViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HomeView extends JPanel {

    // View Model
    private DisplayHomeViewModel homeViewModel;

    // View Name
    public final String viewName = "Home";

    // Weather Data Labels
    private JLabel temperatureLabel;
    private JLabel highTemperatureLabel;
    private JLabel lowTemperatureLabel;
    private JLabel conditionLabel;

    // City Label
    private JLabel cityLabel; // TODO: This needs to be a input field later on

    // Time Label
    private JLabel timeLabel;

    // Buttons
    private JButton DailyButton;
    private JButton WeeklyButton;
    private JButton CheckerButton;
    private JButton OutfitButton;
    private JButton MapButton;

    public HomeView(DisplayHomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;

        // Set the layout
        this.setLayout(null); // Absolute positioning

        // Create the city label
        cityLabel = new JLabel("Toronto");

        // Create the weather data labels
        temperatureLabel = new JLabel("5 C");
        highTemperatureLabel = new JLabel("High: 10 C");
        lowTemperatureLabel = new JLabel("Low: 0 C");
        conditionLabel = new JLabel("Cloudy");

        // Create the time label
        timeLabel = new JLabel("12:00 PM");

        // Create the buttons
        DailyButton = new JButton("Daily");
        WeeklyButton = new JButton("Weekly");
        CheckerButton = new JButton("Checker");
        OutfitButton = new JButton("Outfit");
        MapButton = new JButton("Map");

        // Add the components to the view
        this.add(cityLabel);
        this.add(temperatureLabel);
        this.add(highTemperatureLabel);
        this.add(lowTemperatureLabel);
        this.add(conditionLabel);
        this.add(timeLabel);

        this.add(DailyButton);
        this.add(WeeklyButton);
        this.add(CheckerButton);
        this.add(OutfitButton);
        this.add(MapButton);

        // Set the bounds of the components
        cityLabel.setBounds(10, 10, 100, 20);
        temperatureLabel.setBounds(10, 30, 100, 20);
        highTemperatureLabel.setBounds(10, 50, 100, 20);
        lowTemperatureLabel.setBounds(10, 70, 100, 20);
        conditionLabel.setBounds(10, 90, 100, 20);
        timeLabel.setBounds(10, 110, 100, 20);
    }
}
