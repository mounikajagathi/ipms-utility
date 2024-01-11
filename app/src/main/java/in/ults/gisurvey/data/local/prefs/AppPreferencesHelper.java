package in.ults.gisurvey.data.local.prefs;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.di.PreferenceInfo;
import in.ults.gisurvey.utils.AppConstants;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_SERVER_URL = "PREF_KEY_SERVER_URL";

    private static final String PREF_KEY_URL = "PREF_KEY_URL";

    private static final String PREF_KEY_PORT = "PREF_KEY_PORT";

    private static final String PREF_KEY_SERIES = "PREF_KEY_SERIES";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";

    private static final String PREF_KEY_USER_IMAGE = "PREF_KEY_USER_IMAGE";

    private static final String PREF_KEY_CURRENT_USER_ACTIVE = "PREF_KEY_CURRENT_USER_ACTIVE";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_CURRENT_SURVEY_ID = "PREF_KEY_CURRENT_SURVEY_ID";

    private static final String PREF_KEY_SURVEY_OPEN_MODE = "PREF_KEY_SURVEY_OPEN_MODE";

    private static final String PREF_KEY_CURRENT_PROPERTY_ID = "PREF_KEY_CURRENT_PROPERTY_ID";

    private static final String PREF_KEY_CURRENT_POINT_STATUS = "PREF_KEY_CURRENT_POINT_STATUS";

    private static final String PREF_KEY_DASH_BOARD = "PREF_KEY_DASHBOARD";

    private static final String PREF_KEY_SURVEY_POINT = "PREF_KEY_SURVEYPOINTS";

    private static final String PREF_KEY_DISTRICT = "PREF_KEY_DISTRICT";

    private static final String PREF_KEY_LOCAL_BODY = "PREF_KEY_LOCAL_BODY";

    private static final String PREF_KEY_LOCAL_BODY_CODE = "PREF_KEY_LOCAL_BODY_CODE";

    private static final String PREF_KEY_WARD_NUMBER = "PREF_KEY_WARD_NUMBER";

    private static final String PREF_KEY_WARD_NAME = "PREF_KEY_WARD_NAME";

    private static final String PREF_KEY_TPK_FILE_LOC = "PREF_KEY_TPK_FILE_LOCATION";

    private static final String PREF_SURVEYOR_NAME = "PREF_KEY_SURVEYOR_NAME";

    private static final String  PREF_MOBILE_CODE="PREF_MOBILE_CODE";

    private static final String PREF_KEY_AR_FILE_LOC = "PREF_KEY_AR_FILE_LOCATION";

    private static final String PREF_KEY_INFO_VIDEO_FILE_LOC = "PREF_KEY_INFO_VIDEO_FILE_LOCATION";

    private static final String PREF_KEY_SELECTED_PROPID="PREF_KEY_SELECTED_PROPID";

    private static final String PREF_KEY_SELECTED_DRONEID="PREF_KEY_SELECTED_DRONEID";

    private static final String PREF_KEY_SELECTED_SERVER_SURVEYID="PREF_KEY_SELECTED_SERVER_SURVEYID";

    private static final String PREF_KEY_SELECTED_NEW_PROP_ID="PREF_KEY_SELECTED_NEW_PROP_ID";

    private static final String PREF_KEY_LIVE_LOCATION_STATUS="PREF_KEY_LIVE_LOCATION_STATUS";

    private static final String PREF_KEY_COMPLETED_LIST_TYPE="PREF_KEY_COMPLETED_LIST_TYPE";

    private static final String PREF_KEY_SELECTEDSURVEY_POINT_WARD="PREF_KEY_SELECTEDSURVEY_POINT_WARD";

    private static final String PREF_SELECTED_SURVEYORS_DATA ="PREF_SELECTED_SURVEYORS_DATA";

    private static final String PREF_SELECTED_SURVEYORS_NAME="PREF_SELECTED_SURVEYORS_NAME";

    private final SharedPreferences mPrefs;

    @Inject
    AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    /**
     * to fetch access token from preference
     * @return
     */
    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    /**
     * to insert access token to preference
     * @param accessToken
     */
    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getServerUrl() {
        return mPrefs.getString(PREF_KEY_SERVER_URL, null);
    }

    @Override
    public void setServerUrl(String url) {
        mPrefs.edit().putString(PREF_KEY_SERVER_URL, url).apply();
    }

    @Override
    public String getUrl() {
        return mPrefs.getString(PREF_KEY_URL, null);
    }

    @Override
    public void setUrl(String url) {
        mPrefs.edit().putString(PREF_KEY_URL, url).apply();
    }

    @Override
    public String getPort() {
        return mPrefs.getString(PREF_KEY_PORT, null);
    }

    @Override
    public void setPort(String port) {
        mPrefs.edit().putString(PREF_KEY_PORT, port).apply();
    }

    /**
     * to fetch series from preference
     * @return
     */
    @Override
    public String getSeries() {
            return mPrefs.getString(PREF_KEY_SERIES, null);
    }

    /**
     * to insert series to preference
     * @param series
     */
    @Override
    public void setSeries(String series) {
        mPrefs.edit().putString(PREF_KEY_SERIES, series).apply();
    }

    /**
     * to fetch
     * @return
     */
    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public int getCurrentUserId() {
        return mPrefs.getInt(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
    }

    @Override
    public void setCurrentUserId(int userId) {
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public boolean getUserActive() {
        return mPrefs.getBoolean(PREF_KEY_CURRENT_USER_ACTIVE, false);
    }

    @Override
    public void setUserActive(boolean active) {
        mPrefs.edit().putBoolean(PREF_KEY_CURRENT_USER_ACTIVE, active).apply();
    }

    /**
     * set current survey id in shared preference
     *
     */
    @Override
    public void setCurrentSurveyID(String surveyID) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_SURVEY_ID, surveyID).apply();
    }

    @Override
    public String getCurrentSurveyID() {
        return mPrefs.getString(PREF_KEY_CURRENT_SURVEY_ID, "");
    }

    /**
     * setting survey open mode
     * @param mode
     */
    @Override
    public void setSurveyOpenMode(String mode) {
        mPrefs.edit().putString(PREF_KEY_SURVEY_OPEN_MODE, mode).apply();
    }

    /**
     * getting survey open mode
     */
    @Override
    public String getSurveyOpenMode() {
        return mPrefs.getString(PREF_KEY_SURVEY_OPEN_MODE, "");
    }

    @Override
    public String getCurrentPropertyId() {
        return mPrefs.getString(PREF_KEY_CURRENT_PROPERTY_ID, "");
    }

    /**
     * to store current property id in shared preference
     * @param propertyID
     */
    @Override
    public void setCurrentPropertyId(String propertyID) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_PROPERTY_ID, propertyID).apply();

    }

    @Override
    public void setCurrentPointStatus(String pointStatus) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_POINT_STATUS, pointStatus).apply();
    }

    @Override
    public String getCurrentPointStatus() {
        return mPrefs.getString(PREF_KEY_CURRENT_POINT_STATUS, "");
    }

    @Override
    public void setUserImage(String userImage) {
        mPrefs.edit().putString(PREF_KEY_USER_IMAGE, userImage).apply();

    }

    @Override
    public String getUserImage() {
        return mPrefs.getString(PREF_KEY_USER_IMAGE, "");
    }


    @Override
    public void saveDashBoardData(Dashboard dashboard) {
        String data = new Gson().toJson(dashboard);
        mPrefs.edit().putString(PREF_KEY_DASH_BOARD, data).apply();
    }


    @Override
    public Dashboard getDashBoardData() {
        String data = mPrefs.getString(PREF_KEY_DASH_BOARD, null);
        Dashboard dashboard = null;
        if (data != null) {
            dashboard = new Gson().fromJson(data, Dashboard.class);
        }
        return dashboard;
    }

    @Override
    public String getDistrict() {
        return mPrefs.getString(PREF_KEY_DISTRICT, null);
    }

    @Override
    public String getLocalBody() {
        return mPrefs.getString(PREF_KEY_LOCAL_BODY, null);
    }

    @Override
    public String getWardNumber() {
        return mPrefs.getString(PREF_KEY_WARD_NUMBER,null);
    }

    @Override
    public String getWardName() {
        return  mPrefs.getString(PREF_KEY_WARD_NAME,null);
    }


    @Override
    public String getLocalbodyCode() {
        return mPrefs.getString(PREF_KEY_LOCAL_BODY_CODE,null);
    }

    @Override
    public String getSurveyorName() {
        return mPrefs.getString(PREF_SURVEYOR_NAME,null);
    }

    @Override
    public String getMobileCOde() {
        return mPrefs.getString(PREF_MOBILE_CODE,null);
    }

    @Override
    public void setDistrict(String district) {
        mPrefs.edit().putString(PREF_KEY_DISTRICT, district).apply();
    }

    @Override
    public void setLocalBody(String localBody) {
        mPrefs.edit().putString(PREF_KEY_LOCAL_BODY, localBody).apply();
    }
    @Override
    public void setWardNumber(String wardNumber) {
        mPrefs.edit().putString(PREF_KEY_WARD_NUMBER, wardNumber).apply();
    }

    @Override
    public void setWardName(String wardName) {
        mPrefs.edit().putString(PREF_KEY_WARD_NAME,wardName).apply();

    }

    @Override
    public void setLocalbodyCode(String localbodyCode) {
        mPrefs.edit().putString(PREF_KEY_LOCAL_BODY_CODE, localbodyCode).apply();
    }

    @Override
    public String getTpkFileLocation() {
        return mPrefs.getString(PREF_KEY_TPK_FILE_LOC, null);
    }

    @Override
    public void setTpkFileLocation(String tpkFileLocation) {
        mPrefs.edit().putString(PREF_KEY_TPK_FILE_LOC, tpkFileLocation).apply();
    }

    @Override
    public void setSurveyorName(String surveyorName) {
        mPrefs.edit().putString(PREF_SURVEYOR_NAME, surveyorName).apply();
    }

    @Override
    public void setMobileCode(String code) {
        mPrefs.edit().putString(PREF_MOBILE_CODE, code).apply();
    }

    /**
     * This is to get selected server property id for its details
     * @return
     */
    @Override
    public String getSelectedPropId() {
        return mPrefs.getString(PREF_KEY_SELECTED_PROPID, null);
    }
    /**
     * This is to set selected server property id for its details
     * @return
     */
    @Override
    public void setSelectedPropId(String propId) {
        mPrefs.edit().putString(PREF_KEY_SELECTED_PROPID, propId).apply();
    }
    /**
     * This is to get selected server drone id for its details
     * @return
     */
    @Override
    public String getSelectedDroneId() {
        return mPrefs.getString(PREF_KEY_SELECTED_DRONEID, null);
    }
    /**
     * This is to set selected server drone id for its details
     * @return
     */
    @Override
    public void setSelectedDroneId(String droneId) {
        mPrefs.edit().putString(PREF_KEY_SELECTED_DRONEID, droneId).apply();
    }

    /**
     * This is to get selected server new property id for its details
     * @return
     */
    @Override
    public String getSelectedNewPropId() {
        return mPrefs.getString(PREF_KEY_SELECTED_NEW_PROP_ID, null);
    }

    /**
     * This is to set selected server new property id for its details
     * @return
     */
    @Override
    public void setSelectedNewPropId(String newPropId) {
        mPrefs.edit().putString(PREF_KEY_SELECTED_NEW_PROP_ID, newPropId).apply();
    }
    /**
     * This is to get selected server survey id for its details
     * @return
     */
    @Override
    public String getSelectedServerSurveyId() {
        return mPrefs.getString(PREF_KEY_SELECTED_SERVER_SURVEYID, null);
    }
    /**
     * This is to set selected server survey id for its details
     * @return
     */
    @Override
    public void setSelectedServerSurveyId(String id) {
        mPrefs.edit().putString(PREF_KEY_SELECTED_SERVER_SURVEYID, id).apply();
    }

    @Override
    public String getARFileLocation() {
        return mPrefs.getString(PREF_KEY_AR_FILE_LOC, null);
    }

    @Override
    public void setARFileLocation(String arFileLocation) {
        mPrefs.edit().putString(PREF_KEY_AR_FILE_LOC, arFileLocation).apply();
    }

    @Override
    public String getInfoVideoFileLocation() {
        return mPrefs.getString(PREF_KEY_INFO_VIDEO_FILE_LOC, null);
    }

    @Override
    public void setInfoVideoFileLocation(String videoFileLocation) {
        mPrefs.edit().putString(PREF_KEY_INFO_VIDEO_FILE_LOC, videoFileLocation).apply();
    }

    @Override
    public String getLiveLocationStatus() {
        return mPrefs.getString(PREF_KEY_LIVE_LOCATION_STATUS, null);
    }

    @Override
    public void setLiveLocationStatus(String liveloc) {
        mPrefs.edit().putString(PREF_KEY_LIVE_LOCATION_STATUS, liveloc).apply();
    }

    @Override
    public void saveSurveyPointData(SurvryPointsResponse survryPointsResponse) {

        String data = new Gson().toJson(survryPointsResponse);
        mPrefs.edit().putString(PREF_KEY_SURVEY_POINT, data).apply();
    }

    @Override
    public SurvryPointsResponse getSurveyPoints() {
        String data = mPrefs.getString(PREF_KEY_SURVEY_POINT, null);
        SurvryPointsResponse surveyPoints = null;
        if (data != null) {
            surveyPoints = new Gson().fromJson(data, SurvryPointsResponse.class);
        }
        return surveyPoints;
    }

    @Override
    public String getCompletedListType() {
        return mPrefs.getString(PREF_KEY_COMPLETED_LIST_TYPE, null);
    }

    @Override
    public void setCompletedListType(String type) {
        mPrefs.edit().putString(PREF_KEY_COMPLETED_LIST_TYPE, type).apply();
    }
    @Override
    public void setSelectedSurveyorDetails(ArrayList<Surveyor> surveyorData) {
        Gson gson = new Gson();
        String json = gson.toJson(surveyorData);
        mPrefs.edit().putString(PREF_SELECTED_SURVEYORS_DATA, json).apply();

    }

    @Override
    public ArrayList<Surveyor> getSelectedSurveyorDetails() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_SELECTED_SURVEYORS_DATA, null);
        Type type = new TypeToken<ArrayList<Surveyor>>() {}.getType();
        ArrayList<Surveyor> surveyorsData = gson.fromJson(json, type);
        if (surveyorsData == null) {
            surveyorsData = new ArrayList<>();
        }
        return surveyorsData;
    }
    @Override
    public ArrayList<String> getSelectedSurveyorNames() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_SELECTED_SURVEYORS_NAME, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> surveyors = gson.fromJson(json, type);
        if (surveyors == null) {
            surveyors = new ArrayList<>();
        }
        return surveyors;
    }

    @Override
    public void setSelectedSurveyorNames(ArrayList<String> surveyorsNames) {
        Gson gson = new Gson();
        String json = gson.toJson(surveyorsNames);
        mPrefs.edit().putString(PREF_SELECTED_SURVEYORS_NAME, json).apply();
    }

    @Override
    public ArrayList<String> getSelectedSurveyPointWards() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_SELECTEDSURVEY_POINT_WARD, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> wards = gson.fromJson(json, type);
        if (wards == null) {
            wards = new ArrayList<>();
        }
        return wards;
    }

    @Override
    public void setSelectedSurveyPointWards(ArrayList<String> wards) {
        Gson gson = new Gson();
        String json = gson.toJson(wards);
        mPrefs.edit().putString(PREF_KEY_SELECTEDSURVEY_POINT_WARD, json).apply();
    }
}
