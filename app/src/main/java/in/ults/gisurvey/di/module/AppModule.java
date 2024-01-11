package in.ults.gisurvey.di.module;

import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.ults.gisurvey.BuildConfig;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.AppDataManager;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.local.db.AppDatabase;
import in.ults.gisurvey.data.local.db.AppDbHelper;
import in.ults.gisurvey.data.local.db.DbHelper;
import in.ults.gisurvey.data.local.prefs.AppPreferencesHelper;
import in.ults.gisurvey.data.local.prefs.PreferencesHelper;
import in.ults.gisurvey.data.remote.ApiHeader;
import in.ults.gisurvey.data.remote.ApiHelper;
import in.ults.gisurvey.data.remote.AppApiHelper;
import in.ults.gisurvey.di.ApiInfo;
import in.ults.gisurvey.di.DatabaseInfo;
import in.ults.gisurvey.di.PreferenceInfo;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewSingleSelectAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;
import in.ults.gisurvey.utils.adapters.StateListAdapter;
import in.ults.gisurvey.utils.rx.AppSchedulerProvider;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    FlexRecyclerViewSingleSelectAdapter provideCommonFlexSingleAdapter() {
        return new FlexRecyclerViewSingleSelectAdapter();
    }

    @Provides
    FlexRecyclerViewMultiSelectAdapter provideCommonFlexMultiAdapter() {
        return new FlexRecyclerViewMultiSelectAdapter();
    }

    @Provides
    CommonDropDownAdapter provideDropdownAdapter(Context context) {
        return new CommonDropDownAdapter(context, 0, new ArrayList<>());
    }

    @Provides
    StateListAdapter provideStateListAdapter(Context context) {
        return new StateListAdapter(context, 0, new ArrayList<>());
    }

    @Provides
    PostOfficeListAdapter providePostOfficeListAdapter(Context context) {
        return new PostOfficeListAdapter(context, 0, new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
    }

    @Provides
    FlexboxLayoutManager provideFlexLayoutManager(Context context) {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        return layoutManager;
    }
}
