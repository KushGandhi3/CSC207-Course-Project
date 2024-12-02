package entity.weather.hourly_weather;

import data_access.weather.InMemoryWeatherDAO;
import entity.weather.daily_weather.ConcreteDailyWeatherDataFactory;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hour_weather.HourWeatherDataFactory;
import exception.APICallException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HourlyWeatherDataTest {

    @Test
    void getHourlyWeatherDataTest() {
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();

        InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory,
                dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
        try {
            HourlyWeatherData hourlyWeatherData = inMemoryWeatherDAO.getHourlyWeatherData("Toronto");

            // test the list of HourWeatherData entities
            List<HourWeatherData> hourWeatherDataList = hourlyWeatherData.getHourWeatherDataList();

            assertEquals(48, hourWeatherDataList.size());

            List<String> expectedCities = new ArrayList<>();
            for (int i = 0; i < 48; i++) {
                expectedCities.add("Toronto");
            }
            List<String> expectedTimezones = new ArrayList<>();
            for (int i = 0; i < 48; i++) {
                expectedTimezones.add("America/New_York");
            }
            List<String> expectedConditions = List.of("Rain","Clouds","Clouds","Rain","Rain","Rain","Clouds",
                    "Rain","Rain","Rain","Rain","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds",
                    "Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clear","Clear","Clear","Clear","Clear",
                    "Clear","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds",
                    "Clouds","Clouds","Clouds","Clouds","Clouds","Clouds","Clouds");
            List<Integer> expectedTemperatures = List.of(7, 7, 7, 7, 7, 8, 8, 8, 9, 10, 8, 7, 6, 6, 6, 5, 5,
                    4, 5, 5, 5, 4, 3, 3, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 5, 4, 4, 4);
            List<Integer> expectedFeelsLikeTemperatures = List.of(6, 6, 5, 5, 6, 6, 7, 7, 7, 9, 5, 3, 2, 1,
                    1, 0, 0, 0, 0, 1, 1, 0, -1, -1, -2, -2, -2, -2, -3, -4, -4, -4, -4, -4, -4, -3, -3, -3, -2, -1, 0,
                    0, 1, 1, 2, 2, 1, 1);
            List<Integer> expectedWindSpeeds = List.of(1, 2, 2, 3, 2, 2, 2, 2, 4, 6, 6, 7, 7, 8, 8, 8, 7, 7,
                    7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 3, 3, 2);
            List<Integer> expectedUVIndexes = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0);
            List<Integer> expectedCloudCovers = List.of(100, 100, 100, 100, 100, 100, 100, 96, 98, 99, 99,
                    99, 99, 100, 100, 100, 100, 100, 100, 100, 86, 60, 47, 38, 32, 2, 2, 3, 5, 5, 6, 12, 33, 55, 65,
                    72, 77, 98, 99, 99, 97, 96, 93, 100, 100, 100, 99, 99);
            List<Integer> expectedPrecipitations = List.of(20, 80, 80, 100, 100, 100, 80, 100, 100, 100, 100,
                    80, 80, 18, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0);
            List<Integer> expectedHumidities = List.of(80, 81, 84, 85, 92, 94, 94, 95, 97, 93, 84, 84, 81,
                    71, 64, 64, 62, 61, 56, 54, 54, 53, 50, 50, 51, 51, 52, 53, 55, 58, 60, 61, 62, 63, 63, 62, 62,
                    61, 57, 51, 46, 45, 46, 48, 51, 54, 60, 63);

            for (int i = 0; i < hourWeatherDataList.size(); i++) {
                HourWeatherData hourWeatherData = hourWeatherDataList.get(i);
                assertEquals(expectedCities.get(i), hourWeatherData.getCity());
                assertEquals(expectedTimezones.get(i), hourWeatherData.getTimezone());
                assertEquals(expectedConditions.get(i), hourWeatherData.getCondition());
                assertEquals(expectedTemperatures.get(i), hourWeatherData.getTemperature());
                assertEquals(expectedFeelsLikeTemperatures.get(i), hourWeatherData.getFeelsLikeTemperature());
                assertEquals(expectedWindSpeeds.get(i), hourWeatherData.getWindSpeed());
                assertEquals(expectedUVIndexes.get(i), hourWeatherData.getUvIndex());
                assertEquals(expectedCloudCovers.get(i), hourWeatherData.getCloudCover());
                assertEquals(expectedPrecipitations.get(i), hourWeatherData.getPrecipitation());
                assertEquals(expectedHumidities.get(i), hourWeatherData.getHumidity());
            }

            // assert other HourlyWeatherData instance variables
            assertEquals(9, hourlyWeatherData.getHighTemperature());
            assertEquals(2, hourlyWeatherData.getLowTemperature());
            assertEquals("Toronto", hourlyWeatherData.getCity());
            assertEquals("America/New_York", hourlyWeatherData.getTimezone());
        } catch(APICallException exception) {
            fail(exception.getMessage());
        }
    }
}
