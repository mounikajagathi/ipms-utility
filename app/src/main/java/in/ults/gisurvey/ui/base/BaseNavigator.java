package in.ults.gisurvey.ui.base;

import android.content.DialogInterface;

import androidx.annotation.StringRes;

public interface BaseNavigator {
    void showToast(String message);
    void showToast(@StringRes int message);
    void showProgressDialog();
    void hideProgressDialog();
    void onApiFailure();
    void onLogoutUser();
    void showInfoDialog(String info);
    void showInfoDialogWithVideo(String info,String video);
    void showDialog(String title, String message, String positiveButtonLabel, DialogInterface.OnClickListener positiveButton, String negativeButtonLabel, DialogInterface.OnClickListener negativeButton);
}
