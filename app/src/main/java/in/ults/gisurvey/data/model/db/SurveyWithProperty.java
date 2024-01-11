package in.ults.gisurvey.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.gisurvey.data.local.db.converter.BasementAreaConverter;
import in.ults.gisurvey.data.local.db.converter.FloorAreaConverter;
import in.ults.gisurvey.data.local.db.converter.MemberDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.RoofTypeConverter;
import in.ults.gisurvey.data.local.db.converter.StringListConverter;
import in.ults.gisurvey.data.local.db.converter.SurveyorDetailsConverter;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;

import static in.ults.gisurvey.utils.AppConstants.BUILDING_USAGE_RENTED;
import static in.ults.gisurvey.utils.AppConstants.DOOR_STATUS_NC;

public class SurveyWithProperty {

    @PrimaryKey
    @SerializedName("globalid")
    @ColumnInfo(name = "survey_id")
    public String surveyID;

    @SerializedName("ptstatus")
    @ColumnInfo(name = "point_status")
    public String pointStatus = "";

    @SerializedName("floor_count")
    @ColumnInfo(name = "floor_count")
    public int floorCount = -1;

    @SerializedName("property_count")
    @ColumnInfo(name = "property_count")
    public int propertyCount = -1;

    @SerializedName("grndfloor")
    @ColumnInfo(name = "ground_floor")
    public int groundFloor = -1;

    @SerializedName("noofbaseme")
    @ColumnInfo(name = "no_of_basements")
    public String noOfBasements = "";


    @SerializedName("basementDetails")
    @ColumnInfo(name = "basement_area")
    @TypeConverters(BasementAreaConverter.class)
    public ArrayList<BasementAreaItem> basementArea = new ArrayList<>();

    @SerializedName("district")
    @ColumnInfo(name = "district")
    public String district = "";

    @SerializedName("localbody")
    @ColumnInfo(name = "local_body")
    public String localBody = "";

    @SerializedName("wardno")
    @ColumnInfo(name = "ward_number")
    public String wardNumber = "";

    @SerializedName("wardname")
    @ColumnInfo(name = "ward_name")
    public String wardName = "";

    @SerializedName("street")
    @ColumnInfo(name = "street_name")
    public String streetName = "";

    @SerializedName("place")
    @ColumnInfo(name = "place_name")
    public String placeName = "";

    @SerializedName("village_name")
    @ColumnInfo(name = "village_name")
    public String villageName = "";

    @SerializedName("postoffice")
    @ColumnInfo(name = "post_office")
    public String postOffice = "";

    @SerializedName("location_pincode")
    @ColumnInfo(name = "pin_code")
    public String pinCode = "";

    @SerializedName("zone")
    @ColumnInfo(name = "building_zone")
    public String buildingZone = "";

    @SerializedName("property_id")
    @ColumnInfo(name = "property_id")
    public String propertyID;

    @SerializedName("latitude")
    @ColumnInfo(name = "latitude")
    public double latitude = 0;

    @SerializedName("longitude")
    @ColumnInfo(name = "longitude")
    public double longitude = 0;

    @SerializedName("surveyType")
    @ColumnInfo(name = "survey_type")
    public String surveyType = "";

    @SerializedName("bldgstatus")
    @ColumnInfo(name = "building_status")
    public String buildingStatus = "";

    @SerializedName("doorstatus")
    @ColumnInfo(name = "door_status")
    public String doorStatus = "";

    @SerializedName("bldgunder")
    @ColumnInfo(name = "building_under")
    public String buildingUnder = "";

    @SerializedName("bldgtype")
    @ColumnInfo(name = "building_type")
    public String buildingType = "";

    @SerializedName("bldgsubtyp")
    @ColumnInfo(name = "building_sub_type")
    public String buildingSubType = "";

    @SerializedName("establishmentUsage")
    @ColumnInfo(name = "establishment_usage")
    public String establishmentUsage = "";

    @SerializedName("bldgusage")
    @ColumnInfo(name = "building_usage")
    public String buildingUsage = "";

    @SerializedName("mainBldg")
    @ColumnInfo(name = "main_building")
    public String mainBuilding = "";

    @SerializedName("electricit")
    @ColumnInfo(name = "electricity")
    public String electricity = "";

    @SerializedName("consumerno")
    @ColumnInfo(name = "consumer_number")
    public String consumerNumber = "";

    @SerializedName("latrine")
    @ColumnInfo(name = "latrine")
    public String latrine = "";

    @SerializedName("toiletWasteDisposal")
    @ColumnInfo(name = "toilet_waste_disposal")
    public String toiletWasteDisposal = "";

    @SerializedName("airConditioner")
    @ColumnInfo(name = "air_conditioner")
    public String airConditioner = "";

    @SerializedName("landmark")
    @ColumnInfo(name = "landmark")
    public String landmark = "";

    @SerializedName("landmarkna")
    @ColumnInfo(name = "landmark_name")
    public String landmarkName = "";

    @SerializedName("landmarkca")
    @ColumnInfo(name = "landmark_category")
    public String landmarkCategory = "";

    @SerializedName("landmarksu")
    @ColumnInfo(name = "landmark_sub_category")
    public String landmarkSubCategory = "";

    @SerializedName("ownrshpOfEduBldg")
    @ColumnInfo(name = "ownership_education")
    public String ownershipEducation = "";

    @SerializedName("droneid")
    @ColumnInfo(name = "drone_id")
    public String droneId = "";

    @SerializedName("oldproid")
    @ColumnInfo(name = "old_property_id")
    public String oldPropertyId = "";

    @SerializedName("uniqueid")
    @ColumnInfo(name = "u_id")
    public String uId = "";

    @SerializedName("newproid")
    @ColumnInfo(name = "new_property_id")
    public String newPropertyId = "";

    @SerializedName("newProptyIDRmks")
    @ColumnInfo(name = "new_property_remarks")
    public String newPropertyRemarks = "";

    @SerializedName("nearprono")
    @ColumnInfo(name = "near_property_number")
    public String nearPropertyNumber = "";


    @SerializedName("ownername")
    @ColumnInfo(name = "owner_name")
    public String ownerName = "";

    @SerializedName("owneroccup")
    @ColumnInfo(name = "owner_occupation")
    public String ownerOccupation = "";

    @SerializedName("owner_house_name")
    @ColumnInfo(name = "owner_house_name_number")
    public String ownerHouseNameNumber = "";

    @SerializedName("owner_place")
    @ColumnInfo(name = "owner_street_place_name")
    public String ownerStreetPlaceName = "";

    @SerializedName("owner_state")
    @ColumnInfo(name = "owner_state")
    public String ownerState = "";

    @SerializedName("owner_postoffice")
    @ColumnInfo(name = "owner_post_office")
    public String ownerPostOffice = "";

    @SerializedName("owner_pincode")
    @ColumnInfo(name = "owner_pincode")
    public String ownerPincode = "";

    @SerializedName("owner_email")
    @ColumnInfo(name = "owner_email")
    public String ownerEmail = "";

    @SerializedName("ownerland")
    @ColumnInfo(name = "owner_land_line")
    public String ownerLandLine = "";

    @SerializedName("ownerphone")
    @ColumnInfo(name = "owner_mobile")
    public String ownerMobile = "";

    @SerializedName("teleCallNumber")
    @ColumnInfo(name = "tele_Call_number")
    public String teleCallNumber = "";

    @SerializedName("nearroad")
    @ColumnInfo(name = "near_road")
    public String nearRoad = "";

    @SerializedName("roadtype")
    @ColumnInfo(name = "road_type")
    public String roadType = "";

    @SerializedName("roadwidth")
    @ColumnInfo(name = "road_width")
    public String roadWidth = "";

    @SerializedName("tenentname")
    @ColumnInfo(name = "tenant_name")
    public String tenantName = "";

    @SerializedName("tenant_house_name")
    @ColumnInfo(name = "tenant_house_name_number")
    public String tenantHouseNameNumber = "";

    @SerializedName("tenant_street")
    @ColumnInfo(name = "tenant_street_place_name")
    public String tenantStreetPlaceName = "";

    @SerializedName("tentSrvyNum")
    @ColumnInfo(name = "tenant_survey_number")
    public String tenantSurveyNumber = "";

    @SerializedName("tenant_state")
    @ColumnInfo(name = "tenant_state")
    public String tenantState = "";

    @SerializedName("tenant_postoffice")
    @ColumnInfo(name = "tenant_post_office")
    public String tenantPostOffice = "";

    @SerializedName("tenant_pincode")
    @ColumnInfo(name = "tenant_pincode")
    public String tenantPincode = "";

    @SerializedName("tenentphon")
    @ColumnInfo(name = "tenant_mobile")
    public String tenantMobile = "";

    @SerializedName("tenentland")
    @ColumnInfo(name = "tenant_land_line")
    public String tenantLandLine = "";

    @SerializedName("tenentemai")
    @ColumnInfo(name = "tenant_email")
    public String tenantEmail = "";

    @SerializedName("rentamout")
    @ColumnInfo(name = "tenant_amount")
    public String tenantAmount = "";

    @SerializedName("tenentnati")
    @ColumnInfo(name = "tenant_native")
    public String tenantNative = "";

    @SerializedName("tentStatus")
    @ColumnInfo(name = "tenant_status")
    public String tenantStatus = "";

    @SerializedName("taxnumber")
    @ColumnInfo(name = "tax_number")
    public String taxNumber = "";

    @SerializedName("tax_amount")
    @ColumnInfo(name = "tax_amount")
    public String taxAmount = "";

    @SerializedName("tax_paid_date")
    @ColumnInfo(name = "tax_date")
    public String taxDate = "";

    @SerializedName("tax_paid_year")
    @ColumnInfo(name = "tax_year")
    public String taxYear = "";

    @SerializedName("annual_tax_amount")
    @ColumnInfo(name = "tax_annual_amount")
    public String taxAnnualAmount = "";

    @SerializedName("establishment_name")
    @ColumnInfo(name = "establishment_name")
    public String establishmentName = "";

    @SerializedName("establishment_type")
    @ColumnInfo(name = "establishment_type")
    public String establishmentType = "";

    @SerializedName("establishment_year")
    @ColumnInfo(name = "establishment_year")
    public String establishmentYear = "";

    @SerializedName("incharge")
    @ColumnInfo(name = "in_charge")
    public String inCharge = "";

    @SerializedName("inchargero")
    @ColumnInfo(name = "in_charge_role")
    public String inChargeRole = "";

    @SerializedName("noofemploy")
    @ColumnInfo(name = "employee_count")
    public String employeeCount = "";

    @SerializedName("licenceno")
    @ColumnInfo(name = "license_number")
    public String licenseNumber = "";

    @SerializedName("gstnumStatus")
    @ColumnInfo(name = "gst_status")
    public String gstStatus = "";

    @SerializedName("establishment_email")
    @ColumnInfo(name = "establishment_email")
    public String establishmentEmail = "";

    @SerializedName("establishment_landline")
    @ColumnInfo(name = "establishment_landline")
    public String establishmentLandline = "";

    @SerializedName("establishment_mobile")
    @ColumnInfo(name = "establishment_mobile")
    public String establishmentMobile = "";

    @SerializedName("noofmem")
    @ColumnInfo(name = "no_of_members")
    public String noOfMembers = "";

    @SerializedName("memberDetail")
    @ColumnInfo(name = "member_details")
    @TypeConverters(MemberDetailsConverter.class)
    public ArrayList<MemberDetailsItem> memberDetails = new ArrayList<>();

    @SerializedName("cast_name")
    @ColumnInfo(name = "religion_cast")
    public String religionCast = "";

    @SerializedName("religion")
    @ColumnInfo(name = "religion")
    public String religion = "";

    @SerializedName("rationcard")
    @ColumnInfo(name = "ration_card")
    public String rationCard = "";

    @SerializedName("rationca1")
    @ColumnInfo(name = "ration_card_number")
    public String rationCardNumber = "";

    @SerializedName("bathroom")
    @ColumnInfo(name = "bathroom")
    public String bathroom = "";

    @SerializedName("kwa_water")
    @ColumnInfo(name = "kwa_water")
    public String kwaWater = "";

    @SerializedName("typOfWaterCon")
    @ColumnInfo(name = "water_connection_type")
    public String waterConnectionType = "";

    @SerializedName("waterSupplyDuration")
    @ColumnInfo(name = "water_supply_duration")
    public String waterSupplyDuration = "";

    @SerializedName("lackOfDrinkingWater")
    @ColumnInfo(name = "lack_drinking_water")
    public String lackDrinkingWater = "";

    @SerializedName("gas_connection")
    @ColumnInfo(name = "gas_connection")
    public String gasConnection = "";

    @SerializedName("rain_water_harvest")
    @ColumnInfo(name = "rain_water_harvest")
    public String rainWaterHarvest = "";

    @SerializedName("solarPanel")
    @ColumnInfo(name = "solar_panel")
    public String solarPanel = "";

    @SerializedName("water_source_types")
    @ColumnInfo(name = "water_source_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> waterSourceType = new ArrayList<>();

    @SerializedName("well_details")
    @ColumnInfo(name = "well_details")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wellDetails = new ArrayList<>();

    @SerializedName("water_connection")
    @ColumnInfo(name = "water_connection")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> waterConnection = new ArrayList<>();

    @SerializedName("other_source")
    @ColumnInfo(name = "other_source")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> otherSource = new ArrayList<>();

    @SerializedName("plasticWasteManagementType")
    @ColumnInfo(name = "plastic_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> plasticWasteManagementType = new ArrayList<>();

    @SerializedName("liquidWasteManagementType")
    @ColumnInfo(name = "liquid_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> liquidWasteManagementType = new ArrayList<>();

    @SerializedName("organicWasteManagementType")
    @ColumnInfo(name = "organic_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> organicWasteManagementType = new ArrayList<>();

    @SerializedName("anyothrfaclty")
    @ColumnInfo(name = "any_other_facility")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> otherFacility = new ArrayList<>();

    @SerializedName("pets")
    @ColumnInfo(name = "pet")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> pets = new ArrayList<>();

    @SerializedName("accountno")
    @ColumnInfo(name = "bank_account")
    public String bankAccount = "";

    @SerializedName("paultry")
    @ColumnInfo(name = "paultry")
    public String paultry = "";

    @SerializedName("no_of_poultry")
    @ColumnInfo(name = "no_of_poultry")
    public String noOfPoultry = "";

    @SerializedName("cattles")
    @ColumnInfo(name = "cattles")
    public String cattles = "";

    @SerializedName("no_of_cattles")
    @ColumnInfo(name = "no_of_cattles")
    public String noOfCattles = "";

    @SerializedName("memCount")
    @ColumnInfo(name = "mem_count")
    public String memCount = "";

    @SerializedName("swimmingPool")
    @ColumnInfo(name = "swimming_pool")
    public String swimmingPool = "";

    @SerializedName("swimmingPoolArea")
    @ColumnInfo(name = "swimming_pool_area")
    public String swimmingPoolArea = "";

    @SerializedName("typeOfLand")
    @ColumnInfo(name = "type_of_land")
    public String typeOfLand = "";

    @SerializedName("bldUndrAnySchm")
    @ColumnInfo(name = "building_under_scheme")
    public String buildingUnderScheme = "";

    @SerializedName("plot_area")
    @ColumnInfo(name = "plot_area")
    public String plotArea = "";

    @SerializedName("vehicleCount")
    @ColumnInfo(name = "no_vehicles")
    public String noOfVehicle = "-1";

    @SerializedName("vehicle")
    @ColumnInfo(name = "vehicle_details")
    @TypeConverters(StringListConverter.class)
    public ArrayList<VehicleDetailsItem> vehicleDetails = new ArrayList<>();

    @SerializedName("well_perennial")
    @ColumnInfo(name = "well_perennial")
    public String wellPerennial = "";

    @SerializedName("well_perennial_month")
    @ColumnInfo(name = "well_perennial_month")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wellPerennialMonth = new ArrayList<>();

    @SerializedName("thozhilurappu")
    @ColumnInfo(name = "thozhilurapp")
    public String thozhilurapp = "";

    @SerializedName("kudumbasree")
    @ColumnInfo(name = "kudumbasree")
    public String kudumbasree = "";

    @SerializedName("health_insurance")
    @ColumnInfo(name = "health_insurance")
    public String healthInsurance = "";

    @SerializedName("bldgname")
    @ColumnInfo(name = "building_name")
    public String buildingName = "";

    @SerializedName("resiAssctNameAndNum")
    @ColumnInfo(name = "colony_name")
    public String colony_name = "";

    @SerializedName("surveyno")
    @ColumnInfo(name = "survey_number")
    public String surveyNumber = "";

    @SerializedName("yearofcons")
    @ColumnInfo(name = "years_of_construction")
    public String yearsOfConstruction = "";

    @SerializedName("noofyears")
    @ColumnInfo(name = "total_years")
    public String totalYears = "";

    @SerializedName("noofrooms")
    @ColumnInfo(name = "no_of_rooms")
    public String noOfRooms = "";

    @SerializedName("nooffloors")
    @ColumnInfo(name = "no_of_floors")
    public String noOfFloors = "-1";

    @SerializedName("floorDetails")
    @ColumnInfo(name = "floor_area")
    @TypeConverters(FloorAreaConverter.class)
    public ArrayList<BuildingDetailsFloorAreaItem> floorArea = new ArrayList<>();

    @SerializedName("areaRemarks")
    @ColumnInfo(name = "area_remarks")
    public String areaRemarks = "";

    @SerializedName("structure_type")
    @ColumnInfo(name = "structure_type")
    public String structureType = "";

    @SerializedName("car_porch")
    @ColumnInfo(name = "car_porch")
    public String carPorch = "";

    @SerializedName("car_porch_area")
    @ColumnInfo(name = "car_porch_area")
    public String carPorchArea = "";

    @SerializedName("common_stair")
    @ColumnInfo(name = "common_stair")
    public String commonStair = "";

    @SerializedName("common_stair_area")
    @ColumnInfo(name = "common_stair_area")
    public String commonStairArea = "";

    @SerializedName("pathwayArea")
    @ColumnInfo(name = "pathway_area")
    public String pathwayArea = "";

    @SerializedName("anyStructChangeDone")
    @ColumnInfo(name = "any_structural_change")
    public String anyStructuralChange = "";

    @SerializedName("structYrOfChange")
    @ColumnInfo(name = "structural_change_year")
    public String anyStructuralChangeYear = "";

    @SerializedName("anyChangeDoneToRofTyp")
    @ColumnInfo(name = "roof_type_change")
    public String anyRoofChange = "";

    @SerializedName("roofYrOfChange")
    @ColumnInfo(name = "roof_type_change_year")
    public String anyRoofChangeYear = "";

    @SerializedName("otherbldg")
    @ColumnInfo(name = "other_building")
    public String otherBuilding = "";

    @SerializedName("othrBldProptyDetls")
    @ColumnInfo(name = "other_building_details")
    public String otherBuildingDetails = "";

    @SerializedName("floortype")
    @ColumnInfo(name = "floor_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> floorType = new ArrayList<>();

    @SerializedName("floorsqmtr")
    @ColumnInfo(name = "higher_floor_sqft")
    public String higherFloorSqft = "";

    @SerializedName("wall_type")
    @ColumnInfo(name = "wall_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wallType = new ArrayList<>();

    @SerializedName("rooftotper")
    @ColumnInfo(name = "roof_total")
    public String roofTotal = "";

    @SerializedName("norftypes")
    @ColumnInfo(name = "no_of_roof_type")
    public String noOfRoofType = "-1";


    @SerializedName("roofType")
    @ColumnInfo(name = "roof_type_details")
    @TypeConverters(RoofTypeConverter.class)
    public ArrayList<BuildingDetailsRoofItem> roofDetails = new ArrayList<>();

    @SerializedName("propertyImage1")
    @ColumnInfo(name = "property_image_one")
    public String propertyImageOne = "";

    @SerializedName("propertyImage2")
    @ColumnInfo(name = "property_image_two")
    public String propertyImageTwo = "";

    @SerializedName("planImage")
    @ColumnInfo(name = "property_image_three")
    public String propertyImageThree = "";

    @SerializedName("informedby")
    @ColumnInfo(name = "informed_by")
    public String informedBy = "";

    @SerializedName("coWithSrvy")
    @ColumnInfo(name = "cooperate_survey")
    public String cooperativeSurvey = "";

    @SerializedName("surveyorName")
    @ColumnInfo(name = "surveyor_name")
    public String surveyorName = "";

    @SerializedName("common_remarks")
    @ColumnInfo(name = "common_remarks")
    public String commonRemarks = "";

    @SerializedName("version")
    @ColumnInfo(name = "version")
    public String version = "";

    @SerializedName("srvyStrtDate")
    @ColumnInfo(name = "survey_start_date")
    public String surveyStartDate = "";

    @SerializedName("propStrtDate")
    @ColumnInfo(name = "property_start_date")
    public String propertyStartDate = "";

    @SerializedName("propEndDate")
    @ColumnInfo(name = "property_end_date")
    public String propertyEndDate = "";

    @SerializedName("floodAffected")
    @ColumnInfo(name = "fld_effctd")
    public String floodAffected = "";

    @SerializedName("waterLevelHit")
    @ColumnInfo(name = "wtr_lvlhit")
    public String waterLevelHit = "";

    @SerializedName("completedStatus")
    @ColumnInfo(name = "completed_status")
    public boolean completedStatus = false;

    @SerializedName("syncCompleted")
    @ColumnInfo(name = "sync_completed")
    public boolean syncCompleted = false;

    @SerializedName("surveyCompletedStatus")
    @ColumnInfo(name = "survey_completed_status")
    public boolean surveyCompletedStatus = false;

    @SerializedName("anyExtension")
    @ColumnInfo(name = "is_any_extension")
    public String anyExtension = "";

    @SerializedName("surveyCompletedDate")
    @ColumnInfo(name = "survey_completed_date")
    public String surveyCompletedDate = "";

    @SerializedName("arRemarks")
    @ColumnInfo(name = "ar_remarks")
    public String arRemarks = "";

    @SerializedName("arOwnerAddress")
    @ColumnInfo(name = "ar_owner_address")
    public String arOwnerAddress = "";

    @SerializedName("syncRemarks")
    @ColumnInfo(name = "sync_remark")
    public String syncRemarks = "";

    @SerializedName("arNewPropId")
    @ColumnInfo(name = "ar_new_prop_id")
    public String arNewPropId = "";

    @SerializedName("arOldPropId")
    @ColumnInfo(name = "ar_old_prop_id")
    public String arOldPropId = "";

    @SerializedName("arYearOfConstruction")
    @ColumnInfo(name = "ar_year_of_construction")
    public String arYearOfConstruction = "";

    @SerializedName("arArea")
    @ColumnInfo(name = "ar_area")
    public String arArea = "";

    @SerializedName("newproid_arremarks")
    @ColumnInfo(name = "newproid_ar_remarks")
    public String newpropidArRemarks = "";

    @SerializedName("oldproid_ar_remarks")
    @ColumnInfo(name = "oldproid_ar_remarks")
    public String oldpropidArRemarks = "";

    @SerializedName("newForm3Employee")
    @ColumnInfo(name = "surveyor_details")
    @TypeConverters(SurveyorDetailsConverter.class)
    public ArrayList<Surveyor> surveyorDetails = new ArrayList<>();

    @SerializedName("app_version_details")
    @ColumnInfo(name = "app_version_details")
    public String appVersionDetails = "";

    @SerializedName("syncCompletedDate")
    @ColumnInfo(name = "sync_completed_date")
    public String syncCompletedDate = "";

    @SerializedName("arZone")
    @ColumnInfo(name = "ar_zone")
    public String arZone = "";

    @SerializedName("arAC")
    @ColumnInfo(name = "ar_ac")
    public String arAC = "";

    @SerializedName("arFloorArea")
    @ColumnInfo(name = "ar_floor_area")
    public String arFloorArea = "";


    @SerializedName("arBuildingUsage")
    @ColumnInfo(name = "ar_building_usage")
    public String arBuildingUsage = "";

    @SerializedName("arRoadType")
    @ColumnInfo(name = "ar_road_type")
    public String arRoadType = "";

    @SerializedName("arRoadName")
    @ColumnInfo(name = "ar_road_name")
    public String arRoadName = "";

    @SerializedName("arBuildingAge")
    @ColumnInfo(name = "ar_building_age")
    public String arBuildingAge = "";

    @SerializedName("arRoofDetails")
    @ColumnInfo(name = "ar_roof_details")
    public String arRoofDetails = "";

    @SerializedName("arFloorDetails")
    @ColumnInfo(name = "ar_floor_details")
    public String arFloorDetails = "";

    @SerializedName("arModification")
    @ColumnInfo(name = "ar_modification")
    public String arModification = "";

    @SerializedName("arOccupierDetails")
    @ColumnInfo(name = "ar_occupier_details")
    public String arOccupierDetails = "";

    @SerializedName("arTaxTotal")
    @ColumnInfo(name = "ar_tax_total")
    public String arTaxToatal = "";

    @SerializedName("surveyIdWardNumber")
    @ColumnInfo(name = "survey_id_ward_number")
    public String surveyIdWardNumber = "";

    @SerializedName("extra_remarks")
    @ColumnInfo(name = "extra_remarks")
    public String extraRemarks = "";

    @SerializedName("stair")
    @ColumnInfo(name = "stair")
    public String stair = "";

    @SerializedName("stair_area")
    @ColumnInfo(name = "stair_area")
    public String stairArea = "";

    @SerializedName("isBuildingValidationOk")
    @ColumnInfo(name = "is_building_validation_ok")
    public boolean isBuildingValidationOk = false;

    @SerializedName("isEstablishmentValidationOk")
    @ColumnInfo(name = "is_establishment_validation_ok")
    public boolean isEstablishmentValidationOk = false;

    @SerializedName("isImageValidationOk")
    @ColumnInfo(name = "is_image_validation_ok")
    public boolean isImageValidationOk = false;

    @SerializedName("isLivehoodValidationOk")
    @ColumnInfo(name = "is_livehood_validation_ok")
    public boolean isLivehoodValidationOk = false;

    @SerializedName("isMemberValidationOk")
    @ColumnInfo(name = "is_member_validation_ok")
    public boolean isMemberValidationOk = false;

    @SerializedName("isMoreValidationOk")
    @ColumnInfo(name = "is_more_validation_ok")
    public boolean isMoreValidationOk = false;

    @SerializedName("isOwnerValidationOk")
    @ColumnInfo(name = "is_owner_validation_ok")
    public boolean isOwnerValidationOk = false;

    @SerializedName("isRoadValidationOk")
    @ColumnInfo(name = "is_road_validation_ok")
    public boolean isRoadValidationOk = false;

    @SerializedName("isTaxValidationOk")
    @ColumnInfo(name = "is_tax_validation_ok")
    public boolean isTaxValidationOk = false;

    @SerializedName("isTenantValidationOk")
    @ColumnInfo(name = "is_tenant_validation_ok")
    public boolean isTenantValidationOk = false;



    public SurveyWithProperty(String surveyID) {
        this.surveyID = surveyID;
    }

    public SurveyWithProperty() {
    }

    public String getSurveyID() {
        return surveyID;
    }

    public String getPointStatus() {
        return pointStatus;
    }

    public String getPropertyID() {
        return propertyID;
    }

    //NR = -1
    //NA = -2

    public void addNA() {
        if (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)||
                doorStatus.equalsIgnoreCase(DOOR_STATUS_NC)) {

            toiletWasteDisposal = CommonUtils.setDefaultStringValue(toiletWasteDisposal,AppConstants.NA_UPPERCASE);
            cooperativeSurvey = CommonUtils.setDefaultStringValue(cooperativeSurvey,AppConstants.NA_UPPERCASE);

            latrine = CommonUtils.setDefaultStringValue(latrine,AppConstants.NR_UPPERCASE);
            airConditioner = CommonUtils.setDefaultStringValue(airConditioner,AppConstants.NR_UPPERCASE);
            taxNumber = CommonUtils.setDefaultStringValue(taxNumber,AppConstants.NR_UPPERCASE);
            taxAmount = CommonUtils.setDefaultStringValue(taxAmount,AppConstants.NR_UPPERCASE);
            taxYear = CommonUtils.setDefaultStringValue(taxYear,AppConstants.NR_UPPERCASE);
            taxDate = CommonUtils.setDefaultStringValue(taxDate,AppConstants.NR_UPPERCASE);
            taxAnnualAmount = CommonUtils.setDefaultStringValue(taxAnnualAmount,AppConstants.NR_UPPERCASE);
            noOfMembers = CommonUtils.setDefaultStringValue(noOfMembers,AppConstants.NR_UPPERCASE);
            kwaWater = CommonUtils.setDefaultStringValue(kwaWater,AppConstants.NR_UPPERCASE);
            waterConnectionType = CommonUtils.setDefaultStringValue(waterConnectionType,AppConstants.NR_UPPERCASE);
            waterSupplyDuration = CommonUtils.setDefaultStringValue(waterSupplyDuration,AppConstants.NR_UPPERCASE);
            rainWaterHarvest = CommonUtils.setDefaultStringValue(rainWaterHarvest,AppConstants.NR_UPPERCASE);
            solarPanel = CommonUtils.setDefaultStringValue(solarPanel,AppConstants.NR_UPPERCASE);
            surveyNumber = CommonUtils.setDefaultStringValue(surveyNumber,AppConstants.NR_UPPERCASE);
            yearsOfConstruction = CommonUtils.setDefaultStringValue(yearsOfConstruction,AppConstants.NR_UPPERCASE);
            totalYears = CommonUtils.setDefaultStringValue(totalYears,AppConstants.NR_UPPERCASE);
            noOfRooms = CommonUtils.setDefaultStringValue(noOfRooms,AppConstants.NR_UPPERCASE);
            anyStructuralChange = CommonUtils.setDefaultStringValue(anyStructuralChange,AppConstants.NR_UPPERCASE);
            anyStructuralChangeYear = CommonUtils.setDefaultStringValue(anyStructuralChangeYear,AppConstants.NR_UPPERCASE);
            anyRoofChange = CommonUtils.setDefaultStringValue(anyRoofChange,AppConstants.NR_UPPERCASE);
            anyRoofChangeYear = CommonUtils.setDefaultStringValue(anyRoofChangeYear,AppConstants.NR_UPPERCASE);
            otherBuilding = CommonUtils.setDefaultStringValue(otherBuilding,AppConstants.NR_UPPERCASE);
            higherFloorSqft = CommonUtils.setDefaultStringValue(higherFloorSqft,AppConstants.NR_UPPERCASE);
            otherBuildingDetails = CommonUtils.setDefaultStringValue(otherBuildingDetails,AppConstants.NR_UPPERCASE);
            if(doorStatus.equalsIgnoreCase(DOOR_STATUS_NC)&&buildingUsage.equalsIgnoreCase(BUILDING_USAGE_RENTED)){
                tenantName = CommonUtils.setDefaultStringValue(tenantName,AppConstants.NR_UPPERCASE);
                tenantHouseNameNumber = CommonUtils.setDefaultStringValue(tenantHouseNameNumber,AppConstants.NR_UPPERCASE);
                tenantStreetPlaceName = CommonUtils.setDefaultStringValue(tenantStreetPlaceName,AppConstants.NR_UPPERCASE);
                tenantState = CommonUtils.setDefaultStringValue(tenantState,AppConstants.NR_UPPERCASE);
                tenantPostOffice = CommonUtils.setDefaultStringValue(tenantPostOffice,AppConstants.NR_UPPERCASE);
                tenantPincode = CommonUtils.setDefaultStringValue(tenantPincode,AppConstants.NR_UPPERCASE);
                tenantLandLine = CommonUtils.setDefaultStringValue(tenantLandLine,AppConstants.NR_UPPERCASE);
                tenantEmail = CommonUtils.setDefaultStringValue(tenantEmail,AppConstants.NR_UPPERCASE);
                tenantMobile = CommonUtils.setDefaultStringValue(tenantMobile,AppConstants.NR_UPPERCASE);
                tenantAmount = CommonUtils.setDefaultStringValue(tenantAmount,AppConstants.NR_UPPERCASE);
                tenantNative = CommonUtils.setDefaultStringValue(tenantNative,AppConstants.NR_UPPERCASE);
            }

            if (plasticWasteManagementType != null && plasticWasteManagementType.size() == 0) {
                plasticWasteManagementType.add(AppConstants.NR_UPPERCASE);
            }
            if (liquidWasteManagementType != null && liquidWasteManagementType.size() == 0) {
                liquidWasteManagementType.add(AppConstants.NR_UPPERCASE);
            }
            if (organicWasteManagementType != null && organicWasteManagementType.size() == 0) {
                organicWasteManagementType.add(AppConstants.NR_UPPERCASE);
            }
            if (otherFacility != null && otherFacility.size() == 0) {
                otherFacility.add(AppConstants.NR_UPPERCASE);
            }
            if (floorType != null && floorType.size() == 0) {
                floorType.add(AppConstants.NR_UPPERCASE);
            }
            if (wallType != null && wallType.size() == 0) {
                wallType.add(AppConstants.NR_UPPERCASE);
            }


            if (establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_HOUSE)
                    ||establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE)
                    ||establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE)
                    || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS)
                    || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT)
                    || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA)) {
                religion = CommonUtils.setDefaultStringValue(religion,AppConstants.NR_UPPERCASE);
                religionCast = CommonUtils.setDefaultStringValue(religionCast,AppConstants.NR_UPPERCASE);
                rationCard = CommonUtils.setDefaultStringValue(rationCard,AppConstants.NR_UPPERCASE);
                buildingUnderScheme = CommonUtils.setDefaultStringValue(buildingUnderScheme,AppConstants.NR_UPPERCASE);
                plotArea = CommonUtils.setDefaultStringValue(plotArea,AppConstants.NR_UPPERCASE);
                rationCardNumber = CommonUtils.setDefaultStringValue(rationCardNumber,AppConstants.NR_UPPERCASE);
                bathroom = CommonUtils.setDefaultStringValue(bathroom,AppConstants.NR_UPPERCASE);
                lackDrinkingWater = CommonUtils.setDefaultStringValue(lackDrinkingWater,AppConstants.NR_UPPERCASE);
                gasConnection = CommonUtils.setDefaultStringValue(gasConnection,AppConstants.NR_UPPERCASE);
                wellPerennial = CommonUtils.setDefaultStringValue(wellPerennial,AppConstants.NR_UPPERCASE);
                bankAccount = CommonUtils.setDefaultStringValue(bankAccount,AppConstants.NR_UPPERCASE);
                cattles = CommonUtils.setDefaultStringValue(cattles,AppConstants.NR_UPPERCASE);
                noOfCattles = CommonUtils.setDefaultStringValue(noOfCattles,AppConstants.NR_UPPERCASE);
                memCount = CommonUtils.setDefaultStringValue(memCount,AppConstants.NR_UPPERCASE);
                swimmingPool = CommonUtils.setDefaultStringValue(swimmingPool,AppConstants.NR_UPPERCASE);
                swimmingPoolArea = CommonUtils.setDefaultStringValue(swimmingPoolArea,AppConstants.NR_UPPERCASE);
                paultry = CommonUtils.setDefaultStringValue(paultry,AppConstants.NR_UPPERCASE);
                noOfPoultry = CommonUtils.setDefaultStringValue(noOfPoultry,AppConstants.NR_UPPERCASE);
                noOfVehicle = CommonUtils.setDefaultIntegerStringVale(noOfVehicle,AppConstants.NR_INTEGER_STRING);
                thozhilurapp = CommonUtils.setDefaultStringValue(thozhilurapp,AppConstants.NR_UPPERCASE);
                kudumbasree = CommonUtils.setDefaultStringValue(kudumbasree,AppConstants.NR_UPPERCASE);
                healthInsurance = CommonUtils.setDefaultStringValue(healthInsurance,AppConstants.NR_UPPERCASE);
                colony_name = CommonUtils.setDefaultStringValue(colony_name,AppConstants.NR_UPPERCASE);
                if (wellPerennialMonth != null && wellPerennialMonth.size() == 0) {
                    wellPerennialMonth.add(AppConstants.NR_UPPERCASE);
                }
                if (pets != null && pets.size() == 0) {
                    pets.add(AppConstants.NR_UPPERCASE);
                }
                if (waterSourceType != null && waterSourceType.size() == 0) {
                    waterSourceType.add(AppConstants.NR_UPPERCASE);
                }
                if (wellDetails != null && wellDetails.size() == 0) {
                    wellDetails.add(AppConstants.NR_UPPERCASE);
                }
                if (waterConnection != null && waterConnection.size() == 0) {
                    waterConnection.add(AppConstants.NR_UPPERCASE);
                }
                if (otherSource != null && otherSource.size() == 0) {
                    otherSource.add(AppConstants.NR_UPPERCASE);
                }
            } else {
                establishmentYear = CommonUtils.setDefaultStringValue(establishmentYear,AppConstants.NR_UPPERCASE);
                inCharge = CommonUtils.setDefaultStringValue(inCharge,AppConstants.NR_UPPERCASE);
                inChargeRole = CommonUtils.setDefaultStringValue(inChargeRole,AppConstants.NR_UPPERCASE);
                employeeCount = CommonUtils.setDefaultStringValue(employeeCount,AppConstants.NR_UPPERCASE);
                licenseNumber = CommonUtils.setDefaultStringValue(licenseNumber,AppConstants.NR_UPPERCASE);
                gstStatus = CommonUtils.setDefaultStringValue(gstStatus,AppConstants.NR_UPPERCASE);
                establishmentEmail = CommonUtils.setDefaultStringValue(establishmentEmail,AppConstants.NR_UPPERCASE);
                establishmentLandline = CommonUtils.setDefaultStringValue(establishmentLandline,AppConstants.NR_UPPERCASE);
                establishmentMobile = CommonUtils.setDefaultStringValue(establishmentMobile,AppConstants.NR_UPPERCASE);
            }
        }
        anyExtension=CommonUtils.setDefaultStringValue(anyExtension,AppConstants.NA_UPPERCASE);
        waterLevelHit=CommonUtils.setDefaultStringValue(waterLevelHit,AppConstants.NA_UPPERCASE);
        surveyType = CommonUtils.setDefaultStringValue(surveyType,AppConstants.NA_UPPERCASE);
        buildingStatus = CommonUtils.setDefaultStringValue(buildingStatus,AppConstants.NA_UPPERCASE);
        oldPropertyId = CommonUtils.setDefaultStringValue(oldPropertyId,AppConstants.NA_UPPERCASE);
        uId = CommonUtils.setDefaultStringValue(uId,AppConstants.NA_UPPERCASE);
        newPropertyId = CommonUtils.setDefaultStringValue(newPropertyId,AppConstants.NA_UPPERCASE);
        nearPropertyNumber = CommonUtils.setDefaultStringValue(nearPropertyNumber,AppConstants.NA_UPPERCASE);
        newPropertyRemarks = CommonUtils.setDefaultStringValue(newPropertyRemarks,AppConstants.NA_UPPERCASE);
        landmark = CommonUtils.setDefaultStringValue(landmark,AppConstants.NA_UPPERCASE);
        landmarkName = CommonUtils.setDefaultStringValue(landmarkName,AppConstants.NA_UPPERCASE);
        landmarkCategory = CommonUtils.setDefaultStringValue(landmarkCategory,AppConstants.NA_UPPERCASE);
        landmarkSubCategory = CommonUtils.setDefaultStringValue(landmarkSubCategory,AppConstants.NA_UPPERCASE);
        buildingUsage = CommonUtils.setDefaultStringValue(buildingUsage,AppConstants.NA_UPPERCASE);
        doorStatus = CommonUtils.setDefaultStringValue(doorStatus,AppConstants.NA_UPPERCASE);
        buildingUnder = CommonUtils.setDefaultStringValue(buildingUnder,AppConstants.NA_UPPERCASE);
        buildingType = CommonUtils.setDefaultStringValue(buildingType,AppConstants.NA_UPPERCASE);
        buildingSubType = CommonUtils.setDefaultStringValue(buildingSubType,AppConstants.NA_UPPERCASE);
        establishmentUsage = CommonUtils.setDefaultStringValue(establishmentUsage,AppConstants.NA_UPPERCASE);
        electricity = CommonUtils.setDefaultStringValue(electricity,AppConstants.NA_UPPERCASE);
        consumerNumber = CommonUtils.setDefaultStringValue(consumerNumber,AppConstants.NA_UPPERCASE);
        ownerName = CommonUtils.setDefaultStringValue(ownerName,AppConstants.NA_UPPERCASE);
        ownerOccupation = CommonUtils.setDefaultStringValue(ownerOccupation,AppConstants.NA_UPPERCASE);
        ownerHouseNameNumber = CommonUtils.setDefaultStringValue(ownerHouseNameNumber,AppConstants.NA_UPPERCASE);
        ownerStreetPlaceName = CommonUtils.setDefaultStringValue(ownerStreetPlaceName,AppConstants.NA_UPPERCASE);
        ownerState = CommonUtils.setDefaultStringValue(ownerState,AppConstants.NA_UPPERCASE);
        ownerPostOffice = CommonUtils.setDefaultStringValue(ownerPostOffice,AppConstants.NA_UPPERCASE);
        ownerPincode = CommonUtils.setDefaultStringValue(ownerPincode,AppConstants.NA_UPPERCASE);
        ownerEmail = CommonUtils.setDefaultStringValue(ownerEmail,AppConstants.NA_UPPERCASE);
        ownerLandLine = CommonUtils.setDefaultStringValue(ownerLandLine,AppConstants.NA_UPPERCASE);
        ownerMobile = CommonUtils.setDefaultStringValue(ownerMobile,AppConstants.NA_UPPERCASE);
        teleCallNumber = CommonUtils.setDefaultStringValue(teleCallNumber,AppConstants.NA_UPPERCASE);
        nearRoad = CommonUtils.setDefaultStringValue(nearRoad,AppConstants.NA_UPPERCASE);
        roadType = CommonUtils.setDefaultStringValue(roadType,AppConstants.NA_UPPERCASE);
        roadWidth = CommonUtils.setDefaultStringValue(roadWidth,AppConstants.NA_UPPERCASE);
        establishmentName = CommonUtils.setDefaultStringValue(establishmentName,AppConstants.NA_UPPERCASE);
        establishmentType = CommonUtils.setDefaultStringValue(establishmentType,AppConstants.NA_UPPERCASE);
        tenantName = CommonUtils.setDefaultStringValue(tenantName,AppConstants.NA_UPPERCASE);
        tenantHouseNameNumber = CommonUtils.setDefaultStringValue(tenantHouseNameNumber,AppConstants.NA_UPPERCASE);
        tenantStreetPlaceName = CommonUtils.setDefaultStringValue(tenantStreetPlaceName,AppConstants.NA_UPPERCASE);
        tenantState = CommonUtils.setDefaultStringValue(tenantState,AppConstants.NA_UPPERCASE);
        tenantPostOffice = CommonUtils.setDefaultStringValue(tenantPostOffice,AppConstants.NA_UPPERCASE);
        tenantPincode = CommonUtils.setDefaultStringValue(tenantPincode,AppConstants.NA_UPPERCASE);
        tenantLandLine = CommonUtils.setDefaultStringValue(tenantLandLine,AppConstants.NA_UPPERCASE);
        tenantEmail = CommonUtils.setDefaultStringValue(tenantEmail,AppConstants.NA_UPPERCASE);
        tenantMobile = CommonUtils.setDefaultStringValue(tenantMobile,AppConstants.NA_UPPERCASE);
        tenantAmount = CommonUtils.setDefaultStringValue(tenantAmount,AppConstants.NA_UPPERCASE);
        tenantNative = CommonUtils.setDefaultStringValue(tenantNative,AppConstants.NA_UPPERCASE);
        buildingName = CommonUtils.setDefaultStringValue(buildingName,AppConstants.NA_UPPERCASE);
        noOfFloors = CommonUtils.setDefaultIntegerStringVale(noOfFloors,AppConstants.NA_INTEGER_STRING);
        structureType = CommonUtils.setDefaultStringValue(structureType,AppConstants.NA_UPPERCASE);
        carPorch = CommonUtils.setDefaultStringValue(carPorch,AppConstants.NA_UPPERCASE);
        carPorchArea = CommonUtils.setDefaultStringValue(carPorchArea,AppConstants.NA_UPPERCASE);
        commonStair = CommonUtils.setDefaultStringValue(commonStair,AppConstants.NA_UPPERCASE);
        commonStairArea = CommonUtils.setDefaultStringValue(commonStairArea,AppConstants.NA_UPPERCASE);
        stair = CommonUtils.setDefaultStringValue(stair,AppConstants.NA_UPPERCASE);
        stairArea = CommonUtils.setDefaultStringValue(stairArea,AppConstants.NA_UPPERCASE);
        noOfRoofType = CommonUtils.setDefaultIntegerStringVale(noOfRoofType,AppConstants.NA_INTEGER_STRING);
        roofTotal = CommonUtils.setDefaultStringValue(roofTotal,AppConstants.NA_UPPERCASE);
        propertyImageOne = CommonUtils.setDefaultStringValue(propertyImageOne,AppConstants.NA_UPPERCASE);
        propertyImageTwo = CommonUtils.setDefaultStringValue(propertyImageTwo,AppConstants.NA_UPPERCASE);
        propertyImageThree= CommonUtils.setDefaultStringValue(propertyImageThree,AppConstants.NA_UPPERCASE);
        commonRemarks = CommonUtils.setDefaultStringValue(commonRemarks,AppConstants.NA_UPPERCASE);
        informedBy = CommonUtils.setDefaultStringValue(informedBy,AppConstants.NA_UPPERCASE);
        version = CommonUtils.setDefaultStringValue(version,AppConstants.NA_UPPERCASE);
        ownershipEducation = CommonUtils.setDefaultStringValue(ownershipEducation,AppConstants.NA_UPPERCASE);
        mainBuilding = CommonUtils.setDefaultStringValue(mainBuilding,AppConstants.NA_UPPERCASE);
        tenantSurveyNumber = CommonUtils.setDefaultStringValue(tenantSurveyNumber,AppConstants.NA_UPPERCASE);
        tenantStatus = CommonUtils.setDefaultStringValue(tenantStatus,AppConstants.NA_UPPERCASE);
        typeOfLand = CommonUtils.setDefaultStringValue(typeOfLand,AppConstants.NA_UPPERCASE);
        pathwayArea = CommonUtils.setDefaultStringValue(pathwayArea,AppConstants.NA_UPPERCASE);
        surveyorName = CommonUtils.setDefaultStringValue(surveyorName,AppConstants.NA_UPPERCASE);
        pinCode = CommonUtils.setDefaultStringValue(pinCode,AppConstants.NA_UPPERCASE);
        postOffice = CommonUtils.setDefaultStringValue(postOffice,AppConstants.NA_UPPERCASE);
        streetName = CommonUtils.setDefaultStringValue(streetName,AppConstants.NA_UPPERCASE);
        noOfBasements = CommonUtils.setDefaultStringValue(noOfBasements,AppConstants.NA_UPPERCASE);
        villageName = CommonUtils.setDefaultStringValue(villageName,AppConstants.NA_UPPERCASE);
        buildingZone = CommonUtils.setDefaultStringValue(buildingZone,AppConstants.NA_UPPERCASE);
        floorCount = CommonUtils.setDefaultIntegerVale(floorCount,AppConstants.NA_INTEGER);
        propertyCount = CommonUtils.setDefaultIntegerVale(propertyCount,AppConstants.NA_INTEGER);
        groundFloor = CommonUtils.setDefaultIntegerVale(groundFloor,AppConstants.NA_INTEGER);


        toiletWasteDisposal = CommonUtils.setDefaultStringValue(toiletWasteDisposal,AppConstants.NA_UPPERCASE);
        cooperativeSurvey = CommonUtils.setDefaultStringValue(cooperativeSurvey,AppConstants.NA_UPPERCASE);
        latrine = CommonUtils.setDefaultStringValue(latrine,AppConstants.NA_UPPERCASE);
        airConditioner = CommonUtils.setDefaultStringValue(airConditioner,AppConstants.NA_UPPERCASE);
        taxNumber = CommonUtils.setDefaultStringValue(taxNumber,AppConstants.NA_UPPERCASE);
        taxAmount = CommonUtils.setDefaultStringValue(taxAmount,AppConstants.NA_UPPERCASE);
        taxYear = CommonUtils.setDefaultStringValue(taxYear,AppConstants.NA_UPPERCASE);
        taxDate = CommonUtils.setDefaultStringValue(taxDate,AppConstants.NA_UPPERCASE);
        taxAnnualAmount = CommonUtils.setDefaultStringValue(taxAnnualAmount,AppConstants.NA_UPPERCASE);
        noOfMembers = CommonUtils.setDefaultStringValue(noOfMembers,AppConstants.NA_UPPERCASE);
        kwaWater = CommonUtils.setDefaultStringValue(kwaWater,AppConstants.NA_UPPERCASE);
        waterConnectionType = CommonUtils.setDefaultStringValue(waterConnectionType,AppConstants.NA_UPPERCASE);
        waterSupplyDuration = CommonUtils.setDefaultStringValue(waterSupplyDuration,AppConstants.NA_UPPERCASE);
        buildingUnderScheme = CommonUtils.setDefaultStringValue(buildingUnderScheme,AppConstants.NA_UPPERCASE);
        plotArea = CommonUtils.setDefaultStringValue(plotArea,AppConstants.NA_UPPERCASE);
        rainWaterHarvest = CommonUtils.setDefaultStringValue(rainWaterHarvest,AppConstants.NA_UPPERCASE);
        solarPanel = CommonUtils.setDefaultStringValue(solarPanel,AppConstants.NA_UPPERCASE);
        surveyNumber = CommonUtils.setDefaultStringValue(surveyNumber,AppConstants.NA_UPPERCASE);
        yearsOfConstruction = CommonUtils.setDefaultStringValue(yearsOfConstruction,AppConstants.NA_UPPERCASE);
        totalYears = CommonUtils.setDefaultStringValue(totalYears,AppConstants.NA_UPPERCASE);
        noOfRooms = CommonUtils.setDefaultStringValue(noOfRooms,AppConstants.NA_UPPERCASE);
        anyStructuralChange = CommonUtils.setDefaultStringValue(anyStructuralChange,AppConstants.NA_UPPERCASE);
        anyStructuralChangeYear = CommonUtils.setDefaultStringValue(anyStructuralChangeYear,AppConstants.NA_UPPERCASE);
        anyRoofChange = CommonUtils.setDefaultStringValue(anyRoofChange,AppConstants.NA_UPPERCASE);
        anyRoofChangeYear = CommonUtils.setDefaultStringValue(anyRoofChangeYear,AppConstants.NA_UPPERCASE);
        otherBuilding = CommonUtils.setDefaultStringValue(otherBuilding,AppConstants.NA_UPPERCASE);
        higherFloorSqft = CommonUtils.setDefaultStringValue(higherFloorSqft,AppConstants.NA_UPPERCASE);
        otherBuildingDetails = CommonUtils.setDefaultStringValue(otherBuildingDetails,AppConstants.NA_UPPERCASE);
        religion = CommonUtils.setDefaultStringValue(religion,AppConstants.NA_UPPERCASE);
        religionCast = CommonUtils.setDefaultStringValue(religionCast,AppConstants.NA_UPPERCASE);
        rationCard = CommonUtils.setDefaultStringValue(rationCard,AppConstants.NA_UPPERCASE);
        rationCardNumber = CommonUtils.setDefaultStringValue(rationCardNumber,AppConstants.NA_UPPERCASE);
        bathroom = CommonUtils.setDefaultStringValue(bathroom,AppConstants.NA_UPPERCASE);
        lackDrinkingWater = CommonUtils.setDefaultStringValue(lackDrinkingWater,AppConstants.NA_UPPERCASE);
        gasConnection = CommonUtils.setDefaultStringValue(gasConnection,AppConstants.NA_UPPERCASE);
        wellPerennial = CommonUtils.setDefaultStringValue(wellPerennial,AppConstants.NA_UPPERCASE);
        bankAccount = CommonUtils.setDefaultStringValue(bankAccount,AppConstants.NA_UPPERCASE);
        cattles = CommonUtils.setDefaultStringValue(cattles,AppConstants.NA_UPPERCASE);
        noOfCattles = CommonUtils.setDefaultStringValue(noOfCattles,AppConstants.NA_UPPERCASE);
        memCount = CommonUtils.setDefaultStringValue(memCount,AppConstants.NA_UPPERCASE);
        swimmingPool = CommonUtils.setDefaultStringValue(swimmingPool,AppConstants.NA_UPPERCASE);
        swimmingPoolArea = CommonUtils.setDefaultStringValue(swimmingPoolArea,AppConstants.NA_UPPERCASE);
        paultry = CommonUtils.setDefaultStringValue(paultry,AppConstants.NA_UPPERCASE);
        noOfPoultry = CommonUtils.setDefaultStringValue(noOfPoultry,AppConstants.NA_UPPERCASE);
        noOfVehicle = CommonUtils.setDefaultIntegerStringVale(noOfVehicle,AppConstants.NA_INTEGER_STRING);
        thozhilurapp = CommonUtils.setDefaultStringValue(thozhilurapp,AppConstants.NA_UPPERCASE);
        kudumbasree = CommonUtils.setDefaultStringValue(kudumbasree,AppConstants.NA_UPPERCASE);
        healthInsurance = CommonUtils.setDefaultStringValue(healthInsurance,AppConstants.NA_UPPERCASE);
        colony_name = CommonUtils.setDefaultStringValue(colony_name,AppConstants.NA_UPPERCASE);
        establishmentYear = CommonUtils.setDefaultStringValue(establishmentYear,AppConstants.NA_UPPERCASE);
        inCharge = CommonUtils.setDefaultStringValue(inCharge,AppConstants.NA_UPPERCASE);
        inChargeRole = CommonUtils.setDefaultStringValue(inChargeRole,AppConstants.NA_UPPERCASE);
        employeeCount = CommonUtils.setDefaultStringValue(employeeCount,AppConstants.NA_UPPERCASE);
        licenseNumber = CommonUtils.setDefaultStringValue(licenseNumber,AppConstants.NA_UPPERCASE);
        gstStatus = CommonUtils.setDefaultStringValue(gstStatus,AppConstants.NA_UPPERCASE);
        establishmentEmail = CommonUtils.setDefaultStringValue(establishmentEmail,AppConstants.NA_UPPERCASE);
        establishmentLandline = CommonUtils.setDefaultStringValue(establishmentLandline,AppConstants.NA_UPPERCASE);
        establishmentMobile = CommonUtils.setDefaultStringValue(establishmentMobile,AppConstants.NA_UPPERCASE);

        arRemarks = CommonUtils.setDefaultStringValue(arRemarks,AppConstants.NA_UPPERCASE);
        arOwnerAddress = CommonUtils.setDefaultStringValue(arOwnerAddress,AppConstants.NA_UPPERCASE);
        syncRemarks = CommonUtils.setDefaultStringValue(syncRemarks,AppConstants.NA_UPPERCASE);
        surveyCompletedDate = CommonUtils.setDefaultStringValue(surveyCompletedDate,AppConstants.NA_UPPERCASE);
        arNewPropId = CommonUtils.setDefaultStringValue(arNewPropId,AppConstants.NA_UPPERCASE);
        arOldPropId = CommonUtils.setDefaultStringValue(arOldPropId,AppConstants.NA_UPPERCASE);
        arYearOfConstruction = CommonUtils.setDefaultStringValue(arYearOfConstruction,AppConstants.NA_UPPERCASE);
        arArea = CommonUtils.setDefaultStringValue(arArea,AppConstants.NA_UPPERCASE);
        newpropidArRemarks=CommonUtils.setDefaultStringValue(newpropidArRemarks,AppConstants.NA_UPPERCASE);
        arOwnerAddress=CommonUtils.setDefaultStringValue(arOwnerAddress,AppConstants.NA_UPPERCASE);

        arZone=CommonUtils.setDefaultStringValue(arZone,AppConstants.NA_UPPERCASE);
        arAC=CommonUtils.setDefaultStringValue(arAC,AppConstants.NA_UPPERCASE);
        arFloorArea=CommonUtils.setDefaultStringValue(arFloorArea,AppConstants.NA_UPPERCASE);
        arBuildingUsage=CommonUtils.setDefaultStringValue(arBuildingUsage,AppConstants.NA_UPPERCASE);
        arRoadType=CommonUtils.setDefaultStringValue(arRoadType,AppConstants.NA_UPPERCASE);
        arRoadName=CommonUtils.setDefaultStringValue(arRoadName,AppConstants.NA_UPPERCASE);
        arBuildingAge=CommonUtils.setDefaultStringValue(arBuildingAge,AppConstants.NA_UPPERCASE);
        arRoofDetails=CommonUtils.setDefaultStringValue(arRoofDetails,AppConstants.NA_UPPERCASE);
        arFloorDetails=CommonUtils.setDefaultStringValue(arFloorDetails,AppConstants.NA_UPPERCASE);
        arModification=CommonUtils.setDefaultStringValue(arModification,AppConstants.NA_UPPERCASE);
        arOccupierDetails=CommonUtils.setDefaultStringValue(arOccupierDetails,AppConstants.NA_UPPERCASE);
        arTaxToatal=CommonUtils.setDefaultStringValue(arTaxToatal,AppConstants.NA_UPPERCASE);
        extraRemarks=CommonUtils.setDefaultStringValue(extraRemarks,AppConstants.NA_UPPERCASE);
        areaRemarks=CommonUtils.setDefaultStringValue(areaRemarks,AppConstants.NA_UPPERCASE);

        oldpropidArRemarks=CommonUtils.setDefaultStringValue(oldpropidArRemarks,AppConstants.NA_UPPERCASE);

        if (plasticWasteManagementType != null && plasticWasteManagementType.size() == 0) {
            plasticWasteManagementType.add(AppConstants.NA_UPPERCASE);
        }
        if (liquidWasteManagementType != null && liquidWasteManagementType.size() == 0) {
            liquidWasteManagementType.add(AppConstants.NA_UPPERCASE);
        }
        if (organicWasteManagementType != null && organicWasteManagementType.size() == 0) {
            organicWasteManagementType.add(AppConstants.NA_UPPERCASE);
        }
        if (otherFacility != null && otherFacility.size() == 0) {
            otherFacility.add(AppConstants.NA_UPPERCASE);
        }
        if (floorType != null && floorType.size() == 0) {
            floorType.add(AppConstants.NA_UPPERCASE);
        }
        if (wallType != null && wallType.size() == 0) {
            wallType.add(AppConstants.NA_UPPERCASE);
        }
        if (wellPerennialMonth != null && wellPerennialMonth.size() == 0) {
            wellPerennialMonth.add(AppConstants.NA_UPPERCASE);
        }
        if (pets != null && pets.size() == 0) {
            pets.add(AppConstants.NA_UPPERCASE);
        }
        if (waterSourceType != null && waterSourceType.size() == 0) {
            waterSourceType.add(AppConstants.NA_UPPERCASE);
        }
        if (wellDetails != null && wellDetails.size() == 0) {
            wellDetails.add(AppConstants.NA_UPPERCASE);
        }
        if (waterConnection != null && waterConnection.size() == 0) {
            waterConnection.add(AppConstants.NA_UPPERCASE);
        }
        if (otherSource != null && otherSource.size() == 0) {
            otherSource.add(AppConstants.NA_UPPERCASE);
        }
    }

}
