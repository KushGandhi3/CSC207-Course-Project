package app;

import javax.swing.JFrame;

// TODO: Implement the Main class [TEAM]

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame frame = appBuilder.build();
        frame.setVisible(true);
    }
}
