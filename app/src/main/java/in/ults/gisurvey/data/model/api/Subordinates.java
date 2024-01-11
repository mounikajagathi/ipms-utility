package in.ults.gisurvey.data.model.api;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subordinates {

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


    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getRole() {
        return role;
    }
}
