package in.ults.gisurvey.ui.main.syncgrid;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class SyncGridViewModel extends BaseViewModel<SyncGridNavigator> {

    public final MutableLiveData<Integer> totalSurvey = new MutableLiveData<>();

    public SyncGridViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setTotalSurveyCount(int totalSurvey) {
        this.totalSurvey.setValue(totalSurvey);
    }


    @Override
    protected void onFetchUnSyncData(List<SurveyWithProperty> surveyWithProperties) {

        /*for (int i = 0; i < 500; i++) {

            long surveyid = 45678 + i;
            Survey survey = new Survey(surveyid);
            survey.surveyCompletedStatus = true;
            survey.syncCompleted = false;
            getCompositeDisposable().add(getDataManager()
                    .insertQFieldID(survey)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().io())
                    .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> getDataManager().insertProperty(surveyid,surveyid))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(aBoolean -> {
                    })
                    .subscribe());
        }*/

        if (surveyWithProperties != null) {
            setTotalSurveyCount(surveyWithProperties.size());
        }
    }
}