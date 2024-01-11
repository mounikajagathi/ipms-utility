package in.ults.gisurvey.ui.survey.owner;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Set;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class OwnerViewModel extends BaseViewModel<OwnerNavigator> {

    public final ObservableBoolean isBuildStatusDemolishedUnusable = new ObservableBoolean(false);
    public final MutableLiveData<List<CommonItem>> occupationList = new MutableLiveData<>();
    public final MutableLiveData<List<Owner>> ownerList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> postOfficeList = new MutableLiveData<>();
    public final MutableLiveData<Set<String>> stateList = new MutableLiveData<>();


    public final MutableLiveData<String> ownerName = new MutableLiveData<>();
    public final MutableLiveData<String> ownerOccupation = new MutableLiveData<>();
    public final MutableLiveData<String> ownerHouseName = new MutableLiveData<>();
    public final MutableLiveData<String> ownerStreetName = new MutableLiveData<>();
    public final MutableLiveData<String> ownerState = new MutableLiveData<>();
    public final MutableLiveData<String> ownerPostOffice = new MutableLiveData<>();
    public final MutableLiveData<String> ownerPincode = new MutableLiveData<>();
    public final MutableLiveData<String> ownerEmail = new MutableLiveData<>();
    public final MutableLiveData<String> ownerLandline = new MutableLiveData<>();
    public final MutableLiveData<String> ownerMobile = new MutableLiveData<>();
    public final MutableLiveData<String> ownerAddressAR = new MutableLiveData<>();
    public final ObservableBoolean isAROwnerAddressAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isTeleCall = new ObservableBoolean(false);
    public final MutableLiveData<String> teleCallNumber = new MutableLiveData<>();

    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingUsage = "";
    private String buildingSubType = "";
    private String surveyType = "";


    public OwnerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to fetch property details from db when back pressed and set to property fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        doorStatus = property.doorStatus;
        buildingStatus = property.buildingStatus;
        buildingType = property.buildingType;
        buildingSubType = property.buildingSubType;
        buildingUsage = property.buildingUsage;
        ownerName.setValue(property.ownerName);
        surveyType = property.surveyType;
        if (buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)) {
            isBuildStatusDemolishedUnusable.set(true);
        }
        if(property.arOwnerAddress.length()!=0){
            isAROwnerAddressAvailable.set(true);
        }
        ownerAddressAR.setValue(property.arOwnerAddress);
        if(surveyType.equalsIgnoreCase(AppConstants.TELE_CALL)){
            isTeleCall.set(true);
        }
        if (isPointStatusBuilding() && !isBuildStatusDemolishedUnusable.get()) {
            ownerOccupation.setValue(property.ownerOccupation);
            ownerHouseName.setValue(property.ownerHouseNameNumber);
            ownerStreetName.setValue(property.ownerStreetPlaceName);
            ownerEmail.setValue(property.ownerEmail);
            ownerLandline.setValue(property.ownerLandLine);
            ownerMobile.setValue(property.ownerMobile);
            teleCallNumber.setValue(property.teleCallNumber);

            String state = property.ownerState;
            ownerState.setValue(state);
            if (IPMSApp.getAppInstance().getPostOffice().containsKey(state)) {
                postOfficeList.setValue(IPMSApp.getAppInstance().getPostOffice().get(state));
            }
            ownerPostOffice.setValue(property.ownerPostOffice);
            ownerPincode.setValue(property.ownerPincode);
        }
        if(property.isOwnerValidationOk)
            getNavigator().disablePartialSave();
    }


    public void setOwnerPostOffice(String ownerPostOffice) {
        this.ownerPostOffice.setValue(ownerPostOffice);
    }

    public void setOwnerState(String ownerState) {
        this.ownerState.setValue(ownerState);
    }

    public void setOwnerPincode(String ownerPincode) {
        this.ownerPincode.setValue(ownerPincode);
    }

    public void setPostOfficeList(List<CommonItem> postOfficeList) {
        this.postOfficeList.setValue(postOfficeList);
    }


    /**
     * to set the list of owners
     */
    public void setOwnerListData(String ownerOccupation, String ownerHouseName, String ownerStreetName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandline, String ownerMobile) {
        this.ownerOccupation.setValue(ownerOccupation);

        if(isPointStatusBuilding() && !isBuildStatusDemolishedUnusable.get()) {
            this.ownerHouseName.setValue(ownerHouseName);
            this.ownerStreetName.setValue(ownerStreetName);
            this.ownerState.setValue(ownerState);
            this.ownerPostOffice.setValue(ownerPostOffice);
            this.ownerPincode.setValue(ownerPincode);
            this.ownerEmail.setValue(ownerEmail);
            this.ownerLandline.setValue(ownerLandline);
            this.ownerMobile.setValue(ownerMobile);
        }
    }

    /**
     * load occupation list
     */
    void loadData() {
        occupationList.setValue(IPMSApp.getAppInstance().getLocMainItem().getJobs());
        stateList.setValue(IPMSApp.getAppInstance().getPostOffice().keySet());
        ownerState.setValue(AppConstants.DEFAULT_STATE);
        postOfficeList.setValue(IPMSApp.getAppInstance().getPostOffice().get(AppConstants.DEFAULT_STATE));
    }

    /**
     * Show owner details which we got from Ar during  property id check
     */
  public  void onFetchOwnerAddressClick(){
        getNavigator().showAROwnerAddress();
    }

    /**
     * @param ownerName to save owner details in db  and navigate to next page
     */
    void saveOwnerDetails(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String ownerEmail, String ownerLandLine, String ownerMobile, String teleCallNumber,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertOwnerDetails(ownerName, ownerOccupation, ownerHouseNameNumber, ownerStreetPlaceName, ownerState, ownerPostOffice, ownerPincode, ownerEmail, ownerLandLine, ownerMobile, teleCallNumber, isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if(isValidationOk){
//                        if (isPointStatusUnwanted()) {
//                            getNavigator().navigateToImageDetails();
//                        } else {
//                            if (canIGoToRoadDetails(buildingStatus)) {
//                                getNavigator().navigateToRoadPage();
//                            } else if (canIGoToTenantDetails(buildingUsage,buildingStatus,doorStatus,buildingSubType)) {
//                                getNavigator().navigateToTenantPage();
//                            } else if (canIGoToTaxDetails(buildingStatus, doorStatus)) {
//                                getNavigator().navigateToTaxPage();
//                            } else if (canIGoToEstablishmentDetails(buildingStatus, buildingSubType)) {
//                                getNavigator().navigateToEstablishmentPage();
//                            } else if (canIGoToMemberDetails(buildingStatus, buildingType, buildingSubType, doorStatus)) {
//                                getNavigator().navigateToMemberPage();
//                            } else if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType,doorStatus)) {
//                                getNavigator().navigateToLiveHoodPage();
//                            } else if (canIGoToMoreDetails(buildingStatus,doorStatus,buildingType,buildingSubType)) {
//                                getNavigator().navigateToMorePage();
//                            }else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                                getNavigator().navigateToBuildingPage();
//                            } else {
//                                getNavigator().navigateToImageDetails();
//                            }
//                        }
//                    }else{
                        getNavigator().navigateToScreenSelection();
//                    }

                })
                .subscribe());
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String ownerName, String ownerOccupation, String ownerHouseNameNumber, String ownerStreetPlaceName, String ownerState, String ownerPostOffice, String ownerPincode, String email, String ownerLandline, String ownerMobile, String teleCallNumber) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(ownerName)) {
            getNavigator().validationError(OwnerFragment.OWNER_NAME_ERROR, getBaseActivity().getString(R.string.error_owner_name));
            return false;
        } else if(isPointStatusBuilding() && !isBuildStatusDemolishedUnusable.get()) {
            if (TextUtils.isEmpty(ownerOccupation) && !isTeleCall.get()) {
                getNavigator().validationError(OwnerFragment.OWNER_OCCUPATION_ERROR, getBaseActivity().getString(R.string.error_owner_occupation));
                return false;
            } else if (TextUtils.isEmpty(ownerHouseNameNumber)) {
                getNavigator().validationError(OwnerFragment.OWNER_HOUSE_ERROR, getBaseActivity().getString(R.string.error_owner_house_name));
                return false;
            } else if (TextUtils.isEmpty(ownerStreetPlaceName)) {
                getNavigator().validationError(OwnerFragment.OWNER_STREET_ERROR, getBaseActivity().getString(R.string.error_owner_street_name));
                return false;
            } else if (!ownerPostOffice.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownerState)) {
                getNavigator().validationError(OwnerFragment.OWNER_STATE_ERROR, getBaseActivity().getString(R.string.error_owner_state));
                return false;
            } else if (!ownerPostOffice.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownerPostOffice)) {
                getNavigator().validationError(OwnerFragment.OWNER_POST_OFFICE_ERROR, getBaseActivity().getString(R.string.error_owner_post));
                return false;
            } else if (!ownerPincode.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownerPincode)) {
                getNavigator().validationError(OwnerFragment.OWNER_PIN_ERROR, getBaseActivity().getString(R.string.error_owner_pincode));
                return false;
            } else if (!ownerPincode.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && ownerPincode.length() != 6) {
                getNavigator().validationError(OwnerFragment.OWNER_PIN_ERROR, getBaseActivity().getString(R.string.error_owner_valid_pincode));
                return false;
            } else if (!email.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(email)&& !isTeleCall.get()) {
                getNavigator().validationError(OwnerFragment.OWNER_EMAIL_ERROR, getBaseActivity().getString(R.string.error_enter_owner_email));
                return false;
            } else if (!email.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !(CommonUtils.isEmailValid(email) || email.equalsIgnoreCase(AppConstants.NR_SIMPLE)) && !isTeleCall.get()) {
                getNavigator().validationError(OwnerFragment.OWNER_EMAIL_ERROR, getBaseActivity().getString(R.string.error_owner_valid_email));
                return false;
            } else if (!ownerLandline.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownerLandline)) {
                getNavigator().validationError(OwnerFragment.OWNER_LAND_LINE, getBaseActivity().getString(R.string.error_owner_enter_land_line));
                return false;
            }else if (!ownerLandline.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !CommonUtils.isLandlineValid(ownerLandline)) {
                getNavigator().validationError(OwnerFragment.OWNER_LAND_LINE, getBaseActivity().getString(R.string.error_owner_valid_land_line));
                return false;
            } else if (!ownerMobile.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && TextUtils.isEmpty(ownerMobile)) {
                getNavigator().validationError(OwnerFragment.OWNER_MOBILE_ERROR, getBaseActivity().getString(R.string.error_owner_mobile));
                return false;
            } else if (!ownerMobile.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !CommonUtils.isMobileValid(ownerMobile)) {
                getNavigator().validationError(OwnerFragment.OWNER_MOBILE_ERROR, getBaseActivity().getString(R.string.error_owner_valid_mobile));
                return false;
            } else if (TextUtils.isEmpty(teleCallNumber) && isTeleCall.get()) {
                getNavigator().validationError(OwnerFragment.TELE_CALL_ERROR, getBaseActivity().getString(R.string.error_tele_call_number));
                return false;
            } else if (!CommonUtils.isMobileValid(teleCallNumber) && isTeleCall.get()) {
                getNavigator().validationError(OwnerFragment.TELE_CALL_ERROR, getBaseActivity().getString(R.string.error_valid_tele_call_number));
                return false;
            }
        }
        return true;
    }

    /**
     * to save owner details
     */
    public void onNextClick() {
        getNavigator().saveOwnerDetails(false);
    }
    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveOwnerDetails(true);
    }
    /**
     * get owner list from db
     */
    public void getOwnerList() {
        getCompositeDisposable().add(getDataManager()
                .getOwnerList(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(owners -> {
                    if (owners != null && owners.size() > 0) {
                        ownerList.setValue(owners);
                    }
                })
                .subscribe());
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrName:
                ownerName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOccupation:
                ownerOccupation.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrHouseName:
                ownerHouseName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrStreetName:
                ownerStreetName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOwnerState:
                ownerState.setValue(AppConstants.NR_UPPERCASE);
                ownerPostOffice.setValue(AppConstants.NR_UPPERCASE);
                ownerPincode.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOwnerPostOffice:
            case R.id.nrOwnerPinCode:
                ownerPostOffice.setValue(AppConstants.NR_UPPERCASE);
                ownerPincode.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOwnerEmail:
                ownerEmail.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOwnerLandLine:
                ownerLandline.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrOwnerMobile:
                ownerMobile.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnOccupationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_occupation),InfoVideoNames.OWNER_OCCUPATION_INFO_VIDEO);
                break;
            case R.id.btnHouseNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_house_name),InfoVideoNames.OWNER_HOUSE_NAME_INFO_VIDEO);
                break;
            case R.id.btnStreetNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_street_name),InfoVideoNames.OWNER_PLACE_NAME_INFO_VIDEO);
                break;
            case R.id.btnPostOfficeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_post_office),InfoVideoNames.OWNER_POST_OFFICE_INFO_VIDEO);
                break;
            case R.id.btnPincodeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pincode),InfoVideoNames.OWNER_PINCODE_INFO_VIDEO);
                break;
            case R.id.btnEmailInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_email),InfoVideoNames.OWNER_EMAIL_INFO_VIDEO);
                break;
            case R.id.btnLandlineInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_landline),InfoVideoNames.OWNER_LANDLINE_INFO_VIDEO);
                break;
            case R.id.btnMobileInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_mobile),InfoVideoNames.OWNER_MOBILE_INFO_VIDEO);
                break;
            case R.id.btnTeleCallNumberInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_tele_call_number),InfoVideoNames.OWNER_TELE_CALL_NUMBER_INFO_VIDEO);
                break;


        }
    }
}