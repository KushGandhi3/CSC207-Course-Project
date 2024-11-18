package interface_adapter.checker;

import  interface_adapter.ViewModel;

public class CheckerViewModel extends ViewModel<CheckerState> {

    public static final String TITLE_LABEL = "Weather Checker";
    public static final String LOCATION_LABEL = "Location";
    public static final String TEMPERATURE_LABEL = "Temperature";
    public static final String TEMPERATURE_UNIT_LABEL = "°C";
    public static final String START_CHECKING_LABEL = "Start Checking";
    public static final String START_CHECKING_DESCRIPTION = "hour(s) from now";
    public static final String STOP_CHECKING_LABEL = "Stop Checking";
    public static final String STOP_CHECKING_DESCRIPTION = "hour(s) from start time";

    public static final String[] TEMPERATURE_OPTIONS = new String[]{"Below", "At", "Above"};
    public static final int[] START_CHECKING_OPTIONS = generateStartCheckingOptions();
    public static final int[] STOP_CHECKING_OPTIONS = generateStopCheckingOptions();

    public static final String CHECK_BUTTON_LABEL = "Check";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public CheckerViewModel() {
        super("checker");
        setState(new CheckerState());
    }

    // Method to generate the start checking options list (0 to 48 hours)
    private static int[] generateStartCheckingOptions() {
        int[] options = new int[49]; // Array of size 49 (0 to 48)
        for (int i = 0; i <= 48; i++) {
            options[i] = i;
        }
        return options;
    }

    // Method to generate the stop checking options list (1 to 24 hours)
    private static int[] generateStopCheckingOptions() {
        int[] options = new int[24]; // Array of size 24 (1 to 24)
        for (int i = 0; i < 24; i++) {
            options[i] = (i+1);
        }
        return options;
    }




}
