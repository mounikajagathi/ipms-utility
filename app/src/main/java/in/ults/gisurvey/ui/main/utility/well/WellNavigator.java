package in.ults.gisurvey.ui.main.utility.well;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface WellNavigator extends BaseNavigator {

    void saveUtilityDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData();

    void disablePartialSave();
}
