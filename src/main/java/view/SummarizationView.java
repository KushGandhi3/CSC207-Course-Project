package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
public class SummarizationView extends JPanel implements PropertyChangeListener {

    private static final Font CRIMSONTITLE = FontManager.getCrimsonTextBold(80);
    private static final Font CRIMSONSUBTITLE = FontManager.getCrimsonTextBold(40);

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

        // Top Panel
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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the cards to the content panel
        contentPanel.add(createCard(summarySubtitle, summaryLabel));
        contentPanel.add(Box.createHorizontalStrut(20));
        contentPanel.add(createCard(outfitSubtitle, outfitLabel));
        contentPanel.add(Box.createHorizontalStrut(20));
        contentPanel.add(createCard(travelSubtitle, travelLabel));

        this.add(contentPanel, BorderLayout.CENTER);

        // Set Labels
        setTextLabels(viewModel.getState());

        // Back Button Action
        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                displaySummarizationController.switchToHomeView();
            }
        });
    }

    private JPanel createCard(JLabel subtitle, JLabel body) {
        // Create the card panel
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Add subtitle and body to the card
        card.add(subtitle, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);

        return card;
    }

    private void setTextLabels(DisplaySummarizationState state) {
        summaryLabel.setText("<html>" + state.getWeatherSummary() + "</html>");
        outfitLabel.setText("<html>" + state.getOutfitSuggestion() + "</html>");
        travelLabel.setText("<html>" + state.getTravelAdvice() + "</html>");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DisplaySummarizationState currentState = (DisplaySummarizationState) evt.getNewValue();
        setTextLabels(currentState);
    }
}
