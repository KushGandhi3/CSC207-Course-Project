package view;

import constants.Constants;
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The view for the daily weather use-case.
 */
public class DailyView extends JPanel implements PropertyChangeListener {
    private static final Font crimsonText70 = FontManager.getCrimsonTextRegular(70);
    private static final Font interTextBold12 = FontManager.getCrimsonTextBold(12);
    private static final Font interTextBold15 = FontManager.getCrimsonTextBold(15);
    private static final Font interTextBold18 = FontManager.getCrimsonTextBold(18);

    private final String viewName = "Weekly Forecast";

    private final DisplayDailyViewModel displayDailyViewModel;
    private DisplayDailyController displayDailyController;

    // data to be displayed
    private final JLabel city = new JLabel();
    private final List<JButton> temperatures = new ArrayList<>(Constants.WEEK_SIZE);
    private final List<JButton> conditions = new ArrayList<>(Constants.WEEK_SIZE);
    private final JLabel feelsLikeTemperature = new JLabel();
    private final JLabel uvIndex = new JLabel();
    private final JLabel windSpeed = new JLabel();
    private final JLabel cloudCover = new JLabel();
    private final JLabel precipitation = new JLabel();
    private final JLabel humidity = new JLabel();

    // weekday names listed in order (i.e. Tuesday, Wednesday, ... )
    private final List<JButton> weekdays = new ArrayList<>(Constants.WEEK_SIZE);
    private final JButton backButton = new JButton();

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayDailyViewModel.addPropertyChangeListener(this);

        // city label
        setCityLabel();

        // daily forecast box
        JPanel forecastBox = getForecastBox();

        // day details box
        JPanel detailsBox = getDetailsBox();

        JPanel backButtonPanel = getBackButton();

        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            final int index = i;
            final JButton weekdayButton = weekdays.get(i);
            weekdayButton.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    evt -> {
                        if (evt.getSource().equals(weekdayButton)) {
                            final DisplayDailyState currentState = this.displayDailyViewModel.getState();

                            this.displayDailyController.execute(
                                    currentState.getWeekdays().get(index)
                            );
                        }
                    }
            );
            final JButton conditionButton = conditions.get(index);
            conditionButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(conditionButton)) {
                            final DisplayDailyState currentState = this.displayDailyViewModel.getState();

                            this.displayDailyController.execute(
                                    currentState.getWeekdays().get(index)
                            );
                        }
                    }
            );
            final JButton temperatureButton = temperatures.get(index);
            temperatureButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(temperatureButton)) {
                            final DisplayDailyState currentState = this.displayDailyViewModel.getState();

                            this.displayDailyController.execute(
                                    currentState.getWeekdays().get(index)
                            );
                        }
                    }
            );
        }

        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        final DisplayDailyState currentState = this.displayDailyViewModel.getState();

                        // TODO: Implement the back button
                        this.displayDailyController.execute(
                                currentState.getWeekdays().getFirst()
                        );
                    }
                }
        );

        // set the layout and border of the main panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box box = Box.createVerticalBox();
        box.add(backButtonPanel);
        box.add(city);
        box.add(forecastBox);
        box.add(detailsBox);
        this.add(box);

        // set default state
        DisplayDailyState state = displayDailyViewModel.getState();
        setLabels(state);
    }

    /**
     * Gets the details box, overlays the details data, and styles the resulting JPanel.
     * @return the details box JPanel object
     */
    @NotNull
    private JPanel getDetailsBox() {
        // set fonts
        feelsLikeTemperature.setFont(interTextBold18);
        uvIndex.setFont(interTextBold18);
        windSpeed.setFont(interTextBold18);
        cloudCover.setFont(interTextBold18);
        precipitation.setFont(interTextBold18);
        humidity.setFont(interTextBold18);

        // place the Daily Details Box on the screen
        JPanel detailsBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int forecastBoxX = (this.getWidth() - DisplayDailyViewModel.DAILY_DETAILS_BOX
                        .getImage().getWidth(this)) / 2 + 4;
                final int forecastBoxY = 0;
                // draw the image
                g.drawImage(DisplayDailyViewModel.DAILY_DETAILS_BOX.getImage(), forecastBoxX,
                        forecastBoxY, this);
            }
        };

        // details panel with horizontal shift applied
        JPanel shiftedDetailsPanel = new JPanel();
        shiftedDetailsPanel.setLayout(new BoxLayout(shiftedDetailsPanel, BoxLayout.X_AXIS));
        shiftedDetailsPanel.setOpaque(false);
        // details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, 3, 195, 0));
        detailsPanel.setOpaque(false);
        // column 1
        JPanel detailsPanel1 = new JPanel();
        detailsPanel1.setLayout(new GridLayout(2, 1, 0, 55));
        detailsPanel1.setOpaque(false);
        detailsPanel1.add(feelsLikeTemperature);
        detailsPanel1.add(uvIndex);
        // column 2
        JPanel detailsPanel2 = new JPanel();
        detailsPanel2.setLayout(new GridLayout(2, 1, 0, 55));
        detailsPanel2.setOpaque(false);
        detailsPanel2.add(windSpeed);
        detailsPanel2.add(cloudCover);
        // column 3
        JPanel detailsPanel3 = new JPanel();
        detailsPanel3.setLayout(new GridLayout(2, 1, 0, 55));
        detailsPanel3.setOpaque(false);
        detailsPanel3.add(precipitation);
        detailsPanel3.add(humidity);
        // add all columns to details panel
        detailsPanel.add(detailsPanel1);
        detailsPanel.add(detailsPanel2);
        detailsPanel.add(detailsPanel3);

        // shift the shiftedDetailsPanel horizontally
        shiftedDetailsPanel.add(detailsPanel);
        shiftedDetailsPanel.add(Box.createHorizontalStrut(135));
        // padding
        detailsBox.add(Box.createVerticalStrut(220));
        // add detailsPanel to detailsBox
        detailsBox.add(shiftedDetailsPanel);

        return detailsBox;
    }

    /**
     * Gets the back button JPanel and styles it
     * @return a back button JPanel
     */
    @NotNull
    private JPanel getBackButton() {
        backButton.setIcon(DisplayDailyViewModel.BACK_BUTTON_IMAGE);
        // style the back button
        backButton.setBorder(null);
        // back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButtonPanel.add(backButton);

        return backButtonPanel;
    }

    /**
     * Styles the current instance of the city label.
     */
    private void setCityLabel() {
        city.setFont(crimsonText70);
        city.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Gets the daily forecast box and fills it with the weather.
     * @return the daily forecast box as a JPanel
     */
    @NotNull
    private JPanel getForecastBox() {
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            conditions.add(new JButton());
            temperatures.add(new JButton());
            weekdays.add(new JButton());

            // style the weekday buttons
            weekdays.get(i).setFont(interTextBold12);
            // style the temperature buttons
            temperatures.get(i).setFont(interTextBold15);
        }

        // place the Daily Forecast Box Image on the screen
        JPanel forecastBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int forecastBoxX = (this.getWidth() - DisplayDailyViewModel.DAILY_FORECAST_BOX
                        .getImage().getWidth(this)) / 2 + 4;
                final int forecastBoxY = 0;
                // draw the image
                g.drawImage(DisplayDailyViewModel.DAILY_FORECAST_BOX.getImage(), forecastBoxX,
                        forecastBoxY, this);
            }
        };

        // daily content panel
        JPanel dailyPanel = new JPanel();
        dailyPanel.setLayout(new GridLayout(1, Constants.WEEK_SIZE, 15, 0));
        // add padding
        dailyPanel.add(Box.createHorizontalStrut(15));
        // make the daily panel invisible
        dailyPanel.setOpaque(false);
        for(int i = 0; i < Constants.WEEK_SIZE; i++) {
            // individual panel for each day
            final JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new GridLayout(3, 1, 0, 0));
            dayPanel.setOpaque(false);

            // remove the border of the button
            weekdays.get(i).setBorder(BorderFactory.createEmptyBorder());
            dayPanel.add(weekdays.get(i));

            conditions.get(i).setBorder(BorderFactory.createEmptyBorder());
            dayPanel.add(conditions.get(i));

            temperatures.get(i).setBorder(BorderFactory.createEmptyBorder());
            dayPanel.add(temperatures.get(i));

            dailyPanel.add(dayPanel);
        }
        // add padding
        dailyPanel.add(Box.createHorizontalStrut(15));
        // vertical spacing for the dailyPanel
        forecastBox.add(Box.createVerticalStrut(150));
        forecastBox.add(dailyPanel);

        return forecastBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DisplayDailyState currentState = (DisplayDailyState) evt.getNewValue();
        setLabels(currentState);
    }

    private void setLabels(DisplayDailyState state) {
        city.setText(state.getCity());
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            temperatures.get(i).setText(state.getTemperatures().get(i));
            conditions.get(i).setIcon(chooseWeatherIcon(state.getConditions().get(i)));
            weekdays.get(i).setText(state.getWeekdays().get(i));
        }
        feelsLikeTemperature.setText(state.getFeelsLikeTemperature());
        uvIndex.setText(state.getUvIndex());
        windSpeed.setText(state.getWindSpeed());
        cloudCover.setText(state.getCloudCover());
        precipitation.setText(state.getPrecipitation());
        humidity.setText(state.getHumidity());
    }

    public String getViewName() {
        return viewName;
    }

    public void setDisplayDailyController(DisplayDailyController displayDailyController) {
        this.displayDailyController = displayDailyController;
    }

    /**
     * Chooses the corresponding weather icon based on the String code.
     * @param condition the weather condition
     * @return the corresponding weather image icon
     */
    private ImageIcon chooseWeatherIcon(String condition) {
        return switch (condition) {
            case DisplayDailyViewModel.CLOUDS -> DisplayDailyViewModel.CLOUDS_IMAGE;
            case DisplayDailyViewModel.CLEAR -> DisplayDailyViewModel.CLEAR_IMAGE;
            case DisplayDailyViewModel.DRIZZLE, DisplayDailyViewModel.THUNDERSTORM, DisplayDailyViewModel.RAIN,
                 DisplayDailyViewModel.MIST -> DisplayDailyViewModel.RAIN_IMAGE;
            case DisplayDailyViewModel.SNOW -> DisplayDailyViewModel.SNOW_IMAGE;
            default -> DisplayDailyViewModel.CLEAR_IMAGE;
        };
    }

}