package in.ults.gisurvey.data.remote;

import in.ults.gisurvey.BuildConfig;

final class ApiConstants {

    static final String API_CONTENT_TYPE = "application/json";

    static final String ENDPOINT_SERVER_LOGIN =  "/auth/login";

    static final String ENDPOINT_SERVER_URL_CHECK = "/service/status";

    static final String ENDPOINT_FORGOT_PASSWORD =  "/user/forgotpassword";

    static final String ENDPOINT_DASHBOARD =  "/dashboard";

    static final String ENDPOINT_SURVEYOR_LIST =  "/employees";

    static final String ENDPOINT_SYNC_DATA = "/new/assets";

    static final String ENDPOINT_IMAGE_UPLOAD =  "/uploadMultipleFiles";

    static final String ENDPOINT_SURVEY_POINTS = "/getdatabyward?ward=";

    static final String KEY_UPLOAD_FILES = "files";

    static final String KEY_BEARER = "Bearer ";

    private ApiConstants() {
        // This class is not publicly instantiable
    }
}
