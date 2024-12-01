package interface_adapter.display_checker;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.util.Objects;

public class DisplayCheckerViewModel extends ViewModel<DisplayCheckerState> {

    public static final String TITLE_LABEL = "Weather Checker";
    public static final String LOCATION_LABEL = "Location";
    public static final String WEATHER_CONDITION_LABEL = "Weather Condition";
    public static final String START_CHECKING_LABEL = "Start Checking";
    public static final String START_CHECKING_DESCRIPTION = "hour(s) from now";
    public static final String STOP_CHECKING_LABEL = "Stop Checking";
    public static final String STOP_CHECKING_DESCRIPTION = "hour(s) from start time";
    public static final String[] WEATHER_CONDITION_OPTIONS = new String[]{"Thunderstorm", "Drizzle", "Rain", "Snow", "Clear",
            "Clouds"};
    public static final Integer[] START_CHECKING_OPTIONS = generateStartCheckingOptions();
    public static final Integer[] STOP_CHECKING_OPTIONS = generateStopCheckingOptions();

    public static final String CHECK_BUTTON_LABEL = "Check";
    public static final ImageIcon BACK_BUTTON_IMAGE = new ImageIcon(
            Objects.requireNonNull(DisplayCheckerViewModel.class.getClassLoader().getResource(
                    "assets/Buttons/BackButton.png")));

    public DisplayCheckerViewModel() {
        super("checker");
        setState(new DisplayCheckerState());
    }

    // Method to generate the start checking options list (0 to 24 hours)
    private static Integer[] generateStartCheckingOptions() {
        Integer[] options = new Integer[25]; // Array of size 25 (0 to 24)
        for (int i = 0; i <= 24; i++) {
            options[i] = i;
        }
        return options;
    }

    // Method to generate the stop checking options list (1 to 24 hours)
    private static Integer[] generateStopCheckingOptions() {
        Integer[] options = new Integer[24]; // Array of size 24 (1 to 24)
        for (int i = 0; i < 24; i++) {
            options[i] = (i+1);
        }
        return options;
    }
}
