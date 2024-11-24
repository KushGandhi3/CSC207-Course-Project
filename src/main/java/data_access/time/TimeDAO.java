package data_access.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeDAO {

    /**
     * Get the current date and time in a particular timezone.
     * @param timezone the timezone for the date and time
     * @return ZonedDateTime object
     */
    public static ZonedDateTime getCurrentZonedDateTime(String timezone) {
        return ZonedDateTime.now(ZoneId.of(timezone));
    }

}
