package in.ults.gisurvey.ui.survey.startsurvey;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class StartSurveyViewModel extends BaseViewModel<StartSurveyNavigator> {


    public StartSurveyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    boolean isValidationSuccess(String qFieldID1, String qFieldID2, String qFieldID3) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(qFieldID1)) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_1, getBaseActivity().getString(R.string.enter_q_field_id1));
            return false;
        } else if (qFieldID1.contains("\\") || qFieldID1.contains("/")) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_1, getBaseActivity().getString(R.string.enter_valid_building_id));
            return false;
        } else if (TextUtils.isEmpty(qFieldID2)) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_2, getBaseActivity().getString(R.string.enter_q_field_id));
            return false;
        } else if (qFieldID2.contains("\\") || qFieldID2.contains("/")) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_2, getBaseActivity().getString(R.string.enter_valid_building_id));
            return false;
        } else if (TextUtils.isEmpty(qFieldID3)) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_3, getBaseActivity().getString(R.string.enter_q_field_id));
            return false;
        } else if (qFieldID3.contains("\\") || qFieldID3.contains("/")) {
            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD_3, getBaseActivity().getString(R.string.enter_valid_building_id));
            return false;
        } else {
            return true;
        }
//        getNavigator().clearValidationErrors();
//        if (qFieldID.length() == 0) {
//            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD, getBaseActivity().getString(R.string.enter_q_field_id));
//            return false;
//        } else if (qFieldID.contains("\\") || qFieldID.contains("/")) {
//            getNavigator().validationError(StartSurveyFragment.VALIDATION_ERROR_Q_FIELD, getBaseActivity().getString(R.string.enter_valid_building_id));
//            return false;
//        } else {
//            return true;
//        }
    }

    /**
     * Checking survey id duplication with server data
     *
     * @param qFieldId
     */
    void checkIdwithServerData(String qFieldId,String wardNumber) {
        boolean isfound = false;
        if (getSurveyPoints() != null) {
            SurvryPointsResponse survryPointsResponse = getSurveyPoints();
            if (survryPointsResponse != null && survryPointsResponse.getSurveyPointsList() != null) {
                for (int i = 0; i < survryPointsResponse.getSurveyPointsList().size(); i++) {
                    String surveyId = survryPointsResponse.getSurveyPointsList().get(i).getGlobalid();
                    if (surveyId.equalsIgnoreCase(qFieldId)) {
                        isfound = true;
                        break;
                    }
                }
                if (isfound) {

                    getNavigator().showWarning();
                } else {
                    saveQFieldId(qFieldId,wardNumber);
                }
            }
        } else {
            getBaseActivity().showToast(R.string.no_data);
        }
    }

    /**
     * to save qfield id in preference
     *
     * @param qFieldId
     */
    void saveQFieldId(String qFieldId,String wardNumber) {
        Survey survey = new Survey(qFieldId);
        survey.setSurveyIdWardNumber(wardNumber);
        DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_DATE_FORMAT, Locale.ENGLISH);
        String startDate = df.format(Calendar.getInstance().getTime());
        getCompositeDisposable().add(getDataManager()
                .insertQFieldID(survey)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean ->saveCurrentSurveyIdInPref(qFieldId,AppConstants.SURVEY_OPEN_MODE_EDIT))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().insertSurveyStartDate(startDate, qFieldId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getNavigator().navigateToNextPage())
                .subscribe());
    }


    /**
     * to check survey already exists
     *
     * @param
     */
    void checkSurveyExists(String buildingId1, String buildingId2, String buildingId3) {
        String series = getDataManager().getSeries();
        String mobileCode = "-" + getMobileCode();

        String qFieldId = series + mobileCode + "-" + buildingId1 + "-" + buildingId2 + "-" + buildingId3;
        Survey survey = new Survey(qFieldId);
        getCompositeDisposable().add(getDataManager()
                .isSurveyExists(survey)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        getNavigator().showEditDialog(qFieldId,buildingId2);
                    } else {
                        //No duplication found in db entries so now check with server data which is stored in shared preference
                        checkIdwithServerData(qFieldId,buildingId2);
                    }
                })
                .subscribe());
    }


    public void onNextClick() {
        getNavigator().createSurvey();
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuildingIdInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_building_id), InfoVideoNames.MAP_SL_NO_INFO_VIDEO);
        }

    }

}