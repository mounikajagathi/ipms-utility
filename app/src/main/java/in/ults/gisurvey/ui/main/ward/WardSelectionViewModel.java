package in.ults.gisurvey.ui.main.ward;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

/**
 * Created by anuag on 10-07-2020
 **/
public class WardSelectionViewModel extends BaseViewModel<WardSelectionNavigator> {
    public final MutableLiveData<ArrayList<CommonItem>> wardsList = new MutableLiveData<>();
    public final MutableLiveData<String> wardMsg = new MutableLiveData<>();

    public WardSelectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to save selected wards and  calling for suvey points under these wards
     */
    public void saveData(ArrayList<String> selectedWards) {
        getDataManager().setSelectedSurveyPointWards(selectedWards);
            fetchSurveyPoints(getSelectedWardsInString());
    }

    @Override
    protected void onGettingSurveyPointsFromServer() {
        super.onGettingSurveyPointsFromServer();
        getNavigator().onGettingSurveyPoints();
    }

    public void loadData() {
        if(getWardNumber()!=null){
            wardMsg.setValue(getBaseActivity().getString(R.string.ward_selection_hint)+" "+getWardNumber());
        }

        if (getSelectedWardNumberList() != null)
        {
            ArrayList<CommonItem> tempwardList=new ArrayList<CommonItem>();
            for(CommonItem c:getSelectedWardNumberList()){
                if(!c.getContent().equalsIgnoreCase(getWardNumber())){
                    tempwardList.add(c);
                }
            }
            wardsList.setValue(tempwardList);
        }
    }

    public void onNextClick() {
//
        getNavigator().saveWards();
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validation(ArrayList<String> selectedWards) {
        getNavigator().clearValidationErrors();
        if(selectedWards.size()>4){
            getNavigator().validationError(WardSelectionFragment.COMMON_ERROR,getBaseActivity().getString(R.string.ward_selection_list_exceed_error));
            return false;
        }
        return true;
    }
}
