package use_case.display_hourly;

import java.time.LocalTime;

/**
 * Data object representing the input data for the hourly forecast use case.
 */
public class DisplayHourlyInputData {
    private final String selectedTime;

    /**
     * Constructs DisplayHourlyInputData with the selected time.
     *
     * @param selectedTime the selected time
     */
    public DisplayHourlyInputData(LocalTime selectedTime) {
        this.selectedTime = selectedTime.toString();
    }

    /**
     * Gets the selected time as a string.
     *
     * @return the selected time
     */
    public String getSelectedTime() {
        return selectedTime;
    }
}
