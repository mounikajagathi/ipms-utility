package in.ults.gisurvey.ui.survey.floorcount;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentFloorCountBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class FloorCountFragment extends BaseFragment<FragmentFloorCountBinding, FloorCountViewModel> implements FloorCountNavigator {

    public static final String TAG = FloorCountFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private FloorCountViewModel viewModel;
    private int floorCount = -1;
    private int propertyCount = -1;

    public static FloorCountFragment newInstance() {
        Bundle args = new Bundle();
        FloorCountFragment fragment = new FloorCountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_floor_count;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public FloorCountViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(FloorCountViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_floor_count);
    }


    @Override
    public void navigateToNextPage() {
        getBaseActivity().showGroundFloorSelection(true);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
        }
//        viewModel.getCurrentSurvey();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCurrentSurvey();
    }

    /**
     * here  save floor and property count
     */
    @Override
    public void saveFloorPropertyCount() {
        viewModel.saveFloorPropertyCount(Integer.parseInt(getViewDataBinding().txtFloorCount.getText().toString()),
                Integer.parseInt(getViewDataBinding().txtPropertyCount.getText().toString()));
    }

    /**
     * to update the  floor count
     */
    @Override
    public void increaseFloorCount() {
        int value = Integer.parseInt(getViewDataBinding().txtFloorCount.getText().toString());
        if (value > 0) {
            viewModel.setFloorCount(value + 1);
        }
    }

    /**
     * to decrease the floor count and update with new floor count
     */
    @Override
    public void decreaseFloorCount() {
        int value = Integer.parseInt(getViewDataBinding().txtFloorCount.getText().toString());
        if (value > 1) {
            if (floorCount!=-1 && value - 1 < floorCount) {
                showDialog(null, getString(R.string.msg_change_floor_count),
                        getString(android.R.string.yes), (dialog, which) -> {
                    viewModel.setFloorCount(value - 1);
                    floorCount=-1;
                }, getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
            } else {
                viewModel.setFloorCount(value - 1);
            }
        }
    }

    /**
     * to increase the property count
     */
    @Override
    public void increasePropertyCount() {
        int value = Integer.parseInt(getViewDataBinding().txtPropertyCount.getText().toString());
        if (value > 0) {
            viewModel.setPropertyCount(value + 1);
        }
    }

    /**
     * to decrease property count and update with new count
     */
    @Override
    public void decreasePropertyCount() {
        int value = Integer.parseInt(getViewDataBinding().txtPropertyCount.getText().toString());
        if (value > 1) {
            if (propertyCount!=-1 && value - 1 < propertyCount) {
                showInfoDialog(getString(R.string.msg_change_property_count));
          } else {
                viewModel.setPropertyCount(value - 1);
            }
        }
    }

    /**
     * to get current floor and property count from db
     * @param floorCount
     * @param propertyCount
     */
    @Override
    public void setCount(int floorCount, int propertyCount) {
        this.floorCount = floorCount == -1 ? 1 : floorCount;
        this.propertyCount = propertyCount == -1 ? 1 : propertyCount;
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }
}