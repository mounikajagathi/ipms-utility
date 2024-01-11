package in.ults.gisurvey.data.local.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.db.Survey;

@Dao
public interface SurveyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Survey survey);

    @Query("UPDATE survey SET point_status = :pointStatus WHERE survey_id = :surveyID")
    void insertPointStatus(String pointStatus, String surveyID);

    @Query("UPDATE survey SET floor_count = :floorCount , property_count = :propertyCount  WHERE survey_id = :surveyID")
    void insertFloorPropertyCount(int floorCount, int propertyCount, String surveyID);

    @Query("UPDATE survey SET no_of_basements = :groundFloor,ground_floor = :groundFloor WHERE survey_id = :surveyID")
    void insertGroundFloor(int groundFloor, String surveyID);

    @Query("UPDATE survey SET basement_area = :basementArea WHERE survey_id = :surveyID")
    void insertBasementArea(ArrayList<BasementAreaItem> basementArea, String surveyID);

    @Query("UPDATE survey SET district = :district, local_body = :localBody, ward_number = :wardNumber, ward_name = :wardName, street_name = :streetName, place_name = :placeName,  village_name= :villageName, post_office = :postOffice,pin_code = :pinCode, building_zone = :buildingZone, fld_effctd = :floodAffected, wtr_lvlhit = :waterLevelHit WHERE survey_id = :surveyID")
    void insertLocationDetails(String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice,String pinCode, String buildingZone, String floodAffected, String waterLevelHit, String surveyID);

    @Query("SELECT * FROM survey WHERE survey_id = :surveyID")
    Survey getSurveyById(String surveyID);

    @Query("SELECT * FROM  survey")
    List<Survey> loadAllSurveys();

    @Query("UPDATE survey SET survey_completed_status = 1 WHERE survey_id = :surveyID")
    void setSurveyCompletedStatus(String surveyID);

    @Query("SELECT * FROM  survey WHERE survey_completed_status = 0")
    List<Survey> getPendingSurveys();

    @Query("SELECT * FROM  survey WHERE survey_completed_status = 1")
    List<Survey> getCompletedSurveys();

    @Query("UPDATE survey SET floor_count = 1, property_count = 1, ground_floor = -1, survey_completed_status = 0 WHERE survey_id = :surveyID")
    void clearSurveyDetails(String surveyID);

    @Query("UPDATE survey SET survey_completed_status = 0 WHERE survey_id = :surveyID")
    void clearSurveyCompletedStatus(String surveyID);

    @Query("UPDATE survey SET  " +
            "  basement_area = :emptyList" +
            ", survey_completed_status = 0 WHERE survey_id = :surveyID")
    void clearFloorRelatedSurveyData(ArrayList emptyList, String surveyID);

    @Query("SELECT survey_start_date FROM survey WHERE survey_id = :surveyID")
    String getSurveyStartDate(String surveyID);

    @Query("UPDATE survey SET survey_start_date = :surveyStartDate WHERE survey_id = :surveyID")
    void insertSurveyStartDate(String surveyStartDate, String surveyID);

    @Query("DELETE FROM survey WHERE survey_id = :surveyID")
    void deleteSurvey(String surveyID);

    @Query("SELECT ward_number FROM survey WHERE survey_id = :surveyID")
    String getWardNumber(String surveyID);

    @Query("SELECT COUNT(survey_id) FROM survey")
    int getSurveyDataCount();

}
