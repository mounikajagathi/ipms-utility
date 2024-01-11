package in.ults.gisurvey.ui.main.utility.park;

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

public class ParkViewModel extends BaseViewModel<ParkNavigator> {

    public final MutableLiveData<List<CommonItem>> parkTypeList = new MutableLiveData<>();

    public ParkViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutParkLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_area), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkSurveyNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_survey_no), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutParkTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_park_type), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_park_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        parkTypeList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getParkType());
    }

    protected boolean validateFields(String location, String name, String area, String surveyNo, String type, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(ParkFragment.PARK_LOCATION_ERROR, getBaseActivity().getString(R.string.err_park_details_location));
            return false;
        } else if (TextUtils.isEmpty(name)) {
            getNavigator().validationError(ParkFragment.PARK_NAME_ERROR, getBaseActivity().getString(R.string.err_park_details_name));
            return false;
        } else if (TextUtils.isEmpty(area)) {
            getNavigator().validationError(ParkFragment.PARK_AREA_ERROR, getBaseActivity().getString(R.string.err_park_details_area));
            return false;
        } else if (TextUtils.isEmpty(surveyNo)) {
            getNavigator().validationError(ParkFragment.PARK_SURVEY_NUMBER_ERROR, getBaseActivity().getString(R.string.err_park_details_survey_no));
            return false;
        } else if (TextUtils.isEmpty(type)) {
            getNavigator().validationError(ParkFragment.PARK_TYPE_ERROR, getBaseActivity().getString(R.string.err_park_details_type));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(ParkFragment.PARK_REMARKS_ERROR, getBaseActivity().getString(R.string.err_park_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(ParkFragment.PARK_COMMON_ERROR, getBaseActivity().getString(R.string.err_park_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String name, String area, String surveyNo, String type, String remarks, String imagePath1, boolean is) {

    }
}