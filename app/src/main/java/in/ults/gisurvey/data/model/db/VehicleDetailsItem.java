package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammed Shafeeq on 2019-07-01.
 */
public class VehicleDetailsItem {

    @SerializedName("vehicle_type")
    private String vehicleType = "";

    @SerializedName("vehicle_usage")
    private String vehicleUsage = "";

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleUsage() {
        return vehicleUsage;
    }

    public void setVehicleUsage(String vehicleUsage) {
        this.vehicleUsage = vehicleUsage;
    }
}

