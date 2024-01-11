package in.ults.gisurvey.ui.main.utility.busstop;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentUtilityBusStopDetailsBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class BusStopFragment extends BaseFragment<FragmentUtilityBusStopDetailsBinding, BusStopViewModel> implements BusStopNavigator {

    public static final String TAG = BusStopFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private BusStopViewModel viewModel;

    private String imagePath1 = "";
    static final int BUS_STOP_LOCATION_ERROR = 1;
    static final int BUS_STOP_REMARKS_ERROR = 2;
    static final int BUS_STOP_COMMON_ERROR = 3;

    public static BusStopFragment newInstance() {
        Bundle args = new Bundle();
        BusStopFragment fragment = new BusStopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_utility_bus_stop_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public BusStopViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(BusStopViewModel.class);
        return viewModel;
    }

    @Override
    public void saveUtilityDetails(boolean isPartial) {
        getBaseActivity().hideKeyboard();
        String busStopLocation = Objects.requireNonNull(getViewDataBinding().etBusStopLocation.getText()).toString().trim();
        String busStopRemarks = Objects.requireNonNull(getViewDataBinding().etRemarks.getText()).toString().trim();
       if(!isPartial){
            //Data submission
            //So validation is necessary
            if (viewModel.validateFields(busStopLocation, busStopRemarks,imagePath1)) {
                viewModel.saveUtilityDetails(busStopLocation, busStopRemarks,imagePath1,true);
            }

        }else {
            //Partial Saving
            //No need of validation
          viewModel.saveUtilityDetails(busStopLocation, busStopLocation,imagePath1,false);
        }
    }

    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case BUS_STOP_LOCATION_ERROR:
                getViewDataBinding().layoutBusStopLocation.setError(error);
                getViewDataBinding().layoutBusStopLocation.getParent().requestChildFocus
                        (getViewDataBinding().layoutBusStopLocation, getViewDataBinding().layoutBusStopLocation);
                break;
            case BUS_STOP_REMARKS_ERROR:
                getViewDataBinding().layoutRemarks.setError(error);
                getViewDataBinding().layoutRemarks.getParent().requestChildFocus
                        (getViewDataBinding().layoutRemarks, getViewDataBinding().layoutRemarks);
                break;
            case BUS_STOP_COMMON_ERROR:
                showToast(error);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutBusStopLocation.setErrorEnabled(false);
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
        return getString(R.string.toolbar_utility_bus_stop);
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
    }

}