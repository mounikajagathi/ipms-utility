package in.ults.gisurvey.ui.main.utility.culvert;
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

public class CulvertViewModel extends BaseViewModel<CulvertNavigator> {


    public final MutableLiveData<List<CommonItem>> constructionMaterialList = new MutableLiveData<>();


    public CulvertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNextClick() {
    }

    public void onPartialSaveClick() {
    }

    @SuppressLint("NonConstantResourceId")
    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.layoutCulvertNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_culvert_details_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutCulvertPlaceInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_culvert_details_place), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutConstructionMaterialInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_culvert_details_construction_material), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRoadNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_culvert_details_road_name), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
            case R.id.layoutRemarksInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_culvert_details_remarks), InfoVideoNames.ROAD_ENTRANCE_INFO_VIDEO);
                break;
        }
    }

    void loadData() {
        constructionMaterialList.setValue(IPMSApp.getAppInstance().getAllDropdowns().getCulvertConstructionMaterial());
    }
    protected boolean validateFields(String name,String place,String constMat,String roadName, String remarks,String imagePath1) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(name)) {
            getNavigator().validationError(CulvertFragment.CULVERT_NAME_ERROR, getBaseActivity().getString(R.string.err_culvert_details_name));
            return false;
        } else if (TextUtils.isEmpty(place)) {
            getNavigator().validationError(CulvertFragment.CULVERT_PLACE_ERROR, getBaseActivity().getString(R.string.err_culvert_details_place));
            return false;
        }
        else if (TextUtils.isEmpty(constMat)) {
            getNavigator().validationError(CulvertFragment.CULVERT_CONST_MAT_ERROR, getBaseActivity().getString(R.string.err_culvert_details_construction_material));
            return false;
        }
        else if (TextUtils.isEmpty(roadName)) {
            getNavigator().validationError(CulvertFragment.CULVERT_ROAD_NAME_ERROR, getBaseActivity().getString(R.string.err_culvert_details_road_name));
            return false;
        }
        else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(CulvertFragment.CULVERT_REMARKS_ERROR, getBaseActivity().getString(R.string.err_culvert_details_remarks));
            return false;
        } else if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(CulvertFragment.CULVERT_COMMON_ERROR, getBaseActivity().getString(R.string.err_culvert_details_photo_1));
            return false;
        }
        return true;
    }
    void saveUtilityDetails(String name,String place,String constMat,String roadName,String remarks,String imagePath1,boolean is){

    }
}