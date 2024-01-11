package in.ults.gisurvey.ui.survey.groundfloorselection;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class GroundFloorSelectionViewModel extends BaseViewModel<GroundFloorSelectionNavigator> {


    private final ObservableField<Integer> floorCount = new ObservableField<>(0);
    private final ObservableField<Integer> propCount = new ObservableField<>(0);
    private final ObservableField<Integer> selectedPosition = new ObservableField<>(-1);


    public GroundFloorSelectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        floorCount.set(survey.getFloorCount());
        propCount.set(survey.getPropertyCount());
        selectedPosition.set(survey.getGroundFloor());
    }

    /**
     * set ground floor in db and navigate to next page
     * @param groundFloor
     */
    void insertGroundFloor(int groundFloor) {
        selectedPosition.set(groundFloor);
        getCompositeDisposable().add(getDataManager()
                .insertGroundFloor(groundFloor, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if (groundFloor == 0 || propCount.get()<=1 ) {
                        getNavigator().navigateToLocationPage();
                    } else {
                        getNavigator().navigateToBasementPage();
                    }
                })
                .subscribe());
    }


    void clearFloorRelatedData() {
        getCompositeDisposable().add(getDataManager()
                .clearFloorRelatedSurveyData(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> getDataManager().clearFloorRelatedPropertyData(getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId()))
                .subscribe());
    }


    public ObservableField<Integer> getFloorCount() {
        return floorCount;
    }

    public ObservableField<Integer> getSelectedPosition() {
        return selectedPosition;
    }

    public void onNextClick() {
        getNavigator().saveGroundFloorCount();
    }

}
