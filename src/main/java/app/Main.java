package app;

import javax.swing.JFrame;

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addHomeView()
                .addHourlyView()
                .addDailyView()
                .addHistoryView()
                .addCheckerView()
                .addSummarizationView()
                .addDisplayHomeUseCase()
                .addDisplayHourlyUseCase()
                .addDisplayDailyUseCase()
                .addDisplayHistoryUseCase()
                .addDisplayCheckerUseCase()
                .addDisplaySummarizationUseCase()
                .build();

        application.setVisible(true);
    }
}
