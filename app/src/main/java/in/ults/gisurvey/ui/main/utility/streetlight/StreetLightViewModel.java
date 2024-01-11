package in.ults.gisurvey.ui.main.utility.streetlight;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.StringRes;
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

public class StreetLightViewModel extends BaseViewModel<StreetLightNavigator> {

    public final MutableLiveData<List<CommonItem>> streetLightBulbTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> streetLightWorkingStatusList = new MutableLiveData<>();


    public StreetLightViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutStreetLightLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetLightAddressInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_address), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetLightFundedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_funded_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetLightBulbTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_bulb_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetLightPostNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_post_no), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetLightWorkingStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_light_details_working_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        streetLightBulbTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getStreetLightType());
        streetLightWorkingStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getStreetLightWorkingStatus());
    }

    protected boolean validateFields(String location, String address, String fundedBy, String bulbType, String workingStatus, String postNo, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_LOCATION_ERROR, getBaseActivity().getString(R.string.err_street_light_details_location));
            return false;
        } else if (TextUtils.isEmpty(address)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_ADDRESS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_address));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_LOCATION_ERROR, getBaseActivity().getString(R.string.err_street_light_details_location));
            return false;
        } else if (TextUtils.isEmpty(fundedBy)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_FUNDED_BY_ERROR, getBaseActivity().getString(R.string.err_street_light_details_funded_by));
            return false;
        } else if (TextUtils.isEmpty(bulbType)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_BULB_TYPE_ERROR, getBaseActivity().getString(R.string.err_street_light_details_bulb_type));
            return false;
        } else if (TextUtils.isEmpty(workingStatus)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_WORKING_STATUS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_working_status));
            return false;
        } else if (TextUtils.isEmpty(postNo)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_POST_NO_ERROR, getBaseActivity().getString(R.string.err_street_light_details_post_no));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_REMARKS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(StreetLightFragment.STREET_LIGHT_COMMON_ERROR, getBaseActivity().getString(R.string.err_street_light_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String address, String fundedBy, String bulbType, String workingStatus, String postNo, String remarks, String imagePath1, boolean is) {

    }
}