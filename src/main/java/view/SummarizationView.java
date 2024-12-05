package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.display_summarization.DisplaySummarizationController;
import interface_adapter.display_summarization.DisplaySummarizationState;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;

/**
 * The View for when the user wants to view the summarization of the data.
 */
public class SummarizationView extends JPanel implements PropertyChangeListener, ActionListener {

    private static final Font CRIMSONTITLE = FontManager.getCrimsonTextBold(80);
    private static final Font CRIMSONSUBTITLE = FontManager.getCrimsonTextBold(24);
    private static final int NUM_20 = 20;
    private static final int NUM_10 = 10;
    private static final String HTML_OPEN_TAG = "<html>";
    private static final String HTML_CLOSE_TAG = "</html>";

    private final String viewName = "Summarization View";
    private final DisplaySummarizationViewModel viewModel;
    private DisplaySummarizationController displaySummarizationController;

    // Components for the view
    private final JLabel title = new JLabel(DisplaySummarizationViewModel.TITLE_LABEL, JLabel.CENTER);
    private final JLabel summarySubtitle = new JLabel(DisplaySummarizationViewModel.SUMMARY_TITLE, JLabel.CENTER);
    private final JLabel outfitSubtitle = new JLabel(DisplaySummarizationViewModel.OUTFIT_LABEL, JLabel.CENTER);
    private final JLabel travelSubtitle = new JLabel(DisplaySummarizationViewModel.TRAVEL_LABEL, JLabel.CENTER);
    private final JLabel summaryLabel = new JLabel();
    private final JLabel outfitLabel = new JLabel();
    private final JLabel travelLabel = new JLabel();
    private final JButton backButton = new JButton(DisplaySummarizationViewModel.BACK_BUTTON_ICON);

    public SummarizationView(DisplaySummarizationViewModel displaySummarizationViewModel) {
        this.viewModel = displaySummarizationViewModel;
        this.viewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Top Panel
        backButton.setBorder(null);
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(title, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        // Configure Static Components
        title.setFont(CRIMSONTITLE);
        summarySubtitle.setFont(CRIMSONSUBTITLE);
        outfitSubtitle.setFont(CRIMSONSUBTITLE);
        travelSubtitle.setFont(CRIMSONSUBTITLE);

        // Main content panel
        final JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(NUM_20, NUM_20, NUM_20, NUM_20));

        // Add the cards to the content panel
        contentPanel.add(createCard(summarySubtitle, summaryLabel));
        contentPanel.add(Box.createHorizontalStrut(NUM_20));
        contentPanel.add(createCard(outfitSubtitle, outfitLabel));
        contentPanel.add(Box.createHorizontalStrut(NUM_20));
        contentPanel.add(createCard(travelSubtitle, travelLabel));

        this.add(contentPanel, BorderLayout.CENTER);

        // Set Labels
        setTextLabels(viewModel.getState());

        // Back Button Action
        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        this.displaySummarizationController.switchToHomeView();
                    }
                }
        );
    }

    private JPanel createCard(JLabel subtitle, JLabel body) {
        // Create the card panel
        final JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(NUM_10, NUM_10, NUM_10, NUM_10)));

        // Add subtitle and body to the card
        card.add(subtitle, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);

        return card;
    }

    private void setTextLabels(DisplaySummarizationState state) {
        summaryLabel.setText(HTML_OPEN_TAG + state.getWeatherSummary() + HTML_CLOSE_TAG);
        outfitLabel.setText(HTML_OPEN_TAG + state.getOutfitSuggestion() + HTML_CLOSE_TAG);
        travelLabel.setText(HTML_OPEN_TAG + state.getTravelAdvice() + HTML_CLOSE_TAG);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("update_data")) {
            displaySummarizationController.execute();
        }
        else {
            final DisplaySummarizationState currentState = (DisplaySummarizationState) evt.getNewValue();
            setTextLabels(currentState);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(DisplaySummarizationController controller) {
        this.displaySummarizationController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
