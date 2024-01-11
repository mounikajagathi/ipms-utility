package in.ults.gisurvey.ui.survey.selection;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.items.GridItem;

public class ScreenSelectionGridAdapterViewModel {

    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableBoolean completedStatus = new ObservableBoolean(false);
    public final ObservableBoolean isSurveyModeEdit = new ObservableBoolean(false);

    ScreenSelectionGridAdapterViewModel(GridItem item) {
        this.id.set(item.getId());
        this.title.set(item.getContent());
        if(item.isCompletedStatus()){
            this.status.set("Completed");
        }else{
            this.status.set("Not Completed");
        }

        this.completedStatus.set(item.isCompletedStatus());
    }

}
