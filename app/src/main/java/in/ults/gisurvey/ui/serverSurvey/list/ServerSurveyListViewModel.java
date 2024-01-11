package in.ults.gisurvey.ui.serverSurvey.list;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.api.SurveyPoints;
import in.ults.gisurvey.data.model.db.Survey;

public class ServerSurveyListViewModel {

    public final ObservableField<String> propertyId = new ObservableField<>();

    ServerSurveyListViewModel(SurveyPoints item) {
        this.propertyId.set(String.valueOf(item.getPropertyId()));
    }
}
