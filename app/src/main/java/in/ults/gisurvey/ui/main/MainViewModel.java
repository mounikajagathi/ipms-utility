package in.ults.gisurvey.ui.main;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> userEmail = new ObservableField<>();

    private final ObservableField<String> userName = new ObservableField<>();

    private final ObservableField<String> userProfilePicUrl = new ObservableField<>();

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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


    void logout() {
        getDataManager().setUserAsLoggedOut();
        getNavigator().openAuthActivity();
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
    }

    /**
     * to check all survey data synced or not for report generation
     *
     * @return boolean
     */
    public void checkReportView(){

        getCompositeDisposable().add(getDataManager()
                .getTotalPropertyCountWithDatePointStatusBuildingTypeBuildingSubTypeBuildingStatusDoorStatus(true,false,"","%%","%%","%%","%%","%%")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(propertyCount ->{
                    if(propertyCount != null){
                        if(propertyCount == 0) {
                            getNavigator().openReportFragment(true);
                        }
                        else{
                            getNavigator().openReportFragment(false);
                        }
                    }
                })
                .subscribe());

    }
    /**
     * This is to set which data need to load on completeed survey lis
     * ie synced or unsynced
     */
    public void setCompleteListType(String type){
        getDataManager().setCompletedListType(type);
    }
}
