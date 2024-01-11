package in.ults.gisurvey;

import android.app.Activity;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import in.ults.gisurvey.data.model.items.AllDropdowns;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.data.model.items.LocMainItem;
import in.ults.gisurvey.di.component.DaggerAppComponent;
import in.ults.gisurvey.utils.CommonUtils;

public class IPMSApp extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
    
    private LocMainItem locMainItem;
    private AllDropdowns allDropdowns;
    static IPMSApp appInstance;
    private HashMap<String,ArrayList<CommonItem>> postOffice;



    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        appInstance = this;
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud6433112909,none,C6JC7XLS1M0F8YAJM124");
        AndroidNetworking.initialize(getApplicationContext());
        /*if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }*/

        allDropdowns = new Gson().fromJson(CommonUtils.loadUtilityDropdownFromAsset(this), AllDropdowns.class);
        locMainItem = new Gson().fromJson(CommonUtils.loadJsonFromAsset(this), LocMainItem.class);
        postOffice = CommonUtils.loadPostOfficeJsonFromAsset(this);
    }

    public static IPMSApp getAppInstance() {
        return appInstance;
    }

    public LocMainItem getLocMainItem() {
        return locMainItem;
    }

    public AllDropdowns getAllDropdowns() {
        return allDropdowns;
    }

    public HashMap<String, ArrayList<CommonItem>> getPostOffice() {
        return postOffice;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}