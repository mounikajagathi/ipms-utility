package in.ults.gisurvey.ui.survey.location;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.IPMSApp;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.api.LocalBody;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.data.model.items.CommonItem;
import in.ults.gisurvey.databinding.FragmentLocationBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.arcgis.ArcGisActivity;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;
import in.ults.gisurvey.utils.adapters.PostOfficeListAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class LocationFragment extends BaseFragment<FragmentLocationBinding, LocationViewModel> implements LocationNavigator {

    public static final String TAG = LocationFragment.class.getSimpleName();

    static final int COMMON_ERROR = 1;
    static final int WARD_NUMBER_ERROR = 2;
    static final int STREET_ERROR = 3;
    static final int PLACE_ERROR = 4;
    static final int VILLAGE_NAME_ERROR = 5;
    static final int POST_OFFICE_ERROR = 6;
    static final int BUILDING_ZONE_ERROR = 8;
    static final int WATER_LEVEL_HIT_ERROR = 9;

    private LocationViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CommonDropDownAdapter wardNumberAdapter;

    @Inject
    CommonDropDownAdapter villageNameAdapter;

    @Inject
    PostOfficeListAdapter postOfficeAdapter;

    @Inject
    CommonDropDownAdapter buildingZoneAdapter;

    public static LocationFragment newInstance() {
        Bundle args = new Bundle();
        LocationFragment fragment = new LocationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_location;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public LocationViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LocationViewModel.class);
        return viewModel;
    }


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_location);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        getViewDataBinding().etWardNumber.setAdapter(wardNumberAdapter);
        getViewDataBinding().etVillageName.setAdapter(villageNameAdapter);
        getViewDataBinding().etPostOffice.setAdapter(postOfficeAdapter);
        getViewDataBinding().etBuildingZone.setAdapter(buildingZoneAdapter);
        Dashboard dashboard = viewModel.getDashBoard();
        if (dashboard != null) {
            ArrayList<CommonItem> villages = dashboard.getVillage();
            ArrayList<CommonItem> postOffices = dashboard.getPostOffice();
            viewModel.setVillageNameList(villages);
            viewModel.setPostOfficeList(postOffices);

        }
        getViewDataBinding().etPostOffice.setOnItemClickListener((parent, view, position, id) -> {
            CommonItem postOffice = postOfficeAdapter.getItem(position);
            if (postOffice != null && postOffice.getSubContent() != null) {
                viewModel.setPostOffice(postOffice.getContent());
                viewModel.setPinCode(postOffice.getSubContent());

            }

        });
        ArrayList<CommonItem> buildingZones = IPMSApp.getAppInstance().getLocMainItem().getBuildingZones();
        viewModel.setBuildingZoneList(buildingZones);
        String districtName = viewModel.getDistrict();
        String localBodyName = viewModel.getLocalBody();
        String notSet = getBaseActivity().getString(R.string.settings_default);
        viewModel.setDistrict(districtName != null ? districtName : notSet);
        viewModel.setLocalBody(localBodyName != null ? localBodyName : notSet);
        LocalBody localBody = viewModel.getSelectedLocalBody();
        if (localBody != null) {
            ArrayList<CommonItem> wardNumbers = localBody.getWardNumberArrayList();
            viewModel.setWardNumberList(wardNumbers);
            getViewDataBinding().etWardNumber.setOnItemClickListener((parent, view, position, id) -> viewModel.setWardName(wardNumbers.get(position).getSubContent()));
        }
        getViewDataBinding().rgFloodStatus.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbFloodStatusYes) {
                viewModel.setIsFloodAffected(true);
            } else {
                viewModel.setIsFloodAffected(false);
            }
        });

        getViewModel().getCurrentSurvey();
    }

    /**
     * to validate fields and save location details
     */
    @Override
    public void saveLocationDetails() {
        getBaseActivity().hideKeyboard();
        String district = Objects.requireNonNull(getViewDataBinding().etDistrict.getText()).toString().trim();
        String localBody = Objects.requireNonNull(getViewDataBinding().etLocalBody.getText()).toString().trim();
        String wardNumber = Objects.requireNonNull(getViewDataBinding().etWardNumber.getText()).toString().trim();
        String wardName = Objects.requireNonNull(getViewDataBinding().etWardName.getText()).toString().trim();
        String streetName = Objects.requireNonNull(getViewDataBinding().etStreetName.getText()).toString().trim();
        String placeName = Objects.requireNonNull(getViewDataBinding().etPlaceName.getText()).toString().trim();
        String villageName = Objects.requireNonNull(getViewDataBinding().etVillageName.getText()).toString().trim();
        String postOffice = Objects.requireNonNull(getViewDataBinding().etPostOffice.getText()).toString().trim();
        String pinCode = Objects.requireNonNull(getViewDataBinding().etPinCode.getText()).toString().trim();
        String buildingZone = Objects.requireNonNull(getViewDataBinding().etBuildingZone.getText()).toString().trim();
        String floodAffected = "";
        if (getViewDataBinding().rgFloodStatus.getCheckedRadioButtonId() != -1) {
            floodAffected = ((RadioButton) getViewDataBinding().rgFloodStatus.findViewById(getViewDataBinding().rgFloodStatus.getCheckedRadioButtonId())).getText().toString();
        }
        String waterLevelHit = Objects.requireNonNull(getViewDataBinding().etWaterLevelHit.getText().toString().trim());
        if (viewModel.validateFields(district, localBody, wardNumber, streetName, placeName, villageName, postOffice, buildingZone, floodAffected, waterLevelHit)) {
            if(viewModel.surveyIdWardNumber.getValue().equalsIgnoreCase(wardNumber)){
                viewModel.saveLocation(district, localBody, wardNumber, wardName, streetName, placeName, villageName, postOffice, pinCode, buildingZone, floodAffected, waterLevelHit);
            }else{
                showInfoDialog(getString(R.string.location_ward_no_warning));
            }
        }
    }

    /**
     * navigate to survey grid fragment
     */
    @Override
    public void navigateToSurveyGridPage() {
        getBaseActivity().showSurveyGridFragment(true);
    }

    @Override
    public void navigateToPropertyPage() {
        if (getBaseActivity().hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && getBaseActivity().hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
            startActivityForResult(new Intent(getBaseActivity(), ArcGisActivity.class),54321);
        }else{
            String[] permissions  = {Manifest.permission.ACCESS_FINE_LOCATION};
            getBaseActivity().requestPermissionsSafely(permissions,786);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 54321) {
            if(resultCode == Activity.RESULT_OK){
                getBaseActivity().showPropertyFragment(true);
            }
        }
    }



    /**
     * set validation error message
     *
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
            case WARD_NUMBER_ERROR:
                getViewDataBinding().layoutWardNumber.setError(error);
                getViewDataBinding().layoutWardNumber.getParent().requestChildFocus
                        (getViewDataBinding().layoutWardNumber,getViewDataBinding().layoutWardNumber);
                break;
            case STREET_ERROR:
                getViewDataBinding().layoutStreetName.setError(error);
                getViewDataBinding().layoutStreetName.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetName,getViewDataBinding().layoutStreetName);
                break;
            case PLACE_ERROR:
                getViewDataBinding().layoutPlaceName.setError(error);
                getViewDataBinding().layoutPlaceName.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlaceName,getViewDataBinding().layoutPlaceName);
                break;
            case VILLAGE_NAME_ERROR:
                getViewDataBinding().layoutVillageName.setError(error);
                getViewDataBinding().layoutVillageName.getParent().requestChildFocus
                        (getViewDataBinding().layoutVillageName,getViewDataBinding().layoutVillageName);
                break;
            case POST_OFFICE_ERROR:
                getViewDataBinding().layoutPostOffice.setError(error);
                getViewDataBinding().layoutPostOffice.getParent().requestChildFocus
                        (getViewDataBinding().layoutPostOffice,getViewDataBinding().layoutPostOffice);
                break;
           case BUILDING_ZONE_ERROR:
                getViewDataBinding().layoutBuildingZone.setError(error);
                getViewDataBinding().layoutBuildingZone.getParent().requestChildFocus
                        (getViewDataBinding().layoutBuildingZone,getViewDataBinding().layoutBuildingZone);
                break;
            case WATER_LEVEL_HIT_ERROR:
                getViewDataBinding().layoutWaterLevelHit.setError(error);
                getViewDataBinding().layoutWaterLevelHit.getParent().requestChildFocus
                        (getViewDataBinding().layoutWaterLevelHit, getViewDataBinding().layoutWaterLevelHit);
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutDistrict.setErrorEnabled(false);
        getViewDataBinding().layoutLocalBody.setErrorEnabled(false);
        getViewDataBinding().layoutWardNumber.setErrorEnabled(false);
        getViewDataBinding().layoutStreetName.setErrorEnabled(false);
        getViewDataBinding().layoutPlaceName.setErrorEnabled(false);
        getViewDataBinding().layoutVillageName.setErrorEnabled(false);
        getViewDataBinding().layoutPostOffice.setErrorEnabled(false);
        getViewDataBinding().layoutBuildingZone.setErrorEnabled(false);
        getViewDataBinding().layoutWaterLevelHit.setErrorEnabled(false);
    }

    @Override
    public void setCachedData(Survey survey) {
        if (survey.floodAffected.length() != 0)
            getViewDataBinding().rgFloodStatus.check(survey.floodAffected.equalsIgnoreCase(getString(R.string.cmn_yes)) ? R.id.rbFloodStatusYes : R.id.rbFloodStatusNo);


    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
}