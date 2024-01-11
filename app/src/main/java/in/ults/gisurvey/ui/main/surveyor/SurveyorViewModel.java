package in.ults.gisurvey.ui.main.surveyor;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.Subordinates;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class SurveyorViewModel extends BaseViewModel<SurveyorNavigator> {

    private final MutableLiveData<List<Subordinates>> SurveyorList = new MutableLiveData<>();

    public SurveyorViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setSurveyorList() {
        Dashboard dashboard = getDashBoard();
        if (dashboard != null && dashboard.getEmployee() != null
                && dashboard.getEmployee().getSubordinates() != null)
            this.SurveyorList.setValue(dashboard.getEmployee().getSubordinates());
    }

    public MutableLiveData<List<Subordinates>> getSurveyorList() {
        return SurveyorList;
    }
}
