package in.ults.gisurvey.ui.main.utility.lowmastlight;

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
import in.ults.gisurvey.ui.main.utility.highmastlight.HighMastLightFragment;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class LowMastLightViewModel extends BaseViewModel<LowMastLightNavigator> {

    public final MutableLiveData<List<CommonItem>> lowMastLightTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> lowMastWorkingStatusList = new MutableLiveData<>();


    public LowMastLightViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutLowMastLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastAddressInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_address), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastFundedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_funded_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastLightTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_light_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastWorkingStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_working_status), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastLightWarrantyInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_warranty), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastVendorInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_vendor), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastExpiryDateInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_expire_date), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutLowMastAMCInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_amc), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_low_mast_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        lowMastLightTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getLowMastLightType());
        lowMastWorkingStatusList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getLowMastWorkingStatus());
    }

    protected boolean validateFields(String location, String address, String fundedBy, String lightType, String workingStatus, String warranty, String vendor, String expiryDate, String amc, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_LOCATION_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_location));
            return false;
        } else if (TextUtils.isEmpty(address)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_ADDRESS_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_address));
            return false;
        } else if (TextUtils.isEmpty(fundedBy)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_FUNDED_BY_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_funded_by));
            return false;
        } else if (TextUtils.isEmpty(lightType)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_LIGHT_TYPE_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_light_type));
            return false;
        } else if (TextUtils.isEmpty(workingStatus)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_WORKING_STATUS_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_working_status));
            return false;
        } else if (TextUtils.isEmpty(warranty)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_WARRANTY_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_warranty));
            return false;
        } else if (TextUtils.isEmpty(vendor)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_VENDOR_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_vendor));
            return false;
        } else if (TextUtils.isEmpty(expiryDate)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_EXPIRY_DATE_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_expire_date));
            return false;
        } else if (TextUtils.isEmpty(amc)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_AMC_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_amc));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_REMARKS_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(LowMastLightFragment.LOW_MAST_COMMON_ERROR, getBaseActivity().getString(R.string.err_low_mast_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String address, String fundedBy, String lightType, String workingStatus, String warranty, String vendor, String expiryDate, String amc, String remarks, String imagePath1, boolean is) {

    }
}