package in.ults.gisurvey.ui.main.ward;

import in.ults.gisurvey.ui.base.BaseNavigator;

/**
 * Created by anuag on 10-07-2020
 **/
public interface WardSelectionNavigator extends BaseNavigator {
    void saveWards();
    void validationError(int code, String error);
    void clearValidationErrors();
    void onGettingSurveyPoints();
}
