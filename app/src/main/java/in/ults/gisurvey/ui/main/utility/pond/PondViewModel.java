package in.ults.gisurvey.ui.main.utility.pond;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopFragment;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class PondViewModel extends BaseViewModel<PondNavigator> {

    public final MutableLiveData<List<CommonItem>> pondUsageList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondOdourList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondStatusList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondPresentConditionList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondNatureList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> pondColourList = new MutableLiveData<>();


    public PondViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutPondNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCapacityInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_capacity), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutUsageInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_usage), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutOdourInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_odour), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPondStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPondTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPresentConditionInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_present_condition), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutNatureInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_nature), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWallInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_sidewall), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWallTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_sidewall_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.llayoutPondConditionInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_condition), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPondWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_width), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPondLengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_length), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPondOwnerInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_pond_owner), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPurposeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_purpose), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSurveyNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_survey_no), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutColourInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_color), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutMaintainByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_maintain_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pond_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;


        }
    }

    void loadData() {
        pondUsageList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondUsage());
        pondOdourList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondOdour());
        pondStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondStatus());
        pondTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondType());
        pondPresentConditionList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondPresentCondition());
        pondNatureList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondNature());
        pondColourList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPondColour());
    }

    protected boolean validateFields(String pondName, String location, String area, String capacity,
                                     String usage, String odour, String pondStatus, String pondType,
                                     String presentCondition, String nature, String sidewall,
                                     String sidewallType, String pondCondition, String pondWidth,
                                     String pondLength, String pondOwner, String purpose, String surveyNo,
                                     String color, String maintainBy, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(pondName)) {
            getNavigator().validationError(PondFragment.POND_NAME_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_name));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(PondFragment.POND_LOCATION_ERROR, getBaseActivity().getString(R.string.err_pond_details_location));
            return false;
        } else if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(PondFragment.POND_AREA_ERROR, getBaseActivity().getString(R.string.err_pond_details_area));
            return false;
        } else if (TextUtils.isEmpty(capacity)) {
            getNavigator().validationError(PondFragment.POND_CAPACITY_ERROR, getBaseActivity().getString(R.string.err_pond_details_capacity));
            return false;
        } else if (TextUtils.isEmpty(usage)) {
            getNavigator().validationError(PondFragment.POND_USAGE_ERROR, getBaseActivity().getString(R.string.err_pond_details_usage));
            return false;
        } else if (TextUtils.isEmpty(odour)) {
            getNavigator().validationError(PondFragment.POND_ODOUR_ERROR, getBaseActivity().getString(R.string.err_pond_details_odour));
            return false;
        } else if (TextUtils.isEmpty(pondStatus)) {
            getNavigator().validationError(PondFragment.POND_POND_STATUS_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_status));
            return false;
        } else if (TextUtils.isEmpty(pondType)) {
            getNavigator().validationError(PondFragment.POND_TYPE_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_type));
            return false;
        } else if (TextUtils.isEmpty(presentCondition)) {
            getNavigator().validationError(PondFragment.POND_PRESENT_CONDITION_ERROR, getBaseActivity().getString(R.string.err_pond_details_present_condition));
            return false;
        } else if (TextUtils.isEmpty(nature)) {
            getNavigator().validationError(PondFragment.POND_NATURE_ERROR, getBaseActivity().getString(R.string.err_pond_details_nature));
            return false;
        } else if (TextUtils.isEmpty(sidewall)) {
            getNavigator().validationError(PondFragment.POND_SIDEWALL_ERROR, getBaseActivity().getString(R.string.err_pond_details_sidewall));
            return false;
        } else if (TextUtils.isEmpty(sidewallType)) {
            getNavigator().validationError(PondFragment.POND_SIDEWALL_TYPE_ERROR, getBaseActivity().getString(R.string.err_pond_details_sidewall_type));
            return false;
        } else if (TextUtils.isEmpty(pondCondition)) {
            getNavigator().validationError(PondFragment.POND_CONDITION_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_condition));
            return false;
        } else if (TextUtils.isEmpty(pondWidth)) {
            getNavigator().validationError(PondFragment.POND_WIDTH_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_width));
            return false;
        } else if (TextUtils.isEmpty(pondLength)) {
            getNavigator().validationError(PondFragment.POND_LENGTH_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_length));
            return false;
        } else if (TextUtils.isEmpty(pondOwner)) {
            getNavigator().validationError(PondFragment.POND_OWNER_ERROR, getBaseActivity().getString(R.string.err_pond_details_pond_owner));
            return false;
        } else if (TextUtils.isEmpty(purpose)) {
            getNavigator().validationError(PondFragment.POND_PURPOSE_ERROR, getBaseActivity().getString(R.string.err_pond_details_purpose));
            return false;
        } else if (TextUtils.isEmpty(surveyNo)) {
            getNavigator().validationError(PondFragment.POND_SURVEY_NO_ERROR, getBaseActivity().getString(R.string.err_pond_details_survey_no));
            return false;
        } else if (TextUtils.isEmpty(color)) {
            getNavigator().validationError(PondFragment.POND_COLOR_ERROR, getBaseActivity().getString(R.string.err_pond_details_color));
            return false;
        } else if (TextUtils.isEmpty(maintainBy)) {
            getNavigator().validationError(PondFragment.POND_MAINTAIN_BY_ERROR, getBaseActivity().getString(R.string.err_pond_details_maintain_by));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(PondFragment.POND_REMARKS_ERROR, getBaseActivity().getString(R.string.err_pond_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(PondFragment.POND_COMMON_ERROR, getBaseActivity().getString(R.string.err_pond_details_photo));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String pondName, String location, String area, String capacity, String usage, String odour, String pondStatus, String pondType, String presentCondition, String nature, String sidewall, String sidewallType, String pondCondition, String pondWidth, String pondLength, String pondOwner, String purpose, String surveyNo, String color, String maintainBy, String remarks, String imagePath1, boolean is) {

    }
}