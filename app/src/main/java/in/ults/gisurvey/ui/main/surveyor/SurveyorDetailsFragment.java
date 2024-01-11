package in.ults.gisurvey.ui.main.surveyor;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentSurveyorDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.FlexRecyclerViewMultiSelectAdapter;

/**
 * Created by anuag on 10-07-2020
 **/
public class SurveyorDetailsFragment extends BaseFragment<FragmentSurveyorDetailsBinding, SurveyorDetailsViewModel> implements SurveyorDetailsNavigator {
    public static final String TAG = SurveyorDetailsFragment.class.getSimpleName();
    static final int SURVEYOR_NAME_ERROR = 1;
    static final int MOBILE_CODE_ERROR = 2;
    static final int COMMON_ERROR = 3;
    private SurveyorDetailsViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FlexRecyclerViewMultiSelectAdapter surveyorListAdapter;

    @Inject
    FlexboxLayoutManager layoutManagerSurveyorList;

    public static SurveyorDetailsFragment newInstance() {
        Bundle args = new Bundle();
        SurveyorDetailsFragment fragment = new SurveyorDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_surveyor_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SurveyorDetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SurveyorDetailsViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_surveyor);
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.loadData();
        getViewDataBinding().rvSurveyorName.setAdapter(surveyorListAdapter);
        getViewDataBinding().rvSurveyorName.setLayoutManager(layoutManagerSurveyorList);


    }


    @Override
    public void saveSurveyorName() {
        String mobileCode = Objects.requireNonNull(getViewDataBinding().etMobileCode.getText()).toString();
        ArrayList<String> selectedSurveyors = surveyorListAdapter.getSelectedData();
        if (viewModel.validateFields( mobileCode,selectedSurveyors)) {
            viewModel.saveData( mobileCode,selectedSurveyors);
            super.onFragmentBackPressed();
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case MOBILE_CODE_ERROR:
                getViewDataBinding().layoutMobileCode.setError(error);
                getViewDataBinding().layoutMobileCode.getParent().requestChildFocus
                        (getViewDataBinding().layoutMobileCode, getViewDataBinding().layoutMobileCode);
                break;
            case COMMON_ERROR:
                getBaseActivity().showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutMobileCode.setErrorEnabled(false);
    }

    @Override
    public void showProgress() {
        getViewDataBinding().actualContent.setVisibility(View.GONE);
        getViewDataBinding().homeProgressBar.setVisibility(View.VISIBLE);
        getViewDataBinding().txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        getViewDataBinding().actualContent.setVisibility(View.VISIBLE);
        getViewDataBinding().homeProgressBar.setVisibility(View.GONE);
        getViewDataBinding().txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        getViewDataBinding().actualContent.setVisibility(View.GONE);
        getViewDataBinding().homeProgressBar.setVisibility(View.GONE);
        getViewDataBinding().txtNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSelectedSurveyor(ArrayList<String> list) {
        surveyorListAdapter.setSelectedData(list);
    }
    @Override
    public void clearList() {

        surveyorListAdapter.setSelectedData(new ArrayList<String>());

    }
}

