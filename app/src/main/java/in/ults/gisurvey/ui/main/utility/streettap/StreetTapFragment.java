package in.ults.gisurvey.ui.main.utility.streettap;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityStreetTapDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class StreetTapFragment extends BaseFragment<FragmentUtilityStreetTapDetailsBinding, StreetTapViewModel> implements StreetTapNavigator {

    public static final String TAG = StreetTapFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private StreetTapViewModel viewModel;
    private String imagePath1 = "";
    static final int STREET_LIGHT_LOCATION_ERROR = 1;
    static final int STREET_LIGHT_ADDRESS_ERROR = 2;
    static final int STREET_LIGHT_FUNDED_BY_ERROR = 3;
    static final int STREET_LIGHT_WORKING_STATUS_ERROR = 4;
    static final int STREET_LIGHT_REMARKS_ERROR = 5;
    static final int STREET_LIGHT_COMMON_ERROR = 6;

    @Inject
    CommonDropDownAdapter streetTapWorkingStatusAdapter;

    public static StreetTapFragment newInstance() {
        Bundle args = new Bundle();
        StreetTapFragment fragment = new StreetTapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_street_tap_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public StreetTapViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StreetTapViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etStreetTapLocation.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewDataBinding().etStreetTapAddress.getText()).toString().trim();
        String fundedBy = Objects.requireNonNull(getViewDataBinding().etStreetTapFundedBy.getText()).toString().trim();
        String workingStatus = (String) getViewDataBinding().srStreetTapWorkingStatus.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, address, fundedBy, workingStatus, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, address, fundedBy, workingStatus, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, address, fundedBy, workingStatus, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case STREET_LIGHT_LOCATION_ERROR:
                getViewDataBinding().layoutStreetTapLocation.setError(error);
                getViewDataBinding().layoutStreetTapLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetTapLocation, getViewDataBinding().layoutStreetTapLocation);
                break;
            case STREET_LIGHT_ADDRESS_ERROR:
                getViewDataBinding().layoutStreetTapAddress.setError(error);
                getViewDataBinding().layoutStreetTapAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetTapAddress, getViewDataBinding().layoutStreetTapAddress);
                break;
            case STREET_LIGHT_FUNDED_BY_ERROR:
                getViewDataBinding().layoutStreetTapFundedBy.setError(error);
                getViewDataBinding().layoutStreetTapFundedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetTapFundedBy, getViewDataBinding().layoutStreetTapFundedBy);
                break;
            case STREET_LIGHT_WORKING_STATUS_ERROR:
                getViewDataBinding().layoutStreetTapWorkingStatus.setError(error);
                getViewDataBinding().layoutStreetTapWorkingStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetTapWorkingStatus, getViewDataBinding().layoutStreetTapWorkingStatus);
                break;
            case STREET_LIGHT_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case STREET_LIGHT_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutStreetTapLocation.setErrorEnabled(false);
        getViewDataBinding().layoutStreetTapAddress.setErrorEnabled(false);
        getViewDataBinding().layoutStreetTapFundedBy.setErrorEnabled(false);
        getViewDataBinding().layoutStreetTapWorkingStatus.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_street_light);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srStreetTapWorkingStatus.setAdapter(streetTapWorkingStatusAdapter);
        viewModel.loadData();
    }

}