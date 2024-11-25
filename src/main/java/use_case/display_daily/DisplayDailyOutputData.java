package use_case.display_daily;

import java.util.List;

/**
 * Output Data for the Display Daily use-case.
 */
public class DisplayDailyOutputData {

    private String city;
    // list of the weekdays in the correct order
    private List<String> weekdays;
    // list of the temperatures in order of the weekdays
    private List<Integer> temperatures;
    private List<String> conditions;
    // selected weather details for the particular weekday
    private int feelsLikeTemperature;
    private int uvIndex;
    private int windSpeed;
    private int cloudCover;
    private int precipitation;
    private int humidity;

    public DisplayDailyOutputData(String city, List<String> weekdays, List<Integer> temperatures,
                                  List<String> conditions, int feelsLikeTemperature, int uvIndex, int windSpeed,
                                  int cloudCover, int precipitation, int humidity) {
        this.city = city;
        this.weekdays = weekdays;
        this.temperatures = temperatures;
        this.conditions = conditions;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.uvIndex = uvIndex;
        this.windSpeed = windSpeed;
        this.cloudCover = cloudCover;
        this.precipitation = precipitation;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    public List<Integer> getTemperatures() {
        return temperatures;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public int getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public int getHumidity() {
        return humidity;
    }

}