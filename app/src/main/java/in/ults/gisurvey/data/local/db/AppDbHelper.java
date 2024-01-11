package in.ults.gisurvey.data.local.db;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.utils.AppConstants;
import io.reactivex.Observable;

import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF;
import static in.ults.gisurvey.utils.AppConstants.DOOR_STATUS_NC;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    /**
     * to insert qfield id to db
     *
     * @param survey
     * @return
     */
    @Override
    public Observable<Boolean> insertQFieldID(Survey survey) {
        return Observable.fromCallable(() -> {
            if (mAppDatabase.surveyDao().getSurveyById(survey.getSurveyID()) == null) {
                mAppDatabase.surveyDao().insert(survey);
            }
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteSurvey(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().deleteSurvey(surveyID);
            return true;
        });
    }
    @Override
    public Observable<Boolean> deleteProperty(String surveyId,String propertyId) {

        return Observable.fromCallable(() -> {
           if (mAppDatabase.propertyDao().getPropertyById(surveyId, propertyId) != null) {
                mAppDatabase.propertyDao().deleteProperty(propertyId);

                 }
            return true;
        });
    }
    /**
     * to check survey existis in db
     *
     * @param survey
     * @return
     */
    @Override
    public Observable<Boolean> isSurveyExists(Survey survey) {
        return Observable.fromCallable(() ->
                mAppDatabase.surveyDao().getSurveyById(survey.getSurveyID()) != null
        );
    }

    /**
     * to insert point status to db
     *
     * @param pointStatus
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> insertPointStatus(String pointStatus, String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().insertPointStatus(pointStatus, surveyID);
            return true;
        });
    }

    /**
     * to fetch survey from db
     *
     * @return
     */
    @Override
    public Observable<List<Survey>> loadSurvey() {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().loadAllSurveys());
    }

    /**
     * to insert floor and property count to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertFloorPropertyCount(int floorCount, int propertyCount, String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().insertFloorPropertyCount(floorCount, propertyCount, surveyID);
            return true;
        });
    }

    /**
     * to insert ground floor to db
     *
     * @param groundFloor
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> insertGroundFloor(int groundFloor, String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().insertGroundFloor(groundFloor, surveyID);
            return true;
        });
    }

    /**
     * to insert basement area to db
     *
     * @param basementArea
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> insertBasementArea(ArrayList<BasementAreaItem> basementArea, String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().insertBasementArea(basementArea, surveyID);
            return true;
        });
    }

    /**
     * to fetch survey details from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Survey> getSurveyById(String surveyID) {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().getSurveyById(surveyID));
    }

    /**
     * to insert location details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertLocationDetails(String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice, String pinCode, String buildingZone,String isFloodAffected,String waterLevelHit, String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().insertLocationDetails(district, localBody, wardNumber, wardName, streetName, placeName, villageName, postOffice, pinCode, buildingZone, isFloodAffected, waterLevelHit, surveyID);
            return true;
        });
    }

    /**
     * to insert owner details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertOwnerDetails(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertOwnerDetails(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber, isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert road details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertRoadDetails(String nearRoad, String roadType, String roadWidth,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertRoadDetails(nearRoad, roadType, roadWidth,isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert establishment details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertEstablishmentDetails(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertEstablishmentDetails(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile,isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert tenant details to db
     */
    @Override
    public Observable<Boolean> insertTenantDetails(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertTenantDetails(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus,isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert tax details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertTaxDetails(String taxNumber, String taxAmount, String taxYear, String taxDate, String taxAnnualAmount,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertTaxDetails(taxNumber, taxAmount, taxYear, taxDate, taxAnnualAmount,isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert to live hood details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertLiveHoodDetails(String religion, String religionCast, String rationCard, String rationCardNumber, String kwaWater, String waterConnectionType, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> waterSourceType, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry,ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource,String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, kwaWater, waterConnectionType, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, waterSourceType, plasticWasteManagementType, liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pet, cattles, noOfCattles, paultry, noOfPoultry,wellDetails,waterConnection,otherSource,wellPerennial,wellPerennialMonth, memCount, swimmingPool, swimmingPoolArea, isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertMoreDetails(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails, String thozhilurapp, String kudumbasree, String healthInsurance,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance,isValidationOk, surveyID, propertyID);
            return true;
        });
    }


    /**
     * to insert member details to db
     *
     * @param memberDetailsItems
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> insertMemberDetails(String noOfMembers, ArrayList<MemberDetailsItem> memberDetailsItems,boolean isValidationOk, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertMemberDetails(noOfMembers, memberDetailsItems,isValidationOk, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert property details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertPropertyDetails(String droneId,String oldPropertyId, String uId, String newPropertyId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropertyNumber, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber,String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType, String newPropARRemark,String oldPropARRemark,String arOwnerAddress,String arZone,String arAC,String arFloorArea,String arBuildingUsage,String arRoadType,String arRoadName,String arBuildingAge,String arRoofDetails,String arFloorDetails,String arModification,String arOccupierDetails,String arTaxTotal, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertPropertyDetails(droneId,oldPropertyId, uId, newPropertyId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropertyNumber, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber,bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType, newPropARRemark,oldPropARRemark,arOwnerAddress,arZone,arAC,arFloorArea,arBuildingUsage,arRoadType,arRoadName,arBuildingAge,arRoofDetails,arFloorDetails,arModification,arOccupierDetails,arTaxTotal, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to insert geo location details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertGeoLocationDetails(double latitude, double longitude, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertGeoLocationDetails(latitude, longitude, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to create a property by adding a column in table
     *
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> insertProperty(String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (mAppDatabase.propertyDao().getPropertyById(surveyID, propertyID) == null) {
                mAppDatabase.propertyDao().insert(new Property(surveyID, propertyID));
            }
            return true;
        });
    }

    /**
     * to fetch property from db
     *
     * @return
     */
    @Override
    public Observable<List<Property>> loadProperty() {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().loadAllProperty());

    }

    /**
     * to insert building details to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> insertBuildingDetails(String buildingName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails,String stair, String stairArea, String areaRemarks, boolean isValidationOk,String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, anyStructuralChange, anyStructuralChangeYear, anyRoofChange, anyRoofChangeYear, otherBuildingDetails,stair,stairArea,areaRemarks,isValidationOk, surveyID, propertyID);
            return true;
        });
    }


    /**
     * to fetch property details using id from db
     *
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Property> getPropertyById(String surveyID, String propertyID) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getPropertyById(surveyID, propertyID));
    }

    /**
     * to fetch all survey details from db
     *
     * @return
     */
    @Override
    public Observable<List<SurveyWithProperty>> loadAllSurveyWithProperty() {
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().loadAllSurveyWithProperty());
    }

    /**
     * to fetch all survey details from db
     *
     * @return
     */
    @Override
    public Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithProperty() {
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().loadAllCompletedSurveyWithProperty());
    }

    /**
     * to fetch limit survey details from db
     *
     * @return
     */
    @Override
    public Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithPropertyLimit(int limit, int offset) {
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().loadAllCompletedSurveyWithPropertyLimit(limit, offset));
    }

    /**
     * insert completed survey details to  db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> setSurveyCompletedStatus(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().setSurveyCompletedStatus(surveyID);
            return true;
        });
    }

    /**
     * to fetch completed property list from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<List<Property>> getCompletedProperty(String surveyID) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getCompletedProperty(surveyID));
    }

    /**
     * to insert property completed status to db
     * @param versionCode
     * @param appVersion
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> setPropertyCompletedStatus(String versionCode, String appVersion, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().setPropertyCompletedStatus(versionCode,appVersion, surveyID, propertyID);
            return true;
        });
    }

    /**
     * to set sync completed status to db
     *
     * @return
     */
    @Override
    public Observable<Boolean> updateSyncCompletedStatus(List<String> propertyIds, String syncCompletedDate) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().updateSyncCompletedStatus(propertyIds, syncCompletedDate);
            return true;
        });
    }

    /**
     * to fetch pending surveys from db
     *
     * @return
     */
    @Override
    public Observable<List<Survey>> getPendingSurveys() {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().getPendingSurveys());
    }

    /**
     * to fetch completed surveys from db
     *
     * @return
     */
    @Override
    public Observable<List<Survey>> getCompletedSurveys() {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().getCompletedSurveys());
    }

    /**
     * to clear survey details from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> clearSurveyDetails(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().clearSurveyDetails(surveyID);
            return true;
        });
    }

    /**
     * to delete properties from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> deleteProperties(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().deleteProperties(surveyID);
            return true;
        });
    }

    /**
     * to clear building status related data from db
     *
     * @param buildingStatus
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> clearBuildingStatusRelatedData(String buildingStatus, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING)) {
                mAppDatabase.propertyDao().clearBuildingStatusOnGoing("", surveyID, propertyID);
            } else if (buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                    buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)) {
                mAppDatabase.propertyDao().clearBuildingStatusDemolishedUnusable("", new ArrayList(), surveyID, propertyID);
            }else if(buildingStatus.equalsIgnoreCase(BUILDING_STATUS_ONGOING_WITHOUT_ROOF)){
                mAppDatabase.propertyDao().clearBuildingStatusOnGoingWithoutRoof("", new ArrayList(), surveyID, propertyID);
            }
            return true;
        });
    }

    /**
     * to clear survey completed status from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> clearSurveyCompletedStatus(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().clearSurveyCompletedStatus(surveyID);
            return true;
        });
    }

    /**
     * to clear door status related data from
     *
     * @param doorStatus
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> clearDoorStatusRelatedData(String doorStatus, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)) {
                mAppDatabase.propertyDao().clearDoorStatusGL("", new ArrayList(), surveyID, propertyID);
            } else if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)) {
                mAppDatabase.propertyDao().clearDoorStatusPDC("", new ArrayList(), surveyID, propertyID);
            } else if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)) {
                mAppDatabase.propertyDao().clearDoorStatusDC("", surveyID, propertyID);
            }else  if(doorStatus.equalsIgnoreCase(DOOR_STATUS_NC)){
                mAppDatabase.propertyDao().clearDoorStatusNC("",new ArrayList(), surveyID, propertyID );
            }
            return true;
        });
    }

    /**
     * to clear building sub type related data from
     *
     * @param buildingSubType
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> clearBuildingSubTypeRelatedData(String buildingSubType, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_HOUSE) ||
                    buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_QUARTERS) ||
                    buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_APARTMENT) ||
                    buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_FLAT)) {
                mAppDatabase.propertyDao().clearBuildingSubTypeHQAV("", surveyID, propertyID);
            }else if(buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_SUB_TYPE_RELATED_BUILDING)){
                mAppDatabase.propertyDao().clearBuildingSubTypeRelatedBuilding("", new ArrayList(), surveyID, propertyID);
            } else {
                mAppDatabase.propertyDao().clearBuildingSubTypeNotHQAV("", new ArrayList(), surveyID, propertyID);
            }
            return true;
        });
    }

    /**
     * to fetch owner list from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<List<Owner>> getOwnerList(String surveyID) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().loadOwnerList(surveyID));

    }

    /**
     * to fetch owner list from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<List<Road>> getRoadList(String surveyID) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().loadRoadList(surveyID));
    }

    /**
     * to clear floor related survey data from db
     *
     * @param surveyID
     * @return
     */
    @Override
    public Observable<Boolean> clearFloorRelatedSurveyData(String surveyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.surveyDao().clearFloorRelatedSurveyData(new ArrayList(), surveyID);
            return true;
        });
    }
    /**
     * Fetch last one property row other than the mentioned property Id
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Property> getPreviousProperty(String propertyID) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getPreviousProperty( propertyID));
    }


    /**
     * to clear floor related property data from db
     *
     * @param surveyID
     * @param propertyID
     * @return
     */
    @Override
    public Observable<Boolean> insertPropertyImageDetails(String informedBy, String cooperativeSurvey, String surveyorName, ArrayList<Surveyor> surveyorDetailsList, String commonRemarks, String imageOne, String imageTwo, String imageThree,String extraRemarks,boolean isValidationOK, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insertPropertyImageDetails(informedBy, cooperativeSurvey, surveyorName,surveyorDetailsList, commonRemarks, imageOne, imageTwo,imageThree,extraRemarks,isValidationOK, surveyID, propertyID);
            return true;
        });
    }

    @Override
    public Observable<Boolean> clearFloorRelatedPropertyData(String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().clearFloorRelatedPropertyData(new ArrayList(), surveyID, propertyID);
            return true;
        });
    }


    @Override
    public Observable<Boolean> insertSurveyStartDate(String startDate, String surveyID) {
        return Observable.fromCallable(() -> {
            if (mAppDatabase.surveyDao().getSurveyStartDate(surveyID) == null ||
                    mAppDatabase.surveyDao().getSurveyStartDate(surveyID).length() == 0) {
                mAppDatabase.surveyDao().insertSurveyStartDate(startDate, surveyID);
            }
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertPropertyStartDate(String startDate, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (mAppDatabase.propertyDao().getPropertyStartDate(surveyID, propertyID) == null ||
                    mAppDatabase.propertyDao().getPropertyStartDate(surveyID, propertyID).length() == 0) {
                mAppDatabase.propertyDao().insertPropertyStartDate(startDate, surveyID, propertyID);
            }
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertPropertyEndDate(String endDate, String surveyID, String propertyID) {
        return Observable.fromCallable(() -> {
            if (mAppDatabase.propertyDao().getPropertyEndDate(surveyID, propertyID) == null ||
                    mAppDatabase.propertyDao().getPropertyEndDate(surveyID, propertyID).length() == 0) {
                mAppDatabase.propertyDao().insertPropertyEndDate(endDate, surveyID, propertyID);
            }
            return true;
        });
    }
    @Override
    public Observable<String> getWardNumber(String surveyID) {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().getWardNumber(surveyID));
    }

    @Override
    public Observable<Boolean> insertProperty(Property property) {
        return Observable.fromCallable(() -> {
            mAppDatabase.propertyDao().insert(property);
            return true;
        });
    }
    @Override
    public Observable<Integer> getSurveyCount() {
        return Observable.fromCallable(() -> mAppDatabase.surveyDao().getSurveyDataCount());
    }

    @Override
    public Observable<Integer> getPropertyCount(String surveyId) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getPropertyCount(surveyId));
    }
    /**
     * to fetch property with same newProperty id
     *
     * @return
     */
    @Override
    public Observable<Integer> getDuplicateNewPropId(String propertyId,String newpropertyId) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getDuplicateNewPropertyId(propertyId,newpropertyId));
    }

    /**
     *
     * @param propertyId
     * @param oldProprtyId
     * @return count of duplicate oldproperty id
     */
    @Override
    public Observable<Integer> getDuplicateOldPropId(String propertyId, String oldProprtyId) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getDuplicateOldPropertyId(propertyId,oldProprtyId));
    }

    /**
     * to fetch completed survey non residential total property count with building status not in for daily report
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingStatus
     * @param doorStatus
     * @param buildingStatuses
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses){
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingStatus,doorStatus,buildingStatuses));
    }

    /**
     * to fetch completed survey non residential total property count with building status not in and survey type for daily report
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingStatus
     * @param doorStatus
     * @param buildingStatuses
     * @param surveyType
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType){
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingStatus,doorStatus,buildingStatuses,surveyType));
    }


    /**
     * to fetch completed survey ward numbers list for daily report
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @return
     */
    @Override
    public Observable<List> getWardNumbersWithDate(boolean surveyCompletedStatus, boolean syncCompleted, String date){
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getWardNumbersWithDate(surveyCompletedStatus,syncCompleted,date));
    }

    /**
     * to fetch property counts of completed synced survey
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingSubType
     * @param buildingStatus
     * @param doorStatus
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus){
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus));
    }

    /**
     * to fetch property counts of completed synced survey
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingSubType
     * @param buildingStatus
     * @param doorStatus
     * @param surveyType
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String surveyType){
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus,surveyType));
    }

    /**
     * to fetch property counts of completed synced survey with building sub type not equal and building status not in
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingSubType
     * @param buildingStatus
     * @param doorStatus
     * @param buildingStatuses
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses) {
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus,buildingStatuses));
    }

    /**
     * to fetch property counts of completed synced survey with building sub type not equal and building status not in and survey type
     *
     * @param surveyCompletedStatus
     * @param syncCompleted
     * @param date
     * @param pointStatus
     * @param buildingType
     * @param buildingSubType
     * @param buildingStatus
     * @param doorStatus
     * @param buildingStatuses
     * @param surveyType
     *
     * @return
     */
    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType) {
        return Observable.fromCallable(() -> mAppDatabase.surveyWithPropertyDao().getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus,buildingStatuses,surveyType));
    }

    /**
     * This is to get count of synced property under a particular survey
     * @param surveyId
     * @return
     */
    @Override
    public Observable<Integer> getSyncRowCount(String surveyId) {
        return Observable.fromCallable(() -> mAppDatabase.propertyDao().getSyncRowCount(surveyId));

    }

    /**
     * This is to get completed survey list based on sync status
     * @param syncStatus
     * @return
     */

    @Override
    public Observable<List<SurveyWithProperty>> loadSurveyWithPropertyOnSyncStatus(boolean syncStatus) {
        return Observable.fromCallable(() -> {
            if (syncStatus) {
               return mAppDatabase.surveyWithPropertyDao().loadAllSyncedSurveyWithProperty();
            } else {
               return mAppDatabase.surveyWithPropertyDao().loadAllCompletedUnsyncedSurveyWithProperty();
            }

        });
    }

    /**
     * This is to rest property survey completion status
     * @param surveyId
     * @param propertyId
     * @return
     */
    @Override
    public Observable<Boolean> resetPropertyCompletionStatus(String surveyId, String propertyId) {
        return Observable.fromCallable(()->{
            if (mAppDatabase.propertyDao().getPropertyById(surveyId, propertyId) != null) {
                mAppDatabase.propertyDao().resetPropertyCompletedStatus(surveyId,propertyId);
                mAppDatabase.surveyDao().clearSurveyCompletedStatus(surveyId);
            }

            return true;
        });
    }

}