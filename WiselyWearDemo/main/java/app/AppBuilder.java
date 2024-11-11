package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.*;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_home.*;
import use_case.display_home.*;
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
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final WeatherDataAccessObject weatherDataAccessObject =
            new WeatherDataAccessObject(weatherFactory);

    private DisplayHomeViewModel displayHomeViewModel;
    private DisplayHomeView displayHomeView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the WeatherHomeView to the app.
     * @return the AppBuilder
     */
    public AppBuilder addWeatherHomeView() {
        displayHomeViewModel = new DisplayHomeViewModel();
        displayHomeView = new DisplayHomeView(displayHomeViewModel);
        cardPanel.add(displayHomeView, displayHomeView.getViewName());
        return this;
    }

    /**
     * Adds the Display Home Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final DisplayHomeOutputBoundary displayHomeOutputBoundary =
                new DisplayHomePresenter(displayHomeViewModel,
                        viewManagerModel);
        final DisplayHomeInteractor displayHomeInteractor =
                new DisplayHomeInteractor(
                weatherDataAccessObject, displayHomeOutputBoundary); // This
        // should be decoupled to the input interface, but it's okay for the
        // demo atm

        // No controller necessary for demo
        /*final DisplayHomeController controller =
                new DisplayHomeController(displayHomeInteractor);*/

        displayHomeView.setDisplayHomeController(controller);
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