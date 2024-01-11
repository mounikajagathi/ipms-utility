package in.ults.gisurvey.ui.main.utility.playground;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentProfileBinding;
import in.ults.gisurvey.databinding.FragmentUtilityPlaygroundDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.utils.adapters.CommonDropDownAdapter;


public class PlaygroundFragment extends BaseFragment<FragmentUtilityPlaygroundDetailsBinding, PlaygroundViewModel> implements PlaygroundNavigator {

    public static final String TAG = PlaygroundFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private PlaygroundViewModel viewModel;

    private String imagePath1 = "";
    static final int PLAYGROUND_LOCATION_ERROR = 1;
    static final int PLAYGROUND_GROUND_NAME_ERROR = 2;
    static final int PLAYGROUND_GROUND_TYPE_ERROR = 3;
    static final int PLAYGROUND_AREA_ERROR = 4;
    static final int PLAYGROUND_REMARKS_ERROR = 5;
    static final int PLAYGROUND_COMMON_ERROR = 6;

    @Inject
    CommonDropDownAdapter playgroundTypeAdapter;

    public static PlaygroundFragment newInstance() {
        Bundle args = new Bundle();
        PlaygroundFragment fragment = new PlaygroundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_playground_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public PlaygroundViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(PlaygroundViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String location = Objects.requireNonNull(getViewDataBinding().etPlaygroundLocation.getText()).toString().trim();
        String groundName = Objects.requireNonNull(getViewDataBinding().etPlaygroundName.getText()).toString().trim();
        String groundType = (String) getViewDataBinding().srPlaygroundType.getTag();
        String area = Objects.requireNonNull(getViewDataBinding().etPlaygroundArea.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
        if (!isPartial) {
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(location, groundName, groundType, area, remarks, imagePath1)) {
                viewModel.saveUtilityDetails(location, groundName, groundType, area, remarks, imagePath1, true);
            }

        } else {
            //Partial Saving
            //No need of validation
            viewModel.saveUtilityDetails(location, groundName, groundType, area, remarks, imagePath1, false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case PLAYGROUND_LOCATION_ERROR:
                getViewDataBinding().layoutPlaygroundLocation.setError(error);
                getViewDataBinding().layoutPlaygroundLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlaygroundLocation, getViewDataBinding().layoutPlaygroundLocation);
                break;
            case PLAYGROUND_GROUND_NAME_ERROR:
                getViewDataBinding().layoutPlaygroundName.setError(error);
                getViewDataBinding().layoutPlaygroundName.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlaygroundName, getViewDataBinding().layoutPlaygroundName);
                break;
            case PLAYGROUND_GROUND_TYPE_ERROR:
                getViewDataBinding().layoutPlaygroundType.setError(error);
                getViewDataBinding().layoutPlaygroundType.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlaygroundType, getViewDataBinding().layoutPlaygroundType);
                break;
            case PLAYGROUND_AREA_ERROR:
                getViewDataBinding().layoutPlaygroundArea.setError(error);
                getViewDataBinding().layoutPlaygroundArea.getParent().requestChildFocus
                        (getViewDataBinding().layoutPlaygroundArea, getViewDataBinding().layoutPlaygroundArea);
                break;
            case PLAYGROUND_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case PLAYGROUND_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutPlaygroundLocation.setErrorEnabled(false);
        getViewDataBinding().layoutPlaygroundName.setErrorEnabled(false);
        getViewDataBinding().layoutPlaygroundType.setErrorEnabled(false);
        getViewDataBinding().layoutPlaygroundArea.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_playground);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        getViewDataBinding().srPlaygroundType.setAdapter(playgroundTypeAdapter);
        viewModel.loadData();
    }

}