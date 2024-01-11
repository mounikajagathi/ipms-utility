package in.ults.gisurvey.ui.auth.login;


import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentLoginBinding;
import in.ults.gisurvey.ui.base.BaseFragment;
import in.ults.gisurvey.ui.main.MainActivity;
import in.ults.gisurvey.utils.CommonUtils;


public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> implements LoginNavigator {

    public static final String TAG = LoginFragment.class.getSimpleName();

    static final int USERNAME_ERROR = 1;
    static final int PASSWORD_ERROR = 2;

    @Inject
    ViewModelProviderFactory factory;

    private LoginViewModel viewModel;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public LoginViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return viewModel;
    }

    /**
     * to call login method if username and password validation is success
     *
     */
    @Override
    public void login() {
        hideKeyboard();
        String userName = Objects.requireNonNull(getViewDataBinding().etUserName.getText()).toString();
        String password = Objects.requireNonNull(getViewDataBinding().etPassword.getText()).toString();
        String deviceId = CommonUtils.getDeviceId(getBaseActivity());
        if (viewModel.validateFields(userName, password)) {
            viewModel.login(userName, password, deviceId);
        }
    }

    /**
     *
     * set validation error message
     * @param code
     * @param error
     */
    @Override
    public void validationError(int code, String error) {
        switch (code) {
            case USERNAME_ERROR:
                getViewDataBinding().layoutUserName.setError(error);
                break;
            case PASSWORD_ERROR:
                getViewDataBinding().layoutPassword.setError(error);
                break;
        }
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.newIntent(getBaseActivity()));
        getBaseActivity().finish();
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutUserName.setErrorEnabled(false);
        getViewDataBinding().layoutPassword.setErrorEnabled(false);
    }
}