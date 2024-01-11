package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anuag on 08-09-2020
 **/
public class SurveyPoints {
   @SerializedName("newproid")
    public String newproid;
    @SerializedName("longitude")
    public String longitude;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("propertyId")
    public String propertyId;
    @SerializedName("droneid")
    public String droneid;
    @SerializedName("globalid")
    public String globalid;
    @SerializedName("bldgstatus")
    public String bldgstatus;
    @SerializedName("doorstatus")
    public String doorstatus;

    public String getBldgstatus() {
        return bldgstatus;
    }

    public void setBldgstatus(String bldgstatus) {
        this.bldgstatus = bldgstatus;
    }

    public String getDoorstatus() {
        return doorstatus;
    }

    public void setDoorstatus(String doorstatus) {
        this.doorstatus = doorstatus;
    }

    public String getGlobalid() {
        return globalid;
    }

    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getDroneid() {
        return droneid;
    }

    public void setDroneid(String droneid) {
        this.droneid = droneid;
    }

    public String getNewproid() {
        return newproid;
    }

    public void setNewproid(String newproid) {
        this.newproid = newproid;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
