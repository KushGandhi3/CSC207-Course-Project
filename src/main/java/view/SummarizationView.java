package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import interface_adapter.display_summarization.DisplaySummarizationController;
import interface_adapter.display_summarization.DisplaySummarizationState;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;

/**
 * The View for when the user wants to analyze an outfit.
 */
public class SummarizationView extends JPanel implements PropertyChangeListener, ActionListener {

    // View Name
    public final String viewName = "Summarization View";

    // View Model
    private final DisplaySummarizationViewModel viewModel;

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

    public SummarizationView(DisplaySummarizationViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Initializing Font
        final Font crimsonTextTitle = FontManager.getCrimsonText(80);
        final Font crimsonTextSubtitle = FontManager.getCrimsonText(35);
        // final Font crimsonTextText = FontManager.getCrimsonText(15);

        // Initializing Title Labels
        this.titleLabel = new JLabel("AI Summarization", SwingConstants.CENTER);
        this.titleLabel.setFont(crimsonTextTitle);

        // Panel for title
        final JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        titlePanel.add(this.titleLabel, BorderLayout.CENTER);

        // Initializing Subtitle Labels
        this.weatherSummaryLabel = new JLabel("Weather Summary", SwingConstants.CENTER);
        this.weatherSummaryLabel.setFont(crimsonTextSubtitle);
        this.outfitSuggestionsLabel = new JLabel("Outfit Suggestions", SwingConstants.CENTER);
        this.outfitSuggestionsLabel.setFont(crimsonTextSubtitle);
        this.travelAdviceLabel = new JLabel("Travel Advice", SwingConstants.CENTER);
        this.travelAdviceLabel.setFont(crimsonTextSubtitle);

        // Initializing Text Labels
        this.weatherSummaryText = new JLabel("<html>Good morning, Toronto! Today is cloudy with a 60% chance of "
                +
                "rain, so you might want to grab an umbrella on your way out. Temperatures will range from a cool "
                +
                "8C this morning to a comfortable 14C by the afternoon. A light breeze at 16 km/h makes it a great"
                +
                " day for a cozy cafe visit!</html>", SwingConstants.CENTER);
        this.weatherSummaryText.setVerticalAlignment(SwingConstants.TOP);
        this.outfitSuggestionsText = new JLabel("<html>In Toronto today, its a cool and rainy day-perfect for "
                +
                " layering! A light sweater with a waterproof jacket will keep you comfy and dry. Pair it with "
                +
                "sturdy boots for puddles, and don't forget an umbrella for the rain. If you're out in the evening, "
                +
                "consider adding a scarf for extra warmth.</html>", SwingConstants.CENTER);
        this.outfitSuggestionsText.setVerticalAlignment(SwingConstants.TOP);
        this.travelAdviceText = new JLabel("<html>Travelling today in Toronto? With a 60% chance of rain, wet "
                +
                "roads might cause delays, so plan extra time for your commute. Drive carefully and keep a safe"
                +
                " distance from other vehicles. If you're walking, wear waterproof shoes to avoid soggy feet. Public"
                +
                " transit is a great option to stay dry and avoid traffic congestion!</html>", SwingConstants.CENTER);
        this.travelAdviceText.setVerticalAlignment(SwingConstants.TOP);

        // Panel for Weather Summary
        final JPanel weatherPanel = createAlignedPanel(this.weatherSummaryLabel, this.weatherSummaryText);

        // Panel for Outfit Suggestions
        final JPanel outfitPanel = createAlignedPanel(this.outfitSuggestionsLabel, this.outfitSuggestionsText);

        // Panel for Travel Advice
        final JPanel travelPanel = createAlignedPanel(this.travelAdviceLabel, this.travelAdviceText);

        // Main Panel for subtitles and text
        final JPanel contentPanel = new JPanel(new GridLayout(1, 3, 50, 0));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.add(weatherPanel);
        contentPanel.add(outfitPanel);
        contentPanel.add(travelPanel);

        // Add All Components
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a JPanel with vertically aligned components.
     *
     * @param subtitle the subtitle JLabel to be added to the panel
     * @param text the text JLabel to be added to the panel
     * @return a JPanel with the given JLabels aligned and added in a vertical layout
     */
    private JPanel createAlignedPanel(JLabel subtitle, JLabel text) {
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(text);

        return panel;
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
