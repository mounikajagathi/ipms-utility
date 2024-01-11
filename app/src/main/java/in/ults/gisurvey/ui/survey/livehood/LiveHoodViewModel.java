package in.ults.gisurvey.ui.survey.livehood;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.AbstractPreferences;

import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_COMPLETED;
import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_EXTENSION;
import static in.ults.gisurvey.utils.AppConstants.BUILDING_STATUS_REPAIR;
import static in.ults.gisurvey.utils.AppConstants.DOOR_STATUS_NC;
import static in.ults.gisurvey.utils.AppConstants.NO;

public class LiveHoodViewModel extends BaseViewModel<LiveHoodNavigator> {


    public final MutableLiveData<String> rationCard = new MutableLiveData<>();
    public final MutableLiveData<String> rationCardNumber = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> waterSourceType = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> wellType = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> otherWaterSource = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> wellPerennialMonthList = new MutableLiveData<>();
    public final MutableLiveData<String> cast = new MutableLiveData<>();
    public final MutableLiveData<String> religion = new MutableLiveData<>();//
    public final MutableLiveData<String> noOfCattles = new MutableLiveData<>();
    public final MutableLiveData<String> noOfPoultry = new MutableLiveData<>();
    public final MutableLiveData<String> waterConnectionType = new MutableLiveData<>();
    public final MutableLiveData<String> waterSupplyDuration = new MutableLiveData<>();
    public final MutableLiveData<String> latrine = new MutableLiveData<>();
    public final MutableLiveData<String> bathroom = new MutableLiveData<>();
    public final MutableLiveData<String> ac = new MutableLiveData<>();
    public final MutableLiveData<String> electricity = new MutableLiveData<>();
    public final MutableLiveData<String> memCount = new MutableLiveData<>();
    public final MutableLiveData<String> swimmingPoolArea = new MutableLiveData<>();


    public final MutableLiveData<List<CommonItem>> castList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> religionList = new MutableLiveData<>();//
    public final MutableLiveData<List<CommonItem>> rationCardList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> waterConnectionTypeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> waterSupplyDurationList = new MutableLiveData<>();

    public final ObservableBoolean noRationCard = new ObservableBoolean(false);
    public final ObservableBoolean isWaterConnectionAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isCattlesAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isPoultryAvailable = new ObservableBoolean(false);
    public final ObservableBoolean isSwimmingPoolAvailable = new ObservableBoolean(false);

    public final MutableLiveData<ArrayList<CommonItem>> plasticWasteManagementList = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> liquidWasteManagementList = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> organicWasteManagementList = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> otherFacilities = new MutableLiveData<>();
    public final MutableLiveData<ArrayList<CommonItem>> pets = new MutableLiveData<>();
    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingSubtype = "";
    private String buildingType = "";
    public final ObservableBoolean establishmentUsageHouseFlatVilla = new ObservableBoolean(false);
    public final ObservableBoolean buildingTypeResidential = new ObservableBoolean(false);
    public final ObservableBoolean buildingSubTypeResidential = new ObservableBoolean(false);
    public final ObservableBoolean buildingStatusCompletedRepairExtension = new ObservableBoolean(false);
    public final ObservableBoolean buildingStatusOnGoingWithoutRoof = new ObservableBoolean(false);
    public final ObservableBoolean isWellSelected = new ObservableBoolean(false);
    public final ObservableBoolean isOtherWaterSourceSelected = new ObservableBoolean(false);
    public final ObservableBoolean isShowDryMonth = new ObservableBoolean(false);
    public final MutableLiveData<String> isWellSeasonal =  new MutableLiveData<>();
    public final ObservableBoolean doorStatusNC = new ObservableBoolean(false);
    public final ObservableBoolean isTeleCall = new ObservableBoolean(false);

    public LiveHoodViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void loadData() {
        castList.setValue(IPMSApp.getAppInstance().getLocMainItem().getCasteTemp());
        religionList.setValue(IPMSApp.getAppInstance().getLocMainItem().getReligionTemp());//
        rationCardList.setValue(IPMSApp.getAppInstance().getLocMainItem().getRationCards());
        waterSourceType.setValue(IPMSApp.getAppInstance().getLocMainItem().getWaterSourceType());
        wellPerennialMonthList.setValue(IPMSApp.getAppInstance().getLocMainItem().getFullMonth());
        wellType.setValue(IPMSApp.getAppInstance().getLocMainItem().getWellDetails());
        otherWaterSource.setValue(IPMSApp.getAppInstance().getLocMainItem().getOtherWaterSource());
        waterConnectionTypeList.setValue(IPMSApp.getAppInstance().getLocMainItem().getWaterConnectionType());
        waterSupplyDurationList.setValue(IPMSApp.getAppInstance().getLocMainItem().getWaterSupplyDuration());
        otherFacilities.setValue(IPMSApp.getAppInstance().getLocMainItem().getOtherFacility());
        pets.setValue(IPMSApp.getAppInstance().getLocMainItem().getPetType());
        plasticWasteManagementList.setValue(IPMSApp.getAppInstance().getLocMainItem().getPlasticWasteManagementType());
        liquidWasteManagementList.setValue(IPMSApp.getAppInstance().getLocMainItem().getLiquidWasteManagementType());
        organicWasteManagementList.setValue(IPMSApp.getAppInstance().getLocMainItem().getOrganicWasteManagementType());


    }

    public void setIsWellSeasonal(String isWellSeasonal) {
        this.isWellSeasonal.setValue(isWellSeasonal);
        isShowDryMonth.set(!isWellSeasonal.equalsIgnoreCase(getBaseActivity().getString(R.string.more_well_perennial)));
    }



    /**
     * data updating via data binding
     */
    void setNoRationCard(boolean noRationCard) {
        if (noRationCard) {
            rationCardNumber.setValue("");
        }
        this.noRationCard.set(noRationCard);
    }

    /**
     * to fetch property details from db when back pressed and set to its fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        buildingStatus = property.buildingStatus;
        doorStatus = property.doorStatus;
        doorStatusNC.set(property.doorStatus.equalsIgnoreCase(DOOR_STATUS_NC));
        buildingSubtype=property.buildingSubType;
        buildingType=property.buildingType;
        establishmentUsageHouseFlatVilla.set(property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_HOUSE) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS) ||
                property.establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA));
        buildingSubTypeResidential.set(property.buildingSubType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL));
        buildingTypeResidential.set(property.buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL));
        buildingStatusOnGoingWithoutRoof.set(property.buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF));
        buildingStatusCompletedRepairExtension.set(property.buildingStatus.equalsIgnoreCase(BUILDING_STATUS_COMPLETED) ||
                property.buildingStatus.equalsIgnoreCase(BUILDING_STATUS_EXTENSION) ||
                property.buildingStatus.equalsIgnoreCase(BUILDING_STATUS_REPAIR));
        if (establishmentUsageHouseFlatVilla.get()) {

            setNoRationCard(property.rationCard.equalsIgnoreCase(AppConstants.LIVEHOOD_NO_RATION_CARD) || property.rationCard.equalsIgnoreCase(AppConstants.NR_UPPERCASE));
            cast.setValue(property.religionCast);
            religion.setValue(property.religion);
            if (!noRationCard.get()) {
                rationCardNumber.setValue(property.rationCardNumber);
            }
            rationCard.setValue(property.rationCard);
            setCattleAvailable(property.cattles.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isCattlesAvailable.get()) {
                noOfCattles.setValue(property.noOfCattles);
            }
            setPoultryAvailable(property.paultry.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isPoultryAvailable.get()) {
                noOfPoultry.setValue(property.noOfPoultry);
            }
            setSwimmingPoolAvailable(property.swimmingPool.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
            if (isSwimmingPoolAvailable.get()) {
                swimmingPoolArea.setValue(property.swimmingPoolArea);
            }

        }
        setWellWaterAvailable(!property.wellDetails.contains(NO));
        setOtherWaterSourceAvailable(!property.otherSource.contains(NO));
        setIsWellSeasonal(property.wellPerennial);
        isTeleCall.set(property.surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));

        if (property.waterConnection.size() != 0)
            setWaterConnectionAvailable(!property.waterConnection.contains(NO));

        if (isWaterConnectionAvailable.get()) {
            waterSupplyDuration.setValue(property.waterSupplyDuration);
            waterConnectionType.setValue(property.waterConnectionType);
        }
        latrine.setValue(property.latrine);
        bathroom.setValue(property.bathroom);
        electricity.setValue(property.electricity);
        ac.setValue(property.airConditioner);
        memCount.setValue(property.memCount);
        getNavigator().setCachedData(property);
        if(property.isLivehoodValidationOk)
            getNavigator().disablePartialSave();

    }


    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String religion, String religionCast, String rationCard, String rationCardNumber, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry, ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherResource, String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea) {
        getNavigator().clearValidationErrors();
        if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && TextUtils.isEmpty(religion)) {
            getNavigator().validationError(LiveHoodFragment.RELIGION_ERROR, getBaseActivity().getString(R.string.error_religion));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && TextUtils.isEmpty(religionCast)) {
            getNavigator().validationError(LiveHoodFragment.RELIGION_CAST_ERROR, getBaseActivity().getString(R.string.error_cast));
            return false;
        } else if (establishmentUsageHouseFlatVilla.get() &&!buildingStatusOnGoingWithoutRoof.get() &&!doorStatusNC.get()&& TextUtils.isEmpty(rationCard)) {
            getNavigator().validationError(LiveHoodFragment.RATION_CARD_ERROR, getBaseActivity().getString(R.string.error_ration_card));
            return false;
        } else if (establishmentUsageHouseFlatVilla.get()&&!buildingStatusOnGoingWithoutRoof.get()&&!doorStatusNC.get() && !noRationCard.get() && TextUtils.isEmpty(rationCardNumber)) {
            getNavigator().validationError(LiveHoodFragment.RATION_CARD_NUMBER_ERROR, getBaseActivity().getString(R.string.error_ration_card_number));
            return false;
        } else if (establishmentUsageHouseFlatVilla.get()&&!doorStatusNC.get()&&!buildingStatusOnGoingWithoutRoof.get() && !noRationCard.get() && !rationCardNumber.equalsIgnoreCase(AppConstants.NR_UPPERCASE) && rationCardNumber.length() != 10) {
            getNavigator().validationError(LiveHoodFragment.RATION_CARD_NUMBER_ERROR, getBaseActivity().getString(R.string.error_valid_ration_card_number));
            return false;
        } else if (!isTeleCall.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get() && wellDetails.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_well_details));
            return false;
        } else if (!isTeleCall.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get()&& isWellSelected.get() && TextUtils.isEmpty(wellPerennial)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_well_perennial));
            return false;
        } else if (!isTeleCall.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get() && (isWellSeasonal.getValue().equalsIgnoreCase(getBaseActivity().getString(R.string.more_well_seasonal))||isWellSeasonal.getValue().equalsIgnoreCase(getBaseActivity().getString(R.string.more_well_polluted))) && wellPerennialMonth.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_well_perennial_month));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&&!doorStatusNC.get()&&waterConnection.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_water_conection));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& isWaterConnectionAvailable.get() && TextUtils.isEmpty(waterSupplyDuration)) {
            getNavigator().validationError(LiveHoodFragment.WATER_SUPPLY_DURATION_ERROR, getBaseActivity().getString(R.string.error_water_supply_duration));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& establishmentUsageHouseFlatVilla.get() && otherResource.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_other_water_resource));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& establishmentUsageHouseFlatVilla.get() && TextUtils.isEmpty(lackDrinkingWater)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_lack_drinking));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get()&& !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(gasConnection)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_gas_connection));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()  && TextUtils.isEmpty(rainWaterHarvest)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_rain_water_harvest));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&&TextUtils.isEmpty(solarPanel)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_solar));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& plasticWasteManagementType.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_plastic_waste_management));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& liquidWasteManagementType.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_liquid_waste_management));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& organicWasteManagementType.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_organic_waste_management));
            return false;
        } else if (!isTeleCall.get() && !buildingStatusOnGoingWithoutRoof.get()&& !doorStatusNC.get()&& otherFacility.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_other_facility));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get()&& pet.size() == 0) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_pet_dog));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get()&& !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(bankAccount)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_bank_account));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get()&&!doorStatusNC.get()&&!buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(paultry)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_poultry));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get() && !buildingStatusOnGoingWithoutRoof.get()&& isPoultryAvailable.get() && TextUtils.isEmpty(noOfPoultry) || noOfPoultry.equalsIgnoreCase("0") || noOfPoultry.startsWith("0")) {
            getNavigator().validationError(LiveHoodFragment.NO_POULTRY_ERROR, getBaseActivity().getString(R.string.error_no_of_poultry));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get()&& !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(cattles)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_cattle));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get()&& isCattlesAvailable.get() && TextUtils.isEmpty(noOfCattles) || noOfCattles.equalsIgnoreCase("0") || noOfCattles.startsWith("0")) {
            getNavigator().validationError(LiveHoodFragment.NO_CATTLE_ERROR, getBaseActivity().getString(R.string.error_no_of_cattle));
            return false;
        } else if (establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get()&& isTeleCall.get() && TextUtils.isEmpty(memCount)) {
            getNavigator().validationError(LiveHoodFragment.MEM_COUNT_ERROR, getBaseActivity().getString(R.string.error_no_of_members));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get()&& !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get() && TextUtils.isEmpty(swimmingPool)) {
            getNavigator().validationError(LiveHoodFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_swimming_pool));
            return false;
        } else if (!isTeleCall.get() && establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get()&& !buildingStatusOnGoingWithoutRoof.get()&& isSwimmingPoolAvailable.get() && TextUtils.isEmpty(swimmingPoolArea)) {
            getNavigator().validationError(LiveHoodFragment.SWIMMING_POOL_AREA_ERROR, getBaseActivity().getString(R.string.error_swimming_pool_area));
            return false;
        } else if (establishmentUsageHouseFlatVilla.get() && !doorStatusNC.get()&& buildingStatusCompletedRepairExtension.get() && !isWellSelected.get() && !isWaterConnectionAvailable.get() && !isOtherWaterSourceSelected.get()) {
            getNavigator().showInfoDialog(getBaseActivity().getString(R.string.error_no_water_source));
            return false;
        }
        return true;
    }


    /**
     * to save live hood details in db  and navigate next page
     */
    void saveLiveHoodDetails(String religion, String religionCast, String rationCard, String rationCardNumber, String waterSupplyDuration, String gasConnection, String rainWaterHarvest, String lackDrinkingWater, String solarPanel, ArrayList<String> plasticWasteManagementType, ArrayList<String> liquidWasteManagementType, ArrayList<String> organicWasteManagementType, ArrayList<String> otherFacility, String bankAccount, ArrayList<String> pet, String cattles, String noOfCattles, String paultry, String noOfPoultry, ArrayList<String> wellDetails, ArrayList<String> waterConnection, ArrayList<String> otherSource, String wellPerennial, ArrayList<String> wellPerennialMonth, String memCount, String swimmingPool, String swimmingPoolArea,boolean isValidationOk) {
        String kwaWater = getkwaValue(waterConnection);
        String waterConnectionType = getWaterConnectionTypeInString(waterConnection);
        ArrayList<String> waterSourceType = getwaterSourceType(wellDetails, waterConnection, otherSource);
        getCompositeDisposable().add(getDataManager()
                .insertLiveHoodDetails(religion, religionCast, rationCard, rationCardNumber, kwaWater, waterConnectionType, waterSupplyDuration, gasConnection, rainWaterHarvest, lackDrinkingWater, solarPanel, waterSourceType, plasticWasteManagementType, liquidWasteManagementType, organicWasteManagementType, otherFacility, bankAccount, pet, cattles, noOfCattles, paultry, noOfPoultry, wellDetails, waterConnection, otherSource, wellPerennial, wellPerennialMonth, memCount, swimmingPool, swimmingPoolArea, isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    if (canIGoToMoreDetails(buildingStatus, doorStatus,buildingType,buildingSubtype)) {
//                        getNavigator().navigateToMorePage();
//                    } else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
//                        getNavigator().navigateToBuildingPage();
//                    } else {
//                        getNavigator().navigateToImageDetails();
//                    }
                    getNavigator().navigateToScreenSelection();
                })
                .subscribe());
    }

    private String getkwaValue(ArrayList<String> waterConnection) {
        String kwa = "";
        if(waterConnection.size()!=0){
            if (isWaterConnectionAvailable.get()) {
                kwa = getBaseActivity().getString(R.string.cmn_yes);
            } else {
                kwa = getBaseActivity().getString(R.string.cmn_no);
            }
        }
        return kwa;

    }

    private String getWaterConnectionTypeInString(ArrayList<String> waterConnection) {
        String conectionType = "";

            for (int i = 0; i < waterConnection.size(); i++) {
                if (i == 0) {
                    conectionType = waterConnection.get(i);
                } else {
                    conectionType = (conectionType) + "," + waterConnection.get(i);
                }

            }


        return conectionType;

    }

    private ArrayList<String> getwaterSourceType(ArrayList<String> wellType, ArrayList<String> waterConnection, ArrayList<String> otherWaterSource) {
        ArrayList<String> waterSource = new ArrayList<String>();

           for (String s : wellType) {
               if (!s.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_no))) {
                   waterSource.add(s);
               }
           }
           for (String s : waterConnection) {
               if (!s.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_no))) {
                   waterSource.add(s);
               }
           }
           for (String s : otherWaterSource) {
               if (!s.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_no))) {
                   waterSource.add(s);
               }
           }
           if (waterSource.size() == 0)
               waterSource.add(getBaseActivity().getString(R.string.cmn_no));

        return waterSource;
    }

    /**
     * to save live hood details
     */
    public void onNextClick() {
        getNavigator().saveLiveHoodDetails(false);
    }
    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveLiveHoodDetails(true);
    }
    public void setCattleAvailable(boolean isCattle) {
        isCattlesAvailable.set(isCattle);
        if (!isCattle) {
            noOfCattles.setValue("");
        }
    }

    public void setWaterConnectionAvailable(boolean isWaterConnection) {
        if (isWaterConnection) {
            //PREVOIUS CASE NO WATER RESOURCE
            //NOW  WATER CONECTION IS SETTING
            if (!isWellSelected.get() && !isOtherWaterSourceSelected.get()) {
                getNavigator().setLackOfDrinkingWater(false);
            }
        } else {
            //NOW NO WATER RESOURCE AVALABLE SO SET LACK OF DRINKING WATER
            if (!isWellSelected.get() && !isOtherWaterSourceSelected.get()) {
                getNavigator().setLackOfDrinkingWater(true);
            }
        }
        isWaterConnectionAvailable.set(isWaterConnection);
        if (!isWaterConnection) {
            waterConnectionType.setValue("");
            waterSupplyDuration.setValue("");
        }
    }

    public void setWellWaterAvailable(boolean isWellAvailable) {
        if (isWellAvailable) {
            //PREVOIUS CASE NO WATER RESOURCE
            //NOW  WATER CONECTION IS SETTING
            if (!isWaterConnectionAvailable.get() && !isOtherWaterSourceSelected.get()) {
                getNavigator().setLackOfDrinkingWater(false);
            }
        } else {
            //NOW NO WATER RESOURCE AVALABLE SO SET LACK OF DRINKING WATER
            if (!isWaterConnectionAvailable.get() && !isOtherWaterSourceSelected.get()) {
                getNavigator().setLackOfDrinkingWater(true);
            }
        }
        isWellSelected.set(isWellAvailable);
        if (!isWellAvailable) {

        }

    }

    public void setOtherWaterSourceAvailable(boolean isOtherWaterSourceAvailable) {
        if (isOtherWaterSourceAvailable) {
            //PREVOIUS CASE NO WATER RESOURCE
            //NOW  WATER CONECTION IS SETTING
            if (!isWaterConnectionAvailable.get() && !isWellSelected.get()) {
                getNavigator().setLackOfDrinkingWater(false);
            }
        } else {
            //NOW NO WATER RESOURCE AVALABLE SO SET LACK OF DRINKING WATER
            if (!isWaterConnectionAvailable.get() && !isWellSelected.get()) {
                getNavigator().setLackOfDrinkingWater(true);
            }
        }
        isOtherWaterSourceSelected.set(isOtherWaterSourceAvailable);
    }

    public void setPoultryAvailable(boolean isPoultry) {
        isPoultryAvailable.set(isPoultry);
        if (!isPoultry) {
            noOfPoultry.setValue("");
        }
    }

    public void setSwimmingPoolAvailable(boolean isSwimmingPool) {
        isSwimmingPoolAvailable.set(isSwimmingPool);
        if (!isSwimmingPool) {
            swimmingPoolArea.setValue("");
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnReligionInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_religion),InfoVideoNames.LIVELIHOOD_RELIGION_INFO_VIDEO);
                break;
            case R.id.btnCastInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_cast),InfoVideoNames.LIVELIHOOD_CAST_INFO_VIDEO);
                break;
            case R.id.btnRationCardInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_ration_card),InfoVideoNames.LIVELIHOOD_RATION_CARD_INFO_VIDEO);
                break;
            case R.id.btnRationCardNoInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_ration_card_no),InfoVideoNames.LIVELIHOOD_RATION_CARD_NUMBER_INFO_VIDEO);
                break;
            case R.id.btnwaterSupplyDurationInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_water_supply_duration),InfoVideoNames.LIVELIHOOD_WATER_SUPPLY_DURATION_INFO_VIDEO);
                break;
            case R.id.tvLackDrinkingWater:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_lack_drinking_water),InfoVideoNames.LIVELIHOOD_LACK_OF_DRINKING_WATER_INFO_VIDEO);
                break;
            case R.id.tvGasConnection:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_gas_connection),InfoVideoNames.LIVELIHOOD_GAS_CONNECTION_INFO_VIDEO);
                break;
            case R.id.tvRainWaterHarvest:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_rain_water_harvest),InfoVideoNames.LIVELIHOOD_RAIN_WATER_HARVESTING_INFO_VIDEO);
                break;
            case R.id.tvSolarPanel:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_solar_panel),InfoVideoNames.LIVELIHOOD_SOLAR_PANEL_INFO_VIDEO);
                break;
            case R.id.txtOtherFacilities:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_other_facility),InfoVideoNames.LIVELIHOOD_FOLLOWING_FACILITY_AVAILABLE_INFO_VIDEO);
                break;
            case R.id.txtPets:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_pet),InfoVideoNames.LIVELIHOOD_PET_INFO_VIDEO);
                break;
            case R.id.tvPaultry:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_poultry),InfoVideoNames.LIVELIHOOD_POULTRY_INFO_VIDEO);
                break;
            case R.id.btnNumPoultryInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_num_poultry),InfoVideoNames.LIVELIHOOD_NUMBER_OF_POULTRY_INFO_VIDEO);
                break;
            case R.id.tvBankAccount:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_banck_account),InfoVideoNames.LIVELIHOOD_BANK_ACCOUNT_INFO_VIDEO);
                break;
            case R.id.tvCattles:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_cattles),InfoVideoNames.LIVELIHOOD_CATTLE_INFO_VIDEO);
                break;
            case R.id.btnNumCattlesInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_num_cattles),InfoVideoNames.LIVELIHOOD_NUMBER_OF_CATTLE_INFO_VIDEO);
                break;
            case R.id.txtWellWaterSourceType:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_details),InfoVideoNames.LIVELIHOOD_WELL_DETAILS_INFO_VIDEO);
                break;
            case R.id.tvWellPerennialDetails:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_water_available),InfoVideoNames.LIVELIHOOD_WELL_WATER_AVAILABILITY_INFO_VIDEO);
                break;
            case R.id.tvWellPerennialMonth:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_well_dry_month),InfoVideoNames.LIVELIHOOD_DRY_MONTH_INFO_VIDEO);
                break;
            case R.id.txtWaterConnection:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_water_connection_details),InfoVideoNames.LIVELIHOOD_WATER_CONNECTION_INFO_VIDEO);
                break;
            case R.id.txtOtherWaterSource:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_other_water_source),InfoVideoNames.LIVELIHOOD_OTHER_WATER_SOURCE_INFO_VIDEO);
                break;
            case R.id.btnMemCountInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_mem_count),InfoVideoNames.LIVELIHOOD_MEMBER_COUNT_INFO_VIDEO);
                break;
            case R.id.txtPlasticWasteManagement:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_plastic_waste_management),InfoVideoNames.LIVELIHOOD_PLASTIC_WASTE_MANAGEMENT_INFO_VIDEO);
                break;
            case R.id.txtLiquidWasteManagement:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_liquid_waste_management),InfoVideoNames.LIVELIHOOD_LIQUID_WASTE_MANAGEMENT_INFO_VIDEO);
                break;
            case R.id.txtOrganicWasteManagement:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_organic_waste_management),InfoVideoNames.LIVELIHOOD_ORGANIC_WASTE_MANAGEMENT_INFO_VIDEO);
                break;
            case R.id.tvSwimmingPool:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_swimming_pool),InfoVideoNames.LIVELIHOOD_SWIMMING_POOL_INFO_VIDEO);
                break;
            case R.id.btnAreaSwimmingPoolInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_swimming_pool_area),InfoVideoNames.LIVELIHOOD_SWIMMING_POOL_AREA_INFO_VIDEO);
                break;
        }
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrRationCardNumber:
                rationCardNumber.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }

}