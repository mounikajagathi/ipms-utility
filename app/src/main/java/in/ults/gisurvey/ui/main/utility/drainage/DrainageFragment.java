package in.ults.gisurvey.ui.main.utility.drainage;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityDrainageDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class DrainageFragment extends BaseFragment<FragmentUtilityDrainageDetailsBinding, DrainageViewModel> implements DrainageNavigator {

    public static final String TAG = DrainageFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private DrainageViewModel viewModel;

    @Inject
    CommonDropDownAdapter drainageMaterialAdapter;
    @Inject
    CommonDropDownAdapter drainageTypeAdapter;
    @Inject
    CommonDropDownAdapter drainagePlacementAdapter;
    private String imagePath1 = "";
    static final int DRAINAGE_NAME_ERROR = 1;
    static final int DRAINAGE_PLACE_ERROR = 2;
    static final int DRAINAGE_STREET_NAME_ERROR = 3;
    static final int DRAINAGE_MATERIAL_ERROR = 4;
    static final int DRAINAGE_TYPE_ERROR = 5;
    static final int DRAINAGE_LENGTH_ERROR = 6;
    static final int DRAINAGE_PLACEMENT_ERROR = 7;
    static final int DRAINAGE_CATEGORY_ERROR = 8;
    static final int DRAINAGE_REMARKS_ERROR = 9;
    static final int DRAINAGE_COMMON_ERROR = 10;

    public static DrainageFragment newInstance() {
        Bundle args = new Bundle();
        DrainageFragment fragment = new DrainageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_drainage_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public DrainageViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(DrainageViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String name = Objects.requireNonNull(getViewDataBinding().etDrainageName.getText()).toString().trim();
        String place = Objects.requireNonNull(getViewDataBinding().etPlace.getText()).toString().trim();
        String streetName = Objects.requireNonNull(getViewDataBinding().etDrainageStreet.getText()).toString().trim();
        String material = (String) getViewDataBinding().srDrainageMaterial.getTag();
        String type = (String) getViewDataBinding().srDrainageType.getTag();
        String length = Objects.requireNonNull(getViewDataBinding().etDrainageLength.getText()).toString().trim();
        String placement = (String) getViewDataBinding().srDrainagePlacement.getTag();
        String category = Objects.requireNonNull(getViewDataBinding().etDrainageCategory.getText()).toString().trim();
        String drainageRemarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(name, place, streetName, material, type, length, placement, category, drainageRemarks, imagePath1)) {
                viewModel.saveUtilityDetails(name, place, streetName, material, type, length, placement, category, drainageRemarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(name, place, streetName, material, type, length, placement, category, drainageRemarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case DRAINAGE_NAME_ERROR:
                getViewDataBinding().layoutDrainageName.setError(error);
                getViewDataBinding().layoutDrainageName.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageName, getViewDataBinding().layoutDrainageName);
                break;
            case DRAINAGE_PLACE_ERROR:
                getViewDataBinding().layoutPlace.setError(error);
                getViewDataBinding().layoutPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlace, getViewDataBinding().layoutPlace);
                break;
            case DRAINAGE_STREET_NAME_ERROR:
                getViewDataBinding().layoutDrainageStreet.setError(error);
                getViewDataBinding().layoutDrainageStreet.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageStreet, getViewDataBinding().layoutDrainageStreet);
                break;
            case DRAINAGE_MATERIAL_ERROR:
                getViewDataBinding().layoutDrainageMaterial.setError(error);
                getViewDataBinding().layoutDrainageMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageMaterial, getViewDataBinding().layoutDrainageMaterial);
                break;
            case DRAINAGE_TYPE_ERROR:
                getViewDataBinding().layoutDrainageType.setError(error);
                getViewDataBinding().layoutDrainageType.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageType, getViewDataBinding().layoutDrainageType);
                break;
            case DRAINAGE_LENGTH_ERROR:
                getViewDataBinding().layoutDrainageLength.setError(error);
                getViewDataBinding().layoutDrainageLength.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageLength, getViewDataBinding().layoutDrainageLength);
            case DRAINAGE_PLACEMENT_ERROR:
                getViewDataBinding().layoutDrainagePlacement.setError(error);
                getViewDataBinding().layoutDrainagePlacement.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainagePlacement, getViewDataBinding().layoutDrainagePlacement);
                break;
            case DRAINAGE_CATEGORY_ERROR:
                getViewDataBinding().layoutDrainageCategory.setError(error);
                getViewDataBinding().layoutDrainageCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutDrainageCategory, getViewDataBinding().layoutDrainageCategory);
                break;
            case DRAINAGE_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case DRAINAGE_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutDrainageName.setErrorEnabled(false);
        getViewDataBinding().layoutPlace.setErrorEnabled(false);
        getViewDataBinding().layoutDrainageType.setErrorEnabled(false);
        getViewDataBinding().layoutDrainageStreet.setErrorEnabled(false);
        getViewDataBinding().layoutDrainageMaterial.setErrorEnabled(false);
        getViewDataBinding().layoutDrainageLength.setErrorEnabled(false);
        getViewDataBinding().layoutDrainageCategory.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_drainage);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srDrainageMaterial.setAdapter(drainageMaterialAdapter);
        getViewDataBinding().srDrainagePlacement.setAdapter(drainagePlacementAdapter);
        getViewDataBinding().srDrainageType.setAdapter(drainageTypeAdapter);
        viewModel.loadData();
    }

}