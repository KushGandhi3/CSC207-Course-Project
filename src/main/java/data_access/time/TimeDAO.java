package data_access.time;

import entity.time.TimeData;
import entity.time.TimeDataFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeDAO {

    private final TimeDataFactory timeDataFactory;

    public TimeDAO(TimeDataFactory timeDataFactory) {
        this.timeDataFactory = timeDataFactory;
    }

    /**
     * Get the current date and time in a particular timezone.
     * @param timezone the timezone for the date and time
     * @return ZonedDateTime object
     */
    public TimeData getCurrentZonedDateTime(String timezone) {
        return this.timeDataFactory.create(ZonedDateTime.now(ZoneId.of(timezone)));
    }

}
