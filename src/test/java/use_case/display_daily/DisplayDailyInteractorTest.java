package use_case.display_daily;

import constants.Constants;
import data_access.recent_city.RecentCitiesDAO;
import data_access.weather.InMemoryWeatherDAO;
import entity.recent_city.ConcreteRecentCityDataFactory;
import entity.recent_city.RecentCityData;
import entity.recent_city.RecentCityDataFactory;
import entity.weather.daily_weather.ConcreteDailyWeatherDataFactory;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.ConcreteHourlyWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import exception.APICallException;
import interface_adapter.display_daily.DisplayDailyPresenter;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_checker.DisplayCheckerInputData;
import use_case.display_checker.DisplayCheckerInteractor;
import use_case.display_checker.DisplayCheckerOutputBoundary;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisplayDailyInteractorTest {

    @Test
    void successConditionMetTest() {
        DisplayDailyWeatherDAI weatherDAO = getDisplayDailyWeatherDAO();
        DisplayDailyRecentCitiesDAI recentCitiesDAO = getDisplayDailyRecentCitiesDAO();

        // Output boundary
        DisplayDailyOutputBoundary presenter = new DisplayDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
                assertEquals("Toronto", displayDailyOutputData.getCity());

                List<String> actualWeekdays = displayDailyOutputData.getWeekdays();
                List<String> expectedWeekdays = new ArrayList<>();
                ZonedDateTime time = ZonedDateTime.now(ZoneId.of("America/New_York"));
                for (int i = 0; i < Constants.WEEK_SIZE; i++) {
                    expectedWeekdays.add(time.plusDays(i).getDayOfWeek().toString());
                }

                List<String> actualTemperatures = displayDailyOutputData.getTemperatures();

            }
            @Override
            public void prepareFailView(String errorMessage) {

            }
            @Override
            public void switchToHomeView() {

            }
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, presenter);
        interactor.execute(inputData);
    }

    @Test
    void failureConditionNotMetTest() {
        // input data
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("Toronto", "Clouds", 0, 1);

        // DAI
        DisplayCheckerDAI displayCheckerDAI = getDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {fail("Condition met view unexpectedly called.");}
            @Override
            public void prepareCondNotMetView() {assertTrue(true);}
            @Override
            public void prepareHomeView() {fail("Home view unexpectedly called.");}
            @Override
            public void prepareLocationEmptyView() {fail("Location empty view unexpectedly called.");}
            @Override
            public void prepareInvalidLocationView() {fail("Invalid location view unexpectedly called.");}
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToHomeViewTest() {
        // DAI
        DisplayCheckerDAI displayCheckerDAI = getDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {fail("Condition met view unexpectedly called.");}
            @Override
            public void prepareCondNotMetView() {fail("Condition not met view unexpectedly called.");}
            @Override
            public void prepareHomeView() {assertTrue(true);}
            @Override
            public void prepareLocationEmptyView() {fail("Location empty view unexpectedly called.");}
            @Override
            public void prepareInvalidLocationView() {fail("Invalid location view unexpectedly called.");}
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, successPresenter);
        interactor.switchToHomeView();
    }

    @Test
    void emptyLocationTest() {
        // input data
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("", "Clear", 0, 1);

        // DAI
        DisplayCheckerDAI displayCheckerDAI = getDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {fail("Condition met view unexpectedly called.");}
            @Override
            public void prepareCondNotMetView() {fail("Condition not met view unexpectedly called.");}
            @Override
            public void prepareHomeView() {fail("Home view unexpectedly called.");}
            @Override
            public void prepareLocationEmptyView() {assertTrue(true);}
            @Override
            public void prepareInvalidLocationView() {fail("Invalid location view unexpectedly called.");}
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void invalidLocationTest() {
        // input data
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("Torontowww", "Clear", 0, 1);

        // DAI
        DisplayCheckerDAI displayCheckerDAI = getEmptyDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {fail("Condition met view unexpectedly called.");}
            @Override
            public void prepareCondNotMetView() {fail("Condition not met view unexpectedly called.");}
            @Override
            public void prepareHomeView() {fail("Home view unexpectedly called.");}
            @Override
            public void prepareLocationEmptyView() {fail("Location empty view unexpectedly called.");}
            @Override
            public void prepareInvalidLocationView() {assertTrue(true);}
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void apiCallExceptionTest() {
        // input data
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("Toronto", "Clear", 0, 1);

        // DAI (simulate API call exception)
        DisplayCheckerDAI displayCheckerDAI = getAPIerrorDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {fail("Condition met view unexpectedly called.");}
            @Override
            public void prepareCondNotMetView() {fail("Condition not met view unexpectedly called.");}
            @Override
            public void prepareHomeView() {fail("Home view unexpectedly called.");}
            @Override
            public void prepareLocationEmptyView() {fail("Location empty view unexpectedly called.");}
            @Override
            public void prepareInvalidLocationView() {assertTrue(true);}
        };

        // Interactor
        DisplayCheckerInteractor interactor = new DisplayCheckerInteractor(displayCheckerDAI, successPresenter);
        interactor.execute(inputData);
    }

    /**
     * Helper for creating DisplayDailyRecentCitiesDAO's.
     */
    private DisplayDailyRecentCitiesDAI getDisplayDailyRecentCitiesDAO() {
        RecentCityDataFactory recentCityDataFactory = new ConcreteRecentCityDataFactory();

        return new RecentCitiesDAO(recentCityDataFactory);
    }

    /**
     * Helper for creating DisplayDailyWeatherDAO's.
     */
    private DisplayDailyWeatherDAI getDisplayDailyWeatherDAO() {
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

        return new InMemoryWeatherDAO(dayWeatherDataFactory, dailyWeatherDataFactory, hourWeatherDataFactory,
                hourlyWeatherDataFactory);
    }
}