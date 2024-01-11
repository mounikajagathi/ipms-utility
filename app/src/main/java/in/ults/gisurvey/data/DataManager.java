package in.ults.gisurvey.data;


import in.ults.gisurvey.data.local.db.DbHelper;
import in.ults.gisurvey.data.local.prefs.PreferencesHelper;
import in.ults.gisurvey.data.remote.ApiHelper;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(String accessToken);

    void updateUserInfo(
            String accessToken,
            String series,
            int userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String userImage,
            boolean active);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
