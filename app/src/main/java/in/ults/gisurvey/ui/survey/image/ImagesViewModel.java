package in.ults.gisurvey.ui.survey.image;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.IMG_ONE;
import static in.ults.gisurvey.utils.AppConstants.IMG_THREE;
import static in.ults.gisurvey.utils.AppConstants.IMG_TWO;
import static in.ults.gisurvey.utils.AppConstants.NA_UPPERCASE;
import static in.ults.gisurvey.utils.AppConstants.NR_UPPERCASE;
import static in.ults.gisurvey.utils.FileUtils.getTimeStampForSave;

public class ImagesViewModel extends BaseViewModel<ImagesNavigator> {

    public final MutableLiveData<String> informedBy = new MutableLiveData<>();
    public final MutableLiveData<String> cooperateSurvey = new MutableLiveData<>();
    public final MutableLiveData<String> remarks = new MutableLiveData<>();
    public final MutableLiveData<String> surveyorNameValue = new MutableLiveData<>();
    public final MutableLiveData<String> imageUrl1 = new MutableLiveData<>();
    public final MutableLiveData<String> imageUrl2 = new MutableLiveData<>();
    public final MutableLiveData<String> imageUrl3 = new MutableLiveData<>();
    public final MutableLiveData<String> extraRemarks = new MutableLiveData<>();


    public final MutableLiveData<List<CommonItem>> informedByList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> cooperateSurveyList = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<Surveyor>> surveyorList = new MutableLiveData<>();

    public final ObservableBoolean doorStatusPDCGLDC = new ObservableBoolean(false);
    public final ObservableBoolean isBuildStatusDemolishedUnusable = new ObservableBoolean(false);
    public final ObservableBoolean isMoreRemarksVisible = new ObservableBoolean(false);


    private static final String IMAGE_ONE_NAME = "img_1";
    private static final String IMAGE_TWO_NAME = "img_2";
    private static final String IMAGE_THREE_NAME = "plan_image";


    public ImagesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void saveImageDetails(String informedBy, String cooperativeSurvey, String surveyorName, ArrayList<Surveyor> surveyorDetailsList, String remarks, String imagePath1, String imagePath2, String imagePath3,String extraRemarks,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertPropertyImageDetails(informedBy, cooperativeSurvey, surveyorName,surveyorDetailsList, remarks, imagePath1, imagePath2,imagePath3,extraRemarks,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getNavigator().navigateToScreenSelection())
                .subscribe());
    }


    protected boolean validateFields(String informedBy, String cooperativeSurvey, String surveyorName, String remarks, String imagePath1, String imagePath2,String imagePath3) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(imagePath1)) {
            getNavigator().validationError(ImagesFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_image_path1));
            return false;
        } else if (TextUtils.isEmpty(imagePath2)) {
            getNavigator().validationError(ImagesFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_image_path2));
            return false;
        }else if (!isBuildStatusDemolishedUnusable.get() && TextUtils.isEmpty(imagePath3)) {
            getNavigator().validationError(ImagesFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_image_path3));
            return false;
        } else if (TextUtils.isEmpty(informedBy)) {
            getNavigator().validationError(ImagesFragment.INFORMED_BY_ERROR, getBaseActivity().getString(R.string.error_informed_by));
            return false;
        } else if (isPointStatusBuilding() && !isBuildStatusDemolishedUnusable.get() && !doorStatusPDCGLDC.get() && TextUtils.isEmpty(cooperativeSurvey)) {
            getNavigator().validationError(ImagesFragment.COOPERATIVE_SURVEY_ERROR, getBaseActivity().getString(R.string.error_cooperative_survey));
            return false;
        } else if (TextUtils.isEmpty(surveyorName)) {
            getNavigator().validationError(ImagesFragment.SURVEYOR_NAME_ERROR, getBaseActivity().getString(R.string.error_surveyor_name));
            return false;
        }else if(getSurveyorName().equals(getBaseActivity().getString(R.string.settings_default))){
            getNavigator().validationError(ImagesFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_surveyor_selection));
            return false;
        } else if (TextUtils.isEmpty(remarks)) {
            getNavigator().validationError(ImagesFragment.REMARKS_ERROR, getBaseActivity().getString(R.string.error_remarks));
            return false;
        }
        return true;
    }

    void loadData() {
        informedByList.setValue(IPMSApp.getAppInstance().getLocMainItem().getInformedBy());
        cooperateSurveyList.setValue(IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNO());
    }


    void setImageUrl1(String imageUrl) {
        this.imageUrl1.setValue(imageUrl);
    }

    void setImageUrl2(String imageUrl) {
        this.imageUrl2.setValue(imageUrl);
    }

    void setImageUrl3(String imageUrl) {
        this.imageUrl3.setValue(imageUrl);
    }

    public void onImage1Click() {
        getNavigator().captureImage(ImagesFragment.IMAGE_CAPTURE_ONE);
    }

    public void onImage2Click() {
        getNavigator().captureImage(ImagesFragment.IMAGE_CAPTURE_TWO);
    }
    public void onImage3Click() {
        getNavigator().captureImage(ImagesFragment.IMAGE_CAPTURE_THREE);
    }

    void saveImageLocally(String path, int reqTypeCode) {
        String imageNameType = "";
        String imgType = "";
        if (reqTypeCode == ImagesFragment.IMAGE_CAPTURE_ONE) {
            imageNameType = IMAGE_ONE_NAME;
            imgType=IMG_ONE;
        } else if (reqTypeCode == ImagesFragment.IMAGE_CAPTURE_TWO) {
            imageNameType = IMAGE_TWO_NAME;
            imgType=IMG_TWO;
        }else if (reqTypeCode == ImagesFragment.IMAGE_CAPTURE_THREE) {
            imageNameType = IMAGE_THREE_NAME;
            imgType=IMG_THREE;
        }

        String imageName = getDataManager().getCurrentPropertyId() + "_" +getTimeStampForSave()+"_"+
                imageNameType + FileUtils.DEFAULT_IMAGE_EXTENSION;
        //for above Android10
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (FileUtils.reduceImageSizeAboveQ(getBaseActivity(),new File(path), imageName,imgType)) {
                getNavigator().setImagePath(imageName, reqTypeCode);
            }
        }else{
            //for below Android10
            if (FileUtils.reduceImageSizeBelowQ(new File(path), imageName,imgType)) {
                getNavigator().setImagePath(imageName, reqTypeCode);
            }
        }
    }

    public void onNextClick() {
        getNavigator().saveImageDetails(false);
    }
    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveImageDetails(true);
    }

    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        isBuildStatusDemolishedUnusable.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE));

        doorStatusPDCGLDC.set(property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL));

        informedBy.setValue(property.informedBy);
        remarks.setValue(property.commonRemarks);
        if (isPointStatusBuilding() && !isBuildStatusDemolishedUnusable.get() && !doorStatusPDCGLDC.get()) {
            cooperateSurvey.setValue(property.cooperateSurvey);
        }
        if (property.surveyorName.length() == 0)
            surveyorNameValue.setValue(getSurveyorName());
        else
            surveyorNameValue.setValue(property.surveyorName);
        if(property.surveyorDetails!=null&&property.surveyorDetails.size()!=0){
            surveyorList.setValue(property.surveyorDetails);
        }else{
            surveyorList.setValue(getSurveyorDetails());
        }
        if(property.extraRemarks.length()==0||property.extraRemarks.equalsIgnoreCase(NA_UPPERCASE)||property.extraRemarks.equalsIgnoreCase(NR_UPPERCASE)){
            isMoreRemarksVisible.set(false);
        }else{
            isMoreRemarksVisible.set(true);
        }
        extraRemarks.setValue(property.extraRemarks);

        getNavigator().setImagePath(property.propertyImageOne, ImagesFragment.IMAGE_CAPTURE_ONE);
        getNavigator().setImagePath(property.propertyImageTwo, ImagesFragment.IMAGE_CAPTURE_TWO);
        getNavigator().setImagePath(property.propertyImageThree, ImagesFragment.IMAGE_CAPTURE_THREE);
        if(property.isImageValidationOk)
            getNavigator().disablePartialSave();

    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnInformedByInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_informed_by), InfoVideoNames.COMMON_DETAILS_INFORMED_BY_INFO_VIDEO);
                break;
            case R.id.btnCooperativeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_cooperative),InfoVideoNames.COMMON_DETAILS_COOPERATIVE_WITH_SURVEY_INFO_VIDEO);
                break;
            case R.id.btnInformedRemarks:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_remarks),InfoVideoNames.COMMON_DETAILS_REMARKS_INFO_VIDEO);
                break;
        }
    }
    public void onNoCommentsClick() {
        remarks.setValue(AppConstants.NO_COMMENTS);
    }
    public void onNoMoreCommentsClick() {
        extraRemarks.setValue(AppConstants.NO_COMMENTS);
    }
    public void onMoreCommentsClick() {

        isMoreRemarksVisible.set(true);

    }
}