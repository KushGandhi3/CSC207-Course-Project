package interface_adapter.display_map;

import org.json.JSONObject;

/**
 * DisplayMapState processes the raw weather data retrieved from the WeatherDataService
 * and provides methods to access individual weather properties like temperature, wind speed,
 * pressure, precipitation, and cloudiness.
 */
public class DisplayMapState {
    private final JSONObject currentWeatherData;

    /**
     * Constructor initializes the WeatherViewModel with weather data for a given city.
     *
     * @param city The name of the city to retrieve weather data for.
     */
    public DisplayMapState(String city) {
        this.currentWeatherData = WeatherDataService.getWeatherData(city);
    }


    /**
     * Gets the current temperature in Celsius from the weather data.
     *
     * @return The current temperature in Celsius.
     */
    public double getTemperature() {
        return currentWeatherData.getJSONObject("main").getDouble("temp");
    }

    /**
     * Gets the current wind speed in meters per second from the weather data.
     *
     * @return The current wind speed in meters per second.
     */
    public double getWindSpeed() {
        return currentWeatherData.getJSONObject("wind").getDouble("speed");
    }

    /**
     * Gets the current air pressure in hPa from the weather data.
     *
     * @return The current air pressure in hPa.
     */
    public double getPressure() {
        return currentWeatherData.getJSONObject("main").getDouble("pressure");
    }

    /**
     * Gets the current precipitation in millimeters from the weather data.
     * If precipitation data is not available, it returns 0.
     *
     * @return The amount of precipitation in millimeters, or 0 if no data is available.
     */
    public double getPrecipitation() {
        if (currentWeatherData.has("rain")) {
            return currentWeatherData.getJSONObject("rain").optDouble("1h", 0);
        }
        return 0;
    }

    /**
     * Gets the current cloudiness percentage from the weather data.
     *
     * @return The cloudiness percentage, from 0 (clear) to 100 (overcast).
     */
    public double getClouds() {
        return currentWeatherData.getJSONObject("clouds").getDouble("all");
    }
}
