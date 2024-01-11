package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Employee {

    @SerializedName("employeeID")
    @Expose
    public String employeeId ;

    @SerializedName("employeeName")
    @Expose
    public String employeeName ;

    @SerializedName("employeeType")
    @Expose
    public String employeeType ;

    @SerializedName("role")
    @Expose
    public String role ;

    @SerializedName("subordinates")
    @Expose
    public ArrayList<Subordinates> subordinates;

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public ArrayList<Subordinates> getSubordinates() {
        return subordinates;
    }

    public String getRole() {
        return role;
    }
}
