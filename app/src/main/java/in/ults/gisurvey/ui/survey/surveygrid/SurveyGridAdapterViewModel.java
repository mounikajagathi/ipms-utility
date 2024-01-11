package in.ults.gisurvey.ui.survey.surveygrid;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.items.GridItem;

public class SurveyGridAdapterViewModel {

    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> propertyID = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableBoolean completedStatus = new ObservableBoolean(false);
    public final ObservableBoolean syncStatus = new ObservableBoolean(false);
    public final ObservableBoolean isSurveyModeEdit = new ObservableBoolean(false);

    SurveyGridAdapterViewModel(GridItem item) {
        this.id.set(item.getId());
        this.propertyID.set(String.valueOf(item.getId()));
        this.content.set(item.getContent());
        this.completedStatus.set(item.isCompletedStatus());
        this.syncStatus.set(item.isEditVisible());
        this.isSurveyModeEdit.set(item.isSurveyOpenEdit());


    }

}
