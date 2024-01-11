package in.ults.gisurvey.ui.main.utility.map;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class MapFragment extends BaseFragment<FragmentProfileBinding, MapViewModel> implements MapNavigator {

    public static final String TAG = MapFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private MapViewModel viewModel;

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public MapViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(MapViewModel.class);
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
        return getString(R.string.toolbar_profile);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().btnSave.setOnClickListener(v -> getBaseActivity().onBackPressed());
    }

}