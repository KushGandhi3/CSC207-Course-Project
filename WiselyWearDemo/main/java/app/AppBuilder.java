package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interface_adapter.display_home.DisplayHomeViewModel;
import view.DisplayHomeView;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final DisplayHomeViewModel displayHomeViewModel = new DisplayHomeViewModel();
    private final DisplayHomeView displayHomeView =
            new DisplayHomeView(displayHomeViewModel);

    /**
     * Adds the WeatherHomeView to the app.
     * @return the AppBuilder
     */
    public AppBuilder addWeatherHomeView() {
        cardPanel.add(displayHomeView, "displayHomeView");
        return this;
    }

    /**
     * Builds the JFrame for the app and initially sets the displayHomeView to be displayed.
     * @return the JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        cardLayout.show(cardPanel, "displayHomeView");
        return application;
    }
}