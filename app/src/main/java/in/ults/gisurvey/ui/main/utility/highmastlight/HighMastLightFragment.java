package in.ults.gisurvey.ui.main.utility.highmastlight;


import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityHighMastLightBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class HighMastLightFragment extends BaseFragment<FragmentUtilityHighMastLightBinding, HighMastLightViewModel> implements HighMastLightNavigator {

    public static final String TAG = HighMastLightFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private HighMastLightViewModel viewModel;

    @Inject
    CommonDropDownAdapter highMastLightTypeAdapter;
    @Inject
    CommonDropDownAdapter highMastLightWorkingStatusAdapter;

    private String imagePath1 = "";
    static final int HIGH_MAST_LOCATION_ERROR = 1;
    static final int HIGH_MAST_ADDRESS_ERROR = 2;
    static final int HIGH_MAST_FUNDED_BY_ERROR = 3;
    static final int HIGH_MAST_LIGHT_BY_ERROR = 4;
    static final int HIGH_MAST_HEIGHT_ERROR = 5;
    static final int HIGH_MAST_NO_OF_BULBS_ERROR = 6;
    static final int HIGH_MAST_WORKING_STATUS_ERROR = 7;
    static final int HIGH_MAST_VENDOR_ERROR = 8;
    static final int HIGH_MAST_EXPIRY_DATE_ERROR = 9;
    static final int HIGH_MAST_REMARKS_ERROR = 10;
    static final int HIGH_MAST_COMMON_ERROR = 11;

    public static HighMastLightFragment newInstance() {
        Bundle args = new Bundle();
        HighMastLightFragment fragment = new HighMastLightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_high_mast_light;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public HighMastLightViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HighMastLightViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etHighMastLocation.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewDataBinding().etHighMastAddress.getText()).toString().trim();
        String fundedBy = Objects.requireNonNull(getViewDataBinding().etHighMastFundedBy.getText()).toString().trim();
        String lightType = (String) getViewDataBinding().srHighMastLightType.getTag();
        String height = Objects.requireNonNull(getViewDataBinding().etHighMastLightHeight.getText()).toString().trim();
        String noOfBulb = Objects.requireNonNull(getViewDataBinding().etHighMastNoOfBulb.getText()).toString().trim();
        String workingStatus = (String) getViewDataBinding().srHighMastLightWorkingStatus.getTag();
        String vendor = Objects.requireNonNull(getViewDataBinding().etHighMastLightVendor.getText()).toString().trim();
        String expiryDate = Objects.requireNonNull(getViewDataBinding().etHighMastExpiryDate.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, address, fundedBy, lightType, height, noOfBulb, workingStatus, vendor, expiryDate, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, address, fundedBy, lightType, height, noOfBulb, workingStatus, vendor, expiryDate, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, address, fundedBy, lightType, height, noOfBulb, workingStatus, vendor, expiryDate, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case HIGH_MAST_LOCATION_ERROR:
                getViewDataBinding().layoutHighMastLocation.setError(error);
                getViewDataBinding().layoutHighMastLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastLocation, getViewDataBinding().layoutHighMastLocation);
                break;
            case HIGH_MAST_ADDRESS_ERROR:
                getViewDataBinding().layoutHighMastAddress.setError(error);
                getViewDataBinding().layoutHighMastAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastAddress, getViewDataBinding().layoutHighMastAddress);
                break;
            case HIGH_MAST_FUNDED_BY_ERROR:
                getViewDataBinding().layoutHighMastFundedBy.setError(error);
                getViewDataBinding().layoutHighMastFundedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastFundedBy, getViewDataBinding().layoutHighMastFundedBy);
                break;
            case HIGH_MAST_LIGHT_BY_ERROR:
                getViewDataBinding().layoutHighMastLightType.setError(error);
                getViewDataBinding().layoutHighMastLightType.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastLightType, getViewDataBinding().layoutHighMastLightType);
                break;
            case HIGH_MAST_HEIGHT_ERROR:
                getViewDataBinding().layoutHighMastLightHeight.setError(error);
                getViewDataBinding().layoutHighMastLightHeight.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastLightHeight, getViewDataBinding().layoutHighMastLightHeight);
                break;
            case HIGH_MAST_NO_OF_BULBS_ERROR:
                getViewDataBinding().layoutHighMastNoOfBulb.setError(error);
                getViewDataBinding().layoutHighMastNoOfBulb.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastNoOfBulb, getViewDataBinding().layoutHighMastNoOfBulb);
                break;
            case HIGH_MAST_WORKING_STATUS_ERROR:
                getViewDataBinding().layoutHighMastWorkingStatus.setError(error);
                getViewDataBinding().layoutHighMastWorkingStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastWorkingStatus, getViewDataBinding().layoutHighMastWorkingStatus);
                break;
            case HIGH_MAST_VENDOR_ERROR:
                getViewDataBinding().layoutHighMastVendor.setError(error);
                getViewDataBinding().layoutHighMastVendor.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastVendor, getViewDataBinding().layoutHighMastVendor);
                break;
            case HIGH_MAST_EXPIRY_DATE_ERROR:
                getViewDataBinding().layoutHighMastExpiryDate.setError(error);
                getViewDataBinding().layoutHighMastExpiryDate.getParent().requestChildFocus
                        (getViewDataBinding().layoutHighMastExpiryDate, getViewDataBinding().layoutHighMastExpiryDate);
                break;
            case HIGH_MAST_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case HIGH_MAST_COMMON_ERROR:
                showToast(error);
                break;
        }

    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutHighMastLocation.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastAddress.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastFundedBy.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastLightType.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastLightHeight.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastNoOfBulb.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastWorkingStatus.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastVendor.setErrorEnabled(false);
        getViewDataBinding().layoutHighMastExpiryDate.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_high_mast);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srHighMastLightType.setAdapter(highMastLightTypeAdapter);
        getViewDataBinding().srHighMastLightWorkingStatus.setAdapter(highMastLightWorkingStatusAdapter);
        viewModel.loadData();
    }

}