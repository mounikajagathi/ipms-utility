package in.ults.gisurvey.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

import in.ults.gisurvey.data.local.db.converter.FloorAreaConverter;
import in.ults.gisurvey.data.local.db.converter.MemberDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.RoofTypeConverter;
import in.ults.gisurvey.data.local.db.converter.StringListConverter;
import in.ults.gisurvey.utils.AppConstants;

import static androidx.room.ForeignKey.CASCADE;


public class Owner {

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


    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerOccupation() {
        return ownerOccupation;
    }

    public String getOwnerHouseNameNumber() {
        return ownerHouseNameNumber;
    }

    public String getOwnerStreetPlaceName() {
        return ownerStreetPlaceName;
    }

    public String getOwnerPostOffice() {
        return ownerPostOffice;
    }

    public String getOwnerPincode() {
        return ownerPincode;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOwnerLandLine() {
        return ownerLandLine;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public String getOwnerState() {
        return ownerState;
    }
}
