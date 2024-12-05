package interface_adapter.display_home;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the HomeView.
 */
public class DisplayHomeViewModel extends ViewModel<DisplayHomeState> {

    public static final String CLEAR = "Clear";
    public static final String CLOUDS = "Clouds";
    public static final String RAIN = "Rain";
    public static final String DRIZZLE = "Drizzle";
    public static final String THUNDERSTORM = "Thunderstorm";
    public static final String SNOW = "Snow";
    public static final String MIST = "Mist";

    public static final String HIGHLABEL = "H: ";
    public static final String LOWLABEL = "L: ";
    // infoLabelSplitter splits the displayed low and high temperatures
    public static final String DIVIDER = " | ";

    // use case button demensions
    public static final int USE_CASE_WIDTH = 131;
    public static final int USE_CASE_HEIGHT = 51;
    public static final ImageIcon CHECKER_BUTTON;
    public static final ImageIcon DAILY_BUTTON;
    public static final ImageIcon HOURLY_BUTTON;
    public static final ImageIcon SUMMARY_BUTTON;
    public static final ImageIcon HISTORY_BUTTON;

    public static final int REFRESH_WIDTH = 88;
    public static final int REFRESH_HEIGHT = 44;
    public static final ImageIcon REFRESH_BUTTON;

    static {
        CHECKER_BUTTON = resizeIcon("assets/Buttons/CheckerButton.png", USE_CASE_WIDTH, USE_CASE_HEIGHT);
        DAILY_BUTTON = resizeIcon("assets/Buttons/DailyButton.png", USE_CASE_WIDTH, USE_CASE_HEIGHT);
        HOURLY_BUTTON = resizeIcon("assets/Buttons/HourlyButton.png", USE_CASE_WIDTH, USE_CASE_HEIGHT);
        SUMMARY_BUTTON = resizeIcon("assets/Buttons/SummaryButton.png", USE_CASE_WIDTH, USE_CASE_HEIGHT);
        HISTORY_BUTTON = resizeIcon("assets/Buttons/HistoryButton.png", USE_CASE_WIDTH, USE_CASE_HEIGHT);
        REFRESH_BUTTON = resizeIcon("assets/Buttons/RefreshButton.png", REFRESH_WIDTH, REFRESH_HEIGHT);
    }

    public DisplayHomeViewModel() {
        super("Home");
        this.setState(new DisplayHomeState());
    }

    /**
     * Resizes an Image Icon given a resource path to the Image.
     * @param path the resource path to the image
     * @param width the desired width
     * @param height the desired height
     * @return a resized Image Icon
     */
    private static ImageIcon resizeIcon(String path, int width, int height) {
        final ImageIcon original = new ImageIcon(Objects.requireNonNull(
                DisplayHomeViewModel.class.getClassLoader().getResource(path)));
        final Image resized = original.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

}
