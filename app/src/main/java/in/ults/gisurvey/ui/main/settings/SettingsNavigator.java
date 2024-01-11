package in.ults.gisurvey.ui.main.settings;

import java.util.ArrayList;

import in.ults.gisurvey.data.model.api.District;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface SettingsNavigator extends BaseNavigator {

    void navigateToSurveyorList();

    void navigateToSurveyPointsWardSelection();

    void navigateToSync();

    void showDistrictBottomSheet(ArrayList<District> data);

    void showLocalBodyBottomSheet(ArrayList<LocalBody> data);

    void showWardNumberBottomSheet(ArrayList<CommonItem> data);

    void syncData();

    void selectTPKData();

    void backupSurvey();

    void selectBackupData();

    void selectARData();

    void selectInfoVideoData();

    void showMultipleWardSelectionAlert();

}
