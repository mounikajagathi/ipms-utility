package in.ults.gisurvey.ui.main.settings;

import android.os.Environment;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.DataSyncResponse;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.db.BackupData;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.CryptoUtils;
import in.ults.gisurvey.utils.FileUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import static in.ults.gisurvey.utils.FileUtils.fromBase64;
import static in.ults.gisurvey.utils.FileUtils.getDataFromFile;
import static in.ults.gisurvey.utils.FileUtils.toBase64;

public class SettingsViewModel extends BaseViewModel<SettingsNavigator> {


    public final MutableLiveData<String> districtName = new MutableLiveData<>();
    public final MutableLiveData<String> localBodyName = new MutableLiveData<>();
    public final MutableLiveData<String> wardNumberName = new MutableLiveData<>();
    public final MutableLiveData<String> reporting = new MutableLiveData<>();
    public final MutableLiveData<String> role = new MutableLiveData<>();
    public final MutableLiveData<String> tpkFile = new MutableLiveData<>();
    public final MutableLiveData<String> arFile = new MutableLiveData<>();
    public final MutableLiveData<String> infoVideoFile = new MutableLiveData<>();
    public final MutableLiveData<String> liveLocationUpdate = new MutableLiveData<>();
    public final ObservableBoolean widgetVisibility = new ObservableBoolean(true);
    private final MutableLiveData<List<SurveyWithProperty>> backupList = new MutableLiveData<>();
    private final MutableLiveData<List<SurveyWithProperty>> backupListAddedNA = new MutableLiveData<>();
    public final MutableLiveData<String> txtFile = new MutableLiveData<>();
    ArrayList<Survey> surveyArrayList = new ArrayList<Survey>();
    ArrayList<Property> propertyArrayList = new ArrayList<Property>();

    public SettingsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        /*districtName.setValue("");
        localBodyName.setValue("");
        reporting.setValue("");
        role.setValue("");
        tpkFile.setValue("");*/
    }

    @Override
    protected void onSurveyCountFromDb(int count) {
        super.onSurveyCountFromDb(count);
        if (count > 0)
            getBaseActivity().showToast(getBaseActivity().getString(R.string.settings_fetch_alert));
        else
            getNavigator().selectBackupData();
    }


    private void setBackupList(List<SurveyWithProperty> syncList) {
        this.backupList.setValue(syncList);
        txtFile.setValue(getBaseActivity().getString(R.string.settings_default_value));

        List<SurveyWithProperty> dataList = new ArrayList<>();
        for (SurveyWithProperty data : syncList) {
            if (data.surveyCompletedStatus)
                data.addNA();
            dataList.add(data);
        }
        this.backupListAddedNA.setValue(dataList);
    }

    public void onSurveyorListClick() {
        getNavigator().navigateToSurveyorList();
    }

    public void onSurveyPointWardSelectionClick() {
        if (getSelectedLocalBody() != null&&getWardNumber()!=null)
            getNavigator().navigateToSurveyPointsWardSelection();
    }

    public void onFetchBtnClick() {
        getSurveyCount();

    }

    @Override
    protected void onGettingSurveyPointsFromServer() {
        super.onGettingSurveyPointsFromServer();
        getNavigator().showMultipleWardSelectionAlert();
    }

    public void updateContent() {
        String notSet = getBaseActivity().getString(R.string.settings_default);
        String noTpkFile = getBaseActivity().getString(R.string.settings_tpk_location);
        String noARFile = getBaseActivity().getString(R.string.settings_ar_location);
        String noInfoVideoFolder = getBaseActivity().getString(R.string.settings_info_video_location);

        String district = getDataManager().getDistrict();
        String localBody;
        if (getDataManager().getLocalBody() != null)
            localBody = getDataManager().getLocalBody() + " (" + getDataManager().getLocalbodyCode() + ")";
        else
            localBody = getDataManager().getLocalBody();
        String wardNumberValue = getDataManager().getWardNumber();
        String tpkFileLocation = getDataManager().getTpkFileLocation();
        String arFileLocation = getDataManager().getARFileLocation();
        String infoVideoFileLocation = getDataManager().getInfoVideoFileLocation();

        districtName.setValue(district != null ? district : notSet);
        wardNumberName.setValue(wardNumberValue != null ? wardNumberValue : notSet);
        localBodyName.setValue(localBody != null ? localBody : notSet);
        tpkFile.setValue(tpkFileLocation != null ? tpkFileLocation : noTpkFile);
        arFile.setValue(arFileLocation != null ? arFileLocation : noARFile);
        infoVideoFile.setValue(infoVideoFileLocation != null ? infoVideoFileLocation : noInfoVideoFolder);
        liveLocationUpdate.setValue(getDataManager().getLiveLocationStatus() != null ? getDataManager().getLiveLocationStatus() : AppConstants.LIVE_LOC_OFF);

        Dashboard data = getDashBoard();
        if (data != null) {
            if (data.getEmployee() != null) {
                reporting.setValue(data.getEmployee().getEmployeeName());
                role.setValue(data.getEmployee().getRole());
            }
        }
    }

    /**
     * shows bottom sheet for district
     */
    public void onDistrictBtnClick() {
        Dashboard dashboard = getDashBoard();
        if (dashboard != null && dashboard.getDistrict() != null &&
                dashboard.getDistrict().size() > 0) {
            getNavigator().showDistrictBottomSheet(dashboard.getDistrict());
        }
    }

    /**
     * shows bottom sheet for district
     */
    public void onWardNumberBtnClick() {
        ArrayList<CommonItem> wardList = getSelectedWardNumberList();
        if (wardList != null)
            getNavigator().showWardNumberBottomSheet(wardList);

    }

    public void onARBtnClick() {
        getNavigator().selectARData();
    }


    /**
     * shows bottom sheet for local body to select
     */
    public void onLocalBodyBtnClick() {
        ArrayList<LocalBody> localBodyArrayList = getSelectedLocalBodyList();
        if (localBodyArrayList != null) {
            getNavigator().showLocalBodyBottomSheet(localBodyArrayList);
        }
    }

    /**
     * to set district in its field
     */
    protected void setDistrict(String district) {
        getDataManager().setDistrict(district);
        getDataManager().setLocalBody(null);
        this.districtName.setValue(district);
        this.localBodyName.setValue(getBaseActivity().getString(R.string.settings_default));
    }

    /**
     * to set local body in its field
     */
    protected void setLocalBody(String localBody) {
        getDataManager().setLocalBody(localBody);
        this.localBodyName.setValue(localBody);

    }

    /**
     * to set local body code in its field
     */
    protected void setLocalBodyCode(String localBodyCode) {
        getDataManager().setLocalbodyCode(localBodyCode);
        if (localBodyCode.length() != 0)
            this.localBodyName.setValue(this.localBodyName.getValue() + " (" + localBodyCode + ")");


    }

    protected void setWardNumber(String wardNumberValue) {
        getDataManager().setWardNumber(wardNumberValue);
        this.wardNumberName.setValue(wardNumberValue);

    }

    protected void setWardName(String wardnameValue) {
        getDataManager().setWardName(wardnameValue);

    }

    public void saveARLocation(String arLocation) {
        getDataManager().setARFileLocation(arLocation);
        arFile.setValue(arLocation);
    }

    public void saveInfoVideoLocation(String infoVideoLocation) {
        getDataManager().setInfoVideoFileLocation(infoVideoLocation);
        infoVideoFile.setValue(infoVideoLocation);
    }

    public void onSyncBtnClick() {
        getNavigator().syncData();
    }

    public void onTPKBtnClick() {
        getNavigator().selectTPKData();
    }

    public void onInfoVideoBtnClick() {
        getNavigator().selectInfoVideoData();
    }

    public void onBackupBtnClick() {
        getNavigator().backupSurvey();
    }

    /**
     * here call the sync method if surveywith property has value
     */
    @Override
    protected void onFetchUnSyncData(List<SurveyWithProperty> surveyWithProperties) {
        if (surveyWithProperties != null &&
                surveyWithProperties.size() > 0) {
            getNavigator().navigateToSync();
        } else {
            getBaseActivity().showToast(R.string.settings_no_data_to_sync);
        }
    }

    public void backupData() {
        getCompositeDisposable().add(getDataManager()
                .loadAllSurveyWithProperty()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveyWithProperties -> {

                    if (surveyWithProperties.size() > 0) {
                        setBackupList(surveyWithProperties);
                        if (backupListAddedNA != null)
                            backupFileToStorage(new Gson().toJson(backupListAddedNA));
                    } else {
                        getBaseActivity().showToast(R.string.settings_backup_no_data);
                    }
                })
                .doOnError(throwable -> getBaseActivity().showToast(R.string.settings_backup_failed))
                .subscribe());
    }

    public void saveTpkLocation(String tpkLocation) {
        getDataManager().setTpkFileLocation(tpkLocation);
        tpkFile.setValue(tpkLocation);
    }

    public void saveTxtLocation(String txtLocation, boolean isEncrypted) {
        txtFile.setValue(txtLocation);
        StringBuilder backupData = getDataFromFile(txtLocation);
        Gson gson = new Gson();
        BackupData dataObj = null;
        if (isEncrypted) {
            try {
                dataObj = gson.fromJson(CryptoUtils.decrypt(backupData.toString()), BackupData.class);
            } catch (Exception e) {
                getBaseActivity().showToast("Data restoration failed, selected file not in encrypted data format");
            }

        } else {
            try {
                dataObj = gson.fromJson(backupData.toString(), BackupData.class);
            } catch (Exception e) {
                getBaseActivity().showToast("Data restoration failed, selected file not in normal data format");
            }

        }

        surveyArrayList.clear();
        propertyArrayList.clear();
        if (dataObj != null) {
            for (int i = 0; i < dataObj.getmData().size(); i++) {
                SurveyWithProperty v = dataObj.getmData().get(i);
                Survey survey = new Survey(v.surveyID, v.surveyStartDate, v.pointStatus, v.floorCount, v.propertyCount, v.groundFloor, v.noOfBasements, v.district
                        , v.localBody, v.wardNumber, v.wardName, v.streetName, v.placeName, v.villageName, v.postOffice, v.pinCode, v.buildingZone, v.surveyCompletedStatus, v.floodAffected, v.waterLevelHit, v.basementArea, v.surveyCompletedDate,v.surveyIdWardNumber);
                Property property = new Property(v.surveyID, v.propertyID, v.propertyStartDate, v.latitude, v.longitude, v.surveyType, v.buildingStatus, v.doorStatus, v.buildingUnder, v.buildingType, v.buildingSubType, v.establishmentUsage
                        , v.buildingUsage, v.mainBuilding, v.electricity, v.consumerNumber, v.latrine, v.toiletWasteDisposal, v.airConditioner, v.landmark, v.landmarkName, v.landmarkCategory, v.landmarkSubCategory
                        , v.ownershipEducation, v.droneId, v.oldPropertyId, v.uId, v.newPropertyId, v.newPropertyRemarks, v.nearPropertyNumber, v.ownerName, v.ownerOccupation, v.ownerHouseNameNumber
                        , v.ownerStreetPlaceName, v.ownerState, v.ownerPostOffice, v.ownerPincode, v.ownerEmail, v.ownerLandLine, v.ownerMobile, v.teleCallNumber, v.nearRoad, v.roadType, v.roadWidth, v.tenantName, v.tenantHouseNameNumber
                        , v.tenantStreetPlaceName, v.tenantSurveyNumber, v.tenantState, v.tenantPostOffice, v.tenantPincode, v.tenantMobile, v.tenantLandLine, v.tenantEmail, v.tenantAmount, v.tenantNative
                        , v.tenantStatus, v.taxNumber, v.taxAmount, v.taxDate, v.taxYear, v.taxAnnualAmount, v.establishmentName, v.establishmentType, v.establishmentYear, v.inCharge, v.inChargeRole, v.employeeCount,
                        v.licenseNumber, v.gstStatus, v.establishmentEmail, v.establishmentLandline, v.establishmentMobile, v.noOfMembers, v.memberDetails, v.religion, v.religionCast, v.rationCard, v.rationCardNumber,
                        v.bathroom, v.kwaWater, v.waterConnectionType, v.waterSupplyDuration, v.lackDrinkingWater, v.gasConnection, v.rainWaterHarvest, v.solarPanel, v.waterSourceType, v.plasticWasteManagementType, v.liquidWasteManagementType, v.organicWasteManagementType, v.otherFacility, v.pets
                        , v.bankAccount, v.paultry, v.noOfPoultry, v.cattles, v.noOfCattles, v.memCount, v.swimmingPool, v.swimmingPoolArea, v.typeOfLand, v.buildingUnderScheme, v.plotArea, v.noOfVehicle, v.vehicleDetails, v.wellPerennial, v.wellPerennialMonth, v.thozhilurapp,
                        v.kudumbasree, v.healthInsurance, v.buildingName, v.colony_name, v.surveyNumber, v.yearsOfConstruction, v.totalYears, v.noOfRooms, v.noOfFloors, v.floorArea, v.structureType, v.carPorch, v.carPorchArea,
                        v.commonStair, v.commonStairArea, v.pathwayArea, v.anyExtension, v.anyStructuralChange, v.anyStructuralChangeYear, v.anyRoofChange, v.anyRoofChangeYear, v.otherBuilding, v.otherBuildingDetails, v.floorType, v.higherFloorSqft
                        , v.wallType, v.roofTotal, v.noOfRoofType, v.roofDetails, v.propertyImageOne, v.propertyImageTwo, v.propertyImageThree, v.informedBy, v.cooperativeSurvey, v.surveyorName, v.commonRemarks, v.completedStatus, v.propertyEndDate,
                        v.version, v.wellDetails, v.waterConnection, v.otherSource, v.syncCompleted, v.arRemarks, v.arOwnerAddress, v.syncRemarks, v.arNewPropId, v.arOldPropId, v.arYearOfConstruction, v.arArea, v.newpropidArRemarks, v.oldpropidArRemarks,
                        v.surveyorDetails,v.appVersionDetails, v.syncCompletedDate,v.arZone,v.arAC,v.arFloorArea,v.arBuildingUsage,v.arRoadType,v.arRoadName,v.arBuildingAge,v.arRoofDetails,v.arFloorDetails,v.arModification,v.arOccupierDetails,v.arTaxToatal,
                        v.extraRemarks,v.stair,v.stairArea,v.areaRemarks,v.isBuildingValidationOk,v.isEstablishmentValidationOk,v.isImageValidationOk,v.isLivehoodValidationOk,v.isMemberValidationOk,v.isMoreValidationOk,v.isOwnerValidationOk,v.isRoadValidationOk,
                        v.isTaxValidationOk,v.isTenantValidationOk);
                surveyArrayList.add(survey);
                propertyArrayList.add(property);
            }
            surveySaveCall();

        }

    }

    private void surveySaveCall() {
        for (int j = 0; j < surveyArrayList.size(); j++) {
            if (j == surveyArrayList.size() - 1)
                saveSurveyData(surveyArrayList.get(j), true);
            else
                saveSurveyData(surveyArrayList.get(j), false);
        }
    }

    private void propertySaveCall() {
        for (int k = 0; k < propertyArrayList.size(); k++) {
            if (k == propertyArrayList.size() - 1) {
                saveProperty(propertyArrayList.get(k), true);
            } else {
                saveProperty(propertyArrayList.get(k), false);
            }
        }

    }


    public void saveProperty(Property property, boolean isLastOne) {
        DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_DATE_FORMAT, Locale.ENGLISH);
        String startDate = df.format(Calendar.getInstance().getTime());
        getCompositeDisposable().add(getDataManager()
                .insertProperty(property)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if (isLastOne && aBoolean) {
                        updatePropCount();

                    }
                })
                .doOnError(throwable -> {
                    getBaseActivity().showToast(R.string.settings_fetching_failed);
                })
                .subscribe());
    }


    private void updatePropCount() {
        getAllSurveyList();
    }

    @Override
    protected void onAllSurveys(List<Survey> survey) {
        super.onAllSurveys(survey);
        for (int i = 0; i < survey.size(); i++) {
            Survey s = survey.get(i);
            int finalI = i;
            getCompositeDisposable().add(getDataManager()
                    .getPropertyCount(s.getSurveyID())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnNext(count -> {
                        if (count != null) {
                            getCompositeDisposable().add(getDataManager()
                                    .insertFloorPropertyCount(s.getFloorCount(), count, s.getSurveyID())
                                    .subscribeOn(getSchedulerProvider().io())
                                    .observeOn(getSchedulerProvider().ui())
                                    .doOnNext(aBoolean -> {
                                        if (finalI == survey.size() - 1) {
                                            getBaseActivity().showToast(getBaseActivity().getString(R.string.settings_fetch_success));
                                        }
                                    })
                                    .subscribe());
                        }
                    }).subscribe());

        }

    }

    void saveSurveyData(Survey survey, boolean isLastOne) {
        getCompositeDisposable().add(getDataManager()
                .insertQFieldID(survey)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if (aBoolean && isLastOne) {
                        propertySaveCall();
                    }
                })
                .doOnError(throwable -> {
                    getBaseActivity().showToast(R.string.settings_fetching_failed);
                })
                .subscribe());
    }

    private void backupFileToStorage(String content) {
        try {
            DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_BACKUP_NAME_FORMAT, Locale.ENGLISH);
            String startDate = df.format(Calendar.getInstance().getTime());
            String localBodyName = "";
            if (getLocalBodyCode() != null)
                localBodyName = getLocalBodyCode() + "-";
            File backUpFile = new File(FileUtils.getSurveyDataBackupDirectory(), localBodyName + startDate + ".txt");
            if (!backUpFile.exists()) {
                backUpFile.createNewFile();
            }
            FileWriter writer = new FileWriter(backUpFile);
            writer.append(CryptoUtils.encrypt(content));
            writer.flush();
            writer.close();
            getBaseActivity().showToast(R.string.settings_backup_successfully);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}