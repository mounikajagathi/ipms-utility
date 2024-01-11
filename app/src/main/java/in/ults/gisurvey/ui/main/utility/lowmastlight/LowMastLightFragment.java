package in.ults.gisurvey.ui.main.utility.lowmastlight;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityLowMastLightBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class LowMastLightFragment extends BaseFragment<FragmentUtilityLowMastLightBinding, LowMastLightViewModel> implements LowMastLightNavigator {

    public static final String TAG = LowMastLightFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private LowMastLightViewModel viewModel;
    private String imagePath1 = "";
    static final int LOW_MAST_LOCATION_ERROR = 1;
    static final int LOW_MAST_ADDRESS_ERROR = 2;
    static final int LOW_MAST_FUNDED_BY_ERROR = 3;
    static final int LOW_MAST_LIGHT_TYPE_ERROR = 4;
    static final int LOW_MAST_WORKING_STATUS_ERROR = 5;
    static final int LOW_MAST_WARRANTY_ERROR = 6;
    static final int LOW_MAST_VENDOR_ERROR = 7;
    static final int LOW_MAST_EXPIRY_DATE_ERROR = 8;
    static final int LOW_MAST_AMC_ERROR = 9;
    static final int LOW_MAST_REMARKS_ERROR = 10;
    static final int LOW_MAST_COMMON_ERROR = 11;
    @Inject
    CommonDropDownAdapter lowMastLightTypeAdapter;
    @Inject
    CommonDropDownAdapter lowMastWorkingStatusAdapter;

    public static LowMastLightFragment newInstance() {
        Bundle args = new Bundle();
        LowMastLightFragment fragment = new LowMastLightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_low_mast_light;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public LowMastLightViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LowMastLightViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etLowMastLocation.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewDataBinding().etLowMastAddress.getText()).toString().trim();
        String fundedBy = Objects.requireNonNull(getViewDataBinding().etLowMastFundedBy.getText()).toString().trim();
        String lightType = (String) getViewDataBinding().srLowMastLightType.getTag();
        String workingStatus = (String) getViewDataBinding().srLowMastWorkingStatus.getTag();
        String warranty = Objects.requireNonNull(getViewDataBinding().etLowMastLightWarranty.getText()).toString().trim();
        String vendor = Objects.requireNonNull(getViewDataBinding().etLowMastLightVendor.getText()).toString().trim();
        String expiryDate = Objects.requireNonNull(getViewDataBinding().etLowMastExpiryDate.getText()).toString().trim();
        String amc = Objects.requireNonNull(getViewDataBinding().etLowMastAMC.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, address, fundedBy, lightType, workingStatus,warranty,vendor, expiryDate, amc,remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, address, fundedBy, lightType, workingStatus,warranty,vendor, expiryDate, amc,remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, address, fundedBy, lightType, workingStatus,warranty,vendor, expiryDate, amc,remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case LOW_MAST_LOCATION_ERROR:
                getViewDataBinding().layoutLowMastLocation.setError(error);
                getViewDataBinding().layoutLowMastLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastLocation, getViewDataBinding().layoutLowMastLocation);
                break;
            case LOW_MAST_ADDRESS_ERROR:
                getViewDataBinding().layoutLowMastAddress.setError(error);
                getViewDataBinding().layoutLowMastAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastAddress, getViewDataBinding().layoutLowMastAddress);
                break;
            case LOW_MAST_FUNDED_BY_ERROR:
                getViewDataBinding().layoutLowMastFundedBy.setError(error);
                getViewDataBinding().layoutLowMastFundedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastFundedBy, getViewDataBinding().layoutLowMastFundedBy);
                break;
            case LOW_MAST_LIGHT_TYPE_ERROR:
                getViewDataBinding().layoutLowMastLightType.setError(error);
                getViewDataBinding().layoutLowMastLightType.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastLightType, getViewDataBinding().layoutLowMastLightType);
                break;
            case LOW_MAST_WORKING_STATUS_ERROR:
                getViewDataBinding().layoutLowMastWorkingStatus.setError(error);
                getViewDataBinding().layoutLowMastWorkingStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastWorkingStatus, getViewDataBinding().layoutLowMastWorkingStatus);
                break;
            case LOW_MAST_WARRANTY_ERROR:
                getViewDataBinding().layoutLowMastLightWarranty.setError(error);
                getViewDataBinding().layoutLowMastLightWarranty.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastLightWarranty, getViewDataBinding().layoutLowMastLightWarranty);
                break;
            case LOW_MAST_VENDOR_ERROR:
                getViewDataBinding().layoutLowMastVendor.setError(error);
                getViewDataBinding().layoutLowMastVendor.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastVendor, getViewDataBinding().layoutLowMastVendor);
                break;

            case LOW_MAST_EXPIRY_DATE_ERROR:
                getViewDataBinding().layoutLowMastExpiryDate.setError(error);
                getViewDataBinding().layoutLowMastExpiryDate.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastExpiryDate, getViewDataBinding().layoutLowMastExpiryDate);
                break;
            case LOW_MAST_AMC_ERROR:
                getViewDataBinding().layoutLowMastAMC.setError(error);
                getViewDataBinding().layoutLowMastAMC.getParent().requestChildFocus
                        (getViewDataBinding().layoutLowMastAMC, getViewDataBinding().layoutLowMastAMC);
                break;
            case LOW_MAST_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case LOW_MAST_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutLowMastLocation.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastAddress.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastFundedBy.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastLightType.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastLightWarranty.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastVendor.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastWorkingStatus.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastAMC.setErrorEnabled(false);
        getViewDataBinding().layoutLowMastExpiryDate.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_low_mast);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srLowMastLightType.setAdapter(lowMastLightTypeAdapter);
        getViewDataBinding().srLowMastWorkingStatus.setAdapter(lowMastWorkingStatusAdapter);
        viewModel.loadData();
    }

}