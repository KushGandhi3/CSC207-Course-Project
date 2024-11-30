package use_case.display_daily;

/**
 * The Input Data for the Display Daily Use Case.
 */
public class DisplayDailyInputData {

    private final String selectedWeekday;

    public DisplayDailyInputData(String selectedWeekday) {
        this.selectedWeekday = selectedWeekday;
    }

    public String getWeekday() {
        return selectedWeekday;
    }

}
