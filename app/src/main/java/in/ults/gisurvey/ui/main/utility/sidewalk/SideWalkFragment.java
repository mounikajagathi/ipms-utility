package in.ults.gisurvey.ui.main.utility.sidewalk;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityRoadSidewalkBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class SideWalkFragment extends BaseFragment<FragmentUtilityRoadSidewalkBinding, SideWalkViewModel> implements SideWalkNavigator {

    public static final String TAG = SideWalkFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SideWalkViewModel viewModel;

    private String imagePath1 = "";
    static final int SIDE_WALK_STREET_NAME_ERROR = 1;
    static final int SIDE_WALK_LENGTH_ERROR = 2;
    static final int SIDE_WALK_WIDTH_ERROR = 3;
    static final int SIDE_WALK_START_END_ERROR = 4;
    static final int SIDE_WALK_PLACEMENT_ERROR = 5;
    static final int SIDE_WALK_REMARKS_ERROR = 6;
    static final int SIDE_WALK_COMMON_ERROR = 7;

    @Inject
    CommonDropDownAdapter sideWalkPlacementAdapter;

    public static SideWalkFragment newInstance() {
        Bundle args = new Bundle();
        SideWalkFragment fragment = new SideWalkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_road_sidewalk;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SideWalkViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SideWalkViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String streetName = Objects.requireNonNull(getViewDataBinding().etSideWalkRoadName.getText()).toString().trim();
        String length = Objects.requireNonNull(getViewDataBinding().etSideWalkLength.getText()).toString().trim();
        String width = Objects.requireNonNull(getViewDataBinding().etSideWalkWidth.getText()).toString().trim();
        String startEnd = Objects.requireNonNull(getViewDataBinding().etSideWalkStartEnd.getText()).toString().trim();
        String placement = (String) getViewDataBinding().srSideWalkPlacement.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(streetName, length, width, startEnd, placement, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(streetName, length, width, startEnd, placement, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(streetName, length, width, startEnd, placement, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case SIDE_WALK_STREET_NAME_ERROR:
                getViewDataBinding().layoutSideWalkStreetName.setError(error);
                getViewDataBinding().layoutSideWalkStreetName.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWalkStreetName, getViewDataBinding().layoutSideWalkStreetName);
                break;
            case SIDE_WALK_LENGTH_ERROR:
                getViewDataBinding().layoutSideWalkLength.setError(error);
                getViewDataBinding().layoutSideWalkLength.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWalkLength, getViewDataBinding().layoutSideWalkLength);
                break;
            case SIDE_WALK_WIDTH_ERROR:
                getViewDataBinding().layoutSideWalkWidth.setError(error);
                getViewDataBinding().layoutSideWalkWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWalkWidth, getViewDataBinding().layoutSideWalkWidth);
                break;
            case SIDE_WALK_START_END_ERROR:
                getViewDataBinding().layoutSideWalkStartEnd.setError(error);
                getViewDataBinding().layoutSideWalkStartEnd.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWalkStartEnd, getViewDataBinding().layoutSideWalkStartEnd);
                break;
            case SIDE_WALK_PLACEMENT_ERROR:
                getViewDataBinding().layoutSideWalkPlacement.setError(error);
                getViewDataBinding().layoutSideWalkPlacement.getParent().requestChildFocus
                        (getViewDataBinding().layoutSideWalkPlacement, getViewDataBinding().layoutSideWalkPlacement);
                break;
            case SIDE_WALK_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case SIDE_WALK_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutSideWalkStreetName.setErrorEnabled(false);
        getViewDataBinding().layoutSideWalkLength.setErrorEnabled(false);
        getViewDataBinding().layoutSideWalkWidth.setErrorEnabled(false);
        getViewDataBinding().layoutSideWalkStartEnd.setErrorEnabled(false);
        getViewDataBinding().layoutSideWalkPlacement.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_side_walk);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srSideWalkPlacement.setAdapter(sideWalkPlacementAdapter);
        viewModel.loadData();
    }

}