package in.ults.gisurvey.ui.survey.road;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface RoadNavigator extends BaseNavigator {

    void saveRoadDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void navigateToTenantPage();

    void navigateToTaxPage();

    void navigateToEstablishmentPage();

    void navigateToMemberPage();

    void navigateToLiveHoodPage();

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void navigateToScreenSelection();

    void disablePartialSave();
}
