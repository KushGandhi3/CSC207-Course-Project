package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    private static Font crimsonTextRegular;
    private static Font interTextRegular;
    private static Font crimsonTextBold;
    private static Font interTextBold;
    private static Font crimsonTextItalic;
    private static Font interTextItalic;

    static {
        try {
            // load Crimson Text Regular font
            InputStream crimsonTextRegularStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Regular.ttf");
            if (crimsonTextRegularStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonTextRegular = Font.createFont(Font.TRUETYPE_FONT, crimsonTextRegularStream).deriveFont(24f);

            // load Inter Text Regular font
            InputStream interTextRegularStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/inter/static/Inter_24pt-Black.ttf");
            if (interTextRegularStream == null) {
                throw new IOException("Font file not found!");
            }
            interTextRegular = Font.createFont(Font.TRUETYPE_FONT, interTextRegularStream).deriveFont(24f);

            // load Crimson Text Bold font
            InputStream crimsonTextBoldStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Bold.ttf");
            if (crimsonTextBoldStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonTextBold = Font.createFont(Font.TRUETYPE_FONT, crimsonTextBoldStream).deriveFont(24f);

            // load Inter Text Bold font
            InputStream interTextBoldStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/inter/static/Inter_24pt-Bold.ttf");
            if (interTextBoldStream == null) {
                throw new IOException("Font file not found!");
            }
            interTextBold = Font.createFont(Font.TRUETYPE_FONT, interTextBoldStream).deriveFont(24f);

            // load Crimson Text Italic font
            InputStream crimsonTextItalicStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/crimson_text/CrimsonText-Italic.ttf");
            if (crimsonTextItalicStream == null) {
                throw new IOException("Font file not found!");
            }
            crimsonTextItalic = Font.createFont(Font.TRUETYPE_FONT, crimsonTextItalicStream).deriveFont(24f);

            // load Inter Text Italic font
            InputStream interTextItalicStream =
                    FontManager.class.getClassLoader().getResourceAsStream(
                            "fonts/inter/static/Inter_24pt-Italic.ttf");
            if (interTextItalicStream == null) {
                throw new IOException("Font file not found!");
            }
            interTextItalic = Font.createFont(Font.TRUETYPE_FONT, interTextItalicStream).deriveFont(24f);

        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
            // fallback to default fonts
            crimsonTextRegular = new Font("Serif", Font.PLAIN, 24);
            interTextRegular = new Font("SansSerif", Font.PLAIN, 24);
            crimsonTextBold = new Font("Serif", Font.BOLD, 24);
            interTextBold = new Font("SansSerif", Font.BOLD, 24);
            crimsonTextItalic = new Font("Serif", Font.ITALIC, 24);
            interTextItalic = new Font("SansSerif", Font.ITALIC, 24);
        }
    }

    public static Font getCrimsonText(float size) {
        return crimsonTextRegular.deriveFont(size);
    }

    public static Font getInter(float size) {
        return interTextRegular.deriveFont(size);
    }

    public static Font getCrimsonTextBold(float size) {
        return crimsonTextBold.deriveFont(size);
    }

    public static Font getInterTextBold(float size) {
        return interTextBold.deriveFont(size);
    }

    public static Font getCrimsonTextItalic(float size) {
        return crimsonTextItalic.deriveFont(size);
    }

    public static Font getInterTextItalic(float size) {
        return interTextItalic.deriveFont(size);
    }

}
