package in.ults.gisurvey.data.local.prefs;
import java.util.ArrayList;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.Dashboard;

/**
 * interface to set and fetch data from preference through data manager
 */
public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getServerUrl();

    void setServerUrl(String url);

    String getUrl();

    void setUrl(String url);

    String getPort();

    void setPort(String port);

    String getSeries();

    void setSeries(String series);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    int getCurrentUserId();

    void setCurrentUserId(int userId);

    void setUserImage(String userImage);

    String getUserImage();

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    boolean getUserActive();

    void setUserActive(boolean active);

    void setCurrentSurveyID(String surveyID);

    String getCurrentSurveyID();

    void setSurveyOpenMode(String mode);

    String getSurveyOpenMode();

    void setCurrentPropertyId(String propertyID);

    String getCurrentPropertyId();

    void setCurrentPointStatus(String pointStatus);

    String getCurrentPointStatus();

    void saveDashBoardData(Dashboard dashboard);

    Dashboard getDashBoardData();

    String getDistrict();

    String getLocalBody();

    String getLocalbodyCode();

    String getWardNumber();

    String getWardName();

    String getSurveyorName();

    String getMobileCOde();

    void setDistrict(String district);

    void setLocalBody(String localBody);

    void setLocalbodyCode(String localbodyCode);

    void setWardNumber(String wardNumber);

    void setWardName(String wardName);

    String getTpkFileLocation();

    void setTpkFileLocation(String tpkFileLocation);

    void setSurveyorName(String surveyorName);

    void setMobileCode(String code);

    String getSelectedPropId();

    void setSelectedPropId(String propId);

    String getSelectedDroneId();

    void setSelectedDroneId(String droneId);

    String getSelectedNewPropId();

    void setSelectedNewPropId(String newPropId);

    String getSelectedServerSurveyId();

    void setSelectedServerSurveyId(String id);

    String getARFileLocation();

    void setARFileLocation(String arFileLocation);

    String getInfoVideoFileLocation();

    void setInfoVideoFileLocation(String infoVideoFileLocation);

    String getLiveLocationStatus();

    void setLiveLocationStatus(String liveloc);

    void saveSurveyPointData(SurvryPointsResponse survryPointsResponse);

    SurvryPointsResponse getSurveyPoints();

    String getCompletedListType();

    void setCompletedListType(String type);

    ArrayList<Surveyor> getSelectedSurveyorDetails();

    void setSelectedSurveyorDetails(ArrayList<Surveyor> surveyorData);

    ArrayList<String> getSelectedSurveyorNames();

    void setSelectedSurveyorNames(ArrayList<String> surveyorsNames);

    ArrayList<String> getSelectedSurveyPointWards();

    void setSelectedSurveyPointWards(ArrayList<String> wards);
}
