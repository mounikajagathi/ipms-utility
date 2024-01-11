package in.ults.gisurvey.ui.survey.building;

import android.text.TextUtils;
import android.view.View;
import android.widget.PopupMenu;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.data.model.items.LocMainItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF;
import static in.ults.gisurvey.utils.AppConstants.DOOR_STATUS_NC;
import static in.ults.gisurvey.utils.AppConstants.HIGHER_FLOOTYPE_MATERIAL1;
import static in.ults.gisurvey.utils.AppConstants.HIGHER_FLOOTYPE_MATERIAL2;
import static in.ults.gisurvey.utils.AppConstants.HIGHER_FLOOTYPE_MATERIAL3;
import static in.ults.gisurvey.utils.AppConstants.HIGHER_FLOOTYPE_MATERIAL4;
import static in.ults.gisurvey.utils.AppConstants.HIGHER_FLOOTYPE_VALUE;
import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;

public class BuildingViewModel extends BaseViewModel<BuildingNavigator> {

    public final MutableLiveData<List<CommonItem>> floorTypeData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> wallTypeData = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<BuildingDetailsFloorAreaItem>> floorAreaData = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<BuildingDetailsRoofItem>> rootTypeData = new MutableLiveData<>();
    public final MutableLiveData<String> carporchArea = new MutableLiveData<>();
    public final MutableLiveData<String> commonStairArea = new MutableLiveData<>();
    public final MutableLiveData<String> buildingName = new MutableLiveData<>();
    public final MutableLiveData<String> colonyName = new MutableLiveData<>();
    public final MutableLiveData<String> pathwayArea = new MutableLiveData<>();
    public final MutableLiveData<String> yearOFStructural = new MutableLiveData<>();
    public final MutableLiveData<String> yearOfRoofTypeChange = new MutableLiveData<>();
    public final MutableLiveData<String> otherBuildingDetails = new MutableLiveData<>();
    public final ObservableBoolean isHigherFloorTypeIsVisible = new ObservableBoolean(false);
    public final ObservableBoolean isSubTypeRelatedBuilding = new ObservableBoolean(false);
    public final MutableLiveData<String> rationCard = new MutableLiveData<>();
    public final MutableLiveData<String> stairArea = new MutableLiveData<>();
    public final MutableLiveData<String> areaRemarks = new MutableLiveData<>();


    public final ObservableBoolean isARBuildingAgeVisible = new ObservableBoolean(false);
    public final ObservableBoolean isARFloorDetailsVisible = new ObservableBoolean(false);
    public final ObservableBoolean isARRoofDetailsVisible = new ObservableBoolean(false);
    public final ObservableBoolean isARModificationVisible = new ObservableBoolean(false);


    public final ObservableBoolean doorStatusPDCDCGL = new ObservableBoolean(false);
    public final ObservableBoolean doorStatusNC = new ObservableBoolean(false);
    public final ObservableBoolean establishmentUsageHouseFlatVilla = new ObservableBoolean(false);
    public final ObservableBoolean isCommonStairAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isCarPorchAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isStructuralChange = new ObservableBoolean(false);
    public final ObservableBoolean isRoofTypeChange = new ObservableBoolean(false);
    public final ObservableBoolean isStairAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isOtherBuilding = new ObservableBoolean(false);
    public final MutableLiveData<String> surveyNumber = new MutableLiveData<>("");
    public final ObservableField<String> yearOfConstruction = new ObservableField<>("");
    public final ObservableField<String> noOfRooms = new ObservableField<>("");
    public final ObservableField<String> totalYears = new ObservableField<>("");
    public final ObservableField<String> roofTotal = new ObservableField<>("");
    public final MutableLiveData<String> noOfRoof = new MutableLiveData<>("");
    public final MutableLiveData<String> noOfFloor = new MutableLiveData<>("");
    public final MutableLiveData<String> establishmentYear = new MutableLiveData<>();
    public final ObservableBoolean buildingStatusOnGoingWithoutRoof = new ObservableBoolean(false);
    public final ObservableBoolean buildingStatusOnGoing = new ObservableBoolean(false);
    public final MutableLiveData<String> arBuildingAge = new MutableLiveData<>("");
    public final MutableLiveData<String> arFloorDetails = new MutableLiveData<>("");
    public final MutableLiveData<String> arRoofDetails = new MutableLiveData<>("");
    public final MutableLiveData<String> arModification = new MutableLiveData<>("");
    public final ObservableBoolean isLivehoodValidationOk = new ObservableBoolean(false);
    public final ObservableBoolean isEstablishmentValidationok = new ObservableBoolean(false);
    public final ObservableBoolean isLivehoodVisible = new ObservableBoolean(false);
    public final ObservableBoolean isEstablishmentVisible = new ObservableBoolean(false);
    public final ObservableBoolean isAreaRemarksVisible = new ObservableBoolean(false);
    public final ObservableBoolean isTeleCall = new ObservableBoolean(false);


    public BuildingViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        carporchArea.setValue("");
        commonStairArea.setValue("");
        stairArea.setValue("");
    }

    public MutableLiveData<List<CommonItem>> getFloorTypeData() {
        return floorTypeData;
    }

    public MutableLiveData<List<CommonItem>> getWallTypeData() {
        return wallTypeData;
    }

    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        getNavigator().setFloorCount(survey.getFloorCount(), survey.getGroundFloor());
        getNavigator().setStructureType(survey);
    }

    /**
     * to fetch property details from db when back pressed and set property fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        doorStatusPDCDCGL.set(property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL));
        doorStatusNC.set(property.doorStatus.equalsIgnoreCase(DOOR_STATUS_NC));

        establishmentUsageHouseFlatVilla.set(property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA));
        isSubTypeRelatedBuilding.set(property.buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING));

        buildingStatusOnGoingWithoutRoof.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF));
        buildingStatusOnGoing.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING));

        isTeleCall.set(property.surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));

        if (property.buildingStatus.equalsIgnoreCase(BUILDING_STATUS_ONGOING_WITHOUT_ROOF) && property.doorStatus.equalsIgnoreCase(DOOR_STATUS_NC)) {
            wallTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getWallTypeNrNa());
        } else if (property.buildingStatus.equalsIgnoreCase(BUILDING_STATUS_ONGOING_WITHOUT_ROOF)) {
            wallTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getWallTypeNa());
        } else if (property.doorStatus.equalsIgnoreCase(DOOR_STATUS_NC)) {
            wallTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getWallTypeNr());
        } else {
            wallTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getWallType());
        }

        if (property.buildingName.length() == 0 && canISetNAForBuildingName(property.buildingType, property.buildingSubType)) {
            buildingName.setValue(getBaseActivity().getString(R.string.cmn_na));
            if (property.buildingUsage.equalsIgnoreCase(getBaseActivity().getString(R.string.building_usage_owned))) {
                getNavigator().disableBuildingName();
            }
        } else {
            buildingName.setValue(property.buildingName);
        }

        noOfFloor.setValue(property.noOfFloors.equalsIgnoreCase("-1") ? "0" : property.noOfFloors);
        floorAreaData.setValue(property.floorArea);
        areaRemarks.setValue(property.areaRemarks);
        int noOfFloors = Integer.parseInt(noOfFloor.getValue());
        if(noOfFloors > 0){
            isAreaRemarksVisible.set(true);
        }
        isCarPorchAvailable.set(property.carPorch.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
        if (isCarPorchAvailable.get()) {
            carporchArea.setValue(property.carPorchArea);
        }
        isCommonStairAvailable.set(property.commonStair.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
        isStairAvailable.set(property.stair.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
        if (isCommonStairAvailable.get()) {
            commonStairArea.setValue(property.commonStairArea);
        }
        if (isStairAvailable.get()) {
            stairArea.setValue(property.stairArea);
        }
        pathwayArea.setValue(property.pathwayArea);

        noOfRoof.setValue(property.noOfRoofType.equalsIgnoreCase("-1") ? "0" : property.noOfRoofType);
        roofTotal.set(property.roofTotal);
        rootTypeData.setValue(property.roofDetails);


        if (!doorStatusPDCDCGL.get()) {
            if (establishmentUsageHouseFlatVilla.get()) {
                colonyName.setValue(property.colonyName);
            }
            surveyNumber.setValue(property.surveyNumber);
            yearOfConstruction.set(property.yearsOfConstruction);
            totalYears.set(property.totalYears);
            noOfRooms.set(property.noOfRooms);

            isStructuralChange.set(property.anyStructuralChange.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isStructuralChange.get()) {
                yearOFStructural.setValue(property.structuralChangeYear);
            }
            isRoofTypeChange.set(property.roofTypeChange.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isRoofTypeChange.get()) {
                yearOfRoofTypeChange.setValue(property.roofTypeChangeYear);
            }
            isOtherBuilding.set(property.otherBuilding.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isOtherBuilding.get()) {
                otherBuildingDetails.setValue(property.otherBuildingDetails);
            }

        }
        establishmentYear.setValue(property.establishmentYear);
        getNavigator().setCachedData(property);

        setHigherFloorTypeVisibility(property.floorArea, property.floorType);
        if(property.arBuildingAge.length()!=0&&!property.arBuildingAge.equalsIgnoreCase(NR_UPPERCASE)&&!property.arBuildingAge.equalsIgnoreCase(NA_UPPERCASE)){
            isARBuildingAgeVisible.set(true);
            arBuildingAge.setValue(getBaseActivity().getString(R.string.building_age_hint)+property.arBuildingAge);
        }
        if(property.arRoofDetails.length()!=0&&!property.arRoofDetails.equalsIgnoreCase(NR_UPPERCASE)&&!property.arRoofDetails.equalsIgnoreCase(NA_UPPERCASE)){
            isARRoofDetailsVisible.set(true);
            arRoofDetails.setValue(getBaseActivity().getString(R.string.building_roof_hint)+property.arRoofDetails);
        }
        if(property.arFloorDetails.length()!=0&&!property.arFloorDetails.equalsIgnoreCase(NR_UPPERCASE)&&!property.arFloorDetails.equalsIgnoreCase(NA_UPPERCASE)){
            isARFloorDetailsVisible.set(true);
            arFloorDetails.setValue(getBaseActivity().getString(R.string.building_floor_details_hint)+property.arFloorDetails);
        }
        if(property.arModification.length()!=0&&!property.arModification.equalsIgnoreCase(NR_UPPERCASE)&&!property.arModification.equalsIgnoreCase(NA_UPPERCASE)){
            isARModificationVisible.set(true);
            arModification.setValue(getBaseActivity().getString(R.string.building_Modification_hint)+property.arModification);
        }
        rationCard.setValue(property.rationCard);
        if(canIGoToEstablishmentDetails(property.buildingStatus,property.establishmentUsage)){
            isEstablishmentVisible.set(true);
            isEstablishmentValidationok.set(property.isEstablishmentValidationOk);
        }
        if(canIGoToLiveHoodDetails(property.buildingStatus,property.buildingSubType,property.doorStatus)){
            isLivehoodVisible.set(true);
            isLivehoodValidationOk.set(property.isLivehoodValidationOk);
        }

        if(property.isBuildingValidationOk)
            getNavigator().disablePartialSave();
    }

    public void setHigherFloorTypeVisibility(ArrayList<BuildingDetailsFloorAreaItem> floorArea, ArrayList<String> flootType) {
        if (!doorStatusNC.get()) {
            if (getTotalFloorArea(floorArea) > HIGHER_FLOOTYPE_VALUE && isHigherFloorTypeContains(flootType)) {
                isHigherFloorTypeIsVisible.set(true);
            } else {
                isHigherFloorTypeIsVisible.set(false);
            }
        }

    }

    void loadData() {
        floorTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getFloorType());
        wallTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getWallType());
    }

    public void onNextClick() {
        getNavigator().saveBuildingDetails(false);
    }
    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveBuildingDetails(true);
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String buildingName, String surveyNumber, String yearsOfConstruction, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea,String stair, String stairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails, String areaRemarks) {
        getNavigator().clearValidationErrors();
        if (!buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(buildingName)) {
            getNavigator().validationError(BuildingFragment.BUILDING_NAME_ERROR, getBaseActivity().getString(R.string.error_building_name));
            return false;
        } else if (!isTeleCall.get() && !doorStatusPDCDCGL.get() && !buildingStatusOnGoingWithoutRoof.get() && establishmentUsageHouseFlatVilla.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(colonyName)) {
            getNavigator().validationError(BuildingFragment.COLONY_NAME_ERROR, getBaseActivity().getString(R.string.error_colony_name));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !isSubTypeRelatedBuilding.get() && !isTeleCall.get() && TextUtils.isEmpty(surveyNumber)) {
            getNavigator().validationError(BuildingFragment.SURVEY_NUMBER_ERROR, getBaseActivity().getString(R.string.error_survey_number));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(yearsOfConstruction)) {
            getNavigator().validationError(BuildingFragment.YEAR_OF_CONSTRUCTION_ERROR, getBaseActivity().getString(R.string.error_year_of_construction));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && yearsOfConstruction.length() != 4) {
            getNavigator().validationError(BuildingFragment.YEAR_OF_CONSTRUCTION_ERROR, getBaseActivity().getString(R.string.error_valid_year_of_construction));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !buildingStatusOnGoingWithoutRoof.get() && !doorStatusNC.get() && establishmentYear.getValue().length() != 0 && !establishmentYear.getValue().equalsIgnoreCase(NA_UPPERCASE) && !establishmentYear.getValue().equalsIgnoreCase(NA_UPPERCASE) && Integer.valueOf(yearsOfConstruction) > Integer.valueOf(establishmentYear.getValue())) {
            getNavigator().showWarning(getBaseActivity().getString(R.string.error_construction_establishment_year));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(noOfRooms)) {
            getNavigator().validationError(BuildingFragment.NUMBER_OF_ROOM_ERROR, getBaseActivity().getString(R.string.error_no_of_rooms));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && Integer.parseInt(noOfFloors) == 0) {
            getNavigator().validationError(BuildingFragment.NUMBER_OF_FLOOR_ERROR, getBaseActivity().getString(R.string.error_no_of_floor));
            return false;
        }else if (!buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(areaRemarks)) {
            getNavigator().validationError(BuildingFragment.AREA_REMARKS_ERROR, getBaseActivity().getString(R.string.error_area_remarks));
            return false;
        }
        if (!buildingStatusOnGoingWithoutRoof.get() && !doorStatusNC.get()) {
            for (BuildingDetailsFloorAreaItem data : floorArea) {
                if (data.getFloorNumberDispaly().length() == 0 || data.getArea().length() == 0 || (!data.getArea().equalsIgnoreCase(AppConstants.NR_UPPERCASE) && Double.parseDouble(data.getArea()) == 0)) {
                    getNavigator().validationError(BuildingFragment.NUMBER_OF_FLOOR_AREA_ERROR, "");
                    return false;
                }
            }
        }
        if (!buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(structureType)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_structure_type));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(carPorch)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_car_porch));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && isCarPorchAvailable.get() && TextUtils.isEmpty(carPorchArea)) {
            getNavigator().validationError(BuildingFragment.CAR_PORCH_AREA_ERROR, getBaseActivity().getString(R.string.error_car_porch_area));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(commonStair)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_common_stair));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && isCommonStairAvailable.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(commonStairArea)) {
            getNavigator().validationError(BuildingFragment.COMMON_STAIRS_AREA_ERROR, getBaseActivity().getString(R.string.error_common_stair_area));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get()&&!isSubTypeRelatedBuilding.get()&&TextUtils.isEmpty(stair)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_stair));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get()&&isStairAvailable.get() &&!isSubTypeRelatedBuilding.get()&& TextUtils.isEmpty(stairArea)) {
            getNavigator().validationError(BuildingFragment.STAIRS_AREA_ERROR, getBaseActivity().getString(R.string.error_common_stair_area));
            return false;
        }else if (!buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(pathwayArea)) {
            getNavigator().validationError(BuildingFragment.PATHWAY_AREA_ERROR, getBaseActivity().getString(R.string.error_pathway_area));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoing.get() && !buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(anyStructuralChange)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_structural_change));
            return false;
        } else if (isStructuralChange.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !buildingStatusOnGoing.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(anyStructuralChangeYear)) {
            getNavigator().validationError(BuildingFragment.STRUCTURAL_YEAR_ERROR, getBaseActivity().getString(R.string.error_structural_change_year));
            return false;
        } else if (isStructuralChange.get() && !buildingStatusOnGoingWithoutRoof.get()&& !buildingStatusOnGoing.get() && !isSubTypeRelatedBuilding.get() && anyStructuralChangeYear.length() < 4) {
            getNavigator().validationError(BuildingFragment.STRUCTURAL_YEAR_ERROR, getBaseActivity().getString(R.string.error_structural_change_year_valid));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !buildingStatusOnGoing.get()&& !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(anyRoofChange)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_roof_type_change));
            return false;
        } else if (isStructuralChange.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !buildingStatusOnGoing.get()&& !isSubTypeRelatedBuilding.get() && Integer.valueOf(anyStructuralChangeYear) < Integer.valueOf(yearsOfConstruction)) {
            getNavigator().showWarning(getBaseActivity().getString(R.string.error_year_of_structural_change));
            return false;
        } else if (isRoofTypeChange.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !buildingStatusOnGoing.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(anyRoofChangeYear)) {
            getNavigator().validationError(BuildingFragment.ROOF_CHANGE_YEAR_ERROR, getBaseActivity().getString(R.string.error_roof_type_change_year));
            return false;
        } else if (isRoofTypeChange.get() && !doorStatusNC.get() &&!buildingStatusOnGoing.get() && !buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && anyRoofChangeYear.length() < 4) {
            getNavigator().validationError(BuildingFragment.ROOF_CHANGE_YEAR_ERROR, getBaseActivity().getString(R.string.error_roof_type_change_year_valid));
            return false;
        } else if (isRoofTypeChange.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() &&!buildingStatusOnGoing.get() && !isSubTypeRelatedBuilding.get() && Integer.valueOf(anyRoofChangeYear) < Integer.valueOf(yearsOfConstruction)) {
            getNavigator().showWarning(getBaseActivity().getString(R.string.error_year_of_roof_change));
            return false;
        } else if (!isTeleCall.get() && !doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(otherBuilding)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_other_buildings));
            return false;
        } else if (!isTeleCall.get() && isOtherBuilding.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(otherBuildingDetails)) {
            getNavigator().validationError(BuildingFragment.OTHER_BUILDING_DETAILS_ERROR, getBaseActivity().getString(R.string.error_other_building_details));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !buildingStatusOnGoingWithoutRoof.get() && floorType.size() == 0) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_floor_type));
            return false;
        } else if (!doorStatusPDCDCGL.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(higherFloorSqft)) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_higher_floor_type));
            return false;
        } else if (!doorStatusPDCDCGL.get() && wallType.size() == 0) {
            getNavigator().validationError(BuildingFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_wall_type));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(roofTotal)) {
            getNavigator().validationError(BuildingFragment.ROOF_TOTAL_ERROR, getBaseActivity().getString(R.string.error_roof_total));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && Integer.parseInt(roofTotal) > 100) {
            getNavigator().validationError(BuildingFragment.ROOF_TOTAL_ERROR, getBaseActivity().getString(R.string.error_valid_roof_total));
            return false;
        } else if (!buildingStatusOnGoingWithoutRoof.get() && Integer.parseInt(noOfRoofType) == 0) {
            getNavigator().validationError(BuildingFragment.NUMBER_OF_ROOF_TYPE_ERROR, getBaseActivity().getString(R.string.error_no_of_roof_type));
            return false;
        }

        if (!buildingStatusOnGoingWithoutRoof.get()) {
            int totalPercent = 0;
            int roofTotalValue = Integer.parseInt(roofTotal);
            for (BuildingDetailsRoofItem data : roofDetails) {
                if (data.getRoofType().length() == 0 || data.getRoofPercent().length() == 0) {
                    getNavigator().validationError(BuildingFragment.ROOF_TYPE_ERROR, "");
                    return false;
                } else {
                    totalPercent = totalPercent + Integer.parseInt(data.getRoofPercent());
                }
            }

            if (totalPercent != roofTotalValue) {
                getNavigator().validationError(BuildingFragment.ROOF_TOTAL_ERROR, getBaseActivity().getString(R.string.error_roof_total_mismatch));
                return false;
            }
        }

        return true;
    }

    /**
     * to save building details in db  and navigate to next page
     */
    void saveBuildingDetails(String buildingName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String porchArea, String commonStair, String commonStairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails,String stair, String stairArea, String areaRemarks, boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, porchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, anyStructuralChange, anyStructuralChangeYear, anyRoofChange, anyRoofChangeYear, otherBuildingDetails,stair,stairArea,areaRemarks,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getNavigator().navigateToScreenSelection())
                .subscribe());
    }

    public void isCarPorchAvailable(boolean isCarPorch) {
        isCarPorchAvailable.set(isCarPorch);
        if (!isCarPorch) {
            carporchArea.setValue("");
        }
    }

    public void isCommonStairAvailable(boolean isCommonStair) {
        isCommonStairAvailable.set(isCommonStair);
        if (!isCommonStair) {
            commonStairArea.setValue("");
        }
    }
    public void isStairAvailable(boolean isStair) {
        isStairAvailable.set(isStair);
        if (!isStair) {
            stairArea.setValue("");
        }
    }
    public void isStructureChangeAvailable(boolean isStructure) {
        isStructuralChange.set(isStructure);
        if (!isStructure) {
            yearOFStructural.setValue("");
        }
    }

    public void isRoofChangeAvailable(boolean isRoofChange) {
        isRoofTypeChange.set(isRoofChange);
        if (!isRoofChange) {
            yearOfRoofTypeChange.setValue("");
        }
    }

    public void isOtherBuildingAvailable(boolean isOtherBuild) {
        isOtherBuilding.set(isOtherBuild);
        if (!isOtherBuild) {
            otherBuildingDetails.setValue("");
        }
    }

    public ObservableBoolean getDoorStatusPDCDCGL() {
        return doorStatusPDCDCGL;
    }

    public void addFloorAreaOnClick() {
        getNavigator().addFloorArea();
    }

    public void removeFloorAreaOnClick() {
        getNavigator().removeFloorArea();
    }

    public void addRoofTypeOnClick() {
        getNavigator().addRoofType();
    }

    public void removeRoofTypeOnClick() {
        getNavigator().removeRoofType();
    }


    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrBuildingName:
                //  buildingName.setValue(AppConstants.NR_UPPERCASE);
                getNavigator().showBuildNameNANRPopUp();
                break;
            case R.id.nrColonyName:
//                colonyName.setValue(AppConstants.NR_UPPERCASE);
                getNavigator().showColonyNameNANRPopUp();
                break;
            case R.id.nrSurveyNumber:
                surveyNumber.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrPorchArea:
                carporchArea.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrCommonStairArea:
                commonStairArea.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrStairArea:
                stairArea.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrPathWayArea:
                getNavigator().showPathWayNANRPopUP();
                break;
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuildingNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_building_name), InfoVideoNames.BUILDING_DETAILS_NAME_INFO_VIDEO);
                break;
            case R.id.btnSurveyNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_survey_no), InfoVideoNames.BUILDING_DETAILS_SURVEY_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnYearOfConstructionInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_year_of_construction), InfoVideoNames.BUILDING_DETAILS_YEAR_OF_CONSTRUCTION_INFO_VIDEO);
                break;
            case R.id.btnHowManyYearsInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_how_many_years), InfoVideoNames.BUILDING_DETAILS_HOW_MANY_YEARS_INFO_VIDEO);
                break;
            case R.id.btnNoOfRoomsInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_no_of_rooms), InfoVideoNames.BUILDING_DETAILS_NUMBER_OF_ROOMS_INFO_VIDEO);
                break;
            case R.id.btnNoOfFloorInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_no_of_floors), InfoVideoNames.BUILDING_DETAILS_NUMBER_OF_FLOORS_INFO_VIDEO);
                break;
            case R.id.tvStructureType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_structure_type), InfoVideoNames.BUILDING_DETAILS_STRUCTURE_TYPE_INFO_VIDEO);
                break;
            case R.id.tvCarPorchArea:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_car_porch), InfoVideoNames.BUILDING_DETAILS_CAR_PORCH_OR_PARKING_INFO_VIDEO);
                break;
            case R.id.btnPorchAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_porch_area), InfoVideoNames.BUILDING_DETAILS_CAR_PORCH_OR_PARKING_AREA_INFO_VIDEO);
                break;
            case R.id.btnCommonStairAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_common_stair_area), InfoVideoNames.BUILDING_DETAILS_COMMON_STAIR_OR_STAIR_ROOM_AREA_INFO_VIDEO);
                break;
            case R.id.btnPathWayAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_path_way_area), InfoVideoNames.BUILDING_DETAILS_PATHWAY_AREA_INFO_VIDEO);
                break;
            case R.id.tvAnyStructuralChange:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_any_structural_change), InfoVideoNames.BUILDING_DETAILS_ANY_STRUCTURAL_CHANGE_DONE_INFO_VIDEO);
                break;
            case R.id.btnAnyStructChangeYearInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_any_structural_change_year), InfoVideoNames.BUILDING_DETAILS_STRUCTURAL_YEAR_OF_CHANGE_INFO_VIDEO);
                break;
            case R.id.tvAnyRoofTypeChange:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_any_rooftype_change), InfoVideoNames.BUILDING_DETAILS_ANY_ROOF_TYPE_CHANGE_DONE_INFO_VIDEO);
                break;
            case R.id.btnAnyRoofTypeChangeYearInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_any_rooftype_change_year), InfoVideoNames.BUILDING_DETAILS_ROOF_YEAR_OF_CHANGE_INFO_VIDEO);
                break;
            case R.id.tvOtherBuilding:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_other_building), InfoVideoNames.BUILDING_DETAILS_OTHER_BUILDING_INFO_VIDEO);
                break;
            case R.id.btnOtherBuildingDetailsInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_other_building_details), InfoVideoNames.BUILDING_DETAILS_OTHER_BUILDING_PROPERTY_DETAILS_INFO_VIDEO);
                break;
            case R.id.txtFloorType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_floor_type), InfoVideoNames.BUILDING_DETAILS_FLOOR_TYPE_INFO_VIDEO);
                break;
            case R.id.tvHigherFloorType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_higher_floor_type), InfoVideoNames.BUILDING_DETAILS_HIGHER_FLOOR_TYPE_INFO_VIDEO);
                break;
            case R.id.txtWallType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_wall_type), InfoVideoNames.BUILDING_DETAILS_WALL_TYPE_INFO_VIDEO);
                break;
            case R.id.btnRoofTotalPercentInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_roof_total_percent), InfoVideoNames.BUILDING_DETAILS_ROOF_TOTAL_PERCENTAGE_INFO_VIDEO);
                break;
            case R.id.btnNoOfRoofTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_no_of_roof_type), InfoVideoNames.BUILDING_DETAILS_NUMBER_OF_ROOF_TYPE_INFO_VIDEO);
                break;
            case R.id.btnStairAreaInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_common_stair_area),InfoVideoNames.BUILDING_DETAILS_COMMON_STAIR_OR_STAIR_ROOM_AREA_INFO_VIDEO);
                break;
            case R.id.btnAreaRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_area_remarks),InfoVideoNames.BUILDING_DETAILS_AREA_REMARKS_INFO_VIDEO);
                break;
        }
    }

    private double getTotalFloorArea(ArrayList<BuildingDetailsFloorAreaItem> floors) {
        double area = 0;
        for (BuildingDetailsFloorAreaItem item : floors) {
            if (item.getArea().length() != 0) {
                try {
                    area = area + Double.valueOf(item.getArea());

                } catch (Exception e) {

                }
            }
        }
        return area;
    }

    private boolean isHigherFloorTypeContains(ArrayList<String> material) {

        for (String item : material) {
            if (item.equals(HIGHER_FLOOTYPE_MATERIAL1) || item.equals(HIGHER_FLOOTYPE_MATERIAL2) || item.equals(HIGHER_FLOOTYPE_MATERIAL3) || item.equals(HIGHER_FLOOTYPE_MATERIAL4)) {
                return true;
            }
        }
        return false;
    }
}