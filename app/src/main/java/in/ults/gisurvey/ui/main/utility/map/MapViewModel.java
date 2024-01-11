package in.ults.gisurvey.ui.main.utility.map;
import android.view.View;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class MapViewModel extends BaseViewModel<MapNavigator> {

    public MapViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


}