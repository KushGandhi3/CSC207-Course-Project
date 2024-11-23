package use_case.checker;

import entity.weather_data.WeatherDataFactory;

public interface CheckerDataAccessInterface {
    boolean checkWeatherData(String location, String weatherConditionOptions, int startChecking, int stopChecking);

}
