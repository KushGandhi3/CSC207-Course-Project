package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import interface_adapter.display_checker.DisplayCheckerViewModel;
import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyViewModel;
import constants.Constants;

/**
 * The view for the daily weather use-case.
 */
public class DailyView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final Font crimsonText20 = FontManager.getCrimsonTextRegular(20);

    private final String viewName = "Weekly Forecast";

    private final DisplayDailyViewModel displayDailyViewModel;
    private DisplayDailyController displayDailyController;

    // data to be displayed
    private final JLabel city;
    private final List<JLabel> weekdays;
    private final List<JLabel> temperatures;
    private final List<JLabel> conditions;
    private final JLabel feelsLikeTemperature;
    private final JLabel uvIndex;
    private final JLabel windSpeed;
    private final JLabel cloudCover;
    private final JLabel precipitation;
    private final JLabel humidity;

    // days of the week listed in order
    private final List<JButton> weekdays;

    private final JButton backButton = new JButton(DisplayCheckerViewModel.BACK_BUTTON_IMAGE);

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayDailyViewModel.addPropertyChangeListener(this);

        // set the layout and border of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and style the back button
        this.backButton.setBorder(BorderFactory.createEmptyBorder());
        this.backButton.setContentAreaFilled(false);
        // Back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);



        final JLabel forecastLabel = new JLabel(DisplayDailyViewModel.FORECAST_LABEL);
        forecastLabel.setFont(crimsonText20);
        forecastLabel.setAlignmentX(143);
        forecastLabel.setAlignmentY(238);



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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
