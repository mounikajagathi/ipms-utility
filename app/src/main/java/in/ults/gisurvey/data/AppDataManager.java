package in.ults.gisurvey.data;


import android.content.Context;

import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.gisurvey.data.local.db.DbHelper;
import in.ults.gisurvey.data.local.prefs.PreferencesHelper;
import in.ults.gisurvey.data.model.api.DashboardResponse;
import in.ults.gisurvey.data.model.api.DataSyncResponse;
import in.ults.gisurvey.data.model.api.ForgotPasswordResponse;
import in.ults.gisurvey.data.model.api.ImageSyncResponse;
import in.ults.gisurvey.data.model.api.LoginResponse;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.api.SurveyorListResponse;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.api.UrlCheckResponse;
import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;
import in.ults.gisurvey.data.remote.ApiHeader;
import in.ults.gisurvey.data.remote.ApiHelper;
import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(String request, String url) {
        return mApiHelper.doServerLoginApiCall(request, url);
    }

    @Override
    public Observable<UrlCheckResponse> doApiURLCheck(String url) {
        return mApiHelper.doApiURLCheck(url);
    }

    @Override
    public Observable<ForgotPasswordResponse> doForgotPasswordApiCall(String request, String serverUrl) {
        return mApiHelper.doForgotPasswordApiCall(request, serverUrl);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getServerUrl() {
        return mPreferencesHelper.getServerUrl();
    }

    @Override
    public void setServerUrl(String url) {
        mPreferencesHelper.setServerUrl(url);
    }

    @Override
    public String getUrl() {
        return mPreferencesHelper.getUrl();
    }

    @Override
    public void setUrl(String url) {
        mPreferencesHelper.setUrl(url);
    }

    @Override
    public String getPort() {
        return mPreferencesHelper.getPort();
    }

    @Override
    public void setPort(String port) {
        mPreferencesHelper.setPort(port);
    }

    @Override
    public String getSeries() {
        return mPreferencesHelper.getSeries();
    }

    @Override
    public void setSeries(String series) {
        mPreferencesHelper.setSeries(series);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public int getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(int userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public boolean getUserActive() {
        return mPreferencesHelper.getUserActive();
    }

    @Override
    public void setUserActive(boolean active) {
        mPreferencesHelper.setUserActive(active);
    }


    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                -1,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null,
                false);
    }

    @Override
    public void updateApiHeader(String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String series,
            int userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String userImage,
            boolean active) {

        setAccessToken(accessToken);
        setSeries(series);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);
        setUserImage(userImage);
        setUserActive(active);
        updateApiHeader(accessToken);
    }


    @Override
    public Observable<Boolean> insertQFieldID(Survey survey) {
        return mDbHelper.insertQFieldID(survey);
    }

    @Override
    public Observable<Boolean> deleteSurvey(String surveyID) {
        return mDbHelper.deleteSurvey(surveyID);
    }

    @Override
    public Observable<Boolean> deleteProperty(String surveyId, String propertyId) {
        return mDbHelper.deleteProperty(surveyId, propertyId);
    }

    @Override
    public Observable<Boolean> resetPropertyCompletionStatus(String surveyId, String propertyId) {
        return mDbHelper.resetPropertyCompletionStatus(surveyId, propertyId);
    }

    @Override
    public Observable<Boolean> isSurveyExists(Survey survey) {
        return mDbHelper.isSurveyExists(survey);
    }

    @Override
    public Observable<List<Survey>> loadSurvey() {
        return mDbHelper.loadSurvey();
    }

    @Override
    public void setCurrentSurveyID(String surveyID) {
        mPreferencesHelper.setCurrentSurveyID(surveyID);
    }

    @Override
    public String getCurrentSurveyID() {
        return mPreferencesHelper.getCurrentSurveyID();
    }

    @Override
    public void setSurveyOpenMode(String mode) {
        mPreferencesHelper.setSurveyOpenMode(mode);
    }

    @Override
    public String getSurveyOpenMode() {
        return mPreferencesHelper.getSurveyOpenMode();
    }

    @Override
    public void setCurrentPropertyId(String propertyID) {
        mPreferencesHelper.setCurrentPropertyId(propertyID);
    }

    @Override
    public void setCurrentPointStatus(String pointStatus) {
        mPreferencesHelper.setCurrentPointStatus(pointStatus);
    }

    @Override
    public String getCurrentPointStatus() {
        return mPreferencesHelper.getCurrentPointStatus();
    }

    @Override
    public String getCurrentPropertyId() {
        return mPreferencesHelper.getCurrentPropertyId();
    }

    @Override
    public Observable<Boolean> insertPointStatus(String pointStatus, String surveyID) {
        return mDbHelper.insertPointStatus(pointStatus, surveyID);
    }

    @Override
    public Observable<Boolean> insertFloorPropertyCount(int floorCount, int propertyCount, String surveyID) {
        return mDbHelper.insertFloorPropertyCount(floorCount, propertyCount, surveyID);
    }

    @Override
    public Observable<Boolean> insertGroundFloor(int groundFloor, String surveyID) {
        return mDbHelper.insertGroundFloor(groundFloor, surveyID);
    }

    @Override
    public Observable<Boolean> insertBasementArea(ArrayList<BasementAreaItem> basementArea, String surveyID) {
        return mDbHelper.insertBasementArea(basementArea, surveyID);
    }

    @Override
    public Observable<Boolean> insertLocationDetails(String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice, String pinCode, String buildingZone, String isFloodAffected, String waterLevelHit, String surveyID) {
        return mDbHelper.insertLocationDetails(district, localBody, wardNumber, wardName, streetName, placeName, villageName, postOffice, pinCode, buildingZone, isFloodAffected, waterLevelHit, surveyID);
    }

    public Observable<Boolean> insertPropertyDetails(String droneId, String oldPropertyId, String uId, String newPropertyId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropertyNumber, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber, String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType, String newPropARRemark, String oldPropARRemark, String arOwnerAddress,String arZone,String arAC,String arFloorArea,String arBuildingUsage,String arRoadType,String arRoadName,String arBuildingAge,String arRoofDetails,String arFloorDetails,String arModification,String arOccupierDetails,String arTaxTotal, String surveyID, String propertyID) {
        return mDbHelper.insertPropertyDetails(droneId, oldPropertyId, uId, newPropertyId, newPropertyRemarks, landmark, doorStatus, buildingUnder, buildingStatus, buildingType, buildingSubType, establishmentUsage, landmarkName, landmarkCategory, landmarkSubCategory, nearPropertyNumber, buildingUsage, mainBuilding, ownershipEducation, electricity, consumerNumber, bathroom, latrine, toiletWasteDisposal, airConditioner, surveyType, newPropARRemark, oldPropARRemark, arOwnerAddress,arZone,arAC,arFloorArea,arBuildingUsage,arRoadType,arRoadName,arBuildingAge,arRoofDetails,arFloorDetails,arModification,arOccupierDetails,arTaxTotal, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertOwnerDetails(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertOwnerDetails(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertRoadDetails(String nearRoad, String roadType, String roadWidth,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertRoadDetails(nearRoad, roadType, roadWidth,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertEstablishmentDetails(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertEstablishmentDetails(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertTenantDetails(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertTenantDetails(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus,isValidationOk, surveyID, propertyID);

    }

    @Override
    public Observable<Boolean> insertTaxDetails(String taxNumber, String taxAmount, String taxYear, String taxDate, String taxAnnualAmount,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertTaxDetails(taxNumber, taxAmount, taxYear, taxDate, taxAnnualAmount,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertLiveHoodDetails(String religion, String religionCast, String rationCard, String rationCardNumber, String kwaWater, String waterConnectionType, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> waterSourceType, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry, ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource, String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, kwaWater, waterConnectionType, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, waterSourceType, plasticWasteManagementType, liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pet, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, wellPerennialMonth, memCount, swimmingPool, swimmingPoolArea, isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertMoreDetails(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails, String thozhilurapp, String kudumbasree, String healthInsurance,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertMoreDetails(typeOfLand, buildingUnderScheme, plotArea, noOfVehicle, vehicleDetails, thozhilurapp, kudumbasree, healthInsurance,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertMemberDetails(String noOfMembers, ArrayList<MemberDetailsItem> memberDetailsItems,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertMemberDetails(noOfMembers, memberDetailsItems,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertBuildingDetails(String buildingName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails,String stair, String stairArea, String areaRemarks, boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertBuildingDetails(buildingName, surveyNumber, yearsOfConstruction, totalYears, noOfRooms, noOfFloors, floorArea, structureType, carPorch, carPorchArea, commonStair, commonStairArea, otherBuilding, higherFloorSqft, floorType, wallType, noOfRoofType, roofTotal, roofDetails, colonyName, pathwayArea, anyStructuralChange, anyStructuralChangeYear, anyRoofChange, anyRoofChangeYear, otherBuildingDetails,stair,stairArea,areaRemarks,isValidationOk, surveyID, propertyID);

    }

    @Override
    public Observable<Boolean> insertProperty(String surveyID, String propertyID) {
        return mDbHelper.insertProperty(surveyID, propertyID);

    }

    @Override
    public Observable<List<Property>> loadProperty() {
        return mDbHelper.loadProperty();
    }


    @Override
    public Observable<Survey> getSurveyById(String surveyID) {
        return mDbHelper.getSurveyById(surveyID);
    }

    @Override
    public Observable<Property> getPropertyById(String surveyID, String propertyID) {
        return mDbHelper.getPropertyById(surveyID, propertyID);
    }

    @Override
    public Observable<List<SurveyWithProperty>> loadAllSurveyWithProperty() {
        return mDbHelper.loadAllSurveyWithProperty();
    }

    @Override
    public Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithProperty() {
        return mDbHelper.loadAllCompletedSurveyWithProperty();
    }

    @Override
    public Observable<List<SurveyWithProperty>> loadAllCompletedSurveyWithPropertyLimit(int limit, int offset) {
        return mDbHelper.loadAllCompletedSurveyWithPropertyLimit(limit, offset);
    }

    @Override
    public Observable<Boolean> setSurveyCompletedStatus(String surveyID) {
        return mDbHelper.setSurveyCompletedStatus(surveyID);
    }

    @Override
    public Observable<List<Property>> getCompletedProperty(String surveyID) {
        return mDbHelper.getCompletedProperty(surveyID);
    }

    @Override
    public Observable<Property> getPreviousProperty(String propertyID) {
        return mDbHelper.getPreviousProperty(propertyID);
    }

    @Override
    public Observable<Boolean> setPropertyCompletedStatus(String versionCode, String appVersion, String surveyID, String propertyID) {
        return mDbHelper.setPropertyCompletedStatus(versionCode, appVersion, surveyID, propertyID);
    }

    @Override
    public Observable<DataSyncResponse> doDataSyncToServer(String body, String token, String serverUrl) {
        return mApiHelper.doDataSyncToServer(body, token, serverUrl);
    }

    @Override
    public Observable<ImageSyncResponse> uploadMultipleImages(List<File> data, String token, UploadProgressListener listener, String serverUrl) {
        return mApiHelper.uploadMultipleImages(data, token, listener, serverUrl);
    }

    @Override
    public Observable<Boolean> updateSyncCompletedStatus(List<String> propertyIds, String syncCompletedDate) {
        return mDbHelper.updateSyncCompletedStatus(propertyIds,syncCompletedDate);
    }

    @Override
    public Observable<List<Survey>> getPendingSurveys() {
        return mDbHelper.getPendingSurveys();
    }


    @Override
    public Observable<List<Survey>> getCompletedSurveys() {
        return mDbHelper.getCompletedSurveys();
    }

    @Override
    public void setUserImage(String userImage) {
        mPreferencesHelper.setUserImage(userImage);
    }

    @Override
    public String getUserImage() {
        return mPreferencesHelper.getUserImage();
    }


    @Override
    public Observable<Boolean> clearSurveyDetails(String surveyID) {
        return mDbHelper.clearSurveyDetails(surveyID);
    }

    @Override
    public Observable<Boolean> deleteProperties(String surveyID) {
        return mDbHelper.deleteProperties(surveyID);
    }

    @Override
    public Observable<Boolean> clearBuildingStatusRelatedData(String buildingStatus, String surveyID, String propertyID) {
        return mDbHelper.clearBuildingStatusRelatedData(buildingStatus, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> clearSurveyCompletedStatus(String surveyID) {
        return mDbHelper.clearSurveyCompletedStatus(surveyID);
    }

    @Override
    public Observable<Boolean> clearDoorStatusRelatedData(String doorStatus, String surveyID, String propertyID) {
        return mDbHelper.clearDoorStatusRelatedData(doorStatus, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> clearBuildingSubTypeRelatedData(String buildingSubType, String surveyID, String propertyID) {
        return mDbHelper.clearBuildingSubTypeRelatedData(buildingSubType, surveyID, propertyID);
    }

    @Override
    public Observable<List<Owner>> getOwnerList(String surveyID) {
        return mDbHelper.getOwnerList(surveyID);
    }

    @Override
    public Observable<List<Road>> getRoadList(String surveyID) {
        return mDbHelper.getRoadList(surveyID);
    }

    @Override
    public Observable<DashboardResponse> doHomeDashboardApiCall(String serverUrl) {
        return mApiHelper.doHomeDashboardApiCall(serverUrl);
    }

    @Override
    public Observable<SurveyorListResponse> surveyorApiCall(String serverUrl) {
        return mApiHelper.surveyorApiCall(serverUrl);
    }

    @Override
    public Observable<SurvryPointsResponse> doSurveyPointsApiCall(String wardNo, String serverUrl) {
        return mApiHelper.doSurveyPointsApiCall(wardNo, serverUrl);
    }


    @Override
    public void saveDashBoardData(Dashboard dashboard) {
        mPreferencesHelper.saveDashBoardData(dashboard);
    }

    @Override
    public Dashboard getDashBoardData() {
        return mPreferencesHelper.getDashBoardData();
    }

    @Override
    public String getDistrict() {
        return mPreferencesHelper.getDistrict();
    }

    @Override
    public String getLocalBody() {
        return mPreferencesHelper.getLocalBody();
    }

    @Override
    public String getLocalbodyCode() {
        return mPreferencesHelper.getLocalbodyCode();
    }

    @Override
    public String getWardNumber() {
        return mPreferencesHelper.getWardNumber();
    }

    @Override
    public String getWardName() {
        return mPreferencesHelper.getWardName();
    }

    @Override
    public String getSurveyorName() {
        return mPreferencesHelper.getSurveyorName();
    }

    @Override
    public String getMobileCOde() {
        return mPreferencesHelper.getMobileCOde();
    }

    @Override
    public void setDistrict(String district) {
        mPreferencesHelper.setDistrict(district);
    }

    @Override
    public void setLocalBody(String localBody) {
        mPreferencesHelper.setLocalBody(localBody);
    }

    @Override
    public void saveSurveyPointData(SurvryPointsResponse surveyPoints) {
        mPreferencesHelper.saveSurveyPointData(surveyPoints);
    }

    @Override
    public SurvryPointsResponse getSurveyPoints() {
        return mPreferencesHelper.getSurveyPoints();
    }

    @Override
    public String getCompletedListType() {
        return mPreferencesHelper.getCompletedListType();
    }

    @Override
    public void setCompletedListType(String type) {
        mPreferencesHelper.setCompletedListType(type);
    }
    @Override
    public ArrayList<Surveyor> getSelectedSurveyorDetails() {
        return mPreferencesHelper.getSelectedSurveyorDetails();
    }

    @Override
    public void setSelectedSurveyorDetails(ArrayList<Surveyor> surveyorData) {
        mPreferencesHelper.setSelectedSurveyorDetails(surveyorData);
    }

    @Override
    public ArrayList<String> getSelectedSurveyorNames() {
        return mPreferencesHelper.getSelectedSurveyorNames();
    }

    @Override
    public void setSelectedSurveyorNames(ArrayList<String> surveyorsNames) {
        mPreferencesHelper.setSelectedSurveyorNames(surveyorsNames);
    }

    @Override
    public ArrayList<String> getSelectedSurveyPointWards() {
        return mPreferencesHelper.getSelectedSurveyPointWards();
    }

    @Override
    public void setSelectedSurveyPointWards(ArrayList<String> wards) {
        mPreferencesHelper.setSelectedSurveyPointWards(wards);
    }

    @Override
    public void setLocalbodyCode(String localbodyCode) {
        mPreferencesHelper.setLocalbodyCode(localbodyCode);
    }

    @Override
    public void setWardNumber(String wardNumber) {
        mPreferencesHelper.setWardNumber(wardNumber);
    }

    @Override
    public void setWardName(String wardName) {
        mPreferencesHelper.setWardName(wardName);
    }

    @Override
    public Observable<Boolean> clearFloorRelatedSurveyData(String surveyID) {
        return mDbHelper.clearFloorRelatedSurveyData(surveyID);
    }

    @Override
    public Observable<Boolean> clearFloorRelatedPropertyData(String surveyID, String propertyID) {
        return mDbHelper.clearFloorRelatedPropertyData(surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertPropertyImageDetails(String informedBy, String cooperativeSurvey, String surveyorName, ArrayList<Surveyor> surveyorDetailsList, String commonRemarks, String imageOne, String imageTwo, String imageThree,String extraRemarks,boolean isValidationOk, String surveyID, String propertyID) {
        return mDbHelper.insertPropertyImageDetails(informedBy, cooperativeSurvey, surveyorName, surveyorDetailsList, commonRemarks, imageOne, imageTwo, imageThree,extraRemarks,isValidationOk, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertGeoLocationDetails(double latitude, double longitude, String surveyID, String propertyID) {
        return mDbHelper.insertGeoLocationDetails(latitude, longitude, surveyID, propertyID);
    }

    @Override
    public String getTpkFileLocation() {
        return mPreferencesHelper.getTpkFileLocation();
    }

    @Override
    public void setTpkFileLocation(String tpkFileLocation) {
        mPreferencesHelper.setTpkFileLocation(tpkFileLocation);
    }


    @Override
    public void setSurveyorName(String surveyorName) {
        mPreferencesHelper.setSurveyorName(surveyorName);

    }

    @Override
    public void setMobileCode(String code) {
        mPreferencesHelper.setMobileCode(code);
    }

    @Override
    public String getSelectedPropId() {
        return mPreferencesHelper.getSelectedPropId();
    }

    @Override
    public void setSelectedPropId(String propId) {
        mPreferencesHelper.setSelectedPropId(propId);
    }

    @Override
    public String getSelectedDroneId() {
        return mPreferencesHelper.getSelectedDroneId();
    }

    @Override
    public void setSelectedDroneId(String droneId) {
        mPreferencesHelper.setSelectedDroneId(droneId);
    }

    @Override
    public String getSelectedNewPropId() {
        return mPreferencesHelper.getSelectedNewPropId();
    }

    @Override
    public void setSelectedNewPropId(String newPropId) {
        mPreferencesHelper.setSelectedNewPropId(newPropId);
    }

    @Override
    public String getSelectedServerSurveyId() {
        return mPreferencesHelper.getSelectedServerSurveyId();
    }

    @Override
    public void setSelectedServerSurveyId(String id) {
        mPreferencesHelper.setSelectedServerSurveyId(id);
    }

    @Override
    public Observable<Boolean> insertSurveyStartDate(String startDate, String surveyID) {
        return mDbHelper.insertSurveyStartDate(startDate, surveyID);
    }

    @Override
    public Observable<Boolean> insertPropertyStartDate(String startDate, String surveyID, String propertyID) {
        return mDbHelper.insertPropertyStartDate(startDate, surveyID, propertyID);
    }

    @Override
    public Observable<Boolean> insertPropertyEndDate(String startDate, String surveyID, String propertyID) {
        return mDbHelper.insertPropertyEndDate(startDate, surveyID, propertyID);
    }

    @Override
    public Observable<String> getWardNumber(String surveyID) {
        return mDbHelper.getWardNumber(surveyID);
    }

    @Override
    public Observable<Boolean> insertProperty(Property property) {
        return mDbHelper.insertProperty(property);
    }

    @Override
    public Observable<Integer> getSurveyCount() {
        return mDbHelper.getSurveyCount();
    }

    @Override
    public Observable<Integer> getPropertyCount(String surveyId) {
        return mDbHelper.getPropertyCount(surveyId);
    }

    @Override
    public Observable<Integer> getDuplicateNewPropId(String propertyId, String newpropertyId) {
        return mDbHelper.getDuplicateNewPropId(propertyId, newpropertyId);
    }

    @Override
    public Observable<Integer> getDuplicateOldPropId(String propertyId, String oldProprtyId) {
        return mDbHelper.getDuplicateOldPropId(propertyId, oldProprtyId);
    }

    @Override
    public String getARFileLocation() {
        return mPreferencesHelper.getARFileLocation();
    }

    @Override
    public void setARFileLocation(String arFileLocation) {
        mPreferencesHelper.setARFileLocation(arFileLocation);
    }

    @Override
    public String getInfoVideoFileLocation() {
        return mPreferencesHelper.getInfoVideoFileLocation();
    }

    @Override
    public void setInfoVideoFileLocation(String infoVideoFileLocation) {
        mPreferencesHelper.setInfoVideoFileLocation(infoVideoFileLocation);
    }

    @Override
    public String getLiveLocationStatus() {
        return mPreferencesHelper.getLiveLocationStatus();
    }

    @Override
    public void setLiveLocationStatus(String liveloc) {
        mPreferencesHelper.setLiveLocationStatus(liveloc);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(surveyCompletedStatus, syncCompleted, date, pointStatus, buildingType, buildingStatus, doorStatus, buildingStatuses);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(surveyCompletedStatus, syncCompleted, date, pointStatus, buildingType, buildingStatus, doorStatus, buildingStatuses, surveyType);
    }


    @Override
    public Observable<List> getWardNumbersWithDate(boolean surveyCompletedStatus, boolean syncCompleted, String date) {
        return mDbHelper.getWardNumbersWithDate(surveyCompletedStatus, syncCompleted, date);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(surveyCompletedStatus, syncCompleted, date, pointStatus, buildingType, buildingSubType, buildingStatus, doorStatus);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String surveyType) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(surveyCompletedStatus, syncCompleted, date, pointStatus, buildingType, buildingSubType, buildingStatus, doorStatus, surveyType);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus,buildingStatuses);
    }

    @Override
    public Observable<Integer> getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType) {
        return mDbHelper.getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(surveyCompletedStatus,syncCompleted,date,pointStatus,buildingType,buildingSubType,buildingStatus,doorStatus,buildingStatuses, surveyType);
    }

    @Override
    public Observable<Integer> getSyncRowCount(String surveyId) {
        return mDbHelper.getSyncRowCount(surveyId);
    }

    @Override
    public Observable<List<SurveyWithProperty>> loadSurveyWithPropertyOnSyncStatus(boolean syncStatus) {
        return mDbHelper.loadSurveyWithPropertyOnSyncStatus(syncStatus);
    }
}
