package use_case.display_history;

import entity.recent_city.RecentCityData;
import exception.RecentCitiesDataException;

/**
 * Interactor for the display history use case.
 */
public class DisplayHistoryInteractor implements DisplayHistoryInputBoundary {

    private final DisplayHistoryOutputBoundary displayHistoryPresenter;
    private final DisplayHistoryDAI displayHistoryDAO;

    public DisplayHistoryInteractor(DisplayHistoryDAI displayHistoryDAO,
                                    DisplayHistoryOutputBoundary displayHistoryPresenter) {
        this.displayHistoryDAO = displayHistoryDAO;
        this.displayHistoryPresenter = displayHistoryPresenter;
    }

    /**
     * Executes the history use case with the chosen city.
     * @param displayHistoryInputData the selected city
     */
    @Override
    public void execute(DisplayHistoryInputData displayHistoryInputData) {

        try {
            final String city = displayHistoryInputData.getCity();
            // put the city at the most recent top of the list
            displayHistoryDAO.addCity(city);

            final RecentCityData recentCityData = displayHistoryDAO.getRecentCityData();
            final DisplayHistoryOutputData displayHistoryOutputData = new DisplayHistoryOutputData(recentCityData
                    .getRecentCityList());
            displayHistoryPresenter.prepareSuccessView(displayHistoryOutputData);
        }
        catch (RecentCitiesDataException exception) {
            displayHistoryPresenter.prepareFailureView(exception.getMessage());
        }
    }

    @Override
    public void execute() {

        try {
            final RecentCityData recentCityData = displayHistoryDAO.getRecentCityData();
            final DisplayHistoryOutputData displayHistoryOutputData = new DisplayHistoryOutputData(recentCityData
                    .getRecentCityList());
            displayHistoryPresenter.prepareSuccessView(displayHistoryOutputData);
        }
        catch (RecentCitiesDataException exception) {
            displayHistoryPresenter.prepareFailureView(exception.getMessage());
        }

    }

    /**
     * Switch to the home view.
     */
    @Override
    public void switchToHomeView() {
        this.displayHistoryPresenter.switchToHomeView();
    }
}