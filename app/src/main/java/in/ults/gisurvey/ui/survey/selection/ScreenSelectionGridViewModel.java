package in.ults.gisurvey.ui.survey.selection;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.ults.gisurvey.R;
import in.ults.gisurvey.data.DataManager;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.items.GridItem;
import in.ults.gisurvey.ui.base.BaseViewModel;
import in.ults.gisurvey.utils.rx.SchedulerProvider;

import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_BUILDING;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_ESTABLISHMENT;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_IMAGE;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_LIVEHOOD;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_MEMBER;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_MORE;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_OWNER;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_ROAD;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_TAX;
import static in.ults.gisurvey.utils.AppConstants.SCREEN_SELECTION_TENANAT;

public class ScreenSelectionGridViewModel extends BaseViewModel<ScreenSelectionGridNavigator> {



    private String buildingStatus = "";
    private String doorStatus = "";
    private String buildingType = "";
    private String buildingSubType = "";
    private final MutableLiveData<List<GridItem>> selectionScreenList = new MutableLiveData<>();
    public final ObservableBoolean isCompletedAllValidation = new ObservableBoolean(false);

    public ScreenSelectionGridViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }
    protected boolean validateFields(String taxNumber, String taxAmount, String taxDate, String taxAnnualAmount) {

        return true;
    }



    /**
     * to fetch property details from db, when back pressed and to set fields
     *
     * @param property
     */
    @Override
    protected void onPropertyFetchedFromDb(Property property) {

       setCompletedSurveyList(getScreenItemList(property));
        checkAllValidation(property);

    }
    public void setCompletedSurveyList(List<GridItem> list) {
        this.selectionScreenList.setValue(list);
    }

    public void onSubmitClick() {
        getNavigator().navigateToCompleteSurveyPage();
    }

    public MutableLiveData<List<GridItem>> getSelectedScreenList() {

        return selectionScreenList;
    }

    public void onScreenSelection(String title){
        switch (title){
            case SCREEN_SELECTION_OWNER:
                getNavigator().navigateToOwnerPage();
                break;
            case SCREEN_SELECTION_ROAD:
                getNavigator().navigateToRoadPage();
                break;
            case SCREEN_SELECTION_TENANAT:
                getNavigator().navigateToTenantPage();
                break;
            case SCREEN_SELECTION_TAX:
                getNavigator().navigateToTaxPage();
                break;
            case SCREEN_SELECTION_ESTABLISHMENT:
                getNavigator().navigateToEstablishmentPage();
                break;
            case SCREEN_SELECTION_MEMBER:
                getNavigator().navigateToMemberPage();
                break;
            case SCREEN_SELECTION_LIVEHOOD:
                getNavigator().navigateToLiveHoodPage();
                break;
            case SCREEN_SELECTION_MORE:
                getNavigator().navigateToMorePage();
                break;
            case SCREEN_SELECTION_BUILDING:
                getNavigator().navigateToBuildingPage();
                break;
            case SCREEN_SELECTION_IMAGE:
                getNavigator().navigateToImageDetails();
                break;
        }
        if(title.equalsIgnoreCase(getBaseActivity().getString(R.string.toolbar_owner))){

        }
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
                if(canIGoToTenantDetails(property.buildingUsage,property.buildingStatus,property.doorStatus,property.establishmentUsage)){
                    //Tenant
                    GridItem tenantItem=new GridItem("3",getBaseActivity().getString(R.string.toolbar_tenant),property.isTenantValidationOk);
                    listItems.add(tenantItem);
                }
                if (canIGoToTaxDetails(property.buildingStatus, property.doorStatus)) {
                    GridItem taxItem=new GridItem("4",getBaseActivity().getString(R.string.toolbar_tax),property.isTaxValidationOk);
                    listItems.add(taxItem);
                }
                if (canIGoToEstablishmentDetails(property.buildingStatus, property.establishmentUsage)) {
                    //Establishment
                    GridItem establishmentItem=new GridItem("5",getBaseActivity().getString(R.string.toolbar_establishment),property.isEstablishmentValidationOk);
                    listItems.add(establishmentItem);
                }
                if (canIGoToMemberDetails(property.buildingStatus,property.buildingSubType, property.establishmentUsage, property.doorStatus, property.surveyType)) {
                    //Member
                    GridItem memberItem=new GridItem("6",getBaseActivity().getString(R.string.toolbar_member),property.isMemberValidationOk);
                    listItems.add(memberItem);
                }
                if (canIGoToLiveHoodDetails(property.buildingStatus, property.establishmentUsage,property.doorStatus)) {
                    //livehood
                    GridItem livehoodItem=new GridItem("7",getBaseActivity().getString(R.string.toolbar_live_hood),property.isLivehoodValidationOk);
                    listItems.add(livehoodItem);
                }
                if (canIGoToMoreDetails(property.buildingStatus,property.doorStatus,property.buildingSubType,property.establishmentUsage, property.surveyType)) {
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

    public void checkAllValidation(Property property){

        if(!property.isImageValidationOk){
            isCompletedAllValidation.set(false);
        }else{
            //ImageScreen validation ok
            if(isPointStatusLandmark()){
                isCompletedAllValidation.set(true);
            }else{

                if(!property.isOwnerValidationOk){
                    isCompletedAllValidation.set(false);
                }else{
                    //Owner Validation ok

                    if(isPointStatusUnwanted()){
                        isCompletedAllValidation.set(true);
                    }else{
                        boolean tempStatus=true;
                        if(canIGoToRoadDetails(property.buildingStatus)){
                            //Road
                            if(!property.isRoadValidationOk){
                                tempStatus=false;
                            }
                        }
                        if(tempStatus&&canIGoToTenantDetails(property.buildingUsage,property.buildingStatus,property.doorStatus,property.establishmentUsage)){
                            //Tenant
                            if(!property.isTenantValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToTaxDetails(property.buildingStatus, property.doorStatus)) {
                            if(!property.isTaxValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToEstablishmentDetails(property.buildingStatus, property.establishmentUsage)) {
                            //Establishment
                            if(!property.isEstablishmentValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToMemberDetails(property.buildingStatus,property.buildingSubType, property.establishmentUsage, property.doorStatus, property.surveyType)) {
                            //Member
                            if(!property.isMemberValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToLiveHoodDetails(property.buildingStatus, property.establishmentUsage,property.doorStatus)) {
                            //livehood
                            if(!property.isLivehoodValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToMoreDetails(property.buildingStatus,property.doorStatus,property.buildingSubType,property.establishmentUsage, property.surveyType)) {
                            //More
                            if(!property.isMoreValidationOk){
                                tempStatus=false;
                            }
                        }
                        if (tempStatus&&canIGoToBuildingDetails(property.buildingStatus,property.doorStatus)) {
                            //Building
                            if(!property.isBuildingValidationOk){
                                tempStatus=false;
                            }
                        }
                        if(tempStatus)
                            isCompletedAllValidation.set(true);
                    }
                }
            }
        }

    }
}