package in.ults.gisurvey.data.model.api;


import com.google.gson.annotations.SerializedName;

public final class LoginResponse {

    @SerializedName("basicDetails")
    private BasicDetails basicDetails;
    @SerializedName("token")
    private String token;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("series")
    private String series;

    public BasicDetails getBasicDetails() {
        return basicDetails;
    }

    public void setBasicDetails(BasicDetails basicDetails) {
        this.basicDetails = basicDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeries() {
        return series;
    }
}
