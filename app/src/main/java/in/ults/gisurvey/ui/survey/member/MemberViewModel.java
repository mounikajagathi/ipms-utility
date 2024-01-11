package in.ults.gisurvey.ui.survey.member;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;

import java.util.ArrayList;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.AppConstants;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.GENDER_FEMALE;
import static in.ults.gisurvey.utils.AppConstants.GENDER_MALE;
import static in.ults.gisurvey.utils.AppConstants.MARITAL_STATUS_DIVORCED_SEPARATED;
import static in.ults.gisurvey.utils.AppConstants.MARITAL_STATUS_MARRIED;
import static in.ults.gisurvey.utils.AppConstants.MARITAL_STATUS_SINGLE;
import static in.ults.gisurvey.utils.AppConstants.PENSION_AVIVAHITHA;
import static in.ults.gisurvey.utils.AppConstants.PENSION_OLDAGE;
import static in.ults.gisurvey.utils.AppConstants.PENSION_OLDAGE_AGE_LIMIT;
import static in.ults.gisurvey.utils.AppConstants.PENSION_WIDOW;

public class MemberViewModel extends BaseViewModel<MemberNavigator> {

    public final ObservableField<String> noOfMembers = new ObservableField<>("0");

    private String buildingStatus = "";
    private String buildingSubType = "";
    private String buildingType = "";
    private String doorStatus = "";
    private String surveyType = "";

    public MemberViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    /**
     * to set number of members
     */
    public void setNoOfMembers(int number) {
        noOfMembers.set(String.valueOf(number));
    }

    /**
     * to save member details in db  and navigate to next page
     *
     * @param memberDetailsItems
     */
    void saveMemberDetails(String noOfMembers, ArrayList<MemberDetailsItem> memberDetailsItems,boolean isValidationOk) {
        getCompositeDisposable().add(getDataManager()
                .insertMemberDetails(noOfMembers, memberDetailsItems,isValidationOk, getDataManager().getCurrentSurveyID(), getDataManager().getCurrentPropertyId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(aBoolean -> {
//                    navigateToNextPage();
                    getNavigator().navigateToScreenSelection();
                })
                .subscribe());
    }

    /**
     * to fetch property details from db when back pressed and set to its fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {
        buildingStatus = property.buildingStatus;
        buildingSubType = property.buildingSubType;
        buildingType=property.buildingType;
        doorStatus = property.doorStatus;
        surveyType = property.surveyType;
        noOfMembers.set(String.valueOf(property.memberDetails.size()));
        getNavigator().setCachedData(property);
        if(property.isMemberValidationOk)
            getNavigator().disablePartialSave();

    }

    protected void navigateToNextPage() {
        if (canIGoToLiveHoodDetails(buildingStatus, buildingSubType, doorStatus)) {
            getNavigator().navigateToLiveHoodPage();
        } else if (canIGoToMoreDetails(buildingStatus, doorStatus,buildingType, buildingSubType,surveyType)) {
            getNavigator().navigateToMorePage();
        } else if (canIGoToBuildingDetails(buildingStatus,doorStatus)) {
            getNavigator().navigateToBuildingPage();
        } else {
            getNavigator().navigateToImageDetails();
        }
    }


    protected boolean checkMembers(String noOfMembers) {
        if (Integer.parseInt(noOfMembers) == 0) {
            if(doorStatus.equalsIgnoreCase(AppConstants.DOOR_STATUS_OPEN)&&buildingType.equalsIgnoreCase(AppConstants.BUILDING_TYPE_RESIDENTIAL)&&(buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_COMPLETED) || buildingStatus.equalsIgnoreCase(AppConstants.BUILDING_STATUS_ONGOING))){
                getNavigator().showWarningDialog(getBaseActivity().getString(R.string.member_empty_warning));
            }else{
                getNavigator().showNoMemberDialog();
            }
            return false;
        }
        return true;
    }

    /**
     * validate each field and set error message if validation fails
     *
     * @param dataList
     * @return
     */
    protected boolean validateFields(ArrayList<MemberDetailsItem> dataList) {
        getNavigator().clearValidationErrors();
        boolean showWarning = false;
        for (MemberDetailsItem data : dataList) {
            if (data.getMemberName().length() == 0 || data.getMemberAge().length() == 0
                    || data.getMemberGender().length() == 0 || data.getMemberMartialStatus().length() == 0
                    || data.getIsNRK().length() == 0 || data.getIsNRI().length() == 0
                    || data.getMemberEducation().length() == 0 || data.getMemberEducationType().length() == 0
                    || data.getMemberJob().length() == 0 || data.getMemberPension().contains("")
                    || data.getMemberJobLoss().length() == 0 || data.getMemberJobtype().length() == 0
                    || data.getMemberDisease().contains("") || data.getMemberDisability().length() == 0
                    || data.getMemberDisabilityPercent().length() == 0 || data.getIsStudent().length() == 0) {
                getNavigator().validationError(MemberFragment.NO_OF_MEMBER_LIST_ERROR, "");
                return false;
            }
            if (data.getMemberGender().equalsIgnoreCase(GENDER_MALE) && data.getMemberPension().contains(PENSION_WIDOW)) {
                //MALE WITH MARITAL STATUS SINGLE?MARRIED?DIVORCED/SEPERATED
                //Getting widow pension need to show Error
                if (data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_SINGLE) || data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_MARRIED) || data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_DIVORCED_SEPARATED)) {
                    getNavigator().showErrorDialog(getBaseActivity().getString(R.string.member_male_widow_pension_error));
                    return false;
                }
            } else if (Integer.valueOf(data.getMemberAge().replaceAll("[^0-9]", "")) < PENSION_OLDAGE_AGE_LIMIT && data.getMemberPension().contains(PENSION_OLDAGE)) {
                //AGE < 60
                //Getting oldage pension need to show Error
                getNavigator().showErrorDialog(getBaseActivity().getString(R.string.member_oldage_pension_error));
                return false;

            }else if ( data.getMemberPension().contains(PENSION_AVIVAHITHA)) {
                //GENDER NOT FEMALE AND MARITAL STATUS NOT SINGLE
                //Getting Avivahitha pension need to show Error
                if(! (data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_SINGLE) && data.getMemberGender().equalsIgnoreCase(GENDER_FEMALE))) {
                    getNavigator().showErrorDialog(getBaseActivity().getString(R.string.member_avivahitha_pension_error));
                    return false;
                }
            }
            else if (data.getMemberGender().equalsIgnoreCase(GENDER_FEMALE) && data.getMemberPension().contains(PENSION_WIDOW)) {
                //FEMALE WITH MARITAL STATUS SINGLE?MARRIED?DIVORCED/SEPERATED
                //Getting widow pension need to show warning
                if (data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_SINGLE) || data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_MARRIED) || data.getMemberMartialStatus().equalsIgnoreCase(MARITAL_STATUS_DIVORCED_SEPARATED)) {
                    showWarning = true;
                }
            }
        }

        if (showWarning) {
            getNavigator().showWarningDialog(getBaseActivity().getString(R.string.member_female_widow_pension_warning));
            return false;
        }
        return true;
    }

    /**
     * to save member details
     */
    public void onNextClick() {
        getNavigator().saveMemberDetails(false);
    }

    /**
     * to partial save owner details ie without validation
     */
    public void onPartialSaveClick() {
        getNavigator().saveMemberDetails(true);
    }

    /**
     * to add new member
     */
    public void onAddMembersClick() {
        getNavigator().addMembers();
    }

    /**
     * to remove existing member
     */
    public void onRemoveMembersClick() {
        getNavigator().removeMembers();
    }


}