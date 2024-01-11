package in.ults.gisurvey.ui.main.utility.signboard;

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

public class SignboardViewModel extends BaseViewModel<SignboardNavigator> {

    public final MutableLiveData<List<CommonItem>> roadSignboardCategoryList = new MutableLiveData<>();

    public SignboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutRoadSignboardPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_signboard_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRoadSignboardCategoryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_signboard_details_category), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_signboard_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        roadSignboardCategoryList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getSignboardCategory());
    }

    protected boolean validateFields(String place, String category, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(SignboardFragment.SIGN_BOARD_PLACE_ERROR, getBaseActivity().getString(R.string.err_road_signboard_details_place));
            return false;
        } else if (TextUtils.isEmpty(category)) {
            getNavigator().validationError(SignboardFragment.SIGN_BOARD_CATEGORY_ERROR, getBaseActivity().getString(R.string.err_road_signboard_details_category));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(SignboardFragment.SIGN_BOARD_REMARKS_ERROR, getBaseActivity().getString(R.string.err_road_signboard_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(SignboardFragment.SIGN_BOARD_COMMON_ERROR, getBaseActivity().getString(R.string.err_road_signboard_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String place, String category, String remarks, String imagePath1, boolean is) {

    }
}