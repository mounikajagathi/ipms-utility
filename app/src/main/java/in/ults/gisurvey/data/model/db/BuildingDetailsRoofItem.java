package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

import in.ults.gisurvey.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BuildingDetailsRoofItem {

    @SerializedName("rooftype")
    private String roofType = "";

    @SerializedName("rftypeper")
    private String roofPercent= "";

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getRoofPercent() {
        return roofPercent;
    }

    public void setRoofPercent(String roofPercent) {
        this.roofPercent = roofPercent;
    }
}

