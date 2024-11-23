package view;

import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyViewModel;
import constants.Constants;

/**
 * The view for when the user is in the weekly forecast use-case.
 */
public class DailyView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Weekly Forecast";
    private final DisplayDailyViewModel displayDailyViewModel;
    private DisplayDailyController displayDailyController;

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

    // days of the week
    private final JButton dayOne;
    private final JButton dayTwo;
    private final JButton dayThree;
    private final JButton dayFour;
    private final JButton dayFive;
    private final JButton daySix;
    private final JButton daySeven;

    private final JButton homeButton;

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayDailyViewModel.addPropertyChangeListener(this);

        // absolute positioning
        this.setLayout(null);

        // city label
        this.city = new JLabel();
        Font crimsonText =
                FontManager.getCrimsonText(Constants.TITLE_FONT_SIZE);
        this.city.setFont(crimsonText);
        this.city.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.city.setAlignmentY(55);

        //
        this.dayOne = new JButton("");


        dayTwo = new JButton();
        dayThree = new JButton("");
        dayFour = new JButton("");
        dayFive = new JButton("");
        daySix = new JButton("");
        daySeven = new JButton("");
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

    private void setFields(DisplayDailyState state) {
        usernameInputField.setText(state.get());
        passwordInputField.setText(state.getPassword());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement property change events
    }

}
