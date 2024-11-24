package entity.time;

import java.time.ZonedDateTime;

/**
 * Factory for creating TimeData objects.
 */
public class ConcreteTimeDataFactory implements TimeDataFactory {

    @Override
    public TimeData create(ZonedDateTime zonedDateTime) {
        return new ConcreteTimeData(zonedDateTime);
    }

}
