package in.ults.gisurvey.data.model.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocalBody {
    public int id;

    @Expose
    @SerializedName("local_body_name")
    public String localBodyName;

    @Expose
    @SerializedName("ward_number")
    ArrayList<CommonItem> wardNumberArrayList;

    @Expose
    @SerializedName("village")
    ArrayList<CommonItem> villageArrayList;

    @Expose
    @SerializedName("post_office")
    ArrayList<CommonItem> postOfficeArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalBodyName() {
        return localBodyName;
    }

    public void setLocalBodyName(String localBodyName) {
        this.localBodyName = localBodyName;
    }

    public ArrayList<CommonItem> getWardNumberArrayList() {
        return wardNumberArrayList;
    }

    public void setWardNumberArrayList(ArrayList<CommonItem> wardNumberArrayList) {
        this.wardNumberArrayList = wardNumberArrayList;
    }

    public ArrayList<CommonItem> getVillageArrayList() {
        return villageArrayList;
    }

    public void setVillageArrayList(ArrayList<CommonItem> villageArrayList) {
        this.villageArrayList = villageArrayList;
    }

    public ArrayList<CommonItem> getPostOfficeArrayList() {
        return postOfficeArrayList;
    }

    public void setPostOfficeArrayList(ArrayList<CommonItem> postOfficeArrayList) {
        this.postOfficeArrayList = postOfficeArrayList;
    }
}
