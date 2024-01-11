package in.ults.gisurvey.ui.serverSurvey.detials;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentServerSurveyDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsNavigator;
import in.ults.gisurvey.ui.main.surveyor.SurveyorDetailsViewModel;
import in.ults.gisurvey.ui.serverSurvey.list.ServerSurveyNavigator;

/**
 * Created by anuag on 30-10-2020
 **/
public class ServerSurveyDetailsFragment extends BaseFragment<FragmentServerSurveyDetailsBinding, ServerSurveyDetailsViewModel> implements ServerSurveyDetailsNavigator {
    public static final String TAG = ServerSurveyDetailsFragment.class.getSimpleName();
    private ServerSurveyDetailsViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;


    public static ServerSurveyDetailsFragment newInstance() {
        Bundle args = new Bundle();
        ServerSurveyDetailsFragment fragment = new ServerSurveyDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_server_survey_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ServerSurveyDetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ServerSurveyDetailsViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.loadData();

    }

    @Override
    public void close() {
        super.onFragmentBackPressed();
    }

}

