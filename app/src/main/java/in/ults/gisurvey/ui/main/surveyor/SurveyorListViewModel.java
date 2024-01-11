package in.ults.gisurvey.ui.main.surveyor;

import androidx.databinding.ObservableField;

import in.ults.gisurvey.data.model.api.Employee;
import in.ults.gisurvey.data.model.api.Subordinates;

public class SurveyorListViewModel {
    public final ObservableField<String> surveyorName = new ObservableField<>();
    public final ObservableField<String> surveyorRole = new ObservableField<>();

    SurveyorListViewModel(Subordinates item) {
        this.surveyorName.set("Surveyor Name : "+ item.getEmployeeName());
        this.surveyorRole.set("Role : "+ item.getRole());
    }
}
