package in.ults.gisurvey.ui.main.utility.junction;

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

public class JunctionViewModel extends BaseViewModel<JunctionNavigator> {

    public final MutableLiveData<List<CommonItem>> roadJunctionPedestrianList = new MutableLiveData<>();


    public JunctionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutRoadJunctionNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_junction_details_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRoadJunctionLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_junction_details_location_details), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRoadJunctionNoRoadInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_junction_details_no_of_roads), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRoadJunctionPedestrianInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_junction_details_pedestrian), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_junction_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        roadJunctionPedestrianList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getJunctionPedestrian());
    }

    protected boolean validateFields(String name, String location, String noOfRoads, String pedestrian, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(name)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_NAME_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_name));
            return false;
        } else if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_LOCATION_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_location_details));
            return false;
        } else if (TextUtils.isEmpty(noOfRoads)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_NO_OF_ROADS_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_no_of_roads));
            return false;
        } else if (TextUtils.isEmpty(pedestrian)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_PEDESTRIAN_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_pedestrian));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_REMARKS_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(JunctionFragment.JUNCTION_COMMON_ERROR, getBaseActivity().getString(R.string.err_road_junction_details_photo_1));
            return false;
        }
        return true;
    }
    void saveUtilityDetails(String name,String location,String  noOfRoads,String  pedestrian,String remarks,String imagePath1,boolean is){

    }
}