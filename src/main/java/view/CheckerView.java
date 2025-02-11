package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constants.Constants;
import interface_adapter.display_checker.DisplayCheckerController;
import interface_adapter.display_checker.DisplayCheckerState;
import interface_adapter.display_checker.DisplayCheckerViewModel;

public class CheckerView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int NUM_20 = 20;
    private static final int NUM_10 = 10;
    private static final int NUM_3 = 3;

    private final DisplayCheckerViewModel displayCheckerViewModel;
    private DisplayCheckerController displayCheckerController;

    private final JTextField locationField = new JTextField(10);
    private final JComboBox<String> weatherConditionOptions = new JComboBox<>(DisplayCheckerViewModel
            .WEATHER_CONDITION_OPTIONS);
    private final JComboBox<Integer> startCheckingOptions = new JComboBox<>(DisplayCheckerViewModel
            .START_CHECKING_OPTIONS);
    private final JComboBox<Integer> stopCheckingOptions = new JComboBox<>(DisplayCheckerViewModel
            .STOP_CHECKING_OPTIONS);

    private final JButton checkButton = new JButton(DisplayCheckerViewModel.CHECK_BUTTON_LABEL);
    private final JButton backButton = new JButton(DisplayCheckerViewModel.BACK_BUTTON_IMAGE);

    public CheckerView(DisplayCheckerViewModel displayCheckerViewModel) {
        this.displayCheckerViewModel = displayCheckerViewModel;
        this.displayCheckerViewModel.addPropertyChangeListener(this);

        // set the layout and border of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(NUM_20, NUM_20, NUM_20, NUM_20));

        // Create and style the back button
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);

        // Back button panel
        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);

        // Title label
        final JLabel titleLabel = new JLabel(DisplayCheckerViewModel.TITLE_LABEL, SwingConstants.CENTER);
        titleLabel.setFont(FontManager.getCrimsonTextRegular(Constants.TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Title panel
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        // Main content panel
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields panel
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(NUM_20, NUM_20, NUM_10, NUM_20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Location label and input
        final JLabel locationLabel = new JLabel(DisplayCheckerViewModel.LOCATION_LABEL);
        locationLabel.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        locationField.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(locationField, gbc);

        // Weather condition label and input
        final JLabel weatherConditionLabel = new JLabel(DisplayCheckerViewModel.WEATHER_CONDITION_LABEL);
        weatherConditionLabel.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        weatherConditionOptions.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(weatherConditionLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(weatherConditionOptions, gbc);

        // Start checking label and input
        final JLabel startCheckingLabel = new JLabel(DisplayCheckerViewModel.START_CHECKING_LABEL);
        startCheckingLabel.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        startCheckingOptions.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(startCheckingLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(startCheckingOptions, gbc);

        // Add label "hour(s) from now"
        final JLabel startCheckingDescription = new JLabel(DisplayCheckerViewModel.START_CHECKING_DESCRIPTION);
        startCheckingDescription.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 2;
        inputPanel.add(startCheckingDescription, gbc);

        // Stop checking label and input
        final JLabel stopCheckingLabel = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_LABEL);
        stopCheckingLabel.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        stopCheckingOptions.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 0;
        gbc.gridy = NUM_3;
        inputPanel.add(stopCheckingLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(stopCheckingOptions, gbc);

        // Add label "hour(s) from start time"
        final JLabel stopCheckingDescription = new JLabel(DisplayCheckerViewModel.STOP_CHECKING_DESCRIPTION);
        stopCheckingDescription.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        gbc.gridx = 2;
        inputPanel.add(stopCheckingDescription, gbc);

        // Check button panel
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        checkButton.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
        buttonPanel.add(checkButton);

        // Add components to the content panel
        contentPanel.add(Box.createVerticalStrut(NUM_20));
        contentPanel.add(inputPanel);
        contentPanel.add(Box.createVerticalStrut(NUM_20));
        contentPanel.add(buttonPanel);

        // Add panels to the main layout
        add(backButtonPanel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);

        // add action and document listeners
        addEventListeners();
    }

    private void addEventListeners() {
        // add action listeners to the check button
        checkButton.addActionListener(
                // this creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
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
        );

        // add action listeners to the cancel button
        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        displayCheckerController.switchToHomeView();
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
                evt -> {
                    final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                    final String selectedWeatherCondition = (String) weatherConditionOptions.getSelectedItem();
                    currentState.setWeatherConditionOptions(selectedWeatherCondition);
                    displayCheckerViewModel.setState(currentState);
                }
        );

        // add action listeners to the start and stop checking options
        startCheckingOptions.addActionListener(
                evt -> {
                    final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                    final int selectedStartChecking = (int) startCheckingOptions.getSelectedItem();
                    currentState.setStartChecking(selectedStartChecking);
                    displayCheckerViewModel.setState(currentState);
                }
        );

        // add action listeners to the stop checking options
        stopCheckingOptions.addActionListener(
                evt -> {
                    final DisplayCheckerState currentState = displayCheckerViewModel.getState();
                    final int selectedStopChecking = (int) stopCheckingOptions.getSelectedItem();
                    currentState.setStopChecking(selectedStopChecking);
                    displayCheckerViewModel.setState(currentState);
                }
        );
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // do nothing
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // get the new value from the event
        final DisplayCheckerState state = (DisplayCheckerState) evt.getNewValue();
        // check the weather condition results (condition met or not)
        final String isWeatherConditionMet = state.getMessage();

        // display the message based on the weather condition results
        if (Objects.equals(isWeatherConditionMet, "exist")) {
            JOptionPane.showMessageDialog(this, "Weather condition is met.");
            state.setMessage(null);
        }
        else if (Objects.equals(isWeatherConditionMet, "nonexist")) {
            JOptionPane.showMessageDialog(this, "Weather condition is not met.");
            state.setMessage(null);
        }
        else if (Objects.equals(isWeatherConditionMet, "empty")) {
            JOptionPane.showMessageDialog(this, "Location cannot be empty.");
            state.setMessage(null);
        }
        else if (Objects.equals(isWeatherConditionMet, "invalid")) {
            JOptionPane.showMessageDialog(this, "Invalid location. Please try another location.");
            state.setMessage(null);
        }
    }

    public String getViewName() {
        return "checker";
    }

    public void setCheckerController(DisplayCheckerController controller) {
        this.displayCheckerController = controller;
    }
}
