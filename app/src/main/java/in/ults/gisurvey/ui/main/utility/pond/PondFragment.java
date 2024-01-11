package in.ults.gisurvey.ui.main.utility.pond;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityPondDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class PondFragment extends BaseFragment<FragmentUtilityPondDetailsBinding, PondViewModel> implements PondNavigator {

    public static final String TAG = PondFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private PondViewModel viewModel;

    private String imagePath1 = "";
    static final int POND_NAME_ERROR = 1;
    static final int POND_LOCATION_ERROR = 2;
    static final int POND_AREA_ERROR = 3;
    static final int POND_CAPACITY_ERROR = 4;
    static final int POND_USAGE_ERROR = 5;
    static final int POND_ODOUR_ERROR = 6;
    static final int POND_POND_STATUS_ERROR = 7;
    static final int POND_TYPE_ERROR = 8;
    static final int POND_PRESENT_CONDITION_ERROR = 9;
    static final int POND_NATURE_ERROR = 10;
    static final int POND_SIDEWALL_ERROR = 11;
    static final int POND_SIDEWALL_TYPE_ERROR = 12;
    static final int POND_CONDITION_ERROR = 13;
    static final int POND_WIDTH_ERROR = 14;
    static final int POND_LENGTH_ERROR = 15;
    static final int POND_OWNER_ERROR = 16;
    static final int POND_PURPOSE_ERROR = 17;
    static final int POND_SURVEY_NO_ERROR = 18;
    static final int POND_COLOR_ERROR = 19;
    static final int POND_MAINTAIN_BY_ERROR = 20;
    static final int POND_REMARKS_ERROR = 21;
    static final int POND_COMMON_ERROR = 22;

    @Inject
    CommonDropDownAdapter pondUsageAdapter;
    @Inject
    CommonDropDownAdapter pondOdourAdapter;
    @Inject
    CommonDropDownAdapter pondStatusAdapter;
    @Inject
    CommonDropDownAdapter pondTypeAdapter;
    @Inject
    CommonDropDownAdapter pondPresentConditionAdapter;
    @Inject
    CommonDropDownAdapter pondNatureAdapter;
    @Inject
    CommonDropDownAdapter pondColourAdapter;

    public static PondFragment newInstance() {
        Bundle args = new Bundle();
        PondFragment fragment = new PondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_pond_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public PondViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PondViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String pondName = Objects.requireNonNull(getViewDataBinding().etPondName.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewDataBinding().etLocation.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewDataBinding().etArea.getText()).toString().trim();
        String capacity = Objects.requireNonNull(getViewDataBinding().etCapacity.getText()).toString().trim();
        String usage = (String) getViewDataBinding().srUsage.getTag();
        String odour = (String) getViewDataBinding().srOdour.getTag();
        String pondStatus = (String) getViewDataBinding().srPondStatus.getTag();
        String pondType = (String) getViewDataBinding().srPondType.getTag();
        String presentCondition = (String) getViewDataBinding().srPresentCondition.getTag();
        String nature = (String) getViewDataBinding().srNature.getTag();
        String sidewall = Objects.requireNonNull(getViewDataBinding().etSideWall.getText()).toString().trim();
        String sidewallType = Objects.requireNonNull(getViewDataBinding().etSideWallType.getText()).toString().trim();
        String pondCondition = Objects.requireNonNull(getViewDataBinding().etPondCondition.getText()).toString().trim();
        String pondWidth = Objects.requireNonNull(getViewDataBinding().etPondWidth.getText()).toString().trim();
        String pondLength = Objects.requireNonNull(getViewDataBinding().etPondLength.getText()).toString().trim();
        String pondOwner = Objects.requireNonNull(getViewDataBinding().etPondOwner.getText()).toString().trim();
        String purpose = Objects.requireNonNull(getViewDataBinding().etPurpose.getText()).toString().trim();
        String surveyNo = Objects.requireNonNull(getViewDataBinding().etSurveyNo.getText()).toString().trim();
        String color = (String) getViewDataBinding().srColour.getTag();
        String maintainBy = Objects.requireNonNull(getViewDataBinding().etMaintainBy.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(pondName, location, area, capacity, usage, odour, pondStatus, pondType, presentCondition, nature, sidewall, sidewallType, pondCondition, pondWidth, pondLength, pondOwner, purpose, surveyNo, color, maintainBy, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(pondName, location, area, capacity, usage, odour, pondStatus, pondType, presentCondition, nature, sidewall, sidewallType, pondCondition, pondWidth, pondLength, pondOwner, purpose, surveyNo, color, maintainBy, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(pondName, location, area, capacity, usage, odour, pondStatus, pondType, presentCondition, nature, sidewall, sidewallType, pondCondition, pondWidth, pondLength, pondOwner, purpose, surveyNo, color, maintainBy, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case POND_NAME_ERROR:
                getViewDataBinding().layoutPondName.setError(error);
                getViewDataBinding().layoutPondName.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondName, getViewDataBinding().layoutPondName);
                break;
            case POND_LOCATION_ERROR:
                getViewDataBinding().layoutLocation.setError(error);
                getViewDataBinding().layoutLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutLocation, getViewDataBinding().layoutLocation);
                break;
            case POND_AREA_ERROR:
                getViewDataBinding().layoutArea.setError(error);
                getViewDataBinding().layoutArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutArea, getViewDataBinding().layoutArea);
                break;
            case POND_CAPACITY_ERROR:
                getViewDataBinding().layoutCapacity.setError(error);
                getViewDataBinding().layoutCapacity.getParent().requestChildFocus
                        (getViewDataBinding().layoutCapacity, getViewDataBinding().layoutCapacity);
                break;
            case POND_USAGE_ERROR:
                getViewDataBinding().layoutUsage.setError(error);
                getViewDataBinding().layoutUsage.getParent().requestChildFocus
                        (getViewDataBinding().layoutUsage, getViewDataBinding().layoutUsage);
                break;
            case POND_ODOUR_ERROR:
                getViewDataBinding().layoutOdour.setError(error);
                getViewDataBinding().layoutOdour.getParent().requestChildFocus
                        (getViewDataBinding().layoutOdour, getViewDataBinding().layoutOdour);
                break;
            case POND_POND_STATUS_ERROR:
                getViewDataBinding().layoutPondStatus.setError(error);
                getViewDataBinding().layoutPondStatus.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondStatus, getViewDataBinding().layoutPondStatus);
                break;
            case POND_TYPE_ERROR:
                getViewDataBinding().layoutPondType.setError(error);
                getViewDataBinding().layoutPondType.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondType, getViewDataBinding().layoutPondType);
                break;
            case POND_PRESENT_CONDITION_ERROR:
                getViewDataBinding().layoutPresentCondition.setError(error);
                getViewDataBinding().layoutPresentCondition.getParent().requestChildFocus
                        (getViewDataBinding().layoutPresentCondition, getViewDataBinding().layoutPresentCondition);
                break;
            case POND_NATURE_ERROR:
                getViewDataBinding().layoutNature.setError(error);
                getViewDataBinding().layoutNature.getParent().requestChildFocus
                        (getViewDataBinding().layoutNature, getViewDataBinding().layoutNature);
                break;
            case POND_SIDEWALL_ERROR:
                getViewDataBinding().layoutSideWall.setError(error);
                getViewDataBinding().layoutSideWall.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWall, getViewDataBinding().layoutSideWall);
                break;
            case POND_SIDEWALL_TYPE_ERROR:
                getViewDataBinding().layoutSideWallType.setError(error);
                getViewDataBinding().layoutSideWallType.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWallType, getViewDataBinding().layoutSideWallType);
                break;
            case POND_CONDITION_ERROR:
                getViewDataBinding().layoutPondCondition.setError(error);
                getViewDataBinding().layoutPondCondition.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondCondition, getViewDataBinding().layoutPondCondition);
                break;
            case POND_LENGTH_ERROR:
                getViewDataBinding().layoutPondLength.setError(error);
                getViewDataBinding().layoutPondLength.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondLength, getViewDataBinding().layoutPondLength);
                break;
            case POND_WIDTH_ERROR:
                getViewDataBinding().layoutPondWidth.setError(error);
                getViewDataBinding().layoutPondWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondWidth, getViewDataBinding().layoutPondWidth);
                break;
            case POND_OWNER_ERROR:
                getViewDataBinding().layoutPondOwner.setError(error);
                getViewDataBinding().layoutPondOwner.getParent().requestChildFocus
                        (getViewDataBinding().layoutPondOwner, getViewDataBinding().layoutPondOwner);
                break;
            case POND_PURPOSE_ERROR:
                getViewDataBinding().layoutPurpose.setError(error);
                getViewDataBinding().layoutPurpose.getParent().requestChildFocus
                        (getViewDataBinding().layoutPurpose, getViewDataBinding().layoutPurpose);
                break;
            case POND_SURVEY_NO_ERROR:
                getViewDataBinding().layoutSurveyNo.setError(error);
                getViewDataBinding().layoutSurveyNo.getParent().requestChildFocus
                        (getViewDataBinding().layoutSurveyNo, getViewDataBinding().layoutSurveyNo);
                break;
            case POND_COLOR_ERROR:
                getViewDataBinding().layoutColour.setError(error);
                getViewDataBinding().layoutColour.getParent().requestChildFocus
                        (getViewDataBinding().layoutColour, getViewDataBinding().layoutColour);
                break;
            case POND_MAINTAIN_BY_ERROR:
                getViewDataBinding().layoutMaintainBy.setError(error);
                getViewDataBinding().layoutMaintainBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutMaintainBy, getViewDataBinding().layoutMaintainBy);
                break;
            case POND_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case POND_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutLocation.setErrorEnabled(false);
        getViewDataBinding().layoutPondName.setErrorEnabled(false);
        getViewDataBinding().layoutArea.setErrorEnabled(false);
        getViewDataBinding().layoutCapacity.setErrorEnabled(false);
        getViewDataBinding().layoutUsage.setErrorEnabled(false);
        getViewDataBinding().layoutOdour.setErrorEnabled(false);
        getViewDataBinding().layoutPondStatus.setErrorEnabled(false);
        getViewDataBinding().layoutPondType.setErrorEnabled(false);
        getViewDataBinding().layoutPresentCondition.setErrorEnabled(false);
        getViewDataBinding().layoutNature.setErrorEnabled(false);
        getViewDataBinding().layoutSideWall.setErrorEnabled(false);
        getViewDataBinding().layoutSideWallType.setErrorEnabled(false);
        getViewDataBinding().layoutPondCondition.setErrorEnabled(false);
        getViewDataBinding().layoutPondWidth.setErrorEnabled(false);
        getViewDataBinding().layoutPondLength.setErrorEnabled(false);
        getViewDataBinding().layoutPondOwner.setErrorEnabled(false);
        getViewDataBinding().layoutPurpose.setErrorEnabled(false);
        getViewDataBinding().layoutSurveyNo.setErrorEnabled(false);
        getViewDataBinding().layoutColour.setErrorEnabled(false);
        getViewDataBinding().layoutMaintainBy.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_pond);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srColour.setAdapter(pondColourAdapter);
        getViewDataBinding().srNature.setAdapter(pondNatureAdapter);
        getViewDataBinding().srOdour.setAdapter(pondOdourAdapter);
        getViewDataBinding().srPondType.setAdapter(pondTypeAdapter);
        getViewDataBinding().srPondStatus.setAdapter(pondStatusAdapter);
        getViewDataBinding().srPresentCondition.setAdapter(pondPresentConditionAdapter);
        getViewDataBinding().srUsage.setAdapter(pondUsageAdapter);
        viewModel.loadData();
    }

}