package in.ults.gisurvey.data.remote;

import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.gisurvey.data.local.prefs.AppPreferencesHelper;
import in.ults.gisurvey.data.model.api.DashboardResponse;
import in.ults.gisurvey.data.model.api.DataSyncResponse;
import in.ults.gisurvey.data.model.api.ForgotPasswordResponse;
import in.ults.gisurvey.data.model.api.ImageSyncResponse;
import in.ults.gisurvey.data.model.api.LoginResponse;
import in.ults.gisurvey.data.model.api.SurveyorListResponse;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.api.UrlCheckResponse;
import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private AppPreferencesHelper preferencesHelper;

    @Inject
    AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    /**
     * server login api call
     * @param request
     * @return
     */
    @Override
    public Observable<LoginResponse> doServerLoginApiCall(String request,String serverUrl) {
        return Rx2AndroidNetworking.post(serverUrl+ApiConstants.ENDPOINT_SERVER_LOGIN)
                .setContentType(ApiConstants.API_CONTENT_TYPE)
                .addStringBody(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }
    /**
     * server url check
     * @return
     */
    @Override
    public Observable<UrlCheckResponse> doApiURLCheck(String serverUrl) {
        return Rx2AndroidNetworking.post(serverUrl+ApiConstants.ENDPOINT_SERVER_URL_CHECK)
                .setContentType(ApiConstants.API_CONTENT_TYPE)
                .build()
                .getObjectObservable(UrlCheckResponse.class);
    }
    /**
     * networking call for forgot password
     * @param request
     * @return
     */
    @Override
    public Observable<ForgotPasswordResponse> doForgotPasswordApiCall(String request,String serverUrl) {
        return Rx2AndroidNetworking.post(serverUrl+ApiConstants.ENDPOINT_FORGOT_PASSWORD)
                .setContentType(ApiConstants.API_CONTENT_TYPE)
                .addStringBody(request)
                .build()
                .getObjectObservable(ForgotPasswordResponse.class);
    }

    /**
     * api call for multiple image loading
     * @param data
     * @param token
     * @return
     */
    @Override
    public Observable<ImageSyncResponse> uploadMultipleImages(List<File> data, String token,UploadProgressListener listener,String serverUrl) {
        return Rx2AndroidNetworking.upload(serverUrl+ApiConstants.ENDPOINT_IMAGE_UPLOAD)
                .addMultipartFileList(ApiConstants.KEY_UPLOAD_FILES, data)
                .setPriority(Priority.HIGH)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .setUploadProgressListener(listener)
                .getObjectObservable(ImageSyncResponse.class);
    }

    /**
     * api call for data syncing to server
     * @param body
     * @param token
     * @return
     */
    @Override
    public Observable<DataSyncResponse> doDataSyncToServer(String body, String token,String serverUrl) {
        return Rx2AndroidNetworking.post(serverUrl+ApiConstants.ENDPOINT_SYNC_DATA)
                .setContentType(ApiConstants.API_CONTENT_TYPE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addStringBody(body)
                .build()
                .getObjectObservable(DataSyncResponse.class);
    }
    /**
     * api call for surveyor list
     * @return
     */
    @Override
    public Observable<SurveyorListResponse> surveyorApiCall(String serverUrl) {
        return Rx2AndroidNetworking.get(serverUrl+ApiConstants.ENDPOINT_SURVEYOR_LIST)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(SurveyorListResponse.class);
    }
    /**
     * api call for home dashboard data loading
     * @return
     */
    @Override
    public Observable<DashboardResponse> doHomeDashboardApiCall(String serverUrl) {
        return Rx2AndroidNetworking.get(serverUrl+ApiConstants.ENDPOINT_DASHBOARD)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(DashboardResponse.class);
    }


    /**
     * Api call for getting server survey points
     * @param wardNo
     * @param serverUrl
     * @return
     */
    @Override
    public Observable<SurvryPointsResponse> doSurveyPointsApiCall(String wardNo,String serverUrl) {
        return Rx2AndroidNetworking.get(serverUrl+ApiConstants.ENDPOINT_SURVEY_POINTS+wardNo)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(SurvryPointsResponse.class);
    }
}
