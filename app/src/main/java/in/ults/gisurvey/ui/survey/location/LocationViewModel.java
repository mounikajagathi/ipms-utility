package in.ults.gisurvey.ui.survey.location;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.InfoVideoNames;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

public class LocationViewModel extends BaseViewModel<LocationNavigator> {

    public final MutableLiveData<String> district = new MutableLiveData<>();
    public final MutableLiveData<String> localBody = new MutableLiveData<>();
    public final MutableLiveData<String> wardNumberValue = new MutableLiveData<>();
    public final MutableLiveData<String> wardNameValue = new MutableLiveData<>();
    public final MutableLiveData<String> streetName = new MutableLiveData<>();
    public final MutableLiveData<String> placeName = new MutableLiveData<>();
    public final MutableLiveData<String> villageName = new MutableLiveData<>();
    public final MutableLiveData<String> postOffice = new MutableLiveData<>();
    public final MutableLiveData<String> pinCode = new MutableLiveData<>();
    public final MutableLiveData<String> buildingZone = new MutableLiveData<>();
    public final MutableLiveData<String> surveyIdWardNumber = new MutableLiveData<>();
    public final ObservableBoolean isFloodAffected = new ObservableBoolean(false);
    public final MutableLiveData<String> waterlevelHit = new MutableLiveData<>();

    public final MutableLiveData<List<CommonItem>> wardNumberList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> villageNameList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> postOfficeList = new MutableLiveData<>();
    public final MutableLiveData<List<CommonItem>> buildingZoneList = new MutableLiveData<>();

    public LocationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    /**
     * to save location details in db  and navigate to survey grid page
     */
    void saveLocation(String district, String localBody, String wardNumber, String wardName, String streetName, String placeName, String villageName, String postOffice, String pinCode, String buildingZone, String isFloodAffected, String waterLevelHit) {
        getCompositeDisposable().add(getDataManager()
                .insertLocationDetails(district, localBody, wardNumber, wardName, streetName, placeName, villageName, postOffice, pinCode, buildingZone, isFloodAffected, waterLevelHit, getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
                    if (isPointStatusBuilding()) {
                        getNavigator().navigateToSurveyGridPage();
                    } else {
                        String propertyID = getDataManager().getCurrentSurveyID() + "P" + 1;
                        saveSelectedPropertyId(propertyID);
                    }
                })
                .subscribe());
    }
    public void setPostOffice(String postOffice) {
        this.postOffice.setValue(postOffice);
    }

    /**
     * get property id stored in db and set on back pressed
     */
    @Override
    protected void onPropertyIdSaved() {
        getNavigator().navigateToPropertyPage();
    }

    /**
     * validate each field and set error message if validation fails
     */
    protected boolean validateFields(String district, String localBody, String wardNumber, String streetName, String placeName, String villageName, String postOffice, String buildingZone, String floodAffected, String water_level_hit) {
        getNavigator().clearValidationErrors();
        if (TextUtils.isEmpty(district)) {
            getNavigator().validationError(LocationFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_district));
            return false;
        } else if (TextUtils.isEmpty(localBody)) {
            getNavigator().validationError(LocationFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_local_body));
            return false;
        } else if (TextUtils.isEmpty(wardNumber)) {
            getNavigator().validationError(LocationFragment.WARD_NUMBER_ERROR, getBaseActivity().getString(R.string.error_ward_number));
            return false;
        } else if (isPointStatusBuilding() && TextUtils.isEmpty(streetName)) {
            getNavigator().validationError(LocationFragment.STREET_ERROR, getBaseActivity().getString(R.string.error_street_name));
            return false;
        } else if (TextUtils.isEmpty(placeName)) {
            getNavigator().validationError(LocationFragment.PLACE_ERROR, getBaseActivity().getString(R.string.error_place_name));
            return false;
        } else if (isPointStatusBuilding() && TextUtils.isEmpty(villageName)) {
            getNavigator().validationError(LocationFragment.VILLAGE_NAME_ERROR, getBaseActivity().getString(R.string.error_village_name));
            return false;
        } else if (isPointStatusBuilding() && TextUtils.isEmpty(postOffice)) {
            getNavigator().validationError(LocationFragment.POST_OFFICE_ERROR, getBaseActivity().getString(R.string.error_post_office));
            return false;
        } else if (isPointStatusBuilding() && TextUtils.isEmpty(buildingZone)) {
            getNavigator().validationError(LocationFragment.BUILDING_ZONE_ERROR, getBaseActivity().getString(R.string.error_building_zone));
            return false;
        } else if (TextUtils.isEmpty(floodAffected)) {
            getNavigator().validationError(LocationFragment.COMMON_ERROR, getBaseActivity().getString(R.string.error_flood_status));
            return false;
        } else if (isFloodAffected.get() && TextUtils.isEmpty(water_level_hit)) {
            getNavigator().validationError(LocationFragment.WATER_LEVEL_HIT_ERROR, getBaseActivity().getString(R.string.error_water_level_hit));
            return false;
        }
        return true;
    }

    /**
     * data updating via data binding
     */
    void setPinCode(String pin) {
        pinCode.setValue(pin);
    }

    /**
     * data updating via data binding
     */
    public void setDistrict(String district) {
        this.district.setValue(district);
    }

    /**
     * data updating via data binding
     */
    public void setLocalBody(String localBody) {
        this.localBody.setValue(localBody);
    }

    /**
     * data updating via data binding
     */
    public void setWardName(String wardName) {
        this.wardNameValue.setValue(wardName);
    }

    /**
     * data updating via data binding
     */
    void setWardNumberList(List<CommonItem> wardNumberList) {
        this.wardNumberList.setValue(wardNumberList);
    }

    /**
     * data updating via data binding
     */
    void setVillageNameList(List<CommonItem> villageNameList) {
        this.villageNameList.setValue(villageNameList);
    }

    /**
     * data updating via data binding
     */
    void setPostOfficeList(List<CommonItem> postOfficeList) {
        this.postOfficeList.setValue(postOfficeList);
    }

    /**
     * data updating via data binding
     */
    void setBuildingZoneList(List<CommonItem> buildingZoneList) {
        this.buildingZoneList.setValue(buildingZoneList);
    }


    /**
     * save location details
     */
    public void onNextClick() {
        getNavigator().saveLocationDetails();
    }

    /**
     * @param survey redirected from base viewmodel
     *               to fetch survey details from db when back pressed and set to fields
     */
    @Override
    protected void onSurveyFetchedFromDb(Survey survey) {
        if (survey.getWardNumber().length() != 0)
        {
            wardNumberValue.setValue(survey.getWardNumber());
            wardNameValue.setValue(survey.getWardName());
        }
        else
        {
            wardNumberValue.setValue(getWardNumber());
            wardNameValue.setValue(getWardName());
        }
        surveyIdWardNumber.setValue(survey.getSurveyIdWardNumber());
        streetName.setValue(survey.getStreetName());
        placeName.setValue(survey.getPlaceName());
        villageName.setValue(survey.getVillageName());
        postOffice.setValue(survey.getPostOffice());
        pinCode.setValue(survey.getPinCode());
        buildingZone.setValue(survey.getBuildingZone());
        isFloodAffected.set(survey.floodAffected.equalsIgnoreCase(getBaseActivity().getString(R.string.cmn_yes)));
        if (isFloodAffected.get()) {
            waterlevelHit.setValue(survey.waterLevelHit);
        }
        getNavigator().setCachedData(survey);
    }

    public void setIsFloodAffected(boolean status) {
        isFloodAffected.set(status);

        if (!status) {
            waterlevelHit.setValue("");
        }
    }

    public void onNRClick(View v) {
        switch (v.getId()) {
            case R.id.nrStreetName:
                streetName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrPlaceName:
                placeName.setValue(AppConstants.NR_UPPERCASE);
                break;
            case R.id.nrWaterHit:
                waterlevelHit.setValue(AppConstants.NR_UPPERCASE);
                break;
        }
    }

    public void onInfoClick(View v) {
        switch (v.getId()) {
            case R.id.btnWaterHitLevelInfo:
                getNavigator().showInfoDialogWithVideo(getBaseActivity().getString(R.string.info_water_level_hit), InfoVideoNames.LOCATION_DETAILS_WATER_LEVEL_HIT_INFO_VIDEO);
                break;
        }
    }
}