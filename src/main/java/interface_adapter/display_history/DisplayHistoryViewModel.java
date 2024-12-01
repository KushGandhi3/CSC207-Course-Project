package interface_adapter.display_history;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import interface_adapter.ViewModel;

/**
 * DisplayHistoryViewModel is responsible for managing the state and UI-related
 * properties for displaying the history of city-related data. It extends the
 * generic ViewModel class with a specific state, DisplayHistoryState.
 */
public class DisplayHistoryViewModel extends ViewModel<DisplayHistoryState> {

    public static final String TITLE_LABEL = "City History";

    public static final ImageIcon BACK_BUTTON_ICON;
    public static final int BACK_BUTTON_WIDTH = 34;
    public static final int BACK_BUTTON_HEIGHT = 24;

    static {
        BACK_BUTTON_ICON = resizeIcon();
    }

    public DisplayHistoryViewModel() {
        super("History");
        setState(new DisplayHistoryState());
    }

    /**
     * Resizes the back button image.
     * @return a resized Image Icon
     */
    private static ImageIcon resizeIcon() {
        final ImageIcon originalDailyForecastBox = new ImageIcon(Objects.requireNonNull(
                DisplayHistoryViewModel.class.getClassLoader().getResource("images/back_button.png")));
        final Image resizedDailyForecastBox = originalDailyForecastBox.getImage()
                .getScaledInstance(DisplayHistoryViewModel.BACK_BUTTON_WIDTH, DisplayHistoryViewModel
                        .BACK_BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedDailyForecastBox);
    }
}
