package in.ults.gisurvey.ui.main.ward;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.flexbox.FlexboxLayoutManager;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentWardSelectionBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;

/**
 * Created by anuag on 10-07-2020
 **/
public class WardSelectionFragment extends BaseFragment<FragmentWardSelectionBinding, WardSelectionViewModel> implements WardSelectionNavigator {
    public static final String TAG = WardSelectionFragment.class.getSimpleName();
    static final int COMMON_ERROR = 1;
    private WardSelectionViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FlexRecyclerViewMultiSelectAdapter wardsAdapter;

    @Inject
    FlexboxLayoutManager WardNumberLayoutManager;


    public static WardSelectionFragment newInstance() {
        Bundle args = new Bundle();
        WardSelectionFragment fragment = new WardSelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ward_selection;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public WardSelectionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(WardSelectionViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);

        getViewDataBinding().rvWards.setAdapter(wardsAdapter);
        getViewDataBinding().rvWards.setLayoutManager(WardNumberLayoutManager);
        viewModel.loadData();
        wardsAdapter.setSelectedData(viewModel.getSelectedWardsForSurveyPoints());
    }

    @Override
    public void saveWards() {
        if (viewModel.validation(wardsAdapter.getSelectedData())) {
            viewModel.saveData(wardsAdapter.getSelectedData());
        }
    }



    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
//        getViewDataBinding().layoutSurveyorName.setErrorEnabled(false);
    }

    @Override
    public void onGettingSurveyPoints() {
        super.onFragmentBackPressed();
    }
}

