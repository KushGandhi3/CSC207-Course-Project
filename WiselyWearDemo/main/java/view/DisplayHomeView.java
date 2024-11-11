package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.display_home.*;

/**
 * The View for when the user is on the home page, displaying weather information.
 */
public class DisplayHomeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "home page";

    private final DisplayHomeViewModel displayHomeViewModel;
    private final JLabel temperatureLabel = new JLabel();
    private final JLabel cityLabel = new JLabel();

    //private DisplayHomeController displayHomeController;  // Not needed for
    // the demo program, since no input

    private final JButton hourlyButton = new JButton(DisplayHomeViewModel.HOURLY_BUTTON_LABEL);
    private final JButton weeklyButton = new JButton(DisplayHomeViewModel.WEEKLY_BUTTON_LABEL);
    private final JButton alertsButton = new JButton(DisplayHomeViewModel.ALERTS_BUTTON_LABEL);
    private final JButton outfitButton = new JButton(DisplayHomeViewModel.OUTFIT_BUTTON_LABEL);
    private final JButton mapButton = new JButton(DisplayHomeViewModel.MAP_BUTTON_LABEL);
    // add a text field for the user to input a city

    public DisplayHomeView(DisplayHomeViewModel displayHomeViewModel) {
        this.displayHomeViewModel = displayHomeViewModel;
        this.displayHomeViewModel.addPropertyChangeListener(this);




        // Work in progress, rewriting the initializer
        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.logoutController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }






    public DisplayHomeView(DisplayHomeViewModel displayHomeViewModel) {

        CommonWeatherFactory weatherFactory = new CommonWeatherFactory();
        this.weatherDataAccessObject = new WeatherDataAccessObject(weatherFactory);


        setLayout(new BorderLayout());

        cityLabel = new JLabel("City: ", SwingConstants.CENTER);
        temperatureLabel = new JLabel("Temperature: ", SwingConstants.CENTER);

        add(cityLabel, BorderLayout.NORTH);
        add(temperatureLabel, BorderLayout.CENTER);

        updateWeatherInfo();
    }

    private void updateWeatherInfo() {
        try {
            Weather weather = weatherDataAccessObject.getWeatherData();
            cityLabel.setText("City: " + weather.getLocation());
            temperatureLabel.setText("Temperature: " + weather.getCurrentTemperature() + "K");
        } catch (Exception e) {
            temperatureLabel.setText("Failed to get weather data");
            e.printStackTrace();
        }
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Implement property change events for the Display-Home View
        //  Use case
    }
}