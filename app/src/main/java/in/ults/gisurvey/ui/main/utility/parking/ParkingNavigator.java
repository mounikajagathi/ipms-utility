package in.ults.gisurvey.ui.main.utility.parking;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ParkingNavigator extends BaseNavigator {

    void saveUtilityDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData();

    void disablePartialSave();
}
