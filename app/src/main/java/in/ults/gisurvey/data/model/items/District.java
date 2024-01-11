package in.ults.gisurvey.data.model.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class District {
    public int id;

    @Expose
    @SerializedName("district_name")
    public String districtName;

    @Expose
    @SerializedName("local_body")
    ArrayList<LocalBody> localBodyArrayList;

    public District(int id, String districtName, ArrayList<LocalBody> localBodyArrayList) {
        this.id = id;
        this.districtName = districtName;
        this.localBodyArrayList = localBodyArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public ArrayList<LocalBody> getLocalBodyArrayList() {
        return localBodyArrayList;
    }

    public void setLocalBodyArrayList(ArrayList<LocalBody> localBodyArrayList) {
        this.localBodyArrayList = localBodyArrayList;
    }
}
