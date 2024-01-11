package in.ults.gisurvey.ui.main.surveyor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentSurveyorListBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

public class SurveyorFragment extends BaseFragment<FragmentSurveyorListBinding, SurveyorViewModel> implements SurveyorNavigator {
    public static final String TAG = SurveyorFragment.class.getSimpleName();

    private SurveyorViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    SurveyorListAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    public static SurveyorFragment newInstance() {
        Bundle args = new Bundle();
        SurveyorFragment fragment = new SurveyorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_surveyor_list;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SurveyorViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SurveyorViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_surveyor);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvSurveyorList.setLayoutManager(layoutManager);
        getViewDataBinding().rvSurveyorList.setAdapter(adapter);
        viewModel.setSurveyorList();

    }
}
