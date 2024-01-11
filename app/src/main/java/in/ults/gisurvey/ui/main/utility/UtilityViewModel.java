package in.ults.gisurvey.ui.main.utility;
import android.view.View;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class UtilityViewModel extends BaseViewModel<UtilityNavigator> {
    public UtilityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
