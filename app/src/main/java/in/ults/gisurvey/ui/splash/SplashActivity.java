package in.ults.gisurvey.ui.splash;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.ActivitySplashBinding;
import in.ults.gisurvey.ui.auth.AuthActivity;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;

    SplashViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this, this);
    }

    @Override
    public SplashViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        return viewModel;
    }

    /**
     * to call auth activity
     */
    @Override
    public void openAuthActivity() {
        startActivity(AuthActivity.newIntent(SplashActivity.this));
        finish();
    }

    /**
     * to call mainactivity
     */
    @Override
    public void openMainActivity() {
        startActivity(MainActivity.newIntent(SplashActivity.this));
        finish();
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.runSplash();
    }
}
