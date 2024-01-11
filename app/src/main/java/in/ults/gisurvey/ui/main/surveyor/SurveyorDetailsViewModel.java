package in.ults.gisurvey.ui.main.surveyor;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

/**
 * Created by anuag on 10-07-2020
 **/
public class SurveyorDetailsViewModel extends BaseViewModel<SurveyorDetailsNavigator> {
    public final MutableLiveData<String> surveyorNameValue = new MutableLiveData<>();
    public final MutableLiveData<String> mobileCodeValue = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> surveyorList = new MutableLiveData<>();
    public final MutableLiveData<List<Surveyor>> serverSurveyorData = new MutableLiveData<>();

    public SurveyorDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to save surveyor name
     */
    public void saveData(String mobileCode, ArrayList<String> selectedSurveyor) {
        String surveyorName = "";
        for (int i = 0; i < selectedSurveyor.size(); i++) {
            if (i == selectedSurveyor.size() - 1)
                surveyorName = surveyorName + selectedSurveyor.get(i);
            else
                surveyorName = surveyorName + selectedSurveyor.get(i) + ", ";
        }
        ArrayList<Surveyor> selectedSurveyorDetails=new ArrayList<Surveyor>();
        for (Surveyor surveyor:serverSurveyorData.getValue()){
            if(selectedSurveyor.contains(surveyor.getEmployee_name())){
                selectedSurveyorDetails.add(surveyor);
            }
        }
        getDataManager().setSelectedSurveyorDetails(selectedSurveyorDetails);
        getDataManager().setSelectedSurveyorNames(selectedSurveyor);
        getDataManager().setSurveyorName(surveyorName);
        getDataManager().setMobileCode(mobileCode);
    }

    public void loadData() {
        surveyorNameValue.setValue(getSurveyorName().equals(getBaseActivity().getString(R.string.settings_default_value)) ? "" : getDataManager().getSurveyorName());
        mobileCodeValue.setValue(getMobileCode().equals(getBaseActivity().getString(R.string.settings_default_value)) ? "" : getDataManager().getMobileCOde());
        callSurveyorListFromServer();

    }

    private void callSurveyorListFromServer() {
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getNavigator().showProgress();
            getCompositeDisposable().add(getDataManager()
                    .surveyorApiCall(getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnError(throwable -> {
                        checkApiError(throwable, getNavigator());
                        getNavigator().showNoData();
                    })
                    .doOnNext(response -> {
                        if (response.getStatus()) {
                            if (response.getData() != null)
                                setRecyclerviewData(response.getData());
                        }
                        getNavigator().showContent();
                    }).subscribe());
        } else {
            getNavigator().showNoData();
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }

    private void setRecyclerviewData(List<Surveyor> data) {
        ArrayList<CommonItem> tempData = new ArrayList<CommonItem>();
        for (Surveyor s : data) {
            CommonItem c = new CommonItem();
            c.setId(s.getEmployee_id());
            c.setContent(s.getEmployee_name());
            tempData.add(c);
        }
        surveyorList.setValue(tempData);
        serverSurveyorData.setValue(data);
        if(getDataManager().getSelectedSurveyorNames()!=null&&getDataManager().getSelectedSurveyorNames().size()!=0){
            getNavigator().setSelectedSurveyor(getDataManager().getSelectedSurveyorNames());
        }
    }
    /**
     * Clear existing surveyor name shared prefernce and
     * uncheck the selection
     */
    public void onClearClick() {
        getDataManager().setSelectedSurveyorDetails(null);
        getDataManager().setSelectedSurveyorNames(null);
        getDataManager().setSurveyorName(null);
        getNavigator().clearList();


    }

    public void onNextClick() {
//
        getNavigator().saveSurveyorName();
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String mobileCode, ArrayList<String> selectedSurveyorList) {
        getNavigator().clearValidationErrors();
        if (selectedSurveyorList.size() == 0) {
            getNavigator().validationError(SurveyorDetailsFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_surveyor));
            return false;
        } else if (TextUtils.isEmpty(mobileCode)) {
            getNavigator().validationError(SurveyorDetailsFragment.MOBILE_CODE_ERROR, getBaseActivity().getString(R.string.error_mobile_code));
            return false;
        }
        return true;
    }
}
