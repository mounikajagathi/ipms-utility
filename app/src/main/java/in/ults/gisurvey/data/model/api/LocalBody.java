package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.gisurvey.data.model.items.CommonItem;

public class LocalBody {

    @Expose
    @SerializedName("localbodyName")
    public String localBodyName;

    @Expose
    @SerializedName("localbodyCode")
    public String localBodyCode;

    @Expose
    @SerializedName("localbodyID")
    public String localBodyID;

    @Expose
    @SerializedName("ward")
    ArrayList<CommonItem> wardNumberArrayList;


    public String getLocalBodyName() {
        return localBodyName;
    }

    public String getLocalBodyCode() {
        return localBodyCode;
    }

    public String getLocalBodyID() {
        return localBodyID;
    }

    public ArrayList<CommonItem> getWardNumberArrayList() {
        return wardNumberArrayList;
    }
}
