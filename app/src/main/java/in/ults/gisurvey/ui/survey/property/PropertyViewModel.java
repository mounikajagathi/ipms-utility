package in.ults.gisurvey.ui.survey.property;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.data.model.items.ExcelDataItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_AC;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_AR_TAX_TOTAL;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_BUILDING_AGE;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_BUILDING_USEAGE;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_FLOOR_AREA;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_FLOOR_DETAILS;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_MODIFICATION;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_NEW_PROPERTY_NO;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_NEW_SUB_NO;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_NEW_WARD;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_OCCUPIER_DETAILS;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_OLD_PROPERTY_NO;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_OLD_SUB_NO;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_OLD_WARD;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_OWNER_ADDRESS_ENG;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_ROAD_NAME;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_ROAD_TYPE;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_ROOF_DETAILS;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_UID;
import static in.ults.gisurvey.utils.AppConstants.AR_FILE_COL_ZONE;
import static in.ults.gisurvey.utils.AppConstants.DOOR_STATUS_NC;
import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NEW_PROP_ID_VERIFICATION;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;
import static in.ults.gisurvey.utils.CommonUtils.countChar;

public class PropertyViewModel extends BaseViewModel<PropertyNavigator> {


    public final MutableLiveData<List<CommonItem>> buildingStatusData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> buildingTypeData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> buildingSubTypeData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> buildingUnderData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> doorStatusData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> landmarkCategoryData = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> landmarkSubCategoryData = new MutableLiveData<>();

    public final ObservableBoolean isToiletAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildingTypeAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildingSubTypeAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildStatusDemolishedUnusableOnGoing = new ObservableBoolean(false);
    public final ObservableBoolean isBuildStatusDemolishedUnusable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildStatusOnGoing = new ObservableBoolean(false);
    public final ObservableBoolean isLandmarkCategory = new ObservableBoolean(false);
    public final ObservableBoolean isElectricityAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildingUnderStateGov = new ObservableBoolean(false);
    public final ObservableBoolean isBuildingTypeEducational = new ObservableBoolean(false);
    public final ObservableBoolean isCheckVisible = new ObservableBoolean(true);
    public final ObservableBoolean isWidgetVisible = new ObservableBoolean(false);
    public final ObservableBoolean isOldPropIdEnabled = new ObservableBoolean(false);
    public final ObservableBoolean isSubTypeRelatedBuilding = new ObservableBoolean(false);
    public final ObservableBoolean isTeleCall = new ObservableBoolean(false);

    public final ObservableBoolean isARACVisible = new ObservableBoolean(false);
    public final ObservableBoolean isARBuildingUsageVisible = new ObservableBoolean(false);

    public final MutableLiveData<String> ar_ac_hint = new MutableLiveData<>();
    public final MutableLiveData<String> ar_building_usage_hint = new MutableLiveData<>();

    public final MutableLiveData<String> ownershipEducational = new MutableLiveData<>();
    public final MutableLiveData<String> toiletWasteDisposal = new MutableLiveData<>();
    public final MutableLiveData<String> landmarkName = new MutableLiveData<>();
    public final MutableLiveData<String> landmarkCategory = new MutableLiveData<>();
    public final MutableLiveData<String> landmarkSubCategory = new MutableLiveData<>();
    public final MutableLiveData<String> droneId = new MutableLiveData<>();
    public final MutableLiveData<String> uID = new MutableLiveData<>();
    public final MutableLiveData<String> newPropertyID1 = new MutableLiveData<>();
    public final MutableLiveData<String> newPropertyID2 = new MutableLiveData<>();
    public final MutableLiveData<String> newPropertyID3 = new MutableLiveData<>();
    public final MutableLiveData<String> newPropertyID4 = new MutableLiveData<>();
    public final MutableLiveData<String> oldPropertyID1 = new MutableLiveData<>();
    public final MutableLiveData<String> oldPropertyID2 = new MutableLiveData<>();
    public final MutableLiveData<String> oldPropertyID3 = new MutableLiveData<>();
    public final MutableLiveData<String> consumerNumber = new MutableLiveData<>();
    public final MutableLiveData<String> nearPropertyID1 = new MutableLiveData<>();
    public final MutableLiveData<String> nearPropertyID2 = new MutableLiveData<>();
    public final MutableLiveData<String> nearPropertyID3 = new MutableLiveData<>();
    public final MutableLiveData<String> nearPropertyID4 = new MutableLiveData<>();
    public final MutableLiveData<String> remarks = new MutableLiveData<>();
    public final MutableLiveData<String> newPropARRemark = new MutableLiveData<>();
    public final MutableLiveData<String> ar_owner_address = new MutableLiveData<>();
    public final MutableLiveData<String> surveyType = new MutableLiveData<>();

    public final MutableLiveData<String> ar_zone = new MutableLiveData<>();
    public final MutableLiveData<String> ar_ac = new MutableLiveData<>();
    public final MutableLiveData<String> ar_floor_area = new MutableLiveData<>();
    public final MutableLiveData<String> ar_building_usage = new MutableLiveData<>();
    public final MutableLiveData<String> ar_road_type = new MutableLiveData<>();
    public final MutableLiveData<String> ar_road_name = new MutableLiveData<>();
    public final MutableLiveData<String> ar_building_age = new MutableLiveData<>();
    public final MutableLiveData<String> ar_roof_details = new MutableLiveData<>();
    public final MutableLiveData<String> ar_floor_details = new MutableLiveData<>();
    public final MutableLiveData<String> ar_modification = new MutableLiveData<>();
    public final MutableLiveData<String> ar_occupier_details = new MutableLiveData<>();
    public final MutableLiveData<String> ar_tax_total = new MutableLiveData<>();

    public final MutableLiveData<String> establishmentUsage = new MutableLiveData<>();


    public final MutableLiveData<List<CommonItem>> remarksList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> toiletDisposalList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> ownershipEducationList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> establishmentUsageList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> surveyTypeList = new MutableLiveData<>();


    public final ObservableBoolean doorStatusPermanentDoorClosedGateLockedDoorClosed = new ObservableBoolean(false);
    public final ObservableBoolean doorStatusNC = new ObservableBoolean(false);

    public final ObservableBoolean isNewPropertyRemarkAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isPropertyStatusOngoingWithoutRoof = new ObservableBoolean(false);

    public  Sheet sheet = null;

    public PropertyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        consumerNumber.setValue("");
        landmarkCategory.setValue("");
        landmarkSubCategory.setValue("");
    }


    public void setNewPropertyRelatedData(String newProperty) {
        if (newProperty.toUpperCase().contains(AppConstants.NA_NEAR) || newProperty.toUpperCase().contains(AppConstants.NR_NEAR)) {
            isNewPropertyRemarkAvailable.set(true);
            isCheckVisible.set(false);
        } else {
            isNewPropertyRemarkAvailable.set(false);
            isCheckVisible.set(true);
            remarks.setValue("");
        }
    }


    public void setToiletAvailable(boolean isToilet) {
        isToiletAvailable.set(isToilet);
        if (!isToilet) {
            toiletWasteDisposal.setValue("");
        }
    }


    public void setBuildingTypeAvailable(boolean buildingTypeAvailable) {
        isBuildingTypeAvailable.set(buildingTypeAvailable);
    }

    public void setBuildingSubTypeAvailable(boolean buildingSubTypeAvailable) {
        isBuildingSubTypeAvailable.set(buildingSubTypeAvailable);
    }

    public void isElectricityAvailable(boolean isElectricity) {
        isElectricityAvailable.set(isElectricity);
        if (!isElectricity) {
            consumerNumber.setValue("");
        }
    }

    public void setTeleCall(boolean isTeleCalling) {
        isTeleCall.set(isTeleCalling);
    }


    public void setBuildingUnderStateGov(boolean buildingUnderStateGov) {
        isBuildingUnderStateGov.set(buildingUnderStateGov);
    }

    public void setBuildingTypeEducational(boolean buildingTypeEducational) {
        isBuildingTypeEducational.set(buildingTypeEducational);
    }


    public LiveData<List<CommonItem>> getBuildingStatusData() {
        return buildingStatusData;
    }

    public LiveData<List<CommonItem>> getBuildingTypeData() {
        return buildingTypeData;
    }

    public MutableLiveData<List<CommonItem>> getBuildingSubTypeData() {
        return buildingSubTypeData;
    }

    public MutableLiveData<List<CommonItem>> getBuildingUnderData() {
        return buildingUnderData;
    }


    public MutableLiveData<List<CommonItem>> getDoorStatusData() {
        return doorStatusData;
    }

    public MutableLiveData<List<CommonItem>> getEstablishmentUsageList() {
        return establishmentUsageList;
    }


    /**
     * data updating via data binding
     */
    public void setBuildingSubTypeData(ArrayList<CommonItem> data) {
        buildingSubTypeData.setValue(data);
    }

    /**
     * data updating via data binding
     */
    public void setLandmarkSubCategory(ArrayList<CommonItem> data) {
        landmarkSubCategoryData.setValue(data);
    }

    /**
     * data updating via data binding
     */
    public void setEstablishmentUsageList(ArrayList<CommonItem> data) {
        establishmentUsageList.setValue(data);
    }


    public void setBuildStatusRelatedData(String buildingStatus) {
        isBuildStatusDemolishedUnusableOnGoing.set(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE));
        isBuildStatusDemolishedUnusable.set(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE));
        isBuildStatusOnGoing.set(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING));
        isPropertyStatusOngoingWithoutRoof.set(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF));

    }

    public void setBuildingUnderRelatedData(String buildingUnder) {
        setBuildingUnderStateGov(buildingUnder.equalsIgnoreCase(AppConstants.BUILDING_UNDER_STATE_GOV));
    }

    public void setBuildingTypeRelatedData(String buildingType) {
        setBuildingTypeEducational(buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_EDUCATIONAL));

    }
    public void setBuildingSubTypeRelatedBuilding(String buidingSubType){
        if(buidingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING)){
            isSubTypeRelatedBuilding.set(true);
            getNavigator().updateSubTypeRelatedBauilding();
        }else {
            isSubTypeRelatedBuilding.set(false);
        }
    }

    public void setLandmarkCategoryAvailable(boolean isLandmark) {
        isLandmarkCategory.set(isLandmark);
        if (!isLandmark) {
            landmarkCategory.setValue("");
            landmarkSubCategory.setValue("");
        }
    }


    void loadData() {
        landmarkCategoryData.setValue(IPMSApp.getAppInstance().getLocMainItem().getLandMarkCategory());
        ownershipEducationList.setValue(IPMSApp.getAppInstance().getLocMainItem().getOwnershipOfEduBuilding());
        toiletDisposalList.setValue(IPMSApp.getAppInstance().getLocMainItem().getToiletWasteDisposal());
        buildingStatusData.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingStatus());
        buildingTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingType());
        buildingSubTypeData.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingType());
        buildingUnderData.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingUnder());
        doorStatusData.setValue(IPMSApp.getAppInstance().getLocMainItem().getDoorStatus());
        remarksList.setValue(IPMSApp.getAppInstance().getLocMainItem().getNewPropertyIdRemark());
        establishmentUsageList.setValue(IPMSApp.getAppInstance().getLocMainItem().getBuildingType());
        surveyTypeList.setValue(IPMSApp.getAppInstance().getLocMainItem().getSurveyType());
        fetchExcelFromFolder();

    }

    /**
     * to get ward number
     *
     * @param wardNumber
     */
    @Override
    protected void onWardNumberFetchedFromDb(String wardNumber) {
        super.onWardNumberFetchedFromDb(wardNumber);
        newPropertyID2.setValue(wardNumber);
        nearPropertyID2.setValue(wardNumber);
    }

    /**
     * to get property details from db when back pressed and set in its fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        droneId.setValue(property.droneId);
        if (isPointStatusLandmark()) {
            landmarkName.setValue(property.landmarkName);
            landmarkCategory.setValue(property.landmarkCategory);
            landmarkSubCategory.setValue(property.landmarkSubCategory);
            for (CommonItem item : Objects.requireNonNull(landmarkCategoryData.getValue())) {
                if (item.getContent().equalsIgnoreCase(landmarkCategory.getValue())) {
                    landmarkSubCategoryData.setValue(item.getSubList());
                }
            }
        } else if (isPointStatusUnwanted()) {
            if (property.nearPropertyNumber.length() != 0) {
                int count = countChar(property.nearPropertyNumber, '/');
                nearPropertyID3.setValue(property.nearPropertyNumber.split(AppConstants.PROPERTY_ID_SEPERATOR)[1]);
                if (count == 2) {
                    nearPropertyID4.setValue(property.nearPropertyNumber.split(AppConstants.PROPERTY_ID_SEPERATOR)[2]);
                } else {
                    nearPropertyID4.setValue(AppConstants.NA_UPPERCASE);
                }
                if (property.nearPropertyNumber.contains(AppConstants.NA_NEAR)) {
                    nearPropertyID1.setValue(AppConstants.NA_NEAR);
                } else if (property.nearPropertyNumber.contains(AppConstants.NR_NEAR)) {
                    nearPropertyID1.setValue(AppConstants.NR_NEAR);
                }
            }
        } else if (isPointStatusBuilding()) {
            ar_owner_address.setValue(property.arOwnerAddress);
            ar_zone.setValue(property.arZone);
            ar_ac.setValue(property.arAC);
            ar_floor_area.setValue(property.arFloorArea);
            ar_building_usage.setValue(property.arBuildingUsage);

            if(property.arAC.length()!=0&&!property.arAC.equalsIgnoreCase(NR_UPPERCASE)&&!property.arAC.equalsIgnoreCase(NA_UPPERCASE)) {
                isARACVisible.set(true);
                ar_ac_hint.setValue(getBaseActivity().getString(R.string.building_ac_ar_hint)+property.arAC);
            }
            if(property.arBuildingUsage.length()!=0&&!property.arBuildingUsage.equalsIgnoreCase(NR_UPPERCASE)&&!property.arBuildingUsage.equalsIgnoreCase(NA_UPPERCASE)) {
                isARBuildingUsageVisible.set(true);
                ar_building_usage_hint.setValue(getBaseActivity().getString(R.string.building_usage_ar_hint)+property.arBuildingUsage);
            }


            ar_road_type.setValue(property.arRoadType);
            ar_road_name.setValue(property.arRoadName);
            ar_building_age.setValue(property.arBuildingAge);
            ar_roof_details.setValue(property.arRoofDetails);

            ar_floor_details.setValue(property.arFloorDetails);
            ar_modification.setValue(property.arModification);
            ar_occupier_details.setValue(property.arOccupierDetails);

            ar_tax_total.setValue(property.arTaxToatal);

            newPropARRemark.setValue(property.newpropidArRemarks);
            surveyType.setValue(property.surveyType);
            getNavigator().setCachedData(property);
            getNavigator().setSelectedData(property.buildingStatus, property.doorStatus, property.buildingSubType);
            setBuildingSubTypeRelatedBuilding(property.buildingSubType);
            establishmentUsage.setValue(property.establishmentUsage);
            setBuildStatusRelatedData(property.buildingStatus);
            setDoorStatusPDCGLDCNC(property.doorStatus);
            if (!isBuildStatusDemolishedUnusable.get()) {
                isTeleCall.set(property.surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));
                setBuildingUnderRelatedData(property.buildingUnder);
                setBuildingTypeRelatedData(property.buildingType);

                isElectricityAvailable.set(property.electricity.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
                if (isElectricityAvailable.get()) {
                    consumerNumber.setValue(property.consumerNumber);
                }

                isToiletAvailable.set(property.latrine.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
                if (isToiletAvailable.get()) {
                    toiletWasteDisposal.setValue(property.toiletWasteDisposal);
                }

                if (isBuildingTypeEducational.get() && isBuildingUnderStateGov.get()) {
                    ownershipEducational.setValue(property.ownershipEducation);
                }
            }
            if (!isBuildStatusDemolishedUnusableOnGoing.get()) {
                landmarkCategory.setValue(property.landmarkCategory);
                landmarkSubCategory.setValue(property.landmarkSubCategory);
                for (CommonItem item : Objects.requireNonNull(landmarkCategoryData.getValue())) {
                    if (item.getContent().equalsIgnoreCase(landmarkCategory.getValue())) {
                        landmarkSubCategoryData.setValue(item.getSubList());
                    }
                }
            }


            uID.setValue(property.uId);
            if (property.newPropertyId.length() != 0) {
                int count = countChar(property.newPropertyId, '/');
                newPropertyID3.setValue(property.newPropertyId.split(AppConstants.PROPERTY_ID_SEPERATOR)[1]);
                if (count == 2) {
                    newPropertyID4.setValue(property.newPropertyId.split(AppConstants.PROPERTY_ID_SEPERATOR)[2]);
                } else {
                    newPropertyID4.setValue(AppConstants.NA_UPPERCASE);
                }
                if (property.newPropertyId.contains(AppConstants.NA_NEAR)) {
                    newPropertyID1.setValue(AppConstants.NA_NEAR);
                } else if (property.newPropertyId.contains(AppConstants.NR_NEAR)) {
                    newPropertyID1.setValue(AppConstants.NR_NEAR);
                }
            }
            if (!isBuildStatusOnGoing.get()) {
                if (property.oldPropertyId.length() != 0) {
                    if (property.oldPropertyId.equals(AppConstants.NR_UPPERCASE)) {
                        oldPropertyID1.setValue(AppConstants.NR_UPPERCASE);
                        oldPropertyID2.setValue(AppConstants.NR_UPPERCASE);
                        oldPropertyID3.setValue(AppConstants.NR_UPPERCASE);
                    } else if (property.oldPropertyId.equals(AppConstants.NA_UPPERCASE)) {
                        oldPropertyID1.setValue(AppConstants.NA_UPPERCASE);
                        oldPropertyID2.setValue(AppConstants.NA_UPPERCASE);
                        oldPropertyID3.setValue(AppConstants.NA_UPPERCASE);
                    } else {
                        int count = countChar(property.oldPropertyId, '/');
                        oldPropertyID1.setValue(property.oldPropertyId.split(AppConstants.PROPERTY_ID_SEPERATOR)[0]);
                        oldPropertyID2.setValue(property.oldPropertyId.split(AppConstants.PROPERTY_ID_SEPERATOR)[1]);
                        if (count == 2) {
                            oldPropertyID3.setValue(property.oldPropertyId.split(AppConstants.PROPERTY_ID_SEPERATOR)[2]);
                        } else if (count == 1) {
                            oldPropertyID3.setValue(AppConstants.NA_UPPERCASE);
                        }

                    }

                }
            }

            setNewPropertyRelatedData(property.newPropertyId);
            if (isNewPropertyRemarkAvailable.get()) {
                remarks.setValue(property.newPropertyRemarks);
            }

            getNavigator().setCachedData(property);
        }


    }

    /**
     * to set door status :permanent door closed,door closed or gate locked
     *
     * @param doorStatus
     */
    public void setDoorStatusPDCGLDCNC(String doorStatus) {
        doorStatusPermanentDoorClosedGateLockedDoorClosed.set(doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL));
        doorStatusNC.set(doorStatus.equalsIgnoreCase(DOOR_STATUS_NC));

        getNavigator().updateLatrineBathroomACNROnDoorStatusChange();


    }

    /**
     * to save property details
     */
    public void saveProperty() {
        getNavigator().saveProperty();
    }

    /**
     * insert property details in db and navigate to completed survey
     */

    void insertPropertyDetails(String nearPropId1, String nearPropId2, String nearPropId3, String nearPropId4, String droneId, String oldPropId1, String oldPropId2, String oldPropId3, String newPropId1, String newPropId2, String newPropId3, String newPropId4, String uId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType) {
        String oldPropIdValue = "";
        String newPropIdValue = "";

        String nearPropIdValue = "";
        if (isPointStatusUnwanted()) {
            nearPropIdValue = (nearPropId1.length() != 0 ? nearPropId1 + " " : "") + nearPropId2 + AppConstants.PROPERTY_ID_SEPERATOR + nearPropId3;
            if (!nearPropId4.equalsIgnoreCase(AppConstants.NA_UPPERCASE)) {
                nearPropIdValue = nearPropIdValue + AppConstants.PROPERTY_ID_SEPERATOR + nearPropId4;
            }
        }
        if (isPointStatusBuilding()) {
            newPropIdValue = getNewPropertyId(newPropId1, newPropId2, newPropId3, newPropId4);
            if (!isBuildStatusOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get()) {
                oldPropIdValue = getOldPrpertyId(oldPropId1, oldPropId2, oldPropId3);
            }
//                if (oldPropIdValue.equalsIgnoreCase(AppConstants.NR_UPPERCASE) || oldPropIdValue.equalsIgnoreCase(AppConstants.NA_UPPERCASE)) {
//                    checkNewIdDuplication(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder,
//                            buildingStatus, buildingType, buildingSubType, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue,
//                            buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner);
//
//                } else {
//                    checkOldIdDuplication(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder,
//                            buildingStatus, buildingType, buildingSubType, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue,
//                            buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner);
//                }
//
//
//            } else {
                checkNewIdDuplication(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder,
                        buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue,
                        buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);


//            }

        } else {
            insertIntoDb(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);
        }


    }

    public String getOldPrpertyId(String oldPropId1, String oldPropId2, String oldPropId3) {
        String oldPropIdValue = oldPropId1 + AppConstants.PROPERTY_ID_SEPERATOR + oldPropId2;
        if (!oldPropId3.equalsIgnoreCase(AppConstants.NA_UPPERCASE) && oldPropId3.length() != 0) {
            oldPropIdValue = oldPropIdValue + AppConstants.PROPERTY_ID_SEPERATOR + oldPropId3;
        }
        if (oldPropId1.equals(AppConstants.NR_UPPERCASE) && oldPropId2.equals(AppConstants.NR_UPPERCASE)) {
            oldPropIdValue = AppConstants.NR_UPPERCASE;
        } else if (oldPropId1.equals(AppConstants.NA_UPPERCASE) && oldPropId2.equals(AppConstants.NA_UPPERCASE)) {
            oldPropIdValue = AppConstants.NA_UPPERCASE;
        }
        return oldPropIdValue;
    }

    public String getNewPropertyId(String newPropId1, String newPropId2, String newPropId3, String newPropId4) {
        String newPropIdValue = (newPropId1.length() != 0 ? newPropId1 + " " : "") + newPropId2 + AppConstants.PROPERTY_ID_SEPERATOR + newPropId3;
        if (newPropId4.length() != 0 && !newPropId4.equalsIgnoreCase(AppConstants.NA_UPPERCASE)) {
            newPropIdValue = newPropIdValue + AppConstants.PROPERTY_ID_SEPERATOR + newPropId4;
        }
        return newPropIdValue;
    }

    public void checkNewIdDuplication(String droneId, String oldPropIdValue, String newPropIdValue, String uId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropIdValue, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType) {
//       New property id duplication not needed if id contain NR_NEAR or NA_NEAR

        if (newPropIdValue.contains(AppConstants.NR_NEAR) || newPropIdValue.contains(AppConstants.NA_NEAR)) {
            insertIntoDb(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);
        } else {
            getCompositeDisposable().add(getDataManager()
                    .getDuplicateNewPropId(getDataManager().getCurrentPropertyId(), newPropIdValue)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(count -> {
                        if (count != null) {
                            if (count == 0) {
                                //No duplication found in db entries so now check with server data which is stored in shared preference
                                checkNewPropIdwithServerData(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);

                            } else {
                                getBaseActivity().showToast(getBaseActivity().getString(R.string.error_duplicate_new_propertyId));
                            }
                        }
                    }).subscribe());
        }
    }

//    public void checkOldIdDuplication(String droneId, String oldPropIdValue, String newPropIdValue, String uId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropIdValue, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner) {
//        getCompositeDisposable().add(getDataManager()
//                .getDuplicateOldPropId(getDataManager().getCurrentPropertyId(), oldPropIdValue)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .doOnNext(count -> {
//                    if (count != null) {
//                        if (count == 0) {
//                            checkNewIdDuplication(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType,
//                                    landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner);
//                        } else {
//                            getBaseActivity().showToast(getBaseActivity().getString(R.string.error_duplicate_old_propertyId));
//                        }
//                    }
//                }).subscribe());
//    }

    public void insertIntoDb(String droneId, String oldPropIdValue, String newPropIdValue, String uId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropIdValue, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType) {
        String newARPropRemarkValue = "",ar_owner_add="";
        if (newPropARRemark.getValue() != null) {
            newARPropRemarkValue = newPropARRemark.getValue();
        }
        if(ar_owner_address.getValue()!=null){
            ar_owner_add=ar_owner_address.getValue();
        }
        String arZonedata=ar_zone.getValue()!=null?ar_zone.getValue():"";
        String arACData=ar_zone.getValue()!=null?ar_ac.getValue():"";
        String arFloorAreaData=ar_zone.getValue()!=null?ar_floor_area.getValue():"";
        String arBuildingUsageData=ar_zone.getValue()!=null?ar_building_usage.getValue():"";
        String arRoadTypeData=ar_zone.getValue()!=null?ar_road_type.getValue():"";
        String arRoadNameData=ar_zone.getValue()!=null?ar_road_name.getValue():"";
        String  arBuildingAgeData=ar_zone.getValue()!=null?ar_building_age.getValue():"";
        String arRoofDetailsData=ar_zone.getValue()!=null?ar_roof_details.getValue():"";
        String arFloorDetailsData=ar_zone.getValue()!=null?ar_floor_details.getValue():"";
        String arModificationData=ar_zone.getValue()!=null?ar_modification.getValue():"";
        String arOccupierDetailsData=ar_zone.getValue()!=null?ar_occupier_details.getValue():"";
        String arTaxTotal=ar_zone.getValue()!=null?ar_tax_total.getValue():"";


        getCompositeDisposable().add(getDataManager()
                .insertPropertyDetails(droneId, oldPropIdValue, uId, newPropIdValue, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType, newARPropRemarkValue, "",ar_owner_add,arZonedata,arACData,arFloorAreaData,arBuildingUsageData,arRoadTypeData,arRoadNameData,arBuildingAgeData,arRoofDetailsData,arFloorDetailsData,arModificationData,arOccupierDetailsData,arTaxTotal, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getNavigator().setSelectedData(buildingStatus, doorStatus, buildingSubType);
//                    if (isPointStatusLandmark()) {
//                        getNavigator().navigateToImageDetails();
//                    } else {
                        getNavigator().navigateToNextPage();
//                    }
                })
                .subscribe());
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String nearPropId3, String nearPropId4, String droneId, String oldPropId1, String oldPropId2, String oldPropId3, String newPropId3, String newPropId4, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType) {
        getNavigator().clearValidationErrors();

        if (isPointStatusUnwanted()) {
            if (TextUtils.isEmpty(droneId)) {
                getNavigator().validationError(PropertyFragment.DRONE_ID_ERROR, getBaseActivity().getString(R.string.error_done_id));
                return false;
            } else if (TextUtils.isEmpty(nearPropId3)) {
                getNavigator().validationError(PropertyFragment.NEAR_PROPERTY_ID3_ERROR, getBaseActivity().getString(R.string.error_near_property_id3));
                return false;
            } else if (TextUtils.isEmpty(nearPropId4)) {
                getNavigator().validationError(PropertyFragment.NEAR_PROPERTY_ID4_ERROR, getBaseActivity().getString(R.string.error_near_property_id4));
                return false;
            }
//            else if (TextUtils.isEmpty(nearPropertyNumber)) {
//                getNavigator().validationError(PropertyFragment.NEAR_PROPERTY_NUMBER_ERROR, getBaseActivity().getString(R.string.error_near_property_number));
//                return false;
//            }
            return true;
        } else if (isPointStatusLandmark()) {
            if (TextUtils.isEmpty(landmarkName)) {
                getNavigator().validationError(PropertyFragment.LANDMARK_NAME_ERROR, getBaseActivity().getString(R.string.error_landmark_name));
                return false;
            } else if (TextUtils.isEmpty(landmarkCategory)) {
                getNavigator().validationError(PropertyFragment.LANDMARK_CATEGORY_ERROR, getBaseActivity().getString(R.string.error_landmark_category));
                return false;
            } else if (TextUtils.isEmpty(landmarkSubCategory)) {
                getNavigator().validationError(PropertyFragment.LANDMARK_SUB_CATEGORY_ERROR, getBaseActivity().getString(R.string.error_landmark_sub_category));
                return false;
            } else if (TextUtils.isEmpty(droneId)) {
                getNavigator().validationError(PropertyFragment.DRONE_ID_ERROR, getBaseActivity().getString(R.string.error_done_id));
                return false;
            }
            return true;
        } else if (isPointStatusBuilding()) {
            if (TextUtils.isEmpty(surveyType)) {
                getNavigator().validationError(PropertyFragment.SURVEY_TYPE_ERROR, getBaseActivity().getString(R.string.error_survey_type));
                return false;
            }else if (TextUtils.isEmpty(buildingStatus)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_building_status));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(doorStatus)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_door_status));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(buildingUnder)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_building_under));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(buildingType)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_building_type));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(buildingSubType)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_building_subtype));
                return false;
            }else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(establishmentUsage)) {
                getNavigator().validationError(PropertyFragment.ESTABLISHMENT_USAGE_ERROR, getBaseActivity().getString(R.string.error_establishment_usage));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(buildingUsage)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_building_usage));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && isBuildingTypeEducational.get() && TextUtils.isEmpty(mainBuilding)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_main_building));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !isTeleCall.get() && TextUtils.isEmpty(electricity)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_electricity));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && isElectricityAvailable.get()  && !isTeleCall.get() &&  TextUtils.isEmpty(consumerNumber)) {
                getNavigator().validationError(PropertyFragment.ELECTRICITY_CONSUMER_ERROR, getBaseActivity().getString(R.string.error_enter_consumer_number));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && isElectricityAvailable.get() && consumerNumber.length() != 13 && !consumerNumber.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                getNavigator().validationError(PropertyFragment.ELECTRICITY_CONSUMER_ERROR, getBaseActivity().getString(R.string.error_consumer_number));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !isSubTypeRelatedBuilding.get() &&TextUtils.isEmpty(bathroom)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_bathroom));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !isSubTypeRelatedBuilding.get() &&TextUtils.isEmpty(latrine)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_latrine));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && isToiletAvailable.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isDigitsOnly(toiletWasteDisposal)) {
                getNavigator().validationError(PropertyFragment.TOILET_WASTE_DISPOSAL_ERROR, getBaseActivity().getString(R.string.error_toilet_waste_disposal));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !doorStatusPermanentDoorClosedGateLockedDoorClosed.get() && !isSubTypeRelatedBuilding.get() && !isPropertyStatusOngoingWithoutRoof.get() && TextUtils.isEmpty(airConditioner)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_centralised_ac));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && !isPropertyStatusOngoingWithoutRoof.get() && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(landmark)) {
                getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_landmark));
                return false;
            } else if (!isBuildStatusDemolishedUnusableOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get() && landmark.equalsIgnoreCase("yes") && !isSubTypeRelatedBuilding.get() && TextUtils.isEmpty(landmarkCategory)) {
                getNavigator().validationError(PropertyFragment.LANDMARK_CATEGORY_ERROR, getBaseActivity().getString(R.string.error_landmark_category));
                return false;
            } else if (!isBuildStatusDemolishedUnusableOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get() && landmark.equalsIgnoreCase("yes") && !isSubTypeRelatedBuilding.get() &&TextUtils.isEmpty(landmarkSubCategory)) {
                getNavigator().validationError(PropertyFragment.LANDMARK_SUB_CATEGORY_ERROR, getBaseActivity().getString(R.string.error_landmark_sub_category));
                return false;
            } else if (!isBuildStatusDemolishedUnusable.get() && isBuildingUnderStateGov.get() && isBuildingTypeEducational.get() && !ownershipEducation.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownershipEducation)) {
                getNavigator().validationError(PropertyFragment.OWNERSHIP_EDUCATIONAL_ERROR, getBaseActivity().getString(R.string.error_ownership_education));
                return false;
            } else if (TextUtils.isEmpty(droneId)) {
                getNavigator().validationError(PropertyFragment.DRONE_ID_ERROR, getBaseActivity().getString(R.string.error_done_id));
                return false;
            } else if ((TextUtils.isEmpty(newPropId3))) {
                getNavigator().validationError(PropertyFragment.NEW_PROPERTY_ID3_ERROR, getBaseActivity().getString(R.string.error_property_new_id3));
                return false;
            } else if ((TextUtils.isEmpty(newPropId4))) {
                getNavigator().validationError(PropertyFragment.NEW_PROPERTY_ID4_ERROR, getBaseActivity().getString(R.string.error_property_new_id4));
                return false;
            } else if (!isBuildStatusOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get() && (TextUtils.isEmpty(oldPropId1))) {
                getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID1_ERROR, getBaseActivity().getString(R.string.error_property_old_id1));
                return false;
            } else if (!isBuildStatusOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get() && (TextUtils.isEmpty(oldPropId2))) {
                getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID2_ERROR, getBaseActivity().getString(R.string.error_property_old_id2));
                return false;
            } else if (!isBuildStatusOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get() && (TextUtils.isEmpty(oldPropId3))) {
                getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID3_ERROR, getBaseActivity().getString(R.string.error_property_old_id3));
                return false;
            } else if (isNewPropertyRemarkAvailable.get() && TextUtils.isEmpty(newPropertyRemarks)) {
                getNavigator().validationError(PropertyFragment.NEW_PROPERTY_ID_REMARKS_ERROR, getBaseActivity().getString(R.string.error_property_new_id_remarks));
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check new property id duplication with server data
     * @param droneId
     * @param oldPropIdValue
     * @param newPropIdValue
     * @param uId
     * @param newPropertyRemarks
     * @param landmark
     * @param doorStatus
     * @param buildingUnder
     * @param buildingStatus
     * @param buildingType
     * @param buildingSubType
     * @param landmarkName
     * @param landmarkCategory
     * @param landmarkSubCategory
     * @param nearPropIdValue
     * @param buildingUsage
     * @param mainBuilding
     * @param ownershipEducation
     * @param electricity
     * @param consumerNumber
     * @param bathroom
     * @param latrine
     * @param toiletWasteDisposal
     * @param airConditioner
     */
    public void checkNewPropIdwithServerData(String droneId, String oldPropIdValue, String newPropIdValue, String uId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropIdValue, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType) {
        if(isSurveyOpenEditMode()) {
            boolean isfound = false;
            if (getSurveyPoints() != null) {
                SurvryPointsResponse survryPointsResponse = getSurveyPoints();
                if (survryPointsResponse != null && survryPointsResponse.getSurveyPointsList() != null) {
                    for (int i = 0; i < survryPointsResponse.getSurveyPointsList().size(); i++) {
                        String newPropId = survryPointsResponse.getSurveyPointsList().get(i).newproid;
                        if (newPropIdValue.equalsIgnoreCase(newPropId)) {
                            isfound = true;
                            break;
                        }
                    }
                    if (isfound) {
                        getNavigator().showWarning();
                    } else {
                        insertIntoDb(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);
                    }
                }
            } else {
                getBaseActivity().showToast(R.string.no_data);
            }
        }else{
            insertIntoDb(droneId, oldPropIdValue, newPropIdValue, uId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropIdValue, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType);
        }

    }
    public String getWarningMessage(boolean noElectricity, boolean noBathroom, boolean noLatrine, boolean isElectricityVsACWarning, boolean isPropertyIdWarning) {
        String warning = "";
        if (noElectricity) {
            warning = getBaseActivity().getString(R.string.property_electricity_warning);
        }
        if (noBathroom) {
            if (warning.length() == 0)
                warning = getBaseActivity().getString(R.string.property_bathroom_warning);
            else
                warning = warning + "\n\n" + getBaseActivity().getString(R.string.property_bathroom_warning);
        }
        if (noLatrine) {
            if (warning.length() == 0)
                warning = getBaseActivity().getString(R.string.property_latrine_warning);
            else
                warning = warning + "\n\n" + getBaseActivity().getString(R.string.property_latrine_warning);
        }
        if (isElectricityVsACWarning) {
            if (warning.length() == 0)
                warning = getBaseActivity().getString(R.string.property_ac_warning);
            else
                warning = warning + "\n\n" + getBaseActivity().getString(R.string.property_ac_warning);
        }
        if (isPropertyIdWarning) {
            if (warning.length() == 0)
                warning = getBaseActivity().getString(R.string.property_id_warning);
            else
                warning = warning + "\n\n" + getBaseActivity().getString(R.string.property_id_warning);
        }
        return warning;
    }

    /**
     * clear building status related data when switch to other building status
     *
     * @param buildingStatus
     */
    public void clearBuildingStatusRelatedData(String buildingStatus) {
        getCompositeDisposable().add(getDataManager()
                .clearBuildingStatusRelatedData(buildingStatus, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> getDataManager().clearSurveyCompletedStatus(getDataManager().getCurrentSurveyID()))
                .subscribe());
    }

    /**
     * clear door status related data when switch to other door status
     *
     * @param doorStatus
     */
    public void clearDoorStatusRelatedData(String doorStatus) {
        getCompositeDisposable().add(getDataManager()
                .clearDoorStatusRelatedData(doorStatus, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> getDataManager().clearSurveyCompletedStatus(getDataManager().getCurrentSurveyID()))
                .subscribe());
    }

    /**
     * clear building sub type related data when switch to other building sub type
     *
     * @param buildingSubType
     */
    public void clearBuildingSubTypeRelatedData(String buildingSubType) {
        getCompositeDisposable().add(getDataManager()
                .clearBuildingSubTypeRelatedData(buildingSubType, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<?>>) aBoolean -> getDataManager().clearSurveyCompletedStatus(getDataManager().getCurrentSurveyID()))
                .subscribe());
    }


    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.txtBuildingStatus:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_property_status),InfoVideoNames.PROPERTY_DETAILS_PROPERTY_STATUS_INFO_VIDEO);
                break;
            case R.id.txtDoorStatus:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_door_status),InfoVideoNames.PROPERTY_DETAILS_DOOR_STATUS_INFO_VIDEO);
                break;
            case R.id.txtBuildingUnder:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_property_under),InfoVideoNames.PROPERTY_DETAILS_PROPERTY_UNDER_INFO_VIDEO);
                break;
            case R.id.txtBuildingType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_property_type),InfoVideoNames.PROPERTY_DETAILS_PROPERTY_TYPE_INFO_VIDEO);
                break;
            case R.id.txtBuildingSubType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_property_subtype),InfoVideoNames.PROPERTY_DETAILS_PROPERTY_SUB_TYPE_INFO_VIDEO);
                break;
            case R.id.tvBuildingUsage:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_property_usage),InfoVideoNames.PROPERTY_DETAILS_PROPERTY_USAGE_INFO_VIDEO);
                break;
            case R.id.tvElectricity:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_electricity),InfoVideoNames.PROPERTY_DETAILS_ELECTRICITY_INFO_VIDEO);
                break;
            case R.id.btnConsumerNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_consumer_no),InfoVideoNames.PROPERTY_DETAILS_CONSUMER_NUMBER_INFO_VIDEO);
                break;
            case R.id.tvLatrine:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_latrine),InfoVideoNames.PROPERTY_DETAILS_TOILET_INFO_VIDEO);
                break;
            case R.id.btnToiletWasteDisposalInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_toilet_waste_disposal),InfoVideoNames.PROPERTY_DETAILS_TOILET_WASTE_DISPOSAL_INFO_VIDEO);
                break;
            case R.id.tvAirConditioner:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_centralised_ac),InfoVideoNames.PROPERTY_DETAILS_AIR_CONDITIONER_INFO_VIDEO);
                break;
            case R.id.tvMainBuilding:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_main_building),InfoVideoNames.PROPERTY_DETAILS_MAIN_BUILDING_INFO_VIDEO);
                break;
            case R.id.txtNewPropertyId:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_new_property_Id),InfoVideoNames.PROPERTY_DETAILS_NEW_PROPERTY_ID_INFO_VIDEO);
                break;
            case R.id.btnNewPropertyIdRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_new_property_id_remarks),InfoVideoNames.PROPERTY_DETAILS_NEW_PROPERTY_ID_REMARKS_INFO_VIDEO);
                break;
            case R.id.btnOwnershipEduInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_ownership_educational),InfoVideoNames.PROPERTY_DETAILS_EDUCATIONAL_BUILDING_OWNERSHIP_INFO_VIDEO);
                break;
            case R.id.btnDroneIdInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_drone_id),InfoVideoNames.PROPERTY_DETAILS_DRONE_ID_INFO_VIDEO);
                break;
            case R.id.tvBathroom:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_bathroom),InfoVideoNames.PROPERTY_DETAILS_BATHROOM_INFO_VIDEO);
                break;
            case R.id.btnSurveyTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_survey_type),InfoVideoNames.PROPERTY_DETAILS_SURVEY_TYPE_INFO_VIDEO);
                break;
        }
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrOwnershipEducational:
                ownershipEducational.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.btnNearNewProperty:
                getNavigator().showNearPopUp();
                break;
            case R.id.btnNaNewProprty4:
                newPropertyID4.setValue(AppConstants.NA_UPPERCASE);
                break;
            case R.id.btnNaOldProprty3:
                if (isOldPropIdEnabled.get())
                    oldPropertyID3.setValue(AppConstants.NA_UPPERCASE);
                break;
            case R.id.nrOldPropertyIdValue:
                if (isOldPropIdEnabled.get())
                    getNavigator().showNaNrMenu();
                break;
            case R.id.nrElectricityConsumerNumber:
                consumerNumber.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.btnNearNearPropertyNo:
                getNavigator().showNearPopUpForNearProperty();
                break;
            case R.id.btnNaNearProprty4:
                nearPropertyID4.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }

    public void setOldPropIdValue(String value) {
        oldPropertyID1.setValue(value);
        oldPropertyID2.setValue(value);
        oldPropertyID3.setValue(value);
    }

    public void updateOldPropIdUIDOnNewPropIdChange() {
        clearARData();
        if (!isBuildStatusOnGoing.get() && !isPropertyStatusOngoingWithoutRoof.get()) {
            oldPropertyID1.setValue("");
            oldPropertyID3.setValue("");
            oldPropertyID2.setValue("");
        }

    }
    public void clearARData(){
        uID.setValue("");
        ar_zone.setValue("");
        ar_ac.setValue("");
        ar_ac_hint.setValue("");
        ar_floor_area.setValue("");
        ar_building_usage.setValue("");
        ar_building_usage_hint.setValue("");
        ar_road_type.setValue("");
        ar_road_name.setValue("");
        ar_building_age.setValue("");
        ar_roof_details.setValue("");
        ar_modification.setValue("");
        ar_occupier_details.setValue("");
        ar_tax_total.setValue("");
    }
    public void onCheckClick(View v) {
        getNavigator().preventDoubleClick();
        if (getARFileLoc() == null) {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.error_ar_file_empty));
        } else {
            switch (v.getId()) {
                case R.id.textviewOldPropertyCheck:
                    getNavigator().checkOldPropertyId();
                    break;
                case R.id.textviewNewPropertyCheck:
                    getNavigator().checkNewPropertyId();
                    break;

            }
        }

    }
    public void onNearClick(View v) {
        switch (v.getId()) {
            case R.id.nearDroneId:
                droneId.setValue(AppConstants.NEAR_PREFIX);
                break;
        }
    }

    public void fetchExcelFromFolder() {
        if(getARFileLoc()!=null){
            File xlsFile = null;
            xlsFile = new File(getARFileLoc());
            if (xlsFile != null) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                Workbook workbook;

                try {
                    workbook = Workbook.getWorkbook(xlsFile);
                    sheet = workbook.getSheet(0);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BiffException e) {
                    e.printStackTrace();
                }


            }
        }


    }

    public void getExcelData( String wardno, String proprtyNo, String subNo, String purpose) {
        if(sheet!=null){
            ArrayList<ExcelDataItem> excelDataItemArrayList = new ArrayList<ExcelDataItem>();
            if (subNo.equalsIgnoreCase(NA_UPPERCASE) || subNo.equalsIgnoreCase(AppConstants.NR_UPPERCASE)) {
                subNo = "";
            }

            boolean isFoundProperty = false;

            for (int i = 0; i < sheet.getRows(); i++) {
                Cell[] row = sheet.getRow(i);
                if (purpose.equalsIgnoreCase(NEW_PROP_ID_VERIFICATION)) {

                    if (subNo.equalsIgnoreCase(row[AR_FILE_COL_NEW_SUB_NO].getContents()) && proprtyNo.equalsIgnoreCase(row[AR_FILE_COL_NEW_PROPERTY_NO].getContents()) && wardno.equalsIgnoreCase(row[AR_FILE_COL_NEW_WARD].getContents().split("\\(")[0])) {
                        isFoundProperty = true;
                        ExcelDataItem item = new ExcelDataItem(row[AR_FILE_COL_UID].getContents(), row[AR_FILE_COL_OWNER_ADDRESS_ENG].getContents(), row[AR_FILE_COL_NEW_WARD].getContents().split("\\(")[0], row[AR_FILE_COL_NEW_PROPERTY_NO].getContents(), row[AR_FILE_COL_NEW_SUB_NO].getContents(), row[AR_FILE_COL_OLD_WARD].getContents().split("\\(")[0], row[AR_FILE_COL_OLD_PROPERTY_NO].getContents(), row[AR_FILE_COL_OLD_SUB_NO].getContents(),row[AR_FILE_COL_ZONE].getContents(),row[AR_FILE_COL_AC].getContents(),row[AR_FILE_COL_FLOOR_AREA].getContents(),row[AR_FILE_COL_BUILDING_USEAGE].getContents(),row[AR_FILE_COL_ROAD_TYPE].getContents(),row[AR_FILE_COL_ROAD_NAME].getContents(),row[AR_FILE_COL_BUILDING_AGE].getContents(),row[AR_FILE_COL_ROOF_DETAILS].getContents(),row[AR_FILE_COL_FLOOR_DETAILS].getContents(),row[AR_FILE_COL_MODIFICATION].getContents(),row[AR_FILE_COL_OCCUPIER_DETAILS].getContents(),row[AR_FILE_COL_AR_TAX_TOTAL].getContents());
                        excelDataItemArrayList.add(item);
                    }

                } else {
                    //Old prop verification
                    if (subNo.equalsIgnoreCase(row[AR_FILE_COL_OLD_SUB_NO].getContents()) && proprtyNo.equalsIgnoreCase(row[AR_FILE_COL_OLD_PROPERTY_NO].getContents()) && wardno.equalsIgnoreCase(row[AR_FILE_COL_OLD_WARD].getContents().split("\\(")[0])) {
                        isFoundProperty = true;
                        ExcelDataItem item = new ExcelDataItem(row[AR_FILE_COL_UID].getContents(), row[AR_FILE_COL_OWNER_ADDRESS_ENG].getContents(), row[AR_FILE_COL_NEW_WARD].getContents().split("\\(")[0], row[AR_FILE_COL_NEW_PROPERTY_NO].getContents(), row[AR_FILE_COL_NEW_SUB_NO].getContents(), row[AR_FILE_COL_OLD_WARD].getContents().split("\\(")[0], row[AR_FILE_COL_OLD_PROPERTY_NO].getContents(), row[AR_FILE_COL_OLD_SUB_NO].getContents(),row[AR_FILE_COL_ZONE].getContents(),row[AR_FILE_COL_AC].getContents(),row[AR_FILE_COL_FLOOR_AREA].getContents(),row[AR_FILE_COL_BUILDING_USEAGE].getContents(),row[AR_FILE_COL_ROAD_TYPE].getContents(),row[AR_FILE_COL_ROAD_NAME].getContents(),row[AR_FILE_COL_BUILDING_AGE].getContents(),row[AR_FILE_COL_ROOF_DETAILS].getContents(),row[AR_FILE_COL_FLOOR_DETAILS].getContents(),row[AR_FILE_COL_MODIFICATION].getContents(),row[AR_FILE_COL_OCCUPIER_DETAILS].getContents(),row[AR_FILE_COL_AR_TAX_TOTAL].getContents());
                        excelDataItemArrayList.add(item);
                    }

                }

            }
            if(isFoundProperty){
                int sameOwnerPropertyCount = 1;
                String ownerDetails = excelDataItemArrayList.get(0).getOwnerAddressEng();
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell[] row = sheet.getRow(i);
                    if(ownerDetails.equalsIgnoreCase(row[AR_FILE_COL_OWNER_ADDRESS_ENG].getContents())){
                        sameOwnerPropertyCount += 1;

                    }
                }

            }

            if (!isFoundProperty) {
                getNavigator().showNotFoundPropertyId(getBaseActivity().getString(R.string.proprty_not_found), getNewPropertyId("", wardno, proprtyNo, subNo));
                uID.setValue("");
                oldPropertyID1.setValue("");
                oldPropertyID2.setValue("");
                oldPropertyID3.setValue("");

            } else {
                if (purpose.equalsIgnoreCase(NEW_PROP_ID_VERIFICATION))
                    getNavigator().showARDetailsNewPropertyId(excelDataItemArrayList, getNewPropertyId("", wardno, proprtyNo, subNo));
                else
                    getNavigator().showARDetailsOldPropertyId(excelDataItemArrayList);
            }
        }
    }

    public boolean validateOldPropertyForDialog(String wardno, String propertyNo, String subNo) {
        if ((TextUtils.isEmpty(wardno))) {
            getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_property_old_id1));
            return false;
        } else if ((TextUtils.isEmpty(propertyNo))) {
            getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_property_old_id2));
            return false;
        } else if ((TextUtils.isEmpty(subNo))) {
            getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_property_old_id3));
            return false;
        }
        return true;
    }

    public boolean validateOldPropertyForCheck(String wardno, String propertyNo, String subNo) {
        if ((TextUtils.isEmpty(wardno))) {
            getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID1_ERROR, getBaseActivity().getString(R.string.error_property_old_id1));
            return false;
        } else if ((TextUtils.isEmpty(propertyNo))) {
            getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID2_ERROR, getBaseActivity().getString(R.string.error_property_old_id2));
            return false;
        } else if ((TextUtils.isEmpty(subNo))) {
            getNavigator().validationError(PropertyFragment.OLD_PROPERTY_ID3_ERROR, getBaseActivity().getString(R.string.error_property_old_id3));
            return false;
        } else if (wardno.equalsIgnoreCase(NR_UPPERCASE) || wardno.equalsIgnoreCase(NA_UPPERCASE) || propertyNo.equalsIgnoreCase(NR_UPPERCASE) || propertyNo.equalsIgnoreCase(NA_UPPERCASE)) {
            getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_old_prop_na_nr));
            return false;
        }
        return true;
    }

    public boolean validateNewPropertyForCheck(String propertyNo, String subNo) {
        if ((TextUtils.isEmpty(propertyNo))) {
            getNavigator().validationError(PropertyFragment.NEW_PROPERTY_ID3_ERROR, getBaseActivity().getString(R.string.error_property_new_id3));
            return false;
        } else if ((TextUtils.isEmpty(subNo))) {
            getNavigator().validationError(PropertyFragment.NEW_PROPERTY_ID4_ERROR, getBaseActivity().getString(R.string.error_property_new_id4));
            return false;
        }
        return true;
    }

    public boolean validateOwnerSelection(String selectedAddress) {
        if ((TextUtils.isEmpty(selectedAddress))) {
            getNavigator().validationError(PropertyFragment.COMMON_ERROR, getBaseActivity().getString(R.string.property_owner_selection_validation_error));
            return false;
        }
        return true;
    }

}
