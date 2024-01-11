package in.ults.gisurvey.ui.survey.completesurvey;

import androidx.databinding.ObservableBoolean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class CompleteSurveyViewModel extends BaseViewModel<CompleteSurveyNavigator> {

    public final ObservableBoolean isBuildingSurvey = new ObservableBoolean(false);


    public CompleteSurveyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        if (getDataManager().getCurrentPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_BUILDING)) {
            isBuildingSurvey.set(true);
        }
    }

    /**
     * to save a whole survey in db and
     */
    public void saveSurvey() {
        String versionCode = "";

        Dashboard dashboard = getDashBoard();
        if (dashboard != null) {
            versionCode = dashboard.getVersion();
        }

        DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_DATE_FORMAT, Locale.ENGLISH);
        String endDate = df.format(Calendar.getInstance().getTime());

        if (isBuildingSurvey.get()) {
            getCompositeDisposable().add(getDataManager()
                    .setPropertyCompletedStatus(versionCode,getBaseActivity().getString(R.string.app_title), getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().io())
                    .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().insertPropertyEndDate(endDate, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(aBoolean -> getNavigator().savePropertySurvey())
                    .subscribe());
        } else {
            getCompositeDisposable().add(getDataManager()
                    .setPropertyCompletedStatus(versionCode,getBaseActivity().getString(R.string.app_title), getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().io())
                    .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().insertPropertyEndDate(endDate, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId()))
                    .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().setSurveyCompletedStatus(getDataManager().getCurrentSurveyID()))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(aBoolean -> getNavigator().completeSurvey())
                    .subscribe());
        }


    }
}