package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

import in.ults.gisurvey.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class BuildingDetailsFloorAreaItem {

    @SerializedName("floor_no")
    private String floorNumber = "";

    @SerializedName("floor_no_display")
    private String floorNumberDispaly = "";

    @SerializedName("floor_area")
    private String area = "";

    public String getFloorNumberDispaly() {
        return floorNumberDispaly;
    }

    public void setFloorNumberDispaly(String floorNumberDispaly) {
        this.floorNumberDispaly = floorNumberDispaly;
    }
        public String getFloorNumber() {
        return floorNumber;
    }
//
    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

