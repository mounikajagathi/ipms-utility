package in.ults.gisurvey.ui.auth.serverurl;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ServerUrlNavigator extends BaseNavigator{

    void validationError(int code, String error);

    void clearValidationErrors();

    void verify();

    void onResetPasswordSuccess();
}
