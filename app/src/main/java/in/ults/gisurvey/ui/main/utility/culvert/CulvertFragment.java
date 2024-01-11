package in.ults.gisurvey.ui.main.utility.culvert;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityCulvertDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class CulvertFragment extends BaseFragment<FragmentUtilityCulvertDetailsBinding, CulvertViewModel> implements CulvertNavigator {

    public static final String TAG = CulvertFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private CulvertViewModel viewModel;
    @Inject
    CommonDropDownAdapter constructionMaterialAdapter;
    private String imagePath1 = "";
    static final int CULVERT_NAME_ERROR = 1;
    static final int CULVERT_PLACE_ERROR = 2;
    static final int CULVERT_CONST_MAT_ERROR = 3;
    static final int CULVERT_ROAD_NAME_ERROR = 4;
    static final int CULVERT_REMARKS_ERROR = 5;
    static final int CULVERT_COMMON_ERROR = 6;

    public static CulvertFragment newInstance() {
        Bundle args = new Bundle();
        CulvertFragment fragment = new CulvertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_culvert_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public CulvertViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CulvertViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String name = Objects.requireNonNull(getViewDataBinding().etCulvertName.getText()).toString().trim();
        String place = Objects.requireNonNull(getViewDataBinding().etCulvertPlace.getText()).toString().trim();
        String constMat = (String) getViewDataBinding().srConstructionMaterial.getTag();
        String roadName = Objects.requireNonNull(getViewDataBinding().etRoadName.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(name, place, constMat,roadName, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(name, place, constMat,roadName, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(name, place, constMat,roadName, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case CULVERT_NAME_ERROR:
                getViewDataBinding().layoutCulvertName.setError(error);
                getViewDataBinding().layoutCulvertName.getParent().requestChildFocus
                        (getViewDataBinding().layoutCulvertName, getViewDataBinding().layoutCulvertName);
                break;
            case CULVERT_PLACE_ERROR:
                getViewDataBinding().layoutCulvertPlace.setError(error);
                getViewDataBinding().layoutCulvertPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutCulvertPlace, getViewDataBinding().layoutCulvertPlace);
                break;
            case CULVERT_CONST_MAT_ERROR:
                getViewDataBinding().layoutConstructionMaterial.setError(error);
                getViewDataBinding().layoutConstructionMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutConstructionMaterial, getViewDataBinding().layoutConstructionMaterial);
                break;
            case CULVERT_ROAD_NAME_ERROR:
                getViewDataBinding().layoutRoadName.setError(error);
                getViewDataBinding().layoutRoadName.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadName, getViewDataBinding().layoutRoadName);
                break;
            case CULVERT_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case CULVERT_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutCulvertName.setErrorEnabled(false);
        getViewDataBinding().layoutRoadName.setErrorEnabled(false);
        getViewDataBinding().layoutConstructionMaterial.setErrorEnabled(false);
        getViewDataBinding().layoutCulvertPlace.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_culvert);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srConstructionMaterial.setAdapter(constructionMaterialAdapter);
        viewModel.loadData();
    }

}