package in.ults.gisurvey.ui.main;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface MainNavigator extends BaseNavigator {

    void openAuthActivity();

    void openReportFragment(boolean isVisible);
}
