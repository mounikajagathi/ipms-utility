package in.ults.gisurvey.ui.main.utility.parking;
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

public class ParkingViewModel extends BaseViewModel<ParkingNavigator> {

    public final MutableLiveData<List<CommonItem>> parkingTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> parkingUnderList = new MutableLiveData<>();


    public ParkingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutParkingPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkingTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkingCapacityInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_capacity), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkingAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkingUnderInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_parking_under), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_parking_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        parkingTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getParkingParkingType());
        parkingUnderList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getParkingParkingUnder());
    }
    protected boolean validateFields(String place,String type,String  capacity,String  area,String  parkingUnder,String  remarks,String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(ParkingFragment.PARKING_PLACE_ERROR, getBaseActivity().getString(R.string.err_parking_details_place));
            return false;
        }else  if (TextUtils.isEmpty(type)) {
            getNavigator().validationError(ParkingFragment.PARKING_TYPE_ERROR, getBaseActivity().getString(R.string.err_parking_details_type));
            return false;
        }else  if (TextUtils.isEmpty(capacity)) {
            getNavigator().validationError(ParkingFragment.PARKING_CAPACITY_ERROR, getBaseActivity().getString(R.string.err_parking_details_capacity));
            return false;
        }else  if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(ParkingFragment.PARKING_AREA_ERROR, getBaseActivity().getString(R.string.err_parking_details_area));
            return false;
        }else  if (TextUtils.isEmpty(parkingUnder)) {
            getNavigator().validationError(ParkingFragment.PARKING_UNDER_ERROR, getBaseActivity().getString(R.string.err_parking_details_parking_under));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(ParkingFragment.PARKING_TYPE_REMARKS_ERROR, getBaseActivity().getString(R.string.err_parking_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(ParkingFragment.PARKING_COMMON_ERROR, getBaseActivity().getString(R.string.err_parking_details_photo_1));
            return false;
        }
        return true;
    }
    void saveUtilityDetails(String place,String  type,String  capacity,String  area,String  parkingUnder,String  remarks,String imagePath1,boolean is){

    }
}