//package interface_adapter.dislay_history;
//
//import interface_adapter.ViewManagerModel;
//import use_case.display_history.DisplayHistoryOutputBoundary;
//import use_case.display_history.DisplayHistoryOutputData;
//
//public class DisplayHistoryPresenter implements DisplayHistoryOutputBoundary {
//
//    private DisplayHistoryViewModel displayHistoryViewModel;
//    private final ViewManagerModel viewManagerModel;
//
//    public DisplayHistoryPresenter(DisplayHistoryViewModel displayHistoryViewModel, ViewManagerModel viewManagerModel) {
//        this.displayHistoryViewModel = displayHistoryViewModel;
//        this.viewManagerModel = viewManagerModel;
//    }
//
//    /**
//     * Prepare view to display history
//     */
//    @Override
//    public void prepareHistoryView(DisplayHistoryOutputData locations) {
//
//        DisplayHistoryState displayHistoryState = displayHistoryViewModel.getState();
//        displayHistoryState.setLocations(locations.getLocation());
//        displayHistoryViewModel.setState(displayHistoryState);
//
//        displayHistoryViewModel.firePropertyChanged();
//    }
//
//    /**
//     * Prepare view to switch to home view
//     */
//    @Override
//    public void prepareHomeView() {
//        viewManagerModel.setState("home");
//        viewManagerModel.firePropertyChanged();
//
//    }
//}
