package in.ults.gisurvey.ui.main.completed;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentCompletedSurveyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.SurveyActivity;


public class CompletedSurveyFragment extends BaseFragment<FragmentCompletedSurveyBinding, CompletedSurveyViewModel> implements CompletedSurveyNavigator {
    public static final String TAG = CompletedSurveyFragment.class.getSimpleName();

    private CompletedSurveyViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    CompletedSurveyListAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    public static CompletedSurveyFragment newInstance() {
        Bundle args = new Bundle();
        CompletedSurveyFragment fragment = new CompletedSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_completed_survey;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public CompletedSurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CompletedSurveyViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        if(viewModel.isCompleteListTypeSyncList()) {
            return getString(R.string.toolbar_synced_list);
        }
        return getString(R.string.toolbar_completed_list);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvCompletedSurvey.setLayoutManager(layoutManager);
        getViewDataBinding().rvCompletedSurvey.setAdapter(adapter);
        adapter.setViewItemClickListener(position -> viewModel.saveSurveyId(adapter.getItem(position).getSurveyID()));
    }

    @Override
    public void onResume() {
        super.onResume();
        //For showing both synced survey and unsynced survey we are using same fragment
        //instead of using seperate fragment
        if(viewModel.isCompleteListTypeSyncList()){
            viewModel.fetchCompletedDataWithSyncStatus(true);
        }else{
            viewModel.fetchCompletedDataWithSyncStatus(false);
        }

    }

    @Override
    public void navigateToNextPage() {
        startActivity(SurveyActivity.newIntent(getBaseActivity(), true));
    }
}