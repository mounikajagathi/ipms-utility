package in.ults.gisurvey.ui.main.utility.taxistand;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityTaxiStandDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class TaxiStandFragment extends BaseFragment<FragmentUtilityTaxiStandDetailsBinding, TaxiStandViewModel> implements TaxiStandNavigator {

    public static final String TAG = TaxiStandFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private TaxiStandViewModel viewModel;

    private String imagePath1 = "";
    static final int TAXI_STAND_PLACE_NAME_ERROR = 1;
    static final int TAXI_STAND_LOCATION_ERROR = 2;
    static final int TAXI_STAND_PARKING_TYPE_ERROR = 3;
    static final int TAXI_STAND_CAPACITY_ERROR = 4;
    static final int TAXI_STAND_AUTH_ERROR = 5;
    static final int TAXI_STAND_AUTH_DETAILS_ERROR = 6;
    static final int TAXI_STAND_REMARKS_ERROR = 7;
    static final int TAXI_STAND_COMMON_ERROR = 8;
    @Inject
    CommonDropDownAdapter taxiStandParkingTypeAdapter;
    @Inject
    CommonDropDownAdapter taxiStandAuthorisedAdapter;

    public static TaxiStandFragment newInstance() {
        Bundle args = new Bundle();
        TaxiStandFragment fragment = new TaxiStandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_taxi_stand_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public TaxiStandViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(TaxiStandViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String place = Objects.requireNonNull(getViewDataBinding().etPlace.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewDataBinding().etLocation.getText()).toString().trim();
        String parkingType = (String) getViewDataBinding().srParkingType.getTag();
        String capacity = Objects.requireNonNull(getViewDataBinding().etCapacity.getText()).toString().trim();
        String auth = (String) getViewDataBinding().srTaxiStandAuthorised.getTag();
        String authDetails = Objects.requireNonNull(getViewDataBinding().etAuthDetails.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(place, location, parkingType, capacity, auth, authDetails, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(place, location, parkingType, capacity, auth, authDetails, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(place, location, parkingType, capacity, auth, authDetails, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TAXI_STAND_PLACE_NAME_ERROR:
                getViewDataBinding().layoutTaxiStandPlace.setError(error);
                getViewDataBinding().layoutTaxiStandPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandPlace, getViewDataBinding().layoutTaxiStandPlace);
                break;
            case TAXI_STAND_LOCATION_ERROR:
                getViewDataBinding().layoutTaxiStandLocation.setError(error);
                getViewDataBinding().layoutTaxiStandLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandLocation, getViewDataBinding().layoutTaxiStandLocation);
                break;
            case TAXI_STAND_PARKING_TYPE_ERROR:
                getViewDataBinding().layoutTaxiStandParkingType.setError(error);
                getViewDataBinding().layoutTaxiStandParkingType.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandParkingType, getViewDataBinding().layoutTaxiStandParkingType);
                break;
            case TAXI_STAND_CAPACITY_ERROR:
                getViewDataBinding().layoutTaxiStandCapacity.setError(error);
                getViewDataBinding().layoutTaxiStandCapacity.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandCapacity, getViewDataBinding().layoutTaxiStandCapacity);
                break;
            case TAXI_STAND_AUTH_ERROR:
                getViewDataBinding().layoutTaxiStandAuthorised.setError(error);
                getViewDataBinding().layoutTaxiStandAuthorised.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandAuthorised, getViewDataBinding().layoutTaxiStandAuthorised);
                break;
            case TAXI_STAND_AUTH_DETAILS_ERROR:
                getViewDataBinding().layoutTaxiStandAuthDetails.setError(error);
                getViewDataBinding().layoutTaxiStandAuthDetails.getParent().requestChildFocus
                        (getViewDataBinding().layoutTaxiStandAuthDetails, getViewDataBinding().layoutTaxiStandAuthDetails);
                break;
            case TAXI_STAND_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case TAXI_STAND_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutTaxiStandPlace.setErrorEnabled(false);
        getViewDataBinding().layoutTaxiStandLocation.setErrorEnabled(false);
        getViewDataBinding().layoutTaxiStandParkingType.setErrorEnabled(false);
        getViewDataBinding().layoutTaxiStandCapacity.setErrorEnabled(false);
        getViewDataBinding().layoutTaxiStandAuthDetails.setErrorEnabled(false);
        getViewDataBinding().layoutTaxiStandAuthorised.setErrorEnabled(false);
        getViewDataBinding().layoutRemarks.setErrorEnabled(false);
    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility_taxi_stand);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srParkingType.setAdapter(taxiStandParkingTypeAdapter);
        getViewDataBinding().srTaxiStandAuthorised.setAdapter(taxiStandAuthorisedAdapter);
        viewModel.loadData();
    }

}