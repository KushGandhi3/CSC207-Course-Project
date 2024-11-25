package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.display_summarization.DisplaySummarizationController;
import interface_adapter.display_summarization.DisplaySummarizationState;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;

/*
 * The View for when the user wants to analyze an outfit.
 */
public class SummarizationView extends JPanel implements PropertyChangeListener, ActionListener {

    // View Model
    private final DisplaySummarizationViewModel viewModel;

    // View Name
    public final String viewName = "Summarization View";

    // Title Label
    private final JLabel titleLabel;

    // Weather Summary
    private final JLabel weatherSummaryLabel;
    private final JLabel weatherSummaryText;

    // Outfit Suggestions
    private final JLabel outfitSuggestionsLabel;
    private final JLabel outfitSuggestionsText;

    // Travel Advice
    private final JLabel travelAdviceLabel;
    private final JLabel travelAdviceText;

    // Back Button
    private final JButton backButton;

    // Refresh Button
    private final JButton refreshButton;

    public SummarizationView(DisplaySummarizationViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Initializing Font
        final Font crimsonTextTitle = FontManager.getCrimsonText(80);
        final Font crimsonTextSubtitle = FontManager.getCrimsonText(24);
        final Font crimsonTextText = FontManager.getCrimsonText(16);

        // Initializing Title Labels
        this.titleLabel = new JLabel("AI Summarization");
        this.titleLabel.setFont(crimsonTextTitle);
        this.titleLabel.setBounds(162, 55, 875, 125);

        // Initializing Subtitle Labels
        this.weatherSummaryLabel = new JLabel("Weather Summary");
        this.weatherSummaryLabel.setFont(crimsonTextSubtitle);
        this.weatherSummaryLabel.setBounds(151, 282, 230, 31);
        this.outfitSuggestionsLabel = new JLabel("Outfit Suggestions");
        this.outfitSuggestionsLabel.setFont(crimsonTextSubtitle);
        this.outfitSuggestionsLabel.setBounds(485, 282, 230, 31);
        this.travelAdviceLabel = new JLabel("Travel Advice");
        this.travelAdviceLabel.setFont(crimsonTextSubtitle);
        this.travelAdviceLabel.setBounds(823, 282, 230, 31);

        // Initializing Text Labels
        this.weatherSummaryText = new JLabel("");
        this.weatherSummaryText.setFont(crimsonTextText);
        this.weatherSummaryText.setBounds(147, 323, 234, 195);
        this.outfitSuggestionsText = new JLabel("");
        this.outfitSuggestionsText.setFont(crimsonTextText);
        this.outfitSuggestionsText.setBounds(481, 323, 234, 195);
        this.travelAdviceText = new JLabel("");
        this.travelAdviceText.setFont(crimsonTextText);
        this.travelAdviceText.setBounds(819, 323, 234, 195);

        // Initializing Back Button
        final ImageIcon backIcon = new ImageIcon("src/main/resources/back.png");
        this.backButton = new JButton(backIcon);
        this.backButton.setActionCommand("back");
        this.backButton.addActionListener(this);
        this.backButton.setBounds(7, 6, 34, 24);

        // Initializing Refresh Button
        final ImageIcon refreshIcon = new ImageIcon("src/main/resources/refresh.png");
        this.refreshButton = new JButton(refreshIcon);
        this.refreshButton.setActionCommand("refresh");
        this.refreshButton.addActionListener(this);
        this.refreshButton.setBounds(541, 679, 117, 59);

        // Add All Components
        this.add(this.titleLabel);
        this.add(this.weatherSummaryLabel);
        this.add(this.weatherSummaryText);
        this.add(this.outfitSuggestionsLabel);
        this.add(this.outfitSuggestionsText);
        this.add(this.travelAdviceLabel);
        this.add(this.travelAdviceText);
        this.add(this.backButton);
        this.add(this.refreshButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Handle property changes here
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Handle action events here
    }
}
