package view;

import interface_adapter.display_map.WeatherViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * DisplayWeatherView is a Swing-based graphical user interface (GUI) that allows users
 * to view weather data for a specific city. The GUI includes buttons to toggle through
 * various weather layers (e.g., temperature, wind speed, pressure, precipitation, clouds),
 * and displays the selected layer's data in a label.
 */
public class DisplayWeatherView extends JFrame {

    private final WeatherViewModel viewModel;
    private final JLabel infoLabel;
    private String city = "Toronto"; // Default city

    /**
     * Constructor that sets up the main window and initializes the weather data view.
     * Creates the buttons and action listeners for interacting with the weather layers.
     */
    public DisplayWeatherView() {
        setTitle("Weather Map Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        viewModel = new WeatherViewModel(city);

        // Info label to display selected layer data
        infoLabel = new JLabel("Select a weather layer");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel, BorderLayout.NORTH);

        // Panel to show buttons for each weather layer
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton temperatureButton = new JButton("Temperature");
        JButton windButton = new JButton("Wind Speed");
        JButton pressureButton = new JButton("Pressure");
        JButton precipitationButton = new JButton("Precipitation");
        JButton cloudsButton = new JButton("Clouds");

        buttonPanel.add(temperatureButton);
        buttonPanel.add(windButton);
        buttonPanel.add(pressureButton);
        buttonPanel.add(precipitationButton);
        buttonPanel.add(cloudsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        temperatureButton.addActionListener(e -> updateLayer("Temperature: " + viewModel.getTemperature() + " °C"));
        windButton.addActionListener(e -> updateLayer("Wind Speed: " + viewModel.getWindSpeed() + " m/s"));
        pressureButton.addActionListener(e -> updateLayer("Pressure: " + viewModel.getPressure() + " hPa"));
        precipitationButton.addActionListener(e -> updateLayer("Precipitation: " + viewModel.getPrecipitation() + " mm"));
        cloudsButton.addActionListener(e -> updateLayer("Cloudiness: " + viewModel.getClouds() + " %"));

        // Show the frame
        setVisible(true);

    }

    /**
     * Updates the info label with the data for the selected weather layer.
     *
     * @param layerInfo A string describing the selected weather data (e.g., "Temperature: 22 °C").
     */
    private void updateLayer(String layerInfo) {
        infoLabel.setText(layerInfo);
    }

    /**
     * Main method that launches the weather viewer application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DisplayWeatherView::new);
    }
}
