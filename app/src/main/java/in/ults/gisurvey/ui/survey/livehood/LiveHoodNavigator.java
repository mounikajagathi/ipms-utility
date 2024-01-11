package in.ults.gisurvey.ui.survey.livehood;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface LiveHoodNavigator extends BaseNavigator {

    void saveLiveHoodDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData(Property property);

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void setLackOfDrinkingWater(boolean status);

    void navigateToScreenSelection();

    void disablePartialSave();

}
