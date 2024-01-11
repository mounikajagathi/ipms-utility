package in.ults.gisurvey.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

import in.ults.gisurvey.data.local.db.converter.FloorAreaConverter;
import in.ults.gisurvey.data.local.db.converter.MemberDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.RoofTypeConverter;
import in.ults.gisurvey.data.local.db.converter.StringListConverter;
import in.ults.gisurvey.data.local.db.converter.SurveyorDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.VehicleDetailsConverter;
import in.ults.gisurvey.data.model.api.Surveyor;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "property",
        foreignKeys = @ForeignKey(entity = Survey.class,
                parentColumns = "survey_id",
                childColumns = "srvy_id",
                onDelete = CASCADE))
public class Property {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "srvy_id")
    public String surveyID;

    @ColumnInfo(name = "property_id")
    public String propertyID;

    @ColumnInfo(name = "property_start_date")
    public String propertyStartDate = "";

    @ColumnInfo(name = "latitude")
    public double latitude = 0;

    @ColumnInfo(name = "longitude")
    public double longitude = 0;

    @ColumnInfo(name = "survey_type")
    public String surveyType = "";

    @ColumnInfo(name = "building_status")
    public String buildingStatus = "";

    @ColumnInfo(name = "door_status")
    public String doorStatus = "";

    @ColumnInfo(name = "building_under")
    public String buildingUnder = "";

    @ColumnInfo(name = "building_type")
    public String buildingType = "";

    @ColumnInfo(name = "building_sub_type")
    public String buildingSubType = "";

    @ColumnInfo(name = "establishment_usage")
    public String establishmentUsage = "";

    @ColumnInfo(name = "building_usage")
    public String buildingUsage = "";

    @ColumnInfo(name = "main_building")
    public String mainBuilding = "";

    @ColumnInfo(name = "electricity")
    public String electricity = "";

    @ColumnInfo(name = "consumer_number")
    public String consumerNumber = "";

    @ColumnInfo(name = "latrine")
    public String latrine = "";

    @ColumnInfo(name = "toilet_waste_disposal")
    public String toiletWasteDisposal = "";

    @ColumnInfo(name = "air_conditioner")
    public String airConditioner = "";

    @ColumnInfo(name = "landmark")
    public String landmark = "";

    @ColumnInfo(name = "landmark_name")
    public String landmarkName = "";

    @ColumnInfo(name = "landmark_category")
    public String landmarkCategory = "";

    @ColumnInfo(name = "landmark_sub_category")
    public String landmarkSubCategory = "";

    @ColumnInfo(name = "ownership_education")
    public String ownershipEducation = "";

    @ColumnInfo(name = "drone_id")
    public String droneId = "";

    @ColumnInfo(name = "old_property_id")
    public String oldPropertyId = "";

    @ColumnInfo(name = "u_id")
    public String uId = "";

    @ColumnInfo(name = "new_property_id")
    public String newPropertyId = "";

    @ColumnInfo(name = "new_property_remarks")
    public String newPropertyRemarks = "";

    @ColumnInfo(name = "near_property_number")
    public String nearPropertyNumber = "";

    @ColumnInfo(name = "owner_name")
    public String ownerName = "";

    @ColumnInfo(name = "owner_occupation")
    public String ownerOccupation = "";

    @ColumnInfo(name = "owner_house_name_number")
    public String ownerHouseNameNumber = "";

    @ColumnInfo(name = "owner_street_place_name")
    public String ownerStreetPlaceName = "";

    @ColumnInfo(name = "owner_state")
    public String ownerState = "";

    @ColumnInfo(name = "owner_post_office")
    public String ownerPostOffice = "";

    @ColumnInfo(name = "owner_pincode")
    public String ownerPincode = "";

    @ColumnInfo(name = "owner_email")
    public String ownerEmail = "";

    @ColumnInfo(name = "owner_land_line")
    public String ownerLandLine = "";

    @ColumnInfo(name = "owner_mobile")
    public String ownerMobile = "";

    @ColumnInfo(name = "tele_Call_number")
    public String teleCallNumber = "";

    @ColumnInfo(name = "near_road")
    public String nearRoad = "";

    @ColumnInfo(name = "road_type")
    public String roadType = "";

    @ColumnInfo(name = "road_width")
    public String roadWidth = "";

    @ColumnInfo(name = "tenant_name")
    public String tenantName = "";

    @ColumnInfo(name = "tenant_house_name_number")
    public String tenantHouseNameNumber = "";

    @ColumnInfo(name = "tenant_street_place_name")
    public String tenantStreetPlaceName = "";

    @ColumnInfo(name = "tenant_survey_number")
    public String tenantSurveyNumber = "";

    @ColumnInfo(name = "tenant_state")
    public String tenantState = "";

    @ColumnInfo(name = "tenant_post_office")
    public String tenantPostOffice = "";

    @ColumnInfo(name = "tenant_pincode")
    public String tenantPincode = "";

    @ColumnInfo(name = "tenant_mobile")
    public String tenantMobile = "";

    @ColumnInfo(name = "tenant_land_line")
    public String tenantLandLine = "";

    @ColumnInfo(name = "tenant_email")
    public String tenantEmail = "";

    @ColumnInfo(name = "tenant_amount")
    public String tenantAmount = "";

    @ColumnInfo(name = "tenant_native")
    public String tenantNative = "";

    @ColumnInfo(name = "tenant_status")
    public String tenantStatus = "";

    @ColumnInfo(name = "tax_number")
    public String taxNumber = "";

    @ColumnInfo(name = "tax_amount")
    public String taxAmount = "";

    @ColumnInfo(name = "tax_date")
    public String taxDate = "";

    @ColumnInfo(name = "tax_year")
    public String taxYear = "";

    @ColumnInfo(name = "tax_annual_amount")
    public String taxAnnualAmount = "";

    @ColumnInfo(name = "establishment_name")
    public String establishmentName = "";

    @ColumnInfo(name = "establishment_type")
    public String establishmentType = "";

    @ColumnInfo(name = "establishment_year")
    public String establishmentYear = "";

    @ColumnInfo(name = "in_charge")
    public String inCharge = "";

    @ColumnInfo(name = "in_charge_role")
    public String inChargeRole = "";

    @ColumnInfo(name = "employee_count")
    public String employeeCount = "";

    @ColumnInfo(name = "license_number")
    public String licenseNumber = "";

    @ColumnInfo(name = "gst_status")
    public String gstStatus = "";

    @ColumnInfo(name = "establishment_email")
    public String establishmentEmail = "";

    @ColumnInfo(name = "establishment_landline")
    public String establishmentLandline = "";

    @ColumnInfo(name = "establishment_mobile")
    public String establishmentMobile = "";

    @ColumnInfo(name = "no_of_members")
    public String noOfMembers = "";

    @ColumnInfo(name = "member_details")
    @TypeConverters(MemberDetailsConverter.class)
    public ArrayList<MemberDetailsItem> memberDetails = new ArrayList<>();

    @ColumnInfo(name = "religion")
    public String religion ="";

    @ColumnInfo(name = "religion_cast")
    public String religionCast = "";

    @ColumnInfo(name = "ration_card")
    public String rationCard = "";

    @ColumnInfo(name = "ration_card_number")
    public String rationCardNumber = "";

    @ColumnInfo(name = "bathroom")
    public String bathroom = "";

    @ColumnInfo(name = "kwa_water")
    public String kwaWater = "";

    @ColumnInfo(name = "water_connection_type")
    public String waterConnectionType = "";

    @ColumnInfo(name = "water_supply_duration")
    public String waterSupplyDuration = "";

    @ColumnInfo(name = "lack_drinking_water")
    public String lackDrinkingWater = "";

    @ColumnInfo(name = "gas_connection")
    public String gasConnection = "";

    @ColumnInfo(name = "rain_water_harvest")
    public String rainWaterHarvest = "";

    @ColumnInfo(name = "solar_panel")
    public String solarPanel = "";

    @ColumnInfo(name = "water_source_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> waterSourceType = new ArrayList<>();

    @ColumnInfo(name = "plastic_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> plasticWasteManagementType = new ArrayList<>();

    @ColumnInfo(name = "liquid_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> liquidWasteManagementType = new ArrayList<>();

    @ColumnInfo(name = "organic_waste_management_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> organicWasteManagementType = new ArrayList<>();

    @ColumnInfo(name = "any_other_facility")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> anyOtherFacility = new ArrayList<>();

    @ColumnInfo(name = "pet")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> pets = new ArrayList<>();

    @ColumnInfo(name = "bank_account")
    public String bankAccount = "";

    @ColumnInfo(name = "paultry")
    public String paultry = "";

    @ColumnInfo(name = "no_of_poultry")
    public String noOfPoultry = "";

    @ColumnInfo(name = "cattles")
    public String cattles = "";

    @ColumnInfo(name = "no_of_cattles")
    public String noOfCattles = "";

    @ColumnInfo(name = "mem_count")
    public String memCount = "";

    @ColumnInfo(name = "swimming_pool")
    public String swimmingPool = "";

    @ColumnInfo(name = "swimming_pool_area")
    public String swimmingPoolArea = "";

    @ColumnInfo(name = "type_of_land")
    public String typeOfLand = "";

    @ColumnInfo(name = "building_under_scheme")
    public String buildingUnderScheme = "";

    @ColumnInfo(name = "plot_area")
    public String plotArea = "";

    @ColumnInfo(name = "no_vehicles")
    public String noOfVehicles = "-1";

    @ColumnInfo(name = "vehicle_details")
    @TypeConverters(VehicleDetailsConverter.class)
    public ArrayList<VehicleDetailsItem> vehicleDetails = new ArrayList<>();

    @ColumnInfo(name = "well_perennial")
    public String wellPerennial = "";

    @ColumnInfo(name = "well_perennial_month")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wellPerennialMonth = new ArrayList<>();

    @ColumnInfo(name = "thozhilurapp")
    public String thozhilurapp = "";

    @ColumnInfo(name = "kudumbasree")
    public String kudumbasree = "";

    @ColumnInfo(name = "health_insurance")
    public String healthInsurance = "";

    @ColumnInfo(name = "building_name")
    public String buildingName = "";

    @ColumnInfo(name = "colony_name")
    public String colonyName = "";

    @ColumnInfo(name = "survey_number")
    public String surveyNumber = "";

    @ColumnInfo(name = "years_of_construction")
    public String yearsOfConstruction = "";

    @ColumnInfo(name = "total_years")
    public String totalYears = "";

    @ColumnInfo(name = "no_of_rooms")
    public String noOfRooms = "";

    @ColumnInfo(name = "no_of_floors")
    public String noOfFloors = "-1";

    @ColumnInfo(name = "floor_area")
    @TypeConverters(FloorAreaConverter.class)
    public ArrayList<BuildingDetailsFloorAreaItem> floorArea = new ArrayList<>();

    @ColumnInfo(name = "area_remarks")
    public String areaRemarks = "";

    @ColumnInfo(name = "structure_type")
    public String structureType = "";

    @ColumnInfo(name = "car_porch")
    public String carPorch = "";

    @ColumnInfo(name = "car_porch_area")
    public String carPorchArea = "";

    @ColumnInfo(name = "common_stair")
    public String commonStair = "";

    @ColumnInfo(name = "common_stair_area")
    public String commonStairArea = "";

    @ColumnInfo(name = "pathway_area")
    public String pathwayArea = "";

    @ColumnInfo(name = "is_any_extension")
    public String isAnyExtension = "";

    @ColumnInfo(name = "any_structural_change")
    public String anyStructuralChange = "";

    @ColumnInfo(name = "structural_change_year")
    public String structuralChangeYear = "";

    @ColumnInfo(name = "roof_type_change")
    public String roofTypeChange = "";

    @ColumnInfo(name = "roof_type_change_year")
    public String roofTypeChangeYear = "";

    @ColumnInfo(name = "other_building")
    public String otherBuilding = "";

    @ColumnInfo(name = "other_building_details")
    public String otherBuildingDetails = "";


    @ColumnInfo(name = "floor_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> floorType = new ArrayList<>();

    @ColumnInfo(name = "higher_floor_sqft")
    public String higherFloorSqft = "";

    @ColumnInfo(name = "wall_type")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wallType = new ArrayList<>();

    @ColumnInfo(name = "roof_total")
    public String roofTotal = "";

    @ColumnInfo(name = "no_of_roof_type")
    public String noOfRoofType = "-1";

    @ColumnInfo(name = "roof_type_details")
    @TypeConverters(RoofTypeConverter.class)
    public ArrayList<BuildingDetailsRoofItem> roofDetails = new ArrayList<>();

    @ColumnInfo(name = "property_image_one")
    public String propertyImageOne = "";

    @ColumnInfo(name = "property_image_two")
    public String propertyImageTwo = "";

    @ColumnInfo(name = "property_image_three")
    public String propertyImageThree = "";

    @ColumnInfo(name = "informed_by")
    public String informedBy = "";

    @ColumnInfo(name = "cooperate_survey")
    public String cooperateSurvey = "";

    @ColumnInfo(name = "surveyor_name")
    public String surveyorName = "";

    @ColumnInfo(name = "common_remarks")
    public String commonRemarks = "";

    @ColumnInfo(name = "completed_status")
    public boolean completedStatus = false;

    @ColumnInfo(name = "property_end_date")
    public String propertyEndDate = "";

    @ColumnInfo(name = "version")
    public String version = "";

    @ColumnInfo(name = "well_details")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> wellDetails = new ArrayList<>();

    @ColumnInfo(name = "water_connection")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> waterConnection = new ArrayList<>();

    @ColumnInfo(name = "other_source")
    @TypeConverters(StringListConverter.class)
    public ArrayList<String> otherSource = new ArrayList<>();

    @ColumnInfo(name = "sync_completed")
    public boolean syncCompleted = false;

    @ColumnInfo(name = "ar_remarks")
    public String arRemarks = "";

    @ColumnInfo(name = "ar_owner_address")
    public String arOwnerAddress = "";

    @ColumnInfo(name = "sync_remark")
    public String syncRemarks = "";

    @ColumnInfo(name = "ar_new_prop_id")
    public String arNewPropId = "";

    @ColumnInfo(name = "ar_old_prop_id")
    public String arOldPropId = "";

    @ColumnInfo(name = "ar_year_of_construction")
    public String arYearOfConstruction = "";

    @ColumnInfo(name = "ar_area")
    public String arArea = "";

    @ColumnInfo(name = "newproid_ar_remarks")
    public String newpropidArRemarks = "";

    @ColumnInfo(name = "oldproid_ar_remarks")
    public String oldpropidArRemarks = "";

    @ColumnInfo(name = "surveyor_details")
    @TypeConverters(SurveyorDetailsConverter.class)
    public ArrayList<Surveyor> surveyorDetails = new ArrayList<>();

    @ColumnInfo(name = "app_version_details")
    public String appVersionDetails = "";

    @ColumnInfo(name = "sync_completed_date")
    public String syncCompletedDate = "";

    @ColumnInfo(name = "ar_zone")
    public String arZone = "";

    @ColumnInfo(name = "ar_ac")
    public String arAC = "";

    @ColumnInfo(name = "ar_floor_area")
    public String arFloorArea = "";

    @ColumnInfo(name = "ar_building_usage")
    public String arBuildingUsage = "";

    @ColumnInfo(name = "ar_road_type")
    public String arRoadType = "";

    @ColumnInfo(name = "ar_road_name")
    public String arRoadName = "";

    @ColumnInfo(name = "ar_building_age")
    public String arBuildingAge = "";

    @ColumnInfo(name = "ar_roof_details")
    public String arRoofDetails = "";

    @ColumnInfo(name = "ar_floor_details")
    public String arFloorDetails = "";

    @ColumnInfo(name = "ar_modification")
    public String arModification = "";

    @ColumnInfo(name = "ar_occupier_details")
    public String arOccupierDetails = "";

    @ColumnInfo(name = "ar_tax_total")
    public String arTaxToatal = "";

    @ColumnInfo(name = "extra_remarks")
    public String extraRemarks = "";

    @ColumnInfo(name = "stair")
    public String stair = "";

    @ColumnInfo(name = "stair_area")
    public String stairArea = "";


    @ColumnInfo(name = "is_building_validation_ok")
    public boolean isBuildingValidationOk = false;

    @ColumnInfo(name = "is_establishment_validation_ok")
    public boolean isEstablishmentValidationOk = false;

    @ColumnInfo(name = "is_image_validation_ok")
    public boolean isImageValidationOk = false;

    @ColumnInfo(name = "is_livehood_validation_ok")
    public boolean isLivehoodValidationOk = false;

    @ColumnInfo(name = "is_member_validation_ok")
    public boolean isMemberValidationOk = false;

    @ColumnInfo(name = "is_more_validation_ok")
    public boolean isMoreValidationOk = false;

    @ColumnInfo(name = "is_owner_validation_ok")
    public boolean isOwnerValidationOk = false;

    @ColumnInfo(name = "is_road_validation_ok")
    public boolean isRoadValidationOk = false;

    @ColumnInfo(name = "is_tax_validation_ok")
    public boolean isTaxValidationOk = false;

    @ColumnInfo(name = "is_tenant_validation_ok")
    public boolean isTenantValidationOk = false;

    public Property(String surveyID, String propertyID) {
        this.surveyID = surveyID;
        this.propertyID = propertyID;
    }

    @Ignore

    public Property(String surveyID, String propertyID, String propertyStartDate, double latitude, double longitude, String surveyType, String buildingStatus, String doorStatus, String buildingUnder, String buildingType, String buildingSubType, String establishmentUsage, String buildingUsage, String mainBuilding, String electricity, String consumerNumber, String latrine, String toiletWasteDisposal, String airConditioner, String landmark, String landmarkName, String landmarkCategory, String landmarkSubCategory, String ownershipEducation, String droneId, String oldPropertyId, String uId, String newPropertyId, String newPropertyRemarks, String nearPropertyNumber, String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber, String nearRoad, String roadType, String roadWidth, String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantSurveyNumber, String tenantState, String tenantPostOffice, String tenantPincode, String tenantMobile, String tenantLandLine, String tenantEmail, String tenantAmount, String tenantNative, String tenantStatus, String taxNumber, String taxAmount, String taxDate, String taxYear, String taxAnnualAmount, String establishmentName, String establishmentType, String establishmentYear, String inCharge, String inChargeRole, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile, String noOfMembers, ArrayList<MemberDetailsItem> memberDetails, String religion, String religionCast, String rationCard, String rationCardNumber, String bathroom, String kwaWater, String waterConnectionType, String waterSupplyDuration, String lackDrinkingWater, String gasConnection, String rainWaterHarvest, String solarPanel, ArrayList<String> waterSourceType, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> anyOtherFacility, ArrayList<String> pets, String bankAccount, String paultry, String noOfPoultry, String cattles, String noOfCattles, String memCount, String swimmingPool, String swimmingPoolArea, String typeOfLand, String buildingUnderScheme, String plotArea, String noOfVehicles, ArrayList<VehicleDetailsItem> vehicleDetails, String wellPerennial, ArrayList<String> wellPerennialMonth, String thozhilurapp, String kudumbasree, String healthInsurance, String buildingName, String colonyName, String surveyNumber, String yearsOfConstruction, String totalYears, String noOfRooms, String noOfFloors, ArrayList<BuildingDetailsFloorAreaItem> floorArea, String structureType, String carPorch, String carPorchArea, String commonStair, String commonStairArea, String pathwayArea, String isAnyExtension, String anyStructuralChange, String structuralChangeYear, String roofTypeChange, String roofTypeChangeYear, String otherBuilding, String otherBuildingDetails, ArrayList<String> floorType, String higherFloorSqft, ArrayList<String> wallType, String roofTotal, String noOfRoofType, ArrayList<BuildingDetailsRoofItem> roofDetails, String propertyImageOne, String propertyImageTwo, String propertyImageThree, String informedBy, String cooperateSurvey, String surveyorName, String commonRemarks, boolean completedStatus, String propertyEndDate, String version, ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource, boolean syncCompleted, String arRemarks, String arOwnerAddress, String syncRemarks, String arNewPropId, String arOldPropId, String arYearOfConstruction, String arArea, String newpropidArRemarks, String oldpropidArRemarks, ArrayList<Surveyor> surveyorDetails, String appVersionDetails, String syncCompletedDate, String arZone, String arAC, String arFloorArea, String arBuildingUsage, String arRoadType, String arRoadName, String arBuildingAge, String arRoofDetails, String arFloorDetails, String arModification, String arOccupierDetails, String arTaxToatal, String extraRemarks, String stair, String stairArea, String areaRemarks, boolean isBuildingValidationOk, boolean isEstablishmentValidationOk, boolean isImageValidationOk, boolean isLivehoodValidationOk, boolean isMemberValidationOk, boolean isMoreValidationOk, boolean isOwnerValidationOk, boolean isRoadValidationOk, boolean isTaxValidationOk, boolean isTenantValidationOk) {
        this.surveyID = surveyID;
        this.propertyID = propertyID;
        this.propertyStartDate = propertyStartDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.surveyType = surveyType;
        this.buildingStatus = buildingStatus;
        this.doorStatus = doorStatus;
        this.buildingUnder = buildingUnder;
        this.buildingType = buildingType;
        this.buildingSubType = buildingSubType;
        this.establishmentUsage = establishmentUsage;
        this.buildingUsage = buildingUsage;
        this.mainBuilding = mainBuilding;
        this.electricity = electricity;
        this.consumerNumber = consumerNumber;
        this.latrine = latrine;
        this.toiletWasteDisposal = toiletWasteDisposal;
        this.airConditioner = airConditioner;
        this.landmark = landmark;
        this.landmarkName = landmarkName;
        this.landmarkCategory = landmarkCategory;
        this.landmarkSubCategory = landmarkSubCategory;
        this.ownershipEducation = ownershipEducation;
        this.droneId = droneId;
        this.oldPropertyId = oldPropertyId;
        this.uId = uId;
        this.newPropertyId = newPropertyId;
        this.newPropertyRemarks = newPropertyRemarks;
        this.nearPropertyNumber = nearPropertyNumber;
        this.ownerName = ownerName;
        this.ownerOccupation = ownerOccupation;
        this.ownerHouseNameNumber = ownerHouseNameNumber;
        this.ownerStreetPlaceName = ownerStreetPlaceName;
        this.ownerState = ownerState;
        this.ownerPostOffice = ownerPostOffice;
        this.ownerPincode = ownerPincode;
        this.ownerEmail = ownerEmail;
        this.ownerLandLine = ownerLandLine;
        this.ownerMobile = ownerMobile;
        this.teleCallNumber = teleCallNumber;
        this.nearRoad = nearRoad;
        this.roadType = roadType;
        this.roadWidth = roadWidth;
        this.tenantName = tenantName;
        this.tenantHouseNameNumber = tenantHouseNameNumber;
        this.tenantStreetPlaceName = tenantStreetPlaceName;
        this.tenantSurveyNumber = tenantSurveyNumber;
        this.tenantState = tenantState;
        this.tenantPostOffice = tenantPostOffice;
        this.tenantPincode = tenantPincode;
        this.tenantMobile = tenantMobile;
        this.tenantLandLine = tenantLandLine;
        this.tenantEmail = tenantEmail;
        this.tenantAmount = tenantAmount;
        this.tenantNative = tenantNative;
        this.tenantStatus = tenantStatus;
        this.taxNumber = taxNumber;
        this.taxAmount = taxAmount;
        this.taxDate = taxDate;
        this.taxYear = taxYear;
        this.taxAnnualAmount = taxAnnualAmount;
        this.establishmentName = establishmentName;
        this.establishmentType = establishmentType;
        this.establishmentYear = establishmentYear;
        this.inCharge = inCharge;
        this.inChargeRole = inChargeRole;
        this.employeeCount = employeeCount;
        this.licenseNumber = licenseNumber;
        this.gstStatus = gstStatus;
        this.establishmentEmail = establishmentEmail;
        this.establishmentLandline = establishmentLandline;
        this.establishmentMobile = establishmentMobile;
        this.noOfMembers = noOfMembers;
        this.memberDetails = memberDetails;
        this.religion = religion;
        this.religionCast = religionCast;
        this.rationCard = rationCard;
        this.rationCardNumber = rationCardNumber;
        this.bathroom = bathroom;
        this.kwaWater = kwaWater;
        this.waterConnectionType = waterConnectionType;
        this.waterSupplyDuration = waterSupplyDuration;
        this.lackDrinkingWater = lackDrinkingWater;
        this.gasConnection = gasConnection;
        this.rainWaterHarvest = rainWaterHarvest;
        this.solarPanel = solarPanel;
        this.waterSourceType = waterSourceType;
        this.plasticWasteManagementType = plasticWasteManagementType;
        this.liquidWasteManagementType = liquidWasteManagementType;
        this.organicWasteManagementType = organicWasteManagementType;
        this.anyOtherFacility = anyOtherFacility;
        this.pets = pets;
        this.bankAccount = bankAccount;
        this.paultry = paultry;
        this.noOfPoultry = noOfPoultry;
        this.cattles = cattles;
        this.noOfCattles = noOfCattles;
        this.memCount = memCount;
        this.swimmingPool = swimmingPool;
        this.swimmingPoolArea = swimmingPoolArea;
        this.typeOfLand = typeOfLand;
        this.buildingUnderScheme = buildingUnderScheme;
        this.plotArea = plotArea;
        this.noOfVehicles = noOfVehicles;
        this.vehicleDetails = vehicleDetails;
        this.wellPerennial = wellPerennial;
        this.wellPerennialMonth = wellPerennialMonth;
        this.thozhilurapp = thozhilurapp;
        this.kudumbasree = kudumbasree;
        this.healthInsurance = healthInsurance;
        this.buildingName = buildingName;
        this.colonyName = colonyName;
        this.surveyNumber = surveyNumber;
        this.yearsOfConstruction = yearsOfConstruction;
        this.totalYears = totalYears;
        this.noOfRooms = noOfRooms;
        this.noOfFloors = noOfFloors;
        this.floorArea = floorArea;
        this.areaRemarks = areaRemarks;
        this.structureType = structureType;
        this.carPorch = carPorch;
        this.carPorchArea = carPorchArea;
        this.commonStair = commonStair;
        this.commonStairArea = commonStairArea;
        this.pathwayArea = pathwayArea;
        this.isAnyExtension = isAnyExtension;
        this.anyStructuralChange = anyStructuralChange;
        this.structuralChangeYear = structuralChangeYear;
        this.roofTypeChange = roofTypeChange;
        this.roofTypeChangeYear = roofTypeChangeYear;
        this.otherBuilding = otherBuilding;
        this.otherBuildingDetails = otherBuildingDetails;
        this.floorType = floorType;
        this.higherFloorSqft = higherFloorSqft;
        this.wallType = wallType;
        this.roofTotal = roofTotal;
        this.noOfRoofType = noOfRoofType;
        this.roofDetails = roofDetails;
        this.propertyImageOne = propertyImageOne;
        this.propertyImageTwo = propertyImageTwo;
        this.propertyImageThree = propertyImageThree;
        this.informedBy = informedBy;
        this.cooperateSurvey = cooperateSurvey;
        this.surveyorName = surveyorName;
        this.commonRemarks = commonRemarks;
        this.completedStatus = completedStatus;
        this.propertyEndDate = propertyEndDate;
        this.version = version;
        this.wellDetails = wellDetails;
        this.waterConnection = waterConnection;
        this.otherSource = otherSource;
        this.syncCompleted = syncCompleted;
        this.arRemarks = arRemarks;
        this.arOwnerAddress = arOwnerAddress;
        this.syncRemarks = syncRemarks;
        this.arNewPropId = arNewPropId;
        this.arOldPropId = arOldPropId;
        this.arYearOfConstruction = arYearOfConstruction;
        this.arArea = arArea;
        this.newpropidArRemarks = newpropidArRemarks;
        this.oldpropidArRemarks = oldpropidArRemarks;
        this.surveyorDetails = surveyorDetails;
        this.appVersionDetails = appVersionDetails;
        this.syncCompletedDate = syncCompletedDate;
        this.arZone = arZone;
        this.arAC = arAC;
        this.arFloorArea = arFloorArea;
        this.arBuildingUsage = arBuildingUsage;
        this.arRoadType = arRoadType;
        this.arRoadName = arRoadName;
        this.arBuildingAge = arBuildingAge;
        this.arRoofDetails = arRoofDetails;
        this.arFloorDetails = arFloorDetails;
        this.arModification = arModification;
        this.arOccupierDetails = arOccupierDetails;
        this.arTaxToatal = arTaxToatal;
        this.extraRemarks = extraRemarks;
        this.stair = stair;
        this.stairArea = stairArea;
        this.isBuildingValidationOk = isBuildingValidationOk;
        this.isEstablishmentValidationOk = isEstablishmentValidationOk;
        this.isImageValidationOk = isImageValidationOk;
        this.isLivehoodValidationOk = isLivehoodValidationOk;
        this.isMemberValidationOk = isMemberValidationOk;
        this.isMoreValidationOk = isMoreValidationOk;
        this.isOwnerValidationOk = isOwnerValidationOk;
        this.isRoadValidationOk = isRoadValidationOk;
        this.isTaxValidationOk = isTaxValidationOk;
        this.isTenantValidationOk = isTenantValidationOk;
    }
}
