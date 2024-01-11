package in.ults.gisurvey.ui.main.utility.divider;

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

public class DividerViewModel extends BaseViewModel<DividerNavigator> {

    public final MutableLiveData<List<CommonItem>> dividerMaterialList = new MutableLiveData<>();

    public DividerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutDividerPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDividerRoadNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_road_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDividerLengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_length), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDividerWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_width), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDividerStartEndInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_start_end), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutDividerMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_material), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_divider_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        dividerMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDividerMaterial());
    }

    protected boolean validateFields(String place, String roadName, String length, String width, String material, String startEnd, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(DividerFragment.DIVIDER_PLACE_ERROR, getBaseActivity().getString(R.string.err_divider_details_place));
            return false;
        } else if (TextUtils.isEmpty(roadName)) {
            getNavigator().validationError(DividerFragment.DIVIDER_ROAD_NAME_ERROR, getBaseActivity().getString(R.string.err_divider_details_road_name));
            return false;
        } else if (TextUtils.isEmpty(length)) {
            getNavigator().validationError(DividerFragment.DIVIDER_LENGTH_ERROR, getBaseActivity().getString(R.string.err_divider_details_length));
            return false;
        } else if (TextUtils.isEmpty(width)) {
            getNavigator().validationError(DividerFragment.DIVIDER_WIDTH_ERROR, getBaseActivity().getString(R.string.err_divider_details_width));
            return false;
        } else if (TextUtils.isEmpty(material)) {
            getNavigator().validationError(DividerFragment.DIVIDER_MATERIAL_ERROR, getBaseActivity().getString(R.string.err_divider_details_material));
            return false;
        } else if (TextUtils.isEmpty(startEnd)) {
            getNavigator().validationError(DividerFragment.DIVIDER_START_END_ERROR, getBaseActivity().getString(R.string.err_divider_details_start_end));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(DividerFragment.DIVIDER_REMARKS_ERROR, getBaseActivity().getString(R.string.err_divider_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(DividerFragment.DIVIDER_COMMON_ERROR, getBaseActivity().getString(R.string.err_divider_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String place, String roadName, String length, String width, String material, String startEnd, String remarks, String imagePath1, boolean is) {

    }

}