package in.ults.gisurvey.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRequest {

    @Expose
    @SerializedName("usernameOremail")
    private String usernameOrEmail;


    public ForgotPasswordRequest(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}
