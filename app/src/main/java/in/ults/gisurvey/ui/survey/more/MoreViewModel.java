package in.ults.gisurvey.ui.survey.more;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF;
import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NO;
import static in.ults.gisurvey.utils.AppConstants.NR_SCHEME;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;

/**
 * Created by Iswah M p on 2019-08-23.
 */

public class MoreViewModel extends BaseViewModel<MoreNavigator> {


    public final MutableLiveData<String> plotArea = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> wellPerennialMonthList = new MutableLiveData<>();

    public final MutableLiveData<String> typeOfLand = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> typeOfLandList = new MutableLiveData<>();

    public final MutableLiveData<String> buildingUnderScheme = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> buildingUnderSchemeList = new MutableLiveData<>();


    public final ObservableBoolean isWellPerennial = new ObservableBoolean(false);
    public final ObservableBoolean isWellSelected = new ObservableBoolean(false);


    public final MutableLiveData<ArrayList<VehicleDetailsItem>> vehicleData = new MutableLiveData<>();
    public final MutableLiveData<String> noOfVehicle = new MutableLiveData<>();

    public final ObservableBoolean doorStatusPDCDCGL = new ObservableBoolean(false);
    public final ObservableBoolean isBuildStatusDemolishedUnusable = new ObservableBoolean(false);
    public final ObservableBoolean establishmentUsageHouseFlatVilla = new ObservableBoolean(false);

    public final ObservableBoolean widgetVisibility = new ObservableBoolean(false);
    public final ObservableBoolean buildingStatusOnGoingWithoutRoof = new ObservableBoolean(false);

    private String buildingStatus = "";
    private String doorStatus = "";


    public final ObservableBoolean isMemberValidationok = new ObservableBoolean(false);
    public final ObservableBoolean isMemberVisible = new ObservableBoolean(false);

    public boolean isMemberGovtEmplyee = false;

    public MoreViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        noOfVehicle.setValue("-1");
    }

    public void setIsWellSelected(boolean isWellSelected) {
        this.isWellSelected.set(isWellSelected);
    }

    public void setIsWellPerennial(boolean isWellPerennial) {
        this.isWellPerennial.set(isWellPerennial);
    }


    public void onNextClick() {
        getNavigator().saveMoreDetails(false);
    }

    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveMoreDetails(true);
    }

    void insertMoreDetails(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails, String thozhilurapp, String kudumbasree, String healthInsurance,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance, isValidationOk,getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    } else {
//                        getNavigator().navigateToImagePage();
//                    }
                    getNavigator().navigateToScreenSelection();
                }).subscribe());
    }

    protected boolean validateFields(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails, String thozhilurapp, String kudumbasree, String healthInsurance) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(typeOfLand)) {
            getNavigator().validationError(MoreFragment.TYPE_LAND_ERROR, getBaseActivity().getString(R.string.error_type_land));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !isBuildStatusDemolishedUnusable.get() && establishmentUsageHouseFlatVilla.get()) {
            if (TextUtils.isEmpty(buildingUnderScheme)) {
                getNavigator().validationError(MoreFragment.BUILDING_UNDER_SCHEME_ERROR, getBaseActivity().getString(R.string.error_building_under_scheme));
                return false;
            } else if (TextUtils.isEmpty(plotArea) || (!plotArea.equalsIgnoreCase(AppConstants.NR_UPPERCASE) &&!plotArea.equalsIgnoreCase(AppConstants.NA_UPPERCASE)&& Double.parseDouble(plotArea) == 0)) {
                getNavigator().validationError(MoreFragment.PLOT_AREA_ERROR, getBaseActivity().getString(R.string.error_plot_area));
                return false;
            }
            if(!buildingStatusOnGoingWithoutRoof.get()){
                if (Integer.parseInt(noOfVehicle) > 0) {
                    for (VehicleDetailsItem data : vehicleDetails) {
                        if (data.getVehicleType().length() == 0 || data.getVehicleUsage().length() == 0) {
                            getNavigator().validationError(MoreFragment.VEHICLE_DETAILS_ERROR, "");
                            return false;
                        }
                    }
                }
                if (TextUtils.isEmpty(thozhilurapp)) {
                    getNavigator().validationError(MoreFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_thozhilurapp));
                    return false;
                } else if (TextUtils.isEmpty(kudumbasree)) {
                    getNavigator().validationError(MoreFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_kudumbasree));
                    return false;
                } else if (TextUtils.isEmpty(healthInsurance)) {
                    getNavigator().validationError(MoreFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_health_insurance));
                    return false;
                }
            }

        }
        return true;

    }


    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        super.onPropertyFetchedFromDb(property);
        buildingStatus = property.buildingStatus;
        doorStatus=property.doorStatus;

        doorStatusPDCDCGL.set(property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL));

        isBuildStatusDemolishedUnusable.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE));
        buildingStatusOnGoingWithoutRoof.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF));

        establishmentUsageHouseFlatVilla.set(property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA));
        buildingStatusOnGoingWithoutRoof.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF));

        typeOfLand.setValue(property.typeOfLand);
        if(property.isMoreValidationOk)
            getNavigator().disablePartialSave();
        if (!doorStatusPDCDCGL.get() && !isBuildStatusDemolishedUnusable.get() && establishmentUsageHouseFlatVilla.get()) {
            buildingUnderScheme.setValue(property.buildingUnderScheme);
            plotArea.setValue(property.plotArea);
            isMemberGovtEmplyee = isFamilyMemberGovtEmployee(property.memberDetails);
           if( canIGoToMemberDetails(property.buildingStatus,property.buildingType, property.buildingSubType,property.doorStatus, property.surveyType))
            {
                isMemberVisible.set(true);
                isMemberValidationok.set(property.isMemberValidationOk);
            }

            if(property.noOfVehicles.equalsIgnoreCase("")){
                noOfVehicle.setValue("0");
            }else{
                noOfVehicle.setValue(property.noOfVehicles.equalsIgnoreCase("-1") ? "0" : property.noOfVehicles);
            }
            setIsWellSelected(property.waterSourceType.contains(AppConstants.WATER_SOURCE_TYPE_DUG_WELL) || property.waterSourceType.contains(AppConstants.WATER_SOURCE_TYPE_TUBE_WELL));
            setIsWellPerennial(property.wellPerennial.equalsIgnoreCase(getBaseActivity().getString(R.string.more_well_seasonal)));

        }

        getNavigator().setCachedData(property);
    }


    void loadData() {
        wellPerennialMonthList.setValue(IPMSApp.getAppInstance().getLocMainItem().getFullMonth());
        typeOfLandList.setValue(IPMSApp.getAppInstance().getLocMainItem().getTypeOfLand());
        buildingUnderSchemeList.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingUnderAnyScheme());
    }
    public boolean isBuildingSchemeApplicable(String buildingScheme){
        if(buildingScheme.length()!=0&&!buildingScheme.equalsIgnoreCase(NR_UPPERCASE)&&!buildingScheme.equalsIgnoreCase(NA_UPPERCASE)&&!buildingScheme.equalsIgnoreCase(NO)&&isMemberGovtEmplyee){
            return false;
        }
        return true;
    }
    public void addVehicleOnClick() {
        getNavigator().addVehicle();
    }

    public void removeVehicleOnClick() {
        getNavigator().removeVehicle();
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnTypeOfLandInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_type_of_land),InfoVideoNames.MORE_DETAILS_TYPE_OF_LAND_INFO_VIDEO);
                break;
            case R.id.btnPlotAreaCentInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_plot_area_cent),InfoVideoNames.MORE_DETAILS_PLOT_AREA_INFO_VIDEO);
                break;
            case R.id.btnBuildingUnderSchemeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_building_under_scheme),InfoVideoNames.MORE_DETAILS_SCHEME_INFO_VIDEO);
                break;
            case R.id.btnNoOfVehicleInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_no_of_vehicle),InfoVideoNames.MORE_DETAILS_VEHICLES_NUMBER_INFO_VIDEO);
                break;
            case R.id.tvThozhilurapp:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_thozhilurapp),InfoVideoNames.MORE_DETAILS_THOZHILURAPP_INFO_VIDEO);
                break;
            case R.id.tvKudumbasree:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_kudumbasree),InfoVideoNames.MORE_DETAILS_KUDUMBASREE_INFO_VIDEO);
                break;
            case R.id.tvHealthInsurance:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_health_insurance),InfoVideoNames.MORE_DETAILS_GOVERNMENT_HEALTH_INSURANCE_INFO_VIDEO);
                break;
        }
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrPlotArea:
                plotArea.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }
}
