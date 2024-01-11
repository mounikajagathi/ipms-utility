package in.ults.gisurvey.ui.auth.login;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface LoginNavigator extends BaseNavigator {

    void validationError(int code, String error);

    void clearValidationErrors();

    void login();

    void openMainActivity();
}