package in.ults.gisurvey.ui.serverSurvey.detials;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsFragment;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsNavigator;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

/**
 * Created by anuag on 10-07-2020
 **/
public class ServerSurveyDetailsViewModel extends BaseViewModel<ServerSurveyDetailsNavigator> {


    public ServerSurveyDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to save surveyor name
     */
    public void saveData(String surveyorName, String mobileCode) {
        getDataManager().setSurveyorName(surveyorName);
        getDataManager().setMobileCode(mobileCode);
    }

    public void loadData() {

    }

    public void onNextClick() {
//
        getNavigator().close();
    }


}
