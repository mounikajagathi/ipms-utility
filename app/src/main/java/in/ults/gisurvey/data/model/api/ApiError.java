package in.ults.gisurvey.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ApiError {


    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("error")
    private String error;

    public ApiError(String status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ApiError apiError = (ApiError) object;

        if (!Objects.equals(status, apiError.status)) {
            return false;
        }

        if (!Objects.equals(error, apiError.error)) {
            return false;
        }
        return Objects.equals(message, apiError.message);

    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }
}
