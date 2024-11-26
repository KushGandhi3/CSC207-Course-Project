package use_case.display_daily;

import constants.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public DisplayDailyOutputData(JSONObject outputDataPackage) {
        this.city = outputDataPackage.getString("city");

        JSONArray weekdaysArray = outputDataPackage.getJSONArray("weekdays");
        this.weekdays = parseJSONArray(weekdaysArray, String.class);

        JSONArray temperaturesArray = outputDataPackage.getJSONArray("temperatures");
        this.temperatures = parseJSONArray(temperaturesArray, Integer.class);

        JSONArray conditionsArray = outputDataPackage.getJSONArray("conditions");
        this.conditions = parseJSONArray(conditionsArray, String.class);

        this.feelsLikeTemperature = outputDataPackage.getInt("feelsLikeTemperature");
        this.uvIndex = outputDataPackage.getInt("uvIndex");
        this.windSpeed = outputDataPackage.getInt("windSpeed");
        this.cloudCover = outputDataPackage.getInt("cloudCover");
        this.precipitation = outputDataPackage.getInt("precipitation");
        this.humidity = outputDataPackage.getInt("humidity");
    }

    /**
     * Parses a JSONArray that contains elements of generic type. Returns a list containing the parsed data in order.
     * @param jsonArray the JSONArray to parse
     * @param type the type of the data being parsed
     * @param <T> the type class of the data being parsed
     * @throws IllegalArgumentException when data to be parsed is not of type <T>
     */
    private <T> List<T> parseJSONArray(JSONArray jsonArray, Class<T> type) throws IllegalArgumentException {
        List<T> dataValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (type.isInstance(value)) {
                dataValues.add(type.cast(value));
            } else {
                throw new IllegalArgumentException("Element at index " + i + " is not of type " + type.getSimpleName());
            }
        }
        return dataValues;
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