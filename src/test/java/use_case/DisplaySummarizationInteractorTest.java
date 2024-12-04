package use_case;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import data_access.summarization.InMemorySummarizationDAO;
import data_access.weather.InMemoryWeatherDAO;
import entity.recent_city.RecentCityData;
import entity.summarization.ConcreteSummarizationFactory;
import entity.summarization.Summarization;
import entity.summarization.SummarizationFactory;
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
import exception.RecentCitiesDataException;
import use_case.display_summarization.DisplaySummarizationInteractor;
import use_case.display_summarization.DisplaySummarizationOutputBoundary;
import use_case.display_summarization.DisplaySummarizationOutputData;
import use_case.display_summarization.DisplaySummarizationRecentCitiesDAI;
import use_case.display_summarization.DisplaySummarizationSummaryDAI;
import use_case.display_summarization.DisplaySummarizationWeatherDAI;

public class DisplaySummarizationInteractorTest {

    @Test
    void testSuccessExecution() {
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> (RecentCityData) () -> List.of("Toronto");

        final DisplaySummarizationWeatherDAI weatherDAO = city -> {
            final DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
            final DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
            final HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
            final HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

            final InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(
                    dayWeatherDataFactory, dailyWeatherDataFactory, hourWeatherDataFactory,
                    hourlyWeatherDataFactory);

            return inMemoryWeatherDAO.getHourlyWeatherData(city);
        };

        final DisplaySummarizationSummaryDAI summaryDAO = prompt -> {
            final SummarizationFactory summarizationFactory = new ConcreteSummarizationFactory();
            final InMemorySummarizationDAO summarizationDAO = new InMemorySummarizationDAO(summarizationFactory);

            return summarizationDAO.getSummarization(prompt);
        };

        final DisplaySummarizationInteractor interactor = getDisplaySummarizationInteractor(recentCitiesDAO,
                weatherDAO, summaryDAO);

        interactor.execute();
    }

    @NotNull
    private static DisplaySummarizationInteractor getDisplaySummarizationInteractor(
            DisplaySummarizationRecentCitiesDAI recentCitiesDAO, DisplaySummarizationWeatherDAI weatherDAO,
            DisplaySummarizationSummaryDAI summaryDAO) {
        final DisplaySummarizationOutputBoundary displaySummarizationPresenter = new
                DisplaySummarizationOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplaySummarizationOutputData outputData) {
                assertEquals("The weather in Toronto is expected to be cloudy throughout the day with "
                        + "temperatures fluctuating between -3 and 1 degree Celsius.", outputData.getWeather());
                assertEquals("Considering the cold temperatures and cloudy conditions, it is advisable to "
                        + "wear warm clothing, including a thick coat, gloves and a hat.", outputData.getOutfit());
                assertEquals("The roads might be icy due to low temperatures so please exercise caution "
                        + "while commuting. If possible, avoid driving during peak hours to minimize "
                        + "travel delays.", outputData.getTravel());
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not reach failure view in success case.");
            }

            @Override
            public void switchToHomeView() {
                // No operation needed for this test
            }
        };

        return new DisplaySummarizationInteractor(recentCitiesDAO,
                weatherDAO, summaryDAO, displaySummarizationPresenter);
    }

    @Test
    void testNoRecentCities() {
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> {
            throw new RecentCitiesDataException("Unable to retrieve recent cities data.");
        };

        final DisplaySummarizationWeatherDAI weatherDAO = city -> {
            fail("Weather DAO should not be called when RecentCitiesDataException is thrown.");
            return null;
        };

        final DisplaySummarizationSummaryDAI summaryDAO = prompt -> {
            fail("Summary DAO should not be called when RecentCitiesDataException is thrown.");
            return null;
        };

        final DisplaySummarizationInteractor interactor = getSummarizationInteractor("Should not reach "
                + "success view when RecentCitiesDataException is thrown.", "Unable to retrieve recent"
                + " cities data.", recentCitiesDAO, weatherDAO, summaryDAO);

        interactor.execute();
    }

    @NotNull
    private static DisplaySummarizationInteractor
        getSummarizationInteractor(DisplaySummarizationRecentCitiesDAI recentCitiesDAO,
                               DisplaySummarizationWeatherDAI weatherDAO, DisplaySummarizationSummaryDAI summaryDAO) {
        final DisplaySummarizationOutputBoundary presenter = new DisplaySummarizationOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplaySummarizationOutputData outputData) {
                assertEquals("Mock summary", outputData.getWeather());
                assertEquals("Mock outfit suggestion", outputData.getOutfit());
                assertEquals("Mock travel advice", outputData.getTravel());
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not reach failure view in this test.");
            }

            @Override
            public void switchToHomeView() {
                // Not relevant for this test
            }
        };

        // Instantiate the interactor
        return new DisplaySummarizationInteractor(
                recentCitiesDAO, weatherDAO, summaryDAO, presenter
        );
    }

    @NotNull
    private static DisplaySummarizationInteractor getSummarizationInteractor(
            String message,
            String expected,
            DisplaySummarizationRecentCitiesDAI recentCitiesDAO,
            DisplaySummarizationWeatherDAI weatherDAO,
            DisplaySummarizationSummaryDAI summaryDAO) {
        // method implementation
        final DisplaySummarizationOutputBoundary presenter = new DisplaySummarizationOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplaySummarizationOutputData outputData) {
                fail(message);
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals(expected, errorMessage);
            }

            @Override
            public void switchToHomeView() {
                // Not relevant for this test
            }
        };

        return new DisplaySummarizationInteractor(
                recentCitiesDAO, weatherDAO, summaryDAO, presenter);
    }

    @Test
    void testWeatherDataUnavailable() {
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> (RecentCityData) () -> List.of("Toronto");

        final DisplaySummarizationWeatherDAI weatherDAO = city -> {
            throw new APICallException("Weather data unavailable");
        };

        final DisplaySummarizationInteractor interactor = getSummarizationInteractor("Should not "
                + "reach success view when APICallException is thrown.",
                "Weather Data Unavailable.", recentCitiesDAO, weatherDAO, null);

        interactor.execute();
    }

    @Test
    void testSummarizationServiceUnavailable() {
        // DAO for Recent Cities returning a valid city
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> (RecentCityData) () -> List.of("Toronto");

        // DAO for Weather Data returning valid weather data
        final DisplaySummarizationWeatherDAI weatherDAO = city -> {
            final DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
            final DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
            final HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
            final HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

            final InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(
                    dayWeatherDataFactory, dailyWeatherDataFactory, hourWeatherDataFactory,
                    hourlyWeatherDataFactory);

            return inMemoryWeatherDAO.getHourlyWeatherData("Toronto");
        };

        // DAO for Summarization throwing an APICallException
        final DisplaySummarizationSummaryDAI summaryDAO = prompt -> {
            throw new APICallException("Summarization service unavailable");
        };

        // Mock Presenter to capture the failure view
        final DisplaySummarizationInteractor interactor = getSummarizationInteractor("Should not call "
                + "prepareSuccessView in this test.", "Summarization Service Unavailable.",
                recentCitiesDAO, weatherDAO, summaryDAO);

        // Execute the interactor logic
        interactor.execute();
    }

    @Test
    void testNoHourlyForecastDataAvailable() {
        // DAO for Recent Cities returning a valid city
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> (RecentCityData) () -> List.of("Toronto");

        // DAO for Weather Data returning valid weather data
        final DisplaySummarizationWeatherDAI weatherDAO = city -> new ConcreteHourlyWeatherData(
                List.of(),
                "America/Toronto",
                city,
                -5,
                -1
        );

        // DAO for Summarization to capture the generated prompt
        final DisplaySummarizationSummaryDAI summaryDAO = prompt -> {
            // Assert that the prompt includes the "No hourly forecast data available." message
            assertTrue(prompt.contains("No hourly forecast data available."),
                    "The prompt should include 'No hourly forecast data available.'");
            // Return a mock summarization object to avoid further errors
            return new Summarization() {
                @Override
                public String getWeatherSummary() {
                    return "Mock summary";
                }

                @Override
                public String getOutfitSuggestion() {
                    return "Mock outfit suggestion";
                }

                @Override
                public String getTravelAdvice() {
                    return "Mock travel advice";
                }
            };
        };

        // Mock Presenter to handle the success view
        final DisplaySummarizationInteractor interactor = getSummarizationInteractor(recentCitiesDAO,
                weatherDAO, summaryDAO);

        // Execute the interactor logic
        interactor.execute();
    }

    @Test
    void testNullHourlyForecastData() {
        // DAO for Recent Cities returning a valid city
        final DisplaySummarizationRecentCitiesDAI recentCitiesDAO = () -> (RecentCityData) () -> List.of("Toronto");

        // DAO for Weather Data returning null hourly forecast data
        final DisplaySummarizationWeatherDAI weatherDAO = city -> new ConcreteHourlyWeatherData(
                null,
                "America/Toronto",
                city,
                -5,
                -1
        );

        // DAO for Summarization to capture the generated prompt
        final DisplaySummarizationSummaryDAI summaryDAO = prompt -> {
            // Assert that the prompt includes the "No hourly forecast data available." message
            assertTrue(prompt.contains("No hourly forecast data available."),
                    "The prompt should include 'No hourly forecast data available.'");
            // Return a mock summarization object to avoid further errors
            return new Summarization() {
                @Override
                public String getWeatherSummary() {
                    return "Mock summary";
                }

                @Override
                public String getOutfitSuggestion() {
                    return "Mock outfit suggestion";
                }

                @Override
                public String getTravelAdvice() {
                    return "Mock travel advice";
                }
            };
        };

        // Mock Presenter to handle the success view
        final DisplaySummarizationInteractor interactor = getSummarizationInteractor(recentCitiesDAO,
                weatherDAO, summaryDAO);

        // Execute the interactor logic
        interactor.execute();
    }

    @Test
    void testSwitchToHomeView() {
        // Mock Presenter to capture the switch to home view call
        class MockPresenter implements DisplaySummarizationOutputBoundary {
            private boolean switchToHomeViewCalled;

            @Override
            public void prepareSuccessView(DisplaySummarizationOutputData outputData) {
                fail("Should not call prepareSuccessView in this test.");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not call prepareFailureView in this test.");
            }

            @Override
            public void switchToHomeView() {
                switchToHomeViewCalled = true;
            }

            public boolean wasSwitchToHomeViewCalled() {
                return switchToHomeViewCalled;
            }
        }

        // Instantiate the mock presenter
        final MockPresenter presenter = new MockPresenter();

        // Instantiate the interactor with the mock presenter
        final DisplaySummarizationInteractor interactor = new DisplaySummarizationInteractor(
                null, null, null, presenter
        );

        // Call switchToHomeView
        interactor.switchToHomeView();

        // Assert that switchToHomeView was called
        assertTrue(presenter.wasSwitchToHomeViewCalled(),
                "switchToHomeView should have been called on the presenter.");
    }
}
