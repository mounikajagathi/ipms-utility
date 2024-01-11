package in.ults.gisurvey.ui.auth.forgotpassword;


import android.text.TextUtils;

import com.google.gson.Gson;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.ForgotPasswordRequest;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class ForgotPasswordViewModel extends BaseViewModel<ForgotPasswordNavigator> {

    public ForgotPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * validation of email
     * show validation error if validation fails
     * @param emailAddress
     * @return
     */
    protected boolean validateFields(String emailAddress) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(emailAddress)) {
            getNavigator().validationError(-1, getBaseActivity().getString(R.string.enter_email_address));
            return false;
        } else if (!CommonUtils.isEmailValid(emailAddress)) {
            getNavigator().validationError(-1, getBaseActivity().getString(R.string.enter_valid_email_address));
            return false;
        } else {
            return true;
        }
    }

    /**
     *resetting password
     * @param emailAddressOrUsername
     */
    public void resetPassword(String emailAddressOrUsername) {
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getNavigator().showProgressDialog();
            getCompositeDisposable().add(getDataManager()
                    .doForgotPasswordApiCall(new Gson().toJson(new ForgotPasswordRequest(emailAddressOrUsername)),getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getNavigator().hideProgressDialog())
                    .doOnNext(forgotPasswordResponse -> {
                        if (forgotPasswordResponse.getStatus().equalsIgnoreCase("true")) {
                            getNavigator().onResetPasswordSuccess();
                            getBaseActivity().showToast(forgotPasswordResponse.getMessage());
                        } else {
                            getBaseActivity().showToast(forgotPasswordResponse.getMessage());
                        }
                    }).subscribe());
        } else {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }


    public void onResetPasswordClick() {
        getNavigator().resetPassword();
    }
}
