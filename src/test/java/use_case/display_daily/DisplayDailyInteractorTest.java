package use_case.display_daily;

import constants.Constants;
import entity.recent_city.RecentCityData;
import entity.weather.daily_weather.ConcreteDailyWeatherDataFactory;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.day_weather.DayWeatherData;
import exception.ApiCallException;
import exception.RecentCitiesDataException;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.ConcreteHourlyWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data_access.weather.InMemoryWeatherDAO;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisplayDailyInteractorTest {
    private DisplayDailyRecentCitiesDAI displayDailyRecentCitiesDAO;
    private DisplayDailyWeatherDAI displayDailyWeatherDAO;
    private List<String> cities;

    @BeforeEach
    void setUpWeatherData() {
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();

        InMemoryWeatherDAO weatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory, dailyWeatherDataFactory,
                hourWeatherDataFactory, hourlyWeatherDataFactory);
        displayDailyWeatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory, dailyWeatherDataFactory,
                hourWeatherDataFactory, hourlyWeatherDataFactory) {
            @Override
            public DailyWeatherData getDailyWeatherData(String city) throws ApiCallException {
                DailyWeatherData dailyWeatherData = weatherDAO.getDailyWeatherData(city);
                List<DayWeatherData> dayWeatherDataList = weatherDAO.getDailyWeatherData(city).getDayWeatherDataList();
                return new DailyWeatherData() {
                    @Override
                    public List<DayWeatherData> getDayWeatherDataList() {
                        return dayWeatherDataList;
                    }

                    @Override
                    public String getCity() {
                        return city;
                    }

                    @Override
                    public String getTimezone() {
                        return dailyWeatherData.getTimezone();
                    }
                };
            }
        };
    }

    @BeforeEach
    void setUpRecentCitiesData() {
        cities = new ArrayList<>();
        displayDailyRecentCitiesDAO = new DisplayDailyRecentCitiesDAI() {
            @Override
            public void addCity(String city) throws RecentCitiesDataException {
                // Remove if already exists to avoid duplicates
                cities.remove(city);
                // Add to the beginning of the list
                cities.addFirst(city);
            }

            @Override
            public RecentCityData getRecentCityData() throws RecentCitiesDataException {
                if (cities.isEmpty()) {
                    throw new RecentCitiesDataException("No Cities To Display.");
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
    void displayDailyTest() {
        try {
            // add "Toronto" to the RecentCitiesData
            displayDailyRecentCitiesDAO.addCity("Toronto");

            // Output boundary
            DisplayDailyOutputBoundary presenter = new DisplayDailyOutputBoundary() {
                @Override
                public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
                    assertEquals("Toronto", displayDailyOutputData.getCity());

                    List<String> expectedWeekdays = new ArrayList<>();
                    ZonedDateTime time = ZonedDateTime.now(ZoneId.of("America/New_York"));
                    for (int i = 0; i < Constants.WEEK_SIZE; i++) {
                        expectedWeekdays.add(time.plusDays(i).getDayOfWeek().toString());
                    }
                    List<String> actualWeekdays = displayDailyOutputData.getWeekdays();

                    List<String> expectedTemperatures = List.of("7°C", "4°C", "4°C", "5°C", "3°C", "1°C", "1°C");
                    List<String> actualTemperatures = displayDailyOutputData.getTemperatures();

                    List<String> expectedConditions = List.of("Rain", "Rain", "Clouds", "Clouds", "Clouds", "Clouds", "Snow");
                    List<String> actualConditions = displayDailyOutputData.getConditions();

                    // test all list objects
                    for (int i = 0; i < Constants.WEEK_SIZE; i++) {
                        assertEquals(expectedWeekdays.get(i), actualWeekdays.get(i));
                        assertEquals(expectedTemperatures.get(i), actualTemperatures.get(i));
                        assertEquals(expectedConditions.get(i), actualConditions.get(i));
                    }

                    assertEquals("5°C", displayDailyOutputData.getFeelsLikeTemperature());
                    assertEquals("1", displayDailyOutputData.getUvIndex());
                    assertEquals("3 m/s", displayDailyOutputData.getWindSpeed());
                    assertEquals("100%", displayDailyOutputData.getCloudCover());
                    assertEquals("100%", displayDailyOutputData.getPrecipitation());
                    assertEquals("57%", displayDailyOutputData.getHumidity());
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    fail();
                }

                @Override
                public void switchToHomeView() {
                    fail();
                }
            };

            // Interactor
            DisplayDailyInteractor interactor = new DisplayDailyInteractor(displayDailyRecentCitiesDAO,
                    displayDailyWeatherDAO, presenter);
            final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
            final String selectedWeekday = zonedDateTime.getDayOfWeek().toString();

            DisplayDailyInputData inputData = new DisplayDailyInputData(selectedWeekday);

            interactor.execute(inputData);
            interactor.execute();
        } catch(RecentCitiesDataException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void displayDailyRecentCitiesFailureTest() {
        // Output boundary
        DisplayDailyOutputBoundary presenter = new DisplayDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Weather Data Unavailable.", errorMessage);
            }

            @Override
            public void switchToHomeView() {
                fail();
            }
        };
        DisplayDailyInteractor interactor = new DisplayDailyInteractor(displayDailyRecentCitiesDAO,
                displayDailyWeatherDAO, presenter);
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        final String selectedWeekday = zonedDateTime.getDayOfWeek().toString();

        DisplayDailyInputData inputData = new DisplayDailyInputData(selectedWeekday);
        interactor.execute(inputData);
        interactor.execute();
    }

    @Test
    void displayDailySwitchToHomeViewTest() {
        // Output boundary
        DisplayDailyOutputBoundary presenter = new DisplayDailyOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayDailyOutputData displayDailyOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail();
            }

            @Override
            public void switchToHomeView() {
                assertTrue(true);
            }
        };
        DisplayDailyInteractor interactor = new DisplayDailyInteractor(displayDailyRecentCitiesDAO,
                displayDailyWeatherDAO, presenter);
        interactor.switchToHomeView();
    }
}