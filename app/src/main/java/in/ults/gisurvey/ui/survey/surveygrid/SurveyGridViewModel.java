package in.ults.gisurvey.ui.survey.surveygrid;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.HashSet;
import java.util.Set;

import dagger.Provides;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class SurveyGridViewModel extends BaseViewModel<SurveyGridNavigator> {

    public final MutableLiveData<Integer> propertyCount = new MutableLiveData<>();
    public final MutableLiveData<Integer> floorCount = new MutableLiveData<>();
    public final ObservableBoolean isAllPropertySurveyCompleted = new ObservableBoolean(false);

    public SurveyGridViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        propertyCount.setValue(0);
    }


    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        propertyCount.setValue(survey.getPropertyCount());
        floorCount.setValue(survey.getFloorCount());
        getCompletedSurveyProperty();
    }


    @Override
    protected void onPropertyIdSaved() {
        super.onPropertyIdSaved();
        getNavigator().navigateToNextPage();
    }

    protected String getCurrentSurveyId(){
        return getDataManager().getCurrentSurveyID();
    }

    public void getSyncDataCount(){
        getCompositeDisposable().add(getDataManager()
                .getSyncRowCount(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(count -> {
                    if (count != null) {

                            getNavigator().onFetchSyncCount(count);
                    }
                }).subscribe());
    }
    void getCompletedSurveyProperty() {
        if (!isAllPropertySurveyCompleted.get()) {
            getCompositeDisposable().add(getDataManager()
                    .getCompletedProperty(getDataManager().getCurrentSurveyID())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(properties -> {
                        if (properties != null && propertyCount.getValue() != null &&
                                properties.size() == propertyCount.getValue()) {
                            isAllPropertySurveyCompleted.set(true);
                        } else {
                            isAllPropertySurveyCompleted.set(false);
                        }
                        if (properties != null) {
                            Set<String> data = new HashSet<>();
                            for (Property item : properties) {
                                data.add(item.propertyID);
                            }
                            getNavigator().completedSurveyList(data);
                        }
                    })
                    .subscribe());
        }

    }


    public void completeSurvey() {
        getCompositeDisposable().add(getDataManager().setSurveyCompletedStatus(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getNavigator().completeSurvey())
                .subscribe());
    }
    /**
     * This is to delete property details on grid item deletion click
     * @param propertyId
     */
    void deleteProperty(String propertyId,int postion) {
        getCompositeDisposable().add(getDataManager()
                .deleteProperty(getDataManager().getCurrentSurveyID(), propertyId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if(aBoolean){
                        propertyCount.setValue(propertyCount.getValue()-1);
                        getNavigator().onDeletionSuccess(floorCount.getValue(),propertyCount.getValue(),postion);
                    }else{
                        getBaseActivity().showToast(getBaseActivity().getString(R.string.sync_grid_dltn_error_msg));
                    }

                })
                .subscribe());

    }

    /**
     * This is to edit particular property
     * @param propertyId
     * @param postion
     */
    void editProperty(String propertyId,int postion){
        getCompositeDisposable().add(getDataManager()
                .resetPropertyCompletionStatus(getDataManager().getCurrentSurveyID(), propertyId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getNavigator().onEditSuccess();
                })
                .subscribe());
    }



    /**
     * to set floor and property count in db then navigate to ground floor selection fragment
     * @param floorCount
     * @param propertyCount
     */
    void saveFloorPropertyCount(int floorCount, int propertyCount) {
        getCompositeDisposable().add(getDataManager()
                .insertFloorPropertyCount(floorCount, propertyCount, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {

                })
                .subscribe());
    }
}