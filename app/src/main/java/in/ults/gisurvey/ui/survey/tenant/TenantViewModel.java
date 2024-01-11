package in.ults.gisurvey.ui.survey.tenant;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Set;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.ui.survey.owner.OwnerFragment;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class TenantViewModel extends BaseViewModel<TenantNavigator> {

    public final MutableLiveData<String> tenantName = new MutableLiveData<>();
    public final MutableLiveData<String> tenantHouseName = new MutableLiveData<>();
    public final MutableLiveData<String> tenantStreetName = new MutableLiveData<>();
    public final MutableLiveData<String> tenantSurveyNumber = new MutableLiveData<>();
    public final MutableLiveData<String> tenantState = new MutableLiveData<>();
    public final MutableLiveData<String> tenantPostOffice = new MutableLiveData<>();
    public final MutableLiveData<String> tenantPinCode = new MutableLiveData<>();
    public final MutableLiveData<String> tenantEmail = new MutableLiveData<>();
    public final MutableLiveData<String> tenantMobileNumber = new MutableLiveData<>();
    public final MutableLiveData<String> tenantLandLine = new MutableLiveData<>();
    public final MutableLiveData<String> tenantAmount = new MutableLiveData<>();
    public final MutableLiveData<String> tenantNative = new MutableLiveData<>();
    public final MutableLiveData<String> tenantStatus = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> tenantNativeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> tenantStatusList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> postOfficeList = new MutableLiveData<>();
    public final MutableLiveData<Set<String>> stateList = new MutableLiveData<>();
    public final ObservableBoolean isTenantStatusAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isBuildingtypeResidential = new ObservableBoolean(false);
    public final ObservableBoolean isTeleCall = new ObservableBoolean(false);


    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingSubType = "";

    public TenantViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setTenantNativeRelatedData(String tenantNative) {
        boolean isTenantStatus = tenantNative.equalsIgnoreCase(AppConstants.TENANT_NATIVE_INSIDE_LSGD);
        isTenantStatusAvailable.set(isTenantStatus);
        if(!isTenantStatus){
            tenantStatus.setValue("");
        }
    }

    public void setPostOfficeList(List<CommonItem> postOfficeList) {
        this.postOfficeList.setValue(postOfficeList);
    }

    public void setTenantPostOffice(String tenantPostOffice) {
        this.tenantPostOffice.setValue(tenantPostOffice);
    }

    public void setTenantState(String tenantState) {
        this.tenantState.setValue(tenantState);
    }

    public void setTenantPinCode(String tenantPinCode) {
        this.tenantPinCode.setValue(tenantPinCode);
    }


    void insertTenantDetails(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertTenantDetails(tenantName, tenantHouseNameNumber, tenantStreetPlaceName, tenantState, tenantPostOffice, tenantPincode, tenantLandLine, tenantMobile, tenantEmail, tenantAmount, tenantNative, tenantSurveyNumber, tenantStatus,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToTaxDetails(buildingStatus, doorStatus)) {
//                        getNavigator().navigateToTaxPage();
//                    } else if (canIGoToEstablishmentDetails(buildingStatus, buildingSubType)) {
//                        getNavigator().navigateToEstablishmentPage();
//                    } else if (canIGoToMemberDetails(buildingStatus, buildingType, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToMemberPage();
//                    } else if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToLiveHoodPage();
//                    } else if (canIGoToMoreDetails(buildingStatus,doorStatus,buildingType,buildingSubType)) {
//                        getNavigator().navigateToMorePage();
//                    } else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    } else {
                        getNavigator().navigateToScreenSelection();
//                    }
                })
                .subscribe());
    }

    void loadData() {
        tenantNativeList.setValue(IPMSApp.getAppInstance().getLocMainItem().getTenantNatives());
        tenantStatusList.setValue(IPMSApp.getAppInstance().getLocMainItem().getTenantStatus());
        stateList.setValue(IPMSApp.getAppInstance().getPostOffice().keySet());
        tenantState.setValue(AppConstants.DEFAULT_STATE);
        postOfficeList.setValue(IPMSApp.getAppInstance().getPostOffice().get(AppConstants.DEFAULT_STATE));
    }

    /**
     * to fetch property details from db when back pressed and set to fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        doorStatus = property.doorStatus;
        buildingStatus = property.buildingStatus;
        buildingType = property.buildingType;
        buildingSubType = property.buildingSubType;
        tenantName.setValue(property.tenantName);
        tenantHouseName.setValue(property.tenantHouseNameNumber);
        tenantStreetName.setValue(property.tenantStreetPlaceName);
        tenantSurveyNumber.setValue(property.tenantSurveyNumber);
        tenantState.setValue(property.tenantState);
        tenantPostOffice.setValue(property.tenantPostOffice);
        tenantPinCode.setValue(property.tenantPincode);
        tenantMobileNumber.setValue(property.tenantMobile);
        tenantLandLine.setValue(property.tenantLandLine);
        tenantEmail.setValue(property.tenantEmail);
        tenantAmount.setValue(property.tenantAmount);
        tenantNative.setValue(property.tenantNative);
        setTenantNativeRelatedData(property.tenantNative);
        if (isTenantStatusAvailable.get()) {
            tenantStatus.setValue(property.tenantStatus);
        }

        isBuildingtypeResidential.set(property.buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL));
        isTeleCall.set(property.surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));

        if(property.isTenantValidationOk)
            getNavigator().disablePartialSave();
    }

    /**
     * validate each field and set error message if validation fails
     *
     * @return
     */
    protected boolean validateFields(String tenantName, String tenantHouseNameNumber, String tenantStreetPlaceName, String tenantState, String tenantPostOffice, String tenantPincode, String tenantLandLine, String tenantMobile, String tenantEmail, String tenantAmount, String tenantNative, String tenantSurveyNumber, String tenantStatus) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(tenantName)) {
            getNavigator().validationError(TenantFragment.TENANT_NAME_ERROR, getBaseActivity().getString(R.string.error_tenant_name));
            return false;
        } else if (TextUtils.isEmpty(tenantHouseNameNumber)) {
            getNavigator().validationError(TenantFragment.TENANT_HOUSE_ERROR, getBaseActivity().getString(R.string.error_tenant_house_name));
            return false;
        } else if (TextUtils.isEmpty(tenantStreetPlaceName)) {
            getNavigator().validationError(TenantFragment.TENANT_STREET_ERROR, getBaseActivity().getString(R.string.error_tenant_street_name));
            return false;
        } else if (TextUtils.isEmpty(tenantSurveyNumber)) {
            getNavigator().validationError(TenantFragment.TENANT_SURVEY_NUMBER_ERROR, getBaseActivity().getString(R.string.error_tenant_survey_number));
            return false;
        } else if (TextUtils.isEmpty(tenantState)) {
            getNavigator().validationError(TenantFragment.TENANT_STATE_ERROR, getBaseActivity().getString(R.string.error_tenant_state));
            return false;
        } else if (TextUtils.isEmpty(tenantPostOffice)) {
            getNavigator().validationError(TenantFragment.TENANT_POST_OFFICE_ERROR, getBaseActivity().getString(R.string.error_tenant_post));
            return false;
        } else if (TextUtils.isEmpty(tenantPincode)) {
            getNavigator().validationError(TenantFragment.TENANT_PIN_ERROR, getBaseActivity().getString(R.string.error_tenant_pincode));
            return false;
        } else if (!tenantPincode.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && tenantPincode.length() != 6) {
            getNavigator().validationError(TenantFragment.TENANT_PIN_ERROR, getBaseActivity().getString(R.string.error_tenant_valid_pincode));
            return false;
        } else if (TextUtils.isEmpty(tenantMobile)) {
            getNavigator().validationError(TenantFragment.TENANT_MOBILE_ERROR, getBaseActivity().getString(R.string.error_tenant_mobile));
            return false;
        } else if (!tenantMobile.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !CommonUtils.isMobileValid(tenantMobile)) {
            getNavigator().validationError(TenantFragment.TENANT_MOBILE_ERROR, getBaseActivity().getString(R.string.error_tenant_valid_mobile));
            return false;
        } else if (TextUtils.isEmpty(tenantLandLine)) {
            getNavigator().validationError(TenantFragment.TENANT_LAND_LINE_ERROR, getBaseActivity().getString(R.string.error_tenant_enter_land_line));
            return false;
        } else if (!tenantLandLine.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !CommonUtils.isLandlineValid(tenantLandLine)) {
            getNavigator().validationError(TenantFragment.TENANT_LAND_LINE_ERROR, getBaseActivity().getString(R.string.error_tenant_valid_land_line));
            return false;
        } else if (TextUtils.isEmpty(tenantEmail)) {
            getNavigator().validationError(TenantFragment.TENANT_EMAIL_ERROR, getBaseActivity().getString(R.string.error_enter_tenant_email));
            return false;
        } else if (!tenantEmail.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !(CommonUtils.isEmailValid(tenantEmail) || tenantEmail.equalsIgnoreCase(AppConstants.NR_SIMPLE))) {
            getNavigator().validationError(TenantFragment.TENANT_EMAIL_ERROR, getBaseActivity().getString(R.string.error_tenant_valid_email));
            return false;
        } else if (TextUtils.isEmpty(tenantAmount)) {
            getNavigator().validationError(TenantFragment.TENANT_AMOUNT_ERROR, getBaseActivity().getString(R.string.error_tenant_amount));
            return false;
        } else if (TextUtils.isEmpty(tenantNative)) {
            getNavigator().validationError(TenantFragment.TENANT_NATIVE_ERROR, getBaseActivity().getString(R.string.error_tenant_native));
            return false;
        } else if (isTenantStatusAvailable.get() && isBuildingtypeResidential.get() && TextUtils.isEmpty(tenantStatus)) {
            getNavigator().validationError(TenantFragment.TENANT_STATUS_ERROR, getBaseActivity().getString(R.string.error_tenant_status));
            return false;
        }
        return true;
    }

    public void onNextClick() {
        getNavigator().saveTenantDetails(false);
    }

    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveTenantDetails(true);
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrTenantName:
                tenantName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrHouseName:
                tenantHouseName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrStreetName:
                tenantStreetName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrTenantSurveyNumber:
//                tenantSurveyNumber.setValue(AppConstants.NR_UPPERCASE);
                 getNavigator().showSurveyNoNRNoLandPopUp();
                break;
            case R.id.nrTenantState:
                tenantState.setValue(AppConstants.NR_UPPERCASE);
                tenantPostOffice.setValue(AppConstants.NR_UPPERCASE);
                tenantPinCode.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrTenantPinCode:
            case R.id.nrTenantPostOffice:
                tenantPostOffice.setValue(AppConstants.NR_UPPERCASE);
                tenantPinCode.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrTenantEmail:
                tenantEmail.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrTenantPhoneNumber:
                tenantMobileNumber.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrTenantLandLine:
                tenantLandLine.setValue(AppConstants.NR_UPPERCASE);
                break;
         case R.id.nrTenantAmount:
                tenantAmount.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnTenantNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_name),InfoVideoNames.TENANT_DETAILS_NAME_INFO_VIDEO);
                break;
            case R.id.btnTenantHouseInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_house),InfoVideoNames.TENANT_DETAILS_HOUSE_NAME_INFO_VIDEO);
                break;
            case R.id.btnTenantStreetInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_street),InfoVideoNames.TENANT_DETAILS_PLACE_NAME_INFO_VIDEO);
                break;
            case R.id.btnTenantSurveyNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_survey_no),InfoVideoNames.TENANT_DETAILS_SURVEY_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnTenantStateInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_state),InfoVideoNames.TENANT_DETAILS_STATE_INFO_VIDEO);
                break;
            case R.id.btnTenantPostOfficeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_post_office),InfoVideoNames.TENANT_DETAILS_POST_OFFICE__INFO_VIDEO);
                break;
            case R.id.btnTenantPincodeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_pincode),InfoVideoNames.TENANT_DETAILS_PINCODE_INFO_VIDEO);
                break;
            case R.id.btnTenantLandLineInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_phone),InfoVideoNames.TENANT_DETAILS_PHONE_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnLandlineInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_landline),InfoVideoNames.TENANT_DETAILS_LANDLINE_INFO_VIDEO);
                break;
            case R.id.btnTenantEmailInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_email),InfoVideoNames.TENANT_DETAILS_EMAIL_INFO_VIDEO);
                break;
            case R.id.btnTenantAmountInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_amount),InfoVideoNames.TENANT_DETAILS_AMOUNT_INFO_VIDEO);
                break;
            case R.id.btnTenantNativeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_native),InfoVideoNames.TENANT_DETAILS_NATIVE_INFO_VIDEO);
                break;
            case R.id.btnTenantStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tenant_status),InfoVideoNames.TENANT_DETAILS_STATUS_INFO_VIDEO);
                break;

        }
    }

}