package use_case.display_history;

import entity.recent_city.RecentCityData;

import java.util.List;

/**
 * Interactor for the display history use case
 */
public class DisplayHistoryInteractor implements DisplayHistoryInputBoundary {

    private final DisplayHistoryOutputBoundary displayHistoryOutputBoundary;
    private final DisplayHistoryDAI displayHistoryDAI;

    public DisplayHistoryInteractor(DisplayHistoryDAI displayHistoryDAI, DisplayHistoryOutputBoundary displayHistoryOutputBoundary) {
        this.displayHistoryDAI = displayHistoryDAI;
        this.displayHistoryOutputBoundary = displayHistoryOutputBoundary;
    }

    /**
     * Executes the history use case with the chosen location
     */
    @Override
    public void execute(String location) {
        // TODO implement the logic here

        displayHistoryDAI.setChosenLocation(location);
        DisplayHistoryOutputData outputData = new DisplayHistoryOutputData(location);
        displayHistoryOutputBoundary.prepareSuccessView(outputData);
    }

    /**
     * Switch to the home view
     */
    @Override
    public void switchToHomeView() {
        displayHistoryOutputBoundary.prepareHomeView();
    }
}
