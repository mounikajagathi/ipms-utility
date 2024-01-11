package in.ults.gisurvey.utils;

public final class AppConstants {

    public static final String DB_NAME = "ults_gis.db";

    public static final int NULL_INDEX = -1;

    public static final String PREF_NAME = "ults_pref";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final int REQUEST_CODE_OPEN_CAMERA = 303;

    public static final int REQUEST_CODE_OPEN_GALLERY = 505;

    public static final int REQUEST_CODE_STORAGE_PERMISSION = 134;

    public static final int REQUEST_CODE_PICK_A_FILE = 1001;
    public  static final String HIGHER_FLOOTYPE_MATERIAL1="Vitrified Tile";

    public static final String HIGHER_FLOOTYPE_MATERIAL2="Granite";

    public static final String HIGHER_FLOOTYPE_MATERIAL3="Gracewood";

    public static final String HIGHER_FLOOTYPE_MATERIAL4="Italian Marble";
    public static final double HIGHER_FLOOTYPE_VALUE = 250;
    public static final int REQUEST_CODE_PICK_BACK_UP_FILE = 1050;
    public static final int REQUEST_CODE_PICK_AR_FILE = 1025;

    public static final int REQUEST_CODE_PICK_INFO_VIDEO_FILE = 1030;


    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final String POINT_STATUS_UNWANTED = "UNWANTED";
    public static final String POINT_STATUS_BUILDING = "BUILDING";
    public static final String POINT_STATUS_LANDMARK = "LANDMARK";
    public static final String BUILDING_STATUS_ONGOING = "Ongoing";
    public static final String BUILDING_STATUS_ONGOING_WITHOUT_ROOF = "Ongoing Without Roof";
    public static final String BUILDING_STATUS_COMPLETED = "Completed";
    public static final String BUILDING_STATUS_DEMOLISHED = "Demolished";
    public static final String BUILDING_STATUS_UNUSABLE = "Unusable";
    public static final String BUILDING_STATUS_EXTENSION = "Extension";
    public static final String BUILDING_STATUS_REPAIR = "Repair";
    public static final String BUILDING_UNDER_STATE_GOV = "State Government";
    public static final String BUILDING_TYPE_RESIDENTIAL = "Residential";
    public static final String BUILDING_TYPE_EDUCATIONAL = "Education";
    public static final String BUILDING_SUB_TYPE_HOUSE = "House";
    public static final String ESTABLISHMENT_USAGE_SINGLE_HOUSE = "House - Single Family Dwelling";
    public static final String ESTABLISHMENT_USAGE_MULTIPLE_HOUSE = "House - Multiple Family Dwelling";
    public static final String ESTABLISHMENT_USAGE_RESIDENTIAL_HOUSE = "Residential House (tax exempted)";
    public static final String ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT = "Residential Flat / Apartment building";
    public static final String ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS = "Quarters";
    public static final String ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA = "Villa";
    public static final String BUILDING_SUB_TYPE_HOSTEL = "Hostel";
    public static final String BUILDING_SUB_TYPE_RESORT = "Resort";
    public static final String BUILDING_SUB_TYPE_LODGE = "Lodge";
    public static final String NEW_BUILDING_SUB_TYPE_HOSTEL = "Hostel";
    public static final String NEW_BUILDING_SUB_TYPE_RESORT = "Resorts";
    public static final String NEW_BUILDING_SUB_TYPE_LODGE = "Lodge";
    public static final String BUILDING_SUB_TYPE_RELATED_BUILDING = "Related Building";
    public static final String ESTABLISHMENT_USAGE_RELATED_SMALL = "Small professional offices, small household business or spaces not exceeding 50 sq. and used as part of principal residential";
    public static final String ESTABLISHMENT_USAGE_RELATED_TOOLS = "Sheds for keeping tools";
    public static final String ESTABLISHMENT_USAGE_RELATED_AGRICULTURAL = "Sheds for keeping agricultural implements";
    public static final String ESTABLISHMENT_USAGE_RELATED_MATERIAL = "Sheds for keeping rubbish or other materials";
    public static final String ESTABLISHMENT_USAGE_RELATED_CROPS = "Sheds for watching crops";
    public static final String ESTABLISHMENT_USAGE_RELATED_WOOD = "Wood shed";
    public static final String ESTABLISHMENT_USAGE_RELATED_CATTLE = "Cattle shed appurtenant to residential building";
    public static final String ESTABLISHMENT_USAGE_RELATED_PARKING = "Parking Space - Residential";
    public static final String ESTABLISHMENT_USAGE_NIGHT_SHELTER_CHARITABLE = "Night Shelter - Charitable";
    public static final String ESTABLISHMENT_USAGE_NIGHT_SHELTER_PRIVATE = "Night Shelter - Private";
    public static final String BUILDING_SUB_TYPE_QUARTERS = "Quarters";
    public static final String BUILDING_SUB_TYPE_APARTMENT = "Apartment/Flat";
    public static final String BUILDING_SUB_TYPE_FLAT = "Villa";
    public static final String BUILDING_USAGE_RENTED ="RENTED";
    public static final String DOOR_STATUS_DC = "Door Closed";
    public static final String DOOR_STATUS_NC = "NON CO OPERATION";
    public static final String DOOR_STATUS_PDC = "Permanent Door Closed";
    public static final String DOOR_STATUS_GL = "Gate Locked";
    public static final String DOOR_STATUS_OPEN = "Door Opened";
    public static final String LIVEHOOD_NO_RELIGION = "No religion";
    public static final String LIVEHOOD_NO_RATION_CARD = "No Card";
    public static final String NR_SIMPLE = "Nr";
    public static final String NR = "Not Received";
    public static final String NO = "No";
    public static final String UN_AUTHORISED_ERROR = "Unauthorized";
    public static final String TENANT_NATIVE_INSIDE_LSGD = "Inside LSGD";
    public static final String PROPERTY_ID_SEPERATOR = "/";
    public static final String  NA_NEAR = "NA NEAR";
    public static final String  NR_NEAR = "NR NEAR";
    public static final String  CLEAR = "";

    public static final int NA_INTEGER = -2;
    public static final int NR_INTEGER = -1;
    public static final String NA_INTEGER_STRING = "-2";
    public static final String NR_INTEGER_STRING = "-1";
    public static final String NA_UPPERCASE = "NA";
    public static final String NR_UPPERCASE = "NR";
    public static final String NR_SCHEME = "NR SCHEME";
    public static final String NO_COMMENTS = "No Comments";
    public static final String NEAR_PREFIX = "NEAR ";
    public static final String NOT_NEEDED = "Not needed";
    public static final String NO_LAND = "No Land";
    public static final String TWO_WHEELER = "2 Wheeler";
    public static final String NON_TAXI = "Non Taxi";
    public static final String TELE_CALL = "Tele Call";


    public static final String DEFAULT_STATE = "Kerala";
    public static final int SYNC_COUNT = 5;


    public static final String SURVEY_OPEN_MODE_VIEW = "VIEW";
    public static final String SURVEY_OPEN_MODE_EDIT = "EDIT";

    public static final String LIVE_LOC_ON = "ON";
    public static final String LIVE_LOC_OFF = "OFF";

    public static final String SURVEY_DATE_FORMAT = "EEE, d MMM yyyy, HH:mm:ss";
    public static final String SURVEY_REPORT_DATE_FORMAT = "EEE, d MMM yyyy";
    public static final String SURVEY_BACKUP_NAME_FORMAT = "d-MMM-yyyy-HH-mm-ss";
    public static final String REPORT_VIEW_DATE_FORMAT = "dd-MM-yyyy";


    public static final String WATER_SOURCE_TYPE_DUG_WELL = "Dug Well";
    public static final String WATER_SOURCE_TYPE_TUBE_WELL = "Tube Well";

    public static final String COMPLETED_LIST_TYPE_UNSYNC = "Unsync";
    public static final String COMPLETED_LIST_TYPE_SYNC = "Sync";

    public static final int SYNC_BTN_CLICK_DELAY = 1000;

    public static final int AR_FILE_COL_UID = 0;
    public static final int AR_FILE_COL_OWNER_ADDRESS_ENG = 12;
    public static final int AR_FILE_COL_NEW_SUB_NO = 11;
    public static final int AR_FILE_COL_NEW_PROPERTY_NO = 10;
    public static final int AR_FILE_COL_NEW_WARD = 9;
    public static final int AR_FILE_COL_OLD_SUB_NO = 5;
    public static final int AR_FILE_COL_OLD_PROPERTY_NO = 4;
    public static final int AR_FILE_COL_OLD_WARD = 3;
    public static final int AR_FILE_COL_ZONE =16 ;
    public static final int AR_FILE_COL_AC = 24;
    public static final int AR_FILE_COL_FLOOR_AREA = 14;
    public static final int AR_FILE_COL_BUILDING_USEAGE = 15;
    public static final int AR_FILE_COL_ROAD_TYPE =17 ;
    public static final int AR_FILE_COL_ROAD_NAME = 18;
    public static final int AR_FILE_COL_BUILDING_AGE = 21;
    public static final int AR_FILE_COL_ROOF_DETAILS =22 ;
    public static final int AR_FILE_COL_FLOOR_DETAILS = 23;
    public static final int AR_FILE_COL_MODIFICATION = 25;
    public static final int AR_FILE_COL_OCCUPIER_DETAILS = 13;
    public static final int AR_FILE_COL_AR_TAX_TOTAL =35 ;
    public static final String OLD_PROP_ID_VERIFICATION = "old_prop_verification";
    public static final String NEW_PROP_ID_VERIFICATION = "new_prop_verification";

    public static final String IMG_ONE = "img1";
    public static final String IMG_TWO = "img2";
    public static final String IMG_THREE = "plan_img";

    public static final String GENDER_FEMALE="Female";
    public static final String GENDER_MALE="Male";
    public static final String MARITAL_STATUS_MARRIED="Married";
    public static final String MARITAL_STATUS_SINGLE="Single";
    public static final String MARITAL_STATUS_DIVORCED_SEPARATED="Divorced/Separated";
    public static final String PENSION_WIDOW="WIDOW PENSION";
    public static final String PENSION_AVIVAHITHA="AVIVAHITHA PENSION";
    public static final String PENSION_OLDAGE="OLDAGE PENSION";
    public static final int PENSION_OLDAGE_AGE_LIMIT=60;
    public static final String JOB_TYPE_CENTRAL_GOVERNMENT= "Central Government";
    public static final String JOB_TYPE_STATE_GOVERNMENT= "State Government";

    public static final String RATIONCARD_PINK="Pink";
    public static final String RATIONCARD_WHITE="White";
    public static final String RATIONCARD_YELLOW="Yellow";


    public static final String ROOF_TYPE_CLAY_TILE="Clay Tile";
    public static final int ROOF_TYPE_WARNING_YEAR=2000;

    public static final String SCREEN_SELECTION_OWNER="Owner Details";
    public static final String SCREEN_SELECTION_ROAD="Road Details";
    public static final String SCREEN_SELECTION_TENANAT="Tenant Details";
    public static final String SCREEN_SELECTION_TAX="Tax Details";
    public static final String SCREEN_SELECTION_ESTABLISHMENT="Establishment Details";
    public static final String SCREEN_SELECTION_MEMBER="Member Details";
    public static final String SCREEN_SELECTION_LIVEHOOD="Livelihood Details";
    public static final String SCREEN_SELECTION_MORE="More Details";
    public static final String SCREEN_SELECTION_BUILDING="Building Details";
    public static final String SCREEN_SELECTION_IMAGE="Common Details";

    public static final int REMARKS_LENGTH_LIMIT= 250;


}

