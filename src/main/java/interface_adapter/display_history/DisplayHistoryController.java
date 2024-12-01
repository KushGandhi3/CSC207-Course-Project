//package interface_adapter.dislay_history;
//
//import use_case.display_history.DisplayHistoryInputBoundary;
//
///**
// * Controller for history related use cases
// */
//public class DisplayHistoryController {
//
//    private final DisplayHistoryInputBoundary displayHistoryInteractor;
//
//    public DisplayHistoryController(DisplayHistoryInputBoundary displayHistoryInteractor) {
//        this.displayHistoryInteractor = displayHistoryInteractor;
//    }
//
//    /**
//     * Executes the history use case with the chosen location
//     */
//    public void execute(String location) {
//        displayHistoryInteractor.execute(location);
//    }
//
//    /**
//     * Executes the "Switch to home" use case
//     */
//    public void switchToHomeView() {
//        displayHistoryInteractor.switchToHomeView();
//    }
//
//}
