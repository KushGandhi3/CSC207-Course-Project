package use_case;

import data_access.weather.InMemoryWeatherDAO;
import entity.weather.daily_weather.ConcreteDailyWeatherDataFactory;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.ConcreteHourlyWeatherData;
import entity.weather.hourly_weather.ConcreteHourlyWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import exception.APICallException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_checker.DisplayCheckerInputData;
import use_case.display_checker.DisplayCheckerInteractor;
import use_case.display_checker.DisplayCheckerOutputBoundary;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayCheckerInteractorTest {

    @Test
    void successConditionMetTest() {
        // input data
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("Toronto", "Rain", 0, 1);

        // DAI
        DisplayCheckerDAI displayCheckerDAI = getDisplayCheckerDAI();

        // Output boundary
        DisplayCheckerOutputBoundary successPresenter = new DisplayCheckerOutputBoundary() {
            @Override
            public void prepareCondMetView() {assertTrue(true);}
            @Override
            public void prepareCondNotMetView() {fail("Condition not met view unexpectedly called.");}
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

    private DisplayCheckerDAI getAPIerrorDisplayCheckerDAI() {
        return location -> {
            throw new APICallException("API call failed.");
        };
    }

    // This is a helper method to create a DisplayCheckerDAI object with data.
    private DisplayCheckerDAI getDisplayCheckerDAI() {
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

        InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory,
                dailyWeatherDataFactory,
                hourWeatherDataFactory,
                hourlyWeatherDataFactory);


        return location -> {
            try {
                return inMemoryWeatherDAO.getHourlyWeatherData("Toronto");
            } catch (APICallException e) {
                return null;
            }
        };
    }

    // This is a helper method to create a DisplayCheckerDAI object with no data.
    private DisplayCheckerDAI getEmptyDisplayCheckerDAI() {
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

        InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory,
                dailyWeatherDataFactory,
                hourWeatherDataFactory,
                hourlyWeatherDataFactory);


        return location -> {
            try {
                return inMemoryWeatherDAO.getHourlyWeatherData("Torontoww");
            } catch (APICallException | JSONException e) {
                return null;
            }
        };

    }
}


