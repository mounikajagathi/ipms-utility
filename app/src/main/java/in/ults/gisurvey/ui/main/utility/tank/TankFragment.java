package in.ults.gisurvey.ui.main.utility.tank;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentTaxBinding;
import in.ults.gisurvey.databinding.FragmentUtilityTankDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class TankFragment extends BaseFragment<FragmentUtilityTankDetailsBinding, TankViewModel> implements TankNavigator {

    public static final String TAG = TankFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private TankViewModel viewModel;

    private String imagePath1 = "";
    static final int TANK_LOCATION_ERROR = 1;
    static final int TANK_OWNER_ERROR = 2;
    static final int TANK_CAPACITY_ERROR = 3;
    static final int TANK_WATER_TANK_TYPE_ERROR = 4;
    static final int TANK_REMARKS_ERROR = 5;
    static final int TANK_COMMON_ERROR = 6;

    @Inject
    CommonDropDownAdapter tankTypeAdapter;

    public static TankFragment newInstance() {
        Bundle args = new Bundle();
        TankFragment fragment = new TankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_tank_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public TankViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(TankViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etTankLocation.getText()).toString().trim();
        String tankOwner = Objects.requireNonNull(getViewDataBinding().etTankOwner.getText()).toString().trim();
        String capacity = Objects.requireNonNull(getViewDataBinding().etTankCapacity.getText()).toString().trim();
        String tankType = (String) getViewDataBinding().srTankType.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, tankOwner, capacity, tankType, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, tankOwner, capacity, tankType, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, tankOwner, capacity, tankType, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TANK_LOCATION_ERROR:
                getViewDataBinding().layoutTankLocation.setError(error);
                getViewDataBinding().layoutTankLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutTankLocation, getViewDataBinding().layoutTankLocation);
                break;
            case TANK_OWNER_ERROR:
                getViewDataBinding().layoutTankOwner.setError(error);
                getViewDataBinding().layoutTankOwner.getParent().requestChildFocus
                        (getViewDataBinding().layoutTankOwner, getViewDataBinding().layoutTankOwner);
                break;
            case TANK_CAPACITY_ERROR:
                getViewDataBinding().layoutTankCapacity.setError(error);
                getViewDataBinding().layoutTankCapacity.getParent().requestChildFocus
                        (getViewDataBinding().layoutTankCapacity, getViewDataBinding().layoutTankCapacity);
                break;
            case TANK_WATER_TANK_TYPE_ERROR:
                getViewDataBinding().layoutTankType.setError(error);
                getViewDataBinding().layoutTankType.getParent().requestChildFocus
                        (getViewDataBinding().layoutTankType, getViewDataBinding().layoutTankType);
                break;
            case TANK_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case TANK_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutTankLocation.setErrorEnabled(false);
        getViewDataBinding().layoutTankOwner.setErrorEnabled(false);
        getViewDataBinding().layoutTankCapacity.setErrorEnabled(false);
        getViewDataBinding().layoutTankType.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_tank);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srTankType.setAdapter(tankTypeAdapter);
        viewModel.loadData();
    }

}