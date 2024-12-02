package entity.weather.daily_weather;

import data_access.weather.InMemoryWeatherDAO;
import entity.weather.day_weather.ConcreteDayWeatherDataFactory;
import entity.weather.day_weather.DayWeatherData;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.ConcreteHourWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.ConcreteHourlyWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import exception.APICallException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DailyWeatherDataTest {

    @Test
    void getDailyWeatherDataTest() {
        DailyWeatherDataFactory dailyWeatherDataFactory = new ConcreteDailyWeatherDataFactory();
        DayWeatherDataFactory dayWeatherDataFactory = new ConcreteDayWeatherDataFactory();
        HourlyWeatherDataFactory hourlyWeatherDataFactory = new ConcreteHourlyWeatherDataFactory();
        HourWeatherDataFactory hourWeatherDataFactory = new ConcreteHourWeatherDataFactory();

        InMemoryWeatherDAO inMemoryWeatherDAO = new InMemoryWeatherDAO(dayWeatherDataFactory,
                dailyWeatherDataFactory, hourWeatherDataFactory, hourlyWeatherDataFactory);
        try {
            DailyWeatherData dailyWeatherData = inMemoryWeatherDAO.getDailyWeatherData("Toronto");

            // test the list of DayWeatherData entities
            List<DayWeatherData> dayWeatherDataList = dailyWeatherData.getDayWeatherDataList();

            assertEquals(8, dayWeatherDataList.size());

            List<String> expectedCities = List.of("Toronto", "Toronto", "Toronto", "Toronto", "Toronto",
                    "Toronto", "Toronto", "Toronto");
            List<String> expectedTimezones = List.of("America/New_York", "America/New_York", "America/New_York",
                    "America/New_York", "America/New_York", "America/New_York", "America/New_York", "America/New_York");
            List<String> expectedConditions = List.of("Rain", "Rain", "Clouds", "Clouds",
                    "Clouds", "Clouds", "Snow", "Clouds");
            List<Integer> expectedTemperatures = List.of(7, 4, 4, 5, 3, 1, 1, 2);
            List<Integer> expectedFeelsLikeTemperatures = List.of(5, 0, 0, 3, 0, -3, -4, -2);
            List<Integer> expectedWindSpeeds = List.of(3, 8, 6, 3, 5, 7, 6, 5);
            List<Integer> expectedUVIndexes = List.of(1, 0, 1, 1, 0, 1, 1, 1);
            List<Integer> expectedCloudCovers = List.of(100, 100, 96, 100, 99, 18, 3, 55);
            List<Integer> expectedPrecipitations = List.of(100, 100, 0, 0, 0, 0, 99, 42);
            List<Integer> expectedHumidities = List.of(57, 61, 45, 60, 51, 45, 42, 52);

            for (int i = 0; i < dayWeatherDataList.size(); i++) {
                DayWeatherData dayWeatherData = dayWeatherDataList.get(i);
                assertEquals(expectedCities.get(i), dayWeatherData.getCity());
                assertEquals(expectedTimezones.get(i), dayWeatherData.getTimezone());
                assertEquals(expectedConditions.get(i), dayWeatherData.getCondition());
                assertEquals(expectedTemperatures.get(i), dayWeatherData.getTemperature());
                assertEquals(expectedFeelsLikeTemperatures.get(i), dayWeatherData.getFeelsLikeTemperature());
                assertEquals(expectedWindSpeeds.get(i), dayWeatherData.getWindSpeed());
                assertEquals(expectedUVIndexes.get(i), dayWeatherData.getUvIndex());
                assertEquals(expectedCloudCovers.get(i), dayWeatherData.getCloudCover());
                assertEquals(expectedPrecipitations.get(i), dayWeatherData.getPrecipitation());
                assertEquals(expectedHumidities.get(i), dayWeatherData.getHumidity());
            }

            // assert other DailyWeatherData instance variables
            assertEquals("America/New_York", dailyWeatherData.getTimezone());
            assertEquals("Toronto", dailyWeatherData.getCity());

        } catch(APICallException exception) {
            fail(exception.getMessage());
        }
    }
}
