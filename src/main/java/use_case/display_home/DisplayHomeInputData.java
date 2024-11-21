package use_case.display_home;

/**
 * The Input Data for the DisplayHome Use Case.
 */

public class DisplayHomeInputData {

    private final String cityName;
    private final boolean dailyButtonClicked;
    private final boolean weeklyButtonClicked;
    private final boolean checkerButtonClicked;
    private final boolean outfitButtonClicked;
    private final boolean mapButtonClicked;

    /**
     * Constructor to initialize DisplayHomeInputData with all necessary fields.
     * @param cityName The city name for the weather display.
     * @param dailyButtonClicked Whether the "Daily" button was clicked.
     * @param weeklyButtonClicked Whether the "Weekly" button was clicked.
     * @param checkerButtonClicked Whether the "Checker" button was clicked.
     * @param outfitButtonClicked Whether the "Outfit" button was clicked.
     * @param mapButtonClicked Whether the "Map" button was clicked.
     */
    public DisplayHomeInputData(String cityName, boolean dailyButtonClicked, boolean weeklyButtonClicked,
                                boolean checkerButtonClicked, boolean outfitButtonClicked, boolean mapButtonClicked) {
        this.cityName = cityName;
        this.dailyButtonClicked = dailyButtonClicked;
        this.weeklyButtonClicked = weeklyButtonClicked;
        this.checkerButtonClicked = checkerButtonClicked;
        this.outfitButtonClicked = outfitButtonClicked;
        this.mapButtonClicked = mapButtonClicked;
    }

    public String getCityName() {
        return cityName;
    }

    public boolean isDailyButtonClicked() {
        return dailyButtonClicked;
    }

    public boolean isWeeklyButtonClicked() {
        return weeklyButtonClicked;
    }

    public boolean isCheckerButtonClicked() {
        return checkerButtonClicked;
    }

    public boolean isOutfitButtonClicked() {
        return outfitButtonClicked;
    }

    public boolean isMapButtonClicked() {
        return mapButtonClicked;
    }
}
