package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import interface_adapter.display_daily.DisplayDailyState;
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyViewModel;
import constants.Constants;

/**
 * The view for the daily weather use-case.
 */
public class DailyView extends JPanel implements PropertyChangeListener {
    private static final Font crimsonText20 = FontManager.getCrimsonTextRegular(20);

    private final String viewName = "Weekly Forecast";

    private final DisplayDailyViewModel displayDailyViewModel;
    private DisplayDailyController displayDailyController;

    // data to be displayed
    private final JLabel city;
    private final List<JLabel> temperatures;
    private final List<JLabel> conditions;
    private final JLabel feelsLikeTemperature;
    private final JLabel uvIndex;
    private final JLabel windSpeed;
    private final JLabel cloudCover;
    private final JLabel precipitation;
    private final JLabel humidity;

    // weekday names listed in order (i.e. Tuesday, Wednesday, ... )
    private final List<JButton> weekdays;
    private final JButton backButton;

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.displayDailyViewModel.addPropertyChangeListener(this);

        city = new JLabel();
        city.setAlignmentX(Component.CENTER_ALIGNMENT);

        temperatures = new ArrayList<>(Constants.WEEK_SIZE);
        conditions = new ArrayList<>(Constants.WEEK_SIZE);
        weekdays = new ArrayList<>(Constants.WEEK_SIZE);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            conditions.add(new JLabel());
            temperatures.add(new JLabel());
            weekdays.add(new JButton());
        }
        feelsLikeTemperature = new JLabel();
        uvIndex = new JLabel();
        windSpeed = new JLabel();
        cloudCover = new JLabel();
        precipitation = new JLabel();
        humidity = new JLabel();
        backButton = new JButton(DisplayDailyViewModel.BACK_BUTTON_IMAGE);

        final JPanel buttons = new JPanel();
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            buttons.add(weekdays.get(i));
        }
        buttons.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        }

        backButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        final DisplayDailyState currentState = this.displayDailyViewModel.getState();

                        // TODO: Implement the back button
                        this.displayDailyController.execute(
                                currentState.getWeekdays().getFirst()
                        );
                    }
                });

        this.add(city);
        for (int i = 0; i < Constants.WEEK_SIZE; i++) {
            this.add(temperatures.get(i));
            this.add(conditions.get(i));
            this.add(weekdays.get(i));
        }
        this.add(feelsLikeTemperature);
        this.add(uvIndex);
        this.add(windSpeed);
        this.add(cloudCover);
        this.add(precipitation);
        this.add(humidity);
        this.add(backButton);
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
            conditions.get(i).setText(state.getConditions().get(i));
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

}