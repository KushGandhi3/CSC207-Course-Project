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
     */
    public void execute() {
        // Execute the use case.
        mapUseCaseInteractor.execute(inputData);
    }
}