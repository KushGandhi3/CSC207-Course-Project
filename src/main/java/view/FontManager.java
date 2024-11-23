package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    private static Font crimsonText;
    private static Font inter;

    static {
        try {
            // Load Crimson Text font
            InputStream crimsonStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Regular.ttf");
            if (crimsonStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonText =
                    Font.createFont(Font.TRUETYPE_FONT,
                            crimsonStream).deriveFont(24f);

            // Load Inter font
            InputStream interStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/inter/Inter-Regular.ttf");
            if (interStream == null) {
                throw new IOException("Font file not found!");
            }
            inter = Font.createFont(Font.TRUETYPE_FONT,
                    interStream).deriveFont(24f);
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
            // Fallback to default fonts
            crimsonText = new Font("Serif", Font.PLAIN, 24);
            inter = new Font("SansSerif", Font.PLAIN, 24);
        }
    }

    public static Font getCrimsonText(float size) {
        return crimsonText.deriveFont(size);
    }

    public static Font getInter(float size) {
        return inter.deriveFont(size);
    }

}
