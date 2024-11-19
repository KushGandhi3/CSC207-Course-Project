package use_case.display_map;

import entity.map.Map;

/**
 * Interface for the Display Map use-case data access object.
 */
public interface DisplayMapDataAccessInterface {

    public Map getMap(String city);

}
