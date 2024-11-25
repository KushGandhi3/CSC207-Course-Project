package use_case.display_daily;

import java.time.DayOfWeek;

/**
 * The Input Data for the Display Daily Use Case.
 */
public class DisplayDailyInputData {

    private final String city;
    private final DayOfWeek selectedWeekday;

    public DisplayDailyInputData(String city, DayOfWeek selectedWeekday) {
        this.city = city;
        this.selectedWeekday = selectedWeekday;
    }

    public String getCity() {
        return city;
    }

    public DayOfWeek getWeekday() {
        return selectedWeekday;
    }
}
