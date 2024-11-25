package use_case.display_hourly;

public interface DisplayHourlyOutputBoundary {
    void prepareSuccessView(DisplayHourlyOutputData dailyHourlyOutputData);

    void prepareFailView(String errorMessage);
}
