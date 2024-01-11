package in.ults.gisurvey.ui.survey.tax;

import android.view.View;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface TaxNavigator extends BaseNavigator {

    void navigateToEstablishmentPage();

    void navigateToMemberPage();

    void navigateToLiveHoodPage();

    void navigateToMorePage();

    void navigateToBuildingPage();

    void navigateToImageDetails();

    void saveTaxDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void onChooseYear();

    void showNANRMenu(View view);

    void showNANROption();

    void setCachedData(Property property);

    void navigateToScreenSelection();

    void disablePartialSave();

}
