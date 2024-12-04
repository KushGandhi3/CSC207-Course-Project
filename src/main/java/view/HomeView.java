package view;

import interface_adapter.display_home.DisplayHomeController;
import interface_adapter.display_home.DisplayHomeState;
import interface_adapter.display_home.DisplayHomeViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The View for the Display Home Use Case.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final Font crimsonTextBold65 = FontManager.getCrimsonTextBold(65);
    private static final Font crimsonTextBold75 = FontManager.getCrimsonTextBold(75);
    private static final Font interTextBold12 = FontManager.getCrimsonTextBold(12);
    private static final Font interTextBold15 = FontManager.getCrimsonTextBold(15);
    private static final Font interTextBold35 = FontManager.getCrimsonTextBold(35);
    private static final Font interTextBold50 = FontManager.getCrimsonTextBold(50);

    private final String viewName = "Home";

    private final DisplayHomeViewModel displayHomeViewModel;
    private DisplayHomeController displayHomeController;

    private final JTextField locationField = new JTextField(10);

    private final JLabel dateLabel = new JLabel();
    private final JLabel temperatureLabel = new JLabel();
    // the info label displays high temperature, low temperature, and condition
    private final JLabel infoLabel = new JLabel();

    private final JButton hourlyButton = new JButton();
    private final JButton dailyButton = new JButton();
    private final JButton checkerButton = new JButton();
    private final JButton summaryButton = new JButton();
    private final JButton historyButton = new JButton();
    private final JButton refreshButton = new JButton();

    // scheduler used to update the clock
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public HomeView(DisplayHomeViewModel displayHomeViewModel) {
        this.displayHomeViewModel = displayHomeViewModel;
        this.displayHomeViewModel.addPropertyChangeListener(this);

        componentStyling();

        JPanel buttonPanel = getButtonPanel();

        addActionListeners();

        // set the layout and border of the main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box box = Box.createVerticalBox();
        box.add(locationField);
        box.add(dateLabel);
        box.add(temperatureLabel);
        box.add(infoLabel);
        box.add(Box.createVerticalStrut(50));
        box.add(buttonPanel);
        box.add(Box.createVerticalStrut(50));
        box.add(refreshButton);
        box.add(Box.createVerticalStrut(50));
        this.add(box);

        // set default values
        DisplayHomeState currentState = displayHomeViewModel.getState();
        setLabels(currentState);
    }

    /**
     * Add the buttons to a horizontal JPanel and return the JPanel.
     * @return the button JPanel
     */
    @NotNull
    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 0, 0));
        buttonPanel.add(hourlyButton);
        buttonPanel.add(dailyButton);
        buttonPanel.add(checkerButton);
        buttonPanel.add(summaryButton);
        buttonPanel.add(historyButton);

        return buttonPanel;
    }

    /**
     * Set the button icons and style them.
     */
    private void componentStyling() {
        hourlyButton.setIcon(DisplayHomeViewModel.HOURLY_BUTTON);
        hourlyButton.setBorder(BorderFactory.createEmptyBorder());
        hourlyButton.setContentAreaFilled(false);

        dailyButton.setIcon(DisplayHomeViewModel.DAILY_BUTTON);
        dailyButton.setBorder(BorderFactory.createEmptyBorder());
        dailyButton.setContentAreaFilled(false);

        checkerButton.setIcon(DisplayHomeViewModel.CHECKER_BUTTON);
        checkerButton.setBorder(BorderFactory.createEmptyBorder());
        checkerButton.setContentAreaFilled(false);

        summaryButton.setIcon(DisplayHomeViewModel.SUMMARY_BUTTON);
        summaryButton.setBorder(BorderFactory.createEmptyBorder());
        summaryButton.setContentAreaFilled(false);

        historyButton.setIcon(DisplayHomeViewModel.HISTORY_BUTTON);
        historyButton.setBorder(BorderFactory.createEmptyBorder());
        historyButton.setContentAreaFilled(false);

        refreshButton.setIcon(DisplayHomeViewModel.REFRESH_BUTTON);
        refreshButton.setBorder(BorderFactory.createEmptyBorder());
        refreshButton.setContentAreaFilled(false);
        refreshButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        locationField.setFont(crimsonTextBold65);
        locationField.setText("Enter Location");
        locationField.setBorder(BorderFactory.createEmptyBorder());
        locationField.setHorizontalAlignment(JTextField.CENTER);

        dateLabel.setFont(interTextBold35);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        temperatureLabel.setFont(interTextBold35);
        temperatureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoLabel.setFont(interTextBold35);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addActionListeners() {
        dailyButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(dailyButton)) {
                        this.displayHomeController.switchToDailyView();
                    }
                }
        );

        checkerButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(checkerButton)) {
                        this.displayHomeController.switchToCheckerView();
                    }
                }
        );

        historyButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(historyButton)) {
                        this.displayHomeController.switchToHistoryView();
                    }
                }
        );

        summaryButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(summaryButton)) {
                        this.displayHomeController.switchToSummaryView();
                    }
                }
        );

        refreshButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(refreshButton)) {
                        DisplayHomeState displayHomeState = this.displayHomeViewModel.getState();
                        this.displayHomeController.execute(displayHomeState.getCity());
                    }
                }
        );

        // add document listeners to the location field
        locationField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final DisplayHomeState currentState = displayHomeViewModel.getState();
                currentState.setCity(locationField.getText());
                displayHomeViewModel.setState(currentState);
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
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("update_data")) {
            displayHomeController.execute();
            DisplayHomeState currentState = displayHomeViewModel.getState();
            locationField.setText(currentState.getCity());
        } else {
            final DisplayHomeState currentState = (DisplayHomeState) evt.getNewValue();

            setLabels(currentState);
        }
    }

    private void setLabels(DisplayHomeState currentState) {
        temperatureLabel.setText(currentState.getTemperature());
        // set the info label
        final String infoLabelString = (getWeatherString(currentState.getCondition()) + " "
                + DisplayHomeViewModel.highLabel + currentState.getHighTemperature() + DisplayHomeViewModel.divider
                + DisplayHomeViewModel.lowLabel + currentState.getLowTemperature());
        infoLabel.setText(infoLabelString);
        dateLabel.setText(currentState.getDate());
    }

    /**
     * Invoked when an action occurs.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // do nothing
    }

    public void setDisplayHomeController(DisplayHomeController displayHomeController) {
        this.displayHomeController = displayHomeController;
    }

    /**
     * Chooses the corresponding condition String based on the String code.
     * @param condition the weather condition
     * @return the corresponding weather condition String
     */
    private String getWeatherString(String condition) {
        return switch (condition) {
            case DisplayHomeViewModel.CLOUDS -> "Cloudy";
            case DisplayHomeViewModel.CLEAR -> "Sunny";
            case DisplayHomeViewModel.DRIZZLE, DisplayHomeViewModel.THUNDERSTORM, DisplayHomeViewModel.RAIN,
                 DisplayHomeViewModel.MIST -> "Rainy";
            case DisplayHomeViewModel.SNOW -> "Snowy";
            default -> "Cloudy";
        };
    }

}