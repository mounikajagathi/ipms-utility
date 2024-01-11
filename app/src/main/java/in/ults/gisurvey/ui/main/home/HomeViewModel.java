package in.ults.gisurvey.ui.main.home;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.functions.Consumer;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private final ObservableField<String> userEmail = new ObservableField<>("");
    private final ObservableField<String> userName = new ObservableField<>("");
    private final ObservableField<String> userProfilePicUrl = new ObservableField<>();
    private final ObservableField<String> pendingSurveys = new ObservableField<>("0");
    private final ObservableField<String> newSurveys = new ObservableField<>("0");
    private final ObservableField<String> assignedSurveys = new ObservableField<>("0");
    private final ObservableField<String> completedSurveys = new ObservableField<>("0");


    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getUserEmail() {
        return userEmail;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getUserProfilePicUrl() {
        return userProfilePicUrl;
    }

    public ObservableField<String> getPendingSurveys() {
        return pendingSurveys;
    }

    public ObservableField<String> getNewSurveys() {
        return newSurveys;
    }

    public ObservableField<String> getAssignedSurveys() {
        return assignedSurveys;
    }

    public ObservableField<String> getCompletedSurveys() {
        return completedSurveys;
    }

    public void setPendingSurveyCount(int count) {
        pendingSurveys.set(String.valueOf(count));
    }


    public void setAssignedSurveyCount(String count) {
        assignedSurveys.set(count);
    }

    public void setCompletedSurveyCount(int count) {
        completedSurveys.set(String.valueOf(count));
    }


    @Override
    public void onPendingSurveys(List<Survey> survey) {
        setPendingSurveyCount(survey.size());
    }

    @Override
    protected void onCompletedSurvey(List<Survey> completedSurvey) {
        setCompletedSurveyCount(completedSurvey.size());
    }

    public void onPartialSurveyClick() {
        getNavigator().navigateToPartialSurvey();
    }

    public void onNewSurveyClick() {
        if (getDistrict() == null || getLocalBody() == null || getWardNumber() == null) {
            getBaseActivity().showToast(R.string.home_err_district);
        }else if (getSurveyPoints()==null) {
            getBaseActivity().showToast(R.string.ward_selection_error);
        } else if (getTpkFileLoc() == null) {
            getBaseActivity().showToast(R.string.home_err_tpk);
        } else if (getSurveyorName().equals(getBaseActivity().getString(R.string.settings_default))) {
            getBaseActivity().showToast(R.string.home_err_surveyor_name);
        }  else if (getMobileCode().equals(getBaseActivity().getString(R.string.settings_default))) {
            getBaseActivity().showToast(R.string.home_err_mobile_code);
        }  else {
            getNavigator().navigateToNewSurvey();
        }
    }

    void updateContent() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }

        final String currentUserEmail = getDataManager().getCurrentUserEmail();
        if (!TextUtils.isEmpty(currentUserEmail)) {
            userEmail.set(currentUserEmail);
        }

        final String profilePicUrl = getDataManager().getUserImage();
        if (!TextUtils.isEmpty(profilePicUrl)) {
            userProfilePicUrl.set(profilePicUrl);
        }
        setPendingSurveyCount(0);
        getPendingSurveyList();
        fetchCompletedSurvey();
    }

    /**
     * to fetch data from api response and set to dashboard field
     */
    public void fetchDashBoardContent() {
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getNavigator().showHomeProgress();
            getCompositeDisposable().add(getDataManager()
                    .doHomeDashboardApiCall(getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnError(throwable -> checkApiError(throwable, getNavigator()))
                    .doOnNext(dashboardResponse -> {
                        if (dashboardResponse.getStatus().equalsIgnoreCase("true")) {
                            getDataManager().saveDashBoardData(dashboardResponse.getDashboard());
                            getDataManager().setUserImage(dashboardResponse.getDashboard().getBasicDetails().getUserImage());
                            getDataManager().setCurrentUserName(dashboardResponse.getDashboard().getBasicDetails().getUserName());
                            getDataManager().setCurrentUserEmail(dashboardResponse.getDashboard().getBasicDetails().getEmail());
                            getDataManager().setCurrentUserId(dashboardResponse.getDashboard().getBasicDetails().getUserAccessId());
                            getDataManager().setUserActive(dashboardResponse.getDashboard().getBasicDetails().isActive());
                            updateContent();
                        }
                        getNavigator().setDashboardContent();
                    }).subscribe());
        } else {
            getNavigator().setDashboardContent();
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }
}