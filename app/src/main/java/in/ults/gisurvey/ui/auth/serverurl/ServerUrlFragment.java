package in.ults.gisurvey.ui.auth.serverurl;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentServerUrlBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class ServerUrlFragment extends BaseFragment<FragmentServerUrlBinding, ServerUrlViewModel> implements ServerUrlNavigator {

    public static final String TAG = ServerUrlFragment.class.getSimpleName();
    static final int ADDRESS_ERROR = 1;
    static final int PORT_ERROR = 2;
    @Inject
    ViewModelProviderFactory factory;
    private ServerUrlViewModel viewModel;


    public static ServerUrlFragment newInstance() {
        Bundle args = new Bundle();
        ServerUrlFragment fragment = new ServerUrlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.setData();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_server_url;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ServerUrlViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ServerUrlViewModel.class);
        return viewModel;
    }

    /**
     * to call reset password method if email address validation is success
     */
    @Override
    public void verify() {
        hideKeyboard();
        String serverAddress = Objects.requireNonNull(getViewDataBinding().etServerUrl.getText()).toString().trim();
        String portNo = Objects.requireNonNull(getViewDataBinding().etPortNo.getText()).toString().trim();
        if (viewModel.validateFields(serverAddress,portNo)) {
            viewModel.verifyAddress(serverAddress,portNo);
        }
    }

    @Override
    public void validationError(int code, String error){
        switch (code) {
            case ADDRESS_ERROR:
                getViewDataBinding().layoutServerAddress.setError(error);
                getViewDataBinding().layoutServerAddress.getParent().requestChildFocus
                        (getViewDataBinding().layoutServerAddress, getViewDataBinding().layoutServerAddress);
                break;
            case PORT_ERROR:
                getViewDataBinding().layoutPortNo.setError(error);
                getViewDataBinding().layoutPortNo.getParent().requestChildFocus
                        (getViewDataBinding().layoutPortNo, getViewDataBinding().layoutPortNo);
                break;
        }
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutServerAddress.setErrorEnabled(false);
    }


    @Override
    public void onResetPasswordSuccess() {
        getBaseActivity().onBackPressed();
    }
}