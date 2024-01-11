package in.ults.gisurvey.ui.main.synclist;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface SyncListNavigator extends BaseNavigator {

    void showSyncDialog();

    void dismissSyncDialog();

    void refreshPage();

    void updateSyncStatusLabels(String label);

    void updateUploadPercent(long bytesUploaded, long totalBytes);

    void showUploadCount();

    void preventDoubleClick();

}
