package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

import in.ults.gisurvey.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BasementAreaItem {

    @SerializedName("basement_no")
    private String basementNumber = "";

    @SerializedName("basement_area")
    private String area = "";

    public String getBasementNumber() {
        return basementNumber;
    }

    public void setBasementNumber(String basementNumber) {
        this.basementNumber = basementNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

