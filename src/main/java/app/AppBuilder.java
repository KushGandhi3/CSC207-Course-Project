package app;

import javax.swing.*;
import java.awt.*;

// Daily Page
import data_access.recent_city.RecentCitiesDAO;
import data_access.weather.WeatherDAO;
import entity.recent_city.ConcreteRecentCityDataFactory;
import entity.recent_city.RecentCityDataFactory;
import entity.weather.daily_weather.ConcreteDailyWeatherDataFactory;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.ConcreteHourlyWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_checker.DisplayCheckerController;
import interface_adapter.display_checker.DisplayCheckerPresenter;
import interface_adapter.display_checker.DisplayCheckerViewModel;
import interface_adapter.display_home.DisplayHomeViewModel;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_checker.DisplayCheckerInputBoundary;
import use_case.display_checker.DisplayCheckerInteractor;
import use_case.display_checker.DisplayCheckerOutputBoundary;
import use_case.display_daily.DisplayDailyWeatherDAI;
import use_case.display_daily.DisplayDailyRecentCitiesDAI;
import use_case.display_daily.DisplayDailyInteractor;
import use_case.display_daily.DisplayDailyInputBoundary;
import use_case.display_daily.DisplayDailyOutputBoundary;
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyPresenter;
import interface_adapter.display_daily.DisplayDailyViewModel;
import view.CheckerView;
import view.DailyView;
import view.ViewManager;

//// Weekly Page
//import use_case.display_weekly.DisplayWeeklyDataAccessInterface;
//import use_case.display_weekly.DisplayWeeklyInteractor;
//import use_case.display_weekly.DisplayWeeklyInputBoundary;
//import use_case.display_weekly.DisplayWeeklyOutputBoundary;
//import interface_adapter.display_weekly.DisplayWeeklyController;
//import interface_adapter.display_weekly.DisplayWeeklyPresenter;
//import interface_adapter.display_weekly.DisplayWeeklyState;
//import interface_adapter.display_weekly.DisplayWeeklyViewModel;
//import view.WeeklyView;
//
//// Alerts Page
//import use_case.set_alerts.SetAlertsDataAccessInterface;
//import use_case.set_alerts.SetAlertsInteractor;
//import use_case.set_alerts.SetAlertsInputBoundary;
//import use_case.set_alerts.SetAlertsOutputBoundary;
//import interface_adapter.set_alerts.SetAlertController;
//import interface_adapter.set_alerts.SetAlertPresenter;
//import interface_adapter.set_alerts.SetAlertState;
//import interface_adapter.set_alerts.SetAlertViewModel;
//import view.SetAlertsView;
//
//// Outfit Page
//import use_case.analyze_outfit.AnalyzeOutfitDataAccessInterface;
//import use_case.analyze_outfit.AnalyzeOutfitInteractor;
//import use_case.analyze_outfit.AnalyzeOutfitInputBoundary;
//import use_case.analyze_outfit.AnalyzeOutfitOutputBoundary;
//import interface_adapter.analyze_outfit.AnalyzeOutfitController;
//import interface_adapter.analyze_outfit.AnalyzeOutfitPresenter;
//import interface_adapter.analyze_outfit.AnalyzeOutfitState;
//import interface_adapter.analyze_outfit.AnaylzeOutfitViewModel;
//import view.AnalyzeOutfitView;
//

/**
 * The AppBuilder class is responsible for building the application.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final RecentCityDataFactory recentCityDataFactory = new ConcreteRecentCityDataFactory();
    private final DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
    private final DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
    private final HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();
    private final HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DisplayDailyRecentCitiesDAI recentCitiesDAO = new RecentCitiesDAO(recentCityDataFactory);
    private final DisplayDailyWeatherDAI displayDailyWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    private final DisplayCheckerDAI displayCheckerWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);

    private DailyView dailyView;
    private DisplayDailyViewModel displayDailyViewModel;

    private CheckerView checkerView;
    private DisplayCheckerViewModel displayCheckerViewModel;

    private DisplayHomeViewModel displayHomeViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Daily View to the application
     * @return this builder
     */
    public AppBuilder addDailyView() {
        displayDailyViewModel = new DisplayDailyViewModel();
        dailyView = new DailyView(displayDailyViewModel);
        cardPanel.add(dailyView, dailyView.getViewName());
        return this;
    }

    /**
     * Adds the Display Daily Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayDailyUseCase() {
        final DisplayDailyOutputBoundary displayDailyPresenter = new DisplayDailyPresenter(displayDailyViewModel,
                displayHomeViewModel, viewManagerModel);
        final DisplayDailyInputBoundary displayDailyInteractor = new DisplayDailyInteractor(recentCitiesDAO, displayDailyWeatherDAO,
                displayDailyPresenter);

        final DisplayDailyController controller = new DisplayDailyController(displayDailyInteractor);
        dailyView.setDisplayDailyController(controller);
        return this;
    }

    /**
     * Adds the Checker View to the application
     * @return this builder
     */
    public AppBuilder addCheckerView() {
        displayCheckerViewModel = new DisplayCheckerViewModel();
        checkerView = new CheckerView(displayCheckerViewModel);
        cardPanel.add(checkerView, checkerView.getViewName());
        return this;
    }

    /**
     * Adds the Display Daily Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayCheckerUseCase() {
        final DisplayCheckerOutputBoundary displayCheckerPresenter = new DisplayCheckerPresenter(
                displayCheckerViewModel, viewManagerModel);
        final DisplayCheckerInputBoundary displayCheckerInteractor = new DisplayCheckerInteractor(
                displayCheckerWeatherDAO, displayCheckerPresenter);

        final DisplayCheckerController controller = new DisplayCheckerController(displayCheckerInteractor);
        checkerView.setCheckerController(controller);
        return this;
    }

    // TODO: set default view to be home
    /**
     * Creates the JFrame for the application and initially sets the Daily View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Wisely Wear");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1200, 800);
        application.setResizable(false);

        application.add(cardPanel);

        // set **Daily View** as default view
        viewManagerModel.setState(dailyView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
