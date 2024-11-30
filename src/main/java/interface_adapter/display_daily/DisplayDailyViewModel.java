package interface_adapter.display_daily;

import interface_adapter.ViewModel;
import interface_adapter.display_checker.DisplayCheckerViewModel;

import javax.swing.*;
import java.util.Objects;

/**
 * The View Model for the Display Daily View.
 */
public class DisplayDailyViewModel extends ViewModel<DisplayDailyState> {

    public static final String TITLE_LABEL = "Daily Forecast";

    public static final ImageIcon BACK_BUTTON_IMAGE = new ImageIcon(
            Objects.requireNonNull(DisplayCheckerViewModel.class.getClassLoader().getResource(
                    "assets/Buttons/BackButton.png")));

    public DisplayDailyViewModel() {
        super("Daily Forecast");
        setState(new DisplayDailyState());
    }

}
