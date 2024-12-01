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
    public static final int BACK_BUTTON_WIDTH = 30;
    public static final int BACK_BUTTON_HEIGHT = 20;

    static {
        BACK_BUTTON_ICON = resizeIcon("assets/Buttons/BackButton.png", BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
    }

    public DisplayHistoryViewModel() {
        super("History View");
        setState(new DisplayHistoryState());
    }

    /**
     * Resizes the back button image.
     * @param path the resources file path
     * @param width the desired width of the image
     * @param height the desired height of the image
     * @return a resized Image Icon
     */
    private static ImageIcon resizeIcon(String path, int width, int height) {
        final ImageIcon originalDailyForecastBox = new ImageIcon(Objects.requireNonNull(
                DisplayHistoryViewModel.class.getClassLoader().getResource(path)));
        final Image resizedDailyForecastBox = originalDailyForecastBox.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedDailyForecastBox);
    }
}