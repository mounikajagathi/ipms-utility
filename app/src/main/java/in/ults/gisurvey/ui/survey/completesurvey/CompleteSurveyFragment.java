package in.ults.gisurvey.ui.survey.completesurvey;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentCompleteSurveyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridFragment;


public class CompleteSurveyFragment extends BaseFragment<FragmentCompleteSurveyBinding, CompleteSurveyViewModel> implements CompleteSurveyNavigator {

    public static final String TAG = CompleteSurveyFragment.class.getSimpleName();


    @Inject
    ViewModelProviderFactory factory;
    private CompleteSurveyViewModel viewModel;

    public static CompleteSurveyFragment newInstance() {
        Bundle args = new Bundle();
        CompleteSurveyFragment fragment = new CompleteSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_complete_survey;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public CompleteSurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CompleteSurveyViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_complete_survey);
    }

    @Override
    public void completeSurvey() {
        getBaseActivity().finish();
    }

    @Override
    public void savePropertySurvey() {
        getBaseActivity().popBackStackAfterTag(SurveyGridFragment.TAG);
    }

    @Override
    public void onFragmentBackPressed() {
    }
}