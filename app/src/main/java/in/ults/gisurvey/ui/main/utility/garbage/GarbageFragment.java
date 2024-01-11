package in.ults.gisurvey.ui.main.utility.garbage;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityGarbageDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class GarbageFragment extends BaseFragment<FragmentUtilityGarbageDetailsBinding, GarbageViewModel> implements GarbageNavigator {

    public static final String TAG = GarbageFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private GarbageViewModel viewModel;

    @Inject
    CommonDropDownAdapter garbageCategoryAdapter;

    public static GarbageFragment newInstance() {
        Bundle args = new Bundle();
        GarbageFragment fragment = new GarbageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_garbage_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public GarbageViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(GarbageViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {

    }

    @Override
    public void validationError(int code, String error) {

    }

    @Override
    public void clearValidationErrors() {

    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility_garbage_point);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srGarbageCategory.setAdapter(garbageCategoryAdapter);
        viewModel.loadData();
    }

}