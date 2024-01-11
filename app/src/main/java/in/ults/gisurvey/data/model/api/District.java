package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class District {

    @Expose
    @SerializedName("districtCode")
    public String districtCode;

    @Expose
    @SerializedName("districtMasterID")
    public String districtMasterID;

    @Expose
    @SerializedName("districtName")
    public String districtName;

    @Expose
    @SerializedName("localbody")
    public ArrayList<LocalBody> localBody;


    public String getDistrictCode() {
        return districtCode;
    }

    public String getDistrictMasterID() {
        return districtMasterID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public ArrayList<LocalBody> getLocalBody() {
        return localBody;
    }
}
