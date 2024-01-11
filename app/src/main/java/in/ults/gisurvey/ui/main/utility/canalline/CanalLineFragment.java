package in.ults.gisurvey.ui.main.utility.canalline;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityCanalLineDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class CanalLineFragment extends BaseFragment<FragmentUtilityCanalLineDetailsBinding, CanalLineViewModel> implements CanalLineNavigator {

    public static final String TAG = CanalLineFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private CanalLineViewModel viewModel;

    private String imagePath1 = "";
    static final int CANAL_STREET_NAME_ERROR = 1;
    static final int CANAL_LOCATION_ERROR = 2;
    static final int CANAL_TYPE_ERROR = 3;
    static final int CANAL_SUBTYPE_ERROR = 4;
    static final int CANAL_AREA_ERROR = 5;
    static final int CANAL_WIDTH_ERROR = 6;
    static final int CANAL_CONDITION_ERROR = 7;
    static final int CANAL_START_POINT_ERROR = 8;
    static final int CANAL_END_POINT_ERROR = 9;
    static final int CANAL_REMARKS_ERROR = 10;
    static final int CANAL_COMMON_ERROR = 11;


    @Inject
    CommonDropDownAdapter canalLineTypeAdapter;
    @Inject
    CommonDropDownAdapter canalLineSubTypeAdapter;
    @Inject
    CommonDropDownAdapter canalLineConditionAdapter;

    public static CanalLineFragment newInstance() {
        Bundle args = new Bundle();
        CanalLineFragment fragment = new CanalLineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_canal_line_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(),this);
    }

    @Override
    public CanalLineViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CanalLineViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String streetName = Objects.requireNonNull(getViewDataBinding().etCanalLineStreetName.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewDataBinding().etCanalLineLocation.getText()).toString().trim();
        String  type= (String) getViewDataBinding().srCanalLineType.getTag();
        String subtype = (String) getViewDataBinding().srCanalLineSubType.getTag();
        String area = Objects.requireNonNull(getViewDataBinding().etCanalLineArea.getText()).toString().trim();
        String width = Objects.requireNonNull(getViewDataBinding().etCanalLineWidth.getText()).toString().trim();
        String condition = (String) getViewDataBinding().srCanalLineCondition.getTag();
        String startPoint =Objects.requireNonNull(getViewDataBinding().etCanalLineStartPoint.getText()).toString().trim();
        String endPoint =Objects.requireNonNull(getViewDataBinding().etCanalLineEndPoint.getText()).toString().trim();
        String canalLineRemarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(streetName, location, type, subtype, area, width, condition, startPoint, endPoint, canalLineRemarks,imagePath1)) {
                viewModel.saveUtilityDetails(streetName, location, type, subtype, area, width, condition, startPoint, endPoint, canalLineRemarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(streetName, location, type, subtype, area, width, condition, startPoint, endPoint, canalLineRemarks,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case CANAL_STREET_NAME_ERROR:
                getViewDataBinding().layoutCanalLineStreetName.setError(error);
                getViewDataBinding().layoutCanalLineStreetName.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineStreetName, getViewDataBinding().layoutCanalLineStreetName);
                break;
            case CANAL_LOCATION_ERROR:
                getViewDataBinding().layoutCanalLineLocation.setError(error);
                getViewDataBinding().layoutCanalLineLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineLocation, getViewDataBinding().layoutCanalLineLocation);
                break;
            case CANAL_TYPE_ERROR:
                getViewDataBinding().layoutCanalLineType.setError(error);
                getViewDataBinding().layoutCanalLineType.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineType, getViewDataBinding().layoutCanalLineType);
                break;
            case CANAL_SUBTYPE_ERROR:
                getViewDataBinding().srCanalLineSubType.setError(error);
                getViewDataBinding().srCanalLineSubType.getParent().requestChildFocus
                        (getViewDataBinding().srCanalLineSubType, getViewDataBinding().srCanalLineSubType);
                break;
            case CANAL_AREA_ERROR:
                getViewDataBinding().layoutCanalLineArea.setError(error);
                getViewDataBinding().layoutCanalLineArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineArea, getViewDataBinding().layoutCanalLineArea);
                break;
            case CANAL_WIDTH_ERROR:
                getViewDataBinding().layoutCanalLineWidth.setError(error);
                getViewDataBinding().layoutCanalLineWidth.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineWidth, getViewDataBinding().layoutCanalLineWidth);
                break;
            case CANAL_CONDITION_ERROR:
                getViewDataBinding().layoutCanalLineCondition.setError(error);
                getViewDataBinding().layoutCanalLineCondition.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineCondition, getViewDataBinding().layoutCanalLineCondition);
                break;
            case CANAL_START_POINT_ERROR:
                getViewDataBinding().layoutCanalLineStartPoint.setError(error);
                getViewDataBinding().layoutCanalLineStartPoint.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineStartPoint, getViewDataBinding().layoutCanalLineStartPoint);
                break;
            case CANAL_END_POINT_ERROR:
                getViewDataBinding().layoutCanalLineEndPoint.setError(error);
                getViewDataBinding().layoutCanalLineEndPoint.getParent().requestChildFocus
                        (getViewDataBinding().layoutCanalLineEndPoint, getViewDataBinding().layoutCanalLineEndPoint);
                break;
            case CANAL_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case CANAL_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutCanalLineStreetName.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineLocation.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineType.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineSubType.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineArea.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineWidth.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineCondition.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineEndPoint.setErrorEnabled(false);
        getViewDataBinding().layoutCanalLineEndPoint.setErrorEnabled(false);
        getViewDataBinding().layoutRemarks.setErrorEnabled(false);
    }

    @Override
    public void setCachedData() {

    }

    @Override
    public void disablePartialSave() {

    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.toolbar_utility_canal);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srCanalLineCondition.setAdapter(canalLineConditionAdapter);
        getViewDataBinding().srCanalLineSubType.setAdapter(canalLineSubTypeAdapter);
        getViewDataBinding().srCanalLineType.setAdapter(canalLineTypeAdapter);
        viewModel.loadData();
    }

}