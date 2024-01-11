package in.ults.gisurvey.ui.main.partialsurvey;

import androidx.lifecycle.MutableLiveData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static in.ults.gisurvey.utils.AppConstants.SURVEY_OPEN_MODE_EDIT;

public class PartialSurveyViewModel extends BaseViewModel<PartialSurveyNavigator> {


    private final MutableLiveData<List<Survey>> partialSurveyList = new MutableLiveData<>();


    public PartialSurveyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public MutableLiveData<List<Survey>> getPartialSurveyList() {
        return partialSurveyList;
    }

    public void setPartialSurveyList(List<Survey> partialSurveyList) {
        this.partialSurveyList.setValue(partialSurveyList);
    }

    @Override
    public void onPendingSurveys(List<Survey> survey) {
        getNavigator().setSurveyData(survey);
    }

    /**
     * to save survey id in shared preference and navigate to partial survey
     */
    public void saveSurveyId(String surveyId) {
        saveCurrentSurveyIdInPref(surveyId,SURVEY_OPEN_MODE_EDIT);
        getNavigator().navigateToPartialSurvey();
    }
    /**
     * to navigate to new survey
     */
    public void onNewSurveyClick() {
       if (getDistrict() == null || getLocalBody() == null|| getWardNumber()==null) {
            getBaseActivity().showToast(R.string.home_err_district);
        } else if (getSurveyPoints()==null) {
           getBaseActivity().showToast(R.string.ward_selection_error);
       } else if (getTpkFileLoc() == null) {
            getBaseActivity().showToast(R.string.home_err_tpk);
       }else if (getSurveyorName().equals(getBaseActivity().getString(R.string.settings_default))) {
           getBaseActivity().showToast(R.string.home_err_surveyor_name);
       }else if (getMobileCode().equals(getBaseActivity().getString(R.string.settings_default))) {
           getBaseActivity().showToast(R.string.home_err_mobile_code);
       } else {
            getNavigator().navigateToNewSurvey();
        }

    }

    void deleteSurvey(int pos) {
        getCompositeDisposable().add(getDataManager()
                .deleteSurvey(Objects.requireNonNull(partialSurveyList.getValue()).get(pos).getSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getPendingSurveyList();
                })
                .subscribe());
    }


}