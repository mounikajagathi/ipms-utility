package in.ults.gisurvey.ui.main.utility.busstop;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class BusStopViewModel extends BaseViewModel<BusStopNavigator> {

    public BusStopViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
        getNavigator().saveUtilityDetails(false);
    }

    public void onPartialSaveClick() {
        getNavigator().saveUtilityDetails(true);
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutBusStopLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bus_stop_details_location), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bus_stop_details_remarks), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
        }
    }

    protected boolean validateFields(String busStopLocation, String busStopRemarks, String imagePath) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(busStopLocation)) {
            getNavigator().validationError(BusStopFragment.BUS_STOP_LOCATION_ERROR, getBaseActivity().getString(R.string.err_bus_stop_details_location));
            return false;
        } else if (TextUtils.isEmpty(busStopRemarks)) {
            getNavigator().validationError(BusStopFragment.BUS_STOP_REMARKS_ERROR, getBaseActivity().getString(R.string.err_bus_stop_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath)) {
            getNavigator().validationError(BusStopFragment.BUS_STOP_COMMON_ERROR, getBaseActivity().getString(R.string.err_bus_stop_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String busStopLocation, String busStopRemarks, String imagePath,boolean isValidationOk) {

    }

}