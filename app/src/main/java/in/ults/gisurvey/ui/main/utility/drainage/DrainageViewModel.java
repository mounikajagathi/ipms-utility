package in.ults.gisurvey.ui.main.utility.drainage;

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

public class DrainageViewModel extends BaseViewModel<DrainageNavigator> {

    public final MutableLiveData<List<CommonItem>> drainageMaterialList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> drainageTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> drainagePlacementList = new MutableLiveData<>();


    public DrainageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutDrainageNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainageStreetInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_street_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainageMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_Drainage_material), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainageTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainageLengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_length), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainagePlacementInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_placement), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDrainageCategoryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_drainage_category), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drainage_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        drainageMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainageMaterial());
        drainageTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainageType());
        drainagePlacementList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainagePlacement());
    }

    protected boolean validateFields(String name, String place, String streetName, String material, String type, String length, String placement, String category, String drainageRemarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(name)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_NAME_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_name));
            return false;
        } else if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_PLACE_ERROR, getBaseActivity().getString(R.string.err_drainage_details_start_place));
            return false;
        } else if (TextUtils.isEmpty(streetName)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_STREET_NAME_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_street_name));
            return false;
        } else if (TextUtils.isEmpty(material)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_MATERIAL_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_material));
            return false;
        } else if (TextUtils.isEmpty(type)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_TYPE_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_type));
            return false;
        } else if (TextUtils.isEmpty(length)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_LENGTH_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_length));
            return false;
        } else if (TextUtils.isEmpty(placement)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_PLACEMENT_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_placement));
            return false;
        } else if (TextUtils.isEmpty(category)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_CATEGORY_ERROR, getBaseActivity().getString(R.string.err_drainage_details_drainage_category));
            return false;
        } else if (TextUtils.isEmpty(drainageRemarks)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_REMARKS_ERROR, getBaseActivity().getString(R.string.err_drainage_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(DrainageFragment.DRAINAGE_COMMON_ERROR, getBaseActivity().getString(R.string.err_drainage_details_photo));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String name, String place, String streetName, String material, String type, String length, String placement, String category, String drainageRemarks, String imagePath1, boolean is) {

    }
}