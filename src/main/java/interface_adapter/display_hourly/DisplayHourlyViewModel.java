package interface_adapter.display_hourly;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import interface_adapter.ViewModel;

/**
 * ViewModel for the hourly forecast feature.
 * Holds the state for the hourly forecast view and provides methods to update and manage the state.
 */
public class DisplayHourlyViewModel extends ViewModel<DisplayHourlyState> {
    /**
     * Constructs a DisplayHourlyViewModel with an initial state for the hourly forecast.
     */
    public static final String CLEAR = "Clear";
    public static final String CLOUDS = "Clouds";
    public static final String RAIN = "Rain";
    public static final String DRIZZLE = "Drizzle";
    public static final String THUNDERSTORM = "Thunderstorm";
    public static final String SNOW = "Snow";
    public static final String MIST = "Mist";

    public static final ImageIcon CLOUDS_IMAGE;
    public static final ImageIcon RAIN_IMAGE;
    public static final ImageIcon SNOW_IMAGE;
    public static final ImageIcon CLEAR_IMAGE;
    public static final int WEATHER_ICON_WIDTH = 30;
    public static final int WEATHER_ICON_HEIGHT = 30;

    public static final ImageIcon BACK_BUTTON_IMAGE;
    public static final int BACK_BUTTON_WIDTH = 30;
    public static final int BACK_BUTTON_HEIGHT = 20;

    public static final ImageIcon HOURLY_FORECAST_BOX;
    public static final int HOURLY_FORECAST_WIDTH = 646;
    public static final int HOURLY_FORECAST_HEIGHT = 136;

    public static final ImageIcon HOURLY_DETAILS_BOX;
    public static final int HOURLY_DETAILS_WIDTH = 736;
    public static final int HOURLY_DETAILS_HEIGHT = 196;

    static {
        BACK_BUTTON_IMAGE = resizeIcon("assets/Buttons/BackButton.png", BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);

        HOURLY_FORECAST_BOX = resizeIcon("assets/UIElements/HourlyForecastBox.png",
                HOURLY_FORECAST_WIDTH, HOURLY_FORECAST_HEIGHT);

        CLOUDS_IMAGE = resizeIcon("assets/Weather Icons/Cloudy.png", WEATHER_ICON_WIDTH, WEATHER_ICON_HEIGHT);

        RAIN_IMAGE = resizeIcon("assets/Weather Icons/Raining.png", WEATHER_ICON_WIDTH, WEATHER_ICON_HEIGHT);

        SNOW_IMAGE = resizeIcon("assets/Weather Icons/Snowing.png", WEATHER_ICON_WIDTH, WEATHER_ICON_HEIGHT);

        CLEAR_IMAGE = resizeIcon("assets/Weather Icons/Sunny.png", WEATHER_ICON_WIDTH, WEATHER_ICON_HEIGHT);

        HOURLY_DETAILS_BOX = resizeIcon("assets/UIElements/WeatherDetailsBox.png",
                HOURLY_DETAILS_WIDTH, HOURLY_DETAILS_HEIGHT);
    }

    public DisplayHourlyViewModel() {
        super("Hourly Forecast");
        setState(new DisplayHourlyState());
    }

    /**
     * Resizes an Image Icon given a resource path to the Image.
     * @param path the resource path to the image
     * @param width the desired width
     * @param height the desired height
     * @return a resized Image Icon
     */
    private static ImageIcon resizeIcon(String path, int width, int height) {
        final ImageIcon originalHourlyForecastBox = new ImageIcon(Objects.requireNonNull(
                DisplayHourlyViewModel.class.getClassLoader().getResource(path)));
        final Image resizedHourlyForecastBox = originalHourlyForecastBox.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedHourlyForecastBox);
    }
}
