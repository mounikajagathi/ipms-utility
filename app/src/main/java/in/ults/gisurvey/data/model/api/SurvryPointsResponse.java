package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anuag on 08-09-2020
 **/
public class SurvryPointsResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<SurveyPoints> surveyPointsList;

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

    public ArrayList<SurveyPoints> getSurveyPointsList() {
        return surveyPointsList;
    }

    public void setSurveyPointsList(ArrayList<SurveyPoints> surveyPointsList) {
        this.surveyPointsList = surveyPointsList;
    }
}
