package entity.time;

import java.time.ZonedDateTime;

/**
 * Interface for factories that create TimeData entities.
 */
public interface TimeDataFactory {

    TimeData create(ZonedDateTime zonedDateTime);

}
