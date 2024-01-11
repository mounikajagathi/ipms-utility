package in.ults.gisurvey.ui.main.utility.sidewalk;

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

public class SideWalkViewModel extends BaseViewModel<SideWalkNavigator> {

    public final MutableLiveData<List<CommonItem>> sideWalkPlacementList = new MutableLiveData<>();

    public SideWalkViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutSideWalkStreetNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_street_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWalkLengthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_length), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWalkWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_width), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWalkStartEndInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_start_end), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutSideWalkPlacementInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_placement), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_side_walk_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        sideWalkPlacementList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getDrainagePlacement());
    }

    protected boolean validateFields(String streetName, String length, String width, String startEnd, String placement, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(streetName)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_STREET_NAME_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_road_name));
            return false;
        } else if (TextUtils.isEmpty(length)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_LENGTH_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_length));
            return false;
        } else if (TextUtils.isEmpty(width)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_WIDTH_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_width));
            return false;
        } else if (TextUtils.isEmpty(startEnd)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_START_END_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_start_end));
            return false;
        } else if (TextUtils.isEmpty(placement)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_PLACEMENT_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_placement));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_REMARKS_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(SideWalkFragment.SIDE_WALK_COMMON_ERROR, getBaseActivity().getString(R.string.err_side_walk_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String streetName, String length, String width, String startEnd, String placement, String remarks, String imagePath1, boolean is) {

    }
}