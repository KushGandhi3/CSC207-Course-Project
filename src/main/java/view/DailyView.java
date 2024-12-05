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
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyViewModel;

/**
 * The view for the daily weather use-case.
 */
public class DailyView extends JPanel implements PropertyChangeListener, ActionListener {
    private static final Font CRIMSON_TEXT_70 = FontManager.getCrimsonTextRegular(70);
    private static final Font INTER_TEXT_BOLD_12 = FontManager.getCrimsonTextBold(12);
    private static final Font INTER_TEXT_BOLD_15 = FontManager.getCrimsonTextBold(15);
    private static final Font INTER_TEXT_BOLD_18 = FontManager.getCrimsonTextBold(18);

    private static final String VIEW_NAME = "Daily Forecast";
    private static final int GRID_LAYOUT_HGAP = 195;
    private static final int DETAILS_VGAP = 55;
    private static final int SHIFTED_HORIZONTAL_PADDING = 135;
    private static final int DETAILS_BOX_VERTICAL_PADDING = 220;
    private static final int DAILY_PANEL_HGAP = 15;
    private static final int HORIZONTAL_PADDING = 15;
    private static final int FORECAST_BOX_VERTICAL_PADDING = 150;
    private static final int BORDER_PADDING = 10;
    private static final int NUM_3 = 3;

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
        final JPanel forecastBox = getForecastBox();

        // day details box
        final JPanel detailsBox = getDetailsBox();

        final JPanel backButtonPanel = getBackButton();

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
                        this.displayDailyController.switchToHomeView();
                    }
                }
        );

        // set the layout and border of the main panel
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        final Box box = Box.createVerticalBox();
        box.add(backButtonPanel);
        box.add(city);
        box.add(forecastBox);
        box.add(detailsBox);
        add(box);
    }

    /**
     * Gets the details box, overlays the details data, and styles the resulting JPanel.
     * @return the details box JPanel object
     */
    @NotNull
    private JPanel getDetailsBox() {
        // set fonts
        feelsLikeTemperature.setFont(INTER_TEXT_BOLD_18);
        uvIndex.setFont(INTER_TEXT_BOLD_18);
        windSpeed.setFont(INTER_TEXT_BOLD_18);
        cloudCover.setFont(INTER_TEXT_BOLD_18);
        precipitation.setFont(INTER_TEXT_BOLD_18);
        humidity.setFont(INTER_TEXT_BOLD_18);

        // place the Daily Details Box on the screen
        final JPanel detailsBox = new JPanel() {
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
        final JPanel shiftedDetailsPanel = new JPanel();
        shiftedDetailsPanel.setLayout(new BoxLayout(shiftedDetailsPanel, BoxLayout.X_AXIS));
        shiftedDetailsPanel.setOpaque(false);
        // details panel
        final JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(1, NUM_3, GRID_LAYOUT_HGAP, 0));
        detailsPanel.setOpaque(false);
        // column 1
        final JPanel detailsPanel1 = new JPanel();
        detailsPanel1.setLayout(new GridLayout(2, 1, 0, DETAILS_VGAP));
        detailsPanel1.setOpaque(false);
        detailsPanel1.add(feelsLikeTemperature);
        detailsPanel1.add(uvIndex);
        // column 2
        final JPanel detailsPanel2 = new JPanel();
        detailsPanel2.setLayout(new GridLayout(2, 1, 0, DETAILS_VGAP));
        detailsPanel2.setOpaque(false);
        detailsPanel2.add(windSpeed);
        detailsPanel2.add(cloudCover);
        // column 3
        final JPanel detailsPanel3 = new JPanel();
        detailsPanel3.setLayout(new GridLayout(2, 1, 0, DETAILS_VGAP));
        detailsPanel3.setOpaque(false);
        detailsPanel3.add(precipitation);
        detailsPanel3.add(humidity);
        // add all columns to details panel
        detailsPanel.add(detailsPanel1);
        detailsPanel.add(detailsPanel2);
        detailsPanel.add(detailsPanel3);

        // shift the shiftedDetailsPanel horizontally
        shiftedDetailsPanel.add(detailsPanel);
        shiftedDetailsPanel.add(Box.createHorizontalStrut(SHIFTED_HORIZONTAL_PADDING));
        // padding
        detailsBox.add(Box.createVerticalStrut(DETAILS_BOX_VERTICAL_PADDING));
        // add detailsPanel to detailsBox
        detailsBox.add(shiftedDetailsPanel);

        return detailsBox;
    }

    /**
     * Gets the back button JPanel and styles it.
     * @return a back button JPanel
     */
    @NotNull
    private JPanel getBackButton() {
        backButton.setIcon(DisplayDailyViewModel.BACK_BUTTON_IMAGE);
        // style the back button
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        // back button panel
        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButtonPanel.add(backButton);

        return backButtonPanel;
    }

    /**
     * Styles the current instance of the city label.
     */
    private void setCityLabel() {
        city.setFont(CRIMSON_TEXT_70);
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
            weekdays.get(i).setFont(INTER_TEXT_BOLD_12);
            // style the temperature buttons
            temperatures.get(i).setFont(INTER_TEXT_BOLD_15);
        }

        // place the Daily Forecast Box Image on the screen
        final JPanel forecastBox = new JPanel() {
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
        final JPanel dailyPanel = new JPanel();
        dailyPanel.setLayout(new GridLayout(1, Constants.WEEK_SIZE, DAILY_PANEL_HGAP, 0));
        // add padding
        dailyPanel.add(Box.createHorizontalStrut(HORIZONTAL_PADDING));
        // make the daily panel invisible
        dailyPanel.setOpaque(false);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            // individual panel for each day
            final JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new GridLayout(NUM_3, 1, 0, 0));
            dayPanel.setOpaque(false);

            // remove the border of the button
            weekdays.get(i).setBorder(BorderFactory.createEmptyBorder());
            weekdays.get(i).setContentAreaFilled(false);
            dayPanel.add(weekdays.get(i));

            conditions.get(i).setBorder(BorderFactory.createEmptyBorder());
            conditions.get(i).setContentAreaFilled(false);
            dayPanel.add(conditions.get(i));

            temperatures.get(i).setBorder(BorderFactory.createEmptyBorder());
            temperatures.get(i).setContentAreaFilled(false);
            dayPanel.add(temperatures.get(i));

            dailyPanel.add(dayPanel);
        }
        // add padding
        dailyPanel.add(Box.createHorizontalStrut(HORIZONTAL_PADDING));
        // vertical spacing for the dailyPanel
        forecastBox.add(Box.createVerticalStrut(FORECAST_BOX_VERTICAL_PADDING));
        forecastBox.add(dailyPanel);

        return forecastBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("update_data")) {
            // execute the Display Daily Weather Use Case on the current weekday
            displayDailyController.execute();
        }
        else {
            final DisplayDailyState currentState = (DisplayDailyState) evt.getNewValue();
            setLabels(currentState);
        }
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
        return VIEW_NAME;
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

    /**
     * Invoked when an action occurs.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // do nothing
    }
}
