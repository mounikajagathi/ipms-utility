package in.ults.gisurvey.ui.auth.login;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.LoginRequest;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * validation of username and password
     * @param username
     * @param password
     * @return
     */
    protected boolean validateFields(String username, String password) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(username)) {
            getNavigator().validationError(LoginFragment.USERNAME_ERROR, getBaseActivity().getString(R.string.enter_username));
            return false;
        } else if (TextUtils.isEmpty(password)) {
            getNavigator().validationError(LoginFragment.PASSWORD_ERROR, getBaseActivity().getString(R.string.enter_password));
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * store the basic login details in local if login response is true
     * @param username
     * @param password
     */
    public void login(String username, String password,String deviceId) {
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getNavigator().showProgressDialog();
            getCompositeDisposable().add(getDataManager()
                    .doServerLoginApiCall(new Gson().toJson(new LoginRequest(username, password, deviceId)),getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getNavigator().hideProgressDialog())
                   .doOnNext(loginResponse -> {
                        if (loginResponse.getStatus().equalsIgnoreCase("true")) {
                            getDataManager()
                                    .updateUserInfo(
                                            loginResponse.getToken(),
                                            loginResponse.getSeries(),
                                            loginResponse.getBasicDetails().getUserAccessId(),
                                            DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                            loginResponse.getBasicDetails().getUserName(),
                                            loginResponse.getBasicDetails().getEmail(),
                                            loginResponse.getBasicDetails().getUserImage(),
                                            loginResponse.getBasicDetails().isActive());
                            if(getWardNumber()!=null&&!getWardNumber().equalsIgnoreCase(getBaseActivity().getString(R.string.settings_default))) {
                                String moreWardSelected=getWardNumber();
                                if(getSelectedWardsInString().length()!=0&&!getSelectedWardsInString().equalsIgnoreCase(getBaseActivity().getString(R.string.settings_default))) {
                                    moreWardSelected=moreWardSelected+", "+getSelectedWardsInString();
                                }
                                fetchServerSurveyPoints(moreWardSelected);
                            }else{
                                getNavigator().openMainActivity();
                            }

                        } else {
                            getBaseActivity().showToast(loginResponse.getMessage());
                        }
                    }).subscribe());
        } else {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }

    /**
     * API call for getting all points from server in which survey is completed
     * and then redirect to home
     * @param wardNumber
     */
    public void fetchServerSurveyPoints(String wardNumber) {
        Log.i("CHECKCALLVAL","-LOGINAPICALL-"+wardNumber);
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getBaseActivity().showProgressDialog();
            getCompositeDisposable().add(getDataManager()
                    .doSurveyPointsApiCall(wardNumber,getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() ->getBaseActivity().hideProgressDialog())
                    .doOnNext(response -> {
                        if (response.getStatus().equalsIgnoreCase("true")) {
                            getDataManager().saveSurveyPointData(response);
                            getNavigator().openMainActivity();
                        }
                    }).subscribe());
        } else {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }
    /**
     *
     * to call login method
     */
    public void onLoginClick() {
        getNavigator().login();
    }

    /**
     *to call forgot password fragment
     */
    public void onForgotPasswordClick() {
        getBaseActivity().showForgotPasswordFragment(true);
    }
}
