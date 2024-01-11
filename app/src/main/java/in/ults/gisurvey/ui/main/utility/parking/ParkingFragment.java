package in.ults.gisurvey.ui.main.utility.parking;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityParkingDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class ParkingFragment extends BaseFragment<FragmentUtilityParkingDetailsBinding, ParkingViewModel> implements ParkingNavigator {

    public static final String TAG = ParkingFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private ParkingViewModel viewModel;

    private String imagePath1 = "";
    static final int PARKING_PLACE_ERROR = 1;
    static final int PARKING_TYPE_ERROR = 2;
    static final int PARKING_CAPACITY_ERROR = 3;
    static final int PARKING_AREA_ERROR = 4;
    static final int PARKING_UNDER_ERROR = 5;
    static final int PARKING_TYPE_REMARKS_ERROR = 6;
    static final int PARKING_COMMON_ERROR = 7;

    @Inject
    CommonDropDownAdapter parkingTypeAdapter;
    @Inject
    CommonDropDownAdapter parkingUnderAdapter;

    public static ParkingFragment newInstance() {
        Bundle args = new Bundle();
        ParkingFragment fragment = new ParkingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_parking_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ParkingViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ParkingViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String place = Objects.requireNonNull(getViewDataBinding().etParkingPlace.getText()).toString().trim();
        String type = (String) getViewDataBinding().srParkingType.getTag();
        String capacity = Objects.requireNonNull(getViewDataBinding().etParkingCapacity.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewDataBinding().etParkingArea.getText()).toString().trim();
        String parkingUnder = (String) getViewDataBinding().srParkingUnder.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(place,type, capacity, area, parkingUnder, remarks,imagePath1)) {
                viewModel.saveUtilityDetails(place, type, capacity, area, parkingUnder, remarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(place,type, capacity, area, parkingUnder, remarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case PARKING_PLACE_ERROR:
                getViewDataBinding().layoutParkingPlace.setError(error);
                getViewDataBinding().layoutParkingPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkingPlace, getViewDataBinding().layoutParkingPlace);
                break;
            case PARKING_TYPE_ERROR:
                getViewDataBinding().layoutParkingType.setError(error);
                getViewDataBinding().layoutParkingType.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkingType, getViewDataBinding().layoutParkingType);
                break;
            case PARKING_CAPACITY_ERROR:
                getViewDataBinding().layoutParkingCapacity.setError(error);
                getViewDataBinding().layoutParkingCapacity.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkingCapacity, getViewDataBinding().layoutParkingCapacity);
                break;
            case PARKING_AREA_ERROR:
                getViewDataBinding().layoutParkingArea.setError(error);
                getViewDataBinding().layoutParkingArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkingArea, getViewDataBinding().layoutParkingArea);
                break;
            case PARKING_UNDER_ERROR:
                getViewDataBinding().layoutParkingUnder.setError(error);
                getViewDataBinding().layoutParkingUnder.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkingUnder, getViewDataBinding().layoutParkingUnder);
                break;
            case PARKING_TYPE_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case PARKING_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutParkingPlace.setErrorEnabled(false);
        getViewDataBinding().layoutParkingType.setErrorEnabled(false);
        getViewDataBinding().layoutParkingCapacity.setErrorEnabled(false);
        getViewDataBinding().layoutParkingArea.setErrorEnabled(false);
        getViewDataBinding().layoutParkingUnder.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_parking);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srParkingType.setAdapter(parkingTypeAdapter);
        getViewDataBinding().srParkingUnder.setAdapter(parkingUnderAdapter);
        viewModel.loadData();
    }

}