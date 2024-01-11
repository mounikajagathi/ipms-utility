package in.ults.gisurvey.ui.main.utility.junction;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityRoadJunctionDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class JunctionFragment extends BaseFragment<FragmentUtilityRoadJunctionDetailsBinding, JunctionViewModel> implements JunctionNavigator {

    public static final String TAG = JunctionFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private JunctionViewModel viewModel;
    private String imagePath1 = "";
    static final int JUNCTION_NAME_ERROR = 1;
    static final int JUNCTION_LOCATION_ERROR = 2;
    static final int JUNCTION_NO_OF_ROADS_ERROR = 3;
    static final int JUNCTION_PEDESTRIAN_ERROR = 4;
    static final int JUNCTION_REMARKS_ERROR = 5;
    static final int JUNCTION_COMMON_ERROR = 6;
    @Inject
    CommonDropDownAdapter roadJunctionPedestrianAdapter;

    public static JunctionFragment newInstance() {
        Bundle args = new Bundle();
        JunctionFragment fragment = new JunctionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_road_junction_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public JunctionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(JunctionViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String name = Objects.requireNonNull(getViewDataBinding().etRoadJunctionName.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewDataBinding().etRoadJunctionLocation.getText()).toString().trim();
        String noOfRoads = Objects.requireNonNull(getViewDataBinding().etRoadJunctionNoRoad.getText()).toString().trim();
        String pedestrian = (String) getViewDataBinding().srRoadJunctionPedestrian.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(name, location, noOfRoads, pedestrian, remarks,imagePath1)) {
                viewModel.saveUtilityDetails(name, location, noOfRoads, pedestrian, remarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(name, location, noOfRoads, pedestrian, remarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case JUNCTION_NAME_ERROR:
                getViewDataBinding().layoutRoadJunctionName.setError(error);
                getViewDataBinding().layoutRoadJunctionName.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadJunctionName, getViewDataBinding().layoutRoadJunctionName);
                break;
            case JUNCTION_LOCATION_ERROR:
                getViewDataBinding().layoutRoadJunctionLocation.setError(error);
                getViewDataBinding().layoutRoadJunctionLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadJunctionLocation, getViewDataBinding().layoutRoadJunctionLocation);
                break;
            case JUNCTION_NO_OF_ROADS_ERROR:
                getViewDataBinding().layoutRoadJunctionNoRoad.setError(error);
                getViewDataBinding().layoutRoadJunctionNoRoad.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadJunctionNoRoad, getViewDataBinding().layoutRoadJunctionNoRoad);
                break;
            case JUNCTION_PEDESTRIAN_ERROR:
                getViewDataBinding().layoutRoadJunctionPedestrian.setError(error);
                getViewDataBinding().layoutRoadJunctionPedestrian.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadJunctionPedestrian, getViewDataBinding().layoutRoadJunctionPedestrian);
                break;
            case JUNCTION_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case JUNCTION_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutRoadJunctionName.setErrorEnabled(false);
        getViewDataBinding().layoutRoadJunctionLocation.setErrorEnabled(false);
        getViewDataBinding().layoutRoadJunctionNoRoad.setErrorEnabled(false);
        getViewDataBinding().layoutRoadJunctionPedestrian.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_junction);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srRoadJunctionPedestrian.setAdapter(roadJunctionPedestrianAdapter);
        viewModel.loadData();
    }

}