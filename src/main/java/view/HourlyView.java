package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import constants.Constants;
import interface_adapter.display_hourly.DisplayHourlyController;
import interface_adapter.display_hourly.DisplayHourlyState;
import interface_adapter.display_hourly.DisplayHourlyViewModel;

/**
 * View for displaying hourly weather data.
 * Updates dynamically based on the ViewModel's state.
 */
public class HourlyView extends JPanel implements PropertyChangeListener, ActionListener {
    // Fonts
    private static final Font CRIMSOMTEXTBOLD12 = FontManager.getCrimsonTextBold(12);
    private static final Font CRIMSOMTEXTBOLD15 = FontManager.getCrimsonTextBold(15);
    private static final Font CRIMSOMTEXTBOLD18 = FontManager.getCrimsonTextBold(18);
    private static final Font CRIMSOMTEXTBOLD70 = FontManager.getCrimsonTextBold(70);
    private static final int NUM_3 = 3;
    private static final int NUM_10 = 10;
    private static final int NUM_15 = 15;
    private static final int NUM_55 = 55;
    private static final int NUM_135 = 135;
    private static final int NUM_150 = 150;
    private static final int NUM_195 = 195;
    private static final int NUM_220 = 220;

    // View Name
    private final String viewName = "Hourly";

    // View Model
    private final DisplayHourlyViewModel displayHourlyViewModel;
    private DisplayHourlyController displayHourlyController;

    // City Labels
    private final JLabel city = new JLabel();

    // Weather Labels
    private final List<JButton> condition = new ArrayList<>(Constants.TIME_SIZE);
    private final List<JButton> temperature = new ArrayList<>(Constants.TIME_SIZE);
    private final JLabel feelsLike = new JLabel();
    private final JLabel windSpeed = new JLabel();
    private final JLabel precipitation = new JLabel();
    private final JLabel uvIndex = new JLabel();
    private final JLabel cloudCover = new JLabel();
    private final JLabel humidity = new JLabel();

    // Buttons
    private final List<JButton> times = new ArrayList<>(Constants.TIME_SIZE);
    private final JButton backButton = new JButton();

    /**
     * Constructs an HourlyView with a specific ViewModel.
     *
     * @param displayHourlyViewModel the ViewModel to bind to this view
     */
    public HourlyView(DisplayHourlyViewModel displayHourlyViewModel) {
        this.displayHourlyViewModel = displayHourlyViewModel;
        this.displayHourlyViewModel.addPropertyChangeListener(this);

        // City Label
        setCityLabel();

        // Hourly Forecast Xox
        final JPanel forecastBox = getForecastBox();

        // Hour Details Box
        final JPanel detailsBox = getDetailsBox();

        final JPanel backButtonPanel = getBackButton();

        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            final int index = i;
            final JButton timeButton = times.get(i);
            timeButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(timeButton)) {
                            System.out.println("you pressed time at " + index);
                            final DisplayHourlyState currentState = this.displayHourlyViewModel.getState();
                            this.displayHourlyController.execute(
                                    currentState.getTime().get(index)
                            );
                        }
                    }
            );
            final JButton conditionButton = condition.get(index);
            conditionButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(conditionButton)) {
                            System.out.println("you pressed condition at " + index);
                            final DisplayHourlyState currentState = this.displayHourlyViewModel.getState();
                            this.displayHourlyController.execute(
                                    currentState.getTime().get(index)
                            );
                        }
                    }
            );
            final JButton temperatureButton = temperature.get(index);
            temperatureButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(temperatureButton)) {
                            System.out.println("you pressed temperature at " + index);
                            final DisplayHourlyState currentState = this.displayHourlyViewModel.getState();
                            this.displayHourlyController.execute(
                                    currentState.getTime().get(index)
                            );
                        }
                    }
            );
        }
        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        System.out.println("you pressed the back button");
                        final DisplayHourlyState currentState = this.displayHourlyViewModel.getState();
                        this.displayHourlyController.execute(
                                currentState.getTime().getFirst()
                        );
                    }
                }
        );

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(NUM_10, NUM_10, NUM_10, NUM_10));

        final Box box = Box.createVerticalBox();
        box.add(backButtonPanel);
        box.add(city);
        box.add(forecastBox);
        box.add(detailsBox);
        this.add(box);

        // Set Default State
        final DisplayHourlyState state = displayHourlyViewModel.getState();
        setLabels(state);
    }

    /**
     * Gets the details box, overlays the details data, and styles the resulting JPanel.
     * @return the details box JPanel object
     */
    @NotNull
    private JPanel getDetailsBox() {
        // Set Fonts
        feelsLike.setFont(CRIMSOMTEXTBOLD18);
        windSpeed.setFont(CRIMSOMTEXTBOLD18);
        precipitation.setFont(CRIMSOMTEXTBOLD18);
        uvIndex.setFont(CRIMSOMTEXTBOLD18);
        cloudCover.setFont(CRIMSOMTEXTBOLD18);
        humidity.setFont(CRIMSOMTEXTBOLD18);

        // Hourly Details Box
        final JPanel detailsBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int forecastBoxX = (this.getWidth() - DisplayHourlyViewModel.HOURLY_DETAILS_BOX
                        .getImage().getWidth(this)) / 2 + 4;
                final int forecastBoxY = 0;
                g.drawImage(DisplayHourlyViewModel.HOURLY_DETAILS_BOX.getImage(), forecastBoxX,
                        forecastBoxY, this);
            }
        };

        // Details Panel Shifted
        final JPanel shiftedDetailsPanel = new JPanel();
        shiftedDetailsPanel.setLayout(new BoxLayout(shiftedDetailsPanel, BoxLayout.X_AXIS));
        shiftedDetailsPanel.setOpaque(false);

        // Details Panel
        final JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, NUM_3, NUM_195, 0));
        detailsPanel.setOpaque(false);

        // Column 1
        final JPanel detailsPanel1 = new JPanel();
        detailsPanel1.setLayout(new GridLayout(2, 1, 0, NUM_55));
        detailsPanel1.setOpaque(false);
        detailsPanel1.add(feelsLike);
        detailsPanel1.add(uvIndex);

        // Column 2
        final JPanel detailsPanel2 = new JPanel();
        detailsPanel2.setLayout(new GridLayout(2, 1, 0, NUM_55));
        detailsPanel2.setOpaque(false);
        detailsPanel2.add(windSpeed);
        detailsPanel2.add(cloudCover);

        // Column 3
        final JPanel detailsPanel3 = new JPanel();
        detailsPanel3.setLayout(new GridLayout(2, 1, 0, NUM_55));
        detailsPanel3.setOpaque(false);
        detailsPanel3.add(precipitation);
        detailsPanel3.add(humidity);

        // Add Columns
        detailsPanel.add(detailsPanel1);
        detailsPanel.add(detailsPanel2);
        detailsPanel.add(detailsPanel3);

        // Shift Shifted Details Panel
        shiftedDetailsPanel.add(detailsPanel);
        shiftedDetailsPanel.add(Box.createHorizontalStrut(NUM_135));

        // Padding
        detailsBox.add(Box.createVerticalStrut(NUM_220));

        // Add Details Panel
        detailsBox.add(shiftedDetailsPanel);

        return detailsBox;
    }

    /**
     * Gets the back button JPanel and styles it.
     * @return a back button JPanel
     */
    @NotNull
    private JPanel getBackButton() {
        backButton.setIcon(DisplayHourlyViewModel.BACK_BUTTON_IMAGE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        return backButtonPanel;
    }

    /**
     * Styles the current instance of the city label.
     */
    private void setCityLabel() {
        city.setFont(CRIMSOMTEXTBOLD70);
        city.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Gets the hourly forecast box and fills it with the weather.
     * @return the hourly forecast box as a JPanel
     */
    @NotNull
    private JPanel getForecastBox() {
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            condition.add(new JButton());
            temperature.add(new JButton());
            times.add(new JButton());

            // Style Time Buttons
            times.get(i).setFont(CRIMSOMTEXTBOLD12);

            // Style Temperature Buttons
            times.get(i).setFont(CRIMSOMTEXTBOLD15);
        }

        // Place Hourly Forecast Box Image
        final JPanel forecastBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int forecastBoxX = (this.getWidth() - DisplayHourlyViewModel.HOURLY_FORECAST_BOX
                        .getImage().getWidth(this)) / 2 + 4;
                final int forecastBoxY = 0;
                g.drawImage(DisplayHourlyViewModel.HOURLY_FORECAST_BOX.getImage(), forecastBoxX,
                        forecastBoxY, this);
            }
        };

        // Hourly Content Panel
        final JPanel hourlyPanel = new JPanel();
        hourlyPanel.setLayout(new GridLayout(1, Constants.TIME_SIZE, NUM_15, 0));

        // Padding
        hourlyPanel.add(Box.createHorizontalStrut(NUM_15));

        // Make Hourly Panel Invisible
        hourlyPanel.setOpaque(false);

        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            // Panel For Each Hour
            final JPanel hourPanel = new JPanel();
            hourPanel.setLayout(new GridLayout(NUM_3, 1, 0, 0));
            hourPanel.setOpaque(false);

            // Remove Button Border
            times.get(i).setBorder(BorderFactory.createEmptyBorder());
            times.get(i).setContentAreaFilled(false);
            hourPanel.add(times.get(i));

            condition.get(i).setBorder(BorderFactory.createEmptyBorder());
            condition.get(i).setContentAreaFilled(false);
            hourPanel.add(condition.get(i));

            temperature.get(i).setBorder(BorderFactory.createEmptyBorder());
            temperature.get(i).setContentAreaFilled(false);
            hourPanel.add(temperature.get(i));

            hourlyPanel.add(hourPanel);
        }

        // Add Padding
        hourlyPanel.add(Box.createHorizontalStrut(NUM_15));

        // Vertical Spacing
        forecastBox.add(Box.createVerticalStrut(NUM_150));
        forecastBox.add(hourlyPanel);

        return forecastBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("update_data")) {
            final DisplayHourlyState currentState = (DisplayHourlyState) evt.getNewValue();

            // execute the Display Hourly Weather Use Case on the current hour
            displayHourlyController.execute(currentState.getTime().getFirst());
        }
        else {
            final DisplayHourlyState currentState = (DisplayHourlyState) evt.getNewValue();
            setLabels(currentState);
        }

        final DisplayHourlyState currentState = (DisplayHourlyState) evt.getNewValue();
        setLabels(currentState);
    }

    private void setLabels(DisplayHourlyState state) {
        city.setText(state.getCity());
        for (int i = 0; i < Constants.TIME_SIZE; i++) {
            temperature.get(i).setText(state.getTemperature().get(i));
            condition.get(i).setIcon(chooseWeatherIcon(state.getCondition().get(i)));
            times.get(i).setText(state.getTime().get(i));
        }
        feelsLike.setText(state.getFeelsLike());
        uvIndex.setText(state.getUvIndex());
        windSpeed.setText(state.getWindSpeed());
        cloudCover.setText(state.getCloudCover());
        precipitation.setText(state.getPrecipitation());
        humidity.setText(state.getHumidity());
    }

    public String getViewName() {
        return viewName;
    }

    public void setDisplayHourlyController(DisplayHourlyController displayHourlyController) {
        this.displayHourlyController = displayHourlyController;
    }

    /**
     * Chooses the corresponding weather icon based on the String code.
     * @param cond the weather condition
     * @return the corresponding weather image icon
     */
    private ImageIcon chooseWeatherIcon(String cond) {
        return switch (cond) {
            case DisplayHourlyViewModel.CLOUDS -> DisplayHourlyViewModel.CLOUDS_IMAGE;
            case DisplayHourlyViewModel.DRIZZLE, DisplayHourlyViewModel.THUNDERSTORM, DisplayHourlyViewModel.RAIN,
                 DisplayHourlyViewModel.MIST -> DisplayHourlyViewModel.RAIN_IMAGE;
            case DisplayHourlyViewModel.SNOW -> DisplayHourlyViewModel.SNOW_IMAGE;
            default -> DisplayHourlyViewModel.CLEAR_IMAGE;
        };
    }

    /**
     * Invoked when an action occurs.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Action command: " + event.getActionCommand());
        System.out.println("Action source: " + event.getSource());
        final DisplayHourlyState currentState = this.displayHourlyViewModel.getState();
        this.displayHourlyController.execute(currentState.getTime().getFirst());
    }
}
