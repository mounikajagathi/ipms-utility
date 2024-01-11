package in.ults.gisurvey.data.remote;

import com.androidnetworking.interfaces.UploadProgressListener;

import java.io.File;
import java.util.List;

import in.ults.gisurvey.data.model.api.DashboardResponse;
import in.ults.gisurvey.data.model.api.DataSyncResponse;
import in.ults.gisurvey.data.model.api.ForgotPasswordResponse;
import in.ults.gisurvey.data.model.api.ImageSyncResponse;
import in.ults.gisurvey.data.model.api.LoginResponse;
import in.ults.gisurvey.data.model.api.SurveyorListResponse;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.api.UrlCheckResponse;
import io.reactivex.Observable;

public interface ApiHelper {

    Observable<LoginResponse> doServerLoginApiCall(String request,String url);

    Observable<UrlCheckResponse> doApiURLCheck(String url);

    Observable<ForgotPasswordResponse> doForgotPasswordApiCall(String request,String serverUrl);

    Observable<DashboardResponse> doHomeDashboardApiCall(String serverUrl);

    Observable<SurveyorListResponse> surveyorApiCall(String serverUrl);

    ApiHeader getApiHeader();

    Observable<DataSyncResponse> doDataSyncToServer(String body,String token,String serverUrl);

    Observable<SurvryPointsResponse> doSurveyPointsApiCall(String wardNo,String serverUrl);

    Observable<ImageSyncResponse> uploadMultipleImages(List<File> data, String token, UploadProgressListener listener,String serverUrl);

}
