package in.ults.gisurvey.ui.survey.establishment;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class EstablishmentViewModel extends BaseViewModel<EstablishmentNavigator> {


    public final MutableLiveData<String> establishmentName = new MutableLiveData<>();
    public final MutableLiveData<String> establishmentType = new MutableLiveData<>();
    public final MutableLiveData<String> establishmentYear = new MutableLiveData<>();
    public final MutableLiveData<String> inCharge = new MutableLiveData<>();
    public final MutableLiveData<String> inChargeRole = new MutableLiveData<>();
    public final MutableLiveData<String> employeeCount = new MutableLiveData<>();
    public final MutableLiveData<String> licenseNumber = new MutableLiveData<>();
    public final MutableLiveData<String> gstStatus = new MutableLiveData<>();

    public final MutableLiveData<String> establishmentEmail = new MutableLiveData<>();
    public final MutableLiveData<String> establishmentLandline = new MutableLiveData<>();
    public final MutableLiveData<String> establishmentMobile = new MutableLiveData<>();

    public final MutableLiveData<List<CommonItem>> gstStatusList = new MutableLiveData<>();

    public final ObservableBoolean doorStatusPDCDCGLNC = new ObservableBoolean(false);

    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingSubType = "";


    public EstablishmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void loadData() {
        gstStatusList.setValue(IPMSApp.getAppInstance().getLocMainItem().getCommonOptionsYesNO());
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

        doorStatusPDCDCGLNC.set(property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL) ||
                property.doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC));

        establishmentName.setValue(property.establishmentName);
        establishmentType.setValue(property.establishmentType);

        if (!doorStatusPDCDCGLNC.get()) {
            employeeCount.setValue(property.employeeCount);
            licenseNumber.setValue(property.licenseNumber);
            gstStatus.setValue(property.gstStatus);
            establishmentYear.setValue(property.establishmentYear);
            inCharge.setValue(property.inCharge);
            inChargeRole.setValue(property.inChargeRole);
            establishmentEmail.setValue(property.establishmentEmail);
            establishmentLandline.setValue(property.establishmentLandline);
            establishmentMobile.setValue(property.establishmentMobile);
        }
        if(property.isEstablishmentValidationOk)
            getNavigator().disablePartialSave();

    }

    /**
     * validate license number field and set error message if validation fails
     *
     * @param licenseNumber
     * @return
     */
    protected boolean validateFields(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(establishmentName)) {
            getNavigator().validationError(EstablishmentFragment.NAME_ERROR, getBaseActivity().getString(R.string.error_establishment_name));
            return false;
        } else if (TextUtils.isEmpty(establishmentType)) {
            getNavigator().validationError(EstablishmentFragment.TYPE_ERROR, getBaseActivity().getString(R.string.error_establishment_type));
            return false;
        }

        if (!doorStatusPDCDCGLNC.get()) {
            if (TextUtils.isEmpty(establishmentYear)) {
                getNavigator().validationError(EstablishmentFragment.ESTABLISHMENT_YEAR_ERROR, getBaseActivity().getString(R.string.error_establishment_year));
                return false;
            } else if (!TextUtils.isEmpty(establishmentYear) && establishmentYear.length() != 4) {
                getNavigator().validationError(EstablishmentFragment.ESTABLISHMENT_YEAR_ERROR, getBaseActivity().getString(R.string.error_valid_establishment_year));
                return false;
            } else if (TextUtils.isEmpty(inCharge)) {
                getNavigator().validationError(EstablishmentFragment.IN_CHARGE_ERROR, getBaseActivity().getString(R.string.error_in_charge));
                return false;
            } else if (TextUtils.isEmpty(inChargeRole)) {
                getNavigator().validationError(EstablishmentFragment.IN_CHARGE_ROLE_ERROR, getBaseActivity().getString(R.string.error_in_charge_role));
                return false;
            } else if (TextUtils.isEmpty(employeeCount)) {
                getNavigator().validationError(EstablishmentFragment.EMPLOYEE_COUNT_ERROR, getBaseActivity().getString(R.string.error_employee_count));
                return false;
            } else if (TextUtils.isEmpty(licenseNumber)) {
                getNavigator().validationError(EstablishmentFragment.LICENSE_NUMBER_ERROR, getBaseActivity().getString(R.string.error_license_number));
                return false;
            } else if (TextUtils.isEmpty(gstStatus)) {
                getNavigator().validationError(EstablishmentFragment.GST_STATUS_ERROR, getBaseActivity().getString(R.string.error_gst_status));
                return false;
            } else if (TextUtils.isEmpty(establishmentEmail)) {
                getNavigator().validationError(EstablishmentFragment.EMAIL, getBaseActivity().getString(R.string.error_establishment_email));
                return false;
            } else if (!establishmentEmail.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !(CommonUtils.isEmailValid(establishmentEmail) || establishmentEmail.equalsIgnoreCase(AppConstants.NR_SIMPLE))) {
                getNavigator().validationError(EstablishmentFragment.EMAIL, getBaseActivity().getString(R.string.error_valid_email));
                return false;
            } else if (TextUtils.isEmpty(establishmentLandline)) {
                getNavigator().validationError(EstablishmentFragment.LANDLINE, getBaseActivity().getString(R.string.error_establishment_landline));
                return false;
            } else if (!establishmentLandline.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !(CommonUtils.isLandlineValid(establishmentLandline) || establishmentLandline.equalsIgnoreCase(AppConstants.NR_SIMPLE))) {
                getNavigator().validationError(EstablishmentFragment.LANDLINE, getBaseActivity().getString(R.string.error_valid_land_line));
                return false;
            } else if (TextUtils.isEmpty(establishmentMobile)) {
                getNavigator().validationError(EstablishmentFragment.MOBILE, getBaseActivity().getString(R.string.error_establishment_mobile));
                return false;
            } else if (!establishmentMobile.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && !(CommonUtils.isMobileValid(establishmentMobile) || establishmentMobile.equalsIgnoreCase(AppConstants.NR_SIMPLE))) {
                getNavigator().validationError(EstablishmentFragment.MOBILE, getBaseActivity().getString(R.string.error_valid_mobile));
                return false;
            }
        }
        return true;
    }

    /**
     * to save establishment details in db  and navigate to next page
     *
     * @param establishmentName
     * @param establishmentType
     * @param inCharge
     * @param inChargeRole
     * @param establishmentYear
     * @param employeeCount
     * @param licenseNumber
     */
    void saveEstablishmentDetails(String establishmentName, String establishmentType, String inCharge, String inChargeRole, String establishmentYear, String employeeCount, String licenseNumber, String gstStatus, String establishmentEmail, String establishmentLandline, String establishmentMobile,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertEstablishmentDetails(establishmentName, establishmentType, inCharge, inChargeRole, establishmentYear, employeeCount, licenseNumber, gstStatus, establishmentEmail, establishmentLandline, establishmentMobile,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToMemberDetails(buildingStatus, buildingType, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToMemberPage();
//                    } else if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType, doorStatus)) {
//                        getNavigator().navigateToLiveHoodPage();
//                    } else if (canIGoToMoreDetails(buildingStatus, doorStatus, buildingType, buildingSubType)) {
//                        getNavigator().navigateToMorePage();
//                    } else if (canIGoToBuildingDetails(buildingStatus, doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    } else {
//                        getNavigator().navigateToImageDetails();
//                    }
                    getNavigator().navigateToScreenSelection();
                })
                .subscribe());
    }

    public void onNextClick() {
        getNavigator().saveEstablishmentDetails(false);
    }
    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveEstablishmentDetails(true);
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrEstablishmentEmail:
                establishmentEmail.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrLandline:
                establishmentLandline.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrMobile:
                establishmentMobile.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrEstablishmentName:
                establishmentName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrEstablishmentType:
                establishmentType.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrInCharge:
                inCharge.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrInChargeRole:
                inChargeRole.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrLicenseNumber:
                getNavigator().showLicenceNANRPopUp();
                break;
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnEstNameInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_est_name), InfoVideoNames.ESTABLISHMENT_NAME_INFO_VIDEO);
                break;
            case R.id.btnEstTypeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_est_type), InfoVideoNames.ESTABLISHMENT_TYPE_INFO_VIDEO);
                break;
            case R.id.btnEstYeareInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_est_year), InfoVideoNames.ESTABLISHMENT_YEAR_INFO_VIDEO);
                break;
            case R.id.btnInChargeInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_incharge), InfoVideoNames.ESTABLISHMENT_INCHARGE_INFO_VIDEO);
                break;
            case R.id.btnInChargeRoleInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_incharge_role), InfoVideoNames.ESTABLISHMENT_INCHARGE_ROLE_INFO_VIDEO);
                break;
            case R.id.btnEmployeeCountInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_employee_count), InfoVideoNames.ESTABLISHMENT_EMPLOYEE_COUNT_INFO_VIDEO);
                break;
            case R.id.btnLicenseNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_license_no), InfoVideoNames.ESTABLISHMENT_LICENSE_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnEstGSTStatusInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_est_gst_status), InfoVideoNames.ESTABLISHMENT_GST_NUMBER_STATUS_INFO_VIDEO);
                break;
        }
    }

}