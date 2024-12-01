package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interface_adapter.display_history.DisplayHistoryController;
import interface_adapter.display_history.DisplayHistoryState;
import interface_adapter.display_history.DisplayHistoryViewModel;

/**
 * The View for when the user wants to see previous cities.
 */
public class HistoryView extends JPanel implements PropertyChangeListener {

    private static final Font CRIMSONTITLE = FontManager.getCrimsonTextBold(80);
    private static final Font CRIMSONSUBTITLE = FontManager.getCrimsonTextBold(40);

    private final String viewName = "History View";
    private final DisplayHistoryViewModel viewModel;
    private DisplayHistoryController displayHistoryController;

    // Components for the view
    private final JLabel title = new JLabel(DisplayHistoryViewModel.TITLE_LABEL, JLabel.CENTER);
    private final JButton backButton = new JButton(DisplayHistoryViewModel.BACK_BUTTON_ICON);
    private final JPanel cityListPanel = new JPanel();

    public HistoryView(DisplayHistoryViewModel displayHistoryViewModel) {
        this.viewModel = new DisplayHistoryViewModel();
        this.viewModel.addPropertyChangeListener(this);

        // Configure Main Panel
        setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Top Panel
        backButton.setBorder(null);
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(title, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        // Title Styling
        title.setFont(CRIMSONTITLE);

        // City List Panel
        cityListPanel.setLayout(new BoxLayout(cityListPanel, BoxLayout.Y_AXIS));
        cityListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cityListPanel.setBackground(Color.WHITE);

        this.add(cityListPanel, BorderLayout.CENTER);

        // Back Button Action
        backButton.addActionListener(evt -> {
            if (evt.getSource().equals(backButton)) {
                displayHistoryController.switchToHomeView();
            }
        });

        // Set City Labels
        setCityLabels(viewModel.getState());
    }

    private void setCityLabels(DisplayHistoryState state) {
        cityListPanel.removeAll();

        // Get the city history and limit it to 10
        final List<String> limitedCityHistory = state.getCities().stream()
                .limit(10)
                .toList();

        // Add city buttons
        for (String city : limitedCityHistory) {
            final JButton cityButton = new JButton(city);
            cityButton.setFont(CRIMSONSUBTITLE);
            cityButton.setAlignmentX(CENTER_ALIGNMENT);

            cityButton.addActionListener(evt -> {
                displayHistoryController.execute(city);
            });

            cityListPanel.add(cityButton);
            cityListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        cityListPanel.revalidate();
        cityListPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DisplayHistoryState currentState = (DisplayHistoryState) evt.getNewValue();
        setCityLabels(currentState);
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(DisplayHistoryController controller) {
        this.displayHistoryController = controller;
    }
}
