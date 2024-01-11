package in.ults.gisurvey.ui.survey.basement;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.data.model.db.BasementAreaItem;
import in.ults.gisurvey.data.model.db.Survey;
import in.ults.gisurvey.databinding.FragmentBasementBinding;
import in.ults.gisurvey.ui.base.BaseFragment;

import static in.ults.gisurvey.utils.CommonUtils.disableChildView;


public class BasementFragment extends BaseFragment<FragmentBasementBinding, BasementViewModel> implements BasementNavigator {

    public static final String TAG = BasementFragment.class.getSimpleName();

    private BasementViewModel viewModel;

    @Inject
    public BasementAreaAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    ViewModelProviderFactory factory;

    static final int BASEMENT_AREA_LIST = 1;


    public static BasementFragment newInstance() {
        Bundle args = new Bundle();
        BasementFragment fragment = new BasementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basement;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public BasementViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(BasementViewModel.class);
        return viewModel;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_basement);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().rvBasementArea.setLayoutManager(layoutManager);
        getViewDataBinding().rvBasementArea.setAdapter(adapter);
        if(!viewModel.isSurveyOpenEditMode()){
            //If showing screen only for view(Completed survey)
            //Disable all child views
            disableChildView(getViewDataBinding().layoutContainer);
            adapter.updateSurveyOpenEditMode(false);
        }
        viewModel.getCurrentSurvey();
    }

    /**
     * set validation error message
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        if (code == BASEMENT_AREA_LIST) {
            adapter.setValidation(true);
        }
    }

    @Override
    public void clearValidationErrors() {
        adapter.setValidation(false);
    }

    @Override
    public void setCachedData(Survey survey) {
        int basementCount = survey.getGroundFloor();
        ArrayList<BasementAreaItem> data = survey.getBasementArea();
        if(data!=null && data.size()>0) {
            adapter.setDataList(data);
        }else{
            adapter.setBasementCount(basementCount);
        }
    }

    /**
     * to validate fields and save basement area
     */
    @Override
    public void saveBasementArea() {
        getBaseActivity().hideKeyboard();
        if (adapter != null && adapter.getDataList() != null) {
            if (viewModel.validateFields(adapter.getDataList())) {
                viewModel.saveBasementArea(adapter.getDataList());
            }
        }
    }

    @Override
    public void navigateToNextPage() {
        getBaseActivity().showLocationFragment(true);
    }

    @Override
    public void onFragmentBackPressed() {
        goBackFromSurvey();
    }

}