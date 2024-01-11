package in.ults.gisurvey.ui.survey.location;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface LocationNavigator extends BaseNavigator {
    void navigateToSurveyGridPage();

    void navigateToPropertyPage();

    void saveLocationDetails();

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData(Survey survey);
}
