package in.ults.gisurvey.ui.main.partialsurvey;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.db.Survey;

public class PartialSurveyListViewModel {

    public final ObservableField<String> surveyId = new ObservableField<>();

    PartialSurveyListViewModel(Survey item) {
        this.surveyId.set(String.valueOf(item.getSurveyID()));
    }
}
