package in.ults.gisurvey.ui.main.utility.playground;

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

public class PlaygroundViewModel extends BaseViewModel<PlaygroundNavigator> {

    public final MutableLiveData<List<CommonItem>> playgroundTypeList = new MutableLiveData<>();

    public PlaygroundViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutPlaygroundLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_playground_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPlaygroundNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_playground_details_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPlaygroundTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_playground_details_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutPlaygroundAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_playground_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_playground_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        playgroundTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getPlaygroundGroundType());
    }

    protected boolean validateFields(String location, String groundName, String groundType, String area, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_LOCATION_ERROR, getBaseActivity().getString(R.string.err_playground_details_location));
            return false;
        } else if (TextUtils.isEmpty(groundName)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_GROUND_NAME_ERROR, getBaseActivity().getString(R.string.err_playground_details_name));
            return false;
        } else if (TextUtils.isEmpty(groundType)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_GROUND_TYPE_ERROR, getBaseActivity().getString(R.string.err_playground_details_type));
            return false;
        } else if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_AREA_ERROR, getBaseActivity().getString(R.string.err_playground_details_area));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_REMARKS_ERROR, getBaseActivity().getString(R.string.err_playground_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(PlaygroundFragment.PLAYGROUND_COMMON_ERROR, getBaseActivity().getString(R.string.err_playground_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String groundName, String groundType, String area, String remarks, String imagePath1, boolean is) {

    }
}