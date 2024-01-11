package in.ults.gisurvey.ui.auth.forgotpassword;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ForgotPasswordNavigator extends BaseNavigator{

    void validationError(int code, String error);

    void clearValidationErrors();

    void resetPassword();

    void onResetPasswordSuccess();
}
