package in.ults.gisurvey.ui.main.utility.road;

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

public class RoadViewModel extends BaseViewModel<RoadNavigator> {

    public final MutableLiveData<List<CommonItem>> roadMaterialList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> roadCategoryList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> maintainedByList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> footpathConsMatList = new MutableLiveData<>();

    public RoadViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutRURoadNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_road_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRUStartPointInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_start_point), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRUEndPointInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_end_point), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRURoadMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_road_material), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRURoadCategoryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_road_category), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRUMaintainedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_maintained_by), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRULengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_length_mtr), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRURoadwayWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_roadway_width_mtr), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRUCarriageWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_carriage_width_mtr), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.cbFootPathInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_footpath), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutFootpathConsMatInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_footpath_cons_mat), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRUFootpathWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_footpath_width_mtr), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRURemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_utility_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;


        }
    }

    void loadData() {
        roadMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getRoadMaterial());
        roadCategoryList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getRoadCategory());
        maintainedByList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getRoadMaintainedBy());
        footpathConsMatList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getRoadFootpathMaterial());
    }

    protected boolean validateFields(String roadName, String startPoint, String endPoint, String surfaceType,
                                     String roadCategory, String maintainedBy, String length, String width,
                                     String carriageWidth, boolean footpath, String footpathConsMat,
                                     String footpathWidth, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(roadName)) {
            getNavigator().validationError(RoadFragment.ROAD_NAME_ERROR, getBaseActivity().getString(R.string.err_road_utility_road_name));
            return false;
        } else if (TextUtils.isEmpty(startPoint)) {
            getNavigator().validationError(RoadFragment.ROAD_NAME_ERROR, getBaseActivity().getString(R.string.err_road_utility_start_point));
            return false;
        } else if (TextUtils.isEmpty(endPoint)) {
            getNavigator().validationError(RoadFragment.ROAD_END_POINT_ERROR, getBaseActivity().getString(R.string.err_road_utility_end_point));
            return false;
        } else if (TextUtils.isEmpty(surfaceType)) {
            getNavigator().validationError(RoadFragment.ROAD_SURFACE_TYPE_ERROR, getBaseActivity().getString(R.string.err_road_utility_road_material));
            return false;
        } else if (TextUtils.isEmpty(roadCategory)) {
            getNavigator().validationError(RoadFragment.ROAD_CATEGORY_ERROR, getBaseActivity().getString(R.string.err_road_utility_road_category));
            return false;
        } else if (TextUtils.isEmpty(maintainedBy)) {
            getNavigator().validationError(RoadFragment.ROAD_MAINTAINED_BY_ERROR, getBaseActivity().getString(R.string.err_road_utility_maintained_by));
            return false;
        } else if (TextUtils.isEmpty(length)) {
            getNavigator().validationError(RoadFragment.ROAD_LENGTH_ERROR, getBaseActivity().getString(R.string.err_road_utility_length_mtr));
            return false;
        } else if (TextUtils.isEmpty(width)) {
            getNavigator().validationError(RoadFragment.ROAD_WIDTH_ERROR, getBaseActivity().getString(R.string.err_road_utility_roadway_width_mtr));
            return false;
        } else if (TextUtils.isEmpty(carriageWidth)) {
            getNavigator().validationError(RoadFragment.ROAD_CARRIAGE_WIDTH_ERROR, getBaseActivity().getString(R.string.err_road_utility_carriage_width_mtr));
            return false;
        } else if (TextUtils.isEmpty(footpathConsMat)) {
            getNavigator().validationError(RoadFragment.ROAD_FOOTPATH_CONST_MAT_ERROR, getBaseActivity().getString(R.string.err_road_utility_footpath_cons_mat));
            return false;
        } else if (TextUtils.isEmpty(footpathWidth)) {
            getNavigator().validationError(RoadFragment.ROAD_FOOTPATH_WIDTH_ERROR, getBaseActivity().getString(R.string.err_road_utility_footpath_width_mtr));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(RoadFragment.ROAD_REMARKS_ERROR, getBaseActivity().getString(R.string.err_road_utility_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(RoadFragment.ROAD_COMMON_ERROR, getBaseActivity().getString(R.string.err_road_utility_photo));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String roadName, String startPoint, String endPoint, String surfaceType,
                            String roadCategory, String maintainedBy, String length, String width,
                            String carriageWidth, boolean footpath, String footpathConsMat, String footpathWidth,
                            String remarks, String imagePath1, boolean is) {

    }
}