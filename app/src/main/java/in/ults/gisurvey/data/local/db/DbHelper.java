package in.ults.gisurvey.data.local.db;

import java.util.ArrayList;
import java.util.List;

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
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> insertQFieldID(final Survey survey);

    Observable<Boolean> deleteSurvey(final String surveyID);

    Observable<Boolean> isSurveyExists(final Survey survey);

    Observable<Boolean> insertPointStatus(final String pointStatus, final String surveyID);

    Observable<Boolean> insertFloorPropertyCount(final int floorCount, final int propertyCount, final String surveyID);

    Observable<Boolean> insertGroundFloor(final int groundFloor, final String surveyID);

    Observable<Boolean> insertBasementArea(ArrayList<BasementAreaItem> basementArea, String surveyID);

    Observable<Boolean> insertPropertyImageDetails(String informedBy, String cooperativeSurvey, String surveyorName, ArrayList<Surveyor> surveyorDetailsList, String commonRemarks, String imageOne, String imageTwo, String imageThree,String extraRemarks,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Survey> getSurveyById(String surveyID);

    Observable<Property> getPreviousProperty( String propertyID);

    Observable<Boolean> insertLocationDetails(String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice, String pinCode, String buildingZone,String isFloodAffected,String waterLevelHit, String surveyID);

    Observable<Boolean> insertPropertyDetails(String doneId, String oldPropertyId, String uId, String newPropertyId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropertyNumber, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber,String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType, String newPropARRemark,String oldPropARRemark,String arOwnerAddress, String arZone,String arAC,String arFloorArea,String arBuildingUsage,String arRoadType,String arRoadName,String arBuildingAge,String arRoofDetails,String arFloorDetails,String arModification,String arOccupierDetails,String arTaxTotal,String surveyID, String propertyID);

    Observable<Boolean> insertOwnerDetails(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertRoadDetails(String nearRoad, String roadType, String roadWidth,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertEstablishmentDetails(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertTenantDetails(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertTaxDetails(String taxNumber, String taxAmount, String taxYear, String taxDate, String taxAnnualAmount,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertLiveHoodDetails(String religion, String religionCast, String rationCard, String rationCardNumber, String kwaWater, String waterConnectionType, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> waterSourceType, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry,ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource,String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertMoreDetails(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails,  String thozhilurapp, String kudumbasree, String healthInsurance,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertMemberDetails(String noOfMembers, ArrayList<MemberDetailsItem> memberDetailsItems,boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertGeoLocationDetails(double latitude, double longitude, String surveyID, String propertyID);


    Observable<List<Survey>> loadSurvey();

    Observable<List<Property>> loadProperty();

    Observable<Boolean> deleteProperty(final String surveyId, final String propertyId);

    Observable<Boolean> insertBuildingDetails(String buildingName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails,String stair, String stairArea, String areaRemarks, boolean isValidationOk, String surveyID, String propertyID);

    Observable<Boolean> insertProperty(String surveyID, String propertyID);

    Observable<List<SurveyWithProperty>> loadAllSurveyWithProperty();

    Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithProperty();

    Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithPropertyLimit(int limit, int offset);

    Observable<Property> getPropertyById(String surveyID, String propertyID);

    Observable<Boolean> setSurveyCompletedStatus(String surveyID);

    Observable<List<Property>> getCompletedProperty(String surveyID);

    Observable<Boolean> setPropertyCompletedStatus(String versionCode,String appVersion, String surveyID, String propertyID);

    Observable<Boolean> updateSyncCompletedStatus(List<String> propertyIds, String syncCompletedDate);

    Observable<List<Survey>> getPendingSurveys();

    Observable<List<Survey>> getCompletedSurveys();

    Observable<Boolean> clearSurveyDetails(String surveyID);

    Observable<Boolean> deleteProperties(String surveyID);

    Observable<Boolean> clearBuildingStatusRelatedData(String buildingStatus, String surveyID, String propertyID);

    Observable<Boolean> clearDoorStatusRelatedData(String doorStatus, String surveyID, String propertyID);

    Observable<Boolean> clearBuildingSubTypeRelatedData(String buildingSubType, String surveyID, String propertyID);

    Observable<Boolean> clearSurveyCompletedStatus(String surveyID);

    Observable<List<Owner>> getOwnerList(String surveyID);

    Observable<List<Road>> getRoadList(String surveyID);

    Observable<Boolean> clearFloorRelatedSurveyData(String surveyID);

    Observable<Boolean> clearFloorRelatedPropertyData(String surveyID, String propertyID);

    Observable<Boolean> insertSurveyStartDate(final String startDate, final String surveyID);

    Observable<Boolean> insertPropertyStartDate(final String startDate, String surveyID, String propertyID);

    Observable<Boolean> insertPropertyEndDate(final String startDate, String surveyID, String propertyID);

    Observable<String> getWardNumber(String surveyID);

    Observable<Integer> getSurveyCount();

    Observable<Boolean> insertProperty(Property property);

    Observable<Integer> getPropertyCount(String surveyId);

    Observable<Integer> getDuplicateNewPropId(String propertyId,String newpropertyId);

    Observable<Integer> getDuplicateOldPropId(String propertyId,String oldProprtyId);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType,String buildingStatus, String doorStatus, String[] buildingStatuses);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType,String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType);

    Observable<List> getWardNumbersWithDate(boolean surveyCompletedStatus, boolean syncCompleted,String date);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String surveyType);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses);

    Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType);

    Observable<Boolean> resetPropertyCompletionStatus(final String surveyId, final String propertyId);

    Observable<Integer> getSyncRowCount(String surveyId);

    Observable<List<SurveyWithProperty>> loadSurveyWithPropertyOnSyncStatus(boolean syncStatus);


}
