package interface_adapter.map;

import use_case.map.MapInputBoundary;

/**
 * Controller for the map Use Case.
 */
public class MapController {
    private final MapInputBoundary mapUseCaseInteractor;

    public MapController(MapInputBoundary mapUseCaseInteractor) {
        this.mapUseCaseInteractor = mapUseCaseInteractor;
    }

    /**
     * Executes the map Use Case.
     * @param location the location for which to fetch the weather forecast for the map
     */
    public void execute(String location) {
        final MapInputData inputData = new MapInputData(location);

        // Execute the use case, which fetches the data for map.
        mapUseCaseInteractor.execute(inputData);
    }
}