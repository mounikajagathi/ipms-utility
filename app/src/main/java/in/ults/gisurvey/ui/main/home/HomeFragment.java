package in.ults.gisurvey.ui.main.home;


import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.databinding.FragmentHomeBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.main.utility.UtilityActivity;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private HomeViewModel viewModel;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }


    @Override
    public HomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_home);
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.updateContent();
    }


    @Override
    public void navigateToPartialSurvey() {
        getBaseActivity().showPartialSurvey(true);
    }

    @Override
    public void navigateToNewSurvey() {
        startActivity(UtilityActivity.newIntent(getBaseActivity(), false));
    }

    /**
     * to show home dashboard with actual data
     */
    @Override
    public void showHomeDashboard() {
        getViewDataBinding().homeActualData.setVisibility(View.VISIBLE);
        getViewDataBinding().homeNoData.setVisibility(View.GONE);
        getViewDataBinding().homeProgressBar.setVisibility(View.GONE);
    }

    /**
     * to show progressbar in home
     */
    @Override
    public void showHomeProgress() {
        getViewDataBinding().homeActualData.setVisibility(View.GONE);
        getViewDataBinding().homeNoData.setVisibility(View.GONE);
        getViewDataBinding().homeProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * to show home with no data
     */
    @Override
    public void showHomeNoData() {
        getViewDataBinding().homeActualData.setVisibility(View.GONE);
        getViewDataBinding().homeNoData.setVisibility(View.VISIBLE);
        getViewDataBinding().homeProgressBar.setVisibility(View.GONE);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.fetchDashBoardContent();

        getViewDataBinding().btnNoDataRetry.setOnClickListener(v -> {
            viewModel.fetchDashBoardContent();
        });

    }

    @Override
    public void onApiFailure() {
        super.onApiFailure();
        setDashboardContent();
    }


    @Override
    public void setDashboardContent() {
        Dashboard data = viewModel.getDashBoard();
        if (data != null) {
            getViewModel().setAssignedSurveyCount(data.assignedSurveys);
            showHomeDashboard();
        } else {
            showHomeNoData();
        }


    }
}