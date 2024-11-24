package data_access.time;

import java.time.Instant;

public class TimeDAO {

    /**
     * Get the current time (in UTC) as a Unix timestamp.
     * @return the current time as a Unix timestamp.
     */
    public static long getCurrentUnixTime () {
        return Instant.now().getEpochSecond();
    }

    /**
     * Get the calculated time from current time(in UTC) as a Unix timestamp.
     * @param hours the next hours from current time.
     * @return the calculated time as a Unix timestamp.
     */
    public static long getCalculatedUnixTime (int hours) {
        return Instant.now().plusSeconds(hours * 3600L).getEpochSecond();
    }
}
