package in.ults.gisurvey.ui.main.utility.divider;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface DividerNavigator extends BaseNavigator {

    void saveUtilityDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData();

    void disablePartialSave();
}
