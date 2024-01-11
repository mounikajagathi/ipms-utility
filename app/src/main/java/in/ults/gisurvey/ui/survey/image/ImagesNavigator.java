package in.ults.gisurvey.ui.survey.image;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface ImagesNavigator extends BaseNavigator {

    void setImagePath(String imagePath, int reqTypeCode);

    void saveImageDetails(boolean isPartial);

    void captureImage(int reqTypeCode);

    void validationError(int code, String error);

    void clearValidationErrors();

    void navigateToScreenSelection();

    void disablePartialSave();
}
