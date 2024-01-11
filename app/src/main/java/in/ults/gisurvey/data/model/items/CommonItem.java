package in.ults.gisurvey.data.model.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommonItem {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("sub_content")
    private String subContent;

    @Expose
    @SerializedName("sub")
    ArrayList<CommonItem> subList;

    @Expose
    @SerializedName("sub_usage_content")
    private String subUsageContent;

    @Expose
    @SerializedName("usage")
    ArrayList<CommonItem> subUsageList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<CommonItem> getSubList() {
        return subList;
    }

    public void setSubList(ArrayList<CommonItem> subList) {
        this.subList = subList;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public String getSubUsageContent() {
        return subUsageContent;
    }

    public void setSubUsageContent(String subUsageContent) {
        this.subUsageContent = subUsageContent;
    }

    public ArrayList<CommonItem> getSubUsageList() {
        return subUsageList;
    }

    public void setSubUsageList(ArrayList<CommonItem> subUsageList) {
        this.subUsageList = subUsageList;
    }
}
