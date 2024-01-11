package in.ults.gisurvey.ui.main.utility.canalline;

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
import in.ults.gisurvey.ui.main.utility.bridge.BridgeFragment;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class CanalLineViewModel extends BaseViewModel<CanalLineNavigator> {

    public final MutableLiveData<List<CommonItem>> canalLineTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> canalLineSubTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> canalLineConditionList = new MutableLiveData<>();


    public CanalLineViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutCanalLineStreetNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_street_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineSubTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_sub_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_canal_width), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineConditionInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_canal_condition), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineStartPointInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_start_point), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCanalLineEndPointInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_end_point), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.cbSidewallInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_sidewall), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_canal_line_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
//        canalLineTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeMaterial());
//        canalLineSubTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeSubType());
//        canalLineConditionList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeMaintainedBy());
    }

    protected boolean validateFields(String streetName, String location, String type, String subtype, String area, String width, String condition, String startPoint, String endPoint, String canalLineRemarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(streetName)) {
            getNavigator().validationError(CanalLineFragment.CANAL_STREET_NAME_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_street_name));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(CanalLineFragment.CANAL_LOCATION_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_location));
            return false;
        } else if (TextUtils.isEmpty(type)) {
            getNavigator().validationError(CanalLineFragment.CANAL_TYPE_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_type));
            return false;
        } else if (TextUtils.isEmpty(subtype)) {
            getNavigator().validationError(CanalLineFragment.CANAL_SUBTYPE_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_sub_type));
            return false;
        } else if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(CanalLineFragment.CANAL_AREA_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_area));
            return false;
        } else if (TextUtils.isEmpty(width)) {
            getNavigator().validationError(CanalLineFragment.CANAL_WIDTH_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_canal_width));
            return false;
        } else if (TextUtils.isEmpty(condition)) {
            getNavigator().validationError(CanalLineFragment.CANAL_CONDITION_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_canal_condition));
            return false;
        } else if (TextUtils.isEmpty(startPoint)) {
            getNavigator().validationError(CanalLineFragment.CANAL_START_POINT_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_start_point));
            return false;
        } else if (TextUtils.isEmpty(endPoint)) {
            getNavigator().validationError(CanalLineFragment.CANAL_REMARKS_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(CanalLineFragment.CANAL_COMMON_ERROR, getBaseActivity().getString(R.string.err_canal_line_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String streetName, String location, String type, String subtype, String area, String width, String condition, String startPoint, String endPoint, String canalLineRemarks, String imagePath1, boolean is) {

    }
}