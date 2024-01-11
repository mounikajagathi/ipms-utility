package in.ults.gisurvey.data.local.db.dao;


import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import in.ults.gisurvey.data.model.db.SurveyWithProperty;

@Dao
public interface SurveyWithPropertyDao {

    @Query("SELECT * FROM survey  JOIN property ON survey_id = srvy_id")
    List<SurveyWithProperty> loadAllSurveyWithProperty();

     @Query("SELECT * FROM survey  JOIN property ON survey_id = srvy_id WHERE survey_completed_status = 1 AND sync_completed = 0")
    List<SurveyWithProperty> loadAllCompletedSurveyWithProperty();

    @Query("SELECT * FROM survey   JOIN property ON survey_id = srvy_id WHERE survey_completed_status = 1 AND sync_completed = 0 LIMIT :limit OFFSET :offset")
    List<SurveyWithProperty> loadAllCompletedSurveyWithPropertyLimit(int limit, int offset);


    @Query("SELECT DISTINCT * FROM survey   JOIN property ON survey_id = srvy_id WHERE survey_completed_status = 1 AND sync_completed = 1 GROUP BY survey.survey_id")
    List<SurveyWithProperty> loadAllSyncedSurveyWithProperty();

    @Query("SELECT DISTINCT * FROM survey   JOIN property ON survey_id = srvy_id WHERE survey_completed_status = 1 AND sync_completed = 0 GROUP BY survey.survey_id")
    List<SurveyWithProperty> loadAllCompletedUnsyncedSurveyWithProperty();

    //    Report Query

    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status LIKE :pointStatus AND building_type LIKE :buildingType AND building_sub_type LIKE :buildingSubType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus")
    int getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus);

    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status LIKE :pointStatus AND building_type LIKE :buildingType AND building_sub_type LIKE :buildingSubType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus AND survey_type LIKE :surveyType")
    int getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatusSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String surveyType);

    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status LIKE :pointStatus AND building_type LIKE :buildingType AND building_sub_type != :buildingSubType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus AND building_status NOT IN (:buildingStatuses)")
    int getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotIn (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses);

    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status LIKE :pointStatus AND building_type LIKE :buildingType AND building_sub_type != :buildingSubType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus AND building_status NOT IN (:buildingStatuses) AND survey_type LIKE :surveyType")
    int getTotalPropertyCountWithDatePointStatusBuildingTypeNotBuildingSubTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType (boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingSubType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType);


    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status = :pointStatus AND building_type != :buildingType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus AND building_status NOT IN (:buildingStatuses)")
    int getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotIn(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses);

    @Query("SELECT COUNT(property.property_id) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND sync_completed_date LIKE :date AND point_status = :pointStatus AND building_type != :buildingType AND building_status LIKE :buildingStatus AND door_status LIKE :doorStatus AND building_status NOT IN (:buildingStatuses) AND survey_type LIKE :surveyType")
    int getTotalPropertyCountWithDatePointStatusNotBuildingTypeBuildingStatusDoorStatusBuildingStatusNotInSurveyType(boolean surveyCompletedStatus, boolean syncCompleted, String date, String pointStatus, String buildingType, String buildingStatus, String doorStatus, String[] buildingStatuses, String surveyType);


    @Query("SELECT DISTINCT(ward_number) FROM survey JOIN property ON survey_id = srvy_id WHERE survey_completed_status = :surveyCompletedStatus AND sync_completed = :syncCompleted AND  sync_completed_date LIKE :date ")
    List<String> getWardNumbersWithDate(boolean surveyCompletedStatus, boolean syncCompleted, String date);



}
