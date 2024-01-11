package in.ults.gisurvey.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.data.model.db.VehicleDetailsItem;

@Dao
public interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Property property);

    @Query("SELECT * FROM property WHERE srvy_id = :surveyID AND property_id = :propertyID")
    Property getPropertyById(String surveyID, String propertyID);

    @Query("UPDATE property SET drone_id=:droneId, old_property_id = :oldPropertyId, u_id = :uId, new_property_id = :newPropertyId, new_property_remarks = :newPropertyRemarks, landmark = :landmark, door_status = :doorStatus, building_under = :buildingUnder, building_status = :buildingStatus, building_type = :buildingType, building_sub_type = :buildingSubType, establishment_usage = :establishmentUsage, landmark_name = :landmarkName ,landmark_category = :landmarkCategory,landmark_sub_category = :landmarkSubCategory,near_property_number = :nearPropertyNumber,building_usage = :buildingUsage, main_building = :mainBuilding, ownership_education = :ownershipEducation ,electricity = :electricity,consumer_number = :consumerNumber,bathroom = :bathroom , latrine = :latrine, toilet_waste_disposal = :toiletWasteDisposal, air_conditioner = :airConditioner, survey_type = :surveyType,newproid_ar_remarks=:newPropARRemark,oldproid_ar_remarks=:oldPropARRemark,ar_owner_address=:arOwnerAddress,ar_zone=:arZone ,ar_ac=:arAC,ar_floor_area=:arFloorArea,ar_building_usage=:arBuildingUsage,ar_road_type=:arRoadType,ar_road_name=:arRoadName,ar_building_age=:arBuildingAge,ar_roof_details=:arRoofDetails,ar_floor_details=:arFloorDetails,ar_modification=:arModification,ar_occupier_details=:arOccupierDetails ,ar_tax_total=:arTaxTotal WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertPropertyDetails(String droneId,String oldPropertyId, String uId, String newPropertyId, String newPropertyRemarks, String landmark, String doorStatus, String buildingUnder, String buildingStatus, String buildingType, String buildingSubType, String establishmentUsage, String landmarkName, String landmarkCategory, String landmarkSubCategory, String nearPropertyNumber, String buildingUsage, String mainBuilding, String ownershipEducation, String electricity, String consumerNumber,String bathroom, String latrine, String toiletWasteDisposal, String airConditioner, String surveyType, String newPropARRemark,String oldPropARRemark,String arOwnerAddress,String arZone,String arAC,String arFloorArea,String arBuildingUsage,String arRoadType,String arRoadName,String arBuildingAge,String arRoofDetails,String arFloorDetails,String arModification,String arOccupierDetails,String arTaxTotal, String surveyID, String propertyID);

    @Query("UPDATE property SET informed_by = :informedBy, cooperate_survey = :cooperativeSurvey, surveyor_name = :surveyorName, common_remarks = :commonRemarks, property_image_one = :imageOne, property_image_two = :imageTwo ,property_image_three = :imageThree,surveyor_details=:surveyorDetailsList,extra_remarks=:extraRemarks,is_image_validation_ok=:isValidationOk  WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertPropertyImageDetails(String informedBy, String cooperativeSurvey, String surveyorName, ArrayList<Surveyor> surveyorDetailsList, String commonRemarks, String imageOne, String imageTwo, String imageThree,String extraRemarks,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET owner_name = :ownerName, owner_occupation = :ownerOccupation,owner_house_name_number = :ownerHouseNameNumber,owner_street_place_name = :ownerStreetPlaceName,owner_state = :ownerState,owner_post_office = :ownerPostOffice,owner_pincode = :ownerPincode,owner_email = :ownerEmail,owner_land_line = :ownerLandLine, owner_mobile = :ownerMobile, tele_call_number = :teleCallNumber, is_owner_validation_ok=:isValidationOk  WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertOwnerDetails(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET near_road = :nearRoad, road_type = :roadType, road_width = :roadWidth,is_road_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertRoadDetails(String nearRoad, String roadType, String roadWidth,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET establishment_name = :establishmentName , establishment_type = :establishmentType , in_charge = :inCharge , in_charge_role = :inChargeRole , establishment_year = :establishmentYear ,gst_status = :gstStatus , employee_count = :employeeCount , license_number = :licenseNumber , establishment_email = :establishmentEmail,establishment_landline= :establishmentLandline,establishment_mobile = :establishmentMobile,is_establishment_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertEstablishmentDetails(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET tenant_name = :tenantName , tenant_house_name_number = :tenantHouseNameNumber , tenant_street_place_name = :tenantStreetPlaceName ,tenant_state = :tenantState,tenant_post_office = :tenantPostOffice,tenant_pincode = :tenantPincode, tenant_land_line = :tenantLandLine , tenant_mobile = :tenantMobile ,tenant_email = :tenantEmail , tenant_amount = :tenantAmount , tenant_native = :tenantNative, tenant_survey_number = :tenantSurveyNumber, tenant_status = :tenantStatus , is_tenant_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertTenantDetails(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET tax_number = :taxNumber , tax_amount = :taxAmount , tax_year = :taxYear ,tax_date = :taxDate,tax_annual_amount =:taxAnnualAmount,is_tax_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertTaxDetails(String taxNumber, String taxAmount, String taxYear, String taxDate, String taxAnnualAmount,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET religion = :religion, religion_cast = :religionCast , ration_card = :rationCard ,ration_card_number = :rationCardNumber,    kwa_water = :kwaWater ,water_connection_type = :waterConnectionType ,water_supply_duration = :waterSupplyDuration ,  gas_connection = :gasConnection ,  rain_water_harvest = :rainWaterHarvest ,  lack_drinking_water = :lackDrinkingWater ,solar_panel = :solarPanel ,  water_source_type = :waterSourceType , plastic_waste_management_type = :plasticWasteManagementType , liquid_waste_management_type = :liquidWasteManagementType , organic_waste_management_type = :organicWasteManagementType ,any_other_facility = :otherFacility , bank_account = :bankAccount, pet = :pet, cattles = :cattles, no_of_cattles = :noOfCattles, paultry = :paultry, no_of_poultry =:noOfPoultry,well_details=:wellDetails,water_connection=:waterConnection,other_source=:otherSource,well_perennial = :wellPerennial,well_perennial_month = :wellPerennialMonth, mem_Count = :memCount, swimming_pool = :swimmingPool, swimming_pool_area = :swimmingPoolArea,is_livehood_validation_ok=:isValidationOk  WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertLiveHoodDetails(String religion, String religionCast, String rationCard, String rationCardNumber, String kwaWater, String waterConnectionType, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> waterSourceType, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry, ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource,String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea, boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET type_of_land = :typeOfLand,building_under_scheme = :buildingUnderScheme,plot_area = :plotArea,no_vehicles = :noOfVehicle,vehicle_details = :vehicleDetails,thozhilurapp =:thozhilurapp, kudumbasree=:kudumbasree,health_insurance=:healthInsurance,is_more_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertMoreDetails(String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicle, ArrayList<VehicleDetailsItem> vehicleDetails,  String thozhilurapp, String kudumbasree, String healthInsurance,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE property SET no_of_members = :noOfMembers, member_details = :memberDetailsItems,is_member_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertMemberDetails(String noOfMembers, ArrayList<MemberDetailsItem> memberDetailsItems,boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE Property SET building_name = :buildingName,survey_number = :surveyNumber,years_of_construction = :yearsOfConstruction,total_years = :totalYears,no_of_rooms = :noOfRooms,no_of_floors = :noOfFloors, floor_area = :floorArea, structure_type = :structureType, car_porch = :carPorch, car_porch_area= :carPorchArea , common_stair = :commonStair, common_stair_area = :commonStairArea,stair = :stair, stair_area = :stairArea, area_remarks = :areaRemarks, other_building = :otherBuilding,higher_floor_sqft = :higherFloorSqft,floor_type = :floorType, wall_type = :wallType,no_of_roof_type = :noOfRoofType,roof_total = :roofTotal,roof_type_details = :roofDetails,colony_name = :colonyName,pathway_area = :pathwayArea,any_structural_change = :anyStructuralChange,structural_change_year = :anyStructuralChangeYear,roof_type_change = :anyRoofChange,roof_type_change_year = :anyRoofChangeYear,other_building_details = :otherBuildingDetails,is_building_validation_ok=:isValidationOk WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertBuildingDetails(String buildingName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea, String otherBuilding, String higherFloorSqft, ArrayList<String> floorType, ArrayList<String> wallType, String noOfRoofType, String roofTotal, ArrayList<BuildingDetailsRoofItem> roofDetails, String colonyName, String pathwayArea, String anyStructuralChange, String anyStructuralChangeYear, String anyRoofChange, String anyRoofChangeYear, String otherBuildingDetails,String stair, String stairArea, String areaRemarks, boolean isValidationOk, String surveyID, String propertyID);

    @Query("UPDATE Property SET latitude = :latitude,longitude = :longitude WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertGeoLocationDetails(double latitude, double longitude, String surveyID, String propertyID);

    @Query("SELECT * FROM  property")
    List<Property> loadAllProperty();

    @Query("SELECT * FROM  property where srvy_id = :surveyID AND completed_status = 1")
    List<Property> getCompletedProperty(String surveyID);

    @Query("SELECT * FROM property WHERE  property_id !=:propertyID ORDER BY id DESC LIMIT 1")
    Property getPreviousProperty( String propertyID);

    @Query("UPDATE property SET version = :versionCode,app_version_details = :appVersion, completed_status = 1 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void setPropertyCompletedStatus(String versionCode,String appVersion, String surveyID, String propertyID);

    @Query("DELETE FROM property WHERE srvy_id = :surveyID")
    void deleteProperties(String surveyID);

    @Query("SELECT DISTINCT(owner_mobile) , owner_name ,owner_email,owner_house_name_number,owner_land_line," +
            " owner_occupation,owner_state,owner_pincode,owner_post_office," +
            "owner_street_place_name FROM  property WHERE srvy_id = :surveyID AND owner_name !=\"\" ")
    List<Owner> loadOwnerList(String surveyID);

    @Query("SELECT DISTINCT(road_type) , near_road ,road_width " +
            " FROM  property WHERE srvy_id = :surveyID AND near_road !=\"\" ")
    List<Road> loadRoadList(String surveyID);

    @Query("UPDATE property SET  " +
            " no_of_floors = \"0\" , floor_area = :emptyList" +
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearFloorRelatedPropertyData(ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET sync_completed = 1, sync_completed_date =:syncCompletedDate WHERE property_id IN (:propertyIds)")
    void updateSyncCompletedStatus(List<String> propertyIds, String syncCompletedDate);

    @Query("SELECT property_start_date FROM property  WHERE srvy_id = :surveyID AND property_id = :propertyID")
    String getPropertyStartDate( String surveyID, String propertyID);

    @Query("SELECT property_end_date FROM property  WHERE srvy_id = :surveyID AND property_id = :propertyID")
    String getPropertyEndDate( String surveyID, String propertyID);

    @Query("UPDATE property SET property_start_date = :propertyStartDate WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertPropertyStartDate(String propertyStartDate, String surveyID, String propertyID);

    @Query("UPDATE property SET property_end_date = :propertyEndDate WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void insertPropertyEndDate(String propertyEndDate, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  owner_occupation = :emptyString,owner_house_name_number = :emptyString,owner_street_place_name = :emptyString,owner_post_office = :emptyString,owner_pincode = :emptyString,owner_email = :emptyString,owner_land_line = :emptyString, owner_mobile = :emptyString " +
            ", near_road = :emptyString, road_type = :emptyString, road_width = :emptyString" +
            ", tenant_name = :emptyString , tenant_house_name_number = :emptyString , tenant_street_place_name = :emptyString ,tenant_pincode = :emptyString, tenant_land_line = :emptyString , tenant_mobile = :emptyString , tenant_amount = :emptyString , tenant_native = :emptyString" +
            ", tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString" +
            ", establishment_name = :emptyString , establishment_type = :emptyString , in_charge = :emptyString , in_charge_role = :emptyString , establishment_year = :emptyString , employee_count = :emptyString , license_number = :emptyString " +
            ", religion = :emptyString,religion_cast = :emptyString , ration_card = :emptyString ,ration_card_number = :emptyString, bank_account = :emptyString, cattles = :emptyString, paultry = :emptyString " +
            ", building_name = :emptyString,survey_number = :emptyString,years_of_construction = :emptyString,total_years = :emptyString,no_of_rooms = :emptyString,no_of_floors = \"0\", floor_area = :emptyList,other_building = :emptyString,higher_floor_sqft = :emptyString,floor_type = :emptyList, no_of_roof_type = \"0\" ,roof_total = :emptyString, roof_type_details = :emptyList" +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ",completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingStatusDemolishedUnusable(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString,any_structural_change=:emptyString,structural_change_year=:emptyString,roof_type_change=:emptyString,roof_type_change_year=:emptyString" +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingStatusOnGoing(String emptyString, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "air_conditioner=:emptyString,landmark=:emptyString,landmark_name=:emptyString,landmark_category=:emptyString,landmark_sub_category=:emptyString,old_property_id=:emptyString,u_id=:emptyString,new_property_id=:emptyString,new_property_remarks=:emptyString,near_property_number=:emptyString," +
            "  tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString,tax_annual_amount=:emptyString," +
            "establishment_name=:emptyString,establishment_name=:emptyString,establishment_year=:emptyString,in_charge=:emptyString,in_charge_role=:emptyString,employee_count=:emptyString,employee_count=:emptyString,license_number=:emptyString,gst_status=:emptyString,establishment_email=:emptyString,establishment_landline=:emptyString,establishment_mobile=:emptyString," +
            "no_of_members=:emptyString,member_details=:emptyList," +
            "ration_card=:emptyString,ration_card_number=:emptyString,kwa_water=:emptyString,water_connection_type=:emptyString,water_supply_duration=:emptyString,lack_drinking_water=:emptyString,gas_connection=:emptyString,rain_water_harvest=:emptyString,solar_panel=:emptyString,water_source_type=:emptyList,plastic_waste_management_type=:emptyList,liquid_waste_management_type=:emptyList,organic_waste_management_type=:emptyList,well_details=:emptyList,water_connection=:emptyList,other_source=:emptyList,any_other_facility=:emptyList,pet=:emptyList,bank_account=:emptyString,paultry=:emptyString,no_of_poultry=:emptyString ,cattles=:emptyString,no_of_cattles=:emptyString," +
            "type_of_land=:emptyString,building_under_scheme=:emptyString,plot_area=:emptyString, no_vehicles=\"0\",vehicle_details=:emptyList,well_perennial=:emptyString,well_perennial_month=:emptyList,thozhilurapp=:emptyString,kudumbasree=:emptyString,health_insurance=:emptyString," +
            "building_name=:emptyString,colony_name=:emptyString,years_of_construction=:emptyString,total_years=:emptyString,no_of_rooms=:emptyString,no_of_floors=\"0\",floor_area=:emptyList,structure_type=:emptyString,car_porch=:emptyString,car_porch_area=:emptyString,common_stair=:emptyString,common_stair_area=:emptyString,stair=:emptyString,stair_area=:emptyString,pathway_area=:emptyString,is_any_extension=:emptyString,any_structural_change=:emptyString,structural_change_year=:emptyString,roof_type_change=:emptyString,roof_type_change_year=:emptyString,other_building=:emptyString,other_building_details=:emptyString,floor_type=:emptyList,higher_floor_sqft=:emptyString,roof_total=:emptyString,no_of_roof_type= \"0\",roof_type_details=:emptyList"+
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingStatusOnGoingWithoutRoof(String emptyString,ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  tenant_house_name_number = :emptyString , tenant_street_place_name = :emptyString ,tenant_pincode = :emptyString, tenant_land_line = :emptyString , tenant_mobile = :emptyString , tenant_amount = :emptyString , tenant_native = :emptyString" +
            ", tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString" +
            ", license_number = :emptyString " +
            ", ration_card = :emptyString ,ration_card_number = :emptyString, bank_account = :emptyString " +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearDoorStatusDC(String emptyString, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  tenant_name = :emptyString , tenant_house_name_number = :emptyString , tenant_street_place_name = :emptyString ,tenant_pincode = :emptyString, tenant_land_line = :emptyString , tenant_mobile = :emptyString , tenant_amount = :emptyString , tenant_native = :emptyString" +
            ", tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString" +
            ", employee_count = :emptyString , license_number = :emptyString " +
            ", member_details = :emptyList" +
            ", ration_card = :emptyString ,ration_card_number = :emptyString, bank_account = :emptyString " +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearDoorStatusPDC(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  tenant_house_name_number = :emptyString , tenant_street_place_name = :emptyString ,tenant_pincode = :emptyString, tenant_land_line = :emptyString , tenant_mobile = :emptyString , tenant_amount = :emptyString , tenant_native = :emptyString" +
            ", tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString" +
            ", license_number = :emptyString " +
            ", ration_card = :emptyString ,ration_card_number = :emptyString, bank_account = :emptyString, cattles = :emptyString, paultry = :emptyString " +
            ", other_building = :emptyString, no_of_rooms = :emptyString, no_of_floors = \"0\",floor_area = :emptyList " +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearDoorStatusGL(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  tenant_name = :emptyString ,tenant_house_name_number = :emptyString , tenant_street_place_name = :emptyString ,tenant_pincode = :emptyString, tenant_land_line = :emptyString , tenant_mobile = :emptyString , tenant_amount = :emptyString , tenant_native = :emptyString" +
            ", tax_number = :emptyString , tax_amount = :emptyString , tax_year = :emptyString ,tax_date = :emptyString" +
            ", in_charge = :emptyString , in_charge_role = :emptyString , establishment_year = :emptyString , employee_count = :emptyString , license_number = :emptyString,gst_status = :emptyString ,  establishment_email = :emptyString,establishment_landline= :emptyString,establishment_mobile = :emptyString" +
            ",no_of_members=:emptyString, member_details = :emptyList" +
            ", ration_card = :emptyString ,ration_card_number = :emptyString,kwa_water=:emptyString,water_connection_type=:emptyString,water_supply_duration=:emptyString,lack_drinking_water=:emptyString,gas_connection=:emptyString,water_source_type=:emptyList,plastic_waste_management_type=:emptyList,liquid_waste_management_type=:emptyList,organic_waste_management_type=:emptyList,well_details=:emptyList,water_connection=:emptyList,other_source=:emptyList,any_other_facility=:emptyList, bank_account = :emptyString, cattles = :emptyString, paultry = :emptyString " +
            ",type_of_land=:emptyString,building_under_scheme=:emptyString,plot_area=:emptyString, no_vehicles=\"0\",vehicle_details=:emptyList,well_perennial=:emptyString,well_perennial_month=:emptyList,thozhilurapp=:emptyString,kudumbasree=:emptyString,health_insurance=:emptyString," +
            " survey_number = :emptyString,years_of_construction = :emptyString,total_years = :emptyString,other_building = :emptyString, no_of_rooms = :emptyString, no_of_floors = \"0\",floor_area = :emptyList " +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearDoorStatusNC(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "  establishment_name = :emptyString , establishment_type = :emptyString , in_charge = :emptyString , in_charge_role = :emptyString , establishment_year = :emptyString , employee_count = :emptyString , license_number = :emptyString " +
            ",is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0"+
            ", completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingSubTypeHQAV(String emptyString, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "religion =:emptyString, religion_cast = :emptyString , ration_card = :emptyString ,ration_card_number = :emptyString, bank_account = :emptyString, cattles = :emptyString, paultry = :emptyString " +
            ",no_of_members=:emptyString" +
            "" +
            "" +
            ", member_details = :emptyList," +
            "is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0,"+
            "completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingSubTypeNotHQAV(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("UPDATE property SET  " +
            "building_usage=:emptyString,latrine=:emptyString,toilet_waste_disposal=:emptyString,bathroom=:emptyString,air_conditioner=:emptyString,landmark=:emptyString,landmark_name=:emptyString,landmark_category=:emptyString,landmark_sub_category=:emptyString,u_id=:emptyString,near_property_number=:emptyString," +
            "tenant_name=:emptyString,tenant_house_name_number=:emptyString,tenant_street_place_name=:emptyString,tenant_survey_number=:emptyString,tenant_state=:emptyString,tenant_post_office=:emptyString,tenant_pincode=:emptyString,tenant_mobile=:emptyString,tenant_land_line=:emptyString,tenant_email=:emptyString,tenant_amount=:emptyString,tenant_native=:emptyString,tenant_status=:emptyString," +
            "establishment_name=:emptyString,establishment_type=:emptyString,establishment_year=:emptyString,in_charge=:emptyString,in_charge_role=:emptyString,employee_count=:emptyString,license_number=:emptyString,gst_status=:emptyString,establishment_email=:emptyString,establishment_landline=:emptyString,establishment_mobile=:emptyString," +
            "no_of_members=:emptyString, member_details = :emptyList," +
            "religion =:emptyString, religion_cast = :emptyString , ration_card = :emptyString ,ration_card_number = :emptyString,well_details=:emptyList,well_perennial=:emptyString,well_perennial_month=:emptyString,water_connection=:emptyList,water_connection_type=:emptyString,water_supply_duration=:emptyString,other_source=:emptyString,lack_drinking_water=:emptyString,gas_connection=:emptyString,rain_water_harvest=:emptyString,solar_panel=:emptyString,plastic_waste_management_type=:emptyList,liquid_waste_management_type=:emptyList,organic_waste_management_type=:emptyList,any_other_facility=:emptyList,pet=:emptyString,bank_account=:emptyString,paultry=:emptyString,no_of_poultry=:emptyString,cattles=:emptyString,no_of_cattles=:emptyString," +
            "type_of_land=:emptyString,building_under_scheme=:emptyString,no_vehicles=\"0\",vehicle_details=:emptyList,thozhilurapp=:emptyString,kudumbasree=:emptyString,health_insurance=:emptyString,building_name=:emptyString,colony_name=:emptyString,survey_number=:emptyString,car_porch=:emptyString,car_porch_area=:emptyString,common_stair=:emptyString,common_stair_area=:emptyString,stair=:emptyString,stair_area=:emptyString,pathway_area=:emptyString,is_any_extension=:emptyString,any_structural_change=:emptyString,structural_change_year=:emptyString,roof_type_change=:emptyString,roof_type_change_year=:emptyString,other_building=:emptyString,other_building_details=:emptyString," +
            "is_building_validation_ok=0,is_establishment_validation_ok=0,is_image_validation_ok=0,is_livehood_validation_ok=0,is_member_validation_ok=0,is_more_validation_ok=0,is_owner_validation_ok=0,is_road_validation_ok=0,is_tax_validation_ok=0,is_tenant_validation_ok=0,"+
            "completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void clearBuildingSubTypeRelatedBuilding(String emptyString, ArrayList emptyList, String surveyID, String propertyID);

    @Query("DELETE FROM property WHERE property_id = :propertyId")
    void deleteProperty(String propertyId);


    @Query("SELECT COUNT(property_id) FROM property  WHERE srvy_id = :surveyId")
    int getPropertyCount(String surveyId);

    @Query("SELECT COUNT(property_id) FROM property WHERE new_property_id = :newPropId AND property_id !=:propertyId")
    int getDuplicateNewPropertyId(String propertyId,String newPropId);


    @Query("SELECT COUNT(property_id) FROM property WHERE old_property_id = :oldPropertyId AND property_id !=:propertyId")
    int getDuplicateOldPropertyId(String propertyId,String oldPropertyId);

    @Query("UPDATE property SET completed_status = 0 WHERE srvy_id = :surveyID AND property_id = :propertyID")
    void resetPropertyCompletedStatus( String surveyID, String propertyID);

    @Query("SELECT COUNT(property_id) FROM property WHERE srvy_id = :surveyId AND sync_completed =1")
    int getSyncRowCount(String surveyId);

}
