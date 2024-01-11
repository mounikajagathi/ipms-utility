package in.ults.gisurvey.ui.main.utility.bridge;

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

public class BridgeViewModel extends BaseViewModel<BridgeNavigator> {


    public final MutableLiveData<List<CommonItem>> bridgeMaterialList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> bridgeSubTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> bridgeMaintainByList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> bridgeFootPathConstructionMaterialList = new MutableLiveData<>();

    public BridgeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutBridgeNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_name), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutBridgePlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_place), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutBridgeMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_bridge_material), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutBridgeSubTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_bridge_sub_type), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutBridgeWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_bridge_width), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutBridgeLengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_bridge_length), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutFootpathConstructionMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_footpath_construction_material), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutMaintainByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_maintain_by), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bridge_details_remarks), InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        bridgeMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeMaterial());
        bridgeSubTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeSubType());
        bridgeMaintainByList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeMaintainedBy());
        bridgeFootPathConstructionMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getBridgeFootpathMaterial());
    }

    protected boolean validateFields(String bridgeName, String bridgePlace, String bridgeMaterial, String bridgeSubtype, String bridgeWidth, String bridgeLength, String bridgeFootPathConstMat, String bridgeMaintainBy, String bridgeRemarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(bridgeName)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_NAME_ERROR, getBaseActivity().getString(R.string.err_bridge_details_name));
            return false;
        } else if (TextUtils.isEmpty(bridgePlace)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_PLACE_ERROR, getBaseActivity().getString(R.string.err_bridge_details_place));
            return false;
        } else if (TextUtils.isEmpty(bridgeMaterial)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_MATERIAL_ERROR, getBaseActivity().getString(R.string.err_bridge_details_bridge_material));
            return false;
        } else if (TextUtils.isEmpty(bridgeSubtype)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_SUBTYPE_ERROR, getBaseActivity().getString(R.string.err_bridge_details_bridge_sub_type));
            return false;
        } else if (TextUtils.isEmpty(bridgeWidth)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_WIDTH_ERROR, getBaseActivity().getString(R.string.err_bridge_details_bridge_width));
            return false;
        } else if (TextUtils.isEmpty(bridgeLength)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_LENGTH_ERROR, getBaseActivity().getString(R.string.err_bridge_details_bridge_length));
            return false;
        } else if (TextUtils.isEmpty(bridgeFootPathConstMat)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_FOOTPATH_CONSTRUCTION_MAT_ERROR, getBaseActivity().getString(R.string.err_bridge_details_footpath_construction_material));
            return false;
        } else if (TextUtils.isEmpty(bridgeMaintainBy)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_MAINTAIN_BY_ERROR, getBaseActivity().getString(R.string.err_bridge_details_maintain_by));
            return false;
        } else if (TextUtils.isEmpty(bridgeRemarks)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_REMARKS_ERROR, getBaseActivity().getString(R.string.err_bridge_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(BridgeFragment.BRIDGE_COMMON_ERROR, getBaseActivity().getString(R.string.err_bridge_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String bridgeName, String bridgePlace, String bridgeMaterial, String bridgeSubtype, String bridgeWidth, String bridgeLength, String bridgeFootPathConstMat, String bridgeMaintainBy, String bridgeRemarks, String imagePath1, boolean isValidationOk) {

    }

}