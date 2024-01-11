package in.ults.gisurvey.ui.serverSurvey.list;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.SurveyPoints;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class ServerSurveyViewModel extends BaseViewModel<ServerSurveyNavigator> {
    private final MutableLiveData<List<SurveyPoints>> serverSurveyList = new MutableLiveData<>();
    private final MutableLiveData<List<SurveyPoints>> tempDataServerSurveyList = new MutableLiveData<>();
    private final MutableLiveData<String> totalCount=new MutableLiveData<String>();
    public final ObservableBoolean isEmptyTextVisible = new ObservableBoolean(false);
    public final MutableLiveData<String> emptyMsg = new MutableLiveData<>();
    public ServerSurveyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setServerSurveyList(List<SurveyPoints> surveyList) {
        if (surveyList.size() == 0) {
            isEmptyTextVisible.set(true);
            emptyMsg.setValue(getBaseActivity().getString(R.string.server_survey_empty) );

        } else {
            isEmptyTextVisible.set(false);
            this.serverSurveyList.setValue(surveyList);
            tempDataServerSurveyList.setValue(surveyList);
            this.totalCount.setValue(getBaseActivity().getString(R.string.cmn_count)+" "+ surveyList.size());

        }

    }
    public MutableLiveData<String> getTotalCount() {
        return totalCount;
    }
    public void setSearchResult(String query) {
        if (this.tempDataServerSurveyList.getValue() != null) {
            if (this.tempDataServerSurveyList.getValue().size() == 0) {
                isEmptyTextVisible.set(true);
                emptyMsg.setValue(getBaseActivity().getString(R.string.server_survey_empty));

            } else {
                isEmptyTextVisible.set(false);
                ArrayList<SurveyPoints> searchResult = new ArrayList<SurveyPoints>();
                for (SurveyPoints sp : tempDataServerSurveyList.getValue()) {
                    if (sp.propertyId.contains(query.toUpperCase())) {
                        searchResult.add(sp);
                    }
                }
                this.serverSurveyList.setValue(searchResult);
                if (searchResult.size() != 0) {
                    isEmptyTextVisible.set(false);
                } else {
                    isEmptyTextVisible.set(true);
                    emptyMsg.setValue(getBaseActivity().getString(R.string.server_survey_search_empty));
                }

            }
        }


    }

    public MutableLiveData<List<SurveyPoints>> getServerSurveyList() {
        return serverSurveyList;
    }


    public void getServerSurveyFromPref() {
        SurvryPointsResponse survryPointsResponse = getSurveyPoints();
        if (survryPointsResponse != null)
            setServerSurveyList(survryPointsResponse.getSurveyPointsList());
    }

    public void onItemClick(SurveyPoints surveyPoint) {
        getDataManager().setSelectedPropId(surveyPoint.getPropertyId());
        getDataManager().setSelectedDroneId(surveyPoint.getDroneid());
        getDataManager().setSelectedNewPropId(surveyPoint.getNewproid());
        getDataManager().setSelectedServerSurveyId(surveyPoint.getGlobalid());
        getNavigator().navigateToDetails();
    }

}