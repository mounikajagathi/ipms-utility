package in.ults.gisurvey.ui.main.utility;

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
import in.ults.gisurvey.databinding.ActivitySurveyBinding;
import in.ults.gisurvey.databinding.ActivityUtilityBinding;
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.main.utility.bridge.BridgeFragment;
import in.ults.gisurvey.ui.main.utility.busstop.BusStopFragment;
import in.ults.gisurvey.ui.main.utility.canalline.CanalLineFragment;
import in.ults.gisurvey.ui.main.utility.culvert.CulvertFragment;
import in.ults.gisurvey.ui.main.utility.divider.DividerFragment;
import in.ults.gisurvey.ui.main.utility.drainage.DrainageFragment;
import in.ults.gisurvey.ui.main.utility.garbage.GarbageFragment;
import in.ults.gisurvey.ui.main.utility.highmastlight.HighMastLightFragment;
import in.ults.gisurvey.ui.main.utility.home.UtilityHomeFragment;
import in.ults.gisurvey.ui.main.utility.junction.JunctionFragment;
import in.ults.gisurvey.ui.main.utility.lowmastlight.LowMastLightFragment;
import in.ults.gisurvey.ui.main.utility.park.ParkFragment;
import in.ults.gisurvey.ui.main.utility.parking.ParkingFragment;
import in.ults.gisurvey.ui.main.utility.playground.PlaygroundFragment;
import in.ults.gisurvey.ui.main.utility.pond.PondFragment;
import in.ults.gisurvey.ui.main.utility.road.RoadFragment;
import in.ults.gisurvey.ui.main.utility.sidewalk.SideWalkFragment;
import in.ults.gisurvey.ui.main.utility.signboard.SignboardFragment;
import in.ults.gisurvey.ui.main.utility.stadium.StadiumFragment;
import in.ults.gisurvey.ui.main.utility.streetlight.StreetLightFragment;
import in.ults.gisurvey.ui.main.utility.streettap.StreetTapFragment;
import in.ults.gisurvey.ui.main.utility.tank.TankFragment;
import in.ults.gisurvey.ui.main.utility.taxistand.TaxiStandFragment;
import in.ults.gisurvey.ui.main.utility.transformer.TransformerFragment;
import in.ults.gisurvey.ui.main.utility.well.WellFragment;

public class UtilityActivity extends BaseActivity<ActivityUtilityBinding, UtilityViewModel> implements UtilityNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;

    private UtilityViewModel viewModel;

    public static final String SURVEY_TYPE = "survey_type";

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_utility;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this, this);
    }

    @Override
    public UtilityViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(UtilityViewModel.class);
        return viewModel;
    }

    public static Intent newIntent(Context context, boolean partialSurvey) {
        Intent intent = new Intent(context, UtilityActivity.class);
        intent.putExtra(SURVEY_TYPE, partialSurvey);
        return intent;
    }


    @Override
    protected void setUp(@Nullable Bundle savedInstanceState) {
        super.setUp(savedInstanceState);

        setSupportActionBar(getViewDataBinding().toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener(v -> onBackPressed());
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean(SURVEY_TYPE)) {
            showUtilityHomeFragment(false);
        } else {
            showUtilityHomeFragment(false);
        }
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        getViewDataBinding().toolbar.setTitle(toolbarTitle != null ? toolbarTitle : "");
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void showUtilityHomeFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, UtilityHomeFragment.newInstance(), UtilityHomeFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showBridgeFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, BridgeFragment.newInstance(), BridgeFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showBusStopFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, BusStopFragment.newInstance(), BusStopFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showCanalLineFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, CanalLineFragment.newInstance(), CanalLineFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showCulvertFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, CulvertFragment.newInstance(), CulvertFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showDividerFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, DividerFragment.newInstance(), DividerFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showDrainageFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, DrainageFragment.newInstance(), DrainageFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showGarbagePointFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, GarbageFragment.newInstance(), GarbageFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showHighMastLightFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, HighMastLightFragment.newInstance(), HighMastLightFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showLowMastLightFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, LowMastLightFragment.newInstance(), LowMastLightFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showParkFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ParkFragment.newInstance(), ParkFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showParkingAreaFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ParkingFragment.newInstance(), ParkingFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showPlaygroundFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, PlaygroundFragment.newInstance(), PlaygroundFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showPondFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, PondFragment.newInstance(), PondFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showRoadsFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, RoadFragment.newInstance(), RoadFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showRoadJunctionFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, JunctionFragment.newInstance(), JunctionFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showRoadSideWalkFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SideWalkFragment.newInstance(), SideWalkFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showRoadSignboardFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SignboardFragment.newInstance(), SignboardFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showStadiumFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, StadiumFragment.newInstance(), StadiumFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showStreetLightFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, StreetLightFragment.newInstance(), StreetLightFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showStreetTapFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, StreetTapFragment.newInstance(), StreetTapFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showTankFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, TankFragment.newInstance(), TankFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showTaxiStandFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, TaxiStandFragment.newInstance(), TaxiStandFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showTransformerFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, TransformerFragment.newInstance(), TransformerFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showWellFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, WellFragment.newInstance(), WellFragment.TAG, isAddedToBackStack);
    }
}
