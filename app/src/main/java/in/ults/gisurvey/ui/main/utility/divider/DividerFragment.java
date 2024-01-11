package in.ults.gisurvey.ui.main.utility.divider;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityDividerDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class DividerFragment extends BaseFragment<FragmentUtilityDividerDetailsBinding, DividerViewModel> implements DividerNavigator {

    public static final String TAG = DividerFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private DividerViewModel viewModel;
    private String imagePath1 = "";
    static final int DIVIDER_PLACE_ERROR = 1;
    static final int DIVIDER_ROAD_NAME_ERROR = 2;
    static final int DIVIDER_LENGTH_ERROR = 3;
    static final int DIVIDER_WIDTH_ERROR = 4;
    static final int DIVIDER_MATERIAL_ERROR = 5;
    static final int DIVIDER_START_END_ERROR = 6;
    static final int DIVIDER_REMARKS_ERROR = 7;
    static final int DIVIDER_COMMON_ERROR = 8;
    @Inject
    CommonDropDownAdapter dividerMaterialAdapter;

    public static DividerFragment newInstance() {
        Bundle args = new Bundle();
        DividerFragment fragment = new DividerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_divider_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public DividerViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(DividerViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String place = Objects.requireNonNull(getViewDataBinding().etDividerPlace.getText()).toString().trim();
        String roadName = Objects.requireNonNull(getViewDataBinding().etDividerRoadName.getText()).toString().trim();
        String length = Objects.requireNonNull(getViewDataBinding().etDividerLength.getText()).toString().trim();
        String width = Objects.requireNonNull(getViewDataBinding().etDividerWidth.getText()).toString().trim();
        String material = (String) getViewDataBinding().srDividerMaterial.getTag();
        String startEnd = Objects.requireNonNull(getViewDataBinding().etDividerStartEnd.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(place, roadName,length, width, material, startEnd, remarks,imagePath1)) {
                viewModel.saveUtilityDetails(place, roadName,length, width, material, startEnd, remarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(place, roadName,length, width, material, startEnd, remarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case DIVIDER_PLACE_ERROR:
                getViewDataBinding().layoutDividerPlace.setError(error);
                getViewDataBinding().layoutDividerPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerPlace, getViewDataBinding().layoutDividerPlace);
                break;
                case DIVIDER_ROAD_NAME_ERROR:
                getViewDataBinding().layoutDividerRoadName.setError(error);
                getViewDataBinding().layoutDividerRoadName.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerRoadName, getViewDataBinding().layoutDividerRoadName);
                break;
                case DIVIDER_LENGTH_ERROR:
                getViewDataBinding().layoutDividerLength.setError(error);
                getViewDataBinding().layoutDividerLength.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerLength, getViewDataBinding().layoutDividerLength);
                break;

            case DIVIDER_WIDTH_ERROR:
                getViewDataBinding().layoutDividerWidth.setError(error);
                getViewDataBinding().layoutDividerWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerWidth, getViewDataBinding().layoutDividerWidth);
                break;
                case DIVIDER_MATERIAL_ERROR:
                getViewDataBinding().layoutDividerMaterial.setError(error);
                getViewDataBinding().layoutDividerMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerMaterial, getViewDataBinding().layoutDividerMaterial);
                break;
            case DIVIDER_START_END_ERROR:
                getViewDataBinding().layoutDividerStartEnd.setError(error);
                getViewDataBinding().layoutDividerStartEnd.getParent().requestChildFocus
                        (getViewDataBinding().layoutDividerStartEnd, getViewDataBinding().layoutDividerStartEnd);
                break;
            case DIVIDER_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case DIVIDER_COMMON_ERROR:
                showToast(error);
                break;
        }

    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutDividerRoadName.setErrorEnabled(false);
        getViewDataBinding().layoutDividerPlace.setErrorEnabled(false);
        getViewDataBinding().layoutDividerWidth.setErrorEnabled(false);
        getViewDataBinding().layoutDividerLength.setErrorEnabled(false);
        getViewDataBinding().layoutDividerStartEnd.setErrorEnabled(false);
        getViewDataBinding().layoutDividerMaterial.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_divider);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srDividerMaterial.setAdapter(dividerMaterialAdapter);
        viewModel.loadData();
    }

}