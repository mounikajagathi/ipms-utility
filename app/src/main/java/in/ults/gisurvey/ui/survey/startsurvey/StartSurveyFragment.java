package in.ults.gisurvey.ui.survey.startsurvey;


import android.os.Bundle;
import android.text.InputFilter;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentStartSurveyBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class StartSurveyFragment extends BaseFragment<FragmentStartSurveyBinding, StartSurveyViewModel> implements StartSurveyNavigator {

    public static final String TAG = StartSurveyFragment.class.getSimpleName();
    static final int VALIDATION_ERROR_Q_FIELD_1 = 1;
    static final int VALIDATION_ERROR_Q_FIELD_2 = 2;
    static final int VALIDATION_ERROR_Q_FIELD_3 = 3;
    private StartSurveyViewModel viewModel;


    @Inject
    ViewModelProviderFactory factory;

    public static StartSurveyFragment newInstance() {
        Bundle args = new Bundle();
        StartSurveyFragment fragment = new StartSurveyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_start_survey;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public StartSurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StartSurveyViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_map);
    }

    @Override
    public void validationError(int code, String error) {
//        if (code == VALIDATION_ERROR_Q_FIELD) {
//            getViewDataBinding().layoutQFieldId.setError(error);
//            getViewDataBinding().layoutQFieldId.getParent().requestChildFocus
//                    (getViewDataBinding().layoutQFieldId,getViewDataBinding().layoutQFieldId);
//        }
        switch (code){
            case VALIDATION_ERROR_Q_FIELD_1:
                getViewDataBinding().layoutQFieldId1.setError(error);
                getViewDataBinding().layoutQFieldId1.getParent().requestChildFocus(getViewDataBinding().layoutQFieldId1,getViewDataBinding().layoutQFieldId1);
                break;
            case VALIDATION_ERROR_Q_FIELD_2:
                getViewDataBinding().layoutQFieldId2.setError(error);
                getViewDataBinding().layoutQFieldId2.getParent().requestChildFocus(getViewDataBinding().layoutQFieldId2,getViewDataBinding().layoutQFieldId2);
                break;
            case VALIDATION_ERROR_Q_FIELD_3:
                getViewDataBinding().layoutQFieldId3.setError(error);
                getViewDataBinding().layoutQFieldId3.getParent().requestChildFocus(getViewDataBinding().layoutQFieldId3,getViewDataBinding().layoutQFieldId3);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutQFieldId1.setErrorEnabled(false);
        getViewDataBinding().layoutQFieldId2.setErrorEnabled(false);
        getViewDataBinding().layoutQFieldId3.setErrorEnabled(false);
    }

    /**
     * create survey if validation is success
     */
    @Override
    public void createSurvey() {
        getBaseActivity().hideKeyboard();
      //  String buildingID = Objects.requireNonNull(getViewDataBinding().etQFieldId.getText()).toString();
        String buildingID1 = Objects.requireNonNull(getViewDataBinding().etQFieldId1.getText().toString());
        String buildingID2 = Objects.requireNonNull(getViewDataBinding().etQFieldId2.getText().toString());
        String buildingID3 = Objects.requireNonNull(getViewDataBinding().etQFieldId3.getText().toString());
        if (viewModel.isValidationSuccess(buildingID1,buildingID2,buildingID3)) {
            viewModel.checkSurveyExists(buildingID1,buildingID2,buildingID3);
        }
    }

    /**
     * show edit dialog
     */
    @Override
    public void showEditDialog(String surveyId,String wardNumber) {
            showDialog(null, getString(R.string.msg_building_id), getString(R.string.cmn_edit), (dialog, which) -> viewModel.saveQFieldId(surveyId,wardNumber), getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
    }

    @Override
    public void showWarning() {
        showDialog(getString(R.string.cmn_warning), getString(R.string.msg_building_id_exist_on_server),"", null, getString(android.R.string.ok), (dialog, which) -> dialog.cancel());

    }

    /**
     * call to building check fragment
     */
    @Override
    public void navigateToNextPage() {
        getBaseActivity().showBuildingCheckFragment(true);
    }
}