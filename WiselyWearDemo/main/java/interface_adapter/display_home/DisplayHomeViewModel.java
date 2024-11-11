package interface_adapter.display_home;

import use_case.display_home.DisplayHomeOutputData;

public class DisplayHomeViewModel {

    private DisplayHomeOutputData displayHomeOutputData;

    public DisplayHomeOutputData getDisplayHomeOutputData() {
        return displayHomeOutputData;
    }

    public void setDisplayHomeOutputData(DisplayHomeOutputData displayHomeOutputData) {
        this.displayHomeOutputData = displayHomeOutputData;
    }
}