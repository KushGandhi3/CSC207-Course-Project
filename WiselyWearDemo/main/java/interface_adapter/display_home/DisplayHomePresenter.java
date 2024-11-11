package interface_adapter.display_home;


import use_case.display_home.DisplayHomeOutputBoundary;
import use_case.display_home.DisplayHomeOutputData;

public class DisplayHomePresenter implements DisplayHomeOutputBoundary {

    private final DisplayHomeViewModel displayHomeViewModel;

    public DisplayHomePresenter(DisplayHomeViewModel displayHomeViewModel) {
        this.displayHomeViewModel = displayHomeViewModel;
    }

    @Override
    public void prepareView(DisplayHomeOutputData outputData) {
        displayHomeViewModel.setDisplayHomeOutputData(outputData);
    }
}