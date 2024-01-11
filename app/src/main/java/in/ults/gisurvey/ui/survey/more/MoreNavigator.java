package in.ults.gisurvey.ui.survey.more;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface MoreNavigator extends BaseNavigator {

    void saveMoreDetails(boolean isPartial);

    void validationError(int code, String error);

    void navigateToBuildingPage();

    void navigateToImagePage();

    void clearValidationErrors();

    void setCachedData(Property property);

    void addVehicle();

    void removeVehicle();

    void navigateToScreenSelection();

    void disablePartialSave();

}
