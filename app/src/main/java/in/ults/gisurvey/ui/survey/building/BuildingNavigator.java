package in.ults.gisurvey.ui.survey.building;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface BuildingNavigator extends BaseNavigator {

    void saveBuildingDetails(boolean isPartial);

    void validationError(int code, String error);

    void clearValidationErrors();

    void setCachedData(Property property);

    void navigateToImageDetails();

    void setFloorCount(int count,int groundFloor);

    void showPathWayNANRPopUP();

    void showBuildNameNANRPopUp();

    void showColonyNameNANRPopUp();

    void addFloorArea();

    void removeFloorArea();

    void addRoofType();

    void removeRoofType();

    void disableBuildingName();

    void setStructureType(Survey survey);

    void showWarning(String messgae);

    void navigateToScreenSelection();

    void disablePartialSave();

}
