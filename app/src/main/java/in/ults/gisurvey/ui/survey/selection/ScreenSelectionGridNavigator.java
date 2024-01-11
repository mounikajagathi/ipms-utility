package in.ults.gisurvey.ui.survey.selection;

import android.view.View;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ScreenSelectionGridNavigator extends BaseNavigator {

    void navigateToEstablishmentPage();

    void navigateToMemberPage();

    void navigateToLiveHoodPage();

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void saveTaxDetails();

    void navigateToOwnerPage();

    void navigateToRoadPage();

    void navigateToTenantPage();

    void navigateToTaxPage();

    void validationError(int code, String error);

    void setCachedData(Property property);

    void navigateToCompleteSurveyPage();



}
