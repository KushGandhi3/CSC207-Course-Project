package view;

import interface_adapter.display_home.DisplayHomePresenter;
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

import interface_adapter.display_home.DisplayHomeController;
import interface_adapter.display_home.DisplayHomeState;
import interface_adapter.display_home.DisplayHomeViewModel;

/**
 * The View for the Display Home Use Case.
 */

public class HomeView extends JPanel implements ActionListener,PropertyChangeListener {

    // View Model
    private final DisplayHomeViewModel homeViewModel;

    // View Name
    public final String viewName = "Home";

    // Weather Data Labels
    private final JLabel temperatureLabel;
    private final JLabel highTemperatureLabel;
    private final JLabel lowTemperatureLabel;
    private final JLabel conditionLabel;

    // City Label
    private JLabel cityLabel; // TODO: This needs to be a input field later on

    // Time Label
    private JLabel timeLabel;

    // Buttons
    private final JButton DailyButton;
    private final JButton WeeklyButton;
    private final JButton CheckerButton;
    private final JButton OutfitButton;
    private final JButton MapButton;

    public HomeView(DisplayHomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

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
        cityLabel.setBounds(461, 216, 278, 68);
        temperatureLabel.setBounds(502, 316, 196, 125);
        highTemperatureLabel.setBounds(567, 430, 87, 42);
        lowTemperatureLabel.setBounds(670, 430, 80, 42);
        conditionLabel.setBounds(476, 430, 79, 42);
        timeLabel.setBounds(544, 288, 112, 42);
    }
    // Setup listeners for button clicks

    private void setupListeners() {
        // Listener for the 'Daily' button
        DailyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDailyButtonClicked();
            }
        });

        // Listener for the 'Weekly' button
        WeeklyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onWeeklyButtonClicked();
            }
        });

        // Listener for the 'Checker' button
        CheckerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCheckerButtonClicked();
            }
        });

        // Listener for the 'Outfit' button
        OutfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOutfitButtonClicked();
            }
        });

        // Listener for the 'Map' button
        MapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMapButtonClicked();
            }
        });
    }

    // Action when "Daily" button is clicked
    private void onDailyButtonClicked() {
        DisplayHomePresenter.handleDailyWeatherRequest();
    }

    // Action when "Weekly" button is clicked
    private void onWeeklyButtonClicked() {
        DisplayHomePresenter.handleWeeklyWeatherRequest();
    }

    // Action when "Checker" button is clicked
    private void onCheckerButtonClicked() {
        DisplayHomePresenter.handleWeatherCheckerRequest();
    }

    // Action when "Outfit" button is clicked
    private void onOutfitButtonClicked() {
        DisplayHomePresenter.handleOutfitSuggestionsRequest();
    }

    // Action when "Map" button is clicked
    private void onMapButtonClicked() {
        DisplayHomePresenter.handleMapRequest();
    }

    // Method to update the temperature label
    public void setTemperatureLabel(String text) {
        temperatureLabel.setText(text);
    }

    // Method to update the high temperature label
    public void setHighTemperatureLabel(String text) {
        highTemperatureLabel.setText(text);
    }

    // Method to update the low temperature label
    public void setLowTemperatureLabel(String text) {
        lowTemperatureLabel.setText(text);
    }

    // Method to update the condition label
    public void setConditionLabel(String text) {
        conditionLabel.setText(text);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement property change events

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }
}

