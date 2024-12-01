package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import constants.Constants;
import interface_adapter.dislay_history.DisplayHistoryController;
import interface_adapter.dislay_history.DisplayHistoryState;
import interface_adapter.dislay_history.DisplayHistoryViewModel;
import interface_adapter.display_daily.DisplayDailyState;


import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final DisplayHistoryViewModel displayHistoryViewModel;
    private DisplayHistoryController displayHistoryController;

    private final JButton backButton = new JButton(DisplayHistoryViewModel.BACK_BUTTON_IMAGE);

    public HistoryView(DisplayHistoryViewModel displayHistoryViewModel, DisplayHistoryViewModel displayHistoryViewModel1) {
        this.displayHistoryViewModel = displayHistoryViewModel;
        this.displayHistoryViewModel.addPropertyChangeListener(this);

        // Set the layout and border of the panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and style the back button
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);

        // Back button panel
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);

        // Title label
        JLabel titleLabel = new JLabel(DisplayHistoryViewModel.TITLE_LABEL, SwingConstants.CENTER);
        titleLabel.setFont(FontManager.getCrimsonTextRegular(Constants.TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button for each location
        for (String location : displayHistoryViewModel.getState().getLocations()) {
            JButton locationButton = new JButton(location);

            // setting font for the button text
            locationButton.setFont(FontManager.getCrimsonTextRegular(Constants.LABEL_FONT_SIZE));
            locationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add action listener to the location button
            locationButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(locationButton)) {
                            // switch to home view and set the location
                            displayHistoryController.switchToHomeView(location);
                        }
                    }
            );

            contentPanel.add(locationButton);
        }

        // Add action listener to the back button
        backButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(backButton)) {
                        displayHistoryController.switchToHomeView();
                    }
                }
        );

        // Add the components to the panel
        this.add(backButtonPanel, BorderLayout.NORTH);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO fix this
        final DisplayHistoryState currentState = (DisplayHistoryState) evt.getNewValue();
        setLabels(currentState);
    }
}
