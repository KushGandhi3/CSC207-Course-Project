package interface_adapter.checker;

import use_case.checker.CheckerOutputBoundary;
import interface_adapter.ViewManagerModel;

/**
 * Presenter for the checker use case.
 */
public class CheckerPresenter implements CheckerOutputBoundary {

    private final CheckerViewModel checkerViewModel;
    private final ViewManagerModel viewManagerModel;

    public CheckerPresenter (CheckerViewModel checkerViewModel, ViewManagerModel viewManagerModel) {
        this.checkerViewModel = checkerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare the view when the condition is met for the checker related Use Case
     */
    @Override
    public void prepareCondMetView() {

        CheckerState checkerState = checkerViewModel.getState();
        checkerState.setMessage("exist");
        checkerViewModel.setState(checkerState);

        checkerViewModel.firePropertyChanged();
    }

    /**
     * Prepare the view when the condition is not met for the checker related Use Case
     */
    @Override
    public void prepareCondNotMetView() {

        CheckerState checkerState = checkerViewModel.getState();
        checkerState.setMessage("nonexist");
        checkerViewModel.setState(checkerState);

        checkerViewModel.firePropertyChanged();
    }
}
