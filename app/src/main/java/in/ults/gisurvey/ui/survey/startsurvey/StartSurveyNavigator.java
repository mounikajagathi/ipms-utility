package in.ults.gisurvey.ui.survey.startsurvey;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface StartSurveyNavigator extends BaseNavigator {

    void validationError(int code, String error);

    void clearValidationErrors();

    void createSurvey();

    void navigateToNextPage();

    void showEditDialog(String surveyId,String wardNumber);

    void showWarning();

}
