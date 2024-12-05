package use_case.display_hourly;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Data object representing the output data for the hourly forecast use case.
 */
public class DisplayHourlyOutputData {

    // City Variables
    private final String city;
    private final int lowTemperature;
    private final int highTemperature;

    // Weather Variables
    private final List<String> time;
    private final List<String> condition;
    private final List<String> temperature;
    private final String feelsLike;
    private final String windSpeed;
    private final String precipitation;
    private final String uvIndex;
    private final String cloudCover;
    private final String humidity;

    /**
     * Constructs DisplayHourlyOutputData from a JSON object.
     *
     * @param outputDataPackage the JSON object containing the output data
     */
    public DisplayHourlyOutputData(JSONObject outputDataPackage) {
        this.city = outputDataPackage.getString("city");
        this.lowTemperature = outputDataPackage.getInt("lowTemperature");
        this.highTemperature = outputDataPackage.getInt("highTemperature");

        final JSONArray timeArray = outputDataPackage.getJSONArray("time");
        this.time = parseJSONArray(timeArray, String.class);

        final JSONArray conditionArray = outputDataPackage.getJSONArray("condition");
        this.condition = parseJSONArray(conditionArray, String.class);

        final JSONArray temperatureArray = outputDataPackage.getJSONArray("temperature");
        this.temperature = parseJSONArray(temperatureArray, String.class);

        this.feelsLike = outputDataPackage.getString("feelsLike");
        this.windSpeed = outputDataPackage.getString("windSpeed");
        this.precipitation = outputDataPackage.getString("precipitation");
        this.uvIndex = outputDataPackage.getString("uvIndex");
        this.cloudCover = outputDataPackage.getString("cloudCover");
        this.humidity = outputDataPackage.getString("humidity");
    }

    /**
     * Parses a JSONArray and converts it into a list of objects of the specified type.
     * @param <T> the type of elements in the list
     * @param jsonArray the JSONArray to be parsed
     * @param type the Class object of type T, representing the type of elements expected in the JSONArray
     * @return a list containing the elements of the JSONArray cast to the specified type
     * @throws IllegalArgumentException if any element of the JSONArray cannot be cast to the specified type
     */
    private <T> List<T> parseJSONArray(JSONArray jsonArray, Class<T> type) throws IllegalArgumentException {
        final List<T> dataValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            final Object value = jsonArray.get(i);
            if (type.isInstance(value)) {
                dataValues.add(type.cast(value));
            }
            else {
                throw new IllegalArgumentException("Element at index " + i + " is not of type "
                        + type.getSimpleName() + ".");
            }
        }
        return dataValues;
    }

    // Getters
    public String getCity() {
        return city;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public List<String> getTime() {
        return time;
    }

    public List<String> getCondition() {
        return condition;
    }

    public List<String> getTemperature() {
        return temperature;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getHumidity() {
        return humidity;
    }
}
