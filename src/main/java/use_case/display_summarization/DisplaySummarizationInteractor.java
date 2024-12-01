package use_case.display_summarization;

import entity.recent_city.RecentCityData;
import entity.summarization.Summarization;
import entity.weather.hour_weather.HourWeatherData;
import entity.weather.hourly_weather.HourlyWeatherData;
import exception.APICallException;
import exception.RecentCitiesDataException;

/**
 * Interactor for the Display Summarization use case.
 */
public class DisplaySummarizationInteractor implements DisplaySummarizationInputBoundary {
    private final DisplaySummarizationRecentCitiesDAI recentCitiesDAO;
    private final DisplaySummarizationWeatherDAI weatherDAO;
    private final DisplaySummarizationSummaryDAI summaryDAO;
    private final DisplaySummarizationOutputBoundary displaySummarizationPresenter;

    public DisplaySummarizationInteractor(DisplaySummarizationRecentCitiesDAI recentCitiesDAO,
                                          DisplaySummarizationWeatherDAI weatherDAO,
                                          DisplaySummarizationSummaryDAI summaryDAO,
                                          DisplaySummarizationOutputBoundary displaySummarizationPresenter) {
        this.recentCitiesDAO = recentCitiesDAO;
        this.weatherDAO = weatherDAO;
        this.summaryDAO = summaryDAO;
        this.displaySummarizationPresenter = displaySummarizationPresenter;
    }

    @Override
    public void execute() {

        // Declare variables
        final String city;
        final HourlyWeatherData hourlyWeatherData;
        final Summarization summarization;

        // Get the most recent city
        try {
            final RecentCityData recentCityData = recentCitiesDAO.getRecentCityData();
            city = recentCityData.getRecentCityList().getFirst();
        }
        catch (RecentCitiesDataException exception) {
            displaySummarizationPresenter.prepareFailureView(exception.getMessage());
            return;
        }

        // Get the hourly weather data
        try {
            hourlyWeatherData = weatherDAO.getHourlyWeatherData(city);
        }
        catch (APICallException exception) {
            displaySummarizationPresenter.prepareFailureView(exception.getMessage());
            return;
        }

        // Get the summarization
        try {
            summarization = summaryDAO.getSummarization(promptGenerator(hourlyWeatherData));
        }
        catch (APICallException exception) {
            displaySummarizationPresenter.prepareFailureView(exception.getMessage());
            return;
        }

        // Prepare the success view
        final DisplaySummarizationOutputData response = new DisplaySummarizationOutputData(summarization
                .getWeatherSummary(), summarization.getOutfitSuggestion(), summarization.getTravelAdvice());
        displaySummarizationPresenter.prepareSuccessView(response);
    }

    private String promptGenerator(HourlyWeatherData hourlyWeatherData) {

        final StringBuilder promptBuilder = new StringBuilder();

        // Add general instructions
        promptBuilder.append("Based on the following weather data, provide:\n")
                .append("1. A weather summary\n")
                .append("2. An outfit suggestion\n")
                .append("3. Travel advice\n")
                .append("The output should be in JSON format.\n\n");

        // Add location information
        promptBuilder.append("Weather data:\n")
                .append("City: ").append(hourlyWeatherData.getCity()).append("\n");

        // Add temperature information
        promptBuilder.append("Temperature:\n")
                .append("Low: ").append(hourlyWeatherData.getLowTemperature()).append("\n")
                .append("High: ").append(hourlyWeatherData.getHighTemperature()).append("\n");

        // Add hourly weather data
        if (hourlyWeatherData.getHourWeatherDataList() != null && !hourlyWeatherData
                .getHourWeatherDataList().isEmpty()) {
            promptBuilder.append("Hourly Forecast:\n");

            // Limit to a few hours for concise prompts
            final int maxHoursToInclude = 6;
            for (int i = 0; i < Math.min(maxHoursToInclude, hourlyWeatherData.getHourWeatherDataList().size()); i++) {
                final HourWeatherData hourData = hourlyWeatherData.getHourWeatherDataList().get(i);
                promptBuilder.append("- Hour ").append(i + 1).append(": ")
                        .append("Temperature: ").append(hourData.getTemperature()).append("degrees celsius, ")
                        .append("Conditions: ").append(hourData.getCondition()).append("\n");
            }
        }
        else {
            promptBuilder.append("No hourly forecast data available.\n");
        }

        return promptBuilder.toString();
    }

    @Override
    public void switchToHomeView() {
        displaySummarizationPresenter.switchToHomeView();
    }
}
