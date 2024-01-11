package in.ults.gisurvey.ui.auth.forgotpassword;


import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.FragmentForgotPasswordBinding;
import in.ults.gisurvey.ui.base.BaseFragment;


public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel> implements ForgotPasswordNavigator {

    public static final String TAG = ForgotPasswordFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ForgotPasswordViewModel viewModel;


    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(getBaseActivity(), this);
    }

    @Override
    public ForgotPasswordViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ForgotPasswordViewModel.class);
        return viewModel;
    }

    /**
     * to call reset password method if email address validation is success
     */
    @Override
    public void resetPassword() {
        hideKeyboard();
        String emailAddress = Objects.requireNonNull(getViewDataBinding().etEmailAddress.getText()).toString().trim();
        if (viewModel.validateFields(emailAddress)) {
            viewModel.resetPassword(emailAddress);
        }
    }

    @Override
    public void validationError(int code, String error){
        getViewDataBinding().layoutEmailAddress.setError(error);
    }

    @Override
    public void clearValidationErrors() {
        getViewDataBinding().layoutEmailAddress.setErrorEnabled(false);
    }


    @Override
    public void onResetPasswordSuccess() {
        getBaseActivity().onBackPressed();
    }
}