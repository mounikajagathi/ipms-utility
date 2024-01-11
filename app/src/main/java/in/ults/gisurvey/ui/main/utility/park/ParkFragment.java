package in.ults.gisurvey.ui.main.utility.park;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityParkDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class ParkFragment extends BaseFragment<FragmentUtilityParkDetailsBinding, ParkViewModel> implements ParkNavigator {

    public static final String TAG = ParkFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private ParkViewModel viewModel;

    private String imagePath1 = "";
    static final int PARK_LOCATION_ERROR = 1;
    static final int PARK_NAME_ERROR = 2;
    static final int PARK_AREA_ERROR = 3;
    static final int PARK_SURVEY_NUMBER_ERROR = 4;
    static final int PARK_TYPE_ERROR = 5;
    static final int PARK_REMARKS_ERROR = 6;
    static final int PARK_COMMON_ERROR = 7;

    @Inject
    CommonDropDownAdapter parkTypeAdapter;

    public static ParkFragment newInstance() {
        Bundle args = new Bundle();
        ParkFragment fragment = new ParkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_park_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public ParkViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ParkViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etParkLocation.getText()).toString().trim();
        String name = Objects.requireNonNull(getViewDataBinding().etParkName.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewDataBinding().etParkArea.getText()).toString().trim();
        String surveyNo = Objects.requireNonNull(getViewDataBinding().etParkSurveyNumber.getText()).toString().trim();
        String type = (String) getViewDataBinding().srParkType.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location,name, area, surveyNo,type, remarks,imagePath1)) {
                viewModel.saveUtilityDetails(location,name, area, surveyNo,type, remarks, imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location,name, area, surveyNo,type, remarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case PARK_LOCATION_ERROR:
                getViewDataBinding().layoutParkLocation.setError(error);
                getViewDataBinding().layoutParkLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkLocation, getViewDataBinding().layoutParkLocation);
                break;
            case PARK_NAME_ERROR:
                getViewDataBinding().layoutParkName.setError(error);
                getViewDataBinding().layoutParkName.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkName, getViewDataBinding().layoutParkName);
                break;
            case PARK_AREA_ERROR:
                getViewDataBinding().layoutParkArea.setError(error);
                getViewDataBinding().layoutParkArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkArea, getViewDataBinding().layoutParkArea);
                break;
            case PARK_SURVEY_NUMBER_ERROR:
                getViewDataBinding().layoutParkSurveyNo.setError(error);
                getViewDataBinding().layoutParkSurveyNo.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkSurveyNo, getViewDataBinding().layoutParkSurveyNo);
                break;
            case PARK_TYPE_ERROR:
                getViewDataBinding().layoutParkType.setError(error);
                getViewDataBinding().layoutParkType.getParent().requestChildFocus
                        (getViewDataBinding().layoutParkType, getViewDataBinding().layoutParkType);
                break;
            case PARK_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case PARK_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutParkLocation.setErrorEnabled(false);
        getViewDataBinding().layoutParkName.setErrorEnabled(false);
        getViewDataBinding().layoutParkArea.setErrorEnabled(false);
        getViewDataBinding().layoutParkSurveyNo.setErrorEnabled(false);
        getViewDataBinding().layoutParkType.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_park);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srParkType.setAdapter(parkTypeAdapter);
        viewModel.loadData();
    }

}