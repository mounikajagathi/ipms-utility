package in.ults.gisurvey.ui.survey.basement;

import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface BasementNavigator extends BaseNavigator {

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData(Survey survey);

    void saveBasementArea();

    void navigateToNextPage();
}
