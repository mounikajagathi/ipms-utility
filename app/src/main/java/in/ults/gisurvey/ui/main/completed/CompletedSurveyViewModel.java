package in.ults.gisurvey.ui.main.completed;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class CompletedSurveyViewModel extends BaseViewModel<CompletedSurveyNavigator> {

    private final MutableLiveData<List<SurveyWithProperty>> completedSurveyList = new MutableLiveData<>();
    private final MutableLiveData<String> totalCount=new MutableLiveData<String>();

    public CompletedSurveyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public MutableLiveData<String> getTotalCount() {
        return totalCount;
    }
    public MutableLiveData<List<SurveyWithProperty>> getCompletedSurveyList() {
        return completedSurveyList;
    }

    public void setCompletedSurveyList(List<SurveyWithProperty> completedSurveyList) {
        this.completedSurveyList.setValue(completedSurveyList);
        this.totalCount.setValue(getBaseActivity().getString(R.string.cmn_count)+" "+ completedSurveyList.size());
    }

    @Override
    protected void onFetchUnSyncData(List<SurveyWithProperty> surveyWithProperties) {
       setCompletedSurveyList(surveyWithProperties);

    }

    /**
     * to save survey id in shared preference and navigate to partial survey
     */
    public void saveSurveyId(String surveyId) {
        saveCurrentSurveyIdInPref(surveyId, AppConstants.SURVEY_OPEN_MODE_VIEW);
        getNavigator().navigateToNextPage();
    }


}