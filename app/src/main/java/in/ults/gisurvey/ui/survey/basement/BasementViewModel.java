package in.ults.gisurvey.ui.survey.basement;

import java.util.ArrayList;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class BasementViewModel extends BaseViewModel<BasementNavigator> {

    public BasementViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to insert basement area in db then navigate to next page
     *
     * @param basementAreaItems
     */
    void saveBasementArea(ArrayList<BasementAreaItem> basementAreaItems) {
        getCompositeDisposable().add(getDataManager()
                .insertBasementArea(basementAreaItems, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getNavigator().navigateToNextPage())
                .subscribe());
    }

    /**
     * read from db
     *
     * @param survey
     */
    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        getNavigator().setCachedData(survey);
    }

    /**
     * to validate fields
     *
     * @param dataList
     * @return
     */
    protected boolean validateFields(ArrayList<BasementAreaItem> dataList) {
        getNavigator().clearValidationErrors();
        for (BasementAreaItem data : dataList) {
            if (data.getBasementNumber().length() == 0 || data.getArea().length() == 0 ||(!data.getArea().equalsIgnoreCase(AppConstants.NR_UPPERCASE) && Double.parseDouble(data.getArea()) == 0)) {
                getNavigator().validationError(BasementFragment.BASEMENT_AREA_LIST, "");
                return false;
            }
        }
        return true;
    }

    public void onNextClick() {
        getNavigator().saveBasementArea();
    }

}