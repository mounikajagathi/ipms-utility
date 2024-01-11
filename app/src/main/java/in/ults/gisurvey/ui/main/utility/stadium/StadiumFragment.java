package in.ults.gisurvey.ui.main.utility.stadium;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityStadiumDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class StadiumFragment extends BaseFragment<FragmentUtilityStadiumDetailsBinding, StadiumViewModel> implements StadiumNavigator {

    public static final String TAG = StadiumFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private StadiumViewModel viewModel;

    private String imagePath1 = "";
    static final int STADIUM_NAME_ERROR = 1;
    static final int STADIUM_LOCATION_ERROR = 2;
    static final int STADIUM_ADDRESS_ERROR = 3;
    static final int STADIUM_NUMBER_GALLERY_ERROR = 4;
    static final int STADIUM_CAPACITY_ERROR = 5;
    static final int STADIUM_AREA_ERROR = 6;
    static final int STADIUM_REMARKS_ERROR = 7;
    static final int STADIUM_COMMON_ERROR = 8;

    public static StadiumFragment newInstance() {
        Bundle args = new Bundle();
        StadiumFragment fragment = new StadiumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_stadium_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public StadiumViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StadiumViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String name = Objects.requireNonNull(getViewDataBinding().etStadiumName.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewDataBinding().etLocation.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewDataBinding().etAddress.getText()).toString().trim();
        String numberGallery = Objects.requireNonNull(getViewDataBinding().etNoGallery.getText()).toString().trim();
        String capacity = Objects.requireNonNull(getViewDataBinding().etGalleryCoverage.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewDataBinding().etStadiumArea.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(name, location, address, numberGallery, capacity, area, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(name, location, address, numberGallery, capacity, area, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(name, location, address, numberGallery, capacity, area, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case STADIUM_NAME_ERROR:
                getViewDataBinding().layoutStadiumName.setError(error);
                getViewDataBinding().layoutStadiumName.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumName, getViewDataBinding().layoutStadiumName);
                break;
            case STADIUM_LOCATION_ERROR:
                getViewDataBinding().layoutStadiumLocation.setError(error);
                getViewDataBinding().layoutStadiumLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumLocation, getViewDataBinding().layoutStadiumLocation);
                break;
            case STADIUM_ADDRESS_ERROR:
                getViewDataBinding().layoutStadiumAddress.setError(error);
                getViewDataBinding().layoutStadiumAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumAddress, getViewDataBinding().layoutStadiumAddress);
                break;
            case STADIUM_NUMBER_GALLERY_ERROR:
                getViewDataBinding().layoutStadiumNoGallery.setError(error);
                getViewDataBinding().layoutStadiumNoGallery.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumNoGallery, getViewDataBinding().layoutStadiumNoGallery);
                break;
            case STADIUM_CAPACITY_ERROR:
                getViewDataBinding().layoutStadiumGalleryCapacity.setError(error);
                getViewDataBinding().layoutStadiumGalleryCapacity.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumGalleryCapacity, getViewDataBinding().layoutStadiumGalleryCapacity);
                break;
            case STADIUM_AREA_ERROR:
                getViewDataBinding().layoutStadiumArea.setError(error);
                getViewDataBinding().layoutStadiumArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutStadiumArea, getViewDataBinding().layoutStadiumArea);
                break;
            case STADIUM_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case STADIUM_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutStadiumName.setErrorEnabled(false);
        getViewDataBinding().layoutStadiumLocation.setErrorEnabled(false);
        getViewDataBinding().layoutStadiumAddress.setErrorEnabled(false);
        getViewDataBinding().layoutStadiumNoGallery.setErrorEnabled(false);
        getViewDataBinding().layoutStadiumGalleryCapacity.setErrorEnabled(false);
        getViewDataBinding().layoutStadiumArea.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_stadium);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
    }

}