package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import constants.Constants;
import interface_adapter.display_checker.DisplayCheckerController;
import interface_adapter.display_checker.DisplayCheckerState;
import interface_adapter.display_checker.DisplayCheckerViewModel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class CheckerView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Checker";
    private final DisplayCheckerViewModel displayCheckerViewModel;
    private DisplayCheckerController displayCheckerController;

    private final JTextField locationField = new JTextField(10);
    private final JComboBox<String> weatherConditionOptions = new JComboBox<>(DisplayCheckerViewModel.WEATHER_CONDITION_OPTIONS);
    private final JComboBox<Integer> startCheckingOptions = new JComboBox<>(DisplayCheckerViewModel.START_CHECKING_OPTIONS);
    private final JComboBox<Integer> stopCheckingOptions = new JComboBox<>(DisplayCheckerViewModel.STOP_CHECKING_OPTIONS);

    private final JButton checkButton = new JButton(DisplayCheckerViewModel.CHECK_BUTTON_LABEL);
    private final JButton backButton = new JButton(DisplayCheckerViewModel.BACK_BUTTON_IMAGE);

    public CheckerView(DisplayCheckerViewModel displayCheckerViewModel) {
        this.displayCheckerViewModel = displayCheckerViewModel;
        this.displayCheckerViewModel.addPropertyChangeListener(this);

        // set the layout and border of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and style the back button
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);

        // Back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);

        // Title label
        JLabel titleLabel = new JLabel(DisplayCheckerViewModel.TITLE_LABEL, JLabel.CENTER);
        titleLabel.setFont(FontManager.getCrimsonText(Constants.TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Consistent padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Location label and input
        JLabel locationLabel = new JLabel(DisplayCheckerViewModel.LOCATION_LABEL);
        locationLabel.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        locationField.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(locationField, gbc);

        // Weather condition label and input
        JLabel weatherConditionLabel = new JLabel(DisplayCheckerViewModel.WEATHER_CONDITION_LABEL);
        weatherConditionLabel.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        weatherConditionOptions.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(weatherConditionLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(weatherConditionOptions, gbc);

        // Start checking label and input
        JLabel startCheckingLabel = new JLabel(DisplayCheckerViewModel.START_CHECKING_LABEL);
        startCheckingLabel.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        startCheckingOptions.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(startCheckingLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(startCheckingOptions, gbc);

        // Add label "hour(s) from now"
        JLabel startCheckingDescription = new JLabel(DisplayCheckerViewModel.START_CHECKING_DESCRIPTION);
        startCheckingDescription.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 2;
        inputPanel.add(startCheckingDescription, gbc);

        // Stop checking label and input
        JLabel stopCheckingLabel = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_LABEL);
        stopCheckingLabel.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        stopCheckingOptions.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(stopCheckingLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(stopCheckingOptions, gbc);

        // Add label "hour(s) from start time"
        JLabel stopCheckingDescription = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_DESCRIPTION);
        stopCheckingDescription.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 2;
        inputPanel.add(stopCheckingDescription, gbc);

        // Check button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        checkButton.setFont(FontManager.getCrimsonText(Constants.LABEL_FONT_SIZE));
        buttonPanel.add(checkButton);

        // Add components to the content panel
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(inputPanel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonPanel);

        // Add panels to the main layout
        add(backButtonPanel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);

        // setting default value
        setDefaultValues();

        // add action and document listeners
        addEventListeners();

    }

    // set the default values for the state and location field
    private void setDefaultValues() {
        DisplayCheckerState displayCheckerState = displayCheckerViewModel.getState();
        displayCheckerState.setLocation("Toronto");
        displayCheckerState.setWeatherConditionOptions("Clear");
        displayCheckerState.setStartChecking(0);
        displayCheckerState.setStopChecking(1);
        displayCheckerViewModel.setState(displayCheckerState);

        // set the default value for the location field
        locationField.setText(displayCheckerState.getLocation());
    }

    private void addEventListeners() {
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
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
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
        });

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
        } else if (Objects.equals(isWeatherConditionMet, "empty")) {
            JOptionPane.showMessageDialog(this, "Location cannot be empty.");
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


