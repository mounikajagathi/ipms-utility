package in.ults.gisurvey.ui.main.utility.home;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface UtilityHomeNavigator extends BaseNavigator {

    void saveUtilityDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData();

    void disablePartialSave();
}
