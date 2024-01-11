package in.ults.gisurvey.ui.main.utility.highmastlight;

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

public class HighMastLightViewModel extends BaseViewModel<HighMastLightNavigator> {

    public final MutableLiveData<List<CommonItem>> highMastLightTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> highMastLightWorkingStatusList = new MutableLiveData<>();


    public HighMastLightViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutHighMastLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastAddressInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_address), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastLightTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_light_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastFundedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_funded_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastWorkingStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_working_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastLightHeightInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_height), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastNoOfBulbInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_No_of_bulbs), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastVendorInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_vendor), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutHighMastExpiryDateInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_expire_date), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_high_mast_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        highMastLightTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getHighMastLightType());
        highMastLightWorkingStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getHighMastWorkingStatus());
    }

    protected boolean validateFields(String location, String address, String fundedBy, String lightType, String height, String noOfBulb, String workingStatus, String vendor, String expiryDate, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_LOCATION_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_location));
            return false;
        } else if (TextUtils.isEmpty(address)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_ADDRESS_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_address));
            return false;
        } else if (TextUtils.isEmpty(fundedBy)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_FUNDED_BY_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_funded_by));
            return false;
        } else if (TextUtils.isEmpty(lightType)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_LIGHT_BY_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_light_type));
            return false;
        } else if (TextUtils.isEmpty(height)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_HEIGHT_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_height));
            return false;
        } else if (TextUtils.isEmpty(noOfBulb)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_NO_OF_BULBS_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_no_of_bulbs));
            return false;
        } else if (TextUtils.isEmpty(workingStatus)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_WORKING_STATUS_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_working_status));
            return false;
        } else if (TextUtils.isEmpty(vendor)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_VENDOR_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_vendor));
            return false;
        } else if (TextUtils.isEmpty(expiryDate)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_EXPIRY_DATE_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_expire_date));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_REMARKS_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(HighMastLightFragment.HIGH_MAST_COMMON_ERROR, getBaseActivity().getString(R.string.err_high_mast_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String address, String fundedBy, String lightType, String height, String noOfBulb, String workingStatus, String vendor, String expiryDate, String remarks, String imagePath1, boolean is) {

    }
}