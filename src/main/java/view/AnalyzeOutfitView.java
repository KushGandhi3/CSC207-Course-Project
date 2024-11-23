package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.ViewModel;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import interface_adapter.analyze_outfit.AnalyzeOutfitController;
import interface_adapter.analyze_outfit.AnalyzeOutfitState;
import interface_adapter.analyze_outfit.AnaylzeOutfitViewModel;

/*
 * The View for when the user wants to analyze an outfit.
 */
public class AnalyzeOutfitView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "Analyze Outfit";
    private final AnaylzeOutfitViewModel analyzeOutfitViewModel;

    private final JLabel titleLabel;
    private final JLabel webcamLabel;

    private final JButton analyzeButton;
    private final JButton backButton;

    private final VideoCapture capture;

    public AnalyzeOutfitView(AnaylzeOutfitViewModel analyzeOutfitViewModel) {

        this.analyzeOutfitViewModel = analyzeOutfitViewModel;
        this.analyzeOutfitViewModel.addPropertyChangeListener(this);

        // Webcam Initialization
        capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            throw new RuntimeException("Error: Webcam not found.");
        }

        // Set the layout
        this.setLayout(null); // Absolute positioning

        // Create the title label
        titleLabel = new JLabel("Analyze Outfit");
        titleLabel.setBounds(162, 125, 875, 125);
        final Font titleFont = new Font("Arial", Font.PLAIN, 96);
        titleLabel.setFont(titleFont);
        this.add(titleLabel);

        // Create the webcam label
        webcamLabel = new JLabel();
        webcamLabel.setBounds(198, 282, 803, 386);
        this.add(webcamLabel);

        // Create the analyze button
        final ImageIcon analyzeIcon = new ImageIcon("src/main/resources/analyze.png");
        analyzeButton = new JButton(analyzeIcon);
        analyzeButton.setBounds(520, 701, 159, 77);
        analyzeButton.addActionListener(this);
        this.add(analyzeButton);

        // Create the back button
        final ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        backButton = new JButton(backIcon);
        backButton.setBounds(7, 6, 34, 24);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AnalyzeOutfitState state = (AnalyzeOutfitState) evt.getNewValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
