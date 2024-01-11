package in.ults.gisurvey.ui.survey.property;

import java.util.ArrayList;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.ExcelDataItem;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface PropertyNavigator extends BaseNavigator {

    void navigateToNextPage();

    void navigateToImageDetails();

    void validationError(int code, String error);

    void clearValidationErrors();

    void saveProperty();

    void setCachedData(Property property);

    void setSelectedData(String buildingStatus, String doorStatus,  String buildingSubType);

    void showNearPopUp();

    void showNaNrMenu();

    void showNearPopUpForNearProperty();

    void updateLatrineBathroomACNROnDoorStatusChange();

    void updateSubTypeRelatedBauilding();

    void checkNewPropertyId();

    void checkOldPropertyId();

    void showARDetailsNewPropertyId( ArrayList<ExcelDataItem> dataItems,String propId);

    void showNotFoundPropertyId(String message,String propId);

    void showARDetailsOldPropertyId( ArrayList<ExcelDataItem> dataItems);

    void preventDoubleClick();

    void showWarning();


}
