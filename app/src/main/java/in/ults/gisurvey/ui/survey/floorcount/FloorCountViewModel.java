package in.ults.gisurvey.ui.survey.floorcount;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class FloorCountViewModel extends BaseViewModel<FloorCountNavigator> {


    public final MutableLiveData<String> floorCount = new MutableLiveData<>();
    public final MutableLiveData<String> propertyCount = new MutableLiveData<>();


    public FloorCountViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to set floor and property count in db then navigate to ground floor selection fragment
     * @param floorCount
     * @param propertyCount
     */
    void saveFloorPropertyCount(int floorCount, int propertyCount) {
        getCompositeDisposable().add(getDataManager()
                .insertFloorPropertyCount(floorCount, propertyCount, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    getNavigator().setCount(floorCount, propertyCount);
                    getNavigator().navigateToNextPage();
                })
                .subscribe());
    }
    /**
     * to get count from db when back pressed and set in its fields
     */
    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        getNavigator().setCount(survey.getFloorCount(), survey.getPropertyCount());
        setFloorCount(survey.getFloorCount());
        setPropertyCount(survey.getPropertyCount());
    }

    /**
     * set the floor count in the count of floor field
     * @param count
     */
    public void setFloorCount(int count) {
        floorCount.setValue(String.valueOf(count == -1 ? 1 : count));
    }

    /**
     * set the property count in count of property field
     * @param count
     */
    public void setPropertyCount(int count) {
        propertyCount.setValue(String.valueOf(count == -1 ? 1 : count));
    }

    /**
     * to save floor and property count
     */
    public void onNextClick() {
        getNavigator().saveFloorPropertyCount();
    }

    /**
     * to increase floor count
     */
    public void onIncreaseFloorClick() {
        getNavigator().increaseFloorCount();
    }

    /**
     * to decrease floor count
     */
    public void onDecreaseFloorClick() {
        getNavigator().decreaseFloorCount();
    }

    /**
     * to increase property count
     */
    public void onIncreasePropertyClick() {
        getNavigator().increasePropertyCount();
    }

    /**
     * to decrease property click
     */
    public void onDecreasePropertyClick() {
        getNavigator().decreasePropertyCount();
    }


}