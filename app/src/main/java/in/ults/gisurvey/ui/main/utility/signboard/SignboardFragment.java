package in.ults.gisurvey.ui.main.utility.signboard;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityRoadSignboardDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class SignboardFragment extends BaseFragment<FragmentUtilityRoadSignboardDetailsBinding, SignboardViewModel> implements SignboardNavigator {

    public static final String TAG = SignboardFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SignboardViewModel viewModel;

    private String imagePath1 = "";
    static final int SIGN_BOARD_PLACE_ERROR = 1;
    static final int SIGN_BOARD_CATEGORY_ERROR = 2;
    static final int SIGN_BOARD_REMARKS_ERROR = 3;
    static final int SIGN_BOARD_COMMON_ERROR = 4;

    @Inject
    CommonDropDownAdapter roadSignboardCategoryAdapter;

    public static SignboardFragment newInstance() {
        Bundle args = new Bundle();
        SignboardFragment fragment = new SignboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_road_signboard_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public SignboardViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SignboardViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String place = Objects.requireNonNull(getViewDataBinding().etRoadSignboardPlace.getText()).toString().trim();
        String category = (String) getViewDataBinding().srRoadSignboardCategory.getTag();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(place, category, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(place, category, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(place, category, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case SIGN_BOARD_PLACE_ERROR:
                getViewDataBinding().layoutRoadSignboardPlace.setError(error);
                getViewDataBinding().layoutRoadSignboardPlace.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadSignboardPlace, getViewDataBinding().layoutRoadSignboardPlace);
                break;
            case SIGN_BOARD_CATEGORY_ERROR:
                getViewDataBinding().layoutRoadSignboardCategory.setError(error);
                getViewDataBinding().layoutRoadSignboardCategory.getParent().requestChildFocus
                        (getViewDataBinding().layoutRoadSignboardCategory, getViewDataBinding().layoutRoadSignboardCategory);
                break;
            case SIGN_BOARD_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case SIGN_BOARD_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutRoadSignboardPlace.setErrorEnabled(false);
        getViewDataBinding().layoutRoadSignboardCategory.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_signboard);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srRoadSignboardCategory.setAdapter(roadSignboardCategoryAdapter);
        viewModel.loadData();
    }

}