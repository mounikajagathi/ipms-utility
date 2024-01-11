package in.ults.gisurvey.ui.main.utility.streettap;

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

public class StreetTapViewModel extends BaseViewModel<StreetTapNavigator> {

    public final MutableLiveData<List<CommonItem>> streetTapWorkingStatusList = new MutableLiveData<>();


    public StreetTapViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutStreetTapLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_tap_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetTapAddressInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_tap_details_address), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetTapWorkingStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_tap_details_working_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStreetTapFundedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_tap_details_funded_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_tap_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        streetTapWorkingStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainageMaterial());
    }

    protected boolean validateFields(String location, String address, String fundedBy, String workingStatus, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_LOCATION_ERROR, getBaseActivity().getString(R.string.err_street_light_details_location));
            return false;
        } else if (TextUtils.isEmpty(address)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_ADDRESS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_address));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_LOCATION_ERROR, getBaseActivity().getString(R.string.err_street_light_details_location));
            return false;
        } else if (TextUtils.isEmpty(fundedBy)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_FUNDED_BY_ERROR, getBaseActivity().getString(R.string.err_street_light_details_funded_by));
            return false;
        } else if (TextUtils.isEmpty(workingStatus)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_WORKING_STATUS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_working_status));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_REMARKS_ERROR, getBaseActivity().getString(R.string.err_street_light_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(StreetTapFragment.STREET_LIGHT_COMMON_ERROR, getBaseActivity().getString(R.string.err_street_light_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String address, String fundedBy, String workingStatus, String remarks, String imagePath1, boolean is) {

    }
}