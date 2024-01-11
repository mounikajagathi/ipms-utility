package in.ults.gisurvey.ui.base;


import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.error.ANError;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.api.ApiError;
import in.ults.gisurvey.data.model.api.District;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.api.Surveyor;
import in.ults.gisurvey.data.model.api.SurvryPointsResponse;
import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.db.SurveyWithProperty;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.data.model.items.GridItem;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.CommonUtils;
import in.ults.gisurvey.utils.rx.SchedulerProvider;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

import static in.ults.gisurvey.utils.AppConstants.JOB_TYPE_CENTRAL_GOVERNMENT;
import static in.ults.gisurvey.utils.AppConstants.JOB_TYPE_STATE_GOVERNMENT;
import static in.ults.gisurvey.utils.AppConstants.SURVEY_OPEN_MODE_VIEW;


public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    private WeakReference<BaseActivity> baseActivity;

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    protected N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(BaseActivity baseActivity, N navigator) {
        this.baseActivity = new WeakReference<>(baseActivity);
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }


    public BaseActivity getBaseActivity() {
        return baseActivity.get();
    }


    /**
     * to fetch details of particular survey from db using survey id
     */
    public void getCurrentSurvey() {
        getCompositeDisposable().add(getDataManager()
                .getSurveyById(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(survey -> {
                    if (survey != null) {
                        onSurveyFetchedFromDb(survey);
                    }
                }).subscribe());
    }

    /**
     * to fetch details of particular property from db using property id
     */
    public void getCurrentSurveyProperty() {
        getCompositeDisposable().add(getDataManager()
                .getPropertyById(getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(property -> {
                    if (property != null) {
                        onPropertyFetchedFromDb(property);
                    }
                }).subscribe());
    }

    /**
     * @return true if point status is building
     */
    public boolean isPointStatusBuilding() {
        return getDataManager().getCurrentPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_BUILDING);
    }

    /**
     * @return true if point status is landmark
     */
    public boolean isPointStatusLandmark() {
        return getDataManager().getCurrentPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_LANDMARK);
    }

    /**
     * @return true if point status is unwanted
     */
    public boolean isPointStatusUnwanted() {
        return getDataManager().getCurrentPointStatus().equalsIgnoreCase(AppConstants.POINT_STATUS_UNWANTED);
    }

    /**
     * @return true if complete survey list type is sync
     */
    public boolean isCompleteListTypeSyncList() {
        return getDataManager().getCompletedListType().equalsIgnoreCase(AppConstants.COMPLETED_LIST_TYPE_SYNC);
    }

    /**
     * to create a property and store its id in shared preference
     *
     * @param propertyId
     */
    public void saveSelectedPropertyId(String propertyId) {
        DateFormat df = new SimpleDateFormat(AppConstants.SURVEY_DATE_FORMAT, Locale.ENGLISH);
        String startDate = df.format(Calendar.getInstance().getTime());
        getCompositeDisposable().add(getDataManager()
                .insertProperty(getDataManager().getCurrentSurveyID(), propertyId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> getDataManager().setCurrentPropertyId(propertyId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .flatMap((Function<Boolean, ObservableSource<Boolean>>) aBoolean -> getDataManager().insertPropertyStartDate(startDate, getDataManager().getCurrentSurveyID(), propertyId))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> onPropertyIdSaved())
                .subscribe());
    }

    /**
     * @return surveyor name
     */
    public String getSurveyorName() {
        String surveyorName = getDataManager().getSurveyorName() != null ? getDataManager().getSurveyorName() : getBaseActivity().getString(R.string.settings_default);
        return surveyorName;
    }

    /**
     * @return selected wards for getting survey points
     */
    public String getSelectedWardsInString() {
        String wards = "";
        if (getSelectedWardsForSurveyPoints() == null || getSelectedWardsForSurveyPoints().size() == 0) {
            if (getSurveyPoints() == null) {
                wards = getBaseActivity().getString(R.string.settings_default);
            }
            return wards;
        }
        for (int i = 0; i < getSelectedWardsForSurveyPoints().size(); i++) {
            wards = wards + getSelectedWardsForSurveyPoints().get(i);
            if (i != getSelectedWardsForSurveyPoints().size() - 1) {
                wards = wards + ", ";
            }
        }
        return wards;
    }

    /**
     * @return selected surveyor details
     */
    public ArrayList<Surveyor> getSurveyorDetails() {
        ArrayList<Surveyor> surveyorArrayList = new ArrayList<Surveyor>();
        if (getDataManager().getSelectedSurveyorDetails() != null) {
            surveyorArrayList = getDataManager().getSelectedSurveyorDetails();
        }
        return surveyorArrayList;
    }


    /**
     * @return MobileCode
     */
    public String getMobileCode() {
        String mobileCode = getDataManager().getMobileCOde() != null ? getDataManager().getMobileCOde() : getBaseActivity().getString(R.string.settings_default);
        return mobileCode;
    }

    /**
     * @return selected property id
     */
    public String getSelectedPropertyId() {
        String propId = getBaseActivity().getString(R.string.cmn_no_value);
        if (getDataManager().getSelectedPropId() != null && !getDataManager().getSelectedPropId().equalsIgnoreCase("null")) {
            propId = getDataManager().getSelectedPropId();
        }

        return propId;
    }

    /**
     * @return selected drone id
     */
    public String getSelectedDroneId() {
        String droneId = getBaseActivity().getString(R.string.cmn_no_value);
        if (getDataManager().getSelectedDroneId() != null && !getDataManager().getSelectedDroneId().equalsIgnoreCase("null")) {
            droneId = getDataManager().getSelectedDroneId();
        }

        return droneId;
    }

    public String getBaseUrlInFormat(String address, String port) {
        String baseUrl = "http://" + address + ":" + port;
        return baseUrl;
    }

    /**
     * @return selected new property id
     */
    public String getSelectedNewPropertyId() {
        String newProp = getBaseActivity().getString(R.string.cmn_no_value);
        if (getDataManager().getSelectedNewPropId() != null && !getDataManager().getSelectedNewPropId().equalsIgnoreCase("null")) {
            newProp = getDataManager().getSelectedNewPropId();
        }

        return newProp;
    }

    /**
     * @return selected new survey id
     */
    public String getSelectedServerSurveyId() {
        String id = getBaseActivity().getString(R.string.cmn_no_value);
        if (getDataManager().getSelectedServerSurveyId() != null && !getDataManager().getSelectedServerSurveyId().equalsIgnoreCase("null")) {
            id = getDataManager().getSelectedServerSurveyId();
        }

        return id;
    }

    /**
     * override to navigate to next page
     */
    protected void onPropertyIdSaved() {

    }

    /**
     * override to fetch survey details from db
     *
     * @param survey
     */
    protected void onSurveyFetchedFromDb(Survey survey) {

    }

    /**
     * override to get property details from db
     *
     * @param property
     */
    protected void onPropertyFetchedFromDb(Property property) {

    }

    /**
     * override to server survey list call
     */
    protected void onGettingSurveyPointsFromServer() {

    }


    /**
     * to get pending surveys
     *
     * @param survey
     */
    protected void onPendingSurveys(List<Survey> survey) {

    }

    /**
     * to get all surveys
     *
     * @param survey
     */
    protected void onAllSurveys(List<Survey> survey) {

    }

    protected void onCompletedSurvey(List<Survey> completedSurvey) {

    }

    /**
     * override to get ward number from db
     */
    protected void onSurveyCountFromDb(int count) {

    }

    /**
     * get survey count
     */
    public void getSurveyCount() {
        getCompositeDisposable().add(getDataManager()
                .getSurveyCount()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveyCount -> {
                    if (surveyCount != null) {
                        onSurveyCountFromDb(surveyCount.intValue());
                    }
                }).subscribe());
    }

    public void getAllSurveyList() {
        getCompositeDisposable().add(getDataManager()
                .loadSurvey()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveys -> {
                    if (surveys != null) {
                        onAllSurveys(surveys);
                    }
                })
                .subscribe());
    }

    protected void checkApiError(Throwable throwable, BaseNavigator navigator) {
        if (throwable instanceof ANError) {
            ANError anError = (ANError) throwable;
            if (anError.getErrorCode() != 0) {
                ApiError apiError = anError.getErrorAsObject(ApiError.class);
                if (apiError != null &&
                        apiError.getError().equalsIgnoreCase(AppConstants.UN_AUTHORISED_ERROR)) {
                    navigator.onLogoutUser();
                }
            } else {
                navigator.onApiFailure();
            }
        } else {
            navigator.onApiFailure();
        }
    }

    /**
     * get ward number of particular survey
     */
    public void getSelectedWardNumber() {
        getCompositeDisposable().add(getDataManager()
                .getWardNumber(getDataManager().getCurrentSurveyID())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(wardNumber -> {
                    if (wardNumber != null) {
                        onWardNumberFetchedFromDb(wardNumber);
                    }
                }).subscribe());
    }

    /**
     * to make road details unavailable, when building status unusable or demolished
     *
     * @param buildingStatus
     * @return false if building status is unusable or demolished
     */
    protected boolean canIGoToRoadDetails(String buildingStatus) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED));
    }

    /**
     * to make tenant details unavailable, when building usage is owned,building status is unusable or
     * demolished ,or  door status is pdc
     */
    protected boolean canIGoToTenantDetails(String buildingUsage, String buildingStatus, String doorStatus, String establishmentUsage) {
        return !(buildingUsage.equalsIgnoreCase(getBaseActivity().getString(R.string.building_usage_owned))
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_SMALL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_TOOLS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_AGRICULTURAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_MATERIAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CROPS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_WOOD)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CATTLE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_PARKING)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC));
    }

    /**
     * to make tax details unavailable, when building status is unusable, demolished or ongoing,
     * or door status is pdc,dc or gate locked
     */
    protected boolean canIGoToTaxDetails(String buildingStatus, String doorStatus) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC));
    }

    /**
     * to make tax details unavailable, when building status is unusable, demolished or ongoing,
     * or door status is pdc,dc or gate locked
     */
    protected boolean canIGoToEstablishmentDetails(String buildingStatus, String establishmentUsage) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_SMALL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_TOOLS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_AGRICULTURAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_MATERIAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CROPS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_WOOD)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CATTLE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_PARKING)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_NIGHT_SHELTER_CHARITABLE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_NIGHT_SHELTER_PRIVATE));
    }

    /**
     * to make member details unavailable, when building type is residential, building sub type is resort,lodge or hostel
     * or door status is pdc
     */
    protected boolean canIGoToMemberDetails(String buildingStatus, String buildingSubType, String establishmentUsage, String doorStatus, String surveyType) {
        return !(establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_SMALL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_TOOLS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_AGRICULTURAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_MATERIAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CROPS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_WOOD)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CATTLE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_PARKING)
                || !(establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT))
                || buildingSubType.equalsIgnoreCase(AppConstants.NEW_BUILDING_SUB_TYPE_RESORT)
                || buildingSubType.equalsIgnoreCase(AppConstants.NEW_BUILDING_SUB_TYPE_LODGE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC)
                || surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));
    }

    /**
     * to make building name as NA if  a building is residential and subtype is the house
     */
    protected boolean canISetNAForBuildingName(String buildingType, String buildingSubType) {
        return (buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL)
        );
    }

    /**
     * to make live hood details unavailable, when building status is unusable or demolished,
     * building sub type is quarters,apartment or flat
     */
    protected boolean canIGoToLiveHoodDetails(String buildingStatus, String establishmentUsage, String doorStatus) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL));
    }

    /**
     * to make more details unavailable
     */
    protected boolean canIGoToMoreDetails(String buildingStatus, String doorStatus, String buildingSubType, String establishmentUsage, String surveyType) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_NC)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_SMALL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_TOOLS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_AGRICULTURAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_MATERIAL)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CROPS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_WOOD)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_CATTLE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RELATED_PARKING)
                || !(establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_VILLA)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_SINGLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_MULTIPLE_HOUSE)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_QUARTERS)
                || establishmentUsage.equalsIgnoreCase(AppConstants.ESTABLISHMENT_USAGE_RESIDENTIAL_FLAT))
                || buildingSubType.equalsIgnoreCase(AppConstants.NEW_BUILDING_SUB_TYPE_LODGE)
                || buildingSubType.equalsIgnoreCase(AppConstants.NEW_BUILDING_SUB_TYPE_HOSTEL)
                || buildingSubType.equalsIgnoreCase(AppConstants.NEW_BUILDING_SUB_TYPE_RESORT)
                || surveyType.equalsIgnoreCase(AppConstants.TELE_CALL));
    }

    /**
     * to make building details unavailable, when building status is unusable or demolished
     */
    protected boolean canIGoToBuildingDetails(String buildingStatus, String doorStatus) {
        return !(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_UNUSABLE) ||
                buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_DEMOLISHED)
                || (buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING_WITHOUT_ROOF) && (doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_DC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_PDC)
                || doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_GL))));
    }

    protected boolean isFamilyMemberGovtEmployee(ArrayList<MemberDetailsItem> memberDetails) {
        for (MemberDetailsItem member : memberDetails) {
            if (member.getMemberJobtype().equalsIgnoreCase(JOB_TYPE_CENTRAL_GOVERNMENT) || (member.getMemberJobtype().equalsIgnoreCase(JOB_TYPE_STATE_GOVERNMENT))) {
                return true;
            }
        }
        return false;
    }

    public void getPendingSurveyList() {
        getCompositeDisposable().add(getDataManager()
                .getPendingSurveys()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveys -> {
                    if (surveys != null) {
                        onPendingSurveys(surveys);
                    }
                })
                .subscribe());
    }

    /**
     * Get whether survey is open for view or edit
     *
     * @return
     */

    public boolean isSurveyOpenEditMode() {
        if (getDataManager().getSurveyOpenMode() != null && getDataManager().getSurveyOpenMode().equalsIgnoreCase(SURVEY_OPEN_MODE_VIEW)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * to set current survey id in shared preference
     * also set wheather opening survey for edit or view
     */
    public void saveCurrentSurveyIdInPref(String qFieldId, String mode) {
        getDataManager().setCurrentSurveyID(qFieldId);
        getDataManager().setSurveyOpenMode(mode);
    }

    /**
     * to set live location needed or not status in shared preference
     */
    public void saveLiveLocationStatusInPref(String status) {
        getDataManager().setLiveLocationStatus(status);
    }

    /**
     * override to get ward number from db
     *
     * @param wardNumber
     */
    protected void onWardNumberFetchedFromDb(String wardNumber) {

    }

    /**
     * to get district from shared preference
     */
    public String getDistrict() {
        return getDataManager().getDistrict();
    }

    /**
     * to get district from shared preference
     */
    public String getLocalBody() {
        return getDataManager().getLocalBody();
    }

    /**
     * to get getLocalBodyCode from shared preference
     */
    public String getLocalBodyCode() {
        return getDataManager().getLocalbodyCode();
    }

    /**
     * to get ward number from shared preference
     */
    public String getWardNumber() {
        return getDataManager().getWardNumber();
    }

    /**
     * to get ward number from shared preference
     */
    public String getWardName() {
        return getDataManager().getWardName();
    }

    /**
     * to get dashboard from shared preference
     */
    public Dashboard getDashBoard() {
        return getDataManager().getDashBoardData();
    }

    /**
     * to get survey points from shared preference
     */
    public SurvryPointsResponse getSurveyPoints() {
        return getDataManager().getSurveyPoints();
    }

    /**
     * to get tpk file location from shared preference
     *
     * @return
     */

    public String getTpkFileLoc() {
        return getDataManager().getTpkFileLocation();
    }

    /**
     * Get live location updated needed or not status from Shared preference
     *
     * @return
     */
    public String getLiveLocationStatus() {
        return getDataManager().getLiveLocationStatus();
    }

    /**
     * to get AR file location
     *
     * @return
     */
    public String getARFileLoc() {
        return getDataManager().getARFileLocation();
    }

    /**
     * to get info video file location from shared preference
     *
     * @return
     */
    public String getInfoVideoFileLoc() {
        return getDataManager().getInfoVideoFileLocation();
    }

    /**
     * @return list of localbody
     */
    public ArrayList<LocalBody> getSelectedLocalBodyList() {
        Dashboard dashboard = getDashBoard();
        String district = getDistrict();
        if (dashboard != null && district != null) {
            ArrayList<District> districtArrayList = dashboard.getDistrict();
            if (districtArrayList != null) {
                for (District item : districtArrayList) {
                    if (item.getDistrictName() != null &&
                            item.districtName.equalsIgnoreCase(district)) {
                        return item.getLocalBody();
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return list of wardNumber under selected local body
     */
    public ArrayList<CommonItem> getSelectedWardNumberList() {
        ArrayList<CommonItem> wardNumbers = null;
        LocalBody localBody = getSelectedLocalBody();
        if (localBody != null) {
            wardNumbers = localBody.getWardNumberArrayList();
        }

        return wardNumbers;
    }

    /**
     * Get selected wardnumber for getting previous survey points
     *
     * @return
     */
    public ArrayList<String> getSelectedWardsForSurveyPoints() {
        ArrayList<String> wards = new ArrayList<String>();
        if (getDataManager().getSelectedSurveyPointWards() != null) {
            wards = getDataManager().getSelectedSurveyPointWards();
        }
        return wards;
    }

    public LocalBody getSelectedLocalBody() {
        ArrayList<LocalBody> localBodyArrayList = getSelectedLocalBodyList();
        String localBody = getLocalBody();
        if (localBodyArrayList != null && localBody != null) {
            for (LocalBody item : localBodyArrayList) {
                if (item.getLocalBodyName() != null &&
                        item.getLocalBodyName().equalsIgnoreCase(localBody)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Fetch completed survey list as per given sync status
     *
     * @param syncStatus
     */

    public void fetchCompletedDataWithSyncStatus(boolean syncStatus) {
        getCompositeDisposable().add(getDataManager()
                .loadSurveyWithPropertyOnSyncStatus(syncStatus)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(this::onFetchUnSyncData)
                .subscribe());
    }

    public void fetchUnSyncedData() {
        getCompositeDisposable().add(getDataManager()
                .loadAllCompletedSurveyWithProperty()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(this::onFetchUnSyncData)
                .doOnError(throwable -> getBaseActivity().showToast("Sync Failed"))
                .subscribe());
    }

    /**
     * to fetch completed survey from db
     */
    public void fetchCompletedSurvey() {
        getCompositeDisposable().add(getDataManager()
                .getCompletedSurveys()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(surveys -> {
                    if (surveys != null) {
                        onCompletedSurvey(surveys);
                    }
                })
                .subscribe());
    }

    public void fetchSurveyPoints(String wardNumber) {
        String wardnumbersForData = getWardNumber();
        if (wardNumber.length() != 0 && !wardNumber.equalsIgnoreCase(getBaseActivity().getString(R.string.settings_default))) {
            wardnumbersForData = wardnumbersForData + "," + wardNumber;
        }
        if (CommonUtils.isNetworkConnected(getBaseActivity())) {
            getBaseActivity().showProgressDialog();
            getCompositeDisposable().add(getDataManager()
                    .doSurveyPointsApiCall(wardnumbersForData, getDataManager().getServerUrl())
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doFinally(() -> getBaseActivity().hideProgressDialog())
                    .doOnNext(response -> {
                        if (response.getStatus().equalsIgnoreCase("true")) {
                            getDataManager().saveSurveyPointData(response);
                            onGettingSurveyPointsFromServer();
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.survey_point_success_msg));
                        } else {
                            getBaseActivity().showToast(getBaseActivity().getString(R.string.server_issue));
                        }
                    })
                    .doOnError(error -> {
                        getBaseActivity().showToast(getBaseActivity().getString(R.string.server_issue));
                    }).subscribe());
        } else {
            getBaseActivity().showToast(getBaseActivity().getString(R.string.connection_error));
        }
    }

    protected void onFetchUnSyncData(List<SurveyWithProperty> surveyWithProperties) {

    }

    public ArrayList<GridItem> getScreenItemList(Property property){
        ArrayList<GridItem> listItems =new ArrayList<GridItem>();
       //Owner screen
       if(!isPointStatusLandmark()){
           //Unwanted and Building case (Point Status)
           GridItem ownerItem=new GridItem("1",getBaseActivity().getString(R.string.toolbar_owner),property.isOwnerValidationOk);
           listItems.add(ownerItem);
           if(!isPointStatusUnwanted()){
               //Only for building (Point Status)
               if(canIGoToRoadDetails(property.buildingStatus)){
                   //Road
                   GridItem roadItem=new GridItem("2",getBaseActivity().getString(R.string.toolbar_road),property.isRoadValidationOk);
                   listItems.add(roadItem);
               }
               if(canIGoToTenantDetails(property.buildingUsage,property.buildingStatus,property.doorStatus,property.buildingSubType)){
                   //Tenant
                   GridItem tenantItem=new GridItem("3",getBaseActivity().getString(R.string.toolbar_tenant),property.isTenantValidationOk);
                   listItems.add(tenantItem);
               }
               if (canIGoToTaxDetails(property.buildingStatus, property.doorStatus)) {
                   GridItem taxItem=new GridItem("4",getBaseActivity().getString(R.string.toolbar_tax),property.isTaxValidationOk);
                   listItems.add(taxItem);
               }
               if (canIGoToEstablishmentDetails(property.buildingStatus, property.buildingSubType)) {
                   //Establishment
                   GridItem establishmentItem=new GridItem("5",getBaseActivity().getString(R.string.toolbar_establishment),property.isEstablishmentValidationOk);
                   listItems.add(establishmentItem);
               }
               if (canIGoToMemberDetails(property.buildingStatus,property.buildingType, property.buildingSubType, property.doorStatus, property.surveyType)) {
                   //Member
                   GridItem memberItem=new GridItem("6",getBaseActivity().getString(R.string.toolbar_member),property.isMemberValidationOk);
                   listItems.add(memberItem);
               }
               if (canIGoToLiveHoodDetails(property.buildingStatus, property.buildingSubType,property.doorStatus)) {
                   //livehood
                   GridItem livehoodItem=new GridItem("7",getBaseActivity().getString(R.string.toolbar_live_hood),property.isLivehoodValidationOk);
                   listItems.add(livehoodItem);
               }
               if (canIGoToMoreDetails(property.buildingStatus,property.doorStatus,property.buildingType,property.buildingSubType,property.surveyType)) {
                   //More
                   GridItem moreItem=new GridItem("8",getBaseActivity().getString(R.string.toolbar_more),property.isMoreValidationOk);
                   listItems.add(moreItem);
               }
               if (canIGoToBuildingDetails(property.buildingStatus,property.doorStatus)) {
                   //Building
                   GridItem buildingItem=new GridItem("9",getBaseActivity().getString(R.string.toolbar_building),property.isBuildingValidationOk);
                   listItems.add(buildingItem);
               }

           }
       }
        //Image screen
        GridItem imageItem=new GridItem("10",getBaseActivity().getString(R.string.toolbar_images),property.isImageValidationOk);
        listItems.add(imageItem);

        return listItems;

    }


}
