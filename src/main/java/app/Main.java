package app;

import javax.swing.*;

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addSummarizationView()
                .addHomeView()
                .addDailyView()
                .addHistoryView()
                .addCheckerView()
                .addHourlyView()
                .addDisplaySummarizationUseCase()
                .addDisplayHistoryUseCase()
                .addDisplayDailyUseCase()
                .addDisplayCheckerUseCase()
                .addDisplayHomeUseCase()
                .addDisplayHourlyUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
