package in.ults.gisurvey.ui.about;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.BuildConfig;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentAboutBinding;
import in.ults.gisurvey.ui.base.BaseFragment;



public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator {

    public static final String TAG = AboutFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private AboutViewModel viewModel;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_about);
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public AboutViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this,factory).get(AboutViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().txtVersion.setText(String.format("Version %s", BuildConfig.VERSION_NAME));
    }
}