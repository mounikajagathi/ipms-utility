package in.ults.gisurvey.ui.survey.groundfloorselection;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentGroundFloorSelectionBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;

public class GroundFloorSelectionFragment extends BaseFragment<FragmentGroundFloorSelectionBinding, GroundFloorSelectionViewModel> implements GroundFloorSelectionNavigator {

    public static final String TAG = GroundFloorSelectionFragment.class.getSimpleName();

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    GroundFloorSelectionAdapter mGroundFloorSelectionAdapter;

    private GroundFloorSelectionViewModel viewModel;

    public static GroundFloorSelectionFragment newInstance() {
        Bundle args = new Bundle();
        GroundFloorSelectionFragment fragment = new GroundFloorSelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ground_floor_selection;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public GroundFloorSelectionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(GroundFloorSelectionViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_ground_floor_selection);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            mGroundFloorSelectionAdapter.updateSurveyOpenEditMode(false);
        }
        mLayoutManager.setReverseLayout(true);
        getViewDataBinding().buildingLayout.setLayoutManager(mLayoutManager);
        getViewDataBinding().buildingLayout.setAdapter(mGroundFloorSelectionAdapter);
        mGroundFloorSelectionAdapter.setClickListener(position -> viewModel.clearFloorRelatedData());
        viewModel.getCurrentSurvey();
    }

    /**
     * to set ground floor
     */
    @Override
    public void saveGroundFloorCount() {
        int groundFloor = -1;
        if (mGroundFloorSelectionAdapter != null) {
            groundFloor = mGroundFloorSelectionAdapter.getSelectedPosition();
        }
        if (groundFloor > -1) {
            viewModel.insertGroundFloor(groundFloor);
        } else {
            getBaseActivity().showToast(R.string.error_ground_floor);
        }
    }

    @Override
    public void navigateToLocationPage() {
        getBaseActivity().showLocationFragment(true);
    }

    @Override
    public void navigateToBasementPage() {
        getBaseActivity().showBasementFragment(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

}
