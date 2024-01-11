package in.ults.gisurvey.ui.main.utility.busstop;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface BusStopNavigator extends BaseNavigator {

    void saveUtilityDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData();

    void disablePartialSave();
}
