package in.ults.gisurvey.ui.survey.road;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Owner;
import in.ults.gisurvey.data.model.db.Road;
import in.ults.gisurvey.databinding.FragmentRoadBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.owner.OwnerListAdapter;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class RoadFragment extends BaseFragment<FragmentRoadBinding, RoadViewModel> implements RoadNavigator {

    public static final String TAG = RoadFragment.class.getSimpleName();

    static final int NEAR_ROAD_ERROR = 1;
    static final int ROAD_TYPE_ERROR = 2;
    static final int ROAD_WIDTH_ERROR = 3;

    private RoadViewModel viewModel;


    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CommonDropDownAdapter roadTypeAdapter;

    @Inject
    CommonDropDownAdapter roadWidthAdapter;

    @Inject
    RoadListAdapter roadListAdapter;

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
        return R.layout.fragment_road;
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
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_road);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
        getViewDataBinding().etNearRoad.setAdapter(roadListAdapter);
        getViewDataBinding().etRoadType.setAdapter(roadTypeAdapter);
        getViewDataBinding().etRoadWidth.setAdapter(roadWidthAdapter);
        getViewDataBinding().etNearRoad.setOnItemClickListener((parent, view, position, id) -> {
            Road road = roadListAdapter.getItem(position);
            if (road != null) {
                viewModel.setRoadListData(road.getNearRoad(), road.getRoadType(), road.getRoadWidth());
            }
        });
        viewModel.loadData();
        viewModel.getCurrentSurveyProperty();
        viewModel.getRoadList();

    }

    /**
     * to validate fields and save road details
     */
    @Override
    public void saveRoadDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String nearRoad = Objects.requireNonNull(getViewDataBinding().etNearRoad.getText()).toString().trim();
        String roadType = Objects.requireNonNull(getViewDataBinding().etRoadType.getText()).toString().trim();
        String roadWidth = Objects.requireNonNull(getViewDataBinding().etRoadWidth.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(nearRoad, roadType, roadWidth)) {
                viewModel.saveRoadDetails(nearRoad, roadType, roadWidth,true);
            }

        }else {
            //Partial Saving
            //No need od validation
            viewModel.saveRoadDetails(nearRoad, roadType, roadWidth,false);
        }

    }

    /**
     * set validation error message
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case NEAR_ROAD_ERROR:
                getViewDataBinding().layoutNearRoad.setError(error);
                getViewDataBinding().layoutNearRoad.getParent().requestChildFocus
                        (getViewDataBinding().layoutNearRoad,getViewDataBinding().layoutNearRoad);
                break;
            case ROAD_TYPE_ERROR:
                getViewDataBinding().layoutRoadType.setError(error);
                getViewDataBinding().layoutRoadType.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadType,getViewDataBinding().layoutRoadType);
                break;
            case ROAD_WIDTH_ERROR:
                getViewDataBinding().layoutRoadWidth.setError(error);
                getViewDataBinding().layoutRoadWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadWidth,getViewDataBinding().layoutRoadWidth);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutNearRoad.setErrorEnabled(false);
        getViewDataBinding().layoutRoadType.setErrorEnabled(false);
        getViewDataBinding().layoutRoadWidth.setErrorEnabled(false);
    }


    @Override
    public void navigateToImageDetails() {
        getBaseActivity().showImageFragment(true);
    }

    @Override
    public void navigateToScreenSelection() {
        super.onFragmentBackPressed();
    }

    @Override
    public void disablePartialSave() {
        getViewDataBinding().btnPartialSave.setEnabled(false);
        getViewDataBinding().btnPartialSave.setBackgroundColor(getResources().getColor(R.color.cmn_inactive_btn_color));
    }


    @Override
    public void navigateToTenantPage() {
        getBaseActivity().showTenantFragment(true);
    }

    @Override
    public void navigateToTaxPage() {
        getBaseActivity().showTaxFragment(true);
    }

    @Override
    public void navigateToEstablishmentPage() {
        getBaseActivity().showEstablishmentFragment(true);
    }

    @Override
    public void navigateToMemberPage() {
        getBaseActivity().showMemberFragment(true);
    }

    @Override
    public void navigateToLiveHoodPage() {
        getBaseActivity().showLiveHoodFragment(true);
    }

    @Override
    public void navigateToMorePage() {
        getBaseActivity().showMoreFragment(true);
    }

    @Override
    public void navigateToBuildingPage() {
        getBaseActivity().showBuildingFragment(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
}