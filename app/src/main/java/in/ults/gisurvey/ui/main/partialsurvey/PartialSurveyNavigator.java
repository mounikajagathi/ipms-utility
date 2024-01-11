package in.ults.gisurvey.ui.main.partialsurvey;

import java.util.List;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface PartialSurveyNavigator extends BaseNavigator {

    void setSurveyData(List<Survey> data);
    void navigateToPartialSurvey();
    void navigateToNewSurvey();
}
