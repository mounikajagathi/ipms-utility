package in.ults.gisurvey.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import in.ults.gisurvey.BR;
import in.ults.gisurvey.R;
import in.ults.gisurvey.ViewModelProviderFactory;
import in.ults.gisurvey.databinding.ActivityDetailsBinding;
import in.ults.gisurvey.ui.base.BaseActivity;


public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> implements DetailsNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private DetailsViewModel viewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, DetailsActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this,this);
    }

    @Override
    public DetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel.class);
        return viewModel;
    }

    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);
        viewModel.syncData();
    }
}
