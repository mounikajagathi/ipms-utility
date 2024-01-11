package in.ults.gisurvey.ui.main.home;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface HomeNavigator extends BaseNavigator {

    void navigateToPartialSurvey();

    void navigateToNewSurvey();

    void showHomeDashboard();

    void showHomeProgress();

    void showHomeNoData();

    void setDashboardContent();
}
