//package interface_adapter.dislay_history;
//
//import interface_adapter.ViewModel;
//import interface_adapter.display_checker.DisplayCheckerViewModel;
//
//import javax.swing.*;
//import java.util.Objects;
//
//public class DisplayHistoryViewModel extends ViewModel<DisplayHistoryState> {
//
//    public static final String TITLE_LABEL = "Location History";
//
//    public static final ImageIcon BACK_BUTTON_IMAGE = new ImageIcon(
//            Objects.requireNonNull(DisplayCheckerViewModel.class.getClassLoader().getResource(
//                    "assets/Buttons/BackButton.png")));
//
//    public DisplayHistoryViewModel() {
//        super("History");
//        setState(new DisplayHistoryState());
//    }
//}
