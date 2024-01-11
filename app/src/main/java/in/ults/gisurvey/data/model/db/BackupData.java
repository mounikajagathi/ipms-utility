package in.ults.gisurvey.data.model.db;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anuag on 15-07-2020
 **/
public class BackupData {
    @SerializedName("mActiveCount")
    private int mActiveCount;

    @SerializedName("mData")
    private ArrayList<SurveyWithProperty> mData ;

    @SerializedName("mDataLock")
    private JSONObject mDataLock ;

    @SerializedName("mObservers")
    private JSONObject mObservers;

    @SerializedName("mPendingData")
    private JSONObject mPendingData ;

    @SerializedName("mVersion")
    private String mVersion;

    @SerializedName("mDispatchInvalidated")
    private boolean mDispatchInvalidated;

    @SerializedName("mDispatchingValue")
    private boolean mDispatchingValue;

    public int getmActiveCount() {
        return mActiveCount;
    }

    public void setmActiveCount(int mActiveCount) {
        this.mActiveCount = mActiveCount;
    }

    public ArrayList<SurveyWithProperty> getmData() {
        return mData;
    }

    public void setmData(ArrayList<SurveyWithProperty> mData) {
        this.mData = mData;
    }

    public JSONObject getmDataLock() {
        return mDataLock;
    }

    public void setmDataLock(JSONObject mDataLock) {
        this.mDataLock = mDataLock;
    }

    public JSONObject getmObservers() {
        return mObservers;
    }

    public void setmObservers(JSONObject mObservers) {
        this.mObservers = mObservers;
    }

    public JSONObject getmPendingData() {
        return mPendingData;
    }

    public void setmPendingData(JSONObject mPendingData) {
        this.mPendingData = mPendingData;
    }

    public String getmVersion() {
        return mVersion;
    }

    public void setmVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public boolean ismDispatchInvalidated() {
        return mDispatchInvalidated;
    }

    public void setmDispatchInvalidated(boolean mDispatchInvalidated) {
        this.mDispatchInvalidated = mDispatchInvalidated;
    }

    public boolean ismDispatchingValue() {
        return mDispatchingValue;
    }

    public void setmDispatchingValue(boolean mDispatchingValue) {
        this.mDispatchingValue = mDispatchingValue;
    }
}
