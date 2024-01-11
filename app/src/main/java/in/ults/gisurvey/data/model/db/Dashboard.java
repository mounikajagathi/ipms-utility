package in.ults.gisurvey.data.model.db;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.gisurvey.data.model.api.BasicDetails;
import in.ults.gisurvey.data.model.api.District;
import in.ults.gisurvey.data.model.api.Employee;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.items.CommonItem;

public final class Dashboard {


    @SerializedName("userAccess")
    @Expose
    public BasicDetails basicDetails;

    @SerializedName("employee")
    @Expose
    public Employee employee;

    @SerializedName("district")
    @Expose
    public ArrayList<District> district;

    @SerializedName("village")
    @Expose
    public ArrayList<CommonItem> village;

    @SerializedName("postOffice")
    @Expose
    public ArrayList<CommonItem> postOffice;

    @SerializedName("assignedFromDate")
    @Expose
    public String assignedFromDate;

    @SerializedName("assignedSurveys")
    @Expose
    public String assignedSurveys;

    @SerializedName("assignedToDate")
    @Expose
    public String assignedToDate;

    @SerializedName("completedSurveys")
    @Expose
    public String completedSurveys;

    @SerializedName("version")
    @Expose
    public String version;


    public BasicDetails getBasicDetails() {
        return basicDetails;
    }

    public ArrayList<District> getDistrict() {
        return district;
    }

    public ArrayList<CommonItem> getVillage() {
        return village;
    }

    public ArrayList<CommonItem> getPostOffice() {
        return postOffice;
    }

    public String getAssignedFromDate() {
        return assignedFromDate;
    }

    public String getAssignedSurveys() {
        return assignedSurveys;
    }

    public String getAssignedToDate() {
        return assignedToDate;
    }

    public String getCompletedSurveys() {
        return completedSurveys;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getVersion() {
        return version;
    }
}
