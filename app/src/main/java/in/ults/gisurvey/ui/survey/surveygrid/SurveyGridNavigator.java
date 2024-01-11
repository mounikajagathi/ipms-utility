package in.ults.gisurvey.ui.survey.surveygrid;

import java.util.List;
import java.util.Set;

import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseNavigator;

public interface SurveyGridNavigator extends BaseNavigator {
    void navigateToNextPage();

    void completeSurvey();

    void completedSurveyList(Set<String> data);

    void onDeletionSuccess(int floorcount,int propertyCount,int position);

    void onEditSuccess();

    void onFetchSyncCount(int count);
}
