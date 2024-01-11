package in.ults.gisurvey.ui.main.surveyor;

import java.util.ArrayList;

import in.ults.gisurvey.ui.base.BaseNavigator;

/**
 * Created by anuag on 10-07-2020
 **/
public interface SurveyorDetailsNavigator extends BaseNavigator {
    void saveSurveyorName();
    void validationError(int code, String error);
    void clearValidationErrors();
    void showProgress();
    void showContent();
    void showNoData();
    void setSelectedSurveyor(ArrayList<String> list);
    void clearList();
}
