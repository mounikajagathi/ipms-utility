package in.ults.gisurvey.ui.main.utility.road;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentRoadBindingImpl;
import in.ults.gisurvey.databinding.FragmentUtilityRoadDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class RoadFragment extends BaseFragment<FragmentUtilityRoadDetailsBinding, RoadViewModel> implements RoadNavigator {

    public static final String TAG = RoadFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private RoadViewModel viewModel;

    private String imagePath1 = "";
    static final int ROAD_NAME_ERROR = 1;
    static final int ROAD_START_POINT_ERROR = 2;
    static final int ROAD_END_POINT_ERROR = 3;
    static final int ROAD_SURFACE_TYPE_ERROR = 4;
    static final int ROAD_CATEGORY_ERROR = 5;
    static final int ROAD_MAINTAINED_BY_ERROR = 6;
    static final int ROAD_LENGTH_ERROR = 7;
    static final int ROAD_WIDTH_ERROR = 8;
    static final int ROAD_CARRIAGE_WIDTH_ERROR = 9;
    static final int ROAD_FOOTPATH_ERROR = 10;
    static final int ROAD_FOOTPATH_CONST_MAT_ERROR = 11;
    static final int ROAD_FOOTPATH_WIDTH_ERROR = 12;
    static final int ROAD_REMARKS_ERROR = 13;
    static final int ROAD_COMMON_ERROR = 14;

    @Inject
    CommonDropDownAdapter roadMaterialAdapter;
    @Inject
    CommonDropDownAdapter roadCategoryAdapter;
    @Inject
    CommonDropDownAdapter maintainedByAdapter;
    @Inject
    CommonDropDownAdapter footpathConsMatAdapter;

    public static RoadFragment newInstance() {
        Bundle args = new Bundle();
        RoadFragment fragment = new RoadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_road_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public RoadViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(RoadViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String roadName = Objects.requireNonNull(getViewDataBinding().etRURoadName.getText()).toString().trim();
        String startPoint = Objects.requireNonNull(getViewDataBinding().etRUStartPoint.getText()).toString().trim();
        String endPoint = Objects.requireNonNull(getViewDataBinding().etRUEndPoint.getText()).toString().trim();
        String surfaceType = (String) getViewDataBinding().srRURoadMaterial.getTag();
        String roadCategory = (String)getViewDataBinding().srRURoadCategory.getTag();
        String maintainedBy = (String) getViewDataBinding().srRUMaintainedBy.getTag();
        String length = Objects.requireNonNull(getViewDataBinding().etRULength.getText()).toString().trim();
        String width = Objects.requireNonNull(getViewDataBinding().etRURoadwayWidth.getText()).toString().trim();
        String carriageWidth = Objects.requireNonNull(getViewDataBinding().etRUCarriageWidth.getText()).toString().trim();
        boolean footpath = getViewDataBinding().cbFootPath.isChecked();
        String footpathConsMat = (String) getViewDataBinding().srFootpathConsMat.getTag();
        String footpathWidth = Objects.requireNonNull(getViewDataBinding().etRUFootpathWidth.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRURemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(roadName,startPoint, endPoint, surfaceType,roadCategory, maintainedBy, length, width, carriageWidth, footpath, footpathConsMat, footpathWidth, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(roadName,startPoint, endPoint, surfaceType, roadCategory, maintainedBy, length, width, carriageWidth, footpath, footpathConsMat, footpathWidth, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(roadName,startPoint, endPoint, surfaceType,roadCategory, maintainedBy, length, width, carriageWidth, footpath, footpathConsMat, footpathWidth, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case ROAD_NAME_ERROR:
                getViewDataBinding().layoutRURoadName.setError(error);
                getViewDataBinding().layoutRURoadName.getParent().requestChildFocus
                        (getViewDataBinding().layoutRURoadName, getViewDataBinding().layoutRURoadName);
                break;
            case ROAD_START_POINT_ERROR:
                getViewDataBinding().layoutRUStartPoint.setError(error);
                getViewDataBinding().layoutRUStartPoint.getParent().requestChildFocus
                        (getViewDataBinding().layoutRUStartPoint, getViewDataBinding().layoutRUStartPoint);
                break;
            case ROAD_END_POINT_ERROR:
                getViewDataBinding().layoutRUEndPoint.setError(error);
                getViewDataBinding().layoutRUEndPoint.getParent().requestChildFocus
                        (getViewDataBinding().layoutRUEndPoint, getViewDataBinding().layoutRUEndPoint);
                break;
            case ROAD_SURFACE_TYPE_ERROR:
                getViewDataBinding().layoutRURoadMaterial.setError(error);
                getViewDataBinding().layoutRURoadMaterial.getParent().requestChildFocus
                        (getViewDataBinding().layoutRURoadMaterial, getViewDataBinding().layoutRURoadMaterial);
                break;
            case ROAD_CATEGORY_ERROR:
                getViewDataBinding().layoutRURoadCategory.setError(error);
                getViewDataBinding().layoutRURoadCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutRURoadCategory, getViewDataBinding().layoutRURoadCategory);
                break;
            case ROAD_MAINTAINED_BY_ERROR:
                getViewDataBinding().layoutRUMaintainedBy.setError(error);
                getViewDataBinding().layoutRUMaintainedBy.getParent().requestChildFocus
                        (getViewDataBinding().layoutRUMaintainedBy, getViewDataBinding().layoutRUMaintainedBy);
                break;
            case ROAD_LENGTH_ERROR:
                getViewDataBinding().layoutRULength.setError(error);
                getViewDataBinding().layoutRULength.getParent().requestChildFocus
                        (getViewDataBinding().layoutRULength, getViewDataBinding().layoutRULength);
                break;
            case ROAD_WIDTH_ERROR:
                getViewDataBinding().layoutRURoadwayWidth.setError(error);
                getViewDataBinding().layoutRURoadwayWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutRURoadwayWidth, getViewDataBinding().layoutRURoadwayWidth);
                break;
            case ROAD_CARRIAGE_WIDTH_ERROR:
                getViewDataBinding().layoutRUCarriageWidth.setError(error);
                getViewDataBinding().layoutRUCarriageWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutRUCarriageWidth, getViewDataBinding().layoutRUCarriageWidth);
                break;
            case ROAD_FOOTPATH_ERROR:
                getViewDataBinding().cbFootPath.setError(error);
                getViewDataBinding().cbFootPath.getParent().requestChildFocus
                        (getViewDataBinding().cbFootPath, getViewDataBinding().cbFootPath);
                break;
            case ROAD_FOOTPATH_CONST_MAT_ERROR:
                getViewDataBinding().layoutFootpathConsMat.setError(error);
                getViewDataBinding().layoutFootpathConsMat.getParent().requestChildFocus
                        (getViewDataBinding().layoutFootpathConsMat, getViewDataBinding().layoutFootpathConsMat);
                break;
            case ROAD_FOOTPATH_WIDTH_ERROR:
                getViewDataBinding().layoutRUFootpathWidth.setError(error);
                getViewDataBinding().layoutRUFootpathWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutRUFootpathWidth, getViewDataBinding().layoutRUFootpathWidth);
                break;
            case ROAD_REMARKS_ERROR:
                getViewDataBinding().layoutRURemarks.setError(error);
                getViewDataBinding().layoutRURemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRURemarks, getViewDataBinding().layoutRURemarks);
                break;
            case ROAD_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutRURoadName.setErrorEnabled(false);
        getViewDataBinding().layoutRUStartPoint.setErrorEnabled(false);
        getViewDataBinding().layoutRUEndPoint.setErrorEnabled(false);
        getViewDataBinding().layoutRURoadMaterial.setErrorEnabled(false);
        getViewDataBinding().layoutRURoadCategory.setErrorEnabled(false);
        getViewDataBinding().layoutRUMaintainedBy.setErrorEnabled(false);
        getViewDataBinding().layoutRULength.setErrorEnabled(false);
        getViewDataBinding().layoutRURoadwayWidth.setErrorEnabled(false);
        getViewDataBinding().layoutRUCarriageWidth.setErrorEnabled(false);
        getViewDataBinding().layoutFootpathConsMat.setErrorEnabled(false);
        getViewDataBinding().layoutRUFootpathWidth.setErrorEnabled(false);
        getViewDataBinding().layoutRURemarks.setErrorEnabled(false);
    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility_road);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srFootpathConsMat.setAdapter(footpathConsMatAdapter);
        getViewDataBinding().srRUMaintainedBy.setAdapter(maintainedByAdapter);
        getViewDataBinding().srRURoadCategory.setAdapter(roadCategoryAdapter);
        getViewDataBinding().srRURoadMaterial.setAdapter(roadMaterialAdapter);
        viewModel.loadData();
    }

}