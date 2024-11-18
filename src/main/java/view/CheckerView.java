package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.checker.CheckerController;
import interface_adapter.checker.CheckerState;
import interface_adapter.checker.CheckerViewModel;

import javax.swing.*;
import java.awt.*;

public class CheckerView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Checker";
    private final CheckerViewModel checkerViewModel;
    private final CheckerController controller;

    private final JTextField locationField = new JTextField(10);
    // private final JComboBox<String> tempOptions = new JComboBox<>(CheckerViewModel.TEMPERATURE_OPTIONS);
    // private final JTextField tempField = new JTextField(5);
    private final JComboBox<String> weatherConditionOptions = new JComboBox<>(CheckerViewModel.WEATHER_CONDITION_OPTIONS);
    private final JComboBox<Integer> startCheckingOptions = new JComboBox<>(CheckerViewModel.START_CHECKING_OPTIONS);
    private final JComboBox<Integer> stopCheckingOptions = new JComboBox<>(CheckerViewModel.STOP_CHECKING_OPTIONS);

    private final JButton checkButton = new JButton(CheckerViewModel.CHECK_BUTTON_LABEL);
    private final JButton cancelButton = new JButton(CheckerViewModel.CANCEL_BUTTON_LABEL);

    public CheckerView(CheckerViewModel checkerViewModel) {
        this.checkerViewModel = checkerViewModel;
        this.checkerViewModel.addPropertyChangeListener(this);

        // title label "Weather Checker"
        final JLabel titleLabel = new JLabel(CheckerViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // location label
        final JLabel locationLabel = new JLabel(CheckerViewModel.LOCATION_LABEL);

        // location panel
        final JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        locationPanel.add(locationLabel);
        locationPanel.add(locationField);

        /*
        // temperature label and unit
        final JLabel tempLabel = new JLabel(CheckerViewModel.TEMPERATURE_LABEL);
        final JLabel tempUnit = new JLabel(CheckerViewModel.TEMPERATURE_UNIT_LABEL);

        // temperature panel
        final JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        tempPanel.add(tempLabel);
        tempPanel.add(tempOptions);
        tempPanel.add(tempField);
        tempPanel.add(tempUnit);
         */

        // weather condition label
        final JLabel weatherConditionLabel = new JLabel("Weather Condition");

        // weather condition panel
        final JPanel weatherConditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        weatherConditionPanel.add(weatherConditionLabel);
        weatherConditionPanel.add(weatherConditionOptions);

        // start checking label and description
        final JLabel startCheckingLabel = new JLabel(CheckerViewModel.START_CHECKING_LABEL);
        final JLabel startCheckingDescription = new JLabel(CheckerViewModel.START_CHECKING_DESCRIPTION);

        // start checking panel
        final JPanel startCheckingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        startCheckingPanel.add(startCheckingLabel);
        startCheckingPanel.add(startCheckingOptions);
        startCheckingPanel.add(startCheckingDescription);

        // stop checking label and description
        final JLabel stopCheckingLabel = new JLabel(CheckerViewModel.STOP_CHECKING_LABEL);
        final JLabel stopCheckingDescription = new JLabel(CheckerViewModel.STOP_CHECKING_DESCRIPTION);

        // stop checking panel
        final JPanel stopCheckingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        stopCheckingPanel.add(stopCheckingLabel);
        stopCheckingPanel.add(stopCheckingOptions);
        stopCheckingPanel.add(stopCheckingDescription);

        // button panel
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(checkButton);
        buttonPanel.add(cancelButton);

        // set the fields and combo boxes to the default value
        locationField.setText(checkerViewModel.getState().getLocation());
        // tempOptions.setSelectedIndex(0);
        // tempField.setText(String.valueOf(checkerViewModel.getState().getTempValue()));
        weatherConditionOptions.setSelectedIndex(0);
        startCheckingOptions.setSelectedIndex(0);
        stopCheckingOptions.setSelectedIndex(0);

        // add action listeners to the buttons

        checkButton.addActionListener(
                // TODO add action listener
        );

        cancelButton.addActionListener(
                // TODO add action listener
        );

        addLocationListener();
        // addTempOptionsListener();
        // addTempValueListener();
        addWeatherConditionListener();
        addStartCheckingListener();
        addStopCheckingListener();

        // main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);
        this.add(locationPanel);
        // this.add(tempPanel);
        this.add(weatherConditionPanel);
        this.add(startCheckingPanel);
        this.add(stopCheckingPanel);
        this.add(buttonPanel);
    }

    private void addLocationListener() {
        // TODO add location listener
    }

    /*
    private void addTempOptionsListener() {
        // add temp options listener
    }

    private void addTempValueListener() {
        // add temp value listener
    }
    */

    private void addWeatherConditionListener() {
        // TODO add weather condition listener
    }

    private void addStartCheckingListener() {
        // TODO add start checking listener
    }

    private void addStopCheckingListener() {
        // TODO add stop checking listener
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement actionPerformed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO implement propertyChange
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(CheckerController controller) {
        this.controller = controller;
    }
}


