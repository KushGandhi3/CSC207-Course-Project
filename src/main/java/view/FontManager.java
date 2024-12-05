package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    private static Font crimsonTextRegular;
    private static Font crimsonTextBold;
    private static final float FONT_SIZE = 24f;
    private static final int FONT_SIZE2 = 24;

    static {
        try {
            // load Crimson Text Regular font
            final InputStream crimsonTextRegularStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Regular.ttf");
            if (crimsonTextRegularStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonTextRegular = Font.createFont(Font.TRUETYPE_FONT, crimsonTextRegularStream).deriveFont(FONT_SIZE);

            // load Crimson Text Bold font
            final InputStream crimsonTextBoldStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Bold.ttf");
            if (crimsonTextBoldStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonTextBold = Font.createFont(Font.TRUETYPE_FONT, crimsonTextBoldStream).deriveFont(FONT_SIZE);

        }
        catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
            // fallback to default fonts
            crimsonTextRegular = new Font("Serif", Font.PLAIN, FONT_SIZE2);
            crimsonTextBold = new Font("Serif", Font.BOLD, FONT_SIZE2);
        }
    }

    /**
     * Returns a Font object derived from the Crimson Text Regular font
     * with the specified size.
     * @param size the desired font size
     * @return a Font object with the Crimson Text Regular style and the specified size
     */
    public static Font getCrimsonTextRegular(float size) {
        return crimsonTextRegular.deriveFont(size);
    }

    /**
     * Returns a Font object derived from the Crimson Text Bold font
     * with the specified size.
     * @param size the desired font size
     * @return a Font object with the Crimson Text Bold style and the specified size
     */
    public static Font getCrimsonTextBold(float size) {
        return crimsonTextBold.deriveFont(size);
    }
}
