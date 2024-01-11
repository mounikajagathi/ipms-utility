package in.ults.gisurvey.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("password")
    private String password;


    @Expose
    @SerializedName("device_id")
    private String deviceID;


    public LoginRequest(String username, String password, String deviceID) {
        this.username = username;
        this.password = password;
        this.deviceID = deviceID;
    }
}
