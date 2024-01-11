package in.ults.gisurvey.ui.main.utility.well;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityWellDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class WellFragment extends BaseFragment<FragmentUtilityWellDetailsBinding, WellViewModel> implements WellNavigator {

    public static final String TAG = WellFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private WellViewModel viewModel;

    private String imagePath1 = "";
    static final int WELL_LOCATION_ERROR = 1;
    static final int WELL_OWNER_ERROR = 2;
    static final int WELL_PURPOSE_ERROR = 3;
    static final int WELL_COVER_ERROR = 4;
    static final int WELL_SURVEY_NO_ERROR = 5;
    static final int WELL_NEAR_ROAD_ERROR = 6;
    static final int WELL_RE_WATER_AVAIL_ERROR = 7;
    static final int WELL_STATUS_ERROR = 8;
    static final int WELL_TYPE_ERROR = 9;
    static final int WELL_REMARKS_ERROR = 10;
    static final int WELL_COMMON_ERROR = 11;

    @Inject
    CommonDropDownAdapter wellStatusAdapter;
    @Inject
    CommonDropDownAdapter wellTypeAdapter;

    public static WellFragment newInstance() {
        Bundle args = new Bundle();
        WellFragment fragment = new WellFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_well_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public WellViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(WellViewModel.class);
        return viewModel;
    }


    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etWellLocation.getText()).toString().trim();
        String wellOwner = Objects.requireNonNull(getViewDataBinding().etWellOwner.getText()).toString().trim();
        String purpose = Objects.requireNonNull(getViewDataBinding().etWellPurpose.getText()).toString().trim();
        String cover = Objects.requireNonNull(getViewDataBinding().etWellCover.getText()).toString().trim();
        String surveyNo = Objects.requireNonNull(getViewDataBinding().etWellSurveyNo.getText()).toString().trim();
        String nearRoad = Objects.requireNonNull(getViewDataBinding().etWellNearRoad.getText()).toString().trim();
        String reWaterAvail = Objects.requireNonNull(getViewDataBinding().etWellReWaterAvailabilityMarks.getText()).toString().trim();
        String status = (String) getViewDataBinding().srWellStatus.getTag();
        String wellType = (String) getViewDataBinding().srWellType.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, wellOwner, purpose, cover, surveyNo, nearRoad, reWaterAvail, status, wellType, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, wellOwner, purpose, cover, surveyNo, nearRoad, reWaterAvail, status, wellType, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, wellOwner, purpose, cover, surveyNo, nearRoad, reWaterAvail, status, wellType, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case WELL_LOCATION_ERROR:
                getViewDataBinding().layoutWellLocation.setError(error);
                getViewDataBinding().layoutWellLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellLocation, getViewDataBinding().layoutWellLocation);
                break;
            case WELL_OWNER_ERROR:
                getViewDataBinding().layoutWellOwner.setError(error);
                getViewDataBinding().layoutWellOwner.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellOwner, getViewDataBinding().layoutWellOwner);
                break;
            case WELL_PURPOSE_ERROR:
                getViewDataBinding().layoutWellPurpose.setError(error);
                getViewDataBinding().layoutWellPurpose.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellPurpose, getViewDataBinding().layoutWellPurpose);
                break;
            case WELL_COVER_ERROR:
                getViewDataBinding().layoutWellCover.setError(error);
                getViewDataBinding().layoutWellCover.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellCover, getViewDataBinding().layoutWellCover);
                break;
            case WELL_SURVEY_NO_ERROR:
                getViewDataBinding().layoutWellSurveyNo.setError(error);
                getViewDataBinding().layoutWellSurveyNo.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellSurveyNo, getViewDataBinding().layoutWellSurveyNo);
                break;
            case WELL_NEAR_ROAD_ERROR:
                getViewDataBinding().layoutWellNearRoad.setError(error);
                getViewDataBinding().layoutWellNearRoad.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellNearRoad, getViewDataBinding().layoutWellNearRoad);
                break;
            case WELL_RE_WATER_AVAIL_ERROR:
                getViewDataBinding().layoutWellReWaterAvailabilityMarks.setError(error);
                getViewDataBinding().layoutWellReWaterAvailabilityMarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellReWaterAvailabilityMarks, getViewDataBinding().layoutWellReWaterAvailabilityMarks);
                break;
            case WELL_STATUS_ERROR:
                getViewDataBinding().layoutWellStatus.setError(error);
                getViewDataBinding().layoutWellStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellStatus, getViewDataBinding().layoutWellStatus);
                break;
            case WELL_TYPE_ERROR:
                getViewDataBinding().layoutWellType.setError(error);
                getViewDataBinding().layoutWellType.getParent().requestChildFocus
                        (getViewDataBinding().layoutWellType, getViewDataBinding().layoutWellType);
                break;
            case WELL_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case WELL_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutWellLocation.setErrorEnabled(false);
        getViewDataBinding().layoutWellOwner.setErrorEnabled(false);
        getViewDataBinding().layoutWellPurpose.setErrorEnabled(false);
        getViewDataBinding().layoutWellCover.setErrorEnabled(false);
        getViewDataBinding().layoutWellSurveyNo.setErrorEnabled(false);
        getViewDataBinding().layoutWellNearRoad.setErrorEnabled(false);
        getViewDataBinding().layoutWellReWaterAvailabilityMarks.setErrorEnabled(false);
        getViewDataBinding().layoutWellStatus.setErrorEnabled(false);
        getViewDataBinding().layoutWellType.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_well);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srWellStatus.setAdapter(wellStatusAdapter);
        getViewDataBinding().srWellType.setAdapter(wellTypeAdapter);
        viewModel.loadData();
    }


}