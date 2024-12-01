package app;

import data_access.recent_city.RecentCitiesDAO;
import data_access.summarization.SummarizationSummaryDAO;
import data_access.weather.WeatherDAO;
import entity.recent_city.ConcreteRecentCityDataFactory;
import entity.recent_city.RecentCityDataFactory;
import entity.summarization.ConcreteSummarizationFactory;
import entity.summarization.SummarizationFactory;
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
import interface_adapter.display_daily.DisplayDailyController;
import interface_adapter.display_daily.DisplayDailyPresenter;
import interface_adapter.display_daily.DisplayDailyViewModel;
import interface_adapter.display_history.DisplayHistoryController;
import interface_adapter.display_history.DisplayHistoryPresenter;
import interface_adapter.display_history.DisplayHistoryViewModel;
import interface_adapter.display_home.DisplayHomeController;
import interface_adapter.display_home.DisplayHomePresenter;
import interface_adapter.display_home.DisplayHomeViewModel;
import interface_adapter.display_summarization.DisplaySummarizationController;
import interface_adapter.display_summarization.DisplaySummarizationPresenter;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_checker.DisplayCheckerInputBoundary;
import use_case.display_checker.DisplayCheckerInteractor;
import use_case.display_checker.DisplayCheckerOutputBoundary;
import use_case.display_daily.*;
import use_case.display_history.DisplayHistoryDAI;
import use_case.display_history.DisplayHistoryInputBoundary;
import use_case.display_history.DisplayHistoryInteractor;
import use_case.display_history.DisplayHistoryOutputBoundary;
import use_case.display_home.*;
import use_case.display_summarization.*;
import view.*;

import javax.swing.*;
import java.awt.*;

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
    // the default city to display information for
    private final String DEFAULT_CITY = "Toronto";

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final RecentCityDataFactory recentCityDataFactory = new ConcreteRecentCityDataFactory();
    private final DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
    private final DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
    private final HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();
    private final HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
    private final SummarizationFactory summarizationFactory = new ConcreteSummarizationFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // daily DAI's
    private final DisplayDailyRecentCitiesDAI displayDailyRecentCitiesDAO = new RecentCitiesDAO(recentCityDataFactory);
    private final DisplayDailyWeatherDAI displayDailyWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    // checker DAI's
    private final DisplayCheckerDAI displayCheckerWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    // home DAI's
    private final DisplayHomeRecentCitiesDAI displayHomeRecentCitiesDAO = new RecentCitiesDAO(recentCityDataFactory);
    private final DisplayHomeWeatherDAI displayHomeWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    // summarization DAI's
    private final DisplaySummarizationRecentCitiesDAI displaySummarizationRecentCitiesDAO = new RecentCitiesDAO(recentCityDataFactory);
    private final DisplaySummarizationWeatherDAI displaySummarizationWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    private final DisplaySummarizationSummaryDAI displaySummarizationSummaryDAO =
            new SummarizationSummaryDAO(summarizationFactory);
    // history DAI's
    private final DisplayHistoryDAI displayHistoryDAO = new RecentCitiesDAO(recentCityDataFactory);

    private DailyView dailyView;
    private DisplayDailyViewModel displayDailyViewModel;

    private CheckerView checkerView;
    private DisplayCheckerViewModel displayCheckerViewModel;

    private HomeView homeView;
    private DisplayHomeViewModel displayHomeViewModel;

    private SummarizationView summarizationView;
    private DisplaySummarizationViewModel displaySummarizationViewModel;

    private HistoryView historyView;
    private DisplayHistoryViewModel displayHistoryViewModel;

    // controllers
    private DisplayHomeController displayHomeController;
    private DisplayDailyController displayDailyController;
    private DisplayCheckerController displayCheckerController;
    private DisplaySummarizationController displaySummarizationController;
    private DisplayHistoryController displayHistoryController;

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
     * Adds the Home View to the application
     * @return this builder
     */
    public AppBuilder addHomeView() {
        displayHomeViewModel = new DisplayHomeViewModel();
        homeView = new HomeView(displayHomeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    /**
     * Adds the Summarization View to the application
     * @return this builder
     */
    public AppBuilder addSummarizationView() {
        displaySummarizationViewModel = new DisplaySummarizationViewModel();
        summarizationView = new SummarizationView(displaySummarizationViewModel);
        cardPanel.add(summarizationView, summarizationView.getViewName());
        return this;
    }

    /**
     * Adds the History View to the application
     * @return this builder
     */
    public AppBuilder addHistoryView() {
        displayHistoryViewModel = new DisplayHistoryViewModel();
        historyView = new HistoryView(displayHistoryViewModel);
        cardPanel.add(historyView, historyView.getViewName());
        return this;
    }

    /**
     * Adds the Display Daily Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayDailyUseCase() {
        final DisplayDailyOutputBoundary displayDailyPresenter =
                new DisplayDailyPresenter(displayDailyViewModel, displayHomeViewModel, viewManagerModel);
        final DisplayDailyInputBoundary displayDailyInteractor =
                new DisplayDailyInteractor(displayDailyRecentCitiesDAO, displayDailyWeatherDAO,
                        displayDailyPresenter);

        displayDailyController = new DisplayDailyController(displayDailyInteractor);
        dailyView.setDisplayDailyController(displayDailyController);
        return this;
    }

    /**
     * Adds the Display Daily Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayCheckerUseCase() {
        final DisplayCheckerOutputBoundary displayCheckerPresenter = new DisplayCheckerPresenter(
                displayCheckerViewModel, displayHomeViewModel, viewManagerModel);
        final DisplayCheckerInputBoundary displayCheckerInteractor = new DisplayCheckerInteractor(
                displayCheckerWeatherDAO, displayCheckerPresenter);

        displayCheckerController = new DisplayCheckerController(displayCheckerInteractor);
        checkerView.setCheckerController(displayCheckerController);
        return this;
    }

    /**
     * Adds the Display Summarization Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplaySummarizationUseCase() {
        final DisplaySummarizationOutputBoundary displaySummarizationPresenter =
                new DisplaySummarizationPresenter(displaySummarizationViewModel,
                        displayHomeViewModel, viewManagerModel);
        final DisplaySummarizationInputBoundary displaySummarizationInteractor =
                new DisplaySummarizationInteractor(displaySummarizationRecentCitiesDAO, displaySummarizationWeatherDAO,
                        displaySummarizationSummaryDAO, displaySummarizationPresenter);

        displaySummarizationController =
                new DisplaySummarizationController(displaySummarizationInteractor);
        summarizationView.setController(displaySummarizationController);
        return this;
    }

    /**
     * Adds the Display History Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayHistoryUseCase() {
        final DisplayHistoryOutputBoundary displayHistoryPresenter =
                new DisplayHistoryPresenter(displayHistoryViewModel, displayHomeViewModel, viewManagerModel);
        final DisplayHistoryInputBoundary displayHistoryInteractor =
                new DisplayHistoryInteractor(displayHistoryDAO, displayHistoryPresenter);

        displayHistoryController =
                new DisplayHistoryController(displayHistoryInteractor);
        historyView.setController(displayHistoryController);
        return this;
    }

    /**
     * Adds the Display Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayHomeUseCase() {
        final DisplayHomeOutputBoundary displayHomePresenter =
                new DisplayHomePresenter(viewManagerModel, displayHomeViewModel, displaySummarizationViewModel,
                        displayHistoryViewModel, displayDailyController,
                        displayDailyViewModel, displayCheckerViewModel);
        final DisplayHomeInputBoundary displayHomeInteractor = new DisplayHomeInteractor(displayHomeWeatherDAO,
                displayHomePresenter, displayHomeRecentCitiesDAO);

        displayHomeController =
                new DisplayHomeController(displayHomeInteractor);
        homeView.setDisplayHomeController(displayHomeController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Home View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Wisely Wear");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1200, 800);
        application.setResizable(false);

        application.add(cardPanel);

        // set immediate values to display with a default city
        displayHomeController.execute(DEFAULT_CITY);

        // set Home View as default view
        viewManagerModel.setState(homeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
