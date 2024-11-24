package entity.time;

import java.time.ZonedDateTime;

/**
 * Entity for the time data.
 */
public class ConcreteTimeData implements TimeData {

    private final ZonedDateTime zonedDateTime;

    public ConcreteTimeData(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Override
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
