package in.ults.gisurvey.ui.main.synclist;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;

public class SyncListAdapterViewModel {

    public final ObservableField<String> propertyID = new ObservableField<>();

    SyncListAdapterViewModel(SurveyWithProperty item) {
        this.propertyID.set(String.valueOf(item.getPropertyID()));
    }
}
