package data_access.weather;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import data_access.weather.open_weather.OpenWeatherWeatherDAO;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.DayWeatherData;
import entity.weather.day_weather.DayWeatherDataFactory;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hour_weather.HourWeatherDataFactory;
import entity.weather.hourly_weather.HourlyWeatherData;
import entity.weather.hourly_weather.HourlyWeatherDataFactory;
import exception.APICallException;
import use_case.display_checker.DisplayCheckerDAI;
import use_case.display_daily.DisplayDailyWeatherDAI;
import use_case.display_home.DisplayHomeWeatherDAI;
import use_case.display_hourly.DisplayHourlyWeatherDAI;
import use_case.display_summarization.DisplaySummarizationWeatherDAI;

/**
 * This class parses OpenWeather JSON Objects and creates DailyWeatherData and HourlyWeatherDataObjects.
 */
public class WeatherDAO implements DisplayHomeWeatherDAI, DisplayDailyWeatherDAI, DisplayCheckerDAI,
        DisplaySummarizationWeatherDAI, DisplayHourlyWeatherDAI {

    private static final String TIME_ZONE = "timezone";
    private static final String TEMP = "temp";
    private static final String HUMIDITY = "humidity";

    private final DayWeatherDataFactory dayWeatherDataFactory;
    private final DailyWeatherDataFactory dailyWeatherDataFactory;
    private final HourlyWeatherDataFactory hourlyWeatherDataFactory;
    private final HourWeatherDataFactory hourWeatherDataFactory;

    public WeatherDAO(DayWeatherDataFactory dayWeatherDataFactory,
                      DailyWeatherDataFactory dailyWeatherDataFactory,
                      HourWeatherDataFactory hourWeatherDataFactory,
                      HourlyWeatherDataFactory hourlyWeatherDataFactory) {
        this.dayWeatherDataFactory = dayWeatherDataFactory;
        this.dailyWeatherDataFactory = dailyWeatherDataFactory;
        this.hourlyWeatherDataFactory = hourlyWeatherDataFactory;
        this.hourWeatherDataFactory = hourWeatherDataFactory;
    }

    /**
     * Returns HourlyWeatherData entity with updated weather information from the OpenWeather API.
     * @param city the name of the city to get the weather forecast for
     * @return an HourlyWeatherData entity
     * @throws APICallException if the request fails or the API Key is not set
     */
    @Override
    public HourlyWeatherData getHourlyWeatherData(String city) throws APICallException {
        final JSONObject weatherData = OpenWeatherWeatherDAO.apiRequest(city);

        final String timezone = weatherData.getString(TIME_ZONE);
        // get min and max temperature for the day
        // the weather data for today
        final JSONObject todayWeatherData = weatherData.getJSONArray("daily").getJSONObject(0);
        // temperature data from the weather data today
        final JSONObject temperatureTodayWeatherData = todayWeatherData.getJSONObject(TEMP);
        final int lowTemperature = (int) temperatureTodayWeatherData.getDouble("min");
        final int highTemperature = (int) temperatureTodayWeatherData.getDouble("max");

        final JSONArray hourlyArray = weatherData.getJSONArray("hourly");
        // HourWeatherData objects for the HourlyWeatherData object
        final List<HourWeatherData> hourWeatherDataList = new ArrayList<>(hourlyArray.length());
        getHourWeatherDataList(city, timezone, hourlyArray, hourWeatherDataList);

        return this.hourlyWeatherDataFactory.create(hourWeatherDataList, timezone, city, lowTemperature,
                highTemperature);
    }

    /**
     * Mutates hourWeatherDataList by creating HourWeatherData objects inside of it. HourWeatherData objects are
     * created for each hour provided by the OpenWeather JSON Array.
     * @param city the city the data is being created for
     * @param timezone the timezone of the city
     * @param hourlyArray the JSON Array of hour weather forecast information provided by the API
     * @param hourWeatherDataList the list to be mutated to add the new HourWeatherData objects
     */
    private void getHourWeatherDataList(String city, String timezone, JSONArray hourlyArray,
                                        List<HourWeatherData> hourWeatherDataList) {
        for (int i = 0; i < hourlyArray.length(); i++) {
            final JSONObject hourObject = hourlyArray.getJSONObject(i);

            // note that parsing for DayWeatherData objects and HourWeatherData objects are slightly different
            // unpacking for main weather condition
            final JSONArray conditionArray = hourObject.getJSONArray("weather");
            final JSONObject conditionObject = conditionArray.getJSONObject(0);
            // main weather condition ("Rain", "Clouds", "Snow")
            final String condition = conditionObject.getString("main");

            final int temperature = (int) hourObject.getDouble(TEMP);

            final int feelsLikeTemperature = (int) hourObject.getDouble("feels_like");

            final int windSpeed = (int) hourObject.getDouble("wind_speed");

            final int uvIndex = (int) hourObject.getDouble("uvi");

            final int cloudCover = (int) hourObject.getDouble("clouds");

            final int precipitation = (int) (hourObject.getDouble("pop") * 100);

            final int humidity = (int) hourObject.getDouble(HUMIDITY);

            // package all forecast values
            final JSONObject hourWeatherDataValues = new JSONObject();
            hourWeatherDataValues.put("city", city);
            hourWeatherDataValues.put(TIME_ZONE, timezone);
            hourWeatherDataValues.put("condition", condition);
            hourWeatherDataValues.put("temperature", temperature);
            hourWeatherDataValues.put("feelsLikeTemperature", feelsLikeTemperature);
            hourWeatherDataValues.put("windSpeed", windSpeed);
            hourWeatherDataValues.put("uvIndex", uvIndex);
            hourWeatherDataValues.put("cloudCover", cloudCover);
            hourWeatherDataValues.put("precipitation", precipitation);
            hourWeatherDataValues.put(HUMIDITY, humidity);

            final HourWeatherData hourWeatherData = this.hourWeatherDataFactory.create(hourWeatherDataValues);
            hourWeatherDataList.add(hourWeatherData);
        }
    }

    /**
     * Returns a DailyWeatherData entity with updated weather data from the OpenWeather API.
     * @param city the name of the city to get the weather forecast for
     * @return a DailyWeatherData entity
     * @throws APICallException if the request fails or the API Key is not set
     */
    @Override
    public DailyWeatherData getDailyWeatherData(String city) throws APICallException {
        final JSONObject weatherData = OpenWeatherWeatherDAO.apiRequest(city);

        final String timezone = weatherData.getString(TIME_ZONE);
        final JSONArray dailyArray = weatherData.getJSONArray("daily");

        final List<DayWeatherData> dayWeatherDataList = new ArrayList<>(dailyArray.length());
        getDayWeatherDataList(city, timezone, dailyArray, dayWeatherDataList);
        return this.dailyWeatherDataFactory.create(dayWeatherDataList, timezone, city);
    }

    /**
     * Mutates dayWeatherDataList by creating dayWeatherData objects inside of it. DayWeatherData objects are
     * created for each day provided by the OpenWeather JSON Array.
     * @param city the city the data is being created for
     * @param timezone the timezone of the city
     * @param dailyArray the JSON Array of day weather forecast information provided by the API
     * @param dayWeatherDataList the list to be mutated to add the new DayWeatherData objects
     */
    private void getDayWeatherDataList(String city, String timezone, JSONArray dailyArray,
                                       List<DayWeatherData> dayWeatherDataList) {
        for (int i = 0; i < dailyArray.length(); i++) {
            final JSONObject dayObject = dailyArray.getJSONObject(i);

            // note that parsing for DayWeatherData objects and HourWeatherData objects are slightly different
            // unpacking for main weather condition
            final JSONArray conditionArray = dayObject.getJSONArray("weather");
            final JSONObject conditionObject = conditionArray.getJSONObject(0);
            // main weather condition ("Rain", "Clouds", "Snow")
            final String condition = conditionObject.getString("main");

            // unpacking for temperature
            final JSONObject temperatureObject = dayObject.getJSONObject(TEMP);
            // "day" means temperature in the middle of the day
            final int temperature = (int) temperatureObject.getDouble("day");

            // unpacking for feels like temperature
            final JSONObject feelsLikeObject = dayObject.getJSONObject("feels_like");
            // "day" means temperature in the middle of the day
            final int feelsLikeTemperature = (int) feelsLikeObject.getDouble("day");

            final int windSpeed = (int) dayObject.getDouble("wind_speed");

            final int uvIndex = (int) dayObject.getDouble("uvi");

            final int cloudCover = (int) dayObject.getDouble("clouds");

            final int precipitation = (int) (dayObject.getDouble("pop") * 100);

            final int humidity = (int) dayObject.getDouble(HUMIDITY);

            // package all forecast values
            final JSONObject dayWeatherDataValues = new JSONObject();
            dayWeatherDataValues.put("city", city);
            dayWeatherDataValues.put(TIME_ZONE, timezone);
            dayWeatherDataValues.put("condition", condition);
            dayWeatherDataValues.put("temperature", temperature);
            dayWeatherDataValues.put("feelsLikeTemperature", feelsLikeTemperature);
            dayWeatherDataValues.put("windSpeed", windSpeed);
            dayWeatherDataValues.put("uvIndex", uvIndex);
            dayWeatherDataValues.put("cloudCover", cloudCover);
            dayWeatherDataValues.put("precipitation", precipitation);
            dayWeatherDataValues.put(HUMIDITY, humidity);

            final DayWeatherData dayWeatherData = this.dayWeatherDataFactory.create(dayWeatherDataValues);
            dayWeatherDataList.add(dayWeatherData);
        }
    }
}
