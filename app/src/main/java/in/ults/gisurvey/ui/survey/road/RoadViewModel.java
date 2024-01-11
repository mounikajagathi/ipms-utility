package in.ults.gisurvey.ui.survey.road;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;

public class RoadViewModel extends BaseViewModel<RoadNavigator> {


    public final MutableLiveData<String> nearRoad = new MutableLiveData<>();
    public final MutableLiveData<String> roadType = new MutableLiveData<>();
    public final MutableLiveData<String> roadWidth = new MutableLiveData<>();

    public final MutableLiveData<String> arRoadName = new MutableLiveData<>();
    public final MutableLiveData<String> arRoadType = new MutableLiveData<>();

    public final MutableLiveData<List<CommonItem>> roadTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> roadWidthList = new MutableLiveData<>();

    public final ObservableBoolean isARRoadTypeVisible = new ObservableBoolean(false);
    public final ObservableBoolean isARRoadNameVisible = new ObservableBoolean(false);

    public final MutableLiveData<List<Road>> roadList = new MutableLiveData<>();


    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingUsage = "";
    private String buildingSubType = "";

    public RoadViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void loadData() {
        roadTypeList.setValue(IPMSApp.getAppInstance().getLocMainItem().getRoadTypes());
        roadWidthList.setValue(IPMSApp.getAppInstance().getLocMainItem().getRoadWidths());
    }

    /**
     * @param nearRoad
     * @param roadType
     * @param roadWidth
     */
    void saveRoadDetails(String nearRoad, String roadType, String roadWidth,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertRoadDetails(nearRoad, roadType, roadWidth,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToTenantDetails(buildingUsage,buildingStatus,doorStatus,buildingSubType)) {
//                        getNavigator().navigateToTenantPage();
//                    } else if (canIGoToTaxDetails(buildingStatus, doorStatus)) {
//                        getNavigator().navigateToTaxPage();
//                    } else if (canIGoToEstablishmentDetails(buildingStatus, buildingSubType)) {
//                        getNavigator().navigateToEstablishmentPage();
//                    } else if (canIGoToMemberDetails(buildingStatus, buildingType, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToMemberPage();
//                    } else if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType,doorStatus)) {
//                        getNavigator().navigateToLiveHoodPage();
//                    } else if (canIGoToMoreDetails(buildingStatus,doorStatus,buildingType,buildingSubType)) {
//                        getNavigator().navigateToMorePage();
//                    } else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    } else {
//                        getNavigator().navigateToImageDetails();
//                    }
                    getNavigator().navigateToScreenSelection();

                })
                .subscribe());
    }

    /**
     * fetch property details from db and set to their fields according to the conditions
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        doorStatus = property.doorStatus;
        buildingStatus = property.buildingStatus;
        buildingType = property.buildingType;
        buildingSubType = property.buildingSubType;
        buildingUsage = property.buildingUsage;
        nearRoad.setValue(property.nearRoad);
        roadType.setValue(property.roadType);
        roadWidth.setValue(property.roadWidth);
        if(property.arRoadName.length()!=0&&!property.arRoadName.equalsIgnoreCase(NR_UPPERCASE)&&!property.arRoadName.equalsIgnoreCase(NA_UPPERCASE)){
            isARRoadNameVisible.set(true);
            arRoadName.setValue(getBaseActivity().getString(R.string.road_ar_name_hint)+property.arRoadName);
        }
        if(property.arRoadType.length()!=0&&!property.arRoadType.equalsIgnoreCase(NR_UPPERCASE)&&!property.arRoadType.equalsIgnoreCase(NA_UPPERCASE)){
            isARRoadTypeVisible.set(true);
            arRoadType.setValue(getBaseActivity().getString(R.string.road_ar_type_hint)+property.arRoadType);
        }
        if(property.isRoadValidationOk)
            getNavigator().disablePartialSave();
    }

    /**
     * validate each field and set error message if validation fails
     *
     * @param nearRoad
     * @param roadType
     * @param roadWidth
     * @return
     */
    protected boolean validateFields(String nearRoad, String roadType, String roadWidth) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(nearRoad)) {
            getNavigator().validationError(RoadFragment.NEAR_ROAD_ERROR, getBaseActivity().getString(R.string.error_near_road));
            return false;
        }
        if (TextUtils.isEmpty(roadType)) {
            getNavigator().validationError(RoadFragment.ROAD_TYPE_ERROR, getBaseActivity().getString(R.string.error_road_type));
            return false;
        }
        if (TextUtils.isEmpty(roadWidth)) {
            getNavigator().validationError(RoadFragment.ROAD_WIDTH_ERROR, getBaseActivity().getString(R.string.error_road_width));
            return false;
        }
        return true;
    }


    /**
     * get road list from db
     */
    public void getRoadList() {
        getCompositeDisposable().add(getDataManager()
                .getRoadList(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(roads -> {
                    if (roads != null && roads.size() > 0) {
                        roadList.setValue(roads);
                    }
                })
                .subscribe());
    }

    /**
     * to set the list of road
     */
    public void setRoadListData(String nearRoad, String roadType, String roadWidth) {
        this.nearRoad.setValue(nearRoad);
        this.roadType.setValue(roadType);
        this.roadWidth.setValue(roadWidth);
    }

    public void onNextClick() {
        getNavigator().saveRoadDetails(false);
    }

    public void onPartialSaveClick() {
        getNavigator().saveRoadDetails(true);
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnNearRoadInfo:
               // getNavigator().showToast("Hint");
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_near_road), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.btnRoadTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_type),InfoVideoNames.ROAD_TYPE_INFO_VIDEO);
                break;
            case R.id.btnRoadWidthInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_road_width),InfoVideoNames.ROAD_WIDTH_INFO_VIDEO);
                break;

        }
    }
}