package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.*;
import entity.*;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final WeatherFactory weatherFactory = new CommonWeatherFactory();

    private final WeatherDataAccessObject weatherDataAccessObject =
            new WeatherDataAccessObject(weatherFactory);

    private DisplayHomeView displayHomeView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the WeatherHomeView to the app.
     * @return the AppBuilder
     */
    public AppBuilder addWeatherHomeView() {
        displayHomeView = new DisplayHomeView(weatherDataAccessObject);
        cardPanel.add(displayHomeView, displayHomeView.getViewName());
        return this;
    }

    /**
     * Builds the JFrame for the app and initially sets the displayHomeView to be displayed.
     * @return the JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new java.awt.Dimension(800, 600));
        application.setTitle("Wisely Wear");

        application.add(cardPanel);

        cardLayout.show(cardPanel, "displayHomeView");
        return application;
    }
}