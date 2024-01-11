package in.ults.gisurvey.ui.main.utility.tank;

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

public class TankViewModel extends BaseViewModel<TankNavigator> {

    public final MutableLiveData<List<CommonItem>> tankTypeList = new MutableLiveData<>();

    public TankViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutTankLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tank_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTankOwnerInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tank_details_tank_owner), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTankCapacityInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tank_details_capacity), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTankTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tank_details_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tank_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        tankTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainageMaterial());
    }

    protected boolean validateFields(String location, String tankOwner, String capacity, String tankType, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(TankFragment.TANK_LOCATION_ERROR, getBaseActivity().getString(R.string.err_tank_details_location));
            return false;
        } else if (TextUtils.isEmpty(tankOwner)) {
            getNavigator().validationError(TankFragment.TANK_OWNER_ERROR, getBaseActivity().getString(R.string.err_tank_details_tank_owner));
            return false;
        } else if (TextUtils.isEmpty(capacity)) {
            getNavigator().validationError(TankFragment.TANK_CAPACITY_ERROR, getBaseActivity().getString(R.string.err_tank_details_capacity));
            return false;
        } else if (TextUtils.isEmpty(tankType)) {
            getNavigator().validationError(TankFragment.TANK_WATER_TANK_TYPE_ERROR, getBaseActivity().getString(R.string.err_tank_details_tank_owner));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(TankFragment.TANK_REMARKS_ERROR, getBaseActivity().getString(R.string.err_tank_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(TankFragment.TANK_COMMON_ERROR, getBaseActivity().getString(R.string.err_tank_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String tankOwner, String capacity, String tankType, String remarks, String imagePath1, boolean is) {

    }
}