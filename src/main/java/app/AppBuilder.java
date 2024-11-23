package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// Data Access Objects

// View Models Manager

// Home Page
import interface_adapter.display_checker.DisplayCheckerViewModel;
import interface_adapter.display_home.DisplayHomeViewModel;
import view.CheckerView;
import view.HomeView;

//// Daily Page
//import use_case.display_daily.DisplayDailyDataAccessInterface;
//import use_case.display_daily.DisplayDailyInteractor;
//import use_case.display_daily.DisplayDailyInputBoundary;
//import use_case.display_daily.DisplayDailyOutputBoundary;
//import interface_adapter.display_daily.DisplayDailyController;
//import interface_adapter.display_daily.DisplayDailyPresenter;
//import interface_adapter.display_daily.DisplayDailyState;
//import interface_adapter.display_daily.DisplayDailyViewModel;
//import view.DailyView;
//
//// Weekly Page
//import use_case.display_weekly.DisplayWeeklyDataAccessInterface;
//import use_case.display_weekly.DisplayWeeklyInteractor;
//import use_case.display_weekly.DisplayWeeklyInputBoundary;
//import use_case.display_weekly.DisplayWeeklyOutputBoundary;
//import interface_adapter.display_weekly.DisplayWeeklyController;
//import interface_adapter.display_weekly.DisplayWeeklyPresenter;
//import interface_adapter.display_weekly.DisplayWeeklyState;
//import interface_adapter.display_weekly.DisplayWeeklyViewModel;
//import view.WeeklyView;
//
//// Alerts Page
//import use_case.set_alerts.SetAlertsDataAccessInterface;
//import use_case.set_alerts.SetAlertsInteractor;
//import use_case.set_alerts.SetAlertsInputBoundary;
//import use_case.set_alerts.SetAlertsOutputBoundary;
//import interface_adapter.set_alerts.SetAlertController;
//import interface_adapter.set_alerts.SetAlertPresenter;
//import interface_adapter.set_alerts.SetAlertState;
//import interface_adapter.set_alerts.SetAlertViewModel;
//import view.SetAlertsView;
//
//// Outfit Page
//import use_case.analyze_outfit.AnalyzeOutfitDataAccessInterface;
//import use_case.analyze_outfit.AnalyzeOutfitInteractor;
//import use_case.analyze_outfit.AnalyzeOutfitInputBoundary;
//import use_case.analyze_outfit.AnalyzeOutfitOutputBoundary;
//import interface_adapter.analyze_outfit.AnalyzeOutfitController;
//import interface_adapter.analyze_outfit.AnalyzeOutfitPresenter;
//import interface_adapter.analyze_outfit.AnalyzeOutfitState;
//import interface_adapter.analyze_outfit.AnaylzeOutfitViewModel;
//import view.AnalyzeOutfitView;
//

/**
 * The AppBuilder class is responsible for building the application.
 */
public class AppBuilder {

    // Swing Components
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    private CheckerView checkerView;
    private DisplayCheckerViewModel displayCheckerViewModel;

    public JPanel buildViews() {
        cardPanel.add(new HomeView(new DisplayHomeViewModel()), "Home");
        // TODO: Add the other views here
        return cardPanel;
    }

    /**
     * Adds the Checker View to the application
     * @return this builder
     */
    public AppBuilder addCheckerView() {
        displayCheckerViewModel = new DisplayCheckerViewModel();
        checkerView = new CheckerView(displayCheckerViewModel);
        cardPanel.add(checkerView, checkerView.getViewName());
        return this;
    }

    /**
     *Builds the application.
     * @return the JFrame of the application.
     */
    public JFrame build() {
        final JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setResizable(false);

        frame.add(buildViews());
        return frame;
    }
}
