package in.ults.gisurvey.ui.main.utility.streetlight;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityStreetLightDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class StreetLightFragment extends BaseFragment<FragmentUtilityStreetLightDetailsBinding, StreetLightViewModel> implements StreetLightNavigator {

    public static final String TAG = StreetLightFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private StreetLightViewModel viewModel;
    @Inject
    CommonDropDownAdapter streetLightBulbTypeAdapter;
    @Inject
    CommonDropDownAdapter streetLightWorkingStatusAdapter;

    private String imagePath1 = "";
    static final int STREET_LIGHT_LOCATION_ERROR = 1;
    static final int STREET_LIGHT_ADDRESS_ERROR = 2;
    static final int STREET_LIGHT_FUNDED_BY_ERROR = 3;
    static final int STREET_LIGHT_BULB_TYPE_ERROR = 4;
    static final int STREET_LIGHT_WORKING_STATUS_ERROR = 5;
    static final int STREET_LIGHT_POST_NO_ERROR = 6;
    static final int STREET_LIGHT_REMARKS_ERROR = 7;
    static final int STREET_LIGHT_COMMON_ERROR = 8;

    public static StreetLightFragment newInstance() {
        Bundle args = new Bundle();
        StreetLightFragment fragment = new StreetLightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_street_light_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public StreetLightViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StreetLightViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etStreetLightLocation.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewDataBinding().etStreetLightAddress.getText()).toString().trim();
        String fundedBy = Objects.requireNonNull(getViewDataBinding().etStreetLightFundedBy.getText()).toString().trim();
        String bulbType = (String) getViewDataBinding().srStreetLightBulbType.getTag();
        String workingStatus = (String) getViewDataBinding().srStreetLightWorkingStatus.getTag();
        String postNo = Objects.requireNonNull(getViewDataBinding().etStreetLightPostNo.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, address, fundedBy, bulbType, workingStatus, postNo, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, address, fundedBy, bulbType, workingStatus, postNo, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, address, fundedBy, bulbType, workingStatus, postNo, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case STREET_LIGHT_LOCATION_ERROR:
                getViewDataBinding().layoutStreetLightLocation.setError(error);
                getViewDataBinding().layoutStreetLightLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightLocation, getViewDataBinding().layoutStreetLightLocation);
                break;
            case STREET_LIGHT_ADDRESS_ERROR:
                getViewDataBinding().layoutStreetLightAddress.setError(error);
                getViewDataBinding().layoutStreetLightAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightAddress, getViewDataBinding().layoutStreetLightAddress);
                break;
            case STREET_LIGHT_FUNDED_BY_ERROR:
                getViewDataBinding().layoutStreetLightFundedBy.setError(error);
                getViewDataBinding().layoutStreetLightFundedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightFundedBy, getViewDataBinding().layoutStreetLightFundedBy);
                break;
            case STREET_LIGHT_BULB_TYPE_ERROR:
                getViewDataBinding().layoutStreetLightBulbType.setError(error);
                getViewDataBinding().layoutStreetLightBulbType.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightBulbType, getViewDataBinding().layoutStreetLightBulbType);
                break;
            case STREET_LIGHT_WORKING_STATUS_ERROR:
                getViewDataBinding().layoutStreetLightWorkingStatus.setError(error);
                getViewDataBinding().layoutStreetLightWorkingStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightWorkingStatus, getViewDataBinding().layoutStreetLightWorkingStatus);
                break;
            case STREET_LIGHT_POST_NO_ERROR:
                getViewDataBinding().layoutStreetLightPostNo.setError(error);
                getViewDataBinding().layoutStreetLightPostNo.getParent().requestChildFocus
                        (getViewDataBinding().layoutStreetLightPostNo, getViewDataBinding().layoutStreetLightPostNo);
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
        getViewDataBinding().layoutStreetLightLocation.setErrorEnabled(false);
        getViewDataBinding().layoutStreetLightAddress.setErrorEnabled(false);
        getViewDataBinding().layoutStreetLightFundedBy.setErrorEnabled(false);
        getViewDataBinding().layoutStreetLightBulbType.setErrorEnabled(false);
        getViewDataBinding().layoutStreetLightWorkingStatus.setErrorEnabled(false);
        getViewDataBinding().layoutStreetLightPostNo.setErrorEnabled(false);
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
        getViewDataBinding().srStreetLightBulbType.setAdapter(streetLightBulbTypeAdapter);
        getViewDataBinding().srStreetLightWorkingStatus.setAdapter(streetLightWorkingStatusAdapter);
        viewModel.loadData();
    }

}