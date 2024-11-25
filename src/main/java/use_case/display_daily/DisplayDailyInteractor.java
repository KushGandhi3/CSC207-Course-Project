package use_case.display_daily;

import constants.Constants;
import entity.weather.daily_weather.DailyWeatherData;
import entity.weather.daily_weather.DailyWeatherDataFactory;
import entity.weather.day_weather.DayWeatherData;
import exception.APICallException;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The interactor for the Display Daily use-case.
 */
public class DisplayDailyInteractor implements DisplayDailyInputBoundary {
    private final DisplayDailyDAI weatherDataAccessObject;
    private final DisplayDailyOutputBoundary displayDailyPresenter;

    public DisplayDailyInteractor(DisplayDailyDAI weatherDataAccessObject, DisplayDailyOutputBoundary displayDailyPresenter) {
        this.weatherDataAccessObject = weatherDataAccessObject;
        this.displayDailyPresenter = displayDailyPresenter;
    }

    @Override
    public void execute(DisplayDailyInputData displayDailyInputData) {
        final String city = displayDailyInputData.getCity();
        final DayOfWeek selectedWeekdayString = displayDailyInputData.getWeekday();
        
        try {
            final DailyWeatherData dailyWeatherData = weatherDataAccessObject.getDailyWeatherData(city);

            final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(dailyWeatherData.getTimezone()));
            final List<DayOfWeek> weekdays = new ArrayList<>(Constants.WEEK_SIZE);
            for (int i = 0; i < Constants.WEEK_SIZE; i++) {
                final ZonedDateTime current = zonedDateTime.plusDays(i);
                weekdays.add(zonedDateTime.getDayOfWeek());
            }

            switch (selectedWeekday) {
                case MONDAY:

                    break;
                case TUESDAY:

                    break;
                    case WEDNESDAY:
                        break;
                        case THURSDAY:
                            break;
                            case FRIDAY:
                                break;
                                case SATURDAY:
                                    break;
                                    case SUNDAY:
                                        break;

            }

            final int todayWeekday = zonedDateTime.getDayOfWeek();
            final List<DayWeatherData> dayWeatherDataList = dailyWeatherData.getDayWeatherDataList();



            DayWeatherData dayWeatherData = dayWeatherDataList.get(day);

            final DisplayDailyOutputData displayDailyOutputData = new DisplayDailyOutputData(dailyWeatherData);
        } catch (APICallException exception) {
            displayDailyPresenter.prepareFailView(exception.getMessage());
        }

    }

    @Override
    public void switchToHomeView() {
        displayDailyPresenter.switchToHomeView();
    }
}
