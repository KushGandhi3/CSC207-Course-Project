package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.FlowLayout;

import interface_adapter.checker.CheckerController;
import interface_adapter.checker.CheckerState;
import interface_adapter.checker.CheckerViewModel;

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
    private final CheckerViewModel checkerViewModel;
    private CheckerController checkerController;

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

        // setting default value
        CheckerState checkerState = checkerViewModel.getState();
        checkerState.setLocation("Toronto");
        checkerState.setWeatherConditionOptions("Clear");
        checkerState.setStartChecking(0);
        checkerState.setStopChecking(1);
        checkerViewModel.setState(checkerState);

        // add action listeners to the buttons
        checkButton.addActionListener(
                // this creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkButton)) {
                            final CheckerState currentState = checkerViewModel.getState();

                            checkerController.execute(
                                    currentState.getLocation(),
                                    currentState.getWeatherConditionOptions(),
                                    currentState.getStartChecking(),
                                    currentState.getStopChecking()
                            );

                        }

                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            checkerController.switchToHomeView();
                        }
                    }
                }
        );

        locationField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CheckerState currentState = checkerViewModel.getState();
                currentState.setLocation(locationField.getText());
                checkerViewModel.setState(currentState);
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

        weatherConditionOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final CheckerState currentState = checkerViewModel.getState();
                        String selectedWeatherCondition = (String) weatherConditionOptions.getSelectedItem();
                        currentState.setWeatherConditionOptions(selectedWeatherCondition);
                        checkerViewModel.setState(currentState);
                    }
                }
        );

        startCheckingOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final CheckerState currentState = checkerViewModel.getState();
                        int selectedStartChecking = (int) startCheckingOptions.getSelectedItem();
                        currentState.setStartChecking(selectedStartChecking);
                        checkerViewModel.setState(currentState);
                    }
                }
        );

        stopCheckingOptions.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final CheckerState currentState = checkerViewModel.getState();
                        int selectedStopChecking = (int) stopCheckingOptions.getSelectedItem();
                        currentState.setStopChecking(selectedStopChecking);
                        checkerViewModel.setState(currentState);
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
        final CheckerState state = (CheckerState) evt.getNewValue();
        // check the weather condition results (condition met or not)
        boolean isWeatherConditionMet = state.isWeatherConditionMet();

        // display the message based on the weather condition results
        if (isWeatherConditionMet) {
            JOptionPane.showMessageDialog(this, "Weather condition is met.");
        } else {
            JOptionPane.showMessageDialog(this, "Weather condition is not met.");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setCheckerController(CheckerController checkerController) {
        this.checkerController = checkerController;
    }
}


