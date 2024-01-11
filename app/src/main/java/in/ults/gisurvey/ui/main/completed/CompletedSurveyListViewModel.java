package in.ults.gisurvey.ui.main.completed;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;

public class CompletedSurveyListViewModel {

    public final ObservableField<String> surveyId = new ObservableField<>();
    public final ObservableField<String> pointStatus = new ObservableField<>();

    CompletedSurveyListViewModel(SurveyWithProperty item) {
        this.surveyId.set(String.valueOf(item.getSurveyID()));
        this.pointStatus.set(item.getPointStatus());
    }
}
