package in.ults.gisurvey.ui.survey;

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
import in.ults.gisurvey.ui.base.BaseActivity;
import in.ults.gisurvey.ui.survey.basement.BasementFragment;
import in.ults.gisurvey.ui.survey.building.BuildingFragment;
import in.ults.gisurvey.ui.survey.completesurvey.CompleteSurveyFragment;
import in.ults.gisurvey.ui.survey.establishment.EstablishmentFragment;
import in.ults.gisurvey.ui.survey.floorcount.FloorCountFragment;
import in.ults.gisurvey.ui.survey.groundfloorselection.GroundFloorSelectionFragment;
import in.ults.gisurvey.ui.survey.image.ImagesFragment;
import in.ults.gisurvey.ui.survey.livehood.LiveHoodFragment;
import in.ults.gisurvey.ui.survey.location.LocationFragment;
import in.ults.gisurvey.ui.survey.selection.ScreenSelectionGridFragment;
import in.ults.gisurvey.ui.survey.startsurvey.StartSurveyFragment;
import in.ults.gisurvey.ui.survey.member.MemberFragment;
import in.ults.gisurvey.ui.survey.more.MoreFragment;
import in.ults.gisurvey.ui.survey.owner.OwnerFragment;
import in.ults.gisurvey.ui.survey.pointstatus.PointStatusFragment;
import in.ults.gisurvey.ui.survey.property.PropertyFragment;
import in.ults.gisurvey.ui.survey.road.RoadFragment;
import in.ults.gisurvey.ui.survey.surveygrid.SurveyGridFragment;
import in.ults.gisurvey.ui.survey.tax.TaxFragment;
import in.ults.gisurvey.ui.survey.tenant.TenantFragment;

public class SurveyActivity extends BaseActivity<ActivitySurveyBinding, SurveyViewModel> implements SurveyNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;

    private SurveyViewModel viewModel;

    public static final String SURVEY_TYPE = "survey_type";

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_survey;
    }

    @Override
    public void initNavigator() {
        viewModel.setNavigator(this, this);
    }

    @Override
    public SurveyViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SurveyViewModel.class);
        return viewModel;
    }

    public static Intent newIntent(Context context, boolean partialSurvey) {
        Intent intent = new Intent(context, SurveyActivity.class);
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
            showBuildingCheckFragment(false);
        } else {
            showMapFragment(false);
        }
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        if (getViewDataBinding().toolbar != null) {
            getViewDataBinding().toolbar.setTitle(toolbarTitle != null ? toolbarTitle : "");
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    /**
     * to load building check fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showBuildingCheckFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, PointStatusFragment.newInstance(), PointStatusFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load floor count fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showFloorCountFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, FloorCountFragment.newInstance(), FloorCountFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load ground floor selection fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showGroundFloorSelection(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, new GroundFloorSelectionFragment(), GroundFloorSelectionFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load location fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showLocationFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, LocationFragment.newInstance(), LocationFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load basement fragment
     * @param isAddedToBackStack
     */

    @Override
    public void showBasementFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, BasementFragment.newInstance(), BasementFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load owner fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showOwnerFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, OwnerFragment.newInstance(), OwnerFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load screen selection fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showSelectionFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ScreenSelectionGridFragment.newInstance(), ScreenSelectionGridFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load road fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showRoadFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, RoadFragment.newInstance(), RoadFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load establishment fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showEstablishmentFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, EstablishmentFragment.newInstance(), EstablishmentFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load tenant fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showTenantFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, TenantFragment.newInstance(), TenantFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load tax fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showTaxFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, TaxFragment.newInstance(), TaxFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load member fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showMemberFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, MemberFragment.newInstance(), MemberFragment.TAG, isAddedToBackStack);
    }

    /**
     * to laod live hood fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showLiveHoodFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, LiveHoodFragment.newInstance(), LiveHoodFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load building fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showBuildingFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, BuildingFragment.newInstance(), BuildingFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load property fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showPropertyFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, PropertyFragment.newInstance(), PropertyFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load survey grid fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showSurveyGridFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, SurveyGridFragment.newInstance(), SurveyGridFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load map fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showMapFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, StartSurveyFragment.newInstance(), SurveyGridFragment.TAG, isAddedToBackStack);
    }

    /**
     * to load complete survey fragment
     * @param isAddedToBackStack
     */
    @Override
    public void showCompleteSurvey(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, CompleteSurveyFragment.newInstance(), CompleteSurveyFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showImageFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, ImagesFragment.newInstance(), ImagesFragment.TAG, isAddedToBackStack);
    }

    @Override
    public void showMoreFragment(boolean isAddedToBackStack) {
        showFragment(R.id.fragmentContainer, MoreFragment.newInstance(), MoreFragment.TAG, isAddedToBackStack);
     }
}
