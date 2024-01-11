package in.ults.gisurvey.ui.main.utility.stadium;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopFragment;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class StadiumViewModel extends BaseViewModel<StadiumNavigator> {

    public StadiumViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutStadiumNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStadiumLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStadiumAddressInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_address), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStadiumNoGalleryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_no_gallery), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStadiumGalleryCapacityInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_gallery_capacity), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutStadiumAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_stadium_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    protected boolean validateFields(String name, String location, String address, String numberGallery, String capacity, String area, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(name)) {
            getNavigator().validationError(StadiumFragment.STADIUM_NAME_ERROR, getBaseActivity().getString(R.string.err_stadium_details_name));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(StadiumFragment.STADIUM_LOCATION_ERROR, getBaseActivity().getString(R.string.err_stadium_details_location));
            return false;
        } else if (TextUtils.isEmpty(address)) {
            getNavigator().validationError(StadiumFragment.STADIUM_ADDRESS_ERROR, getBaseActivity().getString(R.string.err_stadium_details_address));
            return false;
        } else if (TextUtils.isEmpty(numberGallery)) {
            getNavigator().validationError(StadiumFragment.STADIUM_NUMBER_GALLERY_ERROR, getBaseActivity().getString(R.string.err_stadium_details_no_gallery));
            return false;
        } else if (TextUtils.isEmpty(capacity)) {
            getNavigator().validationError(StadiumFragment.STADIUM_CAPACITY_ERROR, getBaseActivity().getString(R.string.err_stadium_details_gallery_capacity));
            return false;
        } else if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(StadiumFragment.STADIUM_AREA_ERROR, getBaseActivity().getString(R.string.err_stadium_details_area));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(StadiumFragment.STADIUM_REMARKS_ERROR, getBaseActivity().getString(R.string.err_stadium_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(StadiumFragment.STADIUM_COMMON_ERROR, getBaseActivity().getString(R.string.err_stadium_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String name, String location, String address, String numberGallery, String capacity, String area, String remarks, String imagePath1, boolean is) {

    }
}