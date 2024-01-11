package in.ults.gisurvey.ui.survey.pointstatus;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface PointStatusNavigator extends BaseNavigator {

    void navigateToFloorDetailsPage();

    void navigateToLocationDetailsPage();

    void savePointStatus();

    void setPointStatus(String pointStatus);

    void setSelectedPointStatus(String pointStatus);

    void radioButtonClicked();

    void onEdtSuccess();

    void setEditOnTitleBar();
}
