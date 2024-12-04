package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
import interface_adapter.display_hourly.DisplayHourlyController;
import interface_adapter.display_hourly.DisplayHourlyPresenter;
import interface_adapter.display_hourly.DisplayHourlyViewModel;
import interface_adapter.display_summarization.DisplaySummarizationController;
import interface_adapter.display_summarization.DisplaySummarizationPresenter;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_checker.DisplayCheckerInputBoundary;
import use_case.display_checker.DisplayCheckerInteractor;
import use_case.display_checker.DisplayCheckerOutputBoundary;
import use_case.display_daily.DisplayDailyInputBoundary;
import use_case.display_daily.DisplayDailyInteractor;
import use_case.display_daily.DisplayDailyOutputBoundary;
import use_case.display_daily.DisplayDailyRecentCitiesDAI;
import use_case.display_daily.DisplayDailyWeatherDAI;
import use_case.display_history.DisplayHistoryDAI;
import use_case.display_history.DisplayHistoryInputBoundary;
import use_case.display_history.DisplayHistoryInteractor;
import use_case.display_history.DisplayHistoryOutputBoundary;
import use_case.display_home.DisplayHomeInputBoundary;
import use_case.display_home.DisplayHomeInteractor;
import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeRecentCitiesDAI;
import use_case.display_home.DisplayHomeWeatherDAI;
import use_case.display_hourly.DisplayHourlyInputBoundary;
import use_case.display_hourly.DisplayHourlyInteractor;
import use_case.display_hourly.DisplayHourlyOutputBoundary;
import use_case.display_hourly.DisplayHourlyRecentCitiesDAI;
import use_case.display_hourly.DisplayHourlyWeatherDAI;
import use_case.display_summarization.DisplaySummarizationInputBoundary;
import use_case.display_summarization.DisplaySummarizationInteractor;
import use_case.display_summarization.DisplaySummarizationOutputBoundary;
import use_case.display_summarization.DisplaySummarizationRecentCitiesDAI;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;
import use_case.display_summarization.DisplaySummarizationWeatherDAI;
import view.CheckerView;
import view.DailyView;
import view.HistoryView;
import view.HomeView;
import view.HourlyView;
import view.SummarizationView;

/**
 * The AppBuilder class is responsible for building the application.
 */
public class AppBuilder {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private final JPanel cardPanel = new JPanel();

    private final RecentCityDataFactory recentCityDataFactory = new ConcreteRecentCityDataFactory();
    private final DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
    private final DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
    private final HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();
    private final HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
    private final SummarizationFactory summarizationFactory = new ConcreteSummarizationFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

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
    private final DisplaySummarizationRecentCitiesDAI displaySummarizationRecentCitiesDAO = new
            RecentCitiesDAO(recentCityDataFactory);
    private final DisplaySummarizationWeatherDAI displaySummarizationWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
    private final DisplaySummarizationSummaryDAI displaySummarizationSummaryDAO =
            new SummarizationSummaryDAO(summarizationFactory);
    // history DAI's
    private final DisplayHistoryDAI displayHistoryDAO = new RecentCitiesDAO(recentCityDataFactory);
    // hourly DAI's
    private final DisplayHourlyRecentCitiesDAI displayHourlyRecentCitiesDAO =
            new RecentCitiesDAO(recentCityDataFactory);
    private final DisplayHourlyWeatherDAI displayHourlyWeatherDAO = new WeatherDAO(dayWeatherDataFactory,
            dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);

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

    private HourlyView hourlyView;
    private DisplayHourlyViewModel displayHourlyViewModel;

    public AppBuilder() {
        final CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Daily View to the application.
     * @return this builder
     */
    public AppBuilder addDailyView() {
        displayDailyViewModel = new DisplayDailyViewModel();
        dailyView = new DailyView(displayDailyViewModel);
        cardPanel.add(dailyView, dailyView.getViewName());
        return this;
    }

    /**
     * Adds the Checker View to the application.
     * @return this builder
     */
    public AppBuilder addCheckerView() {
        displayCheckerViewModel = new DisplayCheckerViewModel();
        checkerView = new CheckerView(displayCheckerViewModel);
        cardPanel.add(checkerView, checkerView.getViewName());
        return this;
    }

    /**
     * Adds the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        displayHomeViewModel = new DisplayHomeViewModel();
        homeView = new HomeView(displayHomeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    /**
     * Adds the Summarization View to the application.
     * @return this builder
     */
    public AppBuilder addSummarizationView() {
        displaySummarizationViewModel = new DisplaySummarizationViewModel();
        summarizationView = new SummarizationView(displaySummarizationViewModel);
        cardPanel.add(summarizationView, summarizationView.getViewName());
        return this;
    }

    /**
     * Adds the History View to the application.
     * @return this builder
     */
    public AppBuilder addHistoryView() {
        displayHistoryViewModel = new DisplayHistoryViewModel();
        historyView = new HistoryView(displayHistoryViewModel);
        cardPanel.add(historyView, historyView.getViewName());
        return this;
    }

    /**
     * Adds the Hourly View to the application.
     * @return this builder
     */
    public AppBuilder addHourlyView() {
        displayHourlyViewModel = new DisplayHourlyViewModel();
        hourlyView = new HourlyView(displayHourlyViewModel);
        cardPanel.add(hourlyView, hourlyView.getViewName());
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

        final DisplayDailyController displayDailyController = new DisplayDailyController(displayDailyInteractor);
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

        final DisplayCheckerController displayCheckerController = new
                DisplayCheckerController(displayCheckerInteractor);
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

        final DisplaySummarizationController displaySummarizationController =
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

        final DisplayHistoryController displayHistoryController =
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
                        displayHistoryViewModel, displayDailyViewModel, displayCheckerViewModel);
        final DisplayHomeInputBoundary displayHomeInteractor = new DisplayHomeInteractor(displayHomeWeatherDAO,
                displayHomePresenter, displayHomeRecentCitiesDAO);

        final DisplayHomeController displayHomeController =
                new DisplayHomeController(displayHomeInteractor);
        homeView.setDisplayHomeController(displayHomeController);
        return this;
    }

    /**
     * Adds the Display Hourly Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDisplayHourlyUseCase() {
        final DisplayHourlyOutputBoundary displayHourlyPresenter =
                new DisplayHourlyPresenter(displayHourlyViewModel, displayHomeViewModel, viewManagerModel);
        final DisplayHourlyInputBoundary displayHourlyInteractor =
                new DisplayHourlyInteractor(displayHourlyRecentCitiesDAO, displayHourlyWeatherDAO,
                displayHourlyPresenter);

        final DisplayHourlyController displayHourlyController =
                new DisplayHourlyController(displayHourlyInteractor);
        hourlyView.setDisplayHourlyController(displayHourlyController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Home View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Wisely Wear");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(WIDTH, HEIGHT);
        application.setResizable(false);

        application.add(cardPanel);

        // set Home View as default view
        viewManagerModel.setState(homeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
