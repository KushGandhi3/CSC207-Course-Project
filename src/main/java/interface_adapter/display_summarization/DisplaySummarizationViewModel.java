package interface_adapter.display_summarization;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import interface_adapter.ViewModel;

/**
 * The ViewModel for when the user wants to analyze an outfit.
 */
public class DisplaySummarizationViewModel extends ViewModel<DisplaySummarizationState> {

    public static final String TITLE_LABEL = "AI Summarization";
    public static final String SUMMARY_TITLE = "Weather Summary";
    public static final String OUTFIT_LABEL = "Outfit Suggestion";
    public static final String TRAVEL_LABEL = "Travel Advice";

    public static final ImageIcon BACK_BUTTON_ICON;
    public static final int BACK_BUTTON_WIDTH = 34;
    public static final int BACK_BUTTON_HEIGHT = 24;

    static {
        BACK_BUTTON_ICON = resizeIcon("assets/Buttons/BackButton.png", BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
    }

    public DisplaySummarizationViewModel() {
        super("Summarization View");
        setState(new DisplaySummarizationState());
    }

    /**
     * Resizes an Image Icon given a resource path to the Image.
     * @param path the resource path to the image
     * @param width the desired width
     * @param height the desired height
     * @return a resized Image Icon
     */
    private static ImageIcon resizeIcon(String path, int width, int height) {
        final ImageIcon originalDailyForecastBox = new ImageIcon(Objects.requireNonNull(
                DisplaySummarizationViewModel.class.getClassLoader().getResource(path)));
        final Image resizedDailyForecastBox = originalDailyForecastBox.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedDailyForecastBox);
    }
}
