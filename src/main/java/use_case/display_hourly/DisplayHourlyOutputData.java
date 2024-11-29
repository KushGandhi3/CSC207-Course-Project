package use_case.display_hourly;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private final List<Integer> temperature;
    private final int feelsLike;
    private final int windSpeed;
    private final int precipitation;
    private final int uvIndex;
    private final int cloudCover;
    private final int humidity;

    /**
     * Constructs DisplayHourlyOutputData from a JSON object.
     *
     * @param outputDataPackage the JSON object containing the output data
     */
    public DisplayHourlyOutputData(JSONObject outputDataPackage) {
        this.city = outputDataPackage.getString("city");
        this.lowTemperature = outputDataPackage.getInt("lowTemperature");
        this.highTemperature = outputDataPackage.getInt("highTemperature");

        JSONArray timeArray = outputDataPackage.getJSONArray("time");
        this.time = parseJSONArray(timeArray, String.class);

        JSONArray conditionArray = outputDataPackage.getJSONArray("condition");
        this.condition = parseJSONArray(conditionArray, String.class);

        JSONArray temperatureArray = outputDataPackage.getJSONArray("temperature");
        this.temperature = parseJSONArray(temperatureArray, Integer.class);

        this.feelsLike = outputDataPackage.getInt("feelsLike");
        this.windSpeed = outputDataPackage.getInt("windSpeed");
        this.precipitation = outputDataPackage.getInt("precipitation");
        this.uvIndex = outputDataPackage.getInt("uvIndex");
        this.cloudCover = outputDataPackage.getInt("cloudCover");
        this.humidity = outputDataPackage.getInt("humidity");
    }

    private <T> List<T> parseJSONArray(JSONArray jsonArray, Class<T> type) throws IllegalArgumentException {
        List<T> dataValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (type.isInstance(value)) {
                dataValues.add(type.cast(value));
            } else {
                throw new IllegalArgumentException("Element at index " + i + " is not of type " + type.getSimpleName() + ".");
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

    public List<Integer> getTemperature() {
        return temperature;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getHumidity() {
        return humidity;
    }
}
