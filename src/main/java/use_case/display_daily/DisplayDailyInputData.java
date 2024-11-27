package use_case.display_daily;

import java.time.DayOfWeek;

/**
 * The Input Data for the Display Daily Use Case.
 */
public class DisplayDailyInputData {

    private final DayOfWeek selectedWeekday;

    public DisplayDailyInputData(DayOfWeek selectedWeekday) {
        this.selectedWeekday = selectedWeekday;
    }

    public DayOfWeek getWeekday() {
        return selectedWeekday;
    }

}
