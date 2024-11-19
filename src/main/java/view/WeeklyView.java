package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.display_weekly.DisplayWeeklyController;
import interface_adapter.display_weekly.DisplayWeeklyViewModel;

import resources.Constants;

/**
 * The view for when the user is in the weekly forecast use-case.
 */
public class WeeklyView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Weekly Forecast";
    private final DisplayWeeklyViewModel displayWeeklyViewModel;
    private DisplayWeeklyController displayWeeklyController;

    // weekday temperatures
    private JLabel[] temperatureForecasts = new JLabel[7];
    // weekday conditions (i.e. sunny, cloudy, rainy)
    private JLabel[] conditionForecasts = new JLabel[7];
    // weekday feels like temperatures
    private JLabel[] feelsLikeForecasts = new JLabel[7];
    // weekday wind speeds
    private JLabel[] windSpeedForecasts = new JLabel[7];
    // weekday precipitation forecasts
    private JLabel[] precipitationForecasts = new JLabel[7];
    // weekday UV indices
    private JLabel[] uvIndexForecasts = new JLabel[7];
    // weekday air quality forecasts
    private JLabel[] airQualityForecasts = new JLabel[7];
    // weekday humidity forecasts
    private JLabel[] humidityForecasts = new JLabel[7];

    private JLabel city;

    private final JButton mondayButton;
    private final JButton tuesdayButton;
    private final JButton wednesdayButton;
    private final JButton thursdayButton;
    private final JButton fridayButton;
    private final JButton saturdayButton;
    private final JButton sundayButton;
    private final JButton homeButton;

    public WeeklyView(DisplayWeeklyViewModel displayWeeklyViewModel) {
        this.displayWeeklyViewModel = displayWeeklyViewModel;
        this.displayWeeklyViewModel.addPropertyChangeListener(this);

        // absolute positioning
        this.setLayout(null);

        Font customFont = new Font("Crimson")

        // city label
        this.city = new JLabel();
        city.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.temperatureForecasts[Constants.MONDAY] = new JLabel("5 C");
        this.conditionForecasts[Constants.MONDAY] = new JLabel("Cloudy");

        mondayButton = new JButton("");
        tuesdayButton = new JButton("");
        wednesdayButton = new JButton("");
        thursdayButton = new JButton("");
        fridayButton = new JButton("");
        saturdayButton = new JButton("");
        sundayButton = new JButton("");
        homeButton = new JButton("");

        // Add the components to the view
        this.add(city);
        this.add(temperatureForecasts[Constants.MONDAY]);
        this.add(conditionForecasts[Constants.MONDAY]);

        this.add(mondayButton);
        this.add(tuesdayButton);
        this.add(wednesdayButton);
        this.add(thursdayButton);
        this.add(fridayButton);
        this.add(saturdayButton);
        this.add(sundayButton);
        this.add(homeButton);

        // Set the bounds of the components
        city.setBounds(10, 10, 100, 20);
        temperatureForecasts[Constants.MONDAY].setBounds(10, 30, 100, 20);
        conditionForecasts[Constants.MONDAY].setBounds(10, 90, 100, 20);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement property change events
    }

}
