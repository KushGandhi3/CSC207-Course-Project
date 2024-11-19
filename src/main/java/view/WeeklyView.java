package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private final JLabel[] temperatureForecasts;
    // weekday conditions (i.e. sunny, cloudy, rainy)
    private final JLabel[] conditionForecasts;
    // weekday feels like temperatures
    private final JLabel[] feelsLikeForecasts;
    // weekday wind speeds
    private final JLabel[] windSpeedForecasts;
    // weekday precipitation forecasts
    private final JLabel[] precipitationForecasts;
    // weekday UV indices
    private final JLabel[] uvIndexForecasts;
    // weekday air quality forecasts
    private final JLabel[] airQualityForecasts;
    // weekday humidity forecasts
    private final JLabel[] humidityForecasts;

    private final JLabel city;
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

        // Set the layout
        this.setLayout(null); // Absolute positioning

        // Create the city label
        this.city = new JLabel("Toronto");

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
