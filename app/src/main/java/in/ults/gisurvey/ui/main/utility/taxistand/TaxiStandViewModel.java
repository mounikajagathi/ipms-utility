package in.ults.gisurvey.ui.main.utility.taxistand;

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

public class TaxiStandViewModel extends BaseViewModel<TaxiStandNavigator> {


    public final MutableLiveData<List<CommonItem>> taxiStandParkingTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> taxiStandAuthorisedList = new MutableLiveData<>();

    public TaxiStandViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutTaxiStandPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTaxiStandLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTaxiStandParkingTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_parking_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTaxiStandCapacityInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_capacity), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTaxiStandAuthorisedInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_authorised), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTaxiStandAuthDetailsInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_auth_details), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_taxi_stand_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        taxiStandParkingTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getTaxiParkingType());
        taxiStandAuthorisedList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getTaxiAuthorisation());
    }

    protected boolean validateFields(String place, String location, String parkingType, String capacity, String auth, String authDetails, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_PLACE_NAME_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_place));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_LOCATION_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_location));
            return false;
        } else if (TextUtils.isEmpty(parkingType)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_PARKING_TYPE_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_parking_type));
            return false;
        } else if (TextUtils.isEmpty(capacity)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_CAPACITY_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_capacity));
            return false;
        } else if (TextUtils.isEmpty(auth)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_AUTH_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_authorised));
            return false;
        } else if (TextUtils.isEmpty(authDetails)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_AUTH_DETAILS_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_auth_details));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_REMARKS_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(TaxiStandFragment.TAXI_STAND_COMMON_ERROR, getBaseActivity().getString(R.string.err_taxi_stand_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String place, String location, String parkingType, String capacity, String auth, String authDetails, String remarks, String imagePath1, boolean is) {

    }
}