package in.ults.gisurvey.ui.survey.establishment;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface EstablishmentNavigator extends BaseNavigator {

    void saveEstablishmentDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void navigateToMemberPage();

    void navigateToLiveHoodPage();

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void showLicenceNANRPopUp();

    void navigateToScreenSelection();

    void disablePartialSave();
}
