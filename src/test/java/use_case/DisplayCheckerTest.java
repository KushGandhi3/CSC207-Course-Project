package use_case;

import exception.APICallException;
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
        DisplayCheckerInputData inputData = new DisplayCheckerInputData("Toronto", "Clear", 0, 1);

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

    // This is a helper method to create a list of HourWeatherData objects.
    private List<HourWeatherData> createHourWeatherDataList() {
        List<HourWeatherData> createHourWeatherDataList = new ArrayList<>();
        createHourWeatherDataList.add(new HourWeatherData() {
              @Override
              public int getTemperature() {return 0;}
              @Override
              public int getFeelsLikeTemperature() {return 0;}
              @Override
              public int getWindSpeed() {return 0;}
              @Override
              public int getUvIndex() {return 0;}
              @Override
              public int getCloudCover() {return 0;}
              @Override
              public int getHumidity() {return 0;}
              @Override
              public int getPrecipitation() {return 0;}
              @Override
              public String getCondition() {return "Clear";}
              @Override
              public String getCity() {return "";}
              @Override
              public String getTimezone() {return "";}
          }
        );
        return createHourWeatherDataList;
    }

    // helper method to create a DisplayCheckerDAI object with valid data.
    private DisplayCheckerDAI getDisplayCheckerDAI() {
        return location -> new HourlyWeatherData() {
            @Override
            public List<HourWeatherData> getHourWeatherDataList() {return createHourWeatherDataList();}
            @Override
            public int getLowTemperature() {return 0;}
            @Override
            public int getHighTemperature() {return 0;}
            @Override
            public String getCity() {return "";}
            @Override
            public String getTimezone() {return "";}
        };

    }

    // helper method to create a DisplayCheckerDAI object with no data.
    private DisplayCheckerDAI getEmptyDisplayCheckerDAI() {
        return location -> null;
    }

    // helper method to create a DisplayCheckerDAI with error API call.
    private DisplayCheckerDAI getAPIerrorDisplayCheckerDAI() {
        return location -> {
            throw new APICallException("API call failed.");
        };
    }
}


