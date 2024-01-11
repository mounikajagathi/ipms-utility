package in.ults.gisurvey.ui.main.utility.transformer;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopFragment;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class TransformerViewModel extends BaseViewModel<TransformerNavigator> {

    public final MutableLiveData<List<CommonItem>> transformerCategoryList = new MutableLiveData<>();

    public TransformerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutTransformerLocationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_transformer_details_location), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTransformerCategoryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_transformer_details_category), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutTransformerDetailsInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_transformer_details_transformer), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_transformer_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        transformerCategoryList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getTransformerCategory());
    }

    protected boolean validateFields(String location, String category, String transformerDetails, String remarks, String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(location)) {
            getNavigator().validationError(TransformerFragment.TRANSFORMER_LOCATION_ERROR, getBaseActivity().getString(R.string.err_transformer_details_location));
            return false;
        } else if (TextUtils.isEmpty(category)) {
            getNavigator().validationError(TransformerFragment.TRANSFORMER_CATEGORY_ERROR, getBaseActivity().getString(R.string.err_transformer_details_category));
            return false;
        } else if (TextUtils.isEmpty(transformerDetails)) {
            getNavigator().validationError(TransformerFragment.TRANSFORMER_TRANSFORMER_ERROR, getBaseActivity().getString(R.string.err_transformer_details_transformer));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(TransformerFragment.TRANSFORMER_REMARKS_ERROR, getBaseActivity().getString(R.string.err_transformer_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(TransformerFragment.TRANSFORMER_COMMON_ERROR, getBaseActivity().getString(R.string.err_transformer_details_photo_1));
            return false;
        }
        return true;
    }

    void saveUtilityDetails(String location, String category, String transformerDetails, String remarks, String imagePath1, boolean is) {

    }
}