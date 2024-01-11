package in.ults.gisurvey.ui.main.utility.well;

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
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class WellViewModel extends BaseViewModel<WellNavigator> {

    public final MutableLiveData<List<CommonItem>> wellStatusList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> wellTypeList = new MutableLiveData<>();


    public WellViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutWellOwnerInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_owner), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellPurposeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_well_purpose), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellCoverInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_cover), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellSurveyNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_survey_no), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellNearRoadInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_near_road), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellReWaterAvailabilityMarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_re_water_availability_marks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.cbSeasonalInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_seasonal), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutWellTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        wellStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getWellStatus());
        wellTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getWellType());
    }

    protected boolean validateFields(String location, String wellOwner, String purpose, String cover,
                                     String surveyNo, String nearRoad, String reWaterAvail, String status,
                                     String wellType, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(WellFragment.WELL_LOCATION_ERROR, getBaseActivity().getString(R.string.err_well_details_location));
            return false;
        } else if (TextUtils.isEmpty(wellOwner)) {
            getNavigator().validationError(WellFragment.WELL_OWNER_ERROR, getBaseActivity().getString(R.string.err_well_details_owner));
            return false;
        } else if (TextUtils.isEmpty(purpose)) {
            getNavigator().validationError(WellFragment.WELL_PURPOSE_ERROR, getBaseActivity().getString(R.string.err_well_details_well_purpose));
            return false;
        } else if (TextUtils.isEmpty(cover)) {
            getNavigator().validationError(WellFragment.WELL_COVER_ERROR, getBaseActivity().getString(R.string.err_well_details_cover));
            return false;
        } else if (TextUtils.isEmpty(surveyNo)) {
            getNavigator().validationError(WellFragment.WELL_SURVEY_NO_ERROR, getBaseActivity().getString(R.string.err_well_details_survey_no));
            return false;
        } else if (TextUtils.isEmpty(nearRoad)) {
            getNavigator().validationError(WellFragment.WELL_NEAR_ROAD_ERROR, getBaseActivity().getString(R.string.err_well_details_near_road));
            return false;
        } else if (TextUtils.isEmpty(reWaterAvail)) {
            getNavigator().validationError(WellFragment.WELL_RE_WATER_AVAIL_ERROR, getBaseActivity().getString(R.string.err_well_details_re_water_availability_marks));
            return false;
        } else if (TextUtils.isEmpty(status)) {
            getNavigator().validationError(WellFragment.WELL_STATUS_ERROR, getBaseActivity().getString(R.string.err_well_details_status));
            return false;
        } else if (TextUtils.isEmpty(wellType)) {
            getNavigator().validationError(WellFragment.WELL_TYPE_ERROR, getBaseActivity().getString(R.string.err_well_details_type));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(WellFragment.WELL_REMARKS_ERROR, getBaseActivity().getString(R.string.err_well_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(WellFragment.WELL_COMMON_ERROR, getBaseActivity().getString(R.string.err_well_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String wellOwner, String purpose, String cover, String surveyNo, String nearRoad, String reWaterAvail, String status, String wellType, String remarks, String imagePath1, boolean is) {

    }
}