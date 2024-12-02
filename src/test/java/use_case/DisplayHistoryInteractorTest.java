package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.display_history.*;
import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayHistoryInteractorTest {
    private DisplayHistoryDAI displayHistoryDAO;
    private List<String> cities;

    @BeforeEach
    void setUp() {
        // create a test implementation of DisplayHistoryDAI
        cities = new ArrayList<>();
        displayHistoryDAO = new DisplayHistoryDAI() {
            @Override
            public void addCity(String city) {
                // Remove if already exists to avoid duplicates
                cities.remove(city);
                // Add to the beginning of the list
                cities.addFirst(city);
            }

            @Override
            public RecentCityData getRecentCityData() throws RecentCitiesDataException {
                if (cities.isEmpty()) {
                    throw new RecentCitiesDataException("No recent cities");
                }
                // Instead of instantiating, return a list directly
                return new RecentCityData() {
                    @Override
                    public List<String> getRecentCityList() {
                        return new ArrayList<>(cities);
                    }
                };
            }
        };
    }

    @Test
    void testExecuteWithCity() {
        // input data
        DisplayHistoryInputData inputData = new DisplayHistoryInputData("Vancouver");

        // output boundary
        DisplayHistoryOutputBoundary successPresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                // Verify the city was added
                assertEquals(1, outputData.getCities().size());
                assertEquals("Vancouver", outputData.getCities().getFirst());
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not have failed");
            }

            @Override
            public void switchToHomeView() {
                fail("Should not have switched to home view");
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(displayHistoryDAO, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testExecuteWithoutCity() throws RecentCitiesDataException {
        // Prepare initial cities
        displayHistoryDAO.addCity("Toronto");
        displayHistoryDAO.addCity("Montreal");

        // output boundary
        DisplayHistoryOutputBoundary successPresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                // Verify the cities are retrieved correctly
                assertEquals(2, outputData.getCities().size());
                assertEquals("Montreal", outputData.getCities().get(0));
                assertEquals("Toronto", outputData.getCities().get(1));
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not have failed");
            }

            @Override
            public void switchToHomeView() {
                fail("Should not have switched to home view");
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(displayHistoryDAO, successPresenter);
        interactor.execute();
    }

    @Test
    void testDuplicateCity() throws RecentCitiesDataException {
        // Add initial cities
        displayHistoryDAO.addCity("Toronto");
        displayHistoryDAO.addCity("Montreal");

        // Add a duplicate city
        DisplayHistoryInputData inputData = new DisplayHistoryInputData("Toronto");

        // output boundary
        DisplayHistoryOutputBoundary successPresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                // Verify the duplicate city is moved to the top
                assertEquals(2, outputData.getCities().size());
                assertEquals("Toronto", outputData.getCities().get(0));
                assertEquals("Montreal", outputData.getCities().get(1));
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not have failed");
            }

            @Override
            public void switchToHomeView() {
                fail("Should not have switched to home view");
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(displayHistoryDAO, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testFailureScenario() {
        // create DAO with no cities
        DisplayHistoryDAI emptyDAO = new DisplayHistoryDAI() {
            @Override
            public void addCity(String city) {}

            @Override
            public RecentCityData getRecentCityData() throws RecentCitiesDataException {
                throw new RecentCitiesDataException("No recent cities");
            }
        };

        // output boundary
        DisplayHistoryOutputBoundary failurePresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                fail("Should not have succeeded");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("No recent cities", errorMessage);
            }

            @Override
            public void switchToHomeView() {
                fail("Should not have switched to home view");
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(emptyDAO, failurePresenter);
        interactor.execute();
    }

    @Test
    void testSwitchToHomeView() {
        // output boundary
        DisplayHistoryOutputBoundary homePresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                fail("Should not have succeeded");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                fail("Should not have failed");
            }

            @Override
            public void switchToHomeView() {
                assertTrue(true);
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(displayHistoryDAO, homePresenter);
        interactor.switchToHomeView();
    }

    @Test
    void testUnavailableCityHistory() {
        // create DAO that unable to get the recent cities
        DisplayHistoryDAI emptyDAO = new DisplayHistoryDAI() {
            @Override
            public void addCity(String city) {}

            @Override
            public RecentCityData getRecentCityData() throws RecentCitiesDataException {
                throw new RecentCitiesDataException("No recent cities");
            }
        };

        // output boundary
        DisplayHistoryOutputBoundary failurePresenter = new DisplayHistoryOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayHistoryOutputData outputData) {
                fail("Should not have succeeded");
            }

            @Override
            public void prepareFailureView(String errorMessage) {
                assertEquals("No recent cities", errorMessage);
            }

            @Override
            public void switchToHomeView() {
                fail("Should not have switched to home view");
            }
        };

        // interactor
        DisplayHistoryInteractor interactor = new DisplayHistoryInteractor(emptyDAO, failurePresenter);
        interactor.execute();
    }
}