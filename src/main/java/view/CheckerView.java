package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.FlowLayout;
import java.util.Objects;

import interface_adapter.display_checker.DisplayCheckerController;
import interface_adapter.display_checker.DisplayCheckerState;
import interface_adapter.display_checker.DisplayCheckerViewModel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


public class CheckerView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Checker";
    private final DisplayCheckerViewModel displayCheckerViewModel;
    private DisplayCheckerController displayCheckerController;

    private final JTextField locationField = new JTextField(10);
    // private final JComboBox<String> tempOptions = new JComboBox<>(CheckerViewModel.TEMPERATURE_OPTIONS);
    // private final JTextField tempField = new JTextField(5);
    private final JComboBox<String> weatherConditionOptions = new JComboBox<>(DisplayCheckerViewModel.WEATHER_CONDITION_OPTIONS);
    private final JComboBox<Integer> startCheckingOptions = new JComboBox<>(DisplayCheckerViewModel.START_CHECKING_OPTIONS);
    private final JComboBox<Integer> stopCheckingOptions = new JComboBox<>(DisplayCheckerViewModel.STOP_CHECKING_OPTIONS);

    private final JButton checkButton = new JButton(DisplayCheckerViewModel.CHECK_BUTTON_LABEL);
    private final JButton cancelButton = new JButton(DisplayCheckerViewModel.CANCEL_BUTTON_LABEL);

    public CheckerView(DisplayCheckerViewModel displayCheckerViewModel) {
        this.displayCheckerViewModel = displayCheckerViewModel;
        this.displayCheckerViewModel.addPropertyChangeListener(this);

        // title label "Weather Checker"
        final JLabel titleLabel = new JLabel(DisplayCheckerViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // location label
        final JLabel locationLabel = new JLabel(DisplayCheckerViewModel.LOCATION_LABEL);

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
        final JLabel startCheckingLabel = new JLabel(DisplayCheckerViewModel.START_CHECKING_LABEL);
        final JLabel startCheckingDescription = new JLabel(DisplayCheckerViewModel.START_CHECKING_DESCRIPTION);

        // start checking panel
        final JPanel startCheckingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        startCheckingPanel.add(startCheckingLabel);
        startCheckingPanel.add(startCheckingOptions);
        startCheckingPanel.add(startCheckingDescription);

        // stop checking label and description
        final JLabel stopCheckingLabel = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_LABEL);
        final JLabel stopCheckingDescription = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_DESCRIPTION);

        // stop checking panel
        final JPanel stopCheckingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        stopCheckingPanel.add(stopCheckingLabel);
        stopCheckingPanel.add(stopCheckingOptions);
        stopCheckingPanel.add(stopCheckingDescription);

        // button panel
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(checkButton);
        buttonPanel.add(cancelButton);

        // setting default value
        DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setLocation("Toronto");
        displayCheckerState.setWeatherConditionOptions("Clear");
        displayCheckerState.setStartChecking(0);
        displayCheckerState.setStopChecking(1);
        displayCheckerViewModel.setState(displayCheckerState);

        // add action listeners to the check button
        checkButton.addActionListener(
                // this creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkButton)) {
                            final DisplayCheckerState currentState = displayCheckerViewModel.getState();

                            displayCheckerController.execute(
                                    currentState.getLocation(),
                                    currentState.getWeatherConditionOptions(),
                                    currentState.getStartChecking(),
                                    currentState.getStopChecking()
                            );

                        }

                    }
                }
        );

        // add action listeners to the cancel button
        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            displayCheckerController.switchToHomeView();
                        }
                    }
                }
        );

        // add document listeners to the location field
        locationField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                currentState.setLocation(locationField.getText());
                displayCheckerViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

        }
        );

        // add action listeners to the temperature options
        weatherConditionOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                        String selectedWeatherCondition = (String) weatherConditionOptions.getSelectedItem();
                        currentState.setWeatherConditionOptions(selectedWeatherCondition);
                        displayCheckerViewModel.setState(currentState);
                    }
                }
        );

        // add action listeners to the start and stop checking options
        startCheckingOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                        int selectedStartChecking = (int) startCheckingOptions.getSelectedItem();
                        currentState.setStartChecking(selectedStartChecking);
                        displayCheckerViewModel.setState(currentState);
                    }
                }
        );

        // add action listeners to the stop checking options
        stopCheckingOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                        int selectedStopChecking = (int) stopCheckingOptions.getSelectedItem();
                        currentState.setStopChecking(selectedStopChecking);
                        displayCheckerViewModel.setState(currentState);
                    }
                }
        );

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

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Check this method
        // get the new value from the event
        final DisplayCheckerState state = (DisplayCheckerState) evt.getNewValue();
        // check the weather condition results (condition met or not)
        String isWeatherConditionMet = state.getMessage();

        // display the message based on the weather condition results
        if (Objects.equals(isWeatherConditionMet, "exist")) {
            JOptionPane.showMessageDialog(this, "Weather condition is met.");
            state.setMessage(null);
        } else if (Objects.equals(isWeatherConditionMet, "nonexist")) {
            JOptionPane.showMessageDialog(this, "Weather condition is not met.");
            state.setMessage(null);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setCheckerController(DisplayCheckerController displayCheckerController) {
        this.displayCheckerController = displayCheckerController;
    }
}


