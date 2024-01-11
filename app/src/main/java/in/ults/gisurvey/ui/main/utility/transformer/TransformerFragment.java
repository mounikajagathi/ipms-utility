package in.ults.gisurvey.ui.main.utility.transformer;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityTransformerDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class TransformerFragment extends BaseFragment<FragmentUtilityTransformerDetailsBinding, TransformerViewModel> implements TransformerNavigator {

    public static final String TAG = TransformerFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private TransformerViewModel viewModel;
    @Inject
    CommonDropDownAdapter transformerCategoryAdapter;

    private String imagePath1 = "";
    static final int TRANSFORMER_LOCATION_ERROR = 1;
    static final int TRANSFORMER_CATEGORY_ERROR = 2;
    static final int TRANSFORMER_TRANSFORMER_ERROR = 3;
    static final int TRANSFORMER_REMARKS_ERROR = 4;
    static final int TRANSFORMER_COMMON_ERROR = 5;

    public static TransformerFragment newInstance() {
        Bundle args = new Bundle();
        TransformerFragment fragment = new TransformerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_transformer_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public TransformerViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(TransformerViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etLocation.getText()).toString().trim();
        String category = (String) getViewDataBinding().srTransformerCategory.getTag();
        String transformerDetails = (String) getViewDataBinding().srTransformerCategory.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, category, transformerDetails, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, category, transformerDetails, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, category, transformerDetails, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case TRANSFORMER_LOCATION_ERROR:
                getViewDataBinding().layoutTransformerLocation.setError(error);
                getViewDataBinding().layoutTransformerLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutTransformerLocation, getViewDataBinding().layoutTransformerLocation);
                break;
            case TRANSFORMER_CATEGORY_ERROR:
                getViewDataBinding().layoutTransformerCategory.setError(error);
                getViewDataBinding().layoutTransformerCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutTransformerCategory, getViewDataBinding().layoutTransformerCategory);
                break;
            case TRANSFORMER_TRANSFORMER_ERROR:
                getViewDataBinding().layoutTransformerDetails.setError(error);
                getViewDataBinding().layoutTransformerDetails.getParent().requestChildFocus
                        (getViewDataBinding().layoutTransformerDetails, getViewDataBinding().layoutTransformerDetails);
                break;
            case TRANSFORMER_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case TRANSFORMER_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutTransformerLocation.setErrorEnabled(false);
        getViewDataBinding().layoutTransformerCategory.setErrorEnabled(false);
        getViewDataBinding().layoutTransformerDetails.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_transformer);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srTransformerCategory.setAdapter(transformerCategoryAdapter);
        viewModel.loadData();
    }

}