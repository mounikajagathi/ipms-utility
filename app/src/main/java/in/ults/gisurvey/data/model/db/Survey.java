package in.ults.gisurvey.data.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.gisurvey.data.local.db.converter.BasementAreaConverter;
import in.ults.gisurvey.utils.AppConstants;

@Entity(tableName = "survey")
public class Survey {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "survey_id")
    private String surveyID;

    @ColumnInfo(name = "survey_start_date")
    private String surveyStartDate = "";

    @ColumnInfo(name = "point_status")
    private String pointStatus = "";

    @ColumnInfo(name = "floor_count")
    private int floorCount = -1;

    @ColumnInfo(name = "property_count")
    private int propertyCount = -1;

    @ColumnInfo(name = "ground_floor")
    private int groundFloor = -1;

    @ColumnInfo(name = "no_of_basements")
    private String noOfBasements = "";

    @ColumnInfo(name = "district")
    private String district = "";

    @ColumnInfo(name = "local_body")
    private String localBody = "";

    @ColumnInfo(name = "ward_number")
    private String wardNumber = "";

    @ColumnInfo(name = "ward_name")
    private String wardName = "";

    @ColumnInfo(name = "street_name")
    private String streetName = "";

    @ColumnInfo(name = "place_name")
    private String placeName = "";

    @ColumnInfo(name = "village_name")
    private String villageName = "";

    @ColumnInfo(name = "post_office")
    private String postOffice = "";

    @ColumnInfo(name = "pin_code")
    private String pinCode = "";

    @ColumnInfo(name = "building_zone")
    private String buildingZone = "";

    @ColumnInfo(name = "survey_completed_status")
    public boolean surveyCompletedStatus = false;

    @ColumnInfo(name = "fld_effctd")
    public String floodAffected = "";

    @ColumnInfo(name = "wtr_lvlhit")
    public String waterLevelHit = "";


    @ColumnInfo(name = "basement_area")
    @TypeConverters(BasementAreaConverter.class)
    public ArrayList<BasementAreaItem> basementArea = new ArrayList<>();

    @ColumnInfo(name = "survey_completed_date")
    public String surveyCompletedDate = "";

    @ColumnInfo(name = "survey_id_ward_number")
    private String surveyIdWardNumber = "";

    public String getSurveyCompletedDate() {
        return surveyCompletedDate;
    }

    public void setSurveyCompletedDate(String surveyCompletedDate) {
        this.surveyCompletedDate = surveyCompletedDate;
    }

    public String getFloodAffected() {
        return floodAffected;
    }

    public void setFloodAffected(String floodAffected) {
        this.floodAffected = floodAffected;
    }

    public String getWaterLevelHit() {
        return waterLevelHit;
    }

    public void setWaterLevelHit(String waterLevelHit) {
        this.waterLevelHit = waterLevelHit;
    }

    public Survey(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyID() {
        return surveyID;
    }


    public String getPointStatus() {
        return pointStatus;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public int getGroundFloor() {
        return groundFloor;
    }

    public String getDistrict() {
        return district;
    }

    public String getLocalBody() {
        return localBody;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public String getWardName() {
        return wardName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getVillageName() {
        return villageName;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getBuildingZone() {
        return buildingZone;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public void setPointStatus(String pointStatus) {
        this.pointStatus = pointStatus;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public void setPropertyCount(int propertyCount) {
        this.propertyCount = propertyCount;
    }

    public void setGroundFloor(int groundFloor) {
        this.groundFloor = groundFloor;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setLocalBody(String localBody) {
        this.localBody = localBody;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public void setBuildingZone(String buildingZone) {
        this.buildingZone = buildingZone;
    }

    public boolean isSurveyCompletedStatus() {
        return surveyCompletedStatus;
    }

    public String getSurveyIdWardNumber() {
        return surveyIdWardNumber;
    }

    public void setSurveyIdWardNumber(String surveyIdWardNumber) {
        this.surveyIdWardNumber = surveyIdWardNumber;
    }

    public void setSurveyCompletedStatus(boolean surveyCompletedStatus) {
        this.surveyCompletedStatus = surveyCompletedStatus;
    }

    public ArrayList<BasementAreaItem> getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(ArrayList<BasementAreaItem> basementArea) {
        this.basementArea = basementArea;
    }


    public String getNoOfBasements() {
        return noOfBasements;
    }

    public void setNoOfBasements(String noOfBasements) {
        this.noOfBasements = noOfBasements;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getSurveyStartDate() {
        return surveyStartDate;
    }

    public void setSurveyStartDate(String surveyStartDate) {
        this.surveyStartDate = surveyStartDate;
    }


    @Ignore
    public Survey(@NonNull String surveyID, String surveyStartDate, String pointStatus, int floorCount, int propertyCount, int groundFloor, String noOfBasements, String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice, String pinCode, String buildingZone, boolean surveyCompletedStatus, String floodAffected, String waterLevelHit, ArrayList<BasementAreaItem> basementArea, String surveyCompletedDate, String surveyIdWardNumber) {
        this.surveyID = surveyID;
        this.surveyStartDate = surveyStartDate;
        this.pointStatus = pointStatus;
        this.floorCount = floorCount;
        this.propertyCount = propertyCount;
        this.groundFloor = groundFloor;
        this.noOfBasements = noOfBasements;
        this.district = district;
        this.localBody = localBody;
        this.wardNumber = wardNumber;
        this.wardName = wardName;
        this.streetName = streetName;
        this.placeName = placeName;
        this.villageName = villageName;
        this.postOffice = postOffice;
        this.pinCode = pinCode;
        this.buildingZone = buildingZone;
        this.surveyCompletedStatus = surveyCompletedStatus;
        this.floodAffected = floodAffected;
        this.waterLevelHit = waterLevelHit;
        this.basementArea = basementArea;
        this.surveyCompletedDate = surveyCompletedDate;
        this.surveyIdWardNumber = surveyIdWardNumber;
    }
}
