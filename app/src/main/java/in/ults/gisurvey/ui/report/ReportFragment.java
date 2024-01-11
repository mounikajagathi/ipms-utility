package in.ults.gisurvey.ui.report;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import javax.inject.Inject;
import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentReportBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

public class ReportFragment extends BaseFragment<FragmentReportBinding, ReportViewModel> implements ReportNavigator {

    public static final String TAG = ReportFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private ReportViewModel viewModel;

    public static ReportFragment newInstance() {

        Bundle args = new Bundle();
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_daily_report);
    }

    @Override
    public void initNavigator() {

        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ReportViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ReportViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {

        super.setUp(savedInstanceState);
        viewModel.generateReport();

    }

    @Override
    public void onCloseButtonClick() {
        super.onFragmentBackPressed();
    }
}
