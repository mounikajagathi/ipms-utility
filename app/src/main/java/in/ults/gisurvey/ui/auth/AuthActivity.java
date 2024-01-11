package in.ults.gisurvey.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.ActivityAuthBinding;
import in.ults.gisurvey.ui.auth.forgotpassword.ForgotPasswordFragment;
import in.ults.gisurvey.ui.auth.login.LoginFragment;
import in.ults.gisurvey.ui.auth.serverurl.ServerUrlFragment;
import in.ults.gisurvey.ui.base.BaseActivity;


public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthViewModel> implements AuthNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private AuthViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this,this);
    }

    @Override
    public AuthViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {

//        showLoginFragment(false);
        showServerUrlFragment(false);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    /**
     *to load login fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showLoginFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, LoginFragment.newInstance(), LoginFragment.TAG, isAddedToBackStack);
    }

    /**
     *to load forgot password fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showForgotPasswordFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ForgotPasswordFragment.newInstance(), ForgotPasswordFragment.TAG, isAddedToBackStack);
    }

    /**
     *to load server url entring fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showServerUrlFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ServerUrlFragment.newInstance(), ServerUrlFragment.TAG, isAddedToBackStack);
    }
}
