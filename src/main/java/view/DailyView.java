package view;

import interface_adapter.display_daily.DisplayDailyViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.display_daily.DisplayDailyViewModel;
import interface_adapter.display_daily.DisplayDailyController;

public class DailyView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Daily Forecast";
    private final DisplayDailyViewModel displayDailyViewModel;
    private final DisplayDailyController displayDailyController;

    private JLabel city;

    private final JButton timeButton;
    private final JButton homeButton;

    private JLabel[] temperatureDaily = new JLabel[7];
    private JLabel[] conditionDaily = new JLabel[7];
    private JLabel[] feelsLikeDaily = new JLabel[7];
    private JLabel[] windSpeedDaily = new JLabel[7];
    private JLabel[] precipitationChanceDaily = new JLabel[7];
    private JLabel[] uvIndexDaily = new JLabel[7];
    private JLabel[] airQualityDaily = new JLabel[7];
    private JLabel[] humidityDaily = new JLabel[7];

    public DailyView(DisplayDailyViewModel displayDailyViewModel) {
        this.displayDailyViewModel = displayDailyViewModel;
        this.setLayout(null);
        this.city = new JLabel("Toronto");

        this.add(city);

        city.setBounds(10, 10, 100, 20);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
