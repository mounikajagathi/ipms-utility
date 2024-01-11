package in.ults.gisurvey.ui.survey.member;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface MemberNavigator extends BaseNavigator {

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData(Property property);

    void saveMemberDetails(boolean isPartial);

    void navigateToLiveHoodPage();

    void navigateToBuildingPage();

    void navigateToMorePage();

    void navigateToImageDetails();

    void addMembers();

    void removeMembers();

    void showNoMemberDialog();

    void showWarningDialog(String message);

    void showErrorDialog(String message);

    void navigateToScreenSelection();

    void disablePartialSave();
}
