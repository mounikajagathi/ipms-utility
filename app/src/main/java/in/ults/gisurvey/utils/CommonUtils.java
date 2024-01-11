package in.ults.gisurvey.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.model.items.CommonItem;

public final class CommonUtils {

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static String setDefaultStringValue(String content, String defaultValue) {
        if (content == null || content.length() == 0) {
            content = defaultValue;
        }
        return content;
    }

    public static int setDefaultIntegerVale(int content, int defaultValue) {
        if (content == -1) {
            content = defaultValue;
        }
        return content;
    }

    public static String setDefaultIntegerStringVale(String content, String defaultValue) {
        if (content.equalsIgnoreCase("-1")) {
            content = defaultValue;
        }
        return content;
    }

    /**
     * This is to count occurace of character in string
     *
     * @param str
     * @param c
     * @return
     */
    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }

        return count;
    }

    /**
     * validation of email id
     *
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * validation of landline
     *
     * @param landline
     * @return
     */
    public static boolean isLandlineValid(String landline) {
        return Pattern.matches("\\d{5}([- ]*)\\d{6}", landline);
    }

    /**
     * validation of mobile number
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileValid(String mobile) {
        return Pattern.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$", mobile);
    }


    public static boolean isNewPropertyIDValid(String propertyID) {

        if (propertyID.toUpperCase().contains(AppConstants.NA_NEAR) || propertyID.toUpperCase().contains(AppConstants.NR_NEAR)) {
            propertyID = propertyID.toUpperCase().replace(AppConstants.NA_NEAR, "").replace(AppConstants.NR_NEAR, "").trim();
        }
        return propertyID.matches("^(\\d+)(/)(\\d+)((/)[0-9a-zA-Z])*([0-9])*$");
    }

    public static boolean isOldPropertyIDValid(String propertyID) {
        return propertyID.equalsIgnoreCase(AppConstants.NR_UPPERCASE) || propertyID.matches("^(\\d+)(/)(\\d+)((/)[0-9a-zA-Z])*([0-9])*$");
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, StandardCharsets.UTF_8);
    }

    /**
     * common method to loading dialog
     *
     * @param context
     * @return
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


    public static String loadJsonFromAsset(Application application) {
        String jsonString = null;
        try {
            InputStream is = application.getAssets().open("surveyJson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }

    public static String loadUtilityDropdownFromAsset(Application application) {
        String jsonString = null;
        try {
            InputStream is = application.getAssets().open("utility_drop_down.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }


    public static HashMap<String, ArrayList<CommonItem>> loadPostOfficeJsonFromAsset(Application application) {
        HashMap<String, ArrayList<CommonItem>> mapData = new HashMap<>();
        try {
            for (int i = 1; i <= 11; i++) {
                InputStream is = application.getAssets().open("post_office_" + i + ".json");
                int size1 = is.available();
                byte[] buffer = new byte[size1];
                is.read(buffer);
                is.close();
                String jsonString1 = new String(buffer, StandardCharsets.UTF_8);
                Type type = new TypeToken<HashMap<String, ArrayList<CommonItem>>>() {
                }.getType();
                mapData.putAll(new Gson().fromJson(jsonString1, type));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return mapData;
    }

    // Get the status of internet connectivity of the device with error dialog.
    public static boolean isNetworkConnected(Context ctx) {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            for (int networkType : networkTypes) {
                if (activeNetworkInfo != null && activeNetworkInfo.getType() == networkType)
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void preventTwoClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            public void run() {
                view.setEnabled(true);
            }
        }, AppConstants.SYNC_BTN_CLICK_DELAY);
    }

    public static void disableWidget(final View childView, final View containerView) {
        childView.setEnabled(false);
        if (containerView != null)
            containerView.setAlpha(.5f);
    }

    public static void enableWidget(final View childView, final View containerView) {
        childView.setEnabled(true);
        if (containerView != null)
            containerView.setAlpha(1f);
    }
    public static void disableChildView(ViewGroup view){
            if(view!=null) {
                for (int i = 0; i < view.getChildCount(); i++) {
                    View child = view.getChildAt(i);

                    if(child instanceof ViewGroup){
                        disableChildView((ViewGroup) child);
                    }else{
                        if(child.getId() != R.id.btnNext&&child.getId() != R.id.btnSave) {
                            child.setEnabled(false);
                        }
                    }

                }
            }



    }
}