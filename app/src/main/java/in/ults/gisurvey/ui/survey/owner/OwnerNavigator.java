package in.ults.gisurvey.ui.survey.owner;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface OwnerNavigator extends BaseNavigator {

    void navigateToRoadPage();

    void navigateToTenantPage();

    void navigateToTaxPage();

    void navigateToEstablishmentPage();

    void navigateToMemberPage();

    void navigateToLiveHoodPage();

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void validationError(int code, String error);

    void clearValidationErrors();

    void saveOwnerDetails(boolean isPartial);

    void showAROwnerAddress();

    void navigateToScreenSelection();

    void disablePartialSave();
}
