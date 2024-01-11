package in.ults.gisurvey.ui.survey.pointstatus;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class PointStatusViewModel extends BaseViewModel<PointStatusNavigator> {


    public PointStatusViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to save point status in preference
     * @param pointStatus
     */
    void savePointStatus(String pointStatus) {
        getCompositeDisposable().add(getDataManager()
                .insertPointStatus(pointStatus, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getDataManager().setCurrentPointStatus(pointStatus);
                    getNavigator().setSelectedPointStatus(pointStatus);
                    if (isPointStatusBuilding()) {
                        getNavigator().navigateToFloorDetailsPage();
                    } else {
                        getNavigator().navigateToLocationDetailsPage();
                    }
                })
                .subscribe());
    }

    /**
     * get point status stored in db and set
     * @param survey
     */
    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        getNavigator().setPointStatus(survey.getPointStatus());
    }

    public void onNextClick() {
        getNavigator().savePointStatus();
    }

    /**
     * to update the point status
     */
    public void radioOnClick() {
        getNavigator().radioButtonClicked();
    }

    /**
     * clear survey details for the point status
     * @param pointStatus
     */
    void clearSurveyDetails(String pointStatus) {
        getCompositeDisposable().add(getDataManager()
                .insertPointStatus(pointStatus, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().clearSurveyDetails(getDataManager().getCurrentSurveyID()))
                .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().deleteProperties(getDataManager().getCurrentSurveyID()))
                .subscribe());
    }

    /**
     *On edit click change proprty suvey completed status to false
     */

    public void updateSurveyCompletedStatus(){
        getCompositeDisposable().add(getDataManager()
                .clearSurveyCompletedStatus(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getNavigator().onEdtSuccess();
                })
                .subscribe());


    }


    /**
     * Get count of unsynced property count if it is 0(No syncing done) then only edit option visible
     */
    public void getunsyncDataCount(){
        getCompositeDisposable().add(getDataManager()
                .getSyncRowCount(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(count -> {
                    if (count != null) {
                        if (count == 0) {
                            getNavigator().setEditOnTitleBar();
                        }
                    }
                }).subscribe());
    }
}