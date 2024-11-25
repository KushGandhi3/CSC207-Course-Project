package use_case.display_hourly;

public class DisplayHourlyOutputData {

    // City Variables
    private final String city;
    private final double lowTemperature;
    private final double highTemperature;
    private final double time;

    // Weather Variables
    private final String[] condition;
    private final Double[] temperature;
    private final Double[] feelsLike;
    private final Double[] windSpeed;
    private final Double[] precipitation;
    private final Double[] uvIndex;
    private final Double[] cloudCover;
    private final Double[] humidity;

    public DisplayHourlyOutputData(String city, double lowTemperature,
                                   double highTemperature, double time,
                                   String[] condition, Double[] temperature,
                                   Double[] feelsLike, Double[] windSpeed,
                                   Double[] precipitation, Double[] uvIndex,
                                   Double[] airQuality, Double[] humidity) {
        this.city = city;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.time = time;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
        this.uvIndex = uvIndex;
        this.cloudCover = airQuality;
        this.humidity = humidity;
    }

    // Getters
    public double getLowTemperature() {
        return lowTemperature;
    }

    public double getHighTemperature() {
        return highTemperature;
    }

    public double getTime() {
        return time;
    }

    public String[] getCondition() {
        return condition.clone();
    }

    public Double[] getTemperature() {
        return temperature.clone();
    }

    public Double[] getFeelsLike() {
        return feelsLike.clone();
    }

    public Double[] getWindSpeed() {
        return windSpeed.clone();
    }

    public Double[] getPrecipitation() {
        return precipitation.clone();
    }

    public Double[] getUvIndex() {
        return uvIndex.clone();
    }

    public Double[] getCloudCover() {
        return cloudCover.clone();
    }

    public Double[] getHumidity() {
        return humidity.clone();
    }
}
