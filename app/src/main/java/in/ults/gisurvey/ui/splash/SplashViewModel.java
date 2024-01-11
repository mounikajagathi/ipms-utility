package in.ults.gisurvey.ui.splash;

import java.util.concurrent.TimeUnit;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.Single;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     *
     * to load splash
     */
    void runSplash() {
        getCompositeDisposable().add(Single
                .timer(2, TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSuccess(aLong -> decideNextActivity())
                .subscribe());
    }

    /**
     *
     * if user is logged in then load the mainactivity otherwise open the authactivity
     */
    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openAuthActivity();
        } else {
            getNavigator().openMainActivity();
        }
    }
}
