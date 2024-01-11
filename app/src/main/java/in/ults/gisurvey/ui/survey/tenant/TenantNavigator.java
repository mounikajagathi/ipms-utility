package in.ults.gisurvey.ui.survey.tenant;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface TenantNavigator extends BaseNavigator {

    void saveTenantDetails(boolean isPartial);
    void validationError(int code, String error);
    void clearValidationErrors();
    void navigateToTaxPage();
    void navigateToEstablishmentPage();
    void navigateToMemberPage();
    void navigateToLiveHoodPage();
    void navigateToMorePage();
    void navigateToBuildingPage();
    void navigateToImageDetails();
    void navigateToScreenSelection();
    void disablePartialSave();
    void showSurveyNoNRNoLandPopUp();

}
