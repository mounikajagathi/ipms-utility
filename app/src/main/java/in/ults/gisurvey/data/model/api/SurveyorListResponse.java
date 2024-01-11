package in.ults.gisurvey.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anuag on 16-11-2020
 **/
public class SurveyorListResponse {

    @Expose
    @SerializedName("endIndex")
    private boolean endIndex;
    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private List<Surveyor> data;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("recordCount")
    private int recordCount;

    public boolean getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(boolean endIndex) {
        this.endIndex = endIndex;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Surveyor> getData() {
        return data;
    }

    public void setData(List<Surveyor> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}
