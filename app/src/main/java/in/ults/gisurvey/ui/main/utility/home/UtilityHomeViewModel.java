package in.ults.gisurvey.ui.main.utility.home;
import android.view.View;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class UtilityHomeViewModel extends BaseViewModel<UtilityHomeNavigator> {

    public UtilityHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


}